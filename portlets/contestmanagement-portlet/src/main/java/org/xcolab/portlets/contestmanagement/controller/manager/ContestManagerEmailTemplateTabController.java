package org.xcolab.portlets.contestmanagement.controller.manager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.entities.LabelStringValue;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.EmailTemplateWrapper;
import org.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestManagerEmailTemplateTabController extends ContestManagerBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestManagerEmailTemplateTabController.class);
    static final private TabEnum tab = ContestManagerTabs.EMAIL_TEMPLATES;
    static final private String TAB_VIEW = "manager/emailTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=EMAIL_TEMPLATES")
    public String showEmailTabController(PortletRequest request, PortletResponse response, Model model,
            @RequestParam(required = false) String elementId) {
        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        String templateType = elementId != null ? elementId : getFirstTemplateName();
        model.addAttribute("templateType", templateType);
        if (!StringUtils.isBlank(templateType)) {
            model.addAttribute("emailTemplateWrapper",
                    new EmailTemplateWrapper(templateType));
        }
        final List<ContestEmailTemplate> emailTemplates = EmailTemplateClientUtil
                .listAllContestEmailTemplates();
        List <LabelStringValue> templateSelectionItems = new ArrayList<>();
        for (ContestEmailTemplate emailTemplate : emailTemplates) {
            templateSelectionItems.add(new LabelStringValue(emailTemplate.getType_(),
                    emailTemplate.getType_()));
        }
        model.addAttribute("templateSelectionItems", templateSelectionItems);
        setPageAttributes(request, model, tab);
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateEmailTemplate")
    public void updateEmailTemplateTabController(ActionRequest request, Model model,
            @ModelAttribute EmailTemplateWrapper updateEmailTemplateWrapper,
            BindingResult result, ActionResponse response) {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateEmailTemplate");
            return;
        }

        try {
            updateEmailTemplateWrapper.persist();
            SetRenderParameterUtil.addActionSuccessMessageToSession(request);
            response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tab.getName()
                    + "/elementId/" + updateEmailTemplateWrapper.getType());
        } catch (IOException e) {
            _log.warn("Update email template failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateEmailTemplate", "error=true"})
    public String reportError(PortletRequest request, Model model) {
        return TAB_VIEW;
    }

    //TODO
//    @RequestMapping(params = "action=createEmailTemplate")
//    public void createEmailTemplateTabController(ActionRequest request, Model model, ActionResponse response) {
//
//        if (!tabWrapper.getCanEdit()) {
//            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
//            return;
//        }
//
//        try {
//            ContestSchedule newContestSchedule = ContestCreatorUtil.createNewSchedule();
//            SetRenderParameterUtil
//                    .setSuccessRenderRedirectManagerTab(response, tab.getName(), newContestSchedule.getId());
//
//        } catch (SystemException | IOException e) {
//            _log.warn("Create contest schedule failed with: ", e);
//            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
//        }
//    }
//
//    @RequestMapping(params = "action=deleteEmailTemplate")
//    public void deleteEmailTemplateTabController(ActionRequest request, Model model,
//            @RequestParam Long templateId,
//            ActionResponse response) {
//        if (!tabWrapper.getCanEdit()) {
//            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
//            return;
//        }
//        try {
//            ContestScheduleWrapper.deleteContestSchedule(scheduleId);
//            SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(), getFirstTemplateName());
//        } catch (SystemException | PortalException | IOException e) {
//            _log.warn("Delete contest schedule failed with: ", e);
//            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
//        }
//    }

    private String getFirstTemplateName() {
        final List<ContestEmailTemplate> emailTemplates =
                EmailTemplateClientUtil.listAllContestEmailTemplates();
        if (!emailTemplates.isEmpty()) {
            return emailTemplates.get(0).getType_();
        }
        return "";
    }
}