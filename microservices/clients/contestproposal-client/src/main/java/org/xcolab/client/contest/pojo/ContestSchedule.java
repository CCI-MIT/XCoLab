package org.xcolab.client.contest.pojo;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.util.http.client.RestService;

public class ContestSchedule extends AbstractContestSchedule {

    private final ContestClient contestClient;

    public ContestSchedule() {
        contestClient = ContestClientUtil.getClient();
    }

    public ContestSchedule(ContestSchedule value) {
        super(value);
        contestClient = ContestClientUtil.getClient();
    }

    public ContestSchedule(Long   id_, String name, String description,
        String status, Long   basescheduleid) {
        super(id_, name, description, status, basescheduleid);
        contestClient = ContestClientUtil.getClient();
    }

    public ContestSchedule(AbstractContestSchedule abstractContestSchedule,
            RestService contestService) {
        super(abstractContestSchedule);
        this.contestClient = ContestClient.fromService(contestService);
    }

    public boolean isUsedInNonEmptyContest() {
        return contestClient.getContestsByContestScheduleId(getId_())
                .stream()
                .anyMatch(Contest::isNotEmpty);
    }
}
