package org.xcolab.view.theme;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PopulateLayoutVariablesFilter extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {

        if (modelAndView != null && !isRedirectView(modelAndView)) {
            final Object attribute =
                    request.getAttribute(ThemeVariableFilter.REQUEST_ATTRIBUTE_NAME);

            if (attribute == null) {
                throw new IllegalStateException("ThemeContext variable was not populated in request: "
                                + request.getRequestURI());
            }
            ThemeContext themeContext = (ThemeContext) attribute;
            modelAndView.addObject("_themeContext", themeContext);
        }
    }

    private boolean isRedirectView(ModelAndView modelAndView) {
        return (modelAndView.getView() != null && modelAndView.getView() instanceof RedirectView)
                || modelAndView.getViewName().startsWith("redirect:");
    }
}
