package org.xcolab.portlets.contactform.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.xcolab.utils.ReCaptchaUtils;

import com.liferay.portal.util.PortalUtil;



public class CaptchaValidator implements Validator {

    @Override
    public void validate(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String challengeId = component.getAttributes().get("challengeId").toString();
        
        UIInput challenge = (UIInput) component.getParent().findComponent(challengeId);
        
        PortletRequest portletRequest = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        PortletRequest realPortletRequest = (PortletRequest) portletRequest.getAttribute("javax.portlet.request");
        HttpServletRequest request = PortalUtil.getHttpServletRequest(realPortletRequest);
        
        String remoteIp = request.getRemoteAddr();
        String recaptchaChallenge = challenge.getValue() != null ? challenge.getValue().toString() : "";
        String recaptchaResponse = value.toString();
        
        if (! ReCaptchaUtils.validateCaptcha(recaptchaChallenge, recaptchaResponse, remoteIp)) {
            FacesMessage fm = new FacesMessage();
            fm.setSummary("Captcha verification failed. Provided value is invalid.");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(fm);
        }
    }

}
