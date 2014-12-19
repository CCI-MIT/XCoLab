package com.ext.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author pdeboer
 *         First created on 8/26/13 at 4:05 PM
 */
public class UserAccountGenerator {
    private static final long companyId = 10112L;

    public String generateUsername(String firstname, String lastname) throws SystemException {
        UserNameGenerator[] userNameGenerators = {
                new FirstLetterFullSecond(firstname, lastname),
                new BothFull(firstname, lastname),
                new BothFull(lastname, firstname)};

        for (int i = 1; i < 1000; i++) {
            for (UserNameGenerator ug : userNameGenerators) {
                String cur = ug.get() + ((i < 2) ? "" : i);
                cur = cur.toLowerCase().replaceAll("[^a-z0-9.-_]",""); //remove disallowed chars
                if (checkUsernameAvailable(cur)) {
                    return cur;
                }
            }
        }
        return null;
    }

    private boolean checkUsernameAvailable(String username) throws SystemException {
        try {
            User u = UserLocalServiceUtil.getUserByScreenName(companyId, username);
            return false;
        } catch (PortalException e) {
            return true;
        }
    }

    private interface UserNameGenerator {
        public String get();
    }

    private static class FirstLetterFullSecond implements UserNameGenerator {
        String p1, p2;

        private FirstLetterFullSecond(String p1, String p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public String get() {
            return p1.substring(0, 1) + p2;
        }
    }

    private static class BothFull implements UserNameGenerator {
        String p1, p2;

        private BothFull(String p1, String p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public String get() {
            return p1 + p2;
        }
    }
}
