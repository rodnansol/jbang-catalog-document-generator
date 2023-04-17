package org.rodnansol.document;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public enum TemplateType {

    ADOC(".adoc", "templates/adoc/main.adoc"),
    MD(".md","templates/md/main.md");

    private final String extension;
    private final String template;

    TemplateType(String extension, String template) {
        this.extension = extension;
        this.template = template;
    }

    public String getExtension() {
        return extension;
    }

    public String getTemplate() {
        return template;
    }
}
