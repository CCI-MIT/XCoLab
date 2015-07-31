package org.xcolab.portlets.contestmanagement.controller.details;

import com.ext.portlet.model.ContestWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.wrapper.TabWrapper;

import javax.mail.internet.InternetAddress;
import javax.portlet.*;

@Controller
@RequestMapping("view")
public class ContestDetailsAdminTabController extends ContestDetailsBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestDetailsAdminTabController.class);
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

        setPageAttributes(request, model, tab);
        model.addAttribute("contestAdminBean", new ContestWrapper(getContest()));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestAdmin")
    public void updateTeamTabController(ActionRequest request, Model model,
                                        @ModelAttribute ContestWrapper updateContestAdminBean,
                                        ActionResponse response) {

        if(!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            updateContestAdminBean.persist();
            SetRenderParameterUtil.setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch(Exception e){
            _log.warn("Update contest admin failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @ResourceMapping(value="submitContest")
    public @ResponseBody
    void handleSubmitContest(
            ResourceRequest request,
            @RequestParam("contestId") Long contestId,
            @RequestParam("tab") String tab,
            ResourceResponse response
    ) throws Exception{

        boolean success = true;
        try {
            String contestUrl = "http://www.climatecolab.org/web/guest/cms/-/contestmanagement/contestId/" + contestId;
            if(!tab.isEmpty()){
                contestUrl += "/tab/" + tab;
            }
            contestUrl += "<br/>";

            User user = UserLocalServiceUtil.getUser(Long.parseLong(request.getRemoteUser()));
            String subject  = "Contest draft was submitted from the new contest creation tool!";
            String body = "The following contest: <br />" +
                    contestUrl +
                    "was submitted by the user: " + user.getFullName() + "<br/>";

            InternetAddress fromEmail = new InternetAddress("no-reply@climatecolab.org", "MIT Climate CoLab");

            String emailRecipients = "pdeboer@mit.edu,lfi@mit.edu";
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