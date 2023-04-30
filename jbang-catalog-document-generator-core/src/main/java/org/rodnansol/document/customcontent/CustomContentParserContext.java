package org.rodnansol.document.customcontent;

import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.document.CustomContent;
import org.rodnansol.document.DocumentCustomization;
import org.rodnansol.document.TemplateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

/**
 * Class to handle the selection of the {@link CustomContentParser}.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ApplicationScoped
public class CustomContentParserContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomContentParserContext.class);
    private final FileContentBasedCustomContentParser fileContentBasedCustomContentParser;
    private final Map<CustomContentParserKey, CustomContentParser> parserMap;

    public CustomContentParserContext(FileContentBasedCustomContentParser fileContentBasedCustomContentParser,
                                      Map<CustomContentParserKey, CustomContentParser> parserMap) {
        this.fileContentBasedCustomContentParser = fileContentBasedCustomContentParser;
        this.parserMap = parserMap;
    }

    /**
     * Creates the {@link CustomContent} instance by selecting the proper implemention and executing its parsing method.
     * <p>
     * If the custom content parser can not be found based on the incoming objects a default one will be used: {@link FileContentBasedCustomContentParser}.
     *
     * @param templateType          type of the template.
     * @param documentCustomization document customization object.
     * @return create custom content instance.
     */
    public CustomContent createCustomContentBy(TemplateType templateType, DocumentCustomization documentCustomization) {
        Objects.requireNonNull(templateType, "templateType is NULL");
        Objects.requireNonNull(documentCustomization, "documentCustomization is NULL");
        CustomContentParser contentParser = parserMap.getOrDefault(new CustomContentParserKey(templateType, documentCustomization.isHeaderAndFooterDirectRender()), fileContentBasedCustomContentParser);
        LOGGER.debug("Content parser being used:[{}]", contentParser);
        return contentParser
            .createCustomContent(documentCustomization);
    }
}
