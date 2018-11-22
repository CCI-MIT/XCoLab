package org.xcolab.client.contest.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ContestSchedule extends AbstractContestSchedule {

    public static final TypeProvider<ContestSchedule> TYPES = new TypeProvider<>(
            ContestSchedule.class, new ParameterizedTypeReference<List<ContestSchedule>>() {});

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
