package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.ProposalAttribute;
import org.xcolab.client.proposals.pojo.ProposalContestPhaseAttribute;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class ProposalContestPhaseAttributeClient {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final RestResource<ProposalContestPhaseAttribute, Long> proposalContestPhaseAttributeResource = new RestResource1<>(proposalService,
            "proposalContestPhaseAttributes", ProposalContestPhaseAttribute.TYPES);

    public static List<ProposalContestPhaseAttribute> getAllProposalContestPhaseProposalAttributes(Long contestPhaseId, Long proposalId) {
        return proposalContestPhaseAttributeResource.list()
                .withCache(CacheKeys.withClass(ProposalContestPhaseAttribute.class)
                                .withParameter("proposalId", proposalId)
                                .withParameter("contestPhaseId", contestPhaseId).asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public static ProposalContestPhaseAttribute getProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId, String name) {
        try {
            return proposalContestPhaseAttributeResource.service("getByContestPhaseProposalIdName", ProposalContestPhaseAttribute.class)
                    .optionalQueryParam("contestPhaseId", contestPhaseId)
                    .optionalQueryParam("proposalId", proposalId)
                    .optionalQueryParam("name", name)
                    .getChecked();
        }catch (EntityNotFoundException ignored){
            return null;
        }
    }

    public static Boolean isProposalContestPhaseAttributeSetAndTrue(Long proposalId, long contestPhaseId, String name) {
        ProposalContestPhaseAttribute proposalAttribute = getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
        if (proposalAttribute != null) {
            return proposalAttribute.getStringValue().equals("true");
        } else {
            return false;
        }

    }

    public static ProposalContestPhaseAttribute createProposalContestPhaseAttribute(ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return proposalContestPhaseAttributeResource.create(proposalContestPhaseAttribute).execute();
    }

    public static boolean persistSelectedJudgesAttribute(Long proposalId, Long contestPhaseId, List<Long> selectedJudges) {
        ProposalContestPhaseAttribute judges = getProposalContestPhaseAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.SELECTED_JUDGES);

        StringBuilder attributeValue = new StringBuilder("");
        if (selectedJudges != null) {
            for (Long userId : selectedJudges) {
                attributeValue.append(userId).append(";");
            }
        }
        judges.setStringValue(attributeValue.toString().replaceAll(";$", ""));

        updateProposalContestPhaseAttribute(judges);
        return true;
    }

    public static ProposalContestPhaseAttribute setProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId, String name, Long aditionalId, Long numericValue, String stringValue) {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
        if (proposalContestPhaseAttribute != null) {
            proposalContestPhaseAttribute.setAdditionalId(aditionalId);
            proposalContestPhaseAttribute.setNumericValue(numericValue);
            proposalContestPhaseAttribute.setStringValue(stringValue);
            updateProposalContestPhaseAttribute(proposalContestPhaseAttribute);
            return proposalContestPhaseAttribute;
        } else {
            proposalContestPhaseAttribute = new ProposalContestPhaseAttribute();
            proposalContestPhaseAttribute.setProposalId(proposalId);
            proposalContestPhaseAttribute.setName(name);
            proposalContestPhaseAttribute.setAdditionalId(aditionalId);
            proposalContestPhaseAttribute.setNumericValue(numericValue);
            proposalContestPhaseAttribute.setStringValue(stringValue);
            proposalContestPhaseAttribute.setContestPhaseId(contestPhaseId);
            proposalContestPhaseAttribute = createProposalContestPhaseAttribute(proposalContestPhaseAttribute);
            return proposalContestPhaseAttribute;
        }
    }

    public static boolean updateProposalContestPhaseAttribute(ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return proposalContestPhaseAttributeResource.update(proposalContestPhaseAttribute, proposalContestPhaseAttribute.getId_())
                .execute();
    }
    public static  Boolean deleteProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId, String name) {
        ProposalContestPhaseAttribute pcpa = getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
        return  proposalContestPhaseAttributeResource.delete(pcpa.getId_()).execute();
    }
}
