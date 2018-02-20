package org.xcolab.client.admin.enums;

public enum ServerEnvironment {
    PRODUCTION, STAGING, DEV, LOCAL, UNKNOWN;

    public boolean isProduction() {
        return PRODUCTION == this;
    }
}
