package org.rodnansol.document.template;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.rodnansol.document.DocumentData;

import java.io.IOException;

//@Dependent
public class HandlebarsTemplateCompiler implements TemplateCompiler {

    private final Handlebars handlebars;

    public HandlebarsTemplateCompiler(Handlebars handlebars) {
        this.handlebars = handlebars;
    }

    @Override
    public String generateDocument(String templatePath, DocumentData documentData) {
        try {
            Template template = handlebars.compile(templatePath);
            return template.apply(documentData);
        } catch (IOException e) {
            throw new TemplateCompilingException("Error during compiling template at:[" + templatePath + "]", e);
        }
    }
}
