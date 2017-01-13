package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.flagging.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.flagging.pojo.AggregatedReport;
import org.xcolab.client.flagging.pojo.ReportTarget;
import org.xcolab.util.enums.flagging.ManagerAction;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.entities.LabelValue;
import org.xcolab.view.pages.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.FlaggingReportTargetWrapper;
import org.xcolab.view.pages.contestmanagement.wrappers.FlaggingReportWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class ContestManagerFlaggingTabController extends ContestManagerBaseTabController {

    private final static Logger _log =
            LoggerFactory.getLogger(ContestManagerFlaggingTabController.class);
    static final private ContestManagerTabs tab = ContestManagerTabs.FLAGGING;
    static final private String TAB_VIEW = "contestmanagement/manager/flaggingTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @GetMapping("tab/FLAGGING")
    public String showFlaggingTab(HttpServletRequest request, HttpServletResponse response,
            Model model,
            @RequestParam(required = false) Long elementId) {
        if (!tabWrapper.getCanView()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        try {
            long reportTargetId = elementId != null ? elementId : getFirstReportTargetId();
            model.addAttribute("reportTargetId", reportTargetId);
            if (reportTargetId > 0) {
                model.addAttribute("reportTargetWrapper",
                        new FlaggingReportTargetWrapper(reportTargetId));
            } else {
                model.addAttribute("reportTargetWrapper", new FlaggingReportTargetWrapper());
            }
            final List<ReportTarget> reportTargets =
                    FlaggingClient.listReportTargets(0, Integer.MAX_VALUE);
            List<LabelValue> selectionItems = new ArrayList<>();
            for (ReportTarget reportTarget : reportTargets) {
                selectionItems.add(new LabelValue(reportTarget.getReportTargetId(),
                        reportTarget.getType() + " - " + reportTarget.getReason()));
            }
            model.addAttribute("selectionItems", selectionItems);

            final List<AggregatedReport> reports =
                    FlaggingClient.listAggregatedReports(0, Integer.MAX_VALUE);
            final List<FlaggingReportWrapper> reportWrappers = new ArrayList<>();
            for (AggregatedReport report : reports) {
                reportWrappers.add(new FlaggingReportWrapper(report));
            }
            model.addAttribute("reports", reportWrappers);

            setPageAttributes(request, model, tab);
            return TAB_VIEW;
        } catch (ReportTargetNotFoundException e) {
            _log.warn("Exception while rendering CMS flagging tab", e);
            SetRenderParameterUtil.addActionExceptionMessageToSession(request, e);
        }
        return NOT_FOUND_TAB_VIEW;
    }

    private long getFirstReportTargetId() {
        final List<ReportTarget> reportTargets =
                FlaggingClient.listReportTargets(0, 1);
        if (!reportTargets.isEmpty()) {
            return reportTargets.get(0).getReportTargetId();
        }
        return 0L;
    }

    @PostMapping("tab/FLAGGING/handle/{reportId}/{managerAction}")
    public String approveContent(HttpServletRequest request, HttpServletResponse response,
            Model model,
            @PathVariable long reportId, @PathVariable ManagerAction managerAction)
            throws IOException {
        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
        long memberId = MemberAuthUtil.getMemberId(request);
        FlaggingClient.handleReport(memberId, managerAction, reportId);
        SetRenderParameterUtil
                .addActionSuccessMessageToSession(request, "Report " + managerAction.name() + "D");
        return "redirect:" + tab.getTabUrl();
    }

    @PostMapping("tab/FLAGGING/update")
    public String updateEmailTemplateTabController(HttpServletRequest request, Model model,
            @ModelAttribute FlaggingReportTargetWrapper reportTargetWrapper,
            BindingResult result, HttpServletResponse response) {
        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        if (result.hasErrors()) {
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateEmailTemplate");
            //TODO: errors
            return TAB_VIEW;
        }

        reportTargetWrapper.persist();
        SetRenderParameterUtil.addActionSuccessMessageToSession(request);
        return "redirect:" + tab.getTabUrl(reportTargetWrapper.getReportTargetId());
    }

    @PostMapping("tab/FLAGGING/delete/{reportTargetId}")
    public String deleteEmailTemplateTabController(HttpServletRequest request,
            HttpServletResponse response,
            Model model, @PathVariable long reportTargetId) throws IOException {
        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
        final boolean success = FlaggingClient.deleteReportTarget(reportTargetId);
        if (success) {
            return "redirect:" + tab.getTabUrl();
        } else {
            SetRenderParameterUtil.setErrorRenderParameter(response, "deleteReportTarget");
            //TODO: error
            return TAB_VIEW;
        }
    }
}