package org.xcolab.view.theme;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.theme.ColabTheme;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.util.http.servlet.RequestUtil;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.auth.login.AuthenticationError;
import org.xcolab.view.socialmedia.SocialMediaEngine;
import org.xcolab.view.util.MetaKeys;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.util.entity.flash.AnalyticsAttribute;
import org.xcolab.view.util.entity.flash.ErrorMessage;
import org.xcolab.view.util.entity.flash.InfoMessage;

import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ThemeVariableInterceptor extends HandlerInterceptorAdapter {

    private final AuthenticationService authenticationService;

    public ThemeVariableInterceptor(AuthenticationService authenticationService) {
        Assert.notNull(authenticationService, "AuthenticationContext is required");
        this.authenticationService = authenticationService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {
        if (modelAndView != null && !isRedirectView(modelAndView)) {
            modelAndView.addObject(new ThemeContext(authenticationService));
        }
    }

    private boolean readBooleanParameter(HttpServletRequest request, String name) {
        return Boolean.parseBoolean(request.getParameter(name));
    }

    private boolean isRedirectView(ModelAndView modelAndView) {
        return (modelAndView.getView() != null && modelAndView.getView() instanceof RedirectView)
                || modelAndView.getViewName().startsWith("redirect:");
    }

}
