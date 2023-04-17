package org.rodnansol.document.customcontent;

import org.rodnansol.document.CustomContent;
import org.rodnansol.document.DocumentCustomization;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public interface CustomContentParser {


    /**
     *
     * @param documentCustomization
     * @return
     * @throws CustomContentParserException
     */
    CustomContent createCustomContent(DocumentCustomization documentCustomization) throws CustomContentParserException;

}
