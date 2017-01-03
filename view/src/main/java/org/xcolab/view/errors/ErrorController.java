package org.xcolab.view.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    public static final String ERROR_VIEW = "error";

    @GetMapping("/error")
    public String error() {
        return ERROR_VIEW;
    }
}
