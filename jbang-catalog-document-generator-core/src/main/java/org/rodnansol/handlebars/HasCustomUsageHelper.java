package org.rodnansol.handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import org.rodnansol.document.DocumentGenerationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Helper that checks if a file exits at the given path built by the current working directory and by the value of the context.
 * <p>
 * Usage:
 * {@code
 * {{# has_custom_usage alias-name}}
 * {{/has_custom_usage}}
 * }
 *
 * @author nandorholozsnyak
 * @since 0.2.0
 */
class HasCustomUsageHelper implements Helper<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HasCustomUsageHelper.class);

    private final DocumentGenerationProperties documentGenerationProperties;

    public HasCustomUsageHelper(DocumentGenerationProperties documentGenerationProperties) {
        this.documentGenerationProperties = documentGenerationProperties;
    }

    @Override
    public Object apply(String context, Options options) throws IOException {
        boolean hasUsage = Files.exists(Path.of(getPath(context)));
        LOGGER.debug("Entry with value:[{}] usage file existence result:[{}]", context, hasUsage);
        return hasUsage ? options.fn(context) : options.inverse(context);
    }

    private String getPath(String context) {
        return documentGenerationProperties.aliasUsageFolderPath() + "/" + context + CustomUsageConstants.EXTENSION;
    }
}
