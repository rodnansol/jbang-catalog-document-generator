package org.rodnansol.handlebars;

import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.document.DocumentGenerationProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class that is able to return the current working directory.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ApplicationScoped
class WorkingDirectoryProvider {

    private final DocumentGenerationProperties documentGenerationProperties;

    public WorkingDirectoryProvider(DocumentGenerationProperties documentGenerationProperties){
        this.documentGenerationProperties = documentGenerationProperties;
    }

    /**
     * Returns the current working directory.
     *
     * @return current working directory.
     */
    public Path getCurrentWorkingDirectoryPath() {
        return Paths.get(documentGenerationProperties.currentWorkingDirectory()).toAbsolutePath().normalize();
    }

}
