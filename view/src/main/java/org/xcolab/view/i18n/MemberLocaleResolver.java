package org.xcolab.view.i18n;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.i18n.AbstractLocaleContextResolver;

import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.view.auth.AuthenticationService;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLocaleResolver extends AbstractLocaleContextResolver {

    private final AuthenticationService authenticationService;
    private final LocaleContextResolver fallbackResolver;

    public MemberLocaleResolver(AuthenticationService authenticationService,
            LocaleContextResolver fallbackResolver) {
        this.authenticationService = authenticationService;
        this.fallbackResolver = fallbackResolver;
    }

    @Override
    public LocaleContext resolveLocaleContext(HttpServletRequest request) {
        final Member realMember = authenticationService.getRealMemberOrNull();
        if (realMember != null) {
            final String defaultLocale = realMember.getDefaultLocale();
            if (defaultLocale != null) {
                final Locale locale = StringUtils.parseLocaleString(defaultLocale);
                return () -> locale;
            }
        }
        return fallbackResolver.resolveLocaleContext(request);
    }

    @Override
    public void setLocaleContext(HttpServletRequest request, HttpServletResponse response,
            LocaleContext localeContext) {
        if (localeContext != null) {
            final Member realMember = authenticationService.getRealMemberOrNull();

            final String localeString = localeContext.getLocale().toString();
            if (realMember != null && !localeString
                    .equalsIgnoreCase(realMember.getDefaultLocale())) {
                realMember.setDefaultLocale(localeString);
                MembersClient.updateMember(realMember);
            }
        }
        fallbackResolver.setLocaleContext(request, response, localeContext);
        // Propagate the update to the LocaleContextHolder, as it was initialized earlier
        LocaleContextHolder.setLocaleContext(localeContext);
    }
}
