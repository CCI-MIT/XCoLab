package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class SSOunlinkJSONController extends JSONHelper {

    public SSOunlinkJSONController() { }

    @ResourceMapping("unlinkFacebookSSO")
    public @ResponseBody void handleUnlinkFacebookSSOAJAXRequest(
            HttpServletRequest request, HttpServletResponse response) {

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
            HttpServletRequest request, HttpServletResponse response) {

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
