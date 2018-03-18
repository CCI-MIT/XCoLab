package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;
import org.xcolab.commons.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContestScheduleDto extends AbstractContestSchedule implements DataTransferObject<ContestSchedule> {

    public static final TypeProvider<ContestScheduleDto> TYPES =
            new TypeProvider<>(ContestScheduleDto.class,
                    new ParameterizedTypeReference<List<ContestScheduleDto>>() {
                    });

    public ContestScheduleDto() {}

    public ContestScheduleDto(AbstractContestSchedule value) {
        super(value);
    }

    @Override
    public ContestSchedule toPojo(ServiceNamespace serviceNamespace) {
        return new ContestSchedule(this, serviceNamespace);
    }
}
