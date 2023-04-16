package org.rodnansol.document;

import org.rodnansol.document.model.Catalog;

import java.time.LocalDateTime;

/**
 * Record contains all the neccessary attributes that is used during the document generation.
 *
 * @param catalog        catalog that stores all the JBang catalog objects.
 * @param customization  customization stores all the document customization options.
 * @param customContent  contains the custom header and footer content.
 * @param generationDate generation date.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
record DocumentData(
    Catalog catalog,
    DocumentCustomization customization,
    CustomContent customContent,
    LocalDateTime generationDate) {

}
