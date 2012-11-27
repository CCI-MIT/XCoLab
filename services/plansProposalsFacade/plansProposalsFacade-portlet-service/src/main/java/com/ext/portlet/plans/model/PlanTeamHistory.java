package com.ext.portlet.plans.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the PlanTeamHistory service. Represents a row in the &quot;Plans_PlanTeamHistory&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTeamHistoryModel
 * @see com.ext.portlet.plans.model.impl.PlanTeamHistoryImpl
 * @see com.ext.portlet.plans.model.impl.PlanTeamHistoryModelImpl
 * @generated
 */
public interface PlanTeamHistory extends PlanTeamHistoryModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.plans.model.impl.PlanTeamHistoryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.model.User getUser()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanItem getPlan()
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;
}
