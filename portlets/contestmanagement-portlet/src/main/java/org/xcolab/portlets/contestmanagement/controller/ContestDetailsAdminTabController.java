package org.xcolab.portlets.contestmanagement.controller;

import com.ext.portlet.model.ContestWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngine;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.wrapper.TabWrapper;

import javax.mail.internet.InternetAddress;
import javax.portlet.*;

@Controller
@RequestMapping("view")
public class ContestDetailsAdminTabController extends ContestDetailsBaseTabController {

    static final private TabEnum tab = ContestDetailsTabs.ADMIN;
    static final private String TAB_VIEW = "details/adminTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=ADMIN")
    public String showAdminTabController(PortletRequest request, PortletResponse response, Model model,
                                        @RequestParam(required = false) Long contestId)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        setPageAttributes(request, model, ContestDetailsTabs.TEAM);
        model.addAttribute("contestAdminBean", new ContestWrapper(getContest()));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestAdmin")
    public void updateTeamTabController(ActionRequest request, Model model,
                                        @ModelAttribute ContestWrapper updateContestAdminBean,
                                        ActionResponse response) {

        if(!tabWrapper.getCanEdit()) {
            setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            updateContestAdminBean.persist();
            setSuccessRenderRedirect(response, tab.getName());
        } catch(Exception e){
            setNotFoundErrorRenderParameter(response);
        }
    }

    @ResourceMapping(value="submitContest")
    public @ResponseBody
    void handleSubmitContest(
            ResourceRequest request,
            @RequestParam("contestId") Long contestId,
            ResourceResponse response
    ) throws Exception{

        boolean success = true;
        try {
            User user = UserLocalServiceUtil.getUser(Long.parseLong(request.getRemoteUser()));
            String subject  = "Contest draft was submitted from the new contest creation tool!";
            String body = "Hi Laur,<br />" +
                    "good to see you again.<br/>" +
                    " The following contest: <br />" +
                    "http://www.climatecolab.org/web/guest/cms/-/contestmanagement/contestId/" + contestId + "<br/>"+
                    "was submitted by the user: " + user.getFullName() + "<br/>" +
                    "Enjoy reviewing it! <br/>" +
                    "Warmest, <br/>" +
                    "your production server";

            InternetAddress fromEmail = new InternetAddress("no-reply@climatecolab.org", "MIT Climate CoLab");

            String emailRecipients = "pdeboer@mit.edu,knauert@mit.edu,lfi@mit.edu";
            String[] recipients = emailRecipients.split(",");

            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }

            MailEngine.send(fromEmail, addressTo, subject, body, true);
        } catch (Exception e) {
            success = false;
        }
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(success));
    }



    @RequestMapping(params = {"action=updateContestAdmin", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

    @RequestMapping
    public String updateTeamTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {
        return NOT_FOUND_TAB_VIEW;
    }

    @RequestMapping(params = {"action=showNoPermission", "error=true"})
    public String showNoPermissionErrorRenderParameter(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {
        return NO_PERMISSION_TAB_VIEW;
    }

    @RequestMapping(params = {"action=showNotFound", "error=true"})
    public String showNotFoundErrorRenderParameter(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {
        return NOT_FOUND_TAB_VIEW;
    }

}