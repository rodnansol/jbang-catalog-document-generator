package org.rodnansol.document.customcontent;

public class CustomContentParserException extends RuntimeException  {

    public CustomContentParserException(String message) {
        super(message);
    }

    public CustomContentParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
