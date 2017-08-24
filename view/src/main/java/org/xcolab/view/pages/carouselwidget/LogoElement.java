package org.xcolab.view.pages.carouselwidget;

public class LogoElement {
    private String imageUrl;
    private String linkUrl;
    private String altText;

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
