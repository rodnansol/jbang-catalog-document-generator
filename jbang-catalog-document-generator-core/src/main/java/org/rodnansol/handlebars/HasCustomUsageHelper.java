package org.rodnansol.handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import org.rodnansol.document.DocumentGenerationProperties;

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

    private final DocumentGenerationProperties documentGenerationProperties;

    public HasCustomUsageHelper(DocumentGenerationProperties documentGenerationProperties) {
        this.documentGenerationProperties = documentGenerationProperties;
    }

    @Override
    public Object apply(String context, Options options) throws IOException {
        return Files.exists(Path.of(getPath(context))) ? options.fn(context) : options.inverse(context);
    }

    private String getPath(String context) {
        return documentGenerationProperties.currentWorkingDirectory() + "/" + context + CustomUsageConstants.EXTENSION;
    }
}
