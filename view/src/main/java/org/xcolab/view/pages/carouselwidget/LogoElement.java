package org.xcolab.view.pages.carouselwidget;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LogoElement {

    private String imageUrl;
    private String linkUrl;
    private String altText;

    private Boolean remove;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Boolean getRemove() {
        return remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    @JsonCreator
    public LogoElement(@JsonProperty("imageUrl") String imageUrl,
            @JsonProperty("linkUrl") String linkUrl, @JsonProperty("altText") String altText) {
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.altText = altText;
        this.remove = false;
    }

    public LogoElement() {
        this.remove = false;
    }
}
