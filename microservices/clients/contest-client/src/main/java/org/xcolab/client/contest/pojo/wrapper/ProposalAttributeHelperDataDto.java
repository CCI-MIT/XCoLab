package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalAttributeHelperDataDto implements Serializable {

    private static final long serialVersionUID = 1;

    public static final TypeProvider<ProposalAttributeHelperDataDto> TYPES =
            new TypeProvider<>(ProposalAttributeHelperDataDto.class,
                    new ParameterizedTypeReference<List<ProposalAttributeHelperDataDto>>() {});

    private Map<String, Map<Long, ProposalAttribute>> attributesByNameAndAdditionalId;

    public Map<String, Map<Long, ProposalAttribute>> getAttributesByNameAndAdditionalId() {
        return attributesByNameAndAdditionalId;
    }

    public void setAttributesByNameAndAdditionalId(
            Map<String, Map<Long, ProposalAttribute>> attributesByNameAndAdditionalId) {
        this.attributesByNameAndAdditionalId = attributesByNameAndAdditionalId;
    }
}
