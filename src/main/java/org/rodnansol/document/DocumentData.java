package org.rodnansol.document;

import org.rodnansol.model.Catalog;

/**
 * @param catalog
 * @param customization
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record DocumentData(
    Catalog catalog,
    DocumentCustomization customization,
    CustomContent customContent) {
}
