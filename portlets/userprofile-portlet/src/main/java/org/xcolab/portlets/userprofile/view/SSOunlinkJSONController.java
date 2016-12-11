package org.xcolab.portlets.userprofile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import org.xcolab.entity.utils.members.MemberAuthUtil;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.portlets.userprofile.utils.JSONHelper;
import org.xcolab.util.exceptions.DatabaseAccessException;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

/**
 * Created by Thomas on 1/12/2015.
 */
@Controller
@RequestMapping("view")
public class SSOunlinkJSONController extends JSONHelper {

    public SSOunlinkJSONController() { }

    @ResourceMapping("unlinkFacebookSSO")
    public
    @ResponseBody
    void handleUnlinkFacebookSSOAJAXRequest(
            PortletRequest request,
            ResourceResponse response) {

        boolean successStatus = true;
            Member member = MemberAuthUtil.getMemberOrThrow(request);
            //User user = PortalUtil.getUser(request);
            unlinkFacebookSSOuser(member);

        this.writeSuccessResultResponseJSON(successStatus, response);
    }

    private void unlinkFacebookSSOuser(Member member)  {
        member.setFacebookId(new Long(0));
        MembersClient.updateMember(member);
        //UserLocalServiceUtil.updateUser(user);
    }

    @ResourceMapping("unlinkGoogleSSO")
    public
    @ResponseBody
    void handleUnlinkGoogleSSOAJAXRequest(
            PortletRequest request,
            ResourceResponse response) {

        boolean successStatus = true;
        try {
            User user = PortalUtil.getUser(request);
            unlinkGoogleSSOuser(user);
        } catch (PortalException | SystemException e) {
            successStatus = false;
        }

        this.writeSuccessResultResponseJSON(successStatus, response);
    }

    private void unlinkGoogleSSOuser(User user) {
        user.setOpenId("");
        try {
            UserLocalServiceUtil.updateUser(user);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

}
