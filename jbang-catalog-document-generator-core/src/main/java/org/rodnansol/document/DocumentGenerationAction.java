package org.rodnansol.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.document.checksum.ChecksumCalculationException;
import org.rodnansol.document.checksum.ChecksumService;
import org.rodnansol.document.customcontent.CustomContentParserContext;
import org.rodnansol.document.model.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * Class handling the document generation logic.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ApplicationScoped
public class DocumentGenerationAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentGenerationAction.class);
    private final Handlebars handlebars;
    private final ObjectMapper objectMapper;
    private final FileWriterService fileWriterService;
    private final ChecksumService checksumService;
    private final CustomContentParserContext customContentParserContext;

    public DocumentGenerationAction(Handlebars handlebars,
                                    ObjectMapper objectMapper,
                                    FileWriterService fileWriterService,
                                    ChecksumService checksumService,
                                    CustomContentParserContext customContentParserContext) {
        this.handlebars = handlebars;
        this.objectMapper = objectMapper;
        this.fileWriterService = fileWriterService;
        this.checksumService = checksumService;
        this.customContentParserContext = customContentParserContext;
    }

    /**
     * Based on the incoming command object, parses the incoming file and creates the documentation based on that.
     * <p>
     * There is a checksum, to make sure we do not render the document every time, if the jbang-catalog.json input file's contents are not changed.
     *
     * @param createDocumentCommand command that stores all the information for the generation.
     */
    public void parseJsonFileAndGenerateDocument(CreateDocumentCommand createDocumentCommand) {
        Objects.requireNonNull(createDocumentCommand, "createDocumentCommand is NULL");
        LOGGER.info("Generating document based on command: {}", createDocumentCommand);
        try {
            if (createDocumentCommand.checkChecksum() && isChecksumEquals(createDocumentCommand)) {
                LOGGER.info("Input file has not changed since last time, generation is skipped....");
                LOGGER.info("If you wish to forcefully generate the file disable the checksum check, please use the help function to discover how to disable it.");
                return;
            }
            DocumentData documentData = getDocumentData(createDocumentCommand);
            generateDocument(createDocumentCommand, documentData);
            LOGGER.info("Documentation generated successfully to: [{}]", createDocumentCommand.outputFile().getAbsolutePath());
            updateChecksum(createDocumentCommand);
        } catch (IOException e) {
            throw new DocumentGenerationException("Error during creating document", e);
        }
    }

    private void updateChecksum(CreateDocumentCommand createDocumentCommand) {
        try {
            checksumService.saveChecksumForInputFile(createDocumentCommand.inputFile());
        } catch (ChecksumCalculationException e) {
            LOGGER.warn("Error during saving checksum into its place", e);
        }
    }

    private boolean isChecksumEquals(CreateDocumentCommand createDocumentCommand) {
        return checksumService.calculateChecksums(createDocumentCommand.inputFile()).checksumEquals();
    }

    private DocumentData getDocumentData(CreateDocumentCommand createDocumentCommand) throws IOException {
        Catalog catalog = parseJsonFile(createDocumentCommand.inputFile());
        CustomContent customContent = customContentParserContext.createCustomContentBy(createDocumentCommand.templateType(), createDocumentCommand.documentCustomization());
        return new DocumentData(catalog, createDocumentCommand.documentCustomization(), customContent, LocalDateTime.now());
    }

    private Catalog parseJsonFile(File file) throws IOException {
        LOGGER.debug("Reading catalog file:[{}]", file.getAbsolutePath());
        return objectMapper.readValue(file, Catalog.class);
    }

    private void generateDocument(CreateDocumentCommand createDocumentCommand, DocumentData documentData) throws IOException {
        String templatePath = Optional.ofNullable(createDocumentCommand.customTemplatePath())
            .orElse(createDocumentCommand.templateType().getTemplate());
        Template template = handlebars.compile(templatePath);
        String content = template.apply(documentData);
        File outputFile = createDocumentCommand.outputFile();
        fileWriterService.writeContentToFile(outputFile, content);
    }

}
