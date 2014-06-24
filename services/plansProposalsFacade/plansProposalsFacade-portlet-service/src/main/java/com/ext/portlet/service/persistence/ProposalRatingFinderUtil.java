package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class ProposalRatingFinderUtil {
    private static ProposalRatingFinder _finder;

    public static java.util.List<com.ext.portlet.model.ProposalRating> findByProposalIdJudgeType(
        long proposalId, int judgeType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .findByProposalIdJudgeType(proposalId, judgeType, start, end);
    }

    public static java.util.List<com.ext.portlet.model.ProposalRating> findByProposalIdJudgeTypeJudgeIdContestPhaseId(
        long proposalId, int judgeType, long judgeId, long contestPhaseId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .findByProposalIdJudgeTypeJudgeIdContestPhaseId(proposalId,
            judgeType, judgeId, contestPhaseId, start, end);
    }

    public static ProposalRatingFinder getFinder() {
        if (_finder == null) {
            _finder = (ProposalRatingFinder) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalRatingFinder.class.getName());

            ReferenceRegistry.registerReference(ProposalRatingFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ProposalRatingFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ProposalRatingFinderUtil.class,
            "_finder");
    }
}
