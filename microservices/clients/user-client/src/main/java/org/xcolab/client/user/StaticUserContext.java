package org.xcolab.client.user;

public class StaticUserContext {

    private static IUserClient userClient;

    private static IAnalyticsClient analyticsClient;

    private static ILoginLogClient loginLogClient;

    private static IUserCategoryClient userCategoryClient;

    private static IUserLoginRegisterClient userLoginRegister;

    private static ILoginTokenClient loginTokenClient;

    private static IPermissionClient permissionClient;

    private static IMessagingClient messagingClient;

    private static IPlatformTeamClient platformTeamClient;

    private static ISsoClientDetailsClient ssoClientDetailsClient;

    public static void setUserClient(IUserClient userClient) {
        StaticUserContext.userClient = userClient;
    }

    public static IUserClient getUserClient() {
        return userClient;
    }

    public static IAnalyticsClient getAnalyticsClient() {
        return analyticsClient;
    }

    public static void setAnalyticsClient(IAnalyticsClient analyticsClient) {
        StaticUserContext.analyticsClient = analyticsClient;
    }

    public static ILoginLogClient getLoginLogClient() {
        return loginLogClient;
    }

    public static void setLoginLogClient(ILoginLogClient loginLogClient) {
        StaticUserContext.loginLogClient = loginLogClient;
    }

    public static IUserCategoryClient getUserCategoryClient() {
        return userCategoryClient;
    }

    public static void setUserCategoryClient(IUserCategoryClient userCategoryClient) {
        StaticUserContext.userCategoryClient = userCategoryClient;
    }

    public static IUserLoginRegisterClient getUserLoginRegister() {
        return userLoginRegister;
    }

    public static void setUserLoginRegister(IUserLoginRegisterClient userLoginRegister) {
        StaticUserContext.userLoginRegister = userLoginRegister;
    }

    public static ILoginTokenClient getLoginTokenClient() {
        return loginTokenClient;
    }

    public static void setLoginTokenClient(ILoginTokenClient loginTokenClient) {
        StaticUserContext.loginTokenClient = loginTokenClient;
    }

    public static IPermissionClient getPermissionClient() {
        return permissionClient;
    }

    public static void setPermissionClient(IPermissionClient permissionClient) {
        StaticUserContext.permissionClient = permissionClient;
    }

    public static IMessagingClient getMessagingClient() {
        return messagingClient;
    }

    public static void setMessagingClient(IMessagingClient messagingClient) {
        StaticUserContext.messagingClient = messagingClient;
    }

    public static IPlatformTeamClient getPlatformTeamClient() {
        return platformTeamClient;
    }

    public static void setPlatformTeamClient(IPlatformTeamClient platformTeamClient) {
        StaticUserContext.platformTeamClient = platformTeamClient;
    }

    public static ISsoClientDetailsClient getSsoClientDetailsClient() {
        return ssoClientDetailsClient;
    }

    public static void setSsoClientDetailsClient(
            ISsoClientDetailsClient ssoClientDetailsClient) {
        StaticUserContext.ssoClientDetailsClient = ssoClientDetailsClient;
    }
}
