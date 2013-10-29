package org.xcolab.portlets.loginregister.conference;

import com.ext.portlet.community.CommunityConstants;
import com.ext.utils.UserAccountGenerator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.DefaultScreenNameGenerator;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.auth.ScreenNameGenerator;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.*;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.xcolab.portlets.loginregister.LoginController;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * @author pdeboer
 *         First created on 8/28/13 at 12:01 PM
 */
public class AutoregisterConferencePeople implements MessageListener {
    @Override
    public void receive(Message message) throws MessageListenerException {
        //use separate thread in order to limit the effect of assigning Admin rights to ThreadLocal PermissionChecker
        new Thread() {
            @Override
            public void run() {
                try {
                    List<ConferenceUser> users = getConferenceUsers();
                    for (ConferenceUser u : users) {
                        if (!u.getMember() && u.getJoinColab()) {
                            registerUser(u);
                        } else if (u.getColabEmail() != null && !u.getColabEmail().equals("")) {
                            setConferenceAttendingExpando(getUserByEmail(u.getColabEmail()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void registerUser(ConferenceUser u) {
        try {
            String email = u.geteMail();
            if (!emailExists(email)) {
                UserAccountGenerator userAccountGenerator = new UserAccountGenerator();
                String screenName = userAccountGenerator.generateUsername(u.getFirstName(), u.getLastName());

                Role adminRole = RoleLocalServiceUtil.getRole(LoginController.companyId, "Administrator");
                List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(adminRole.getRoleId());

                PrincipalThreadLocal.setName(adminUsers.get(0).getUserId());
                PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(adminUsers.get(0), true);
                PermissionThreadLocal.setPermissionChecker(permissionChecker);

                // ServiceContext ctx = ServiceContextFactory.getInstance(User.class + "", null);
                ServiceContext ctx = new ServiceContext();
                ctx.setAttribute("anonymousUser", true);

                User user = UserServiceUtil.addUserWithWorkflow(LoginController.companyId, true, null, null, false, screenName, email, 0, "", Locale.ENGLISH, u.getFirstName(), "", u.getLastName(), 0, 0, true, 1, 1, 1970, "", new long[]{}, new long[]{}, new long[]{}, new long[]{}, true, ctx);

                setConferenceAttendingExpando(user);

                System.out.println("automatically registered user " + screenName + ": " + user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setConferenceAttendingExpando(User u) throws SystemException, PortalException {
        if (u == null) return;
        ExpandoTable table = null;
        try {
            table = ExpandoTableLocalServiceUtil.getTable(LoginController.companyId, User.class.getName(),
                    CommunityConstants.EXPANDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //create table
        if (table == null) {
            System.out.println("creating expando table");
            table = ExpandoTableLocalServiceUtil.addTable(LoginController.companyId, User.class.getName(), CommunityConstants.EXPANDO);
        }

        ExpandoColumn conferenceExpando = null;
        try {
            conferenceExpando = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), CommunityConstants.CONFERENCE2013);
        } catch (Exception e) {

        }
        // create column
        if (conferenceExpando == null) {
            System.out.println("creating expando column");
            conferenceExpando = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(),
                    CommunityConstants.CONFERENCE2013, ExpandoColumnConstants.STRING);
        }

        ExpandoValueLocalServiceUtil.addValue(LoginController.companyId, User.class.getName(), CommunityConstants.EXPANDO,
                CommunityConstants.CONFERENCE2013, u.getUserId(), "1");
    }

    private boolean emailExists(String email) {
        return getUserByEmail(email) != null;
    }

    private User getUserByEmail(String email) {
        try {
            return UserLocalServiceUtil.getUserByEmailAddress(LoginController.companyId, email);
        } catch (Exception e) {
            return null;
        }
    }

    private List<ConferenceUser> getConferenceUsers() throws IOException {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod("https://classic.regonline.com/activereports/smartLink.aspx?eventid=WThQT9uce5k=&crid=1056641");

        client.executeMethod(method);

        Workbook wb = new HSSFWorkbook(method.getResponseBodyAsStream());
        Sheet sheet = wb.getSheetAt(0);

        List<ConferenceUser> users = new LinkedList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; //skip header
            try {
                ConferenceUser u = new ConferenceUser(row);
                users.add(u);
            } catch (Exception e) {
            }
        }

        method.releaseConnection();
        return users;
    }

}
