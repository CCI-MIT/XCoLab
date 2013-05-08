package org.climatecollaboratorium.facelets.discussions.permissions;

public interface DiscussionsPermissions {
    boolean getCanAddCategory();
    boolean getCanAddThread();
    boolean getCanAddMessage();
    boolean getCanAdminMessages();
    boolean getCanAdminCategories();
    boolean getCanAdmin();
    boolean getCanSubscribe();
    boolean getIsLoggedIn();
    boolean getCanAddComment();
    
    
}
