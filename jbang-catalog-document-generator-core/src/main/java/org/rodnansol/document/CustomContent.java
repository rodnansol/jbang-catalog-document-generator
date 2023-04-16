package org.rodnansol.document;

/**
 * Record representing the result of the custom content parsing.
 * <p>
 * It can store little to big string that might just contain a line, or multiple lines read from an
 * input file to be rendered into the final document.
 *
 * @param customHeaderContent
 * @param customFooterContent
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record CustomContent(
    String customHeaderContent,
    String customFooterContent) {

}
