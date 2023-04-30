package org.rodnansol.document;


import jakarta.enterprise.context.Dependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Helper class that wraps file writing methods.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
class FileWriterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriterService.class);

    /**
     * Writes the content to the given output file.
     */
    public void writeContentToFile(File outputFile, String content) throws IOException {
        LOGGER.debug("Writing content to output file:[{}]", outputFile);
        if (!outputFile.getParentFile().exists()) {
            LOGGER.warn("Parent file/folder:[{}] does not exists, creating it.", outputFile.getParentFile());
            outputFile.getParentFile().mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(content);
        }
    }

}
