package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttribute;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttributeDto;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalUnversionedAttributeClient {

    private static final Map<RestService, ProposalUnversionedAttributeClient> instances
            = new HashMap<>();

    private final RestService proposalService;
    private final RestResource1<ProposalUnversionedAttributeDto, Long>
            proposalUnversionedAttributeResource;

    private ProposalUnversionedAttributeClient(RestService proposalService) {
        proposalUnversionedAttributeResource = new RestResource1<>(proposalService,
        "proposalUnversionedAttributes", ProposalUnversionedAttributeDto.TYPES);
        this.proposalService = proposalService;
    }

    public static ProposalUnversionedAttributeClient fromService(RestService proposalService) {
        ProposalUnversionedAttributeClient instance = instances.get(proposalService);
        if (instance == null) {
            instance = new ProposalUnversionedAttributeClient(proposalService);
            instances.put(proposalService, instance);
        }
        return instance;
    }

    public Boolean deleteProposalUnversionedAttribute(Long id_) {
        return proposalUnversionedAttributeResource.delete(id_).execute();
    }

    public List<ProposalUnversionedAttribute> getProposalUnversionedAttributesByProposalId(
            Long proposalId) {
        return DtoUtil.toPojos(proposalUnversionedAttributeResource.list()
                .withCache(CacheKeys.withClass(ProposalUnversionedAttributeDto.class)
                                .withParameter("proposalId", proposalId)
                                .asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .execute(), proposalService);
    }

    public void createOrUpdateProposalUnversionedAttribute(long authorId,
            String attributeValue,
            String attributeName,
            Long proposalId) {
        ProposalUnversionedAttribute pua =
                getProposalUnversionedAttribute(proposalId, attributeName.toString());
        if (pua == null) {
            pua = new ProposalUnversionedAttribute();
            pua.setCreateAuthorId(authorId);
            pua.setCreateDate(new Timestamp(new Date().getTime()));
            pua.setLastUpdateDate(new Timestamp(new Date().getTime()));
            pua.setName(attributeName);
            pua.setStringValue(attributeValue);
            pua.setProposalId(proposalId);
            createProposalUnversionedAttribute(pua);
        } else {
            pua.setCreateAuthorId(authorId);
            pua.setLastUpdateDate(new Timestamp(new Date().getTime()));
            pua.setStringValue(attributeValue);
            updateProposalUnversionedAttribute(pua);
        }
    }

    public ProposalUnversionedAttribute createProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return proposalUnversionedAttributeResource
                .create(new ProposalUnversionedAttributeDto(proposalUnversionedAttribute))
                .execute().toPojo(proposalService);
    }

    public ProposalUnversionedAttribute getProposalUnversionedAttribute(Long proposalId,
            String name) {
        return proposalUnversionedAttributeResource
                .service("getByProposalIdName", ProposalUnversionedAttribute.class)
                .queryParam("proposalId", proposalId)
                .queryParam("name", name)
                .get();

    }

    public boolean updateProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return proposalUnversionedAttributeResource
                .update(new ProposalUnversionedAttributeDto(proposalUnversionedAttribute)
                        , proposalUnversionedAttribute.getId_())
                .execute();
    }
}
