package org.xcolab.client.contest.pojo;

import org.xcolab.util.http.client.RestService;

public class ContestSchedule extends AbstractContestSchedule {

    public ContestSchedule() {}

    public ContestSchedule(ContestSchedule value) {
        super(value);
    }

    public ContestSchedule(Long   id_, String name, String description,
        String status, Long   basescheduleid) {
        super(id_, name, description, status, basescheduleid);
    }

    public ContestSchedule(AbstractContestSchedule abstractContestSchedule,
            RestService restService) {
        super(abstractContestSchedule);
    }
}
