package org.xcolab.service.moderation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.moderation.IModerationClient;
import org.xcolab.client.moderation.exceptions.ReportNotFoundException;
import org.xcolab.client.moderation.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.moderation.pojo.IAggregatedReport;
import org.xcolab.client.moderation.pojo.IReport;
import org.xcolab.client.moderation.pojo.IReportTarget;
import org.xcolab.service.moderation.domain.report.ReportDao;
import org.xcolab.service.moderation.domain.reportTarget.ReportTargetDao;
import org.xcolab.service.moderation.service.ModerationService;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.moderation.ManagerAction;
import org.xcolab.util.enums.moderation.TargetType;

import java.util.List;


@RestController
public class ModerationController implements IModerationClient {

    private final ReportDao reportDao;

    private final ReportTargetDao reportTargetDao;

    private final ModerationService moderationService;

    @Autowired
    public ModerationController(ReportDao reportDao, ReportTargetDao reportTargetDao,
            ModerationService moderationService) {
        this.reportDao = reportDao;
        this.reportTargetDao = reportTargetDao;
        this.moderationService = moderationService;
    }

    @GetMapping(value = "/reports")
    public List<IReport> listReports(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer lastRecord,
            @RequestParam(required = false) Long reporterUserId,
            @RequestParam(required = false) TargetType targetType,
            @RequestParam(required = false) Long targetId,
            @RequestParam(required = false) Long targetAdditionalId,
            @RequestParam(required = false) Long managerUserId) {
        //Metrics.counter("moderation-service","function","listReports").increment();
        Integer limitRecord = lastRecord - startRecord;
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, "");

        return reportDao.findByGiven(paginationHelper, reporterUserId,
                managerUserId, targetType != null ? targetType.name() : null,
                targetId, targetAdditionalId, null);
    }

    @GetMapping(value = "/count/reports")
    public int countReports(@RequestParam(required = false) Long reporterUserId,
            @RequestParam(required = false) TargetType targetType,
            @RequestParam(required = false) Long targetId,
            @RequestParam(required = false) Long targetAdditionalId, Long managerUserId) {
        //Metrics.counter("moderation-service","function","countReports").increment();
        return reportDao
                .countByGiven(reporterUserId, managerUserId, targetType.toString(), targetId,
                        targetAdditionalId, "");
    }

    @GetMapping("/reports/{reportId}")
    public IReport getReport(@PathVariable Long reportId) throws ReportNotFoundException {
        //Metrics.counter("moderation-service","function","getReport").increment();
        final IReport report = reportDao.get(reportId);
        if (report == null) {
            throw new ReportNotFoundException(reportId);
        }
        return report;
    }

    @PostMapping("/reports")
    public IReport createReport(@RequestBody IReport report) {
        //Metrics.counter("moderation-service","function","createReport").increment();
        return moderationService.createReport(report);
    }

    @PutMapping("/reports")
    public boolean updateReport(@RequestBody IReport report) throws ReportNotFoundException {
        //Metrics.counter("moderation-service","function","updateReport").increment();
        if (reportDao.get(report.getId()) == null) {
            throw new ReportNotFoundException(report.getId());
        }
        report.setId(report.getId());
        return reportDao.update(report);
    }

    @GetMapping(value = "/aggregatedReports")
    public List<IAggregatedReport> listAggregatedReports(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer lastRecord) {
        //Metrics.counter("moderation-service","function","listAggregatedReports").increment();
        Integer limitRecord = lastRecord - startRecord;
        PaginationHelper paginationHelper =
                new PaginationHelper(startRecord, limitRecord, "firstReportDate");

        return reportDao.findAggregatedByGiven(paginationHelper, null,
                null, null, null, null, "PENDING");
    }

    @GetMapping(value = "/reportTargets")
    public List<IReportTarget> listReportTargets(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer lastRecord,
            @RequestParam(required = false) TargetType type) {
        //Metrics.counter("moderation-service","function","listReportTargets").increment();
        Integer limitRecord = lastRecord - startRecord;
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, "");
        return reportTargetDao.findByGiven(paginationHelper, type);
    }

    @GetMapping("/reportTargets/{reportTargetId}")
    public IReportTarget getReportTarget(@PathVariable Long reportTargetId)
            throws ReportTargetNotFoundException {
        //Metrics.counter("moderation-service","function","getReportTarget").increment();
        final IReportTarget reportTarget = reportTargetDao.get(reportTargetId);
        if (reportTarget == null) {
            throw new ReportTargetNotFoundException(reportTargetId);
        }
        return reportTarget;
    }

    @PostMapping("/reportTargets")
    public IReportTarget createReportTarget(@RequestBody IReportTarget reportTarget) {
        //Metrics.counter("moderation-service","function","createReportTarget").increment();
        return reportTargetDao.create(reportTarget);
    }

    @PutMapping("/reportTargets")
    public boolean updateReportTarget(@RequestBody IReportTarget reportTarget)
            throws ReportTargetNotFoundException {
        //Metrics.counter("moderation-service","function","updateReportTarget").increment();
        if (reportTargetDao.get(reportTarget.getId()) == null) {
            throw new ReportTargetNotFoundException(reportTarget.getId());
        }
        reportTarget.setId(reportTarget.getId());
        return reportTargetDao.update(reportTarget);
    }

    @DeleteMapping("/reportTargets/{reportTargetId}")
    public boolean deleteReportTarget(@PathVariable Long reportTargetId)
            throws ReportTargetNotFoundException {
        //Metrics.counter("moderation-service","function","deleteReportTarget").increment();
        if (reportTargetDao.get(reportTargetId) == null) {
            throw new ReportTargetNotFoundException(reportTargetId);
        }
        return reportTargetDao.delete(reportTargetId);
    }


    @PostMapping("/reports/{reportId}/handle")
    public boolean handleReport(@RequestParam Long managerId,
            @RequestParam ManagerAction managerAction, @PathVariable Long reportId) {
        //Metrics.counter("moderation-service","function","handleReport").increment();
        return moderationService.handleReport(reportId, managerId, managerAction);
    }

}
