package org.xcolab.view.pages.contestmanagement.beans;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class BatchRegisterBean implements Serializable {

    @NotBlank
    private boolean asGuests;

    @NotBlank
    private String batchText;

    public String getBatchText() {
        return batchText;
    }

    public void setBatchText(String batchText) {
        this.batchText = batchText;
    }

    public boolean getAsGuests() {
        return asGuests;
    }

    public void setAsGuest(boolean asGuests) {
        this.asGuests = asGuests;
    }

    @Override
    public String toString() {
        return "BatchRegisterBean [batchText='" + batchText + "', asGuests='" + String.valueOf(asGuests) + "']";
    }

}
