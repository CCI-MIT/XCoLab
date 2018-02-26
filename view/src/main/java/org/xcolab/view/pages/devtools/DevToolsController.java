package org.xcolab.view.pages.devtools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/devtools")
public class DevToolsController {

    /**
     * This endpoint shows a blank page containing only the basic layout scaffolding.
     *
     * It is used as a baseline for monitoring.
     */
    @RequestMapping("blank")
    public String blank(HttpServletRequest request, HttpServletResponse response) {
        return "devtools/blank";
    }

    /**
     * This endpoint always throws an exception.
     *
     * It is used to test error handling and reporting.
     *
     * @throws TestException An exception without a cause
     */
    @GetMapping("forceException")
    public void forceException() throws TestException {
        throw new TestException();
    }

    private static class TestException extends Exception {
        public TestException() {
            super("This is just a test exception for debugging - no action necessary");
        }
    }
}
