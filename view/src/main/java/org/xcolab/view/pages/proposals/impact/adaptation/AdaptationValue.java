package org.xcolab.view.pages.proposals.impact.adaptation;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class AdaptationValue {

    private float predictedDamages;
    private float minPercentReduction;
    private float maxPercentReduction;

    public AdaptationValue() {
    }

    public AdaptationValue(float predictedDamages, float minPercentReduction,
            float maxPercentReduction) {
        this.predictedDamages = predictedDamages;
        this.minPercentReduction = minPercentReduction;
        this.maxPercentReduction = maxPercentReduction;
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
        return Float.compare(that.getPredictedDamages(), getPredictedDamages()) == 0
                && Float.compare(that.getMinPercentReduction(), getMinPercentReduction()) == 0
                && Float.compare(that.getMaxPercentReduction(), getMaxPercentReduction()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPredictedDamages(), getMinPercentReduction(),
                getMaxPercentReduction());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("predictedDamages", predictedDamages)
                .append("minPercentReduction", minPercentReduction)
                .append("maxPercentReduction", maxPercentReduction)
                .toString();
    }
}
