package org.xcolab.view.pages.profile.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.config.spring.resolvers.RealMember;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile/{userId}/api/roles")
public class RolesJsonController extends JSONHelper {

    @Autowired
    private IUserClient userClient;

    @Autowired
    private IPermissionClient permissionClient;

    @PostMapping("add/{roleId}")
    public @ResponseBody void addRole(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long userId, @RealMember UserWrapper loggedInMember, @PathVariable long roleId) {
        if (!permissionClient.canAdminAll(loggedInMember)) {
            this.writeSuccessResultResponseJSON(false, response);
            return;
        }
        try {
            userClient.assignMemberRole(userId, roleId);
            this.writeSuccessResultResponseJSON(true, response);
        } catch (HttpClientErrorException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }

    }

    @PostMapping("remove/{roleId}")
    public @ResponseBody void removeRole(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long userId, @RealMember UserWrapper loggedInMember,
            @PathVariable long roleId) {
        if (!permissionClient.canAdminAll(loggedInMember)) {
            this.writeSuccessResultResponseJSON(false, response);
            return;
        }
        try {
            final boolean success;
            if (userClient.getUserRoles(userId,null).size() > 1) {
                userClient.removeMemberRole(userId, roleId);
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
