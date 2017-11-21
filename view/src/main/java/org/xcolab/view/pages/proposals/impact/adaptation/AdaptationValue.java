package org.xcolab.view.pages.proposals.impact.adaptation;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class AdaptationValue {

    private String region;
    private String subRegion;
    private float predictedDamages;
    private float minPercentReduction;
    private float maxPercentReduction;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public float getPredictedDamages() {
        return predictedDamages;
    }

    public void setPredictedDamages(float predictedDamages) {
        this.predictedDamages = predictedDamages;
    }

    public float getMinPercentReduction() {
        return minPercentReduction;
    }

    public void setMinPercentReduction(float minPercentReduction) {
        this.minPercentReduction = minPercentReduction;
    }

    public float getMaxPercentReduction() {
        return maxPercentReduction;
    }

    public void setMaxPercentReduction(float maxPercentReduction) {
        this.maxPercentReduction = maxPercentReduction;
    }

    public float getMinValue() {
        return predictedDamages * minPercentReduction / 100;
    }

    public float getMaxValue() {
        return predictedDamages * maxPercentReduction / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdaptationValue)) {
            return false;
        }
        AdaptationValue that = (AdaptationValue) o;
        return Objects.equals(getRegion(), that.getRegion())
                && Objects.equals(getSubRegion(), that.getSubRegion())
                && Float.compare(that.getPredictedDamages(), getPredictedDamages()) == 0
                && Float.compare(that.getMinPercentReduction(), getMinPercentReduction()) == 0
                && Float.compare(that.getMaxPercentReduction(), getMaxPercentReduction()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegion(), getSubRegion(), getPredictedDamages(),
                getMinPercentReduction(), getMaxPercentReduction());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("region", region)
                .append("subRegion", subRegion)
                .append("predictedDamages", predictedDamages)
                .append("minPercentReduction", minPercentReduction)
                .append("maxPercentReduction", maxPercentReduction)
                .toString();
    }
}
