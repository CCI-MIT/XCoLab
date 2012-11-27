package com.ext.portlet.contests.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the Contest service. Represents a row in the &quot;Contests_Contest&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ContestModel
 * @see com.ext.portlet.contests.model.impl.ContestImpl
 * @see com.ext.portlet.contests.model.impl.ContestModelImpl
 * @generated
 */
public interface Contest extends ContestModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.contests.model.impl.ContestImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.util.List<com.ext.portlet.contests.model.ContestPhase> getPhases()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanType getPlanType()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> getActivePhases()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.contests.model.ContestPhase getActivePhase()
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    public boolean isActive()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<java.lang.Long> getDebatesIds()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Integer getTotalVotes()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void updateDefaultPlanDescription(java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanTemplate getPlanTemplate()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.ontology.model.FocusArea getFocusArea()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.model.Image getLogo()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setLogo(java.io.File logoFile)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException;

    public java.lang.String getLogoPath()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public long getProposalsCount()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getDiscussionCategoryGroup()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public long getCommentsCount()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public long getProposalsCommentsCount()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public long getTotalComments()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestTeamMember> getTeamMembers()
        throws com.liferay.portal.kernel.exception.SystemException;
}
