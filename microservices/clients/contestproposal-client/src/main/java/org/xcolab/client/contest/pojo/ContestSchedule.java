package org.xcolab.client.contest.pojo;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.util.http.client.enums.ServiceNamespace;


public class ContestSchedule extends AbstractContestSchedule {

    private final ContestClient contestClient;

    public ContestSchedule() {
        contestClient = ContestClientUtil.getClient();
    }

    public ContestSchedule(ContestSchedule value) {
        super(value);
        contestClient = ContestClientUtil.getClient();
    }

    public ContestSchedule(Long   id, String name, String description,
        String status, Long   basescheduleid) {
        super(id, name, description, status, basescheduleid);
        contestClient = ContestClientUtil.getClient();
    }

    public ContestSchedule(AbstractContestSchedule abstractContestSchedule,
            ServiceNamespace serviceNamespace) {
        super(abstractContestSchedule);
        this.contestClient = ContestClient.fromNamespace(serviceNamespace);
    }

    public boolean isUsedInNonEmptyContest() {
        return contestClient.getContestsByContestScheduleId(getId())
                .stream()
                .anyMatch(Contest::isNotEmpty);
    }
}
