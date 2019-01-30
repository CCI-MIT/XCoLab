package org.xcolab.client.moderation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.moderation.exceptions.ReportNotFoundException;
import org.xcolab.client.moderation.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.moderation.pojo.IAggregatedReport;
import org.xcolab.client.moderation.pojo.IReport;
import org.xcolab.client.moderation.pojo.IReportTarget;
import org.xcolab.client.moderation.pojo.tables.pojos.Report;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.util.enums.moderation.ManagerAction;
import org.xcolab.util.enums.moderation.TargetType;

import java.util.List;

@FeignClient("xcolab-moderation-service")
public interface IModerationClient {

    @GetMapping(value = "/reports")
    List<IReport> listReports(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "lastRecord", required = false) Integer lastRecord,
            @RequestParam(value = "reporterUserId", required = false) Long reporterUserId,
            @RequestParam(value = "targetType", required = false) TargetType targetType,
            @RequestParam(value = "targetId", required = false) Long targetId,
            @RequestParam(value = "targetAdditionalId", required = false) Long targetAdditionalId,
            @RequestParam(value = "managerUserId", required = false) Long managerUserId);

    default List<IReport> listReports(int start, int last) {
        return listReports(start, last, null, null, null, null, null);
    }

    @GetMapping(value = "/count/reports")
    int countReports(@RequestParam(value = "reporterUserId", required = false) Long reporterUserId,
            @RequestParam(value = "targetType", required = false) TargetType targetType,
            @RequestParam(value = "targetId", required = false) Long targetId,
            @RequestParam(value = "targetAdditionalId", required = false) Long targetAdditionalId,
            @RequestParam(value = "managerUserId", required = false) Long managerUserId);

    @GetMapping("/reports/{reportId}")
    IReport getReport(@PathVariable(value = "reportId") Long reportId)
            throws ReportNotFoundException;

    @PostMapping("/reports")
    IReport createReport(@RequestBody IReport report);

    default IReport report(UserWrapper reporter, long targetId, Long targetAdditionalId,
            TargetType targetType, String reason, String comment) {
        IReport report = new Report();
        report.setReporterUserId(reporter.getId());
        report.setTargetType(targetType.name());
        report.setTargetId(targetId);
        report.setTargetAdditionalId(targetAdditionalId);
        report.setWeight(reporter.getReportKarma());
        report.setReason(reason);
        report.setComment(comment);
        report.setManagerAction(ManagerAction.PENDING.name());
        return createReport(report);
    }

    @PutMapping("/reports")
    boolean updateReport(@RequestBody IReport report) throws ReportNotFoundException;

    @GetMapping(value = "/aggregatedReports")
    List<IAggregatedReport> listAggregatedReports(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "lastRecord", required = false) Integer lastRecord);

    @GetMapping(value = "/reportTargets")
    List<IReportTarget> listReportTargets(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "lastRecord", required = false) Integer lastRecord,
            @RequestParam(value = "type", required = false) TargetType type);

    default List<IReportTarget> listReportTargets(int start, int last) {
        return listReportTargets(start, last, null);
    }

    default List<IReportTarget> listReportTargets(TargetType targetType) {
        return listReportTargets(0, Integer.MAX_VALUE, targetType);
    }

    @GetMapping("/reportTargets/{reportTargetId}")
    IReportTarget getReportTarget(@PathVariable(value = "reportTargetId") Long reportTargetId)
            throws ReportTargetNotFoundException;

    @PostMapping("/reportTargets")
    IReportTarget createReportTarget(@RequestBody IReportTarget reportTarget);

    @PutMapping("/reportTargets")
    boolean updateReportTarget(@RequestBody IReportTarget reportTarget)
            throws ReportTargetNotFoundException;

    @DeleteMapping("/reportTargets/{reportTargetId}")
    boolean deleteReportTarget(@PathVariable(value = "reportTargetId") Long reportTargetId)
            throws ReportTargetNotFoundException;

    default IReport reportProposal(UserWrapper reporter, long proposalId, long proposalVersion,
            String reason, String comment) {
        return report(reporter, proposalId, proposalVersion, TargetType.PROPOSAL, reason, comment);
    }

    default IReport reportComment(UserWrapper reporter, long commentId,
            String reason, String comment) {
        return report(reporter, commentId, 0L, TargetType.COMMENT, reason, comment);
    }

    @PostMapping("/reports/{reportId}/handle")
    boolean handleReport(@RequestParam(value = "managerId") Long managerId,
            @RequestParam(value = "managerAction") ManagerAction managerAction,
            @PathVariable(value = "reportId") Long reportId);

}
