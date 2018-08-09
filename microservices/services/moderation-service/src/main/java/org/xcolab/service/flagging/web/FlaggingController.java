package org.xcolab.service.flagging.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import org.xcolab.service.flagging.wrappers.AggregatedReport;
import org.xcolab.service.utils.ControllerUtils;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.flagging.ManagerAction;
import org.xcolab.util.enums.flagging.TargetType;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
public class FlaggingController {

    private final ReportDao reportDao;

    private final ReportTargetDao reportTargetDao;

    private final FlaggingService flaggingService;

    @Autowired
    public FlaggingController(ReportDao reportDao, ReportTargetDao reportTargetDao,
            FlaggingService flaggingService) {
        this.reportDao = reportDao;
        this.reportTargetDao = reportTargetDao;
        this.flaggingService = flaggingService;
    }

    @RequestMapping(value = "/reports", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Report> getReports(HttpServletResponse response,
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long reporteruserId,
            @RequestParam(required = false) Long manageruserId,
            @RequestParam(required = false) TargetType targetType,
            @RequestParam(required = false) Long targetId,
            @RequestParam(required = false) Long targetAdditionalId,
            @RequestParam(required = false) String managerAction) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                Integer.toString(reportDao.countByGiven(reporteruserId, manageruserId,
                        targetType != null ?targetType.name() : null,
                        targetId, targetAdditionalId, managerAction)));
        return reportDao.findByGiven(paginationHelper, reporteruserId,
                manageruserId, targetType != null ?targetType.name() : null,
                targetId, targetAdditionalId, managerAction);
    }

    @RequestMapping(value = "/aggregatedReports", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<AggregatedReport> getAggregatedReports(HttpServletResponse response,
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long reporteruserId,
            @RequestParam(required = false) Long manageruserId,
            @RequestParam(required = false) String targetType,
            @RequestParam(required = false) Long targetId,
            @RequestParam(required = false) Long targetAdditionalId,
            @RequestParam(required = false) String managerAction) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                Integer.toString(reportDao.countByGiven(reporteruserId, manageruserId,
                        targetType, targetId, targetAdditionalId, managerAction)));
        return reportDao.findAggregatedByGiven(paginationHelper, reporteruserId,
                manageruserId, targetType, targetId, targetAdditionalId, managerAction);
    }

    @GetMapping("/reports/{reportId}")
    public Report getReport(@PathVariable long reportId) throws NotFoundException {
        final Report report = reportDao.get(reportId);
        if (report == null) {
            throw new NotFoundException();
        }
        return report;
    }

    @PostMapping("/reports")
    public Report createReport(@RequestBody Report report){
        return flaggingService.createReport(report);
    }

    @PutMapping("/reports/{reportId}")
    public boolean updateReport(@PathVariable long reportId, @RequestBody Report report)
            throws NotFoundException  {
        if (reportDao.get(reportId) == null) {
            throw new NotFoundException();
        }
        report.setId(reportId);
        return reportDao.update(report);
    }

    @RequestMapping(value = "/reportTargets", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ReportTarget> getReportTargets(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) TargetType type,
            @RequestParam(required = false) String sort) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        return reportTargetDao.findByGiven(paginationHelper, type);
    }

    @GetMapping("/reportTargets/{reportTargetId}")
    public ReportTarget getReportTarget(@PathVariable long reportTargetId)
            throws NotFoundException {
        final ReportTarget reportTarget = reportTargetDao.get(reportTargetId);
        if (reportTarget == null) {
            throw new NotFoundException();
        }
        return reportTarget;
    }

    @PostMapping("/reportTargets")
    public ReportTarget createReportTarget(@RequestBody ReportTarget reportTarget){
        return reportTargetDao.create(reportTarget);
    }

    @PutMapping("/reportTargets/{reportTargetId}")
    public boolean updateReportTarget(@PathVariable long reportTargetId,
            @RequestBody ReportTarget reportTarget) throws NotFoundException {
        if (reportTargetDao.get(reportTargetId) == null) {
            throw new NotFoundException();
        }
        reportTarget.setId(reportTargetId);
        return reportTargetDao.update(reportTarget);
    }


    @PostMapping("/reports/{reportId}/handle")
    public boolean handleReport(@PathVariable long reportId, @RequestParam long manageruserId,
            @RequestParam ManagerAction managerAction) {
        return flaggingService.handleReport(reportId, manageruserId, managerAction);
    }

    @DeleteMapping("/reportTargets/{reportTargetId}")
    public boolean deleteReportTarget(@PathVariable long reportTargetId) {
        return reportTargetDao.delete(reportTargetId);
    }
}
