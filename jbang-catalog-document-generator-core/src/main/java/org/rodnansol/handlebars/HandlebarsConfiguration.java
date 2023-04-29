package org.rodnansol.handlebars;

import com.github.jknack.handlebars.Handlebars;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.rodnansol.document.DocumentGenerationProperties;

/**
 * Handlebars configuration class.
 * <p>
 * Creates the required CDI beans.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ApplicationScoped
public class HandlebarsConfiguration {

    @Produces
    public Handlebars handlebars(DocumentGenerationProperties documentGenerationProperties,
                                 WorkingDirectoryProvider workingDirectoryProvider) {
        return new Handlebars()
            .registerHelper("has_custom_usage", new HasCustomUsageHelper(documentGenerationProperties))
            .registerHelper("render_custom_usage", new CustomUsageRenderHelper(documentGenerationProperties))
            .with(new ThreadAwareClassPathTemplateLoader(""), new WorkingDirectoryAwareRecursiveFileTemplateLoader(documentGenerationProperties.currentWorkingDirectory(), workingDirectoryProvider));
    }

}
