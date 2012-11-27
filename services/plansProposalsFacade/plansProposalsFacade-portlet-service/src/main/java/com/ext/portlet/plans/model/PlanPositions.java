package com.ext.portlet.plans.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the PlanPositions service. Represents a row in the &quot;Plans_PlanPositions&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionsModel
 * @see com.ext.portlet.plans.model.impl.PlanPositionsImpl
 * @see com.ext.portlet.plans.model.impl.PlanPositionsModelImpl
 * @generated
 */
public interface PlanPositions extends PlanPositionsModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.plans.model.impl.PlanPositionsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.util.List<java.lang.Long> getPositionsIds()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setPositionsIds(java.util.List<java.lang.Long> positionsIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.model.User getUpdateAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;
}
