package org.xcolab.util.attributes;

import java.io.Serializable;
import java.util.Objects;

/**
 * A convenience skeleton implementation of the {@link Attribute} interface.
 */
public abstract class AbstractAttribute implements Attribute, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Long additionalId;
    private String locale;
    private Long numericValue;
    private String stringValue;
    private Double realValue;

    public AbstractAttribute() {
    }

    public AbstractAttribute(AbstractAttribute value) {
        this.name = value.name;
        this.additionalId = value.additionalId;
        this.locale = value.locale;
        this.numericValue = value.numericValue;
        this.stringValue = value.stringValue;
        this.realValue = value.realValue;
    }

    public AbstractAttribute(String name, long additionalId, String locale, Long numericValue,
            String stringValue, Double realValue) {
        this.name = name;
        this.additionalId = additionalId;
        this.locale = locale;
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
    public Long getAdditionalId() {
        return this.additionalId;
    }

    public void setAdditionalId(Long additionalId) {
        this.additionalId = additionalId;
    }

    @Override
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractAttribute)) {
            return false;
        }
        AbstractAttribute that = (AbstractAttribute) o;
        return Objects.equals(getName(), that.getName())
                && Objects.equals(getAdditionalId(), that.getAdditionalId())
                && Objects.equals(getLocale(), that.getLocale())
                && Objects.equals(getNumericValue(), that.getNumericValue())
                && Objects.equals(getStringValue(), that.getStringValue())
                && Objects.equals(getRealValue(), that.getRealValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAdditionalId(), getLocale(),
                getNumericValue(), getStringValue(), getRealValue());
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName()
                + " (" + name +
                ", " + additionalId +
                ", " + locale +
                ", " + numericValue +
                ", " + stringValue +
                ", " + realValue +
                ")";
    }
}
