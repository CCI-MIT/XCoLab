package org.xcolab.client.user;

public class StaticUserContext {
    private static IUserClient userClient;

    public static void setUserClient(IUserClient userClient) {
        StaticUserContext.userClient = userClient;
    }

    public static IUserClient getUserClient() {
        return userClient;
    }
}
