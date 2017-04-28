package org.xcolab.view.pages.loginregister;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.LockoutLoginException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.PasswordLoginException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.util.CountryUtil;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.loginregister.exception.UserLocationNotResolvableException;
import org.xcolab.view.pages.loginregister.singlesignon.SSOKeys;
import org.xcolab.view.util.entity.HttpUtils;
import org.xcolab.view.util.entity.ReCaptchaUtils;
import org.xcolab.view.util.entity.portlet.RequestParamUtil;
import org.xcolab.view.util.entity.portlet.session.SessionErrors;
import org.xcolab.view.util.entity.portlet.session.SessionMessages;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginRegisterController {

    private static final Logger _log = LoggerFactory.getLogger(LoginRegisterController.class);

    public static final String SSO_TARGET_KEY = "SSO_TARGET_KEY";
    public static final String SSO_TARGET_REGISTRATION = "SSO_TARGET_REGISTRATION";
    public static final String SSO_TARGET_LOGIN = "SSO_TARGET_LOGIN";
    public static final String PRE_LOGIN_REFERRER_KEY = "PRE_LOGIN_REFERRER_KEY";

    private static final String USER_NAME_REGEX = "^[a-zA-Z0-9]+$";
    public static final String REGISTER_VIEW_NAME = "loginregister/register";
    public static final String LOGIN_VIEW_NAME = "loginregister/login";

    //    @Autowired
//    private Validator validator;
//
//    @InitBinder("createUserBean")
//    public void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }

    @GetMapping("/login")
    public String login() {
        return LOGIN_VIEW_NAME;
    }

    /**
     * Main view displayed for contact form
     */
    @GetMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response, Model model) {

        String redirect = RequestParamUtil.getString(request, "redirect");

        if (redirect == null || redirect.trim().isEmpty()) {
            redirect = request.getParameter("redirect");
            if (redirect == null) {
                HttpSession session = request.getSession();
                redirect = (String) session.getAttribute(LoginRegisterController.PRE_LOGIN_REFERRER_KEY);
            }
            if (redirect == null) {
                redirect = request.getHeader(HttpHeaders.REFERER);
            }
        }
        if (MemberAuthUtil.getMemberId(request) > 0) {
            return "redirect:/";
        }

        if (StringUtils.isNotEmpty(redirect)) {
            //TODO: or escape?
            model.addAttribute("redirect", redirect);
        }

        // append SSO attributes
        CreateUserBean userBean = new CreateUserBean();
        getSSOUserInfo(request.getSession(), userBean);
        model.addAttribute("createUserBean", userBean);

        // Get country location
        if (StringUtils.isEmpty(userBean.getCountry())) {
            try {
                userBean.setCountry(getCountryCodeFromRemoteAddress(request.getRemoteAddr()));
            } catch (UserLocationNotResolvableException ignored) {
            }
        }
        model.addAttribute("generateScreenName", ConfigurationAttributeKey.GENERATE_SCREEN_NAME.get());
        boolean isSharedColab = ConfigurationAttributeKey.IS_SHARED_COLAB.get();
        final String loginInfoText = ConfigurationAttributeKey.LOGIN_INFO_MESSAGE.get();
        model.addAttribute("hasLoginInfoText", StringUtils.isNotBlank(loginInfoText));
        model.addAttribute("loginInfoText", loginInfoText);
        model.addAttribute("countrySelectItems", CountryUtil.getSelectOptions());
        return REGISTER_VIEW_NAME;
    }

    public static void getSSOUserInfo(HttpSession session, CreateUserBean createUserBean) {
        // append SSO attributes from session
        String fbIdString =
                (String) session.getAttribute(SSOKeys.FACEBOOK_USER_ID);
        String googleId = (String) session.getAttribute(SSOKeys.SSO_GOOGLE_ID);
        String firstName =
                (String) session.getAttribute(SSOKeys.SSO_FIRST_NAME);
        session.removeAttribute(SSOKeys.SSO_FIRST_NAME);
        String lastName = (String) session.getAttribute(SSOKeys.SSO_LAST_NAME);
        session.removeAttribute(SSOKeys.SSO_LAST_NAME);
        String eMail = (String) session.getAttribute(SSOKeys.SSO_EMAIL);
        session.removeAttribute(SSOKeys.SSO_EMAIL);
        String screenName =
                (String) session.getAttribute(SSOKeys.SSO_SCREEN_NAME);
        session.removeAttribute(SSOKeys.SSO_SCREEN_NAME);
        String imageId =
                (String) session.getAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID);
        session.removeAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID);
        String country = (String) session.getAttribute(SSOKeys.SSO_COUNTRY);
        session.removeAttribute(SSOKeys.SSO_COUNTRY);

        if ((StringUtils.isNotBlank(fbIdString) || googleId != null)) {
            createUserBean.setFirstName(firstName);
            createUserBean.setLastName(lastName);
            createUserBean.setEmail(eMail);
            createUserBean.setScreenName(screenName);
            createUserBean.setImageId(imageId);
            createUserBean.setCaptchaNeeded(false);
        }

        if (StringUtils.isNotEmpty(country)) {
            createUserBean.setCountry(country);
        }
    }

    private String getCountryCodeFromRemoteAddress(String ipAddr) throws UserLocationNotResolvableException {
        try {
            Location location = TrackingClient.getLocationForIp(ipAddr);
            if (location != null) {
                return location.getCountry();
            }
        } catch (Exception e) {
            throw new UserLocationNotResolvableException(
                    String.format("Could not retrieve country from IP address %s", ipAddr), e);
        }
        throw new UserLocationNotResolvableException(
                String.format("Could not retrieve country from IP address %s", ipAddr));
    }

    @PostMapping("/register")
    public String registerUser(HttpServletRequest request, HttpServletResponse response, Model model,
            @Valid CreateUserBean newAccountBean, BindingResult result,
            @RequestParam(required = false) String redirect) throws IOException {

        HttpSession session = request.getSession();
        String fbIdString = (String) session.getAttribute(SSOKeys.FACEBOOK_USER_ID);
        String googleId = (String) session.getAttribute(SSOKeys.SSO_GOOGLE_ID);

        if (result.hasErrors()) {
            return showRegistrationError();
        }
        boolean captchaValid = true;
        // require captcha if user is not logged in via SSO
        if (fbIdString == null && googleId == null
                && ConfigurationAttributeKey.GOOGLE_RECAPTCHA_IS_ACTIVE.get()) {
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            captchaValid = ReCaptchaUtils.verify(gRecaptchaResponse,
                    ConfigurationAttributeKey.GOOGLE_RECAPTCHA_SITE_SECRET_KEY.get());
        }
        if (!captchaValid) {
            SessionErrors.clear(request);
            result.addError(new ObjectError("createUserBean", "Please click the box"));
            return showRegistrationError();
        }
        //TODO: improve redirect to avoid double handling
        completeRegistration(request, response, newAccountBean, redirect, false);
        SessionErrors.clear(request);
        SessionMessages.clear(request);
        return REGISTER_VIEW_NAME;
    }

    private String showRegistrationError() {
        return REGISTER_VIEW_NAME;
    }

    /**
     * Completes the user registration with the parameters set in the CreateUserBean
     *
     * @param request        The HttpServletRequest object
     * @param response       The HttpServletResponse object
     * @param newAccountBean The new user bean object
     * @param redirect       Redirect URL for this request (may be null)
     */
    public static void completeRegistration(HttpServletRequest request, HttpServletResponse response,
            CreateUserBean newAccountBean, String redirect, boolean postRegistration)
            throws IOException {
        HttpSession session = request.getSession();
        String fbIdString =
                (String) session.getAttribute(SSOKeys.FACEBOOK_USER_ID);
        String googleId = (String) session.getAttribute(SSOKeys.SSO_GOOGLE_ID);

        BalloonCookie balloonCookie = BalloonCookie.fromCookieArray(request.getCookies());

        final Member user = LoginRegisterUtil.register(newAccountBean.getScreenName(), newAccountBean.getPassword(),
                        newAccountBean.getEmail(), newAccountBean.getFirstName(), newAccountBean.getLastName(),
                        newAccountBean.getShortBio(), newAccountBean.getCountry(), fbIdString, googleId,
                        newAccountBean.getImageId(), ConfigurationAttributeKey.COLAB_URL.get());

        // SSO
        if (StringUtils.isNotBlank(fbIdString)) {
            session.removeAttribute(SSOKeys.FACEBOOK_USER_ID);
        }
        if (googleId != null) {
            session.removeAttribute(SSOKeys.SSO_GOOGLE_ID);
        }

        if (balloonCookie != null && StringUtils.isNotBlank(balloonCookie.getUuid())) {
            try {
                BalloonUserTracking but =
                        BalloonsClient.getBalloonUserTracking(balloonCookie.getUuid());
                but.setRegistrationDate(new Timestamp(new Date().getTime()));
                but.setUserId(user.getId_());
                BalloonsClient.updateBalloonUserTracking(but);
            } catch (BalloonUserTrackingNotFound e) {
                _log.error("Can't find balloon user tracking for uuid: {}",
                        balloonCookie.getUuid());
            }
        }

        try {
            LoginRegisterUtil.login(request, newAccountBean.getScreenName(), newAccountBean.getPassword(), redirect);
        } catch (MemberNotFoundException | PasswordLoginException | LockoutLoginException e) {
            throw new InternalException(e);
        }

        session.setAttribute("collab_user_has_registered", true);

        ActivityEntryHelper.createActivityEntry(ActivitiesClientUtil.getClient(), user.getUserId(),
                user.getUserId(), null, ActivityProvidersType.MemberJoinedActivityEntry.getType());

        if (redirect == null) {
            redirect = "/";
        }

        if (postRegistration) {
            // Add request variable for after-registration popover
            redirect = HttpUtils.addParameter(redirect, "postRegistration", "true");
        }

        response.sendRedirect(redirect);
    }

    private static void setCreateUserBeanSessionVariables(CreateUserBean createUserBean,
            HttpSession portletSession) {
        portletSession
                .setAttribute(SSOKeys.SSO_FIRST_NAME, createUserBean.getFirstName());
        portletSession
                .setAttribute(SSOKeys.SSO_LAST_NAME, createUserBean.getLastName());
        portletSession.setAttribute(SSOKeys.SSO_EMAIL, createUserBean.getEmail());
        portletSession.setAttribute(SSOKeys.SSO_SCREEN_NAME, createUserBean.getScreenName());
        portletSession.setAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, createUserBean.getImageId());
        portletSession.setAttribute(SSOKeys.SSO_COUNTRY, createUserBean.getCountry());
    }

    @PostMapping("/register/finalize")
    public void updateRegistrationParameters(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            JSONObject json = new JSONObject();
            json.put("screenName", new JSONObject());
            json.put("bio", new JSONObject());

            String screenName = request.getParameter("screenName");
            String bio = request.getParameter("bio");

            Member loggedInMember = MemberAuthUtil.getMemberOrNull(request);
            if(loggedInMember!= null) {

                if (!loggedInMember.getScreenName().equals(screenName)) {
                    if (StringUtils.isNotEmpty(screenName) && SharedColabClient
                            .isScreenNameUsed(screenName)
                            && screenName.matches(USER_NAME_REGEX)) {
                        loggedInMember.setScreenName(screenName);
                        json.getJSONObject("screenName").put("success", true);
                    } else {
                        json.getJSONObject("screenName").put("success", false);
                    }
                }

                json.getJSONObject("bio").put("success", true);
                if (StringUtils.isNotEmpty(bio)) {
                    if (bio.length() <= 2000) {
                        loggedInMember.setShortBio(
                                        HtmlUtil.cleanSome(bio, LinkUtils.getBaseUri(request)));
                        MembersClient.updateMember(loggedInMember);
                    } else {
                        json.getJSONObject("bio").put("success", false);
                    }
                }
                MembersClient.updateMember(loggedInMember);
            }
            response.getWriter().write(json.toString());
        } catch (JSONException ignored) {

        }
    }

    @ModelAttribute("recaptchaDataSiteKey")
    public String getRecaptchaDataSiteKey(){
        return ConfigurationAttributeKey.GOOGLE_RECAPTCHA_SITE_KEY.get();
    }

    @PostMapping("/api/register/generateScreenName")
    public void generateScreenName(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

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
        } catch (JSONException ignored) {

        }

        response.getWriter().write(json.toString());
    }
}
