package org.rodnansol.commands;

import jakarta.enterprise.context.Dependent;
import org.rodnansol.document.CreateDocumentCommand;
import org.rodnansol.document.DocumentCustomization;
import org.rodnansol.document.DocumentGenerationAction;
import org.rodnansol.document.DocumentGenerationException;
import org.rodnansol.document.TemplateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
@CommandLine.Command(name = "generate", description = "Generates a documentation for a given jbang-catalog.json file")
public class GenerateCommand implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateCommand.class);
    private final DocumentGenerationAction documentGenerationAction;
    @CommandLine.Parameters(index = "0", description = "The input JSON file to convert.")
    File inputFile;
    @CommandLine.Option(names = {"-o", "--output"}, description = "The output AsciiDoc file name. Defaults to 'jbang-catalog.adoc'")
    File outputFile = new File("jbang-catalog.adoc");
    @CommandLine.Option(names = {"-tt", "--template-type"}, description = "Template type.")
    TemplateType templateType = TemplateType.ADOC;
    @CommandLine.Option(names = {"-t", "--template"}, description = "Custom template path.")
    String customTemplatePath;
    @CommandLine.Option(names = {"-ch", "--custom-header"}, description = "Path to a custom header.")
    String customHeaderFilePath;
    @CommandLine.Option(names = {"-cf", "--custom-footer"}, description = "Path to a custom footer.")
    String customFooterFilePath;
    @CommandLine.Option(names = {"-toc", "--table-of-contents"}, description = "Enable the table of contents or not")
    boolean enableTableOfContents;
    @CommandLine.Option(names = {"-toc-p", "--table-of-contents-placement"}, description = "Placement of the table of contents (In case of AsciiDoc)")
    String tocPlacement = "auto";
    @CommandLine.Option(names = {"-toc-t", "--table-of-contents-title"}, description = "Title of the table of contents")
    String tocTitle;
    @CommandLine.Option(names = {"-toc-l", "--table-of-contents-levels"}, description = "Levels of the table of contents (In case of AsciiDoc)")
    int tocLevels = 2;

    public GenerateCommand(DocumentGenerationAction documentGenerationAction) {
        this.documentGenerationAction = documentGenerationAction;
    }

    @Override
    public void run() {
        try {
            DocumentCustomization documentCustomization = setupDocumentCustomization();
            documentGenerationAction.parseJsonFileAndGenerateDocument(new CreateDocumentCommand(inputFile, outputFile, templateType, customTemplatePath, documentCustomization));
        } catch (DocumentGenerationException e) {
            LOGGER.error("Error during document generation:", e);
        }
    }

    private DocumentCustomization setupDocumentCustomization() {
        DocumentCustomization documentCustomization = new DocumentCustomization();
        documentCustomization.setCustomHeaderFilePath(customHeaderFilePath);
        documentCustomization.setCustomFooterFilePath(customFooterFilePath);
        documentCustomization.setEnableTableOfContents(enableTableOfContents);
        documentCustomization.setTocPlacement(tocPlacement);
        documentCustomization.setTocTitle(tocTitle);
        documentCustomization.setTocLevels(tocLevels);
        return documentCustomization;
    }

}
