package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.moderation.IModerationClient;
import org.xcolab.client.moderation.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.moderation.pojo.IAggregatedReport;
import org.xcolab.client.moderation.pojo.IReportTarget;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.LabelValue;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.util.enums.moderation.ManagerAction;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.wrappers.ModerationReportTargetWrapper;
import org.xcolab.view.pages.contestmanagement.wrappers.ModerationReportWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class FlaggingTabController extends AbstractTabController {

    private static final Logger _log =
            LoggerFactory.getLogger(FlaggingTabController.class);
    static final private ContestManagerTabs tab = ContestManagerTabs.MODERATION;
    static final private String TAB_VIEW = "contestmanagement/manager/flaggingTab";

    @Autowired
    private IModerationClient moderationClient;

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @GetMapping("tab/MODERATION")
    public String showFlaggingTab(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @RequestParam(required = false) Long elementId)
            throws ReportTargetNotFoundException {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        long reportTargetId = elementId != null ? elementId : getFirstReportTargetId();
        model.addAttribute("reportTargetId", reportTargetId);
        if (reportTargetId > 0) {
            model.addAttribute("reportTargetWrapper",
                    new ModerationReportTargetWrapper(reportTargetId));
        } else {
            model.addAttribute("reportTargetWrapper", new ModerationReportTargetWrapper());
        }
        final List<IReportTarget> reportTargets =
                moderationClient.listReportTargets(0, Integer.MAX_VALUE);
        List<LabelValue> selectionItems = new ArrayList<>();
        for (IReportTarget reportTarget : reportTargets) {
            selectionItems.add(new LabelValue(reportTarget.getId(),
                    reportTarget.getType() + " - " + reportTarget.getReason()));
        }
        model.addAttribute("selectionItems", selectionItems);

        final List<IAggregatedReport> reports =
                moderationClient.listAggregatedReports(0, Integer.MAX_VALUE);
        final List<ModerationReportWrapper> reportWrappers = new ArrayList<>();
        for (IAggregatedReport report : reports) {
            reportWrappers.add(new ModerationReportWrapper(report));
        }
        model.addAttribute("reports", reportWrappers);

        return TAB_VIEW;
    }

    private long getFirstReportTargetId() {
        final List<IReportTarget> reportTargets =
                moderationClient.listReportTargets(0, 1);
        if (!reportTargets.isEmpty()) {
            return reportTargets.get(0).getId();
        }
        return 0L;
    }

    @PostMapping("tab/MODERATION/handle/{reportId}/{managerAction}")
    public String approveContent(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member,
            @PathVariable long reportId, @PathVariable ManagerAction managerAction)
            throws IOException {
        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        long userId = MemberAuthUtil.getUserId();
        moderationClient.handleReport(userId, managerAction, reportId);
        AlertMessage.success("Report " + managerAction.name() + "D").flash(request);
        return "redirect:" + tab.getTabUrl();
    }

    @PostMapping("tab/MODERATION/update")
    public String updateEmailTemplateTabController(HttpServletRequest request, Model model,
            UserWrapper member, @ModelAttribute ModerationReportTargetWrapper reportTargetWrapper,
            BindingResult result, HttpServletResponse response) throws ReportTargetNotFoundException {
        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (result.hasErrors()) {
            AlertMessage.danger("An error occurred").flash(request);
            return "redirect:" + tab.getTabUrl(reportTargetWrapper.getReportTargetId());
        }

        reportTargetWrapper.persist();
        AlertMessage.CHANGES_SAVED.flash(request);
        return "redirect:" + tab.getTabUrl(reportTargetWrapper.getReportTargetId());
    }

    @PostMapping("tab/MODERATION/delete/{reportTargetId}")
    public String deleteEmailTemplateTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member,
            @PathVariable long reportTargetId) throws IOException, ReportTargetNotFoundException {
        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        final boolean success = moderationClient.deleteReportTarget(reportTargetId);
        if (success) {
            return "redirect:" + tab.getTabUrl();
        } else {
            AlertMessage.ERROR.flash(request);
            return "redirect:" + tab.getTabUrl(reportTargetId);
        }
    }
}
