package org.rodnansol.document;

import java.util.StringJoiner;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class DocumentCustomization {

    private boolean enableTableOfContents = false;
    private String customHeaderFilePath;
    private String customFooterFilePath;
    private String tocPlacement = "auto";
    private String tocTitle;
    private int tocLevels;
    private boolean collapsibleProperties = true;

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
            .toString();
    }
}
