package org.rodnansol;

import jakarta.enterprise.context.Dependent;
import org.rodnansol.model.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
@CommandLine.Command(name = "convert", description = "Converts a JSON file to AsciiDoc documentation")
public class ConvertCommand implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertCommand.class);
    private final DocumentService documentService;
    @CommandLine.Parameters(index = "0", description = "The input JSON file to convert.")
    private String fileName;
    @CommandLine.Option(names = {"-o", "--output"}, description = "The output AsciiDoc file name. Defaults to 'output.adoc'")
    private String outputFileName = "output.adoc";
    @CommandLine.Option(names = {"-t", "--template"}, description = "Template path, by default it uses an internal template.")
    private String templatePath = "templates/template.adoc";

    public ConvertCommand(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public void run() {
        try {
            Catalog catalog = documentService.parseJsonFile(fileName);
            File outputFile = new File(outputFileName);
            documentService.generateAsciiDoc(templatePath, catalog, outputFile);
            LOGGER.info("AsciiDoc documentation generated successfully to: [{}]", outputFile.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.error("Error during document generation:", e);
        }
    }

}
