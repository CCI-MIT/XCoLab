package org.xcolab.service.proposal.service.proposalattribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.model.tables.pojos.ProposalVersion;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposalattribute.ProposalAttributeDao;
import org.xcolab.service.proposal.domain.proposalversion.ProposalVersionDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ProposalAttributeService {

    private final ProposalAttributeDao proposalAttributeDao;

    private final ProposalDao proposalDao;


    private final ProposalVersionDao proposalVersionDao;

    @Autowired
    public ProposalAttributeService(ProposalDao proposalDao,
            ProposalAttributeDao proposalAttributeDao, ProposalVersionDao proposalVersionDao) {
        this.proposalAttributeDao = proposalAttributeDao;
        this.proposalDao = proposalDao;
        this.proposalVersionDao = proposalVersionDao;
    }

    public ProposalAttribute setAttribute(ProposalAttribute proposalAttribute, Long authorId) {

        try {
            Proposal proposal = proposalDao.get(proposalAttribute.getProposalId());

            int currentVersion = proposal.getCurrentVersion();
            int newVersion = currentVersion + 1;

            List<ProposalAttribute> currentProposalAttributes =
                    proposalAttributeDao.findByGiven(proposal.getProposalId(),
                            null, null, currentVersion);

            // for each attribute, if it isn't the one that we are changing, simply
            // update it to the most recent version
            // if it is the one that we are changing then leave old one as it is and
            // create new one for new proposal version
            for (ProposalAttribute attribute : currentProposalAttributes) {
                ProposalAttributeDetectUpdateAlgorithm updateAlgorithm =
                        new ProposalAttributeDetectUpdateAlgorithm(attribute);
                if (!updateAlgorithm.hasBeenUpdated(proposalAttribute.getName(),
                        zeroIfNull(proposalAttribute.getAdditionalId()),
                        zeroIfNull(proposalAttribute.getNumericValue()),
                        zeroIfNull(proposalAttribute.getRealValue()))) {
                    // clone the attribute and set its version to the new value
                    attribute.setVersion(newVersion);
                    proposalAttributeDao.update(attribute);
                } else {
                }
            }

            // set new value for provided attribute
            proposalAttribute.setVersionWhenCreated(newVersion);
            proposalAttribute.setVersion(newVersion);
            ProposalAttribute attribute = proposalAttributeDao
                    .create(proposalAttribute);//setAttributeValue(proposalId, newVersion,
            // attributeName, additionalId, stringValue, numericValue, realValue);

            proposal.setCurrentVersion(newVersion);
            Timestamp updatedDate = new Timestamp((new Date()).getTime());
            proposal.setUpdatedDate(updatedDate);

            // create newly created version descriptor
            createProposalVersionDescription(authorId, proposalAttribute.getProposalId(),
                    newVersion, proposalAttribute.getName(), proposalAttribute.getAdditionalId(),
                    updatedDate);
            proposalDao.update(proposal);

            // Update the proposal name in the discussion category
            if (proposalAttribute.getName().equals(ProposalAttributeKeys.NAME)) {
                try {
                    CommentThread thread = ThreadClientUtil.getThread(proposal.getDiscussionId());

                    thread.setTitle(
                            String.format("%s %s", getProposalNameFromOldTitle(thread.getTitle()),
                                    proposalAttribute.getStringValue()));
                    ThreadClientUtil.updateThread(thread);
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
            String threadTokens[] = oldTitle.split(" ");
            if (threadTokens != null && threadTokens.length > 1) {
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
            return 0l;
        } else {
            return val;
        }
    }

    private void createProposalVersionDescription(long authorId, long proposalId, int version,
            String updateType,
            long additionalId, Timestamp updatedDate) {

        ProposalVersion proposalVersion = new ProposalVersion();
        proposalVersion.setProposalId(proposalId);
        proposalVersion.setVersion(version);
        proposalVersion.setAuthorId(authorId);
        proposalVersion.setUpdateType(updateType);
        proposalVersion.setUpdateAdditionalId(additionalId);
        proposalVersion.setCreateDate(updatedDate);

        proposalVersionDao.create(proposalVersion);
    }

}
