package org.rodnansol.handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import org.rodnansol.document.DocumentGenerationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Helper that renders the file at the given path built by the current working directory and by the value of the context to the document.
 * <p>
 * Usage:
 * {@code
 * {{#render_custom_usage alias-name}}{{/render_custom_usage}}
 * }
 *
 * @author nandorholozsnyak
 * @since 0.2.0
 */
class CustomUsageRenderHelper implements Helper<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUsageRenderHelper.class);

    private final DocumentGenerationProperties documentGenerationProperties;

    public CustomUsageRenderHelper(DocumentGenerationProperties documentGenerationProperties) {
        this.documentGenerationProperties = documentGenerationProperties;
    }

    @Override
    public Object apply(String context, Options options) throws IOException {
        Path path = Path.of(getPath(context));
        LOGGER.debug("Resolving usage for entry:[{}] at path:[{}]", context, path);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private String getPath(String context) {
        return documentGenerationProperties.aliasUsageFolderPath() + "/" + context + CustomUsageConstants.EXTENSION;
    }
}
