package org.xcolab.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.client.members.MembersClient;

public final class UserCreationUtil {

    public static final String USERNAME_REGEX = "^[a-zA-Z0-9]+$";

    private UserCreationUtil() { }

    public static String generateUsername(String firstName, String lastName) throws SystemException, PortalException {
        return MembersClient.generateScreenName(lastName, firstName);
    }

    public static boolean isUsernameValid(String userName) {
        return userName.matches(USERNAME_REGEX);
    }
}
