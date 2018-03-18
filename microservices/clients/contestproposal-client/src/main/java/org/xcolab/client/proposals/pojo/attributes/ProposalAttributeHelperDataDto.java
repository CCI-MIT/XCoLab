package org.xcolab.client.proposals.pojo.attributes;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
