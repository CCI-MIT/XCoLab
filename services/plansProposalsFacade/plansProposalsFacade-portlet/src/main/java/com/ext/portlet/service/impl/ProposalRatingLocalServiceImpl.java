package com.ext.portlet.service.impl;

import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.proposals.ProposalJudgeType;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;
import com.ext.portlet.service.base.ProposalRatingLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalRatingFinderUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
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

    public List<ProposalRating> getFellowRatingsForProposal(long proposalId) throws SystemException {
        return getRatingsForProposal(proposalId, ProposalJudgeType.FELLOW.getId());
    }
    public List<ProposalRating> getJudgeRatingsForProposal(long proposalId) throws SystemException {
        return getRatingsForProposal(proposalId, ProposalJudgeType.JUDGE.getId());
    }

    protected List<ProposalRating> getRatingsForProposal(long proposalId, int judgeType) throws SystemException {
        return ProposalRatingFinderUtil.findByProposalIdJudgeType(proposalId, judgeType, 0, Integer.MAX_VALUE);
    }



    public List<ProposalRating> getJudgeRatingsForProposalAndUser(long userId, long proposalId, long contestPhaseId) throws SystemException {
        return this.getRatingsForProposalAndUser(proposalId, ProposalJudgeType.JUDGE.getId(), userId, contestPhaseId);
    }
    public List<ProposalRating> getFellowRatingForProposalAndUser(long userId, long proposalId, long contestPhaseId) throws SystemException {
        return this.getRatingsForProposalAndUser(proposalId, ProposalJudgeType.FELLOW.getId(), userId, contestPhaseId);
    }

    protected List<ProposalRating> getRatingsForProposalAndUser(long proposalId, int judgeType, long userId,  long contestPhaseId) throws SystemException {
        return ProposalRatingFinderUtil.findByProposalIdJudgeTypeJudgeIdContestPhaseId(proposalId, judgeType, userId, contestPhaseId, 0, Integer.MAX_VALUE);
    }


    public ProposalRating updateRating(
            long proposalRatingId, long ratingValueId, String comment, String otherDataString
    ) throws SystemException, NoSuchUserException {
        ProposalRating proposalRating = ProposalRatingLocalServiceUtil.fetchProposalRating(proposalRatingId);

        proposalRating.setRatingValueId(ratingValueId);
        proposalRating.setComment(comment);
        proposalRating.setOtherDataString(otherDataString);

        super.updateProposalRating(proposalRating);

        return proposalRating;
    }

    public ProposalRating addProposalRating(
            long proposalId, long contestPhaseId, long userId, long ratingValueId, String comment, String otherDataString
    ) throws SystemException, NoSuchUserException {

        long proposalRatingId = counterLocalService.increment(ProposalRating.class.getName());

        ProposalRating proposalRating = proposalRatingPersistence.create(proposalRatingId);

        proposalRating.setProposalId(proposalId);
        proposalRating.setContestPhaseId(contestPhaseId);
        proposalRating.setUserId(userId);
        proposalRating.setRatingValueId(ratingValueId);
        proposalRating.setComment(comment);
        proposalRating.setOtherDataString(otherDataString);

        super.addProposalRating(proposalRating);

        return proposalRating;
    }

}
