package org.xcolab.view.config.spring.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.auth.AuthenticationContext;

public class MemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthenticationContext authenticationContext;

    public MemberArgumentResolver(AuthenticationContext authenticationContext) {
        Assert.notNull(authenticationContext, "AuthenticationContext required");
        this.authenticationContext = authenticationContext;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(UserWrapper.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {

        if (this.supportsParameter(methodParameter)) {
            RealMember realMemberAnnotation =
                    methodParameter.getParameterAnnotation(RealMember.class);
            if (realMemberAnnotation != null) {
                return authenticationContext.getRealMemberOrNull();
            }
            return authenticationContext.getMemberOrNull();
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }
}
