package org.xcolab.view.auth;

import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import org.xcolab.client.members.pojo.Member;

public class MemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthenticationContext authenticationContext;

    public MemberArgumentResolver(AuthenticationContext authenticationContext) {
        Assert.notNull(authenticationContext, "AuthenticationContext required");
        this.authenticationContext = authenticationContext;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Member.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {

        if (this.supportsParameter(methodParameter)) {
            return authenticationContext.getMemberOrNull();
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }
}
