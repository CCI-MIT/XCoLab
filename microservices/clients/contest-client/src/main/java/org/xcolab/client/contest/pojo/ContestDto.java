package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContestDto extends AbstractContest implements Serializable {

    public static final TypeProvider<ContestDto> TYPES = new TypeProvider<>(ContestDto.class,
            new ParameterizedTypeReference<List<ContestDto>>() {});

    private static final long serialVersionUID = 1L;

    public ContestDto() {
    }

    public ContestDto(AbstractContest contest) {
        super(contest);
    }

    public Contest toContest() {
        return new Contest(this);
    }

    public static List<Contest> toContests(List<ContestDto> dtos) {
        return dtos.stream()
                .map(Contest::new)
                .collect(Collectors.toList());
    }
}
