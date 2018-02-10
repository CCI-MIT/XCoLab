package org.xcolab.view.pages.contestmanagement.beans;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class BatchRegisterBean implements Serializable {

    @NotBlank
    private Boolean asGuests;

    @NotBlank
    private String batchText;

    public String getBatchText() {
        return batchText;
    }

    public void setBatchText(String batchText) {
        this.batchText = batchText;
    }

    public Boolean getAsGuests() {
        return asGuests;
    }

    public void setAsGuest(Boolean asGuests) {
        this.asGuests = asGuests;
    }

    @Override
    public String toString() {
        return "BatchRegisterBean [batchText='" + batchText + "', asGuests='" + asGuests.toString() + "']";
    }

}
