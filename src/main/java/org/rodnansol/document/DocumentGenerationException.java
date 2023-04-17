package org.rodnansol.document;

/**
 * Exception to be thrown when the document generation is not possible.
 *
 * <ul>
 *     <li>The input file is not available</li>
 *     <li>The output file is not available</li>
 *     <li>Error during compiling the final document</li>
 * </ul>
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class DocumentGenerationException extends RuntimeException{

    public DocumentGenerationException(String message) {
        super(message);
    }

    public DocumentGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
