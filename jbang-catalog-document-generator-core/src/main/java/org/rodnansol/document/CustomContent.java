package org.rodnansol.document;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Record representing the result of the custom content parsing.
 * <p>
 * It can store little to big string that might just contain a line, or multiple lines read from an
 * input file to be rendered into the final document.
 *
 * @param customHeaderContent resolved content of the custom header, or null if the file can not be resolved/read.
 * @param customFooterContent resolved content of the custom footer, or null if the file can not be resolved/read.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@RegisterForReflection
public record CustomContent(
    String customHeaderContent,
    String customFooterContent) {

}
