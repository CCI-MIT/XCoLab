package org.xcolab.client.moderation.exceptions;

public class ReportNotFoundException extends Exception {

    public ReportNotFoundException(long reportId) {
        super("Report with id " + reportId + " not found");
    }
}
