package org.xcolab.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.enums.ColabConstants;

public final class UserCreationUtil {

    public static final String USERNAME_REGEX = "^[a-zA-Z0-9]+$";

    private UserCreationUtil() { }

    public static String generateUsername(String firstName, String lastName) throws SystemException, PortalException {
        final UsernameGenerator[] usernameGenerators = {
                //TODO: add configuration attribute to active first initial method
               // new FirstLetterFullSecond(firstName, lastName),
                new BothFull(firstName, lastName)
        };

        for (int i = 0; i < 1000; i++) {
            for (UsernameGenerator generator : usernameGenerators) {
                final String potentialUsernameRaw = generator.getUsername() + ((i == 0) ? "" : i);
                final String potentialUsername = potentialUsernameRaw.toLowerCase().replaceAll("[^a-z0-9]", "");

                if (isUsernameAvailable(potentialUsername)) {
                    return potentialUsername;
                }
            }
        }
        throw new PortalException("Username generation exhausted all options for user " + firstName + " " + lastName);
    }

    public static boolean isUsernameAvailable(String username) throws SystemException {
        try {
            UserLocalServiceUtil.getUserByScreenName(ColabConstants.COLAB_COMPANY_ID, username);
            return false;
        } catch (PortalException e) {
            return true;
        }
    }

    public static boolean isUsernameValid(String userName) {
        return userName.matches(USERNAME_REGEX);
    }

    private interface UsernameGenerator {
        String getUsername();
    }

    private static class FirstLetterFullSecond implements UsernameGenerator {
        private final String firstName;
        private final String lastName;

        public FirstLetterFullSecond(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String getUsername() {
            return firstName.substring(0, 1) + lastName;
        }
    }

    private static class BothFull implements UsernameGenerator {
        private final String firstPart;
        private final String secondPart;

        public BothFull(String firstPart, String secondPart) {
            this.firstPart = firstPart;
            this.secondPart = secondPart;
        }

        @Override
        public String getUsername() {
            return firstPart + secondPart;
        }
    }
}
