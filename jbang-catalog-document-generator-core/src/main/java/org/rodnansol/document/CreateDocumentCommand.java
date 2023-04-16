package org.rodnansol.document;

import java.io.File;
import java.util.Objects;

/**
 * Command for document generation.
 *
 * @param inputFile          jbang-catalog.json file reference.
 * @param outputFile         output file where the documentation should be written.
 * @param templateType       type of the template.
 * @param customTemplatePath path to a custom template.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record CreateDocumentCommand(
    File inputFile,
    File outputFile,
    TemplateType templateType,
    String customTemplatePath,
    DocumentCustomization documentCustomization,
    boolean checkChecksum) {

    public CreateDocumentCommand(File inputFile,
                                 File outputFile,
                                 TemplateType templateType,
                                 String customTemplatePath,
                                 DocumentCustomization documentCustomization,
                                 boolean checkChecksum) {
        this.inputFile = Objects.requireNonNull(inputFile, "inputFile is NULL");
        this.outputFile = Objects.requireNonNull(outputFile, "outputFile is NULL");
        this.templateType = Objects.requireNonNull(templateType, "templateType is NULL");
        this.customTemplatePath = customTemplatePath;
        this.documentCustomization = Objects.requireNonNull(documentCustomization, "customization is NULL");
        this.checkChecksum = checkChecksum;
    }
}
