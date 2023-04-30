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
     * Returns the current working directory where the custom Handlebars templates and the custom header and footer content's can be found.
     * <p>
     * <b>This setting will not affect the input catalog file and the path of the output file.</b>
     *
     * @since 0.2.0
     */
    String currentWorkingDirectory();

    /**
     * Returns the path of the files that contains the usage files for the aliases.
     *
     * @since 0.3.0
     */
    String aliasUsageFolderPath();

}
