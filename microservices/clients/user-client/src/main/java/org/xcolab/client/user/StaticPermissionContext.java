package org.xcolab.client.user;

public class StaticPermissionContext {

    private static IPermissionClient permissionClient;

    public static IPermissionClient getPermissionClient() {
        return permissionClient;
    }

    public static void setPermissionClient(IPermissionClient permissionClient) {
        StaticPermissionContext.permissionClient = permissionClient;
    }
}
