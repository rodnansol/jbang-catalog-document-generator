package org.rodnansol.document.customcontent;

import org.rodnansol.document.CustomContent;
import org.rodnansol.document.DocumentCustomization;

import java.util.Objects;
import java.util.Optional;

/**
 * AsciiDoc related custom content parser.
 * <p>
 * It will use AsciiDoc `include` directives to include the custom header and footer into the final document.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
class AsciiDocCustomContentParser implements CustomContentParser {

    @Override
    public CustomContent createCustomContent(DocumentCustomization documentCustomization) {
        Objects.requireNonNull(documentCustomization, "documentCustomization is NULL");
        String customHeaderContent = getCustomContent(documentCustomization.getCustomHeaderFilePath());
        String customFooterContent = getCustomContent(documentCustomization.getCustomFooterFilePath());
        return new CustomContent(customHeaderContent, customFooterContent);
    }

    private String getCustomContent(String documentCustomization) {
        return Optional.ofNullable(documentCustomization)
            .map(path -> "include::" + path + "[]")
            .orElse(null);
    }
}
