package org.xcolab.view.config.spring.sso.views;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import org.xcolab.view.config.spring.sso.openid.OpenIdHelper;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"authorizationRequest"})
public class ApprovalController {

    @RequestMapping({"/oauth/confirm_access"})
    public ModelAndView showConfirmationPage(HttpServletRequest request, Map<String, Object> model,
            AuthorizationRequest authorizationRequest) {
        model.put("approvalModel", new ApprovalModel(authorizationRequest));
        return new ModelAndView("oauth/confirmation", model);
    }

    public static class ApprovalModel {

        private final AuthorizationRequest authorizationRequest;

        public ApprovalModel(AuthorizationRequest authorizationRequest) {
            this.authorizationRequest = authorizationRequest;
        }

        public boolean hasOpenIdScope() {
            return authorizationRequest.getScope().contains(OpenIdHelper.SCOPE_OPENID);
        }

        public boolean hasEmailScope() {
            return authorizationRequest.getScope().contains(OpenIdHelper.EMAIL);
        }

        public boolean hasProfileScope() {
            return authorizationRequest.getScope().contains(OpenIdHelper.SCOPE_PROFILE);
        }

    }
}
