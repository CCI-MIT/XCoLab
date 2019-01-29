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
public class ProposalUnversionedAttributeHelperDataDto implements Serializable {

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
