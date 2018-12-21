package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContestSchedule extends AbstractContestSchedule implements Serializable {

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

    public ContestSchedule(AbstractContestSchedule abstractContestSchedule) {
        super(abstractContestSchedule);
        this.contestClient = ContestClientUtil.getClient();
    }

    public boolean isUsedInNonEmptyContest() {
        return contestClient.getContestsByContestScheduleId(getId())
                .stream()
                .anyMatch(Contest::isNotEmpty);
    }
}
