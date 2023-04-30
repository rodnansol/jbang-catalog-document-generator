package org.rodnansol.handlebars;

import com.github.jknack.handlebars.io.FileTemplateLoader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;

/**
 * This {@link FileTemplateLoader} implementation is able to find recursively a template file by it's location.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
class WorkingDirectoryAwareRecursiveFileTemplateLoader extends FileTemplateLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkingDirectoryAwareRecursiveFileTemplateLoader.class);

    private final WorkingDirectoryProvider workingDirectoryProvider;

    public WorkingDirectoryAwareRecursiveFileTemplateLoader(String basedir, WorkingDirectoryProvider workingDirectoryProvider) {
        super(basedir);
        this.workingDirectoryProvider = workingDirectoryProvider;
    }

    @Override
    protected URL getResource(String location) throws IOException {
        LOGGER.debug("Resolving template with the following location:[{}]", location);
        URL resource = super.getResource(location);
        if (resource != null) {
            return resource;
        }
        String normalizedInput = Path.of(location).normalize().toString();
        LOGGER.debug("Looking for path with normaliztion:[{}]", normalizedInput);
        File optionalFile = getPossilbeFile(location, normalizedInput);
        if (optionalFile == null) {
            LOGGER.debug("Unable to find resource at location:[{}], null will be returned", location);
            return null;
        }
        LOGGER.debug("Final location:[{}] for input:[{}]", optionalFile.getPath(), location);
        return optionalFile.exists() ? optionalFile.toURI().toURL() : null;
    }

    private File getPossilbeFile(String location, String normalizedInput) {
        String extension = FilenameUtils.getExtension(location);
        File pathUnderTest = workingDirectoryProvider.getCurrentWorkingDirectoryPath().toFile();
        LOGGER.debug("Listing files in folder to find the resource:[{}]", pathUnderTest);
        Collection<File> childFiles = FileUtils.listFiles(pathUnderTest, new String[]{extension}, true);
        return childFiles.stream()
            .filter(file -> file.getPath().contains(normalizedInput))
            .findFirst()
            .orElse(null);
    }

}
