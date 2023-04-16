package org.rodnansol.commands;

import org.rodnansol.document.CreateDocumentCommand;
import org.rodnansol.document.DocumentCustomization;
import org.rodnansol.document.DocumentGenerationAction;
import org.rodnansol.document.DocumentGenerationException;
import org.rodnansol.document.TemplateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class GenerateCommand implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateCommand.class);
    private final DocumentGenerationAction documentGenerationAction;
    private File inputFile;
    private File outputFile = new File("jbang-catalog.adoc");
    private TemplateType templateType = TemplateType.ADOC;
    private String customTemplatePath;
    private String customHeaderFilePath;
    private String customFooterFilePath;
    private boolean enableTableOfContents;
    private String tocPlacement = "auto";
    private String tocTitle;
    private int tocLevels = 2;
    private boolean checkChecksum = true;
    private boolean includeGenerationDate = false;
    private boolean headerAndFooterDirectRender = false;

    public GenerateCommand(DocumentGenerationAction documentGenerationAction) {
        this.documentGenerationAction = documentGenerationAction;
    }

    @Override
    public void run() {
        try {
            DocumentCustomization documentCustomization = setupDocumentCustomization();
            documentGenerationAction.parseJsonFileAndGenerateDocument(new CreateDocumentCommand(inputFile, outputFile, templateType, customTemplatePath, documentCustomization, checkChecksum));
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
        documentCustomization.setIncludeGenerationDate(includeGenerationDate);
        documentCustomization.setHeaderAndFooterDirectRender(headerAndFooterDirectRender);
        return documentCustomization;
    }

    public DocumentGenerationAction getDocumentGenerationAction() {
        return documentGenerationAction;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public TemplateType getTemplateType() {
        return templateType;
    }

    public void setTemplateType(TemplateType templateType) {
        this.templateType = templateType;
    }

    public String getCustomTemplatePath() {
        return customTemplatePath;
    }

    public void setCustomTemplatePath(String customTemplatePath) {
        this.customTemplatePath = customTemplatePath;
    }

    public String getCustomHeaderFilePath() {
        return customHeaderFilePath;
    }

    public void setCustomHeaderFilePath(String customHeaderFilePath) {
        this.customHeaderFilePath = customHeaderFilePath;
    }

    public String getCustomFooterFilePath() {
        return customFooterFilePath;
    }

    public void setCustomFooterFilePath(String customFooterFilePath) {
        this.customFooterFilePath = customFooterFilePath;
    }

    public boolean isEnableTableOfContents() {
        return enableTableOfContents;
    }

    public void setEnableTableOfContents(boolean enableTableOfContents) {
        this.enableTableOfContents = enableTableOfContents;
    }

    public String getTocPlacement() {
        return tocPlacement;
    }

    public void setTocPlacement(String tocPlacement) {
        this.tocPlacement = tocPlacement;
    }

    public String getTocTitle() {
        return tocTitle;
    }

    public void setTocTitle(String tocTitle) {
        this.tocTitle = tocTitle;
    }

    public int getTocLevels() {
        return tocLevels;
    }

    public void setTocLevels(int tocLevels) {
        this.tocLevels = tocLevels;
    }

    public boolean isCheckChecksum() {
        return checkChecksum;
    }

    public void setCheckChecksum(boolean checkChecksum) {
        this.checkChecksum = checkChecksum;
    }

    public boolean isIncludeGenerationDate() {
        return includeGenerationDate;
    }

    public void setIncludeGenerationDate(boolean includeGenerationDate) {
        this.includeGenerationDate = includeGenerationDate;
    }

    public boolean isHeaderAndFooterDirectRender() {
        return headerAndFooterDirectRender;
    }

    public void setHeaderAndFooterDirectRender(boolean headerAndFooterDirectRender) {
        this.headerAndFooterDirectRender = headerAndFooterDirectRender;
    }
}
