package org.xcolab.service.flagging.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.flagging.IFlaggingClient;
import org.xcolab.client.flagging.exceptions.ReportNotFoundException;
import org.xcolab.client.flagging.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.flagging.pojo.IReport;
import org.xcolab.client.flagging.pojo.IReportTarget;
import org.xcolab.client.flagging.pojo.AggregatedReport;
import org.xcolab.service.flagging.domain.report.ReportDao;
import org.xcolab.service.flagging.domain.reportTarget.ReportTargetDao;
import org.xcolab.service.flagging.service.FlaggingService;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.flagging.ManagerAction;
import org.xcolab.util.enums.flagging.TargetType;


import java.util.List;


@RestController
public class FlaggingController implements IFlaggingClient {

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

    @GetMapping(value = "/reports")
    public List<IReport> listReports(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer lastRecord,
            @RequestParam(required = false) Long reporterUserId,
            @RequestParam(required = false) TargetType targetType,
            @RequestParam(required = false) Long targetId,
            @RequestParam(required = false) Long targetAdditionalId,
            @RequestParam(required = false) Long managerUserId) {
        Integer limitRecord = lastRecord - startRecord;
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, "");
        /*
        response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                Integer.toString(reportDao.countByGiven(reporteruserId, manageruserId,
                        targetType != null ?targetType.name() : null,
                        targetId, targetAdditionalId, managerAction)));
        */
        return reportDao.findByGiven(paginationHelper, reporterUserId,
                managerUserId, targetType != null ? targetType.name() : null,
                targetId, targetAdditionalId, null);
    }

    @GetMapping("/reports/{reportId}")
    public IReport getReport(@PathVariable Long reportId) throws ReportNotFoundException {
        final IReport report = reportDao.get(reportId);
        if (report == null) {
            throw new ReportNotFoundException(reportId);
        }
        return report;
    }

    @PostMapping("/reports")
    public IReport createReport(@RequestBody IReport report) {
        return flaggingService.createReport(report);
    }

    @PutMapping("/reports")
    public boolean updateReport(@RequestBody IReport report) throws ReportNotFoundException {
        if (reportDao.get(report.getId()) == null) {
            throw new ReportNotFoundException(report.getId());
        }
        report.setId(report.getId());
        return reportDao.update(report);
    }

    @GetMapping(value = "/aggregatedReports")
    public List<AggregatedReport> listAggregatedReports(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer lastRecord) {
        Integer limitRecord = lastRecord - startRecord;
        PaginationHelper paginationHelper =
                new PaginationHelper(startRecord, limitRecord, "firstReportDate");
        /*
        response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                Integer.toString(reportDao.countByGiven(reporteruserId, manageruserId,
                        targetType, targetId, targetAdditionalId, managerAction)));
        */
        return reportDao.findAggregatedByGiven(paginationHelper, null,
                null, null, null, null, "PENDING");
    }

    @GetMapping(value = "/reportTargets")
    public List<IReportTarget> listReportTargets(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer lastRecord,
            @RequestParam(required = false) TargetType type) {
        Integer limitRecord = lastRecord - startRecord;
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, "");
        return reportTargetDao.findByGiven(paginationHelper, type);
    }

    @GetMapping("/reportTargets/{reportTargetId}")
    public IReportTarget getReportTarget(@PathVariable Long reportTargetId)
            throws ReportTargetNotFoundException {
        final IReportTarget reportTarget = reportTargetDao.get(reportTargetId);
        if (reportTarget == null) {
            throw new ReportTargetNotFoundException(reportTargetId);
        }
        return reportTarget;
    }

    @PostMapping("/reportTargets")
    public IReportTarget createReportTarget(@RequestBody IReportTarget reportTarget) {
        return reportTargetDao.create(reportTarget);
    }

    @PutMapping("/reportTargets")
    public boolean updateReportTarget(@RequestBody IReportTarget reportTarget)
            throws ReportTargetNotFoundException {
        if (reportTargetDao.get(reportTarget.getId()) == null) {
            throw new ReportTargetNotFoundException(reportTarget.getId());
        }
        reportTarget.setId(reportTarget.getId());
        return reportTargetDao.update(reportTarget);
    }

    @DeleteMapping("/reportTargets/{reportTargetId}")
    public boolean deleteReportTarget(@PathVariable Long reportTargetId)
            throws ReportTargetNotFoundException {
        if (reportTargetDao.get(reportTargetId) == null) {
            throw new ReportTargetNotFoundException(reportTargetId);
        }
        return reportTargetDao.delete(reportTargetId);
    }


    @PostMapping("/reports/{reportId}/handle")
    public boolean handleReport(@RequestParam Long managerId,
            @RequestParam ManagerAction managerAction, @PathVariable Long reportId) {
        return flaggingService.handleReport(reportId, managerId, managerAction);
    }

}
