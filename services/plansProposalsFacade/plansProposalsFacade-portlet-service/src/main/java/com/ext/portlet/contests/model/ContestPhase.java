package com.ext.portlet.contests.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the ContestPhase service. Represents a row in the &quot;Contests_ContestPhase&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseModel
 * @see com.ext.portlet.contests.model.impl.ContestPhaseImpl
 * @see com.ext.portlet.contests.model.impl.ContestPhaseModelImpl
 * @generated
 */
public interface ContestPhase extends ContestPhaseModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.contests.model.impl.ContestPhaseImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public com.ext.portlet.contests.model.Contest getContest()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.contests.model.ContestStatus getContestStatus();

    public java.util.List<java.lang.String> getPhaseColumns()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> getPhaseColumnsRaw()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> getPreviousPhases()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.contests.model.ContestPhase getNextContestPhase()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public boolean getPhaseActive();
}
