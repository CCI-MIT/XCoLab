package org.xcolab.client.flagging.exceptions;

import org.xcolab.client.flagging.enums.TargetType;

public class ReportTargetNotFoundException extends Exception {

    public ReportTargetNotFoundException(TargetType type, String reason) {
        super("ReportTarget of type " + type.name() + " and reason " + reason + " not found");
    }
}
