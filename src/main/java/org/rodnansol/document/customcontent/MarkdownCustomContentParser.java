package org.rodnansol.document.customcontent;

import io.quarkus.runtime.util.StringUtil;
import org.rodnansol.document.CustomContent;
import org.rodnansol.document.DocumentCustomization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class MarkdownCustomContentParser implements CustomContentParser {

    @Override
    public CustomContent createCustomContent(DocumentCustomization documentCustomization) throws CustomContentParserException {
        String customHeaderContent = readContent(documentCustomization, DocumentCustomization::getCustomHeaderFilePath);
        String customFooterContent = readContent(documentCustomization, DocumentCustomization::getCustomFooterFilePath);
        return new CustomContent(customHeaderContent, customFooterContent);
    }

    private String readContent(DocumentCustomization documentCustomization, Function<DocumentCustomization, String> documentCustomizationConsumer) {
        String customHeader = documentCustomizationConsumer.apply(documentCustomization);
        if (!StringUtil.isNullOrEmpty(customHeader)) {
            try {
                byte[] bytes = Files.readAllBytes(Path.of(documentCustomization.getCustomHeaderFilePath()));
                return new String(bytes);
            } catch (IOException e) {
                throw new CustomContentParserException("Error during reading custom content", e);
            }
        }
        return null;
    }
}
