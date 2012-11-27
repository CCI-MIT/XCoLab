package com.ext.portlet.plans.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the PlanVote service. Represents a row in the &quot;Plans_PlanVote&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PlanVoteModel
 * @see com.ext.portlet.plans.model.impl.PlanVoteImpl
 * @see com.ext.portlet.plans.model.impl.PlanVoteModelImpl
 * @generated
 */
public interface PlanVote extends PlanVoteModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.plans.model.impl.PlanVoteImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;
}
