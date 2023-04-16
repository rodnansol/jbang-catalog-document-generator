package org.rodnansol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Map;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@RegisterForReflection
public class Template {

    @JsonProperty(value = "file-refs")
    private Map<String, String> fileRefs;
    private String description;
    private Map<String, TemplateProperty> properties;

    public Map<String, String> getFileRefs() {
        return fileRefs;
    }

    public void setFileRefs(Map<String, String> fileRefs) {
        this.fileRefs = fileRefs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, TemplateProperty> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, TemplateProperty> properties) {
        this.properties = properties;
    }
}
