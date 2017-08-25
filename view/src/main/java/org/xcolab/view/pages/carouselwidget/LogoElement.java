package org.xcolab.view.pages.carouselwidget;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LogoElement {

    private final String imageUrl;
    private final String linkUrl;
    private final String altText;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getAltText() {
        return altText;
    }

    @JsonCreator
    public LogoElement(@JsonProperty("imageUrl") String imageUrl,
            @JsonProperty("linkUrl") String linkUrl, @JsonProperty("altText") String altText) {
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.altText = altText;
    }
}
