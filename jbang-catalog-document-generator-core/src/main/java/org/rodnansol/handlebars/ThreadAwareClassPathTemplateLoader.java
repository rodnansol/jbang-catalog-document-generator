package org.rodnansol.handlebars;

import com.github.jknack.handlebars.io.ClassPathTemplateLoader;

import java.net.URL;


public class ThreadAwareClassPathTemplateLoader extends ClassPathTemplateLoader {


    /**
     * Creates a new {@link ThreadAwareClassPathTemplateLoader}.
     *
     * @param prefix The view prefix. Required.
     * @param suffix The view suffix. Required.
     */
    public ThreadAwareClassPathTemplateLoader(final String prefix, final String suffix) {
        setPrefix(prefix);
        setSuffix(suffix);
    }

    /**
     * Creates a new {@link ThreadAwareClassPathTemplateLoader}.
     *
     * @param prefix The view prefix. Required.
     */
    public ThreadAwareClassPathTemplateLoader(final String prefix) {
        this(prefix, DEFAULT_SUFFIX);
    }

    /**
     * Creates a new {@link ThreadAwareClassPathTemplateLoader}. It looks for templates
     * stored in the root of the classpath.
     */
    public ThreadAwareClassPathTemplateLoader() {
        this("/");
    }


    @Override
    protected URL getResource(final String location) {
        return Thread.currentThread().getContextClassLoader().getResource(location);
    }
}
