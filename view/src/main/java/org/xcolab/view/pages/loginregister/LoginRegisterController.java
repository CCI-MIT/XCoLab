package org.xcolab.view.pages.loginregister;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.tracking.ITrackingClient;
import org.xcolab.client.tracking.pojo.ILocation;
import org.xcolab.client.user.IUserLoginRegisterClient;
import org.xcolab.commons.CountryUtil;
import org.xcolab.commons.recaptcha.RecaptchaValidator;
import org.xcolab.commons.servlet.RequestParamUtil;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.i18n.ResourceMessageResolver;
import org.xcolab.view.pages.loginregister.exception.UserLocationNotResolvableException;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginRegisterController {

    private static final Logger _log = LoggerFactory.getLogger(LoginRegisterController.class);

    private static final String REGISTER_VIEW_NAME = "loginregister/register";
    private final LoginRegisterService loginRegisterService;

    private final ResourceMessageResolver resourceMessageResolver;
    private final RecaptchaValidator recaptchaValidator;

    @Autowired
    private ITrackingClient trackingClient;

    @Autowired
    private IUserLoginRegisterClient userLoginRegister;

    @Autowired
    public LoginRegisterController(LoginRegisterService loginRegisterService,
            ResourceMessageResolver resourceMessageResolver) {
        this.loginRegisterService = loginRegisterService;
        this.resourceMessageResolver = resourceMessageResolver;
        final String recaptchaSecret = PlatformAttributeKey.GOOGLE_RECAPTCHA_SITE_SECRET_KEY.get();
        recaptchaValidator = new RecaptchaValidator(recaptchaSecret);
    }

    //    @Autowired
//    private Validator validator;
//
//    @InitBinder("createUserBean")
//    public void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }
    
    @GetMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response, Model model) {

        if(!ConfigurationAttributeKey.ALLOW_SELF_REGISTRATION.get()) {
            return new AccessDeniedPage(null).toViewName(response);
        }

        String redirect = RequestParamUtil.getString(request, "redirect");

        if (redirect == null || redirect.trim().isEmpty()) {
            redirect = request.getParameter("redirect");
            if (redirect == null) {
                redirect = request.getHeader(HttpHeaders.REFERER);
            }
        }
        if (MemberAuthUtil.getUserId() > 0) {
            return "redirect:/";
        }

        if (StringUtils.isNotEmpty(redirect)) {
            model.addAttribute("redirect", redirect);
        }

        // append SSO attributes
        CreateUserBean userBean = new CreateUserBean();
        model.addAttribute("createUserBean", userBean);

        // Get country location
        if (StringUtils.isEmpty(userBean.getCountry())) {
            try {
                userBean.setLanguage(I18nUtils.DEFAULT_LOCALE.getLanguage());
                userBean.setCountry(getCountryCodeFromRemoteAddress(request.getRemoteAddr()));
            } catch (UserLocationNotResolvableException ignored) {
            }
        }
        model.addAttribute("generateScreenName", ConfigurationAttributeKey.GENERATE_SCREEN_NAME.get());
        final String loginInfoText = ConfigurationAttributeKey.LOGIN_INFO_MESSAGE.get();
        model.addAttribute("hasLoginInfoText", StringUtils.isNotBlank(loginInfoText));
        model.addAttribute("loginInfoText", loginInfoText);
        model.addAttribute("countrySelectItems", CountryUtil.getSelectOptions());

        model.addAttribute("isI18NActive",ConfigurationAttributeKey.IS_I18N_ACTIVE.get());
        model.addAttribute("languageSelectItems", I18nUtils.getSelectList());
        return REGISTER_VIEW_NAME;
    }

    private String getCountryCodeFromRemoteAddress(String ipAddr) throws UserLocationNotResolvableException {
        try {
            ILocation location = trackingClient.getLocationForIp(ipAddr);
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

        if(!ConfigurationAttributeKey.ALLOW_SELF_REGISTRATION.get()) {
            return new AccessDeniedPage(null).toViewName(response);
        }
        if (result.hasErrors()) {
            return showRegistrationError(model);
        }
        boolean captchaValid = true;
        // require captcha if user is not logged in via SSO
        if (PlatformAttributeKey.GOOGLE_RECAPTCHA_IS_ACTIVE.get()) {
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            captchaValid = recaptchaValidator.verify(gRecaptchaResponse);
        }
        if (!captchaValid) {
            result.addError(new ObjectError("createUserBean", resourceMessageResolver.
                    getLocalizedMessage("register.form.validation.captcha.message")));
            return showRegistrationError(model);
        }
        //TODO COLAB-2617: improve redirect to avoid double handling
        loginRegisterService.completeRegistration(request, response, newAccountBean, redirect, false);
        return REGISTER_VIEW_NAME;
    }

    private String showRegistrationError(Model model) {
        model.addAttribute("countrySelectItems", CountryUtil.getSelectOptions());
        model.addAttribute("isI18NActive",ConfigurationAttributeKey.IS_I18N_ACTIVE.get());
        model.addAttribute("languageSelectItems", I18nUtils.getSelectList());
        return REGISTER_VIEW_NAME;
    }

    @ModelAttribute("recaptchaDataSiteKey")
    public String getRecaptchaDataSiteKey(){
        return PlatformAttributeKey.GOOGLE_RECAPTCHA_SITE_KEY.get();
    }

    @PostMapping("/api/register/generateScreenName")
    public void generateScreenName(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        JSONObject json = new JSONObject();
        final String firstName = request.getParameter("firstName");
        final String lastName = request.getParameter("lastName");

        try {
            try {
                json.put("screenName", userLoginRegister.generateScreenName(lastName, firstName));
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
