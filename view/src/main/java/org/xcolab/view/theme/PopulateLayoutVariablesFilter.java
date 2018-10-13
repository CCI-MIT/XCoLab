package org.xcolab.view.theme;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PopulateLayoutVariablesFilter extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {

        final Object attribute =
                request.getAttribute(ThemeVariableFilter.REQUEST_ATTRIBUTE_NAME);

        if (attribute != null) {
            ThemeContext themeContext = (ThemeContext) attribute;
            modelAndView.addObject("_themeContext", themeContext);
        }
    }
}
