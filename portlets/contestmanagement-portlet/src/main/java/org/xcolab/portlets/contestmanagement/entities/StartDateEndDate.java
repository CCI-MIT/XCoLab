package org.xcolab.portlets.contestmanagement.entities;

import java.util.Date;

/**
 * Created by Thomas on 2/9/2015.
 */
public class StartDateEndDate {
    private Date startDate;
    private Date endDate;

    public StartDateEndDate(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
