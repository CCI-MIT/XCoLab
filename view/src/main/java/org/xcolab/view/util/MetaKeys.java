package org.xcolab.view.util;

public enum MetaKeys {
    DESCRIPTION;

    public String getAttributeName() {
        return "XCOLAB_META_PAGE_" + name();
    }
}
