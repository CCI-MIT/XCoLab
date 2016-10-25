package com.ext.portlet.models.ui;

public enum ErrorPolicy {

    DISPLAY_AVAILABLE_WITH_MSG(true, true),
    DISPLAY_Available_NO_MSG(true, false),
    NO_DISPLAY_WITH_MSG(false, true);

    private final boolean showData;
    private final boolean showMsg;

    ErrorPolicy(boolean show_data, boolean show_msg) {
        showData = show_data;
        showMsg = show_msg;
    }

    public boolean showData() {
        return showData;
    }

    public boolean showMsg() {
        return showMsg;
    }

}
