package org.xcolab.client.proposals.pojo.attributes;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.util.List;
import java.util.Map;

public class ProposalUnversionedAttributeHelperDataDto {

    private static final long serialVersionUID = 1;

    public static final TypeProvider<ProposalUnversionedAttributeHelperDataDto> TYPES =
            new TypeProvider<>(ProposalUnversionedAttributeHelperDataDto.class,
                    new ParameterizedTypeReference<List<ProposalUnversionedAttributeHelperDataDto>>() {});

    private Map<String, Map<Long, ProposalUnversionedAttribute>> attributesByNameAndAdditionalId;


    public Map<String, Map<Long, ProposalUnversionedAttribute>> getAttributesByNameAndAdditionalId() {
        return attributesByNameAndAdditionalId;
    }

    public void setAttributesByNameAndAdditionalId(
            Map<String, Map<Long, ProposalUnversionedAttribute>> attributesByNameAndAdditionalId) {
        this.attributesByNameAndAdditionalId = attributesByNameAndAdditionalId;
    }
}
