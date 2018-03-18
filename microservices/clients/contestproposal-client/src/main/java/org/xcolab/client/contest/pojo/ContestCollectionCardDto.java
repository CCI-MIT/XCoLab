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
public class ContestCollectionCardDto extends AbstractContestCollectionCard implements DataTransferObject<ContestCollectionCard> {
    public static final TypeProvider<ContestCollectionCardDto> TYPES =
            new TypeProvider<>(ContestCollectionCardDto.class,
                    new ParameterizedTypeReference<List<ContestCollectionCardDto>>() {
                    });

    public ContestCollectionCardDto() {}

    public ContestCollectionCardDto(AbstractContestCollectionCard value) {
        super(value);
    }

    @Override
    public ContestCollectionCard toPojo(ServiceNamespace serviceNamespace) {
        return new ContestCollectionCard(this, serviceNamespace);
    }
}
