package org.rodnansol.document;

import io.quarkus.runtime.annotations.RegisterForReflection;

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
@RegisterForReflection
public record CustomContent(
    String customHeaderContent,
    String customFooterContent) {

}
