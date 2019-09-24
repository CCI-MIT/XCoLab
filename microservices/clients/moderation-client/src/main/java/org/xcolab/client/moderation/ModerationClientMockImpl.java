package org.xcolab.client.moderation;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.moderation.exceptions.ReportNotFoundException;
import org.xcolab.client.moderation.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.moderation.pojo.IAggregatedReport;
import org.xcolab.client.moderation.pojo.IReport;
import org.xcolab.client.moderation.pojo.IReportTarget;
import org.xcolab.util.enums.moderation.ManagerAction;
import org.xcolab.util.enums.moderation.TargetType;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ModerationClientMockImpl implements IModerationClient {

    public List<IReport> listReports(Integer startRecord, Integer lastRecord, Long reporterUserId,
            TargetType targetType, Long targetId, Long targetAdditionalId, Long managerUserId) {
        return Collections.emptyList();
    }

    public int countReports(Long reporterUserId, TargetType targetType, Long targetId,
            Long targetAdditionalId, Long managerUserId) {
        return 0;
    }

    public IReport getReport(Long reportId) throws ReportNotFoundException {
        return null;
    }

    public IReport createReport(IReport report) {
        return null;
    }

    public boolean updateReport(IReport report) throws ReportNotFoundException {
        return true;
    }

    public List<IAggregatedReport> listAggregatedReports(Integer startRecord, Integer lastRecord) {
        return Collections.emptyList();
    }

    public List<IReportTarget> listReportTargets(Integer startRecord, Integer lastRecord,
            TargetType type) {
        return null;
    }

    public IReportTarget getReportTarget(Long reportTargetId) throws ReportTargetNotFoundException {
        return null;
    }

    public IReportTarget createReportTarget(IReportTarget reportTarget) {
        return null;
    }

    public boolean updateReportTarget(IReportTarget reportTarget)
            throws ReportTargetNotFoundException {
        return true;
    }

    public boolean deleteReportTarget(Long reportTargetId) throws ReportTargetNotFoundException {
        return true;
    }

    public boolean handleReport(Long managerId, ManagerAction managerAction, Long reportId) {
        return true;
    }
}
