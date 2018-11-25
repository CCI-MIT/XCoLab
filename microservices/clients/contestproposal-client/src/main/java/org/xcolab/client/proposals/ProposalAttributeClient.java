package org.xcolab.client.proposals;

import org.xcolab.client.contest.resources.ProposalResource;
import org.xcolab.client.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttributeHelperDataDto;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttribute;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttributeHelperDataDto;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class ProposalAttributeClient {

    private final RestResource1<ProposalAttribute, Long> proposalAttributeResource;
    private final RestResource1<ProposalUnversionedAttribute, Long>
            proposalUnversionedAttributeResource;

    private final RestResource1<Proposal, Long> proposalResource;
    private final RestResource2L<Proposal, ProposalVersion> proposalVersionResource;

    public ProposalAttributeClient() {
        proposalAttributeResource = new RestResource1<>(ProposalResource.PROPOSAL_ATTRIBUTE,
                ProposalAttribute.TYPES);
        proposalUnversionedAttributeResource = new RestResource1<>(
                ProposalResource.PROPOSAL_UNVERSIONED_ATTRIBUTE,
                ProposalUnversionedAttribute.TYPES);

        proposalResource = new RestResource1<>(ProposalResource.PROPOSAL, Proposal.TYPES);
        this.proposalVersionResource = new RestResource2L<>(proposalResource,
                "versions", ProposalVersion.TYPES);
    }

    public ProposalAttribute createProposalAttribute(ProposalAttribute proposalAttribute) {
        return proposalAttributeResource.create(new ProposalAttribute(proposalAttribute))
                .execute();
    }

    public ProposalAttribute getProposalAttribute(Long proposalId, String name, Long additionalId) {
        ListQuery<ProposalAttribute> listQ =
                proposalAttributeResource.list()
                        .queryParam("proposalId", proposalId)
                        .queryParam("name", name);
        if (additionalId != null && additionalId != 0) {
            listQ = listQ.queryParam("additionalId", additionalId);
        }
        final ProposalAttribute firstOrNull = listQ.executeWithResult().getFirstIfExists();
        return firstOrNull != null ? firstOrNull : null;
    }

    public ProposalAttribute getProposalAttribute(long proposalId, long version, String name, Long additionalId) {
        ListQuery<ProposalAttribute> listQ =
                proposalAttributeResource.list()
                        .queryParam("proposalId", proposalId)
                        .queryParam("name", name)
                        .queryParam("version", version);
        if (additionalId != null && additionalId != 0) {
            listQ = listQ.queryParam("additionalId", additionalId);
        }
        final ProposalAttribute firstOrNull = listQ.executeWithResult().getFirstIfExists();
        return firstOrNull != null ? firstOrNull : null;
    }

    public ProposalAttribute getProposalAttribute(long id)
            throws ProposalAttributeNotFoundException {
        return proposalAttributeResource.get(id)
                .withCache(CacheKeys.of(ProposalAttribute.class, id), CacheName.MISC_REQUEST)
                .execute();
    }

    public Boolean deleteProposalAttribute(Long id) {
        return proposalAttributeResource.delete(id).execute();
    }

    public List<ProposalAttribute> getImpactProposalAttributes(Proposal proposal) {
        return proposalAttributeResource
                .collectionService("getImpactProposalAttributes", ProposalAttribute.TYPES.getTypeReference())
                .queryParam("proposalId", proposal.getId())
                .queryParam("currentVersion", proposal.getCurrentVersion())
                .getList();
    }

    public boolean updateProposalAttribute(ProposalAttribute proposalAttribute) {
        return proposalAttributeResource
                .update(new ProposalAttribute(proposalAttribute), proposalAttribute.getId())
                .cacheKey(CacheKeys.of(ProposalAttribute.class, proposalAttribute.getId()))
                .execute();
    }

    public List<ProposalAttribute> getAllProposalAttributes(Long proposalId) {
        return proposalAttributeResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .withCache(CacheName.MISC_REQUEST)
                .execute();
    }

    public List<ProposalAttribute> getAllProposalAttributes(Long proposalId, Integer version) {
        return proposalAttributeResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("version", version)
                //.withCache(CacheKeys.of(ProposalAttribute.class, proposalId),CacheName.PROPOSAL_DETAILS))
                .withCache(CacheKeys.withClass(ProposalAttribute.class)
                        .withParameter("proposalId", proposalId)
                        .withParameter("version",version)
                        .asList(), CacheName.PROPOSAL_DETAILS)
                .execute();
    }

    public List<ProposalAttribute> getAllProposalAttributes(long proposalId, String name,
            long additionalId) {
        return proposalAttributeResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("name", name)
                .optionalQueryParam("additionalId", additionalId)
                .execute();
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
        ServiceRequestUtils.invalidateCache(CacheKeys.withClass(Proposal.class)
                .withParameter("proposalId", proposal.getId())
                .withParameter("version", proposal.getVersion()).asList(), CacheName.PROPOSAL_DETAILS);
    }
    public ProposalAttribute setProposalAttribute(ProposalAttribute proposalAttribute,
            Long authorUserId) {
        //TODO COLAB-2589: replace with better cache invalidation mechanism

        //.optionalQueryParam("proposalId", proposalId)
        //        .optionalQueryParam("version", version)
        //        .withCache(CacheName.PROPOSAL_DETAILS)

        return proposalAttributeResource.collectionService("setProposalAttribute", ProposalAttribute.class)
                .queryParam("authorUserId", authorUserId)
                .post(proposalAttribute)
                ;
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
        return proposalUnversionedAttributeResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute();
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
                .create(new ProposalUnversionedAttribute(proposalUnversionedAttribute))
                .execute();
    }

    public ProposalUnversionedAttribute getProposalUnversionedAttribute(Long proposalId,
            String name) throws EntityNotFoundException{
        return proposalUnversionedAttributeResource
                .collectionService("getByProposalIdName", ProposalUnversionedAttribute.class)
                .queryParam("proposalId", proposalId)
                .queryParam("name", name)
                .getChecked()
                ;
    }

    public boolean updateProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return proposalUnversionedAttributeResource
                .update(new ProposalUnversionedAttribute(proposalUnversionedAttribute)
                        , proposalUnversionedAttribute.getId())
                .execute();
    }
}
