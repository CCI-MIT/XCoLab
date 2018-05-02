package org.xcolab.commons.html;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class LabelValue implements Comparable<LabelValue> {

    private final long value;
    private final String lable;

    public LabelValue(long value, String lable) {
        this.value = value;
        this.lable = lable;
    }

    public long getValue() {
        return value;
    }

    public String getLable() {
        return lable;
    }

    @Override
    public int compareTo(LabelValue o) {
        return this.getLable().compareTo(o.getLable());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LabelValue)) {
            return false;
        }
        LabelValue that = (LabelValue) o;
        return value == that.value
                && Objects.equals(getLable(), that.getLable());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, getLable());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .append("lable", lable).toString();
    }
}
