package org.xcolab.portlets.admintasks;

import com.ext.portlet.community.CommunityConstants;
import com.ext.utils.UserAccountGenerator;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class BatchRegisterUsers {
    private String csvInput;
    private String resultMessage;

    private static final long DEFAULT_COMPANY_ID = 10112L;

	public String registerUsers() throws Exception {
        List<String> csvLines = Arrays.asList(csvInput.split("\r?\n"));

        ServiceContext ctx = new ServiceContext();
        ctx.setAttribute("anonymousUser", true);
        UserAccountGenerator userAccountGenerator = new UserAccountGenerator();

        //do some permission stuff necessary for being able to run the addUserWithWorkflow action
        Role adminRole = RoleLocalServiceUtil.getRole(DEFAULT_COMPANY_ID, "Administrator");
        List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(adminRole.getRoleId());
        PrincipalThreadLocal.setName(adminUsers.get(0).getUserId());
        PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(adminUsers.get(0));
        PermissionThreadLocal.setPermissionChecker(permissionChecker);

        //register each line as a user
        int skippedUsers = 0;
        resultMessage = "";
        for (String csvLine : csvLines) {
            if (!csvLine.trim().isEmpty()) {
                //split once again into the specific fields
                StringTokenizer st = new StringTokenizer(csvLine.replaceAll(";;", "; ;") + " ", ";");
                List<String> fields = new ArrayList<String>();
                while (st.hasMoreTokens()) {
                    fields.add(st.nextToken().trim());
                }

                if (fields.size() != 4) {
                    skippedUsers++;
                    resultMessage += "Row found with more or less than 4 columns. Skipping this row<br/>\n";
                    continue;
                }

                //row format: john.doe@example.com;United States;John;Doe
                String email = fields.get(0);
                String country = fields.get(1);
                String firstName = fields.get(2);
                String lastName = fields.get(3);

                if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                    skippedUsers++;
                    resultMessage += "User with email " + email + " has a blank email, first or last name. Skipping this row<br/>\n";
                    continue;
                }

                try {
                    User existingUser = UserLocalServiceUtil.getUserByEmailAddress(DEFAULT_COMPANY_ID, email);
                    if (existingUser != null) {
                        skippedUsers++;
                        resultMessage += "User with email " + email + " was already registered. Skipping this row<br/>\n";
                        continue;
                    }
                } catch (NoSuchUserException ignored) { }

                String screenName = userAccountGenerator.generateUsername(firstName, lastName);

                User user = UserServiceUtil.addUserWithWorkflow(
                        DEFAULT_COMPANY_ID, true,
                        null,
                        null,
                        false,
                        screenName,
                        email, 0L, "",
                        Locale.ENGLISH,
                        firstName, "",
                        lastName, 0, 0, true, 1, 1,
                        1970, "", new long[]{}, new long[]{},
                        new long[]{}, new long[]{}, true, ctx);
                if (!country.isEmpty()) {
                    ExpandoValueLocalServiceUtil.addValue(
                            user.getCompanyId(),
                            User.class.getName(),
                            CommunityConstants.EXPANDO,
                            CommunityConstants.COUNTRY,
                            user.getUserId(),
                            country);
                }
            }
        }
        resultMessage += "<br/>Successfully created "+(csvLines.size()-skippedUsers)+" users.";

        return "";
	}

    public String getCsvInput() {
        return csvInput;
    }

    public void setCsvInput(String csvInput) {
        this.csvInput = csvInput;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
