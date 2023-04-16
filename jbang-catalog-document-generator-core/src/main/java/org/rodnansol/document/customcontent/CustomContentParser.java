package org.rodnansol.document.customcontent;

import org.rodnansol.document.CustomContent;
import org.rodnansol.document.DocumentCustomization;

/**
 * Interface declaring functions for custom contents.
 * <p>
 * Custom contents are being used in the templates and the different formats (AsciiDoc, Markdown) are having the ability to include/render extra header and footer information into the final document.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public interface CustomContentParser {


    /**
     * Creates the custom content based on the document customization.
     *
     * @throws CustomContentParserException when the content could not be setup, for example an input file is missing.
     */
    CustomContent createCustomContent(DocumentCustomization documentCustomization) throws CustomContentParserException;

}
