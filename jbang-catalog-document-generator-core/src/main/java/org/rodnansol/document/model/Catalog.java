package org.rodnansol.document.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Map;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@RegisterForReflection
public class Catalog {

    @JsonProperty(value = "base-ref")
    private String baseRef;
    private String description;
    private Map<String, CatalogReference> catalogs;
    private Map<String, Alias> aliases;
    private Map<String, Template> templates;

    public String getBaseRef() {
        return baseRef;
    }

    public void setBaseRef(String baseRef) {
        this.baseRef = baseRef;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, CatalogReference> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(Map<String, CatalogReference> catalogs) {
        this.catalogs = catalogs;
    }

    public Map<String, Alias> getAliases() {
        return aliases;
    }

    public void setAliases(Map<String, Alias> aliases) {
        this.aliases = aliases;
    }

    public Map<String, Template> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, Template> templates) {
        this.templates = templates;
    }
}
