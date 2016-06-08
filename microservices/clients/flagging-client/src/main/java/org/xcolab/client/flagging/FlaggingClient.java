package org.xcolab.client.flagging;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.flagging.enums.TargetType;
import org.xcolab.client.flagging.exceptions.ReportNotFoundException;
import org.xcolab.client.flagging.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.flagging.pojo.Report;
import org.xcolab.client.flagging.pojo.ReportTarget;
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

    public static ReportTarget getReportTarget(TargetType type, String reason) throws ReportTargetNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reportTargets/" + type.name() + "/" + reason);
        try {
            return RequestUtils.get(uriBuilder, ReportTarget.class,
                    "type_" + type.name() + "reason_" + reason);
        } catch (EntityNotFoundException e) {
            throw new ReportTargetNotFoundException(type, reason);
        }
    }

    public static void updateReportTarget(ReportTarget reportTarget) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reportTargets/"
                + reportTarget.getType() + "/" + reportTarget.getReason());

        RequestUtils.put(uriBuilder, reportTarget);
    }

    public static ReportTarget createReportTarget(ReportTarget report) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/reportTargets");
        return RequestUtils.post(uriBuilder, report, ReportTarget.class);
    }
}
