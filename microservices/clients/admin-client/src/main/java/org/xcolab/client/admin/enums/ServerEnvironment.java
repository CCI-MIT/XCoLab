package org.xcolab.client.admin.enums;

public enum ServerEnvironment {
    PRODUCTION, STAGING, DEV, LOCAL, UNKNOWN;

    public boolean getIsProduction() {
        return PRODUCTION == this;
    }
}
