package org.xcolab.client.proposals;

import org.xcolab.client.contest.resources.ProposalResource;
import org.xcolab.client.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalDto;
import org.xcolab.client.proposals.pojo.ProposalVersionDto;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttributeDto;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttributeHelperDataDto;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttribute;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttributeDto;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttributeHelperDataDto;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalAttributeClient {

    private static final Map<ServiceNamespace, ProposalAttributeClient> instances = new HashMap<>();

    private final ServiceNamespace serviceNamespace;
    private final RestResource1<ProposalAttributeDto, Long> proposalAttributeResource;
    private final RestResource1<ProposalUnversionedAttributeDto, Long>
            proposalUnversionedAttributeResource;

    private final RestResource1<ProposalDto, Long> proposalResource;
    private final RestResource2L<ProposalDto, ProposalVersionDto> proposalVersionResource;

    private ProposalAttributeClient(ServiceNamespace serviceNamespace) {
        proposalAttributeResource = new RestResource1<>(ProposalResource.PROPOSAL_ATTRIBUTE,
                ProposalAttributeDto.TYPES, serviceNamespace);
        proposalUnversionedAttributeResource = new RestResource1<>(
                ProposalResource.PROPOSAL_UNVERSIONED_ATTRIBUTE,
                ProposalUnversionedAttributeDto.TYPES, serviceNamespace);

        proposalResource = new RestResource1<>(ProposalResource.PROPOSAL, ProposalDto.TYPES,
                serviceNamespace);
        this.proposalVersionResource = new RestResource2L<>(proposalResource,
                "versions", ProposalVersionDto.TYPES);
        this.serviceNamespace = serviceNamespace;
    }

    public static ProposalAttributeClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, ProposalAttributeClient::new);
    }

    public ProposalAttribute createProposalAttribute(ProposalAttribute proposalAttribute) {
        return proposalAttributeResource.create(new ProposalAttributeDto(proposalAttribute))
                .execute().toPojo(serviceNamespace);
    }

    public ProposalAttribute getProposalAttribute(Long proposalId, String name, Long additionalId) {
        ListQuery<ProposalAttributeDto> listQ =
                proposalAttributeResource.list()
                        .queryParam("proposalId", proposalId)
                        .queryParam("name", name);
        if (additionalId != null && additionalId != 0) {
            listQ = listQ.queryParam("additionalId", additionalId);
        }
        final ProposalAttributeDto firstOrNull = listQ.executeWithResult().getFirstIfExists();
        return firstOrNull != null ? firstOrNull.toPojo(serviceNamespace) : null;
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
        final ProposalAttributeDto firstOrNull = listQ.executeWithResult().getFirstIfExists();
        return firstOrNull != null ? firstOrNull.toPojo(serviceNamespace) : null;
    }

    public ProposalAttribute getProposalAttribute(long id)
            throws ProposalAttributeNotFoundException {
        return proposalAttributeResource.get(id)
                .withCache(CacheKeys.of(ProposalAttributeDto.class, id), CacheName.MISC_REQUEST)
                .execute().toPojo(serviceNamespace);
    }

    public Boolean deleteProposalAttribute(Long id) {
        return proposalAttributeResource.delete(id).execute();
    }

    public List<ProposalAttribute> getImpactProposalAttributes(Proposal proposal) {
        return DtoUtil.toPojos(proposalAttributeResource
                .collectionService("getImpactProposalAttributes", ProposalAttributeDto.TYPES.getTypeReference())
                .queryParam("proposalId", proposal.getId())
                .queryParam("currentVersion", proposal.getCurrentVersion())
                .getList(), serviceNamespace);
    }

    public boolean updateProposalAttribute(ProposalAttribute proposalAttribute) {
        return proposalAttributeResource
                .update(new ProposalAttributeDto(proposalAttribute), proposalAttribute.getId())
                .cacheKey(CacheKeys.of(ProposalAttributeDto.class, proposalAttribute.getId()))
                .execute();
    }

    public List<ProposalAttribute> getAllProposalAttributes(Long proposalId) {
        return DtoUtil.toPojos(proposalAttributeResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .withCache(CacheName.MISC_REQUEST)
                .execute(), serviceNamespace);
    }

    public List<ProposalAttribute> getAllProposalAttributes(Long proposalId, Integer version) {
        return DtoUtil.toPojos(proposalAttributeResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("version", version)
                //.withCache(CacheKeys.of(ProposalAttributeDto.class, proposalId),CacheName.PROPOSAL_DETAILS))
                .withCache(CacheKeys.withClass(ProposalAttributeDto.class)
                        .withParameter("proposalId", proposalId)
                        .withParameter("version",version)
                        .asList(), CacheName.PROPOSAL_DETAILS)
                .execute(), serviceNamespace);
    }

    public List<ProposalAttribute> getAllProposalAttributes(long proposalId, String name,
            long additionalId) {
        return DtoUtil.toPojos(proposalAttributeResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("name", name)
                .optionalQueryParam("additionalId", additionalId)
                .execute(), serviceNamespace);
    }

    public ProposalAttributeHelperDataDto getProposalAttributeHelperData(long proposalId,
            long version) {
        return proposalVersionResource.resolveParentId(proposalResource.id(proposalId))
                .<ProposalAttributeHelperDataDto, ProposalAttributeHelperDataDto>elementService(
                        version, "attributeHelper",
                        ProposalAttributeHelperDataDto.class)
                .withCache(CacheKeys.withClass(ProposalAttributeHelperDataDto.class)
                        .withParameter("proposalId", proposalId)
                        .withParameter("version", version)
                        .build(), CacheName.MISC_MEDIUM)
                .get();
    }

    public ProposalUnversionedAttributeHelperDataDto getProposalUnversionedAttributeHelperData(
            long proposalId) {
        return proposalResource
                .elementService(proposalId,"attributeHelper",
                        ProposalUnversionedAttributeHelperDataDto.class)
                .get();
    }

    private ProposalAttribute createProposalAttribute(Long userId, Long proposalId, String name,
            Long additionalId) {
        ProposalAttribute proposalAttribute = new ProposalAttribute();
        proposalAttribute.setProposalId(proposalId);
        proposalAttribute.setName(name);
        proposalAttribute.setAdditionalId(additionalId);
        return proposalAttribute;
    }


    public void invalidateProposalAttibuteCache(Proposal proposal) {
        ServiceRequestUtils.invalidateCache(CacheKeys.withClass(ProposalDto.class)
                .withParameter("proposalId", proposal.getId())
                .withParameter("version", proposal.getVersion()).asList(), CacheName.PROPOSAL_DETAILS);
    }
    public ProposalAttribute setProposalAttribute(ProposalAttribute proposalAttribute,
            Long authorUserId) {
        //TODO COLAB-2589: replace with better cache invalidation mechanism

        //.optionalQueryParam("proposalId", proposalId)
        //        .optionalQueryParam("version", version)
        //        .withCache(CacheName.PROPOSAL_DETAILS)

        return proposalAttributeResource.collectionService("setProposalAttribute", ProposalAttributeDto.class)
                .queryParam("authorUserId", authorUserId)
                .post(proposalAttribute)
                .toPojo(serviceNamespace);
    }

    public ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long additionalId, String stringValue, Long numericValue, Double doubleValue,
            Integer version) {
        ProposalAttribute proposalAttribute =
                createProposalAttribute(userId, proposalId, name, additionalId);
        proposalAttribute.setStringValue(stringValue);
        proposalAttribute.setNumericValue(numericValue);
        proposalAttribute.setRealValue(doubleValue);
        proposalAttribute.setVersion(version);
        return setProposalAttribute(proposalAttribute, userId);
    }

    public ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long additionalId, String stringValue, Integer version) {
        return setProposalAttribute(userId, proposalId, name, additionalId, stringValue,
                null, null, version);
    }

    public ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long additionalId, Long numericValue, Integer version) {
        return setProposalAttribute(userId, proposalId, name, additionalId, null,
                numericValue, null, version);
    }

    public Boolean deleteProposalUnversionedAttribute(Long id) {
        return proposalUnversionedAttributeResource.delete(id).execute();
    }

    public List<ProposalUnversionedAttribute> getProposalUnversionedAttributesByProposalId(
            Long proposalId) {
        return DtoUtil.toPojos(proposalUnversionedAttributeResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute(), serviceNamespace);
    }

    public void createOrUpdateUnversionedStringAttribute(Long proposalId, String attributeName,
            long authorUserId, String attributeValue) {
        createOrUpdateUnversionedAttribute(proposalId, attributeName, authorUserId, null,
                attributeValue, null);
    }

    public void createOrUpdateUnversionedDoubleAttribute(Long proposalId, String attributeName,
            long authorUserId, double attributeValue) {
        createOrUpdateUnversionedAttribute(proposalId, attributeName, authorUserId, null,
                null, attributeValue);
    }

    public void createOrUpdateUnversionedLongAttribute(Long proposalId, String attributeName,
            long authorUserId, long attributeValue) {
        createOrUpdateUnversionedAttribute(proposalId, attributeName, authorUserId, attributeValue,
                null, null);
    }

    public void createOrUpdateUnversionedAttribute(Long proposalId, String attributeName,
            long authorUserId, Long longValue, String stringValue, Double doubleValue) {
        ProposalUnversionedAttribute pua;
        try {
             pua =
                    getProposalUnversionedAttribute(proposalId, attributeName);
                pua.setFirstAuthorUserId(authorUserId);
                pua.setNumericValue(longValue);
                pua.setStringValue(stringValue);
                pua.setRealValue(doubleValue);
                updateProposalUnversionedAttribute(pua);

        } catch (EntityNotFoundException e) {
            pua = new ProposalUnversionedAttribute();
            pua.setFirstAuthorUserId(authorUserId);
            pua.setName(attributeName);
            pua.setNumericValue(longValue);
            pua.setStringValue(stringValue);
            pua.setRealValue(doubleValue);
            pua.setProposalId(proposalId);
            createProposalUnversionedAttribute(pua);
        }
    }

    public ProposalUnversionedAttribute createProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return proposalUnversionedAttributeResource
                .create(new ProposalUnversionedAttributeDto(proposalUnversionedAttribute))
                .execute().toPojo(serviceNamespace);
    }

    public ProposalUnversionedAttribute getProposalUnversionedAttribute(Long proposalId,
            String name) throws EntityNotFoundException{
        return proposalUnversionedAttributeResource
                .collectionService("getByProposalIdName", ProposalUnversionedAttributeDto.class)
                .queryParam("proposalId", proposalId)
                .queryParam("name", name)
                .getChecked()
                .toPojo(serviceNamespace);
    }

    public boolean updateProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return proposalUnversionedAttributeResource
                .update(new ProposalUnversionedAttributeDto(proposalUnversionedAttribute)
                        , proposalUnversionedAttribute.getId())
                .execute();
    }
}
