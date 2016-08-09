package org.xcolab.utils;

import java.util.Date;

public class ClockImpl implements Clock {

    /**
     * Default implementation, returns the current date.
     * @return instance of Date with the current date
     */
    @Override
    public Date now() {
        return new Date();
    }
}
