package org.xcolab.portlets.userprofile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.portlets.userprofile.utils.JSONHelper;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class SSOunlinkJSONController extends JSONHelper {

    public SSOunlinkJSONController() { }

    @ResourceMapping("unlinkFacebookSSO")
    public @ResponseBody void handleUnlinkFacebookSSOAJAXRequest(
            PortletRequest request, ResourceResponse response) {

        boolean successStatus = false;
        Member member = MemberAuthUtil.getMemberOrNull(request);
        if (member != null) {
            unlinkFacebookSso(member);
            successStatus = true;
        }

        this.writeSuccessResultResponseJSON(successStatus, response);
    }

    private void unlinkFacebookSso(Member member)  {
        member.setFacebookId(0L);
        MembersClient.updateMember(member);
    }

    @ResourceMapping("unlinkGoogleSSO")
    public @ResponseBody void handleUnlinkGoogleSSOAJAXRequest(
            PortletRequest request, ResourceResponse response) {

        boolean successStatus = false;
        Member member = MemberAuthUtil.getMemberOrNull(request);
        if (member != null) {
            unlinkGoogleSSO(member);
            successStatus = true;
        }

        this.writeSuccessResultResponseJSON(successStatus, response);
    }

    private void unlinkGoogleSSO(Member user) {
        user.setOpenId("");
        MembersClient.updateMember(user);
    }

}
