package org.rodnansol.document;

import io.smallrye.config.ConfigMapping;

/**
 * Document generation properties.
 *
 * @author nandorholozsnyak
 * @since 0.2.0
 */
@ConfigMapping(prefix = "app.document")
public interface DocumentGenerationProperties {

    /**
     * Get the current working directory.
     */
    String currentWorkingDirectory();

}
