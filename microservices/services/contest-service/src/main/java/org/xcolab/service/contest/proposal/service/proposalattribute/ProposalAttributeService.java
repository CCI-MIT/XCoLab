package org.xcolab.service.contest.proposal.service.proposalattribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalVersionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.contest.proposal.domain.proposalattribute.ProposalAttributeDao;
import org.xcolab.service.contest.proposal.domain.proposalunversionedattribute.ProposalUnversionedAttributeDao;
import org.xcolab.service.contest.proposal.domain.proposalversion.ProposalVersionDao;

import java.util.List;

@Service
public class ProposalAttributeService {

    public static final int LATEST_VERSION = Integer.MAX_VALUE;

    private final ProposalAttributeDao proposalAttributeDao;
    private final ProposalUnversionedAttributeDao proposalUnversionedAttributeDao;
    private final ProposalDao proposalDao;
    private final ProposalVersionDao proposalVersionDao;

    @Autowired
    public ProposalAttributeService(ProposalDao proposalDao,
            ProposalAttributeDao proposalAttributeDao,
            ProposalUnversionedAttributeDao proposalUnversionedAttributeDao,
            ProposalVersionDao proposalVersionDao) {
        this.proposalAttributeDao = proposalAttributeDao;
        this.proposalDao = proposalDao;
        this.proposalUnversionedAttributeDao = proposalUnversionedAttributeDao;
        this.proposalVersionDao = proposalVersionDao;
    }

    public ProposalAttributeHelper getProposalAttributeHelper(long proposalId, int version) {
        ProposalAttributeHelperData data = getProposalAttributeHelperData(proposalId, version);
        return new ProposalAttributeHelper(data);
    }

    public ProposalAttributeHelperData getProposalAttributeHelperData(long proposalId,
            int version) {
        final List<ProposalAttribute> attributes = proposalAttributeDao.findByGiven(proposalId,
                null, null, version);
        return new ProposalAttributeHelperData(attributes);
    }

    public ProposalUnversionedAttributeHelperData getProposalUnversionedAttributeHelperData(
            long proposalId) {
        final List<ProposalUnversionedAttribute> attributes =
                proposalUnversionedAttributeDao.findByGiven(proposalId);
        return new ProposalUnversionedAttributeHelperData(attributes);
    }

    public ProposalAttribute setAttribute(ProposalAttribute proposalAttribute, Long authorUserId) {
        try {
            ProposalWrapper proposal = proposalDao.get(proposalAttribute.getProposalId());

            int currentVersion = proposalVersionDao.findMaxVersion(proposalAttribute.getProposalId());
            Integer version = proposalAttribute.getVersion();
            boolean isNewVersion = false;
            if (version == null || version < currentVersion) {
                version = currentVersion + 1;
                isNewVersion = true;
            }

            List<ProposalAttribute> currentProposalAttributes =
                    proposalAttributeDao.findByGiven(proposal.getId(),
                            null, null, currentVersion);

            // for each attribute, if it isn't the one that we are changing, simply
            // update it to the most recent version
            // if it is the one that we are changing then leave old one as it is and
            // create new one for new proposal version
            // TODO COLAB-2414: Fix the update detection to only update updated attributes.
            // for (ProposalAttribute attribute : currentProposalAttributes) {
            //     ProposalAttributeDetectUpdateAlgorithm updateAlgorithm =
            //             new ProposalAttributeDetectUpdateAlgorithm(attribute);
            //     if (!updateAlgorithm.hasBeenUpdated(proposalAttribute.getName(),
            //             zeroIfNull(proposalAttribute.getAdditionalId()),
            //             zeroIfNull(proposalAttribute.getNumericValue()),
            //             zeroIfNull(proposalAttribute.getRealValue()))) {
            //         // clone the attribute and set its version to the new value
            //         attribute.setVersion(version);
            //         proposalAttributeDao.update(attribute);
            //     } else {
            //     }
            // }

            // set new value for provided attribute
            proposalAttribute.setVersion(version);
            ProposalAttribute attribute = proposalAttributeDao
                    .create(proposalAttribute);//setAttributeValue(proposalId, newVersion,
            // attributeName, additionalId, stringValue, numericValue, realValue);

            // create newly created version descriptor
            if (isNewVersion) {
                createProposalVersionDescription(authorUserId, proposalAttribute.getProposalId(),
                        version, proposalAttribute.getName(), proposalAttribute.getAdditionalId());
            }
            proposalDao.update(proposal);

            // Update the proposal name in the discussion category
            if (proposalAttribute.getName().equals(ProposalAttributeKeys.NAME)) {
                try {
                    IThread thread = StaticCommentContext.getThreadClient()
                            .getThread(proposal.getDiscussionId());

                    thread.setTitle(
                            String.format("%s %s", getProposalNameFromOldTitle(thread.getTitle()),
                                    proposalAttribute.getStringValue()));
                    StaticCommentContext.getThreadClient().updateThread(thread.getId(), thread);
                } catch (ThreadNotFoundException  ignored) {
                }
            }

            return attribute;
        } catch (NotFoundException ignored) {
            return null;
        }
    }

    private static String getProposalNameFromOldTitle(String oldTitle) {
        if (oldTitle != null) {
            String[] threadTokens = oldTitle.split(" ");
            if (threadTokens.length > 1) {
                return threadTokens[0];
            }
        }
        return " ";
    }

    private static Double zeroIfNull(Double val) {
        if (val == null) {
            return 0.0;
        } else {
            return val;
        }
    }

    private static Long zeroIfNull(Long val) {
        if (val == null) {
            return 0L;
        } else {
            return val;
        }
    }

    private void createProposalVersionDescription(long authorUserId, long proposalId, int version,
            String updateType, long additionalId) {
        ProposalVersionWrapper proposalVersion = new ProposalVersionWrapper();
        proposalVersion.setProposalId(proposalId);
        proposalVersion.setVersion(version);
        proposalVersion.setAuthorUserId(authorUserId);
        proposalVersion.setUpdateType(updateType);
        proposalVersion.setUpdateAdditionalId(additionalId);

        proposalVersionDao.create(proposalVersion);
    }
}
