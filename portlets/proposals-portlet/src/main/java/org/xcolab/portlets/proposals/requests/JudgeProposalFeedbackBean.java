package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.AutoPopulatingList;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingTypeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingWrapper;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
