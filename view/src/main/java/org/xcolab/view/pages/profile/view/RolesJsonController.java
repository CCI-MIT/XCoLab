package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class RolesJsonController extends JSONHelper {

    @ResourceMapping("addRole")
    public @ResponseBody void addRole(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long memberId, @RequestParam long roleId) {
        long loggedInMemberId = MemberAuthUtil.getMemberId(request);
        if (!PermissionsClient.canAdminAll(loggedInMemberId)) {
            this.writeSuccessResultResponseJSON(false, response);
            return;
        }
        try {
            MembersClient.assignMemberRole(memberId, roleId);
            this.writeSuccessResultResponseJSON(true, response);
        } catch (HttpClientErrorException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }

    }

    @ResourceMapping("removeRole")
    public @ResponseBody void removeRole(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long memberId, @RequestParam long roleId) {
        long loggedInMemberId = MemberAuthUtil.getMemberId(request);
        if (!PermissionsClient.canAdminAll(loggedInMemberId)) {
            this.writeSuccessResultResponseJSON(false, response);
            return;
        }
        try {
            final boolean success;
            if (MembersClient.getMemberRoles(memberId).size() > 1) {
                MembersClient.removeMemberRole(memberId, roleId);
                success = true;
            } else {
                success = false;
            }
            this.writeSuccessResultResponseJSON(success, response);
        } catch (HttpClientErrorException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }

    }
}
