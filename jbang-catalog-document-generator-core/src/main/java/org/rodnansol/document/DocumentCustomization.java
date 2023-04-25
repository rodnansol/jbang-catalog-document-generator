package org.rodnansol.document;

import java.util.StringJoiner;

/**
 * Class representing the customization options for the final document.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class DocumentCustomization {

    /**
     * Controls if the 'Table of contents' should be rendered into the document or not.
     *
     * @since 0.1.0
     */
    private boolean enableTableOfContents = false;
    /**
     * Path to file that contains the custom header.
     *
     * @since 0.1.0
     */
    private String customHeaderFilePath;
    /**
     * Path to file that contains the custom footer.
     *
     * @since 0.1.0
     */
    private String customFooterFilePath;
    /**
     * Placement of the 'Table of contents'.
     * <p>
     * Only for the AsciiDoc format.
     *
     * @since 0.1.0
     */
    private String tocPlacement = "auto";
    /**
     * Defines the title for the 'Table of contents' section.
     * <p>
     * Only for the AsciiDoc format.
     *
     * @since 0.1.0
     */
    private String tocTitle;
    /**
     * Defines the levels of the 'Table of contents'
     * <p>
     * Only for the AsciiDoc format.
     *
     * @since 0.1.0
     */
    private int tocLevels;
    /**
     * If the properties should be wrapped in a collapsible section or not.
     *
     * @since 0.1.0
     */
    private boolean collapsibleProperties = true;
    /**
     * Defines if the generation date should be rendered into the document or not.
     *
     * @since 0.1.0
     */
    private boolean includeGenerationDate = false;

    /**
     * Specifies if the header and footer should be rendered into the final document without using any markup language specific directive.
     * <p>
     * With this flag the custom header and footer will be rendered directly into the final document, and it will not be using any include directive like the `include::[]` in AsciiDoc.
     * <p>
     * It was created to overcome the limitation of the AsciiDoc for example on GitHub.
     *
     * @since 0.1.0
     */
    private boolean headerAndFooterDirectRender = false;

    /**
     * Name of the prefered catalog.
     * <p>
     * It is used to give context for the aliases and templates.
     *
     * @since 0.2.0
     */
    private String preferredCatalogName;

    /**
     * Defines if the usage section should be collapsible or not.
     *
     * @since 0.2.0
     */
    private boolean collapsibleUsageSection = false;

    public boolean isEnableTableOfContents() {
        return enableTableOfContents;
    }

    public void setEnableTableOfContents(boolean enableTableOfContents) {
        this.enableTableOfContents = enableTableOfContents;
    }

    public String getCustomHeaderFilePath() {
        return customHeaderFilePath;
    }

    public void setCustomHeaderFilePath(String customHeaderFilePath) {
        this.customHeaderFilePath = customHeaderFilePath;
    }

    public String getCustomFooterFilePath() {
        return customFooterFilePath;
    }

    public void setCustomFooterFilePath(String customFooterFilePath) {
        this.customFooterFilePath = customFooterFilePath;
    }

    public String getTocPlacement() {
        return tocPlacement;
    }

    public void setTocPlacement(String tocPlacement) {
        this.tocPlacement = tocPlacement;
    }

    public String getTocTitle() {
        return tocTitle;
    }

    public void setTocTitle(String tocTitle) {
        this.tocTitle = tocTitle;
    }

    public int getTocLevels() {
        return tocLevels;
    }

    public void setTocLevels(int tocLevels) {
        this.tocLevels = tocLevels;
    }

    public boolean isCollapsibleProperties() {
        return collapsibleProperties;
    }

    public void setCollapsibleProperties(boolean collapsibleProperties) {
        this.collapsibleProperties = collapsibleProperties;
    }

    public boolean isIncludeGenerationDate() {
        return includeGenerationDate;
    }

    public void setIncludeGenerationDate(boolean includeGenerationDate) {
        this.includeGenerationDate = includeGenerationDate;
    }

    public boolean isHeaderAndFooterDirectRender() {
        return headerAndFooterDirectRender;
    }

    public void setHeaderAndFooterDirectRender(boolean headerAndFooterDirectRender) {
        this.headerAndFooterDirectRender = headerAndFooterDirectRender;
    }

    public String getPreferredCatalogName() {
        return preferredCatalogName;
    }

    public void setPreferredCatalogName(String preferredCatalogName) {
        this.preferredCatalogName = preferredCatalogName;
    }

    public boolean isCollapsibleUsageSection() {
        return collapsibleUsageSection;
    }

    public void setCollapsibleUsageSection(boolean collapsibleUsageSection) {
        this.collapsibleUsageSection = collapsibleUsageSection;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DocumentCustomization.class.getSimpleName() + "[", "]")
            .add("enableTableOfContents=" + enableTableOfContents)
            .add("customHeaderFilePath='" + customHeaderFilePath + "'")
            .add("customFooterFilePath='" + customFooterFilePath + "'")
            .add("tocPlacement='" + tocPlacement + "'")
            .add("tocTitle='" + tocTitle + "'")
            .add("tocLevels=" + tocLevels)
            .add("collapsibleProperties=" + collapsibleProperties)
            .add("includeGenerationDate=" + includeGenerationDate)
            .add("headerAndFooterDirectRender=" + headerAndFooterDirectRender)
            .add("preferredCatalogName='" + preferredCatalogName + "'")
            .add("collapsibleUsageSection=" + collapsibleUsageSection)
            .toString();
    }
}
