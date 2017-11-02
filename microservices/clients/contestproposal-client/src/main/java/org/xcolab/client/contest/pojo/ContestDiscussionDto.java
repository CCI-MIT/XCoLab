package org.xcolab.client.contest.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

public class ContestDiscussionDto extends AbstractContestDiscussion implements DataTransferObject<ContestDiscussion> {

    public static final TypeProvider<ContestDiscussionDto> TYPES = new TypeProvider<>(ContestDiscussionDto.class,
            new ParameterizedTypeReference<List<ContestDiscussionDto>>() {
            });

    public ContestDiscussionDto() {
    }

    public ContestDiscussionDto(AbstractContestDiscussion value) {
        super(value);
    }

    @Override
    public ContestDiscussion toPojo(ServiceNamespace serviceNamespace) {
        return new ContestDiscussion(this, serviceNamespace);
    }
}
