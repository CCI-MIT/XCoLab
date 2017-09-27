package org.xcolab.view.files;

public enum ImageType {
    UNKNOWN(""),
    MEMBER("/images/user_default.png"),
    PROPOSAL("/images/proposal_default.png"),
    CONTEST("/images/proposal_default.png");

    private final String imagePath;

    ImageType(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
