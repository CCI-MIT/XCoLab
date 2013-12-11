package org.xcolab.portlets.loginregister.singlesignon;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.message.ParameterList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageExtension;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.openid4java.message.sreg.SRegMessage;
import org.openid4java.message.sreg.SRegRequest;
import org.openid4java.message.sreg.SRegResponse;

@Controller
@RequestMapping(value = "view", params = "SSO=google")
public class OpenIdController {

    @RequestMapping(params = "action=initiateOpenIdLogin")
    public void initiateOpenIdLogin(ActionRequest actionRequest, Model model, ActionResponse actionResponse) throws Exception{

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpSession session = request.getSession();

        String openId = SSOKeys.GOOGLE_ENDPOINT;

        ConsumerManager manager = OpenIdUtil.getConsumerManager();
        List<DiscoveryInformation> discoveries = manager.discover(openId);
        DiscoveryInformation discovered = manager.associate(discoveries);
        session.setAttribute(SSOKeys.OPEN_ID_DISCO, discovered);
        AuthRequest authRequest = manager.authenticate(
                discovered, themeDisplay.getPortalURL() + SSOKeys.OPEN_ID_RESPONSE_URL);

        try {
            UserLocalServiceUtil.getUserByOpenId(
                    themeDisplay.getCompanyId(), openId);
        }
        catch (NoSuchUserException nsue) {
            String screenName = OpenIdUtil.getScreenName(openId);

            try {
                User user = UserLocalServiceUtil.getUserByScreenName(
                        themeDisplay.getCompanyId(), screenName);
                UserLocalServiceUtil.updateOpenId(user.getUserId(), openId);
            }
            catch (NoSuchUserException nsue2) {
                FetchRequest fetch = FetchRequest.createFetchRequest();

                fetch.addAttribute("email", "http://schema.openid.net/contact/email", true);
                fetch.addAttribute("firstName", "http://schema.openid.net/namePerson/first",true);
                fetch.addAttribute("lastName", "http://schema.openid.net/namePerson/last",true);

                authRequest.addExtension(fetch);
                SRegRequest sregRequest = SRegRequest.createFetchRequest();
                sregRequest.addAttribute("fullname", true);
                sregRequest.addAttribute("email", true);

                authRequest.addExtension(sregRequest);
            }
        }
        actionResponse.sendRedirect(authRequest.getDestinationUrl(true));
    }


    @RequestMapping(params = "action=readOpenIdResponse")
    public void readOpenIdResponse(ActionRequest actionRequest, Model model, ActionResponse actionResponse) throws Exception{

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpSession session = request.getSession();

        ConsumerManager manager = OpenIdUtil.getConsumerManager();
        ParameterList params = new ParameterList(actionRequest.getParameterMap());
        DiscoveryInformation discovered = (DiscoveryInformation)session.getAttribute("OPEN_ID_DISCO");
        if (discovered == null) return;

        String receivingUrl = ParamUtil.getString(actionRequest, "openid.return_to");
        VerificationResult verification = manager.verify(receivingUrl, params, discovered);
        Identifier verified = verification.getVerifiedId();
        if (verified == null) return;

        AuthSuccess authSuccess = (AuthSuccess)verification.getAuthResponse();

        String firstName = null;
        String lastName = null;
        String emailAddress = null;

        if (authSuccess.hasExtension(SRegMessage.OPENID_NS_SREG)) {
            MessageExtension ext = authSuccess.getExtension(SRegMessage.OPENID_NS_SREG);

            if (ext instanceof SRegResponse) {
                SRegResponse sregResp = (SRegResponse)ext;

                String fullName = GetterUtil.getString(sregResp.getAttributeValue("fullname"));

                int pos = fullName.indexOf(CharPool.SPACE);

                if ((pos != -1) && ((pos + 1) < fullName.length())) {
                    firstName = fullName.substring(0, pos);
                    lastName = fullName.substring(pos + 1);
                }

                emailAddress = sregResp.getAttributeValue("email");
            }
        }

        if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
            MessageExtension ext = authSuccess.getExtension(
                    AxMessage.OPENID_NS_AX);
            if (ext instanceof FetchResponse) {
                FetchResponse fetchResp = (FetchResponse)ext;
                if (Validator.isNull(firstName))
                    firstName = getFirstValue(fetchResp.getAttributeValues("firstName"));
                if (Validator.isNull(lastName))
                    lastName = getFirstValue(fetchResp.getAttributeValues("lastName"));
                if (Validator.isNull(emailAddress))
                    emailAddress = getFirstValue(fetchResp.getAttributeValues("email"));
            }
        }

        String openId = OpenIdUtil.normalize(authSuccess.getIdentity());


        User user = null;

        try {
            user = UserLocalServiceUtil.getUserByOpenId(
                    themeDisplay.getCompanyId(), openId);
            session.setAttribute(SSOKeys.OPEN_ID_LOGIN, new Long(user.getUserId()));
            actionResponse.sendRedirect(themeDisplay.getPortalURL());
            return;
        }
        catch (NoSuchUserException nsue) {
            // try to get user by email
                try {
                    user = UserLocalServiceUtil.getUserByEmailAddress(themeDisplay.getCompanyId(),emailAddress);
                    user.setOpenId(openId);
                    UserLocalServiceUtil.updateUser(user);
                    session.setAttribute(SSOKeys.OPEN_ID_LOGIN, new Long(user.getUserId()));
                    actionResponse.sendRedirect(themeDisplay.getPortalURL());
                    return;
                }catch (NoSuchUserException nsue2){
                    // forward to login or register
                    session.setAttribute(SSOKeys.SSO_OPENID_ID, openId);
                    if (Validator.isNotNull(emailAddress)) session.setAttribute(SSOKeys.SSO_EMAIL, emailAddress);
                    if (Validator.isNotNull(firstName)) session.setAttribute(SSOKeys.SSO_FIRST_NAME, firstName);
                    if (Validator.isNotNull(lastName)) session.setAttribute(SSOKeys.SSO_LAST_NAME, lastName);
                    actionResponse.setRenderParameter("SSO", "general");
                    actionResponse.setRenderParameter("status", "registerOrLogin");
                    actionRequest.setAttribute("credentialsError",false);
                }
        }
    }

    protected String getFirstValue(List<String> values) {
        if ((values == null) || (values.size() < 1)) {
            return null;
        }
        return values.get(0);
    }
}