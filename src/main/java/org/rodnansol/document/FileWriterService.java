package org.rodnansol.document;


import jakarta.enterprise.context.Dependent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
class FileWriterService {

    /**
     *
     * @param outputFile
     * @param content
     */
    public void writeContentToFile(File outputFile, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(outputFile))) {
            writer.write(content);
        }
    }

}
