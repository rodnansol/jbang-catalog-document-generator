package org.rodnansol.document.template;

public class TemplateCompilingException extends RuntimeException {

    public TemplateCompilingException(String message) {
        super(message);
    }

    public TemplateCompilingException(String message, Throwable cause) {
        super(message, cause);
    }
}
