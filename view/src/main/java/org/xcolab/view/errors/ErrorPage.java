package org.xcolab.view.errors;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

public class ErrorPage extends ModelAndView {

    public ErrorPage(String view, HttpStatus status) {
        super(view);
        setStatus(status);
    }

    public String toViewName(Model model, HttpServletResponse response) {
        model.addAllAttributes(getModel());
        return toViewName(response);
    }

    public String toViewName(HttpServletResponse response) {
        response.setStatus(getStatus().value());
        return getViewName();
    }
}
