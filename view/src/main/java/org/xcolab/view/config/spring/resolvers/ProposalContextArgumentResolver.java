package org.xcolab.view.config.spring.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.LocaleResolver;

import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalContextImpl;

import javax.servlet.http.HttpServletRequest;

public class ProposalContextArgumentResolver implements HandlerMethodArgumentResolver {

    private final LocaleResolver localeResolver;

    public ProposalContextArgumentResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(ProposalContext.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest,
            WebDataBinderFactory webDataBinderFactory) {
        if (this.supportsParameter(methodParameter)) {
            final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
            return new ProposalContextImpl(request, localeResolver);
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }
}
