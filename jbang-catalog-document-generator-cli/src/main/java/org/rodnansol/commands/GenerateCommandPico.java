package org.rodnansol.commands;

import jakarta.enterprise.context.Dependent;
import org.rodnansol.document.DocumentGenerationAction;
import org.rodnansol.document.TemplateType;
import picocli.CommandLine;

import java.io.File;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
@CommandLine.Command(versionProvider = VersionProvider.class, name = "generate", description = "Generates a documentation for a given jbang-catalog.json file")
public class GenerateCommandPico extends GenerateCommand {

    public GenerateCommandPico(DocumentGenerationAction documentGenerationAction) {
        super(documentGenerationAction);
    }

    @Override
    @CommandLine.Parameters(index = "0", description = "The input JSON file to convert.")
    public void setInputFile(File inputFile) {
        super.setInputFile(inputFile);
    }

    @Override
    @CommandLine.Option(names = {"-o", "--output"}, description = "The output AsciiDoc file name. Defaults to 'jbang-catalog.adoc'")
    public void setOutputFile(File outputFile) {
        super.setOutputFile(outputFile);
    }

    @Override
    @CommandLine.Option(names = {"-tt", "--template-type"}, description = "Template type.")
    public void setTemplateType(TemplateType templateType) {
        super.setTemplateType(templateType);
    }

    @Override
    @CommandLine.Option(names = {"-t", "--template"}, description = "Custom template path.")
    public void setCustomTemplatePath(String customTemplatePath) {
        super.setCustomTemplatePath(customTemplatePath);
    }

    @Override
    @CommandLine.Option(names = {"-ch", "--custom-header"}, description = "Path to a custom header.")
    public void setCustomHeaderFilePath(String customHeaderFilePath) {
        super.setCustomHeaderFilePath(customHeaderFilePath);
    }

    @Override
    @CommandLine.Option(names = {"-cf", "--custom-footer"}, description = "Path to a custom footer.")
    public void setCustomFooterFilePath(String customFooterFilePath) {
        super.setCustomFooterFilePath(customFooterFilePath);
    }

    @Override
    @CommandLine.Option(names = {"-toc", "--table-of-contents"}, description = "Enable the table of contents or not")
    public void setEnableTableOfContents(boolean enableTableOfContents) {
        super.setEnableTableOfContents(enableTableOfContents);
    }

    @Override
    @CommandLine.Option(names = {"-toc-p", "--table-of-contents-placement"}, description = "Placement of the table of contents (In case of AsciiDoc)")
    public void setTocPlacement(String tocPlacement) {
        super.setTocPlacement(tocPlacement);
    }

    @Override
    @CommandLine.Option(names = {"-toc-t", "--table-of-contents-title"}, description = "Title of the table of contents")
    public void setTocTitle(String tocTitle) {
        super.setTocTitle(tocTitle);
    }

    @Override
    @CommandLine.Option(names = {"-toc-l", "--table-of-contents-levels"}, description = "Levels of the table of contents (In case of AsciiDoc)")
    public void setTocLevels(int tocLevels) {
        super.setTocLevels(tocLevels);
    }

    @Override
    @CommandLine.Option(names = {"-cc", "--check-checksum"}, description = "Defines if the checksum check should be running or not. Checksum check is enabled by default.")
    public void setCheckChecksum(boolean checkChecksum) {
        super.setCheckChecksum(checkChecksum);
    }

    @Override
    @CommandLine.Option(names = {"-g", "--include-generation-date"}, description = "Defines if the generation date should be put into the document or not.")
    public void setIncludeGenerationDate(boolean includeGenerationDate) {
        super.setIncludeGenerationDate(includeGenerationDate);
    }

    @Override
    @CommandLine.Option(names = {"-hfdr", "--header-footer-direct-render"}, description = "Defines if the header and footer should be directly rendered into the document or not. In case of AsciiDoc it will not use the `include` directive.")
    public void setHeaderAndFooterDirectRender(boolean headerAndFooterDirectRender) {
        super.setHeaderAndFooterDirectRender(headerAndFooterDirectRender);
    }
}
