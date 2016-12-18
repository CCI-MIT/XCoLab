package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

public class ModelDiscussionDto extends AbstractModelDiscussion
        implements DataTransferObject<ModelDiscussion> {

    public static final TypeProvider<ModelDiscussionDto> TYPES = new TypeProvider<>(
            ModelDiscussionDto.class,
            new ParameterizedTypeReference<List<ModelDiscussionDto>>() {});

    public ModelDiscussionDto() {
    }

    public ModelDiscussionDto(AbstractModelDiscussion value) {
        super(value);
    }

    @Override
    public ModelDiscussion toPojo(RestService restService) {
        return new ModelDiscussion(this, restService);
    }
}
