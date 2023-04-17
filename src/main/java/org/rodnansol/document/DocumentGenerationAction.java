package org.rodnansol.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.document.customcontent.CustomContentParser;
import org.rodnansol.model.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ApplicationScoped
public class DocumentGenerationAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentGenerationAction.class);

    private final Handlebars handlebars;
    private final ObjectMapper objectMapper;
    private final FileWriterService fileWriterService;
    private final Map<TemplateType, CustomContentParser> templateTypeCustomContentParserMap;

    public DocumentGenerationAction(Handlebars handlebars, ObjectMapper objectMapper, FileWriterService fileWriterService, Map<TemplateType, CustomContentParser> templateTypeCustomContentParserMap) {
        this.handlebars = handlebars;
        this.objectMapper = objectMapper;
        this.fileWriterService = fileWriterService;
        this.templateTypeCustomContentParserMap = templateTypeCustomContentParserMap;
    }

    /**
     * @param createDocumentCommand
     */
    public void parseJsonFileAndGenerateDocument(CreateDocumentCommand createDocumentCommand) {
        Objects.requireNonNull(createDocumentCommand, "createDocumentCommand is NULL");
        LOGGER.info("Generating document based on command:[{}]", createDocumentCommand);
        try {
            DocumentData documentData = getDocumentData(createDocumentCommand);
            generateAsciiDoc(createDocumentCommand.templateType().getTemplate(), documentData, createDocumentCommand.outputFile());
            LOGGER.info("AsciiDoc documentation generated successfully to: [{}]", createDocumentCommand.outputFile().getAbsolutePath());
        } catch (IOException e) {
            throw new DocumentGenerationException("Error during creating document", e);
        }
    }

    private DocumentData getDocumentData(CreateDocumentCommand createDocumentCommand) throws IOException {
        Catalog catalog = parseJsonFile(createDocumentCommand.inputFile());
        CustomContentParser customContentParser = templateTypeCustomContentParserMap.get(createDocumentCommand.templateType());
        CustomContent customContent = customContentParser.createCustomContent(createDocumentCommand.documentCustomization());
        return new DocumentData(catalog, createDocumentCommand.documentCustomization(), customContent);
    }

    private Catalog parseJsonFile(File file) throws IOException {
        LOGGER.debug("Reading catalog file:[{}]", file.getAbsolutePath());
        return objectMapper.readValue(file, Catalog.class);
    }

    private void generateAsciiDoc(String templatePath, DocumentData documentData, File outputFile) throws IOException {
        Template template = handlebars.compile(templatePath);
        String content = template.apply(documentData);
        LOGGER.debug("Writing generated content to file:[{}]", outputFile.getAbsolutePath());
        fileWriterService.writeContentToFile(outputFile, content);
    }

}
