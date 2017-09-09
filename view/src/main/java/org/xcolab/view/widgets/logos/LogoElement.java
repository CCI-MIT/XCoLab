package org.xcolab.view.widgets.logos;

import org.json.JSONObject;

public class LogoElement {

    private String imageUrl;
    private String linkUrl;
    private String altText;

    private Boolean remove;

    public LogoElement() {
        this.remove = false;
    }

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

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imageUrl", imageUrl);
        jsonObject.put("linkUrl", linkUrl);
        jsonObject.put("altText", altText);
        return jsonObject;
    }
}
