package org.xcolab.client.activity.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ActivityEntryNotFoundException extends EntityNotFoundException {

    public ActivityEntryNotFoundException(Long activityEntryId) {
        super("ActivityEntry with id " + activityEntryId + " not found.",
                ActivityEntryNotFoundException.class);
    }
}
