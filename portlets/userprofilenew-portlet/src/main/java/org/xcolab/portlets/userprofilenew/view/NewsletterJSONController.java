package org.xcolab.portlets.userprofilenew.view;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.userprofilenew.utils.ConnectorEmmaAPI;
import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * Created by Thomas on 1/12/2015.
 */
@Controller
@RequestMapping("view")
public class NewsletterJSONController {

    private ConnectorEmmaAPI connectorEmmaAPI;

    public NewsletterJSONController(){
        connectorEmmaAPI = new ConnectorEmmaAPI();
    }

    @ResourceMapping("newsletterSubscribe")
    public @ResponseBody
    void handleNewsletterSubscribeAJAXRequest(
            ResourceResponse response,
            @RequestParam("email") String email) throws IOException {

        JSONObject resultResponseJson = JSONFactoryUtil.createJSONObject();
        JSONObject memberDetails = connectorEmmaAPI.subscribeMemberWithEmail(email);

        if(memberDetails.has("status") && memberDetails.getString("status").equals("a")){
            resultResponseJson.put("success", true);
        } else{
            resultResponseJson.put("success", false);
        }

        response.getPortletOutputStream().write(resultResponseJson.toString().getBytes());

    }

    @ResourceMapping("newsletterUnSubscribe")
    public @ResponseBody
    void handleNewsletterUnSubscribeAJAXRequest(
            ResourceResponse response,
            @RequestParam("email") String email) throws IOException {

        JSONObject resultResponseJson = JSONFactoryUtil.createJSONObject();
        resultResponseJson.put("success",  connectorEmmaAPI.unSubscribeMemberWithEmail(email));
        response.getPortletOutputStream().write(resultResponseJson.toString().getBytes());
    }

    @ResourceMapping("newsletterSubscribtionStatus")
    public @ResponseBody
    void handleNewsletterSubscribtionStatusAJAXRequest(
            ResourceResponse response,
            @RequestParam("email") String email) throws IOException {

        JSONObject resultResponseJson = JSONFactoryUtil.createJSONObject();
        JSONObject memberDetails = connectorEmmaAPI.getMemberJSONfromEmail(email);

        if(memberDetails.has("member_status_id") && memberDetails.getString("member_status_id").equals("a")){
                resultResponseJson.put("subscribed",true);
        } else {
            resultResponseJson.put("subscribed",false);
        }

        response.getPortletOutputStream().write(resultResponseJson.toString().getBytes());
    }

}
