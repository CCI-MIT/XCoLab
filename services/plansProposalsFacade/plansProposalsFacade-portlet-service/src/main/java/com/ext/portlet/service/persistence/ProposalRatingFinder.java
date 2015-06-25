package com.ext.portlet.service.persistence;

public interface ProposalRatingFinder {
    public java.util.List<com.ext.portlet.model.ProposalRating> findByProposalIdJudgeTypeContestPhaseId(
        long proposalId, int judgeType, long contestPhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.model.ProposalRating> findByProposalIdJudgeTypeJudgeIdContestPhaseId(
        long proposalId, int judgeType, long judgeId, long contestPhaseId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.model.ProposalRating> findByContestPhaseId(
        long contestPhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;
}
