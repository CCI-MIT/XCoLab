package org.xcolab.client.flagging;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.flagging.pojo.AggregatedReport;
import org.xcolab.util.enums.flagging.ManagerAction;
import org.xcolab.util.enums.flagging.TargetType;
import org.xcolab.client.flagging.exceptions.ReportNotFoundException;
import org.xcolab.client.flagging.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.flagging.pojo.Report;
import org.xcolab.client.flagging.pojo.ReportTarget;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

import java.util.List;

public final class FlaggingClient {

    private static final String EUREKA_APPLICATION_ID =
            "localhost:" + RequestUtils.getServicesPort() + "/flagging-service";

    private FlaggingClient() {
    }

    public static List<Report> listReports(int start, int last) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/reports")
                        .queryParam("startRecord", start)
                        .queryParam("limitRecord", last)
                        .queryParam("sort", "createDate");

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Report>>() {
        });
    }

    public static List<AggregatedReport> listAggregatedReports(int start, int last) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID
                        + "/aggregatedReports")
                        .queryParam("startRecord", start)
                        .queryParam("limitRecord", last)
                        .queryParam("managerAction", ManagerAction.PENDING)
                        .queryParam("sort", "firstReportDate");

        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<AggregatedReport>>() {
        });
    }

    public static int countReports() {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/reports");

        return RequestUtils.getCount(uriBuilder);
    }

    public static Report getReport(long reportId) throws ReportNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reports/" + reportId);
        try {
            return RequestUtils.get(uriBuilder, Report.class, "reportId_" + reportId);
        } catch (EntityNotFoundException e) {
            throw new ReportNotFoundException(reportId);
        }
    }

    public static void updateReport(Report report) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reports/" + report.getReportId());

        RequestUtils.put(uriBuilder, report);
    }

    public static Report createReport(Report report) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reports");
        return RequestUtils.post(uriBuilder, report, Report.class);
    }

    public static List<ReportTarget> listReportTargets(int start, int last) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/reportTargets")
                        .queryParam("startRecord", start)
                        .queryParam("limitRecord", last);

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<ReportTarget>>() {
        });
    }

    public static ReportTarget getReportTarget(long reportTargetId) throws ReportTargetNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reportTargets/" + reportTargetId);
        try {
            return RequestUtils.get(uriBuilder, ReportTarget.class,
                    "id_" + reportTargetId);
        } catch (EntityNotFoundException e) {
            throw new ReportTargetNotFoundException(reportTargetId);
        }
    }

    public static ReportTarget getReportTarget(TargetType type, String reason) throws ReportTargetNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reportTargets")
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
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reportTargets/" + reportTarget.getReportTargetId());

        RequestUtils.put(uriBuilder, reportTarget);
    }

    public static boolean deleteReportTarget(long reportTargetId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reportTargets/" + reportTargetId);

        return RequestUtils.delete(uriBuilder);
    }

    public static ReportTarget createReportTarget(ReportTarget report) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reportTargets");
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

    private static Report report(Member reporter, long targetId, Long targetAdditionalId,
            TargetType targetType, String reason, String comment) {
        Report report = new Report();
        report.setReporterMemberId(reporter.getId_());
        report.setTargetType(targetType.name());
        report.setTargetId(targetId);
        report.setTargetAdditionalId(targetAdditionalId);
        report.setWeight(reporter.getReportKarma());
        report.setReason(reason);
        report.setComment(comment);
        return createReport(report);
    }

    public static boolean handleReport(long managerId, ManagerAction managerAction, long reportId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reports/" + reportId + "/handle")
                    .queryParam("managerMemberId", managerId)
                    .queryParam("managerAction", managerAction);
        return RequestUtils.post(uriBuilder, null, Boolean.class);
    }
}
