package org.rodnansol.document.template.qute;

import io.quarkus.qute.Engine;
import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.document.DocumentData;
import org.rodnansol.document.template.TemplateCompiler;

@ApplicationScoped
public class QuteTemplateCompiler implements TemplateCompiler {

    private final Engine engine;

    public QuteTemplateCompiler(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String generateDocument(String templatePath, DocumentData documentData) {
        return engine.getTemplate(templatePath)
            .render(documentData);
    }
}
