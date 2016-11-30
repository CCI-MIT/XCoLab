package org.xcolab.client.proposals;

import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttributeDto;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttribute;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttributeDto;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.dto.DtoUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalAttributeClient {

    private static final Map<RestService, ProposalAttributeClient> instances = new HashMap<>();

    private final RestService proposalService;
    private final RestResource1<ProposalAttributeDto, Long> proposalAttributeResource;
    private final RestResource1<ProposalUnversionedAttributeDto, Long>
            proposalUnversionedAttributeResource;

    private ProposalAttributeClient(RestService proposalService) {
        proposalAttributeResource = new RestResource1<>(proposalService,
                "proposalAttributes", ProposalAttributeDto.TYPES);
        proposalUnversionedAttributeResource = new RestResource1<>(proposalService,
                "proposalUnversionedAttributes", ProposalUnversionedAttributeDto.TYPES);
        this.proposalService = proposalService;
    }

    public static ProposalAttributeClient fromService(RestService proposalService) {
        ProposalAttributeClient instance = instances.get(proposalService);
        if (instance == null) {
            instance = new ProposalAttributeClient(proposalService);
            instances.put(proposalService, instance);
        }
        return instance;
    }

    public ProposalAttribute createProposalAttribute(ProposalAttribute proposalAttribute) {
        return proposalAttributeResource.create(new ProposalAttributeDto(proposalAttribute))
                .execute().toPojo(proposalService);
    }

    public ProposalAttribute getImpactProposalAttributes(Long proposalId) {
        return null;
    }

    public ProposalAttribute getProposalAttribute(Long proposalId, String name, Long additionalId) {
        ListQuery<ProposalAttributeDto> listQ =
                proposalAttributeResource.list()
                        .queryParam("proposalId", proposalId)
                        .queryParam("name", name);
        if (additionalId != null && additionalId != 0) {
            listQ = listQ.queryParam("additionalId", additionalId);
        }
        return listQ.executeWithResult().getFirstIfExists().toPojo(proposalService);
    }

    public ProposalAttribute getProposalAttribute(long proposalId, long version, String name, Long additionalId) {
        ListQuery<ProposalAttributeDto> listQ =
                proposalAttributeResource.list()
                        .queryParam("proposalId", proposalId)
                        .queryParam("name", name)
                        .queryParam("version", version);
        if (additionalId != null && additionalId != 0) {
            listQ = listQ.queryParam("additionalId", additionalId);
        }
        return listQ.executeWithResult().getFirstIfExists().toPojo(proposalService);
    }

    public ProposalAttribute getProposalAttribute(long id_)
            throws ProposalAttributeNotFoundException {
        return proposalAttributeResource.get(id_)
                .withCache(CacheKeys.of(ProposalAttributeDto.class, id_), CacheRetention.REQUEST)
                .execute().toPojo(proposalService);
    }

    public Boolean deleteProposalAttribute(Long id_) {
        return proposalAttributeResource.delete(id_).execute();
    }

    public List<ProposalAttribute> getImpactProposalAttributes(Proposal proposal,
            FocusArea focusArea) {
        List<ProposalAttribute> filteredProposalAttributes = new ArrayList<>();
        for (ProposalAttribute attribute : getImpactProposalAttributes(proposal)) {
            if (attribute.getAdditionalId() == focusArea.getId_().longValue()) {
                filteredProposalAttributes.add(attribute);
            }
        }
        return filteredProposalAttributes;
    }

    public List<ProposalAttribute> getImpactProposalAttributes(Proposal proposal) {
        return DtoUtil.toPojos(proposalAttributeResource
                .service("getImpactProposalAttributes", ProposalAttributeDto.TYPES.getTypeReference())
                .queryParam("proposalId", proposal.getProposalId())
                .queryParam("currentVersion", proposal.getCurrentVersion())
                .getList(), proposalService);
    }

    public boolean updateProposalAttribute(ProposalAttribute proposalAttribute) {
        return proposalAttributeResource
                .update(new ProposalAttributeDto(proposalAttribute), proposalAttribute.getId_())
                .execute();
    }

    public List<ProposalAttribute> getAllProposalAttributes(Long proposalId) {
        return DtoUtil.toPojos(proposalAttributeResource.list()
                .withCache(CacheKeys.withClass(ProposalAttributeDto.class)
                                .withParameter("proposalId", proposalId).asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .execute(), proposalService);
    }

    public List<ProposalAttribute> getAllProposalAttributes(Long proposalId, Integer version) {
        return DtoUtil.toPojos(proposalAttributeResource.list()
                .withCache(CacheKeys.withClass(ProposalAttributeDto.class)
                                .withParameter("proposalId", proposalId)
                                .withParameter("version", version).asList(),
                        CacheRetention.REQUEST)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("version", version)
                .execute(), proposalService);
    }

    public ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long aditionalId, Long numericValue) {
        ProposalAttribute proposalAttribute =
                createProposalAttribute(userId, proposalId, name, aditionalId);
        proposalAttribute.setNumericValue(numericValue);
        return setProposalAttribute(proposalAttribute, userId);

    }

    private ProposalAttribute createProposalAttribute(Long userId, Long proposalId, String name,
            Long aditionalId) {
        ProposalAttribute proposalAttribute = new ProposalAttribute();
        proposalAttribute.setProposalId(proposalId);
        proposalAttribute.setName(name);
        proposalAttribute.setAdditionalId(aditionalId);
        return proposalAttribute;
    }

    public ProposalAttribute setProposalAttribute(ProposalAttribute proposalAttribute,
            Long authorId) {
        return proposalAttributeResource.service("setProposalAttribute", ProposalAttributeDto.class)
//                .queryParam("proposalId", proposalAttribute.getProposalId())
//                .queryParam("name", proposalAttribute.getName())
//                .queryParam("stringValue", proposalAttribute.getStringValue())
//                .queryParam("numericValue", proposalAttribute.getNumericValue())
//                .queryParam("realValue", proposalAttribute.getRealValue())
//                .queryParam("additionalId", proposalAttribute.getAdditionalId())
//                .queryParam("version", proposalAttribute.getVersion())
//                .queryParam("versionWhenCreated", proposalAttribute.getVersionWhenCreated())
                .queryParam("authorId", authorId)
                .post(proposalAttribute)
                .toPojo(proposalService);
    }

    public ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long aditionalId, String stringValue, Long numericValue, Double doubleValue) {
        ProposalAttribute proposalAttribute =
                createProposalAttribute(userId, proposalId, name, aditionalId);
        proposalAttribute.setStringValue(stringValue);
        proposalAttribute.setNumericValue(numericValue);
        proposalAttribute.setRealValue(doubleValue);
        return setProposalAttribute(proposalAttribute, userId);

    }

    public ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long aditionalId, String stringValue) {
        ProposalAttribute proposalAttribute =
                createProposalAttribute(userId, proposalId, name, aditionalId);
        proposalAttribute.setStringValue(stringValue);
        return setProposalAttribute(proposalAttribute, userId);

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
                .service("getByProposalIdName", ProposalUnversionedAttributeDto.class)
                .queryParam("proposalId", proposalId)
                .queryParam("name", name)
                .get()
                .toPojo(proposalService);
    }

    public boolean updateProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return proposalUnversionedAttributeResource
                .update(new ProposalUnversionedAttributeDto(proposalUnversionedAttribute)
                        , proposalUnversionedAttribute.getId_())
                .execute();
    }
}
