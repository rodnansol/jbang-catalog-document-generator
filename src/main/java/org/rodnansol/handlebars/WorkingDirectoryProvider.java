package org.rodnansol.handlebars;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class that is able to return the current working directory.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
class WorkingDirectoryProvider {

    public static final WorkingDirectoryProvider INSTANCE = new WorkingDirectoryProvider();

    private WorkingDirectoryProvider(){}

    /**
     * Returns the current working directory.
     *
     * @return current working directory.
     */
    public Path getCurrentWorkingDirectoryPath() {
        return Paths.get(".").toAbsolutePath().normalize();
    }

}
