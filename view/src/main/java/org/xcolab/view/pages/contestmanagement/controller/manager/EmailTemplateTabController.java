package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.IEmailTemplateClient;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.LabelStringValue;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.wrappers.EmailTemplateWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class EmailTemplateTabController extends AbstractTabController {

    static final private ContestManagerTabs tab = ContestManagerTabs.EMAIL_TEMPLATES;
    static final private String TAB_VIEW = "contestmanagement/manager/emailTab";

    @Autowired
    private IEmailTemplateClient emailTemplateClient;

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @GetMapping("tab/EMAIL_TEMPLATES")
    public String showEmailTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @RequestParam(required = false) String elementId) {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        String templateType = elementId != null ? elementId : getFirstTemplateName();
        model.addAttribute("templateType", templateType);
        if (!StringUtils.isBlank(templateType)) {
            model.addAttribute("emailTemplateWrapper",
                    new EmailTemplateWrapper(templateType));
        }
        final List<IEmailTemplate> emailTemplates = emailTemplateClient.listEmailTemplates();
        List<LabelStringValue> templateSelectionItems = new ArrayList<>();
        for (IEmailTemplate emailTemplate : emailTemplates) {
            templateSelectionItems.add(new LabelStringValue(emailTemplate.getName(),
                    emailTemplate.getName()));
        }
        model.addAttribute("templateSelectionItems", templateSelectionItems);
        return TAB_VIEW;
    }

    private String getFirstTemplateName() {
        final List<IEmailTemplate> emailTemplates = emailTemplateClient.listEmailTemplates();
        if (!emailTemplates.isEmpty()) {
            return emailTemplates.get(0).getName();
        }
        return "";
    }

    @PostMapping("tab/EMAIL_TEMPLATES/update")
    public String updateEmailTemplateTabController(HttpServletRequest request, Model model,
            UserWrapper member, @ModelAttribute EmailTemplateWrapper updateEmailTemplateWrapper,
            BindingResult result, HttpServletResponse response) {
        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (result.hasErrors()) {
            AlertMessage.danger("An error occurred").flash(request);
            return "redirect:" + tab.getTabUrl(updateEmailTemplateWrapper.getType());
        }

        updateEmailTemplateWrapper.persist();
        AlertMessage.CHANGES_SAVED.flash(request);
        return "redirect:" + tab.getTabUrl(updateEmailTemplateWrapper.getType());
    }
}
