package org.xcolab.portlets.loginregister;

import java.io.IOException;
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
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.loginregister.activity.LoginRegisterActivityKeys;
import org.xcolab.portlets.loginregister.singlesignon.SSOKeys;

import com.ext.portlet.NoSuchBalloonUserTrackingException;
import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.model.BalloonUserTracking;
import com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil;
import com.ext.utils.authentication.service.AuthenticationServiceUtil;
import com.liferay.portal.kernel.captcha.CaptchaException;
import com.liferay.portal.kernel.captcha.CaptchaUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

//import javax.validation.Validator;

@Controller
@RequestMapping("view")
public class MainViewController {

	private long DEFAULT_COMPANY_ID = 10112L;

	@Autowired
	private Validator validator;

	@Autowired
	private MessageSource messageSource;
	
	private final static Log _log = LogFactoryUtil.getLog(MainViewController.class);

    @InitBinder("createUserBean")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * Main view displayed for contactform
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String register(PortletRequest request, PortletResponse response,
			Model model) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

		HttpServletRequest httpRequest = PortalUtil
				.getHttpServletRequest(request);

		while (httpRequest instanceof HttpServletRequestWrapper) {
			httpRequest = (HttpServletRequest) ((HttpServletRequestWrapper) httpRequest)
					.getRequest();
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (redirect == null || redirect.trim().length() == 0) {
			redirect = httpRequest.getParameter("redirect");
			if (redirect == null) {
				redirect = PortalUtil.getHttpServletRequest(request).getHeader(
						"referer");
			}
		}
		if (themeDisplay.isSignedIn()) {
			return "signedIn_logout";
		} else {

			model.addAttribute("redirect", HtmlUtil.escape(redirect));

            // append SSO attributes
            CreateUserBean userBean = new CreateUserBean();
            getSSOUserInfo(request.getPortletSession(),userBean);
            model.addAttribute("createUserBean", userBean);
		}
		return "view";
	}

    private void getSSOUserInfo(PortletSession portletSession, CreateUserBean createUserBean){
        // append SSO attributes from session
        String fbIdString = (String) portletSession.getAttribute(SSOKeys.FACEBOOK_USER_ID ,PortletSession.APPLICATION_SCOPE);
        String openId = (String) portletSession.getAttribute(SSOKeys.SSO_OPENID_ID,PortletSession.APPLICATION_SCOPE);
        String firstName = (String) portletSession.getAttribute(SSOKeys.SSO_FIRST_NAME,PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_FIRST_NAME,PortletSession.APPLICATION_SCOPE);
        String lastName = (String) portletSession.getAttribute(SSOKeys.SSO_LAST_NAME,PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_LAST_NAME,PortletSession.APPLICATION_SCOPE);
        String eMail = (String) portletSession.getAttribute(SSOKeys.SSO_EMAIL,PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_EMAIL,PortletSession.APPLICATION_SCOPE);
        String screenName = (String) portletSession.getAttribute(SSOKeys.SSO_SCREEN_NAME,PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_SCREEN_NAME,PortletSession.APPLICATION_SCOPE);
        String imageId = (String) portletSession.getAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID,PortletSession.APPLICATION_SCOPE);
        portletSession.removeAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID,PortletSession.APPLICATION_SCOPE);

        if ((StringUtils.isNotBlank(fbIdString) || StringUtils.isNotBlank(openId) )&& StringUtils.isNotBlank(eMail)){
            createUserBean.setFirstName(firstName);
            createUserBean.setLastName(lastName);
            createUserBean.setEmail(eMail);
            createUserBean.setScreenName(screenName);
            createUserBean.setImageId(imageId);
            createUserBean.setCaptchaNeeded(false);
        }
    }
	

    @RequestMapping(params = "captcha=true")
    public String getCaptchaImage(PortletRequest request, PortletResponse response) throws IOException {
        CaptchaUtil.serveImage(PortalUtil.getHttpServletRequest(request), PortalUtil.getHttpServletResponse(response));
        
        return null;
    }

	@RequestMapping(params = "error=true")
	public String registerError(PortletRequest request, Model model,
			@Valid CreateUserBean newAccountBean, BindingResult result,
			@RequestParam(required = false) String redirect) {
		if (request.getParameter("recaptchaError") != null) {
			result.addError(new ObjectError("createUserBean",
					"Invalid words in captcha field"));
		}
		
		model.addAttribute("redirect", HtmlUtil.escape(redirect));

		return "view";
	}

	@RequestMapping(params = "action=add")
	public void registerUser(ActionRequest request, Model model,
			ActionResponse response, @Valid CreateUserBean newAccountBean,
			BindingResult result,
			@RequestParam(required = false) String redirect) {
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

        PortletSession portletSession = request.getPortletSession();
        String fbIdString = (String) portletSession.getAttribute(SSOKeys.FACEBOOK_USER_ID,PortletSession.APPLICATION_SCOPE);
        String openId = (String) portletSession.getAttribute(SSOKeys.SSO_OPENID_ID,PortletSession.APPLICATION_SCOPE);
		
		if (!result.hasErrors()) {
		    boolean captchaValid = true;
            // require capcha if user is not logged in via SSO
		    if (fbIdString == null && openId == null){
                try {
                    CaptchaUtil.check(request);
                }
                catch (CaptchaException e) {
                    captchaValid = false;
                }
            }
			if (!captchaValid) {
				SessionErrors.clear(request);
				response.setRenderParameter("error", "true");
				response.setRenderParameter("recaptchaError", "true");
			} else {
				try {
					ServiceContext serviceContext = ServiceContextFactory
							.getInstance(User.class.getName(), request);
					ThemeDisplay themeDisplay = (ThemeDisplay) request
							.getAttribute(WebKeys.THEME_DISPLAY);
					
					BalloonCookie balloonCookie = BalloonCookie.fromCookieArray(httpReq.getCookies());

					User user = UserServiceUtil.addUserWithWorkflow(
							DEFAULT_COMPANY_ID, false,
							newAccountBean.getPassword(),
							newAccountBean.getRetypePassword(), false,
							newAccountBean.getScreenName(),
							newAccountBean.getEmail(), 0L, "",
							themeDisplay.getLocale(),
							newAccountBean.getFirstName(), "",
							newAccountBean.getLastName(), 0, 0, true, 1, 1,
							1970, "", new long[] {}, new long[] {},
							new long[] {}, new long[] {}, true, serviceContext);

					if (newAccountBean.getShortBio() != null
							&& newAccountBean.getShortBio().length() > 0) {
						ExpandoValueLocalServiceUtil.addValue(
								User.class.getName(),
								CommunityConstants.EXPANDO,
								CommunityConstants.BIO, user.getUserId(),
								newAccountBean.getShortBio());
					}

					if (newAccountBean.getCountry() != null
							&& newAccountBean.getCountry().length() > 0) {
						ExpandoValueLocalServiceUtil.addValue(
								User.class.getName(),
								CommunityConstants.EXPANDO,
								CommunityConstants.COUNTRY, user.getUserId(),
								newAccountBean.getCountry());
					}
					
					if (balloonCookie != null && StringUtils.isNotBlank(balloonCookie.getUuid())) {
						try {
							BalloonUserTracking but = BalloonUserTrackingLocalServiceUtil.getBalloonUserTracking(balloonCookie.getUuid());
							but.setRegistrationDate(new Date());
							but.setUserId(user.getUserId());
							BalloonUserTrackingLocalServiceUtil.updateBalloonUserTracking(but);
						}
						catch (NoSuchBalloonUserTrackingException e) {
							_log.error("Can't find balloon user tracking for uuid: " + balloonCookie.getUuid());
						}
					}

                    // SSO
                    if (StringUtils.isNotBlank(fbIdString)){
                        try{
                            long fbId = Long.parseLong(fbIdString);
                            user.setFacebookId(fbId);
                            UserLocalServiceUtil.updateUser(user);
                            portletSession.removeAttribute(SSOKeys.FACEBOOK_USER_ID,PortletSession.APPLICATION_SCOPE);
                            redirect = null;
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if (StringUtils.isNotBlank(openId)){
                        try{
                            user.setOpenId(openId);
                            UserLocalServiceUtil.updateUser(user);
                            portletSession.removeAttribute(SSOKeys.SSO_OPENID_ID,PortletSession.APPLICATION_SCOPE);
                            redirect = null;
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }

					if (newAccountBean.getImageId() != null
							&& newAccountBean.getImageId().length() > 0) {
						Image img = ImageLocalServiceUtil.getImage(Long
								.parseLong(newAccountBean.getImageId()));
						byte[] bytes = img.getTextObj();
						// we need to set permission checker for liferay
						PermissionChecker permissionChecker = PermissionCheckerFactoryUtil
								.create(user, true);

						PermissionThreadLocal
								.setPermissionChecker(permissionChecker);

						UserServiceUtil.updatePortrait(user.getUserId(), bytes);
						user.setPortraitId(0L);
						UserLocalServiceUtil.updateUser(user);
						UserServiceUtil.updatePortrait(user.getUserId(), bytes);
						user = UserLocalServiceUtil.getUser(user.getUserId());
					}

		    		AuthenticationServiceUtil.logUserIn(request, response, newAccountBean.getScreenName(), newAccountBean.getPassword());
					
					httpReq.getSession().setAttribute("collab_user_has_registered", true);
					

		            SocialActivityLocalServiceUtil.addActivity(user.getUserId(), themeDisplay.getScopeGroupId(), User.class.getName(),
		                    user.getUserId(), LoginRegisterActivityKeys.USER_REGISTERED.getType(), null, 0);

					request.getPortletSession().setAttribute("collab_user_has_registered", true);
					PortalUtil.getHttpServletRequest(request).getSession().setAttribute("collab_user_has_registered", true);
					if (redirect != null) {
						response.sendRedirect(redirect);
					} else {
						response.sendRedirect(themeDisplay.getURLHome());
					}

				} catch (PortalException | SystemException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} else {
			response.setRenderParameter("error", "true");
		}        
        SessionErrors.clear(request);
        SessionMessages.clear(request);
	}
	
	@ResourceMapping
	public void captchaHandler(ResourceRequest request, ResourceResponse response) throws IOException {
	    
	    CaptchaUtil.serveImage(request, response);
	}

}
