package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.PermissionsClient;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.view.config.spring.resolvers.RealMember;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile/{userId}/api/roles")
public class RolesJsonController extends JSONHelper {

    @PostMapping("add/{roleId}")
    public @ResponseBody void addRole(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long userId, @RealMember Member loggedInMember, @PathVariable long roleId) {
        if (!PermissionsClient.canAdminAll(loggedInMember)) {
            this.writeSuccessResultResponseJSON(false, response);
            return;
        }
        try {
            MembersClient.assignMemberRole(userId, roleId);
            this.writeSuccessResultResponseJSON(true, response);
        } catch (HttpClientErrorException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }

    }

    @PostMapping("remove/{roleId}")
    public @ResponseBody void removeRole(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long userId, @RealMember Member loggedInMember,
            @PathVariable long roleId) {
        if (!PermissionsClient.canAdminAll(loggedInMember)) {
            this.writeSuccessResultResponseJSON(false, response);
            return;
        }
        try {
            final boolean success;
            if (MembersClient.getMemberRoles(userId).size() > 1) {
                MembersClient.removeMemberRole(userId, roleId);
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
