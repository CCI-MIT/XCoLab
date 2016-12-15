package org.xcolab.portlets.contestmanagement.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.flagging.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.flagging.pojo.AggregatedReport;
import org.xcolab.client.flagging.pojo.ReportTarget;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.FlaggingReportTargetWrapper;
import org.xcolab.portlets.contestmanagement.wrappers.FlaggingReportWrapper;
import org.xcolab.util.enums.flagging.ManagerAction;
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
public class ContestManagerFlaggingTabController extends ContestManagerBaseTabController {

    private final static Logger _log = LoggerFactory.getLogger(ContestManagerFlaggingTabController.class);
    static final private TabEnum tab = ContestManagerTabs.FLAGGING;
    static final private String TAB_VIEW = "manager/flaggingTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=FLAGGING")
    public String showEmailTabController(PortletRequest request, PortletResponse response, Model model,
            @RequestParam(required = false) Long elementId) {
        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
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
            List <LabelValue> selectionItems = new ArrayList<>();
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

    @RequestMapping(params = "action=handleReport")
    public void approveContent(ActionRequest request, ActionResponse response, Model model,
            @RequestParam long reportId, @RequestParam ManagerAction managerAction)
            throws IOException {
        if (!tabWrapper.getCanEdit()) {
            return;
        }
        long memberId = MemberAuthUtil.getMemberId(request);
        FlaggingClient.handleReport(memberId, managerAction, reportId);
        SetRenderParameterUtil.addActionSuccessMessageToSession(request, "Report " + managerAction.name() + "D");
        response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tab.getName());
    }

    @RequestMapping(params = "action=updateReportTarget")
    public void updateEmailTemplateTabController(ActionRequest request, Model model,
            @ModelAttribute FlaggingReportTargetWrapper reportTargetWrapper,
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
            reportTargetWrapper.persist();
            SetRenderParameterUtil.addActionSuccessMessageToSession(request);
            response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tab.getName()
                    + "/elementId/" + reportTargetWrapper.getReportTargetId());
        } catch (IOException e) {
            _log.warn("Update email template failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateReportTarget", "error=true"})
    public String reportError(PortletRequest request, Model model) {
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=deleteReportTarget")
    public void deleteEmailTemplateTabController(ActionRequest request, ActionResponse response,
            Model model, @RequestParam long reportTargetId) {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        try {
            final boolean success = FlaggingClient.deleteReportTarget(reportTargetId);
            if (success) {
                SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(), getFirstReportTargetId());
            } else {
                SetRenderParameterUtil.setErrorRenderParameter(response, "deleteReportTarget");
            }
        } catch (IOException e) {
            _log.warn("Delete contest schedule failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    private long getFirstReportTargetId() {
        final List<ReportTarget> reportTargets =
                FlaggingClient.listReportTargets(0, 1);
        if (!reportTargets.isEmpty()) {
            return reportTargets.get(0).getReportTargetId();
        }
        return 0L;
    }
}