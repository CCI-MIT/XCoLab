package org.xcolab.client.flagging;

import org.xcolab.client.flagging.exceptions.ReportNotFoundException;
import org.xcolab.client.flagging.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.flagging.pojo.AggregatedReport;
import org.xcolab.client.flagging.pojo.Report;
import org.xcolab.client.flagging.pojo.ReportTarget;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.util.enums.flagging.ManagerAction;
import org.xcolab.util.enums.flagging.TargetType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class FlaggingClient {

    private static final RestResource<Report, Long> reportResource =
            new RestResource1<>(FlaggingResource.REPORT, Report.TYPES);
    private static final RestResource<AggregatedReport, Long> aggregatedReportResource =
            new RestResource1<>(FlaggingResource.AGGREGATED_REPORT, AggregatedReport.TYPES);
    private static final RestResource<ReportTarget, Long> reportTargetResource =
            new RestResource1<>(FlaggingResource.REPORT_TARGET, ReportTarget.TYPES);

    private FlaggingClient() {
    }

    public static List<Report> listReports(int start, int last) {
        return listReports(start, last, null, null, null, null, null);
    }

    public static List<Report> listReports(int start, int last, Long reporteruserId,
            TargetType targetType, Long targetId, Long targetAdditionalId, Long manageruserId) {

        return reportResource.list()
                .addRange(start, last)
                .queryParam("sort", "createdAt")
                .optionalQueryParam("reporteruserId", reporteruserId)
                .optionalQueryParam("targetType", targetType)
                .optionalQueryParam("targetId", targetId)
                .optionalQueryParam("targetAdditionalId", targetAdditionalId)
                .optionalQueryParam("manageruserId", manageruserId)
                .execute();
    }

    public static int countReports(Long reporteruserId, TargetType targetType, Long targetId,
            Long targetAdditionalId, Long manageruserId) {
        return reportResource.count()
                .optionalQueryParam("reporteruserId", reporteruserId)
                .optionalQueryParam("targetType", targetType)
                .optionalQueryParam("targetId", targetId)
                .optionalQueryParam("targetAdditionalId", targetAdditionalId)
                .optionalQueryParam("manageruserId", manageruserId)
                .execute();
    }

    public static List<AggregatedReport> listAggregatedReports(int start, int last) {
        return aggregatedReportResource.list()
                .addRange(start, last)
                .queryParam("managerAction", ManagerAction.PENDING)
                .queryParam("sort", "firstReportDate")
                .execute();
    }

    public static Report getReport(long reportId) throws ReportNotFoundException {
        try {
            return reportResource.get(reportId)
                    .withCache(CacheKeys.of(Report.class, reportId),
                    CacheName.MISC_REQUEST).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ReportNotFoundException(reportId);
        }
    }

    public static boolean updateReport(Report report) {
        return reportResource.update(report, report.getReportId()).execute();
    }

    public static Report createReport(Report report) {
        return reportResource.create(report).execute();
    }

    public static List<ReportTarget> listReportTargets(int start, int last) {
        return listReportTargets(start, last, null);
    }

    public static List<ReportTarget> listReportTargets(TargetType targetType) {
        return listReportTargets(0, Integer.MAX_VALUE, targetType);
    }

    public static List<ReportTarget> listReportTargets(int start, int last, TargetType targetType) {
        return reportTargetResource.list()
                .addRange(start, last)
                .optionalQueryParam("type", targetType)
                .withCache(CacheKeys.withClass(ReportTarget.class)
                        .withParameter("type", targetType).asList(), CacheName.MISC_MEDIUM)
                .execute();
    }

    public static ReportTarget getReportTarget(long reportTargetId)
            throws ReportTargetNotFoundException {
        try {
            return reportTargetResource.get(reportTargetId)
                    .withCache(CacheKeys.of(ReportTarget.class, reportTargetId),
                            CacheName.MISC_REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ReportTargetNotFoundException(reportTargetId);
        }
    }

    public static ReportTarget getReportTarget(TargetType type, String reason)
            throws ReportTargetNotFoundException {
        final ReportTarget reportTarget = reportTargetResource.list()
                .queryParam("type", type.name())
                .queryParam("reason", reason)
                .withCache(CacheKeys.withClass(ReportTarget.class)
                        .withParameter("type", type.name())
                        .withParameter("reason", reason).asList(), CacheName.MISC_REQUEST)
                .executeWithResult().getFirstIfExists();
        if (reportTarget == null) {
            throw new ReportTargetNotFoundException(type, reason);
        }
        return reportTarget;
    }

    public static boolean updateReportTarget(ReportTarget reportTarget) {
        return reportTargetResource.update(reportTarget, reportTarget.getReportTargetId())
                .execute();
    }

    public static boolean deleteReportTarget(long reportTargetId) {
        return reportTargetResource.delete(reportTargetId).execute();
    }

    public static ReportTarget createReportTarget(ReportTarget reportTarget) {
        return reportTargetResource.create(reportTarget).execute();
    }

    public static Report reportProposal(UserWrapper reporter, long proposalId, long proposalVersion,
            String reason, String comment) {
        return report(reporter, proposalId, proposalVersion, TargetType.PROPOSAL, reason, comment);
    }

    public static Report reportComment(UserWrapper reporter, long commentId,
            String reason, String comment) {
        return report(reporter, commentId, 0L, TargetType.COMMENT, reason, comment);
    }

    public static Report report(UserWrapper reporter, long targetId, Long targetAdditionalId,
            TargetType targetType, String reason, String comment) {
        Report report = new Report();
        report.setReporteruserId(reporter.getId());
        report.setTargetType(targetType.name());
        report.setTargetId(targetId);
        report.setTargetAdditionalId(targetAdditionalId);
        report.setWeight(reporter.getReportKarma());
        report.setReason(reason);
        report.setComment(comment);
        report.setManagerAction(ManagerAction.PENDING.name());
        return createReport(report);
    }

    public static boolean handleReport(long managerId, ManagerAction managerAction, long reportId) {
        return reportResource.elementService(reportId, "handle", Boolean.class)
                .queryParam("manageruserId", managerId)
                .queryParam("managerAction", managerAction)
                .post();
    }
}
