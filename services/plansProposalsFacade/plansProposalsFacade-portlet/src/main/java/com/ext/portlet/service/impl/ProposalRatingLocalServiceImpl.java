package com.ext.portlet.service.impl;

import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.proposals.ProposalJudgeType;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.ext.portlet.service.base.ProposalRatingLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalRatingFinderUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the proposal rating local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalRatingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Manuel Thurner
 * @see com.ext.portlet.service.base.ProposalRatingLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalRatingLocalServiceUtil
 */
public class ProposalRatingLocalServiceImpl
    extends ProposalRatingLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalRatingLocalServiceUtil} to access the proposal rating local service.
     */

    @Override
    public List<ProposalRating> getFellowRatingsForProposal(long proposalId, long contestPhaseId) throws SystemException {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.FELLOW.getId());
    }

    @Override
    public List<ProposalRating> getJudgeRatingsForProposal(long proposalId, long contestPhaseId) throws SystemException {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.JUDGE.getId());
    }

    protected List<ProposalRating> getRatingsForProposal(long proposalId, long contestPhaseId, int judgeType) throws SystemException {
        return ProposalRatingFinderUtil.findByProposalIdJudgeTypeContestPhaseId(proposalId, judgeType, contestPhaseId, 0, Integer.MAX_VALUE);
    }

    @Override
    public List<ProposalRating> getJudgeRatingsForProposalAndUser(long userId, long proposalId, long contestPhaseId) throws SystemException {
        return this.getRatingsForProposalAndUser(proposalId, ProposalJudgeType.JUDGE.getId(), userId, contestPhaseId);
    }
    @Override
    public List<ProposalRating> getFellowRatingForProposalAndUser(long userId, long proposalId, long contestPhaseId) throws SystemException {
        return this.getRatingsForProposalAndUser(proposalId, ProposalJudgeType.FELLOW.getId(), userId, contestPhaseId);
    }

    protected List<ProposalRating> getRatingsForProposalAndUser(long proposalId, int judgeType, long userId,  long contestPhaseId) throws SystemException {
        return ProposalRatingFinderUtil.findByProposalIdJudgeTypeJudgeIdContestPhaseId(proposalId, judgeType, userId, contestPhaseId, 0, Integer.MAX_VALUE);
    }


    @Override
    public ProposalRating updateRating(
            long proposalRatingId, long ratingValueId, String comment, String otherDataString
    ) throws SystemException, NoSuchUserException {
        ProposalRating proposalRating = ProposalRatingLocalServiceUtil.fetchProposalRating(proposalRatingId);

        proposalRating.setRatingValueId(ratingValueId);
        proposalRating.setComment(comment);
        proposalRating.setOtherDataString(otherDataString);

        updateProposalRating(proposalRating);

        return proposalRating;
    }

    @Override
    public ProposalRating addRating(
            long proposalId, long contestPhaseId, long userId, long ratingValueId, String comment, String otherDataString
    ) throws SystemException, NoSuchUserException {
        return addRating(proposalId, contestPhaseId, userId, ratingValueId, comment, otherDataString, false);
    }

        @Override
        public ProposalRating addRating(
            long proposalId, long contestPhaseId, long userId, long ratingValueId, String comment, String otherDataString, boolean onlyForInternalUsage
    ) throws SystemException, NoSuchUserException {


            long proposalRatingId = counterLocalService.increment(ProposalRating.class.getName());
            ProposalRating proposalRating = proposalRatingPersistence.create(proposalRatingId);
            proposalRating.setProposalId(proposalId);
            proposalRating.setContestPhaseId(contestPhaseId);
            proposalRating.setUserId(userId);
            proposalRating.setRatingValueId(ratingValueId);
            proposalRating.setComment(comment);
            proposalRating.setOnlyForInternalUsage(onlyForInternalUsage);

            if (comment != null && !comment.isEmpty()) {
                proposalRating.setCommentEnabled(true);
            }

            proposalRating.setOtherDataString(otherDataString);
            addProposalRating(proposalRating);
            return proposalRating;
    }

    @Override
    public ProposalRating updateRating(ProposalRating proposalRating) throws SystemException, NoSuchUserException {
        updateProposalRating(proposalRating);
        return proposalRating;
    }

}
