package org.rodnansol.document.customcontent;

import org.rodnansol.document.CustomContent;
import org.rodnansol.document.DocumentCustomization;

import java.util.Optional;

public class AsciiDocCustomContentParser implements CustomContentParser {

    @Override
    public CustomContent createCustomContent(DocumentCustomization documentCustomization) {
        String customHeaderContent = Optional.ofNullable(documentCustomization.getCustomHeaderFilePath())
            .map(path -> "include::" + path + "[]")
            .orElse(null);
        String customFooterContent = Optional.ofNullable(documentCustomization.getCustomFooterFilePath())
            .map(path -> "include::" + path + "[]")
            .orElse(null);
        return new CustomContent(customHeaderContent, customFooterContent);
    }
}
