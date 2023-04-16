package org.rodnansol.document.customcontent;

/**
 * Exception to be thrown when the custom content parsing process encounters an error, like it misses a mandatory input file.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class CustomContentParserException extends RuntimeException {

    public CustomContentParserException(String message) {
        super(message);
    }

    public CustomContentParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
