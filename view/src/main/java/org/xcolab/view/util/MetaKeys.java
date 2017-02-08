package org.xcolab.view.util;

public enum MetaKeys {
    DESCRIPTION,
    KEYWORDS;

    public String getAttributeName() {
        return "XCOLAB_META_PAGE_" + name();
    }
}
