package org.xcolab.client.proposals.pojo.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GroupDto extends AbstractGroup_
        implements DataTransferObject<Group_> {

    public static final TypeProvider<GroupDto> TYPES =
            new TypeProvider<>(GroupDto.class,
                    new ParameterizedTypeReference<List<GroupDto>>() {
                    });

    public GroupDto() {}

    public GroupDto(AbstractGroup_ value) {
        super(value);
    }

    @Override
    public Group_ toPojo(RestService restService) {
        return new Group_(this, restService);
    }
}
