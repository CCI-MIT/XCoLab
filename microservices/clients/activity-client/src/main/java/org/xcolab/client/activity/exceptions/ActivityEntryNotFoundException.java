package org.xcolab.client.activity.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ActivityEntryNotFoundException extends EntityNotFoundException {

    public ActivityEntryNotFoundException(String msg) {
        super(msg, ActivityEntryNotFoundException.class);
    }
}
