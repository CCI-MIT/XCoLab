package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model implementation for the PlanVote service. Represents a row in the &quot;plansProposalsFacade_PlanVote&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanVote} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanVoteImpl extends PlanVoteBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan vote model instance should use the {@link com.ext.portlet.plans.model.PlanVote} interface instead.
     */
    public PlanVoteImpl() {
    }

    public void store() throws SystemException {
        if (isNew()) {
            PlanVoteLocalServiceUtil.addPlanVote(this);
        }
        else {
            PlanVoteLocalServiceUtil.updatePlanVote(this);
        }
    }
}
