package org.xcolab.client.flagging;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.flagging.exceptions.ReportNotFoundException;
import org.xcolab.client.flagging.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.flagging.pojo.AggregatedReport;
import org.xcolab.client.flagging.pojo.Report;
import org.xcolab.client.flagging.pojo.ReportTarget;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.flagging.ManagerAction;
import org.xcolab.util.enums.flagging.TargetType;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class FlaggingClient {

    private static final RestService flaggingService = new RestService("flagging-service");
    private static final RestResource reportResource = new RestResource(flaggingService,
            "reports");
    private static final RestResource aggregatedReportResource = new RestResource(flaggingService,
            "aggregatedReports");
    private static final RestResource reportTargetResource = new RestResource(flaggingService,
            "reportTarget");

    private FlaggingClient() {
    }

    public static List<Report> listReports(int start, int last) {
        return listReports(start, last, null, null, null, null, null);
    }

    public static List<Report> listReports(int start, int last, Long reporterMemberId,
            TargetType targetType, Long targetId, Long targetAdditionalId, Long managerMemberId) {

        final UriBuilder uriBuilder = reportResource.getResourceUrl()
                .addRange(start, last)
                .queryParam("sort", "createDate")
                .optionalQueryParam("reporterMemberId", reporterMemberId)
                .optionalQueryParam("targetType", targetType)
                .optionalQueryParam("targetId", targetId)
                .optionalQueryParam("targetAdditionalId", targetAdditionalId)
                .optionalQueryParam("managerMemberId", managerMemberId);

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Report>>() {
        });
    }

    public static int countReports(Long reporterMemberId, TargetType targetType, Long targetId,
            Long targetAdditionalId, Long managerMemberId) {
        final UriBuilder uriBuilder = reportResource.getResourceUrl()
                .optionalQueryParam("reporterMemberId", reporterMemberId)
                .optionalQueryParam("targetType", targetType)
                .optionalQueryParam("targetId", targetId)
                .optionalQueryParam("targetAdditionalId", targetAdditionalId)
                .optionalQueryParam("managerMemberId", managerMemberId);

        return RequestUtils.getCount(uriBuilder);
    }

    public static List<AggregatedReport> listAggregatedReports(int start, int last) {
        final UriBuilder uriBuilder = aggregatedReportResource.getResourceUrl()
                .addRange(start, last)
                .queryParam("managerAction", ManagerAction.PENDING)
                .queryParam("sort", "firstReportDate");

        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<AggregatedReport>>() {
        });
    }

    public static Report getReport(long reportId) throws ReportNotFoundException {
        final UriBuilder uriBuilder = reportResource.getResourceUrl(reportId);
        try {
            return RequestUtils.get(uriBuilder, Report.class, "reportId_" + reportId);
        } catch (EntityNotFoundException e) {
            throw new ReportNotFoundException(reportId);
        }
    }

    public static void updateReport(Report report) {
        final UriBuilder uriBuilder = reportResource.getResourceUrl(report.getReportId());
        RequestUtils.put(uriBuilder, report);
    }

    public static Report createReport(Report report) {
        final UriBuilder uriBuilder = reportResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, report, Report.class);
    }

    public static List<ReportTarget> listReportTargets(int start, int last) {
        return listReportTargets(start, last, null);
    }

    public static List<ReportTarget> listReportTargets(TargetType targetType) {
        return listReportTargets(0, Integer.MAX_VALUE, targetType);
    }

    public static List<ReportTarget> listReportTargets(int start, int last, TargetType targetType) {
        final UriBuilder uriBuilder = reportTargetResource.getResourceUrl()
                .addRange(start, last)
                .optionalQueryParam("type", targetType);

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<ReportTarget>>() {
        });
    }

    public static ReportTarget getReportTarget(long reportTargetId)
            throws ReportTargetNotFoundException {
        final UriBuilder uriBuilder = reportTargetResource.getResourceUrl(reportTargetId);
        try {
            return RequestUtils.get(uriBuilder, ReportTarget.class,
                    "id_" + reportTargetId);
        } catch (EntityNotFoundException e) {
            throw new ReportTargetNotFoundException(reportTargetId);
        }
    }

    public static ReportTarget getReportTarget(TargetType type, String reason)
            throws ReportTargetNotFoundException {
        final UriBuilder uriBuilder = reportTargetResource.getResourceUrl()
                    .queryParam("type", type.name())
                    .queryParam("reason", reason);
        try {
            return RequestUtils.getFirstFromList(uriBuilder,
                    new ParameterizedTypeReference<List<ReportTarget>>() {
                    }, "type_" + type.name() + "reason_" + reason);
        } catch (EntityNotFoundException e) {
            throw new ReportTargetNotFoundException(type, reason);
        }
    }

    public static void updateReportTarget(ReportTarget reportTarget) {
        final UriBuilder uriBuilder = reportTargetResource
                .getResourceUrl(reportTarget.getReportTargetId());
        RequestUtils.put(uriBuilder, reportTarget);
    }

    public static boolean deleteReportTarget(long reportTargetId) {
        final UriBuilder uriBuilder = reportTargetResource.getResourceUrl(reportTargetId);
        return RequestUtils.delete(uriBuilder);
    }

    public static ReportTarget createReportTarget(ReportTarget report) {
        final UriBuilder uriBuilder = reportTargetResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, report, ReportTarget.class);
    }

    public static Report reportProposal(Member reporter, long proposalId, long proposalVersion,
            String reason, String comment) {
        return report(reporter, proposalId, proposalVersion, TargetType.PROPOSAL, reason, comment);
    }

    public static Report reportComment(Member reporter, long commentId,
            String reason, String comment) {
        return report(reporter, commentId, 0L, TargetType.COMMENT, reason, comment);
    }

    public static Report report(Member reporter, long targetId, Long targetAdditionalId,
            TargetType targetType, String reason, String comment) {
        Report report = new Report();
        report.setReporterMemberId(reporter.getId_());
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
        final UriBuilder uriBuilder = reportResource.getResourceUrl(reportId)
                .path("/handle")
                .queryParam("managerMemberId", managerId)
                .queryParam("managerAction", managerAction);
        return RequestUtils.post(uriBuilder, null, Boolean.class);
    }
}
