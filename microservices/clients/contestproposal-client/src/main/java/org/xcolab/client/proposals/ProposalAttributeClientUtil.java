package org.xcolab.client.proposals;

import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttribute;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;

public final class ProposalAttributeClientUtil {

    private static final ProposalAttributeClient client =
            ProposalAttributeClient.fromNamespace(ServiceNamespace.instance());

    public static ProposalAttributeClient getClient() {
        return client;
    }

    public static ProposalAttribute createProposalAttribute(
            ProposalAttribute proposalAttribute) {
        return client.createProposalAttribute(proposalAttribute);
    }

    public static ProposalAttribute getProposalAttribute(
            Long proposalId, String name, Long additionalId) {
        return client.getProposalAttribute(proposalId, name, additionalId);
    }

    public static ProposalAttribute getProposalAttribute(
            long proposalId, long version, String name, Long additionalId) {
        return client.getProposalAttribute(proposalId, version, name, additionalId);
    }

    public static ProposalAttribute getProposalAttribute(long id_)
            throws ProposalAttributeNotFoundException {
        return client.getProposalAttribute(id_);
    }

    public static Boolean deleteProposalAttribute(Long id_) {
        return client.deleteProposalAttribute(id_);
    }

    public static List<ProposalAttribute> getImpactProposalAttributes(
            Proposal proposal,
            FocusArea focusArea) {
        return client.getImpactProposalAttributes(proposal, focusArea);
    }

    public static List<ProposalAttribute> getImpactProposalAttributes(
            Proposal proposal) {
        return client.getImpactProposalAttributes(proposal);
    }

    public static boolean updateProposalAttribute(
            ProposalAttribute proposalAttribute) {
        return client.updateProposalAttribute(proposalAttribute);
    }

    public static List<ProposalAttribute> getAllProposalAttributes(
            Long proposalId) {
        return client.getAllProposalAttributes(proposalId);
    }

    public static List<ProposalAttribute> getAllProposalAttributes(
            Long proposalId, Integer version) {
        return client.getAllProposalAttributes(proposalId, version);
    }

    public static ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long additionalId, Long numericValue, Integer version) {
        return client.setProposalAttribute(userId, proposalId, name, additionalId, numericValue,
                version);
    }

    public static ProposalAttribute setProposalAttribute(
            ProposalAttribute proposalAttribute,
            Long authorId) {
        return client.setProposalAttribute(proposalAttribute, authorId);
    }

    public static ProposalAttribute setProposalAttribute(
            Long userId, Long proposalId, String name, Long aditionalId, String stringValue,
            Long numericValue, Double doubleValue, Integer version) {
        return client.setProposalAttribute(userId, proposalId, name, aditionalId, stringValue,
                numericValue,
                doubleValue, version);
    }

    public static ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long aditionalId, String stringValue, Integer version) {
        return client
                .setProposalAttribute(userId, proposalId, name, aditionalId, stringValue, version);
    }

    public static Boolean deleteProposalUnversionedAttribute(Long id_) {
        return client.deleteProposalUnversionedAttribute(id_);
    }

    public static List<ProposalUnversionedAttribute> getProposalUnversionedAttributesByProposalId(
            Long proposalId) {
        return client.getProposalUnversionedAttributesByProposalId(proposalId);
    }

    public static ProposalUnversionedAttribute createProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return client.createProposalUnversionedAttribute(proposalUnversionedAttribute);
    }


    public static boolean updateProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return client.updateProposalUnversionedAttribute(proposalUnversionedAttribute);
    }

}
