package org.xcolab.utils;

import java.io.IOException;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.liferay.portal.kernel.captcha.CaptchaTextException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;

public class ReCaptchaUtils {

    private static final Log _log = LogFactoryUtil.getLog(ReCaptchaUtils.class);

    public static boolean validateCaptcha(PortletRequest request) {
        HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
        
        try {
            String remoteIp = servletRequest.getRemoteAddr();
            String recaptchaChallenge = ParamUtil.getString(request, "recaptcha_challenge_field");
            String recaptchaResponse = ParamUtil.getString(request, "recaptcha_response_field");
            if (remoteIp.trim().length() == 0 || recaptchaChallenge.trim().length() == 0) {
                return false;
            }
            
            performValidation(recaptchaChallenge, recaptchaResponse, remoteIp);
        }
        catch (CaptchaTextException e) {
            SessionErrors.add(request, CaptchaTextException.class.getName());
            return false;
        }
        return true;
    }
    

    public static boolean validateCaptcha(String recaptchaChallenge, String recaptchaResponse, String remoteIp) {
        try {
            performValidation(recaptchaChallenge, recaptchaResponse, remoteIp);
        }
        catch (CaptchaTextException e) {
            return false;
        }
        return true;
    }
    
/*
    public static void validateCaptcha(PortletRequest request) throws CaptchaTextException {
        
    }
    */
    private static void performValidation(String recaptchaChallenge, String recaptchaResponse, String remoteIp) throws CaptchaTextException {
        PostMethod post = null;
        String postURL = null;
        String privateKey = null;

        try {
            postURL = PropertiesUtils.get("captcha.engine.recaptcha.url.verify");
            privateKey = PropertiesUtils.get("captcha.engine.recaptcha.key.private");
        } catch (Exception e) {
            _log.error("Can't read recaptcha related properties.", e);
            throw new CaptchaTextException(e);
        }

        try {
            
            if (recaptchaResponse.equals(StringPool.BLANK)) {
                throw new CaptchaTextException();
            }

            HttpClient client = new HttpClient();
            post = new PostMethod(postURL);
            post.addParameter("privatekey", privateKey);
            post.addParameter("remoteip", remoteIp);
            post.addParameter("challenge", recaptchaChallenge);
            post.addParameter("response", recaptchaResponse);

            client.executeMethod(post);
            String response = post.getResponseBodyAsString();

            if (post == null || !response.startsWith("true")) {
                throw new CaptchaTextException("Invalid captcha text supplied");
            }

        } catch (HttpException e) {
            _log.error("An error occurred when performing captcha validation.", e);
            throw new CaptchaTextException(e);
        } catch (IOException e) {
            _log.error("An error occurred when performing captcha validation.", e);
            throw new CaptchaTextException(e);
        }
    }

}
