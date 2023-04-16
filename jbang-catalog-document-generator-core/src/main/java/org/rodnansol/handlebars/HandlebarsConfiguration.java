package org.rodnansol.handlebars;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

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
    public Handlebars handlebars() {
        return new Handlebars().with(new ClassPathTemplateLoader(), new WorkingDirectoryAwareRecursiveFileTemplateLoader(".", WorkingDirectoryProvider.INSTANCE));
    }

}
