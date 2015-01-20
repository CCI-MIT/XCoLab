package org.xcolab.portlets.userprofile.beans;

import com.liferay.portal.kernel.json.JSONObject;
import org.xcolab.portlets.userprofile.utils.ConnectorEmmaAPI;

import javax.portlet.PortletRequest;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Thomas on 1/12/2015.
 */
public class NewsletterBean implements Serializable{


    private ConnectorEmmaAPI connectorEmmaAPI;
    private String email;

    public NewsletterBean(String email, PortletRequest request){
        this.email = email;
        this.connectorEmmaAPI = new ConnectorEmmaAPI(request);
    }

    public boolean isEmailSubscribedToNewsletter(){

        try {
            JSONObject memberDetails = connectorEmmaAPI.getMemberJSONfromEmail(email);

            if (memberDetails.has("member_status_id") && memberDetails.getString("member_status_id").equals("a")) {
                return true;
            }
        } catch (IOException e){

        }

        return false;

    }

}
