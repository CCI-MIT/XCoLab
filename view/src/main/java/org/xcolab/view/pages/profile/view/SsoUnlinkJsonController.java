package org.xcolab.view.pages.profile.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile/{userId}/api/sso")
public class SsoUnlinkJsonController extends JSONHelper {

    private final IUserClient userClient;

    @Autowired
    public SsoUnlinkJsonController(IUserClient userClient) {
        this.userClient = userClient;
    }

    @PostMapping("facebook/unlink")
    public @ResponseBody void handleUnlinkFacebookSSOAJAXRequest(
            HttpServletRequest request, HttpServletResponse response) {

        boolean successStatus = false;
        UserWrapper member = MemberAuthUtil.getMemberOrNull();
        if (member != null) {
            unlinkFacebookSso(member);
            successStatus = true;
        }

        this.writeSuccessResultResponseJSON(successStatus, response);
    }

    private void unlinkFacebookSso(UserWrapper member)  {
        member.setFacebookId(0L);
        try {
            userClient.updateUser(member);
        }catch (MemberNotFoundException e){

        }
    }

    @PostMapping("google/unlink")
    public @ResponseBody void handleUnlinkGoogleSSOAJAXRequest(
            HttpServletRequest request, HttpServletResponse response) {

        boolean successStatus = false;
        UserWrapper member = MemberAuthUtil.getMemberOrNull();
        if (member != null) {
            unlinkGoogleSSO(member);
            successStatus = true;
        }

        this.writeSuccessResultResponseJSON(successStatus, response);
    }

    private void unlinkGoogleSSO(UserWrapper user) {
        user.setGoogleId("");
        user.setOpenId("");
        try{
            userClient.updateUser(user);
        }catch (MemberNotFoundException e){

        }
    }

}
