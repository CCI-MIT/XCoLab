package org.xcolab.portlets.userprofile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.members.MembersClient;
import org.xcolab.portlets.userprofile.utils.JSONHelper;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class RolesJsonController extends JSONHelper {

    @ResourceMapping("addRole")
    public @ResponseBody void addRole(PortletRequest request, ResourceResponse response,
            @RequestParam long memberId, @RequestParam long roleId) {
        try {
            MembersClient.assignMemberRole(memberId, roleId);
            this.writeSuccessResultResponseJSON(true, response);
        } catch (HttpClientErrorException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }

    }

    @ResourceMapping("removeRole")
    public @ResponseBody void removeRole(PortletRequest request, ResourceResponse response,
            @RequestParam long memberId, @RequestParam long roleId) {
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
