package org.xcolab.portlets.loginregister;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.validation.Valid;

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
import org.xcolab.utils.PropertiesUtils;
import org.xcolab.utils.ReCaptchaUtils;

import com.ext.portlet.community.CommunityConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
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

//import javax.validation.Validator;

@Controller
@RequestMapping("view")
public class MainViewController {

	private final static String RECAPTCHA_KEY_PUBLIC = "captcha.engine.recaptcha.key.public";
	private final static String RECAPTCHA_URL_SCRIPT = "captcha.engine.recaptcha.url.script";
	private final static String RECAPTCHA_URL_NOSCRIPT = "captcha.engine.recaptcha.url.noscript";
	private long DEFAULT_COMPANY_ID = 10112L;

	@Autowired
	private Validator validator;

	@Autowired
	private MessageSource messageSource;

	@InitBinder("createUserBean")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * Main view displayed when user enters page with loginregister portlet
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
			model.addAttribute("createUserBean", new CreateUserBean());
			model.addAttribute("redirect", HtmlUtil.escape(redirect));
			addRecaptchaPropertiesToModel(model);
		}
		return "view";
	}

	@RequestMapping(params = "error=true")
	public String registerError(PortletRequest request, Model model,
			@Valid CreateUserBean newAccountBean, BindingResult result,
			@RequestParam(required = false) String redirect) {
		if (request.getParameter("recaptchaError") != null) {
			result.addError(new ObjectError("createUserBean",
					"Invalid words in recaptcha field"));
		}
		model.addAttribute("redirect", HtmlUtil.escape(redirect));

		return "view";
	}

	@RequestMapping(params = "action=add")
	public void registerUser(ActionRequest request, Model model,
			ActionResponse response, @Valid CreateUserBean newAccountBean,
			BindingResult result,
			@RequestParam(required = false) String redirect) {
		addRecaptchaPropertiesToModel(model);

		if (!result.hasErrors()) {
			if (!ReCaptchaUtils.validateCaptcha(request)) {
				SessionErrors.clear(request);
				response.setRenderParameter("error", "true");
				response.setRenderParameter("recaptchaError", "true");
			} else {
				try {
					ServiceContext serviceContext = ServiceContextFactory
							.getInstance(User.class.getName(), request);
					ThemeDisplay themeDisplay = (ThemeDisplay) request
							.getAttribute(WebKeys.THEME_DISPLAY);

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

					LoginUtil.logUserIn(request, response,
							newAccountBean.getScreenName(),
							newAccountBean.getPassword());

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
	}

	private void addRecaptchaPropertiesToModel(Model model) {
		model.addAttribute("recaptchaUrlScript",
				PropertiesUtils.get(RECAPTCHA_URL_SCRIPT));
		model.addAttribute("recaptchaUrlNoscript",
				PropertiesUtils.get(RECAPTCHA_URL_NOSCRIPT));
		model.addAttribute("recaptchaKeyPublic",
				PropertiesUtils.get(RECAPTCHA_KEY_PUBLIC));
	}

}
