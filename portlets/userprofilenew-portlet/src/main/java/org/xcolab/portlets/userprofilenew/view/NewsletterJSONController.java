package org.xcolab.portlets.userprofilenew.view;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.userprofilenew.utils.ConnectorEmmaAPI;
import org.xcolab.portlets.userprofilenew.utils.JSONHelper;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * Created by Thomas on 1/12/2015.
 */
@Controller
@RequestMapping("view")
public class NewsletterJSONController extends JSONHelper{

    private ConnectorEmmaAPI connectorEmmaAPI;

    public NewsletterJSONController(){
    }
        
    @ResourceMapping("newsletterSubscribe")
    public @ResponseBody
    void handleNewsletterSubscribeAJAXRequest(
            PortletRequest request,
            ResourceResponse response,
            @RequestParam("email") String email) throws IOException {

        initializeConnectorIfNull(request);
        JSONObject memberDetails = connectorEmmaAPI.subscribeMemberWithEmail(email);
        boolean memberHasActiveSubscription = hasNewMemberActiveSubscription(memberDetails);
        this.writeResultResponseJSON(memberHasActiveSubscription, response);
    }

    @ResourceMapping("newsletterUnSubscribe")
    public @ResponseBody
    void handleNewsletterUnSubscribeAJAXRequest(
            PortletRequest request,
            ResourceResponse response,
            @RequestParam("email") String email) throws IOException {

        initializeConnectorIfNull(request);
        boolean isMemberUnsubscribed = connectorEmmaAPI.unSubscribeMemberWithEmail(email);
        this.writeResultResponseJSON(isMemberUnsubscribed, response);
    }

    @ResourceMapping("newsletterSubscribtionStatus")
    public @ResponseBody
    void handleNewsletterSubscribtionStatusAJAXRequest(
            PortletRequest request,
            ResourceResponse response,
            @RequestParam("email") String email) throws IOException {

        initializeConnectorIfNull(request);
        JSONObject memberDetails = connectorEmmaAPI.getMemberJSONfromEmail(email);
        boolean memberHasActiveSubscription = hasMemberActiveSubscription(memberDetails);
        this.writeResultResponseJSON(memberHasActiveSubscription, response);
    }


    private void initializeConnectorIfNull(PortletRequest request){
        if(connectorEmmaAPI == null) {
            connectorEmmaAPI = new ConnectorEmmaAPI(request);
        }
    }

    private boolean hasNewMemberActiveSubscription(JSONObject memberDetails){

        if(memberDetails.has("status") && memberDetails.getString("status").equals("a")){
            return true;
        } else{
            return false;
        }
    }

    private boolean hasMemberActiveSubscription(JSONObject memberDetails){

        if(memberDetails.has("member_status_id") && memberDetails.getString("member_status_id").equals("a")){
            return true;
        } else{
            return false;
        }
    }

}
