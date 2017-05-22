package org.xcolab.view.i18n;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.MemberAuthUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomLocaleChangeInterceptor extends LocaleChangeInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        Member member  = MemberAuthUtil.getMemberOrNull(request);
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if(member!=null) {
            localeResolver.setLocale(request, response, this.parseLocaleValue(member.getDefaultLocale()));
        }

        return super.preHandle(request,response,handler);
    }
}
