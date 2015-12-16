package org.xcolab.portlets.contestmanagement.entities;

import org.joda.time.DateTime;

/**
 * Created by Thomas on 2/9/2015.
 */
public class StartDateEndDate {
    private DateTime startDate;
    private DateTime endDate;

    public StartDateEndDate(DateTime startDate, DateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }
}
