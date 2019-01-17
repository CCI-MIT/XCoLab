package org.xcolab.client.contest.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.pojo.tables.pojos.Contest;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;
import java.util.stream.Collectors;

public class ContestDto extends Contest {

    public static final TypeProvider<ContestDto> TYPES = new TypeProvider<>(ContestDto.class,
            new ParameterizedTypeReference<List<ContestDto>>() {});

    private static final long serialVersionUID = 1L;

    public ContestDto() {
    }

    public ContestDto(Contest contest) {
        super(contest);
    }

    public ContestWrapper toContest() {
        return new ContestWrapper(this);
    }

    public static List<ContestWrapper> toContests(List<ContestDto> dtos) {
        return dtos.stream()
                .map(ContestWrapper::new)
                .collect(Collectors.toList());
    }
}
