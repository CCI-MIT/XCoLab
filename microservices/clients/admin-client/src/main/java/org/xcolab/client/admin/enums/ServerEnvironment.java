package org.xcolab.client.admin.enums;

public enum ServerEnvironment {
    PRODUCTION, STAGING, DEV, LOCAL, UNKNOWN;

    public boolean isProduction() {
        if (PRODUCTION == this)
            return true;
        else
            return false;
    }
}
