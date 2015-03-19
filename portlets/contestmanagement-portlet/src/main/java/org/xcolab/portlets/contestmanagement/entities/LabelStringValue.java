package org.xcolab.portlets.contestmanagement.entities;

/**
 * Created by Thomas on 2/9/2015.
 */
public class LabelStringValue {
    private String lable;
    private String value;

    public LabelStringValue(String value, String lable){
        this.value = value;
        this.lable = lable;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
