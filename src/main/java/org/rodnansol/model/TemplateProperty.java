package org.rodnansol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@RegisterForReflection
public class TemplateProperty {

    private String description;
    @JsonProperty(value = "default")
    private String defaultValue;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
