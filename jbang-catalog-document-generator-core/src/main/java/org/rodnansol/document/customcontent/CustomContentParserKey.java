package org.rodnansol.document.customcontent;

import org.rodnansol.document.TemplateType;

/**
 * Defines a key for the custom content parser which wraps a template type and additional flags for a key.
 *
 * @param templateType          type of the template.
 * @param headerAndFooterUnited if the header and footer should be rendered directly into the final document or not.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
record CustomContentParserKey(
    TemplateType templateType,
    boolean headerAndFooterUnited) {

}
