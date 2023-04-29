package org.rodnansol.document.template;

import org.rodnansol.document.DocumentData;

public interface TemplateCompiler {

    String generateDocument(String templatePath, DocumentData documentData);

}
