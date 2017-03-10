package org.xcolab.view.util.entity.enums;

public enum ModelRegions {
    UNITED_STATES("US", "United States"),
    EUROPE("EU", "European Union"),
    CHINA("China", "China"),
    INDIA("India", "India"),
    OTHER_DEVELOPED("Other developed", "Other developed countries"),
    OTHER_DEVELOPING("Other developing", "Other developing countries");

    private String modelRegionName;
    private String modelRegionTitle;

    ModelRegions(String modelRegionName, String modelRegion) {
        this.modelRegionName = modelRegionName;
        this.modelRegionTitle = modelRegion;
    }

    public String getModelRegionName() {
        return modelRegionName;
    }

    public void setModelRegionName(String modelRegionName) {
        this.modelRegionName = modelRegionName;
    }

    public String getModelRegionTitle() {
        return modelRegionTitle;
    }

    public void setModelRegionTitle(String modelRegionTitle) {
        this.modelRegionTitle = modelRegionTitle;
    }
}
