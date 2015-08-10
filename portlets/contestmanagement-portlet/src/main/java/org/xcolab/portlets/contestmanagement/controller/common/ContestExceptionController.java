package org.xcolab.portlets.contestmanagement.controller.common;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.PortletRequest;

/**
 * Created by Thomas on 8/4/2015.
 */

@Controller
@RequestMapping("view")
public class ContestExceptionController {

    private final static Log _log = LogFactoryUtil.getLog(ContestExceptionController.class);
    static final private String EXCEPTION_VIEW = "common/showException";

    @RequestMapping(params = {"action=showException", "error=true"})
    public String showException(PortletRequest request, Model model) throws PortalException, SystemException {
        String exceptionMessage = request.getParameter("exceptionMessage");
        String exceptionStacktrace = request.getParameter("exceptionStacktrace");
        model.addAttribute("exceptionMessage", exceptionMessage);
        model.addAttribute("exceptionStacktrace", exceptionStacktrace);
        return EXCEPTION_VIEW;
    }

}