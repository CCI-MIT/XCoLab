package org.xcolab.client.moderation;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.moderation.exceptions.ReportNotFoundException;
import org.xcolab.client.moderation.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.moderation.pojo.AggregatedReport;
import org.xcolab.client.moderation.pojo.IReport;
import org.xcolab.client.moderation.pojo.IReportTarget;
import org.xcolab.util.enums.moderation.ManagerAction;
import org.xcolab.util.enums.moderation.TargetType;

import java.util.List;

@Component
@Profile("test")
public class ModerationClientMockImpl implements IModerationClient {

    public List<IReport> listReports(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "lastRecord", required = false) Integer lastRecord,
            @RequestParam(value = "reporterUserId", required = false) Long reporterUserId,
            @RequestParam(value = "targetType", required = false) TargetType targetType,
            @RequestParam(value = "targetId", required = false) Long targetId,
            @RequestParam(value = "targetAdditionalId", required = false) Long targetAdditionalId,
            @RequestParam(value = "managerUserId", required = false) Long managerUserId) {
        return null;
    }

    public int countReports(@RequestParam(value = "reporterUserId", required = false) Long reporterUserId,
            @RequestParam(value = "targetType", required = false) TargetType targetType,
            @RequestParam(value = "targetId", required = false) Long targetId,
            @RequestParam(value = "targetAdditionalId", required = false) Long targetAdditionalId,
            @RequestParam(value = "managerUserId", required = false) Long managerUserId) {
        return 0;
    }

    public IReport getReport(@PathVariable(value = "reportId") Long reportId)
            throws ReportNotFoundException {
        return null;
    }

    public IReport createReport(IReport report) {
        return null;
    }

    public boolean updateReport(IReport report) throws ReportNotFoundException {
        return true;
    }

    public List<AggregatedReport> listAggregatedReports(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "lastRecord", required = false) Integer lastRecord) {
        return null;
    }

    public List<IReportTarget> listReportTargets(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "lastRecord", required = false) Integer lastRecord,
            @RequestParam(value = "type", required = false) TargetType type) {
        return null;
    }

    public IReportTarget getReportTarget(@PathVariable(value = "reportTargetId") Long reportTargetId)
            throws ReportTargetNotFoundException {
        return null;
    }

    public IReportTarget createReportTarget(@RequestBody IReportTarget reportTarget) {
        return null;
    }

    public boolean updateReportTarget(IReportTarget reportTarget) throws ReportTargetNotFoundException {
        return true;
    }

    public boolean deleteReportTarget(@PathVariable(value = "reportTargetId") Long reportTargetId)
            throws ReportTargetNotFoundException {
        return true;
    }

    public boolean handleReport(@RequestParam(value = "managerId") Long managerId,
            @RequestParam(value = "managerAction") ManagerAction managerAction,
            @PathVariable(value = "reportId") Long reportId) {
        return true;
    }
}
