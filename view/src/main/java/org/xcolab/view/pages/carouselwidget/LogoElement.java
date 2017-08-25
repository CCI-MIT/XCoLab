package org.xcolab.view.pages.carouselwidget;

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

    public LogoElement(String imageUrl, String linkUrl, String altText) {
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.altText = altText;
    }
}
