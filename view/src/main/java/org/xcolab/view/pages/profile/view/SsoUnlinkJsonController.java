package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile/{userId}/api/sso")
public class SsoUnlinkJsonController extends JSONHelper {

    public SsoUnlinkJsonController() { }

    @PostMapping("facebook/unlink")
    public @ResponseBody void handleUnlinkFacebookSSOAJAXRequest(
            HttpServletRequest request, HttpServletResponse response) {

        boolean successStatus = false;
        Member member = MemberAuthUtil.getMemberOrNull();
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

    @PostMapping("google/unlink")
    public @ResponseBody void handleUnlinkGoogleSSOAJAXRequest(
            HttpServletRequest request, HttpServletResponse response) {

        boolean successStatus = false;
        Member member = MemberAuthUtil.getMemberOrNull();
        if (member != null) {
            unlinkGoogleSSO(member);
            successStatus = true;
        }

        this.writeSuccessResultResponseJSON(successStatus, response);
    }

    private void unlinkGoogleSSO(Member user) {
        user.setGoogleId("");
        user.setOpenId("");
        MembersClient.updateMember(user);
    }

}
