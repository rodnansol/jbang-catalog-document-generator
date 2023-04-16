package org.rodnansol;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.model.Catalog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@ApplicationScoped
public class DocumentService {

    private final Handlebars handlebars;
    private final ObjectMapper objectMapper;

    public DocumentService(Handlebars handlebars, ObjectMapper objectMapper) {
        this.handlebars = handlebars;
        this.objectMapper = objectMapper;
    }

    public Catalog parseJsonFile(String fileName) throws IOException {
        return objectMapper.readValue(new File(fileName), Catalog.class);
    }

    public void generateAsciiDoc(String templatePath, Catalog catalog, File outputFile) throws IOException {
        Template template = handlebars.compile(templatePath);
        String result = template.apply(catalog);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(result);
        }
    }

}
