package com.ext.portlet.service.impl;

import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.ext.portlet.service.base.ProposalRatingLocalServiceBaseImpl;
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

    public enum ProposalRatingType {
        JUDGE(1),
        FELLOW(2);

        private final int id;

        ProposalRatingType(int id) {
            this.id = id;
        }

        int getId() {
            return this.id;
        }
    }

    public List<ProposalRating> getAllRatingsForProposal(long proposalId) throws SystemException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ProposalRating.class)
                .add(PropertyFactoryUtil.forName("proposalId").eq(proposalId));
        query.addOrder(OrderFactoryUtil.asc("ratingType"));
        return dynamicQuery(query);
    }

    public ProposalRating getJudgeRatingForProposal(long judgeId, long proposalId, long contestPhaseId) throws SystemException {
        int ratingType = ProposalRatingType.JUDGE.getId();
        return this.getRatingForProposal(ratingType, judgeId, proposalId, contestPhaseId);
    }
    public ProposalRating getFellowRatingForProposal(long judgeId, long proposalId, long contestPhaseId) throws SystemException {
        int ratingType = ProposalRatingType.FELLOW.getId();
        return this.getRatingForProposal(ratingType, judgeId, proposalId, contestPhaseId);
    }


    protected ProposalRating getRatingForProposal(int ratingType, long judgeId, long proposalId, long contestPhaseId) throws SystemException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ProposalRating.class)
                .add(PropertyFactoryUtil.forName("userId").eq(judgeId))
                .add(PropertyFactoryUtil.forName("ratingType").eq(ratingType))
                .add(PropertyFactoryUtil.forName("proposalId").eq(proposalId))
                .add(PropertyFactoryUtil.forName("contestPhaseId").eq(contestPhaseId));
        List result = dynamicQuery(query);

        if (result.size() > 0) {
            return (ProposalRating)dynamicQuery(query).get(0);
        } else {
            return null;
        }
    }


    public ProposalRating addJudgeRating(
            long proposalId, long contestPhaseId, long judgeId,
            long rating, String comment, String otherDataString
    ) throws SystemException, NoSuchUserException {
        int ratingType = ProposalRatingType.JUDGE.getId();

        return this.addProposalRating(proposalId, contestPhaseId, judgeId, ratingType, rating, comment, otherDataString);
    }

    public ProposalRating addFellowRating(
            long proposalId, long contestPhaseId, long fellowId,
            long rating, String comment, String otherDataString
    ) throws SystemException, NoSuchUserException {
        int ratingType = ProposalRatingType.FELLOW.getId();

        return this.addProposalRating(proposalId, contestPhaseId, fellowId, ratingType, rating, comment, otherDataString);
    }

    public ProposalRating updateRating(
            long proposalRatingId, long rating, String comment, String otherDataString
    ) throws SystemException, NoSuchUserException {
        ProposalRating proposalRating = ProposalRatingLocalServiceUtil.fetchProposalRating(proposalRatingId);

        proposalRating.setRating(rating);
        proposalRating.setComment(comment);
        proposalRating.setOtherDataString(otherDataString);

        super.updateProposalRating(proposalRating);

        return proposalRating;
    }

    protected ProposalRating addProposalRating(
            long proposalId, long contestPhaseId, long userId, int ratingType,
            long rating, String comment, String otherDataString
    ) throws SystemException, NoSuchUserException {

        long proposalRatingId = counterLocalService.increment(ProposalRating.class.getName());

        ProposalRating proposalRating = proposalRatingPersistence.create(proposalRatingId);

        proposalRating.setProposalId(proposalId);
        proposalRating.setContestPhaseId(contestPhaseId);
        proposalRating.setUserId(userId);
        proposalRating.setRatingType(ratingType);
        proposalRating.setRating(rating);
        proposalRating.setComment(comment);
        proposalRating.setOtherDataString(otherDataString);

        super.addProposalRating(proposalRating);

        return proposalRating;
    }

}
