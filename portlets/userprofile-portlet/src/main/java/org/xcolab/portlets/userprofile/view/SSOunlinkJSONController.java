package org.xcolab.portlets.userprofile.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.userprofile.utils.JSONHelper;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

/**
 * Created by Thomas on 1/12/2015.
 */
@Controller
@RequestMapping("view")
public class SSOunlinkJSONController extends JSONHelper{


    public SSOunlinkJSONController(){
    }

    @ResourceMapping("unlinkFacebookSSO")
    public @ResponseBody
    void handleUnlinkFacebookSSOAJAXRequest(
            PortletRequest request,
            ResourceResponse response ){

        boolean successStatus = true;
        try {
            User user = PortalUtil.getUser(request);
            unlinkFacebookSSOuser(user);
        } catch (PortalException | SystemException e) {
            successStatus = false;
        }

        this.writeSuccessResultResponseJSON(successStatus, response);
    }

    @ResourceMapping("unlinkGoogleSSO")
    public @ResponseBody
    void handleUnlinkGoogleSSOAJAXRequest(
            PortletRequest request,
            ResourceResponse response){

        boolean successStatus = true;
        try {
            User user = PortalUtil.getUser(request);
            unlinkGoogleSSOuser(user);
        } catch (PortalException | SystemException e) {
            successStatus = false;
        }

        this.writeSuccessResultResponseJSON(successStatus, response);

    }

    private void unlinkFacebookSSOuser(User user) throws SystemException{
        user.setFacebookId(0);
        UserLocalServiceUtil.updateUser(user);
    }

    private void unlinkGoogleSSOuser(User user) throws SystemException{
        user.setOpenId("");
        UserLocalServiceUtil.updateUser(user);
    }

}
