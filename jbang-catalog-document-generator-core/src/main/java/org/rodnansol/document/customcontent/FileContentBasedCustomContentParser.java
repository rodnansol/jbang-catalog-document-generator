package org.rodnansol.document.customcontent;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.Dependent;
import org.rodnansol.document.CustomContent;
import org.rodnansol.document.DocumentCustomization;
import org.rodnansol.document.DocumentGenerationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

/**
 * Abstract class that reads the content of the custom header and footer and uses it to populate the {@link CustomContent} instance.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
class FileContentBasedCustomContentParser implements CustomContentParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileContentBasedCustomContentParser.class);

    private final DocumentGenerationProperties documentGenerationProperties;

    FileContentBasedCustomContentParser(DocumentGenerationProperties documentGenerationProperties) {
        this.documentGenerationProperties = documentGenerationProperties;
    }

    @Override
    public CustomContent createCustomContent(DocumentCustomization documentCustomization) throws CustomContentParserException {
        String customHeaderContent = readContent(documentCustomization, DocumentCustomization::getCustomHeaderFilePath);
        String customFooterContent = readContent(documentCustomization, DocumentCustomization::getCustomFooterFilePath);
        return new CustomContent(customHeaderContent, customFooterContent);
    }

    private String readContent(DocumentCustomization documentCustomization, Function<DocumentCustomization, String> pathResolver) {
        String customPath = pathResolver.apply(documentCustomization);
        if (!StringUtil.isNullOrEmpty(customPath)) {
            try {
                String finalPath = documentGenerationProperties.currentWorkingDirectory() + "/" + customPath;
                LOGGER.debug("Reading custom content from path:[{}]", finalPath);
                byte[] bytes = Files.readAllBytes(Path.of(finalPath));
                return new String(bytes);
            } catch (IOException e) {
                throw new CustomContentParserException("Error during reading custom content", e);
            }
        }
        return null;
    }
}
