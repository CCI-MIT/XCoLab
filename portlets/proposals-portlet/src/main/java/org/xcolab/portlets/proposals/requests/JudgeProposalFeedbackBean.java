package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;

/**
 * @author pdeboer
 */
public class JudgeProposalFeedbackBean extends RatingBean {

    public JudgeProposalFeedbackBean(ProposalJudgeWrapper wrapper) throws PortalException, SystemException {
        super(wrapper, ProposalRatingTypeLocalServiceUtil.getRatingTypesForJudges());
    }

    public JudgeProposalFeedbackBean() {
    }

}
