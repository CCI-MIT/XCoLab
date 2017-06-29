package org.xcolab.util.attributes;

import java.io.Serializable;

/**
 * A convenience skeleton implementation of the {@link Attribute} interface.
 */
public abstract class AbstractAttribute implements Attribute, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Long additionalId;
    private Long numericValue;
    private String stringValue;
    private Double realValue;

    public AbstractAttribute() {
    }

    public AbstractAttribute(AbstractAttribute value) {
        this.name = value.name;
        this.additionalId = value.additionalId;
        this.numericValue = value.numericValue;
        this.stringValue = value.stringValue;
        this.realValue = value.realValue;
    }

    public AbstractAttribute(String name, long additionalId, Long numericValue,
            String stringValue, Double realValue) {
        this.name = name;
        this.additionalId = additionalId;
        this.numericValue = numericValue;
        this.stringValue = stringValue;
        this.realValue = realValue;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getAdditionalId() {
        return this.additionalId;
    }

    public void setAdditionalId(Long additionalId) {
        this.additionalId = additionalId;
    }

    @Override
    public Long getNumericValue() {
        return this.numericValue;
    }

    public void setNumericValue(Long numericValue) {
        this.numericValue = numericValue;
    }

    @Override
    public String getStringValue() {
        return this.stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public Double getRealValue() {
        return this.realValue;
    }

    public void setRealValue(Double realValue) {
        this.realValue = realValue;
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName()
                + " (" + name +
                ", " + additionalId +
                ", " + numericValue +
                ", " + stringValue +
                ", " + realValue +
                ")";
    }
}
