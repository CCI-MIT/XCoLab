package org.xcolab.service.flagging.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Report;
import org.xcolab.model.tables.pojos.ReportTarget;
import org.xcolab.service.flagging.domain.report.ReportDao;
import org.xcolab.service.flagging.domain.reportTarget.ReportTargetDao;
import org.xcolab.service.flagging.exceptions.NotFoundException;
import org.xcolab.service.flagging.service.FlaggingService;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.flagging.ManagerAction;

import java.util.List;

@RestController
public class FlaggingController {

    @Autowired
    private ReportDao reportDao;

    @Autowired
    private ReportTargetDao reportTargetDao;

    @Autowired
    private FlaggingService flaggingService;

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public List<Report> getReports(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long reporterMemberId,
            @RequestParam(required = false) Long managerMemberId,
            @RequestParam(required = false) String targetType,
            @RequestParam(required = false) Long targetId,
            @RequestParam(required = false) String managerAction) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        return reportDao.findByGiven(paginationHelper, reporterMemberId,
                managerMemberId, targetType, targetId, managerAction);
    }

    @RequestMapping(value = "/reports/{reportId}", method = RequestMethod.GET)
    public Report getReport(@PathVariable long reportId) throws NotFoundException {
        final Report report = reportDao.get(reportId);
        if (report == null) {
            throw new NotFoundException();
        }
        return report;
    }

    @RequestMapping(value = "/reports", method = RequestMethod.POST)
    public Report createReport(@RequestBody Report report){
        return flaggingService.createReport(report);
    }

    @RequestMapping(value = "/reports/{reportId}", method = RequestMethod.PUT)
    public boolean updateReport(@PathVariable long reportId, @RequestBody Report report)
            throws NotFoundException  {
        if (reportDao.get(reportId) == null) {
            throw new NotFoundException();
        }
        report.setReportId(reportId);
        return reportDao.update(report);
    }

    @RequestMapping(value = "/reportTargets", method = RequestMethod.GET)
    public List<ReportTarget> getReportTargets(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        return reportTargetDao.findByGiven(paginationHelper);
    }

    @RequestMapping(value = "/reportTargets/{type}/{reason}", method = RequestMethod.GET)
    public ReportTarget getReportTarget(@PathVariable String type, @PathVariable String reason)
            throws NotFoundException {
        final ReportTarget reportTarget = reportTargetDao.get(type, reason);
        if (reportTarget == null) {
            throw new NotFoundException();
        }
        return reportTarget;
    }

    @RequestMapping(value = "/reportTargets", method = RequestMethod.POST)
    public ReportTarget createReportTarget(@RequestBody ReportTarget reportTarget){
        return reportTargetDao.create(reportTarget);
    }

    @RequestMapping(value = "/reportTargets/{type}/{reason}", method = RequestMethod.PUT)
    public boolean updateReportTarget(@PathVariable String type, @PathVariable String reason,
            @RequestBody ReportTarget reportTarget) throws NotFoundException  {
        if (reportTargetDao.get(type, reason) == null) {
            throw new NotFoundException();
        }
        reportTarget.setType(type);
        reportTarget.setReason(reason);
        return reportTargetDao.update(reportTarget);
    }


    @RequestMapping(value = "/reports/{reportId}/handle", method = RequestMethod.POST)
    public boolean handleReport(@PathVariable long reportId, @RequestParam long managerMemberId,
            @RequestParam ManagerAction managerAction) {
        return flaggingService.handleReport(reportId, managerMemberId, managerAction);
    }
}
