package org.xcolab.client.moderation.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ReportNotFoundException extends EntityNotFoundException {

    public ReportNotFoundException(long reportId) {
        super("Report with id " + reportId + " not found", ReportNotFoundException.class);
    }
}
