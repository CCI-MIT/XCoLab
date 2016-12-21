package org.xcolab.portlets.loginregister;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.ModelAttributeUtil;
import org.xcolab.entity.utils.ReCaptchaUtils;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.entity.utils.portlet.PortletUtil;
import org.xcolab.entity.utils.portlet.RequestParamUtil;
import org.xcolab.entity.utils.portlet.session.SessionErrors;
import org.xcolab.entity.utils.portlet.session.SessionMessages;
import org.xcolab.liferay.LoginRegisterUtil;
import org.xcolab.portlets.loginregister.exception.UserLocationNotResolvableException;
import org.xcolab.portlets.loginregister.singlesignon.SSOKeys;
import org.xcolab.util.html.HtmlUtil;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("view")
public class MainViewController {

    private static final Logger _log = LoggerFactory.getLogger(MainViewController.class);

    public static final String SSO_TARGET_KEY = "SSO_TARGET_KEY";
    public static final String SSO_TARGET_REGISTRATION = "SSO_TARGET_REGISTRATION";
    public static final String SSO_TARGET_LOGIN = "SSO_TARGET_LOGIN";
    public static final String PRE_LOGIN_REFERRER_KEY = "PRE_LOGIN_REFERRER_KEY";

    private static final String USER_NAME_REGEX = "^[a-zA-Z0-9]+$";

    @Autowired
    private Validator validator;

    @InitBinder("createUserBean")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    /**
     * Main view displayed for contact form
     */
    @RequestMapping
    public String register(PortletRequest request, PortletResponse response, Model model) {



        HttpServletRequest httpRequest = PortletUtil
                .getHttpServletRequest(request);

        while (httpRequest instanceof HttpServletRequestWrapper) {
            httpRequest = (HttpServletRequest) ((HttpServletRequestWrapper) httpRequest)
                    .getRequest();
        }

        String redirect = RequestParamUtil.getString(request, "redirect");

        if (redirect == null || redirect.trim().isEmpty()) {
            redirect = httpRequest.getParameter("redirect");
            if (redirect == null) {
                HttpSession session = httpRequest.getSession();
                redirect = (String) session.getAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);
            }
            if (redirect == null) {
                redirect = PortletUtil.getHttpServletRequest(request).getHeader("referer");
            }
        }
        if (MemberAuthUtil.getMemberId(request)>0) {
            HttpServletResponse httpServletResponse = PortletUtils.getHttpServletResponse(response);
            try {
                httpServletResponse.sendRedirect("/");
                return "";
            } catch (IOException ignored) {
            }
        }

        if (com.liferay.portal.kernel.util.Validator.isNotNull(redirect)) {
            model.addAttribute("redirect", com.liferay.portal.kernel.util.HtmlUtil.escape(redirect));
        }

        // append SSO attributes
        CreateUserBean userBean = new CreateUserBean();
        getSSOUserInfo(request.getPortletSession(), userBean);
        model.addAttribute("createUserBean", userBean);

        // Get country location
        if (com.liferay.portal.kernel.util.Validator.isNull(userBean.getCountry())) {
            try {
                userBean.setCountry(
                        getCountryCodeFromRemoteAddress(PortletUtil.getHttpServletRequest(request).getRemoteAddr()));
            } catch (UserLocationNotResolvableException ignored) {
            }
        }
        ModelAttributeUtil.populateModelWithPlatformConstants(model);
        model.addAttribute("generateScreenName", ConfigurationAttributeKey.GENERATE_SCREEN_NAME.get());
        boolean isSharedColab = ConfigurationAttributeKey.IS_SHARED_COLAB.get();
        model.addAttribute("isSharedCollab", isSharedColab);
        if (isSharedColab) {
            final String partnerColabName = ConfigurationAttributeKey.PARTNER_COLAB_NAME.get();
            final String partnerColabImgsAndClasses = partnerColabName.replace(" ","");
            model.addAttribute("partnerColabClassName",partnerColabImgsAndClasses+ "-sketchy");
            model.addAttribute("partnerColabName", partnerColabName);
        }
        final String loginInfoText = ConfigurationAttributeKey.LOGIN_INFO_MESSAGE.get();
        model.addAttribute("hasLoginInfoText", StringUtils.isNotBlank(loginInfoText));
        model.addAttribute("loginInfoText", loginInfoText);
        return "view";
    }

    public static void getSSOUserInfo(PortletSession portletSession, CreateUserBean createUserBean) {
        // append SSO attributes from session
        String fbIdString =
                (String) portletSession.getAttribute(SSOKeys.FACEBOOK_USER_ID, PortletSession.APPLICATION_SCOPE);
        String openId = (String) portletSession.getAttribute(SSOKeys.SSO_OPENID_ID, PortletSession.APPLICATION_SCOPE);
        String firstName =
                (String) portletSession.getAttribute(SSOKeys.SSO_FIRST_NAME, PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_FIRST_NAME, PortletSession.APPLICATION_SCOPE);
        String lastName = (String) portletSession.getAttribute(SSOKeys.SSO_LAST_NAME, PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_LAST_NAME, PortletSession.APPLICATION_SCOPE);
        String eMail = (String) portletSession.getAttribute(SSOKeys.SSO_EMAIL, PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_EMAIL, PortletSession.APPLICATION_SCOPE);
        String screenName =
                (String) portletSession.getAttribute(SSOKeys.SSO_SCREEN_NAME, PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_SCREEN_NAME, PortletSession.APPLICATION_SCOPE);
        String imageId =
                (String) portletSession.getAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, PortletSession.APPLICATION_SCOPE);
        String country = (String) portletSession.getAttribute(SSOKeys.SSO_COUNTRY, PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_COUNTRY, PortletSession.APPLICATION_SCOPE);

        if ((StringUtils.isNotBlank(fbIdString) || StringUtils.isNotBlank(openId))) {
            createUserBean.setFirstName(firstName);
            createUserBean.setLastName(lastName);
            createUserBean.setEmail(eMail);
            createUserBean.setScreenName(screenName);
            createUserBean.setImageId(imageId);
            createUserBean.setCaptchaNeeded(false);
        }

        if (com.liferay.portal.kernel.util.Validator.isNotNull(country)) {
            createUserBean.setCountry(country);
        }
    }

    private String getCountryCodeFromRemoteAddress(String ipAddr) throws UserLocationNotResolvableException {
        try {
            Location location = TrackingClient.getLocationForIp(ipAddr);
            if (com.liferay.portal.kernel.util.Validator.isNotNull(location)) {
                return location.getCountry();
            }
        } catch (Exception e) {
            throw new UserLocationNotResolvableException(
                    String.format("Could not retrieve country from IP address %s", ipAddr), e);
        }
        throw new UserLocationNotResolvableException(
                String.format("Could not retrieve country from IP address %s", ipAddr));
    }



    @RequestMapping(params = "error=true")
    public String registerError(PortletRequest request, Model model,
            @Valid CreateUserBean newAccountBean, BindingResult result,
            @RequestParam(required = false) String redirect) {
        if (request.getParameter("recaptchaError") != null) {
            result.addError(new ObjectError("createUserBean",
                    "Please click the box"));
        }

        if (com.liferay.portal.kernel.util.Validator.isNotNull(redirect)) {
            model.addAttribute("redirect", com.liferay.portal.kernel.util.HtmlUtil.escape(redirect));
        }
        ModelAttributeUtil.populateModelWithPlatformConstants(model);
        newAccountBean.setCaptchaText("");
        boolean isSharedColab = ConfigurationAttributeKey.IS_SHARED_COLAB.get();
        model.addAttribute("isSharedCollab", isSharedColab);
        if(isSharedColab) {
            final String partnerColabName = ConfigurationAttributeKey.PARTNER_COLAB_NAME.get();
            final String partnerColabImgsAndClasses = partnerColabName.replace(" ","");
            model.addAttribute("partnerColabClassName",partnerColabImgsAndClasses+ "-sketchy");
            model.addAttribute("partnerColabName", partnerColabName);
        }
        return "view";
    }

    @RequestMapping(params = "action=add")
    public void registerUser(ActionRequest request, Model model,
            ActionResponse response, @Valid CreateUserBean newAccountBean,
            BindingResult result,
            @RequestParam(required = false) String redirect) {

        PortletSession portletSession = request.getPortletSession();
        String fbIdString =
                (String) portletSession.getAttribute(SSOKeys.FACEBOOK_USER_ID, PortletSession.APPLICATION_SCOPE);
        String openId = (String) portletSession.getAttribute(SSOKeys.SSO_OPENID_ID, PortletSession.APPLICATION_SCOPE);

        if (!result.hasErrors()) {
            boolean captchaValid = true;
            // require captcha if user is not logged in via SSO
            if (fbIdString == null && openId == null) {
                String gRecaptchaResponse = request
                        .getParameter("g-recaptcha-response");
                captchaValid = ReCaptchaUtils.verify(gRecaptchaResponse,ConfigurationAttributeKey.GOOGLE_RECAPTCHA_SITE_SECRET_KEY.get());
            }
            if (!captchaValid) {
                SessionErrors.clear(request);
                response.setRenderParameter("error", "true");
                response.setRenderParameter("recaptchaError", "true");
                if (com.liferay.portal.kernel.util.Validator.isNotNull(redirect)) {
                    model.addAttribute("redirect", com.liferay.portal.kernel.util.HtmlUtil.escape(redirect));
                }
            } else {
                try {
                    completeRegistration(request, response, newAccountBean, redirect, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else {
            response.setRenderParameter("error", "true");
            if (com.liferay.portal.kernel.util.Validator.isNotNull(redirect)) {
                model.addAttribute("redirect", com.liferay.portal.kernel.util.HtmlUtil.escape(redirect));
            }
        }
        SessionErrors.clear(request);
        SessionMessages.clear(request);
    }

    /**
     * Completes the user registration with the parameters set in the CreateUserBean
     *
     * @param request        The ActionRequest object
     * @param response       The ActionResponse object
     * @param newAccountBean The new user bean object
     * @param redirect       Redirect URL for this request (may be null)
     * @throws Exception
     */
    public static void completeRegistration(ActionRequest request, ActionResponse response,
            CreateUserBean newAccountBean,
            String redirect, boolean postRegistration) throws Exception {
        HttpServletRequest httpReq = PortletUtil.getHttpServletRequest(request);
        PortletSession portletSession = request.getPortletSession();
        String fbIdString =
                (String) portletSession.getAttribute(SSOKeys.FACEBOOK_USER_ID, PortletSession.APPLICATION_SCOPE);
        String openId = (String) portletSession.getAttribute(SSOKeys.SSO_OPENID_ID, PortletSession.APPLICATION_SCOPE);



        BalloonCookie balloonCookie = BalloonCookie.fromCookieArray(httpReq.getCookies());

        try {
            final Member user = LoginRegisterUtil.register(newAccountBean.getScreenName(), newAccountBean.getPassword(),
                            newAccountBean.getEmail(), newAccountBean.getFirstName(), newAccountBean.getLastName(),
                            newAccountBean.getShortBio(), newAccountBean.getCountry(), fbIdString, openId,
                            newAccountBean.getImageId(), ConfigurationAttributeKey.COLAB_URL.get());

            // SSO
            if (StringUtils.isNotBlank(fbIdString)) {
                portletSession.removeAttribute(SSOKeys.FACEBOOK_USER_ID, PortletSession.APPLICATION_SCOPE);
            }
            if (StringUtils.isNotBlank(openId)) {
                portletSession.removeAttribute(SSOKeys.SSO_OPENID_ID, PortletSession.APPLICATION_SCOPE);
            }

            if (balloonCookie != null && StringUtils.isNotBlank(balloonCookie.getUuid())) {
                try {
                    BalloonUserTracking but =
                            BalloonsClient.getBalloonUserTracking(balloonCookie.getUuid());
                            //BalloonUserTrackingLocalServiceUtil.getBalloonUserTracking(balloonCookie.getUuid());
                    but.setRegistrationDate(new Timestamp(new Date().getTime()));
                    but.setUserId(user.getId_());
                    BalloonsClient.updateBalloonUserTracking(but);
                } catch (BalloonUserTrackingNotFound e) {
                    _log.error("Can't find balloon user tracking for uuid: {}",
                            balloonCookie.getUuid());
                }
            }

            LoginRegisterUtil.login(request, response, newAccountBean.getScreenName(), newAccountBean.getPassword(), redirect);

            httpReq.getSession().setAttribute("collab_user_has_registered", true);


            ActivityEntryHelper.createActivityEntry(ActivitiesClientUtil.getClient(), user.getUserId(), user.getUserId(),null, ActivityProvidersType.MemberJoinedActivityEntry.getType());


            request.getPortletSession().setAttribute("collab_user_has_registered", true);
            PortletUtil.getHttpServletRequest(request).getSession().setAttribute("collab_user_has_registered", true);
            if (redirect == null) {
                redirect = ConfigurationAttributeKey.COLAB_URL.get();
            }

            if (postRegistration) {
                // Add request variable for after-registration popover
                redirect = HttpUtil.addParameter(redirect, "postRegistration", "true");
            }

            response.sendRedirect(redirect);
        } catch (SystemException | PortalException e) {
            setCreateUserBeanSessionVariables(newAccountBean, portletSession);
            response.sendRedirect("/web/guest/loginregister");
        }
    }

    private static void setCreateUserBeanSessionVariables(CreateUserBean createUserBean,
            PortletSession portletSession) {
        portletSession
                .setAttribute(SSOKeys.SSO_FIRST_NAME, createUserBean.getFirstName(), PortletSession.APPLICATION_SCOPE);
        portletSession
                .setAttribute(SSOKeys.SSO_LAST_NAME, createUserBean.getLastName(), PortletSession.APPLICATION_SCOPE);
        portletSession.setAttribute(SSOKeys.SSO_EMAIL, createUserBean.getEmail(), PortletSession.APPLICATION_SCOPE);
        portletSession.setAttribute(SSOKeys.SSO_SCREEN_NAME, createUserBean.getScreenName(),
                PortletSession.APPLICATION_SCOPE);
        portletSession.setAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, createUserBean.getImageId(),
                PortletSession.APPLICATION_SCOPE);
        portletSession.setAttribute(SSOKeys.SSO_COUNTRY, createUserBean.getCountry(), PortletSession.APPLICATION_SCOPE);
    }

    @ResourceMapping(value = "postRegistration")
    public void updateRegistrationParameters(ResourceRequest request, ResourceResponse response)
            throws IOException, SystemException, PortalException {

        try {
            JSONObject json = new JSONObject();
            json.put("screenName", new JSONObject());
            json.put("bio", new JSONObject());

            String screenName = request.getParameter("screenName");
            String bio = request.getParameter("bio");

            Member loggedInMember = MemberAuthUtil.getMemberOrNull(request);
            if(loggedInMember!= null) {
                User loggedInUser = UserLocalServiceUtil.getUser(loggedInMember.getId_());

                if (!loggedInUser.getScreenName().equals(screenName)) {
                    if (StringUtils.isNotEmpty(screenName) && SharedColabClient
                            .isScreenNameUsed(screenName)
                            && screenName.matches(USER_NAME_REGEX)) {
                        loggedInUser.setScreenName(screenName);
                        json.getJSONObject("screenName").put("success", true);
                    } else {
                        json.getJSONObject("screenName").put("success", false);
                    }
                }

                json.getJSONObject("bio").put("success", true);
                if (StringUtils.isNotEmpty(bio)) {
                    if (bio.length() <= 2000) {
                        loggedInMember
                                .setShortBio(
                                        HtmlUtil.cleanSome(bio, LinkUtils.getBaseUri(request)));
                        MembersClient.updateMember(loggedInMember);
                    } else {
                        json.getJSONObject("bio").put("success", false);
                    }
                }

                UserLocalServiceUtil.updateUser(loggedInUser);
            }
            response.getWriter().write(json.toString());
        }catch (JSONException ignored){

        }
    }
    @ModelAttribute("recaptchaDataSiteKey")
    public String getRecaptchaDataSiteKey(){
        return ConfigurationAttributeKey.GOOGLE_RECAPTCHA_SITE_KEY.get();
    }

    @ResourceMapping(value = "generateScreenName")
    public void generateScreenName(ResourceRequest request, ResourceResponse response)
            throws IOException, SystemException, PortalException {

        JSONObject json = new JSONObject();
        final String firstName = request.getParameter("firstName");
        final String lastName = request.getParameter("lastName");

        try {
            try {
                json.put("screenName", MembersClient.generateScreenName(lastName, firstName));
                json.put("success", true);
            } catch (HttpClientErrorException e) {
                _log.warn("Failed to generate user name ", e);
                json.put("success", false);
                json.put("error", e.toString());
            }
        }catch (JSONException ignored){

        }

        response.getWriter().write(json.toString());
    }


}
