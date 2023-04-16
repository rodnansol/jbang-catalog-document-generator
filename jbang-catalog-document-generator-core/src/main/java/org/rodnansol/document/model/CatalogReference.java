package org.rodnansol.document.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@RegisterForReflection
public class CatalogReference {

    @JsonProperty(value = "catalog-ref")
    private String catalogRef;
    private String description;

    public String getCatalogRef() {
        return catalogRef;
    }

    public void setCatalogRef(String catalogRef) {
        this.catalogRef = catalogRef;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
