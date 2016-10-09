package org.xcolab.client.proposals;

import org.xcolab.client.contest.pojo.FocusArea;
import org.xcolab.client.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalAttribute;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.queries.ListQuery;

import java.util.ArrayList;
import java.util.List;

public final class ProposalAttributeClient {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final RestResource1<ProposalAttribute, Long> proposalAttributeResource = new RestResource1<>(proposalService,
            "proposalAttributes", ProposalAttribute.TYPES);

    public static ProposalAttribute createProposalAttribute(ProposalAttribute proposalAttribute) {
        return proposalAttributeResource.create(proposalAttribute).execute();
    }

    public static ProposalAttribute getImpactProposalAttributes(Long proposalId) {
        return null;
    }

    public static ProposalAttribute getProposalAttribute(Long proposalId, String name, Long additionalId) {
        ListQuery<ProposalAttribute> listQ = proposalAttributeResource.list()
                .queryParam("proposalId", proposalId)
                .queryParam("name", name);
        if (additionalId != null && additionalId != 0) {
            listQ = listQ.queryParam("additionalId", additionalId);
        }
        List<ProposalAttribute> list = listQ.execute();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }

    }

    public static ProposalAttribute getProposalAttribute(long id_) throws ProposalAttributeNotFoundException {
        return proposalAttributeResource.get(id_)
                .withCache(CacheKeys.of(ProposalAttribute.class, id_), CacheRetention.REQUEST)
                .execute();
    }

    public static Boolean deleteProposalAttribute(Long id_) {
        return proposalAttributeResource.delete(id_).execute();
    }

    public static List<ProposalAttribute> getImpactProposalAttributes(Proposal proposal) {
        return proposalAttributeResource.service("getImpactProposalAttributes", ProposalAttribute.TYPES.getTypeReference())
                .queryParam("proposalId", proposal.getProposalId())
                .queryParam("currentVersion", proposal.getCurrentVersion())
                .getList();
    }

    public static List<ProposalAttribute> getImpactProposalAttributes(Proposal proposal, FocusArea focusArea) {
        List<ProposalAttribute> filteredProposalAttributes = new ArrayList<>();
        for (ProposalAttribute attribute : getImpactProposalAttributes(proposal)) {
            if (attribute.getAdditionalId() == focusArea.getId_()) {
                filteredProposalAttributes.add(attribute);
            }
        }
        return filteredProposalAttributes;
    }

    public static boolean updateProposalAttribute(ProposalAttribute proposalAttribute) {
        return proposalAttributeResource.update(proposalAttribute, proposalAttribute.getId_())
                .execute();
    }

    public static List<ProposalAttribute> getAllProposalAttributes(Long proposalId) {
        return proposalAttributeResource.list()
                .withCache(CacheKeys.withClass(ProposalAttribute.class)
                                .withParameter("proposalId", proposalId).asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public static List<ProposalAttribute> getAllProposalAttributes(Long proposalId, Integer version) {
        return proposalAttributeResource.list()
                .withCache(CacheKeys.withClass(ProposalAttribute.class)
                                .withParameter("proposalId", proposalId)
                                .withParameter("version", version).asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("version", version)
                .execute();
    }

    public static ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name, Long aditionalId, Long numericValue) {
        ProposalAttribute proposalAttribute = createProposalAttribute(userId, proposalId, name, aditionalId);
        proposalAttribute.setNumericValue(numericValue);
        return setProposalAttribute(proposalAttribute, userId);

    }

    public static ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name, Long aditionalId, String stringValue, Long numericValue, Double doubleValue) {
        ProposalAttribute proposalAttribute = createProposalAttribute(userId, proposalId, name, aditionalId);
        proposalAttribute.setStringValue(stringValue);
        proposalAttribute.setNumericValue(numericValue);
        proposalAttribute.setRealValue(doubleValue);
        return setProposalAttribute(proposalAttribute, userId);

    }

    private static ProposalAttribute createProposalAttribute(Long userId, Long proposalId, String name, Long aditionalId) {
        ProposalAttribute proposalAttribute = new ProposalAttribute();
        proposalAttribute.setProposalId(proposalId);
        proposalAttribute.setName(name);
        proposalAttribute.setAdditionalId(aditionalId);
        return proposalAttribute;
    }

    public static ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name, Long aditionalId, String stringValue) {
        ProposalAttribute proposalAttribute = createProposalAttribute(userId, proposalId, name, aditionalId);
        proposalAttribute.setStringValue(stringValue);
        return setProposalAttribute(proposalAttribute, userId);

    }

    public static ProposalAttribute setProposalAttribute(ProposalAttribute proposalAttribute, Long authorId) {
        return proposalAttributeResource.service("setProposalAttribute", ProposalAttribute.class)
                .queryParam("proposalId", proposalAttribute.getProposalId())
                .queryParam("name", proposalAttribute.getName())
                .queryParam("stringValue", proposalAttribute.getStringValue())
                .queryParam("numericValue", proposalAttribute.getNumericValue())
                .queryParam("realValue", proposalAttribute.getRealValue())
                .queryParam("additionalId", proposalAttribute.getAdditionalId())
                .queryParam("version", proposalAttribute.getVersion())
                .queryParam("versionWhenCreated", proposalAttribute.getVersionWhenCreated())
                .queryParam("authorId", authorId)
                .post();
    }
}
