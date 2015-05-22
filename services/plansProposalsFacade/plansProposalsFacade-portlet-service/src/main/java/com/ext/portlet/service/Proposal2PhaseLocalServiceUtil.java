package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Proposal2Phase. This utility wraps
 * {@link com.ext.portlet.service.impl.Proposal2PhaseLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see Proposal2PhaseLocalService
 * @see com.ext.portlet.service.base.Proposal2PhaseLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.Proposal2PhaseLocalServiceImpl
 * @generated
 */
public class Proposal2PhaseLocalServiceUtil {
    private static Proposal2PhaseLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.Proposal2PhaseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the proposal2 phase to the database. Also notifies the appropriate model listeners.
    *
    * @param proposal2Phase the proposal2 phase
    * @return the proposal2 phase that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal2Phase addProposal2Phase(
        com.ext.portlet.model.Proposal2Phase proposal2Phase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addProposal2Phase(proposal2Phase);
    }

    /**
    * Creates a new proposal2 phase with the primary key. Does not add the proposal2 phase to the database.
    *
    * @param proposal2PhasePK the primary key for the new proposal2 phase
    * @return the new proposal2 phase
    */
    public static com.ext.portlet.model.Proposal2Phase createProposal2Phase(
        com.ext.portlet.service.persistence.Proposal2PhasePK proposal2PhasePK) {
        return getService().createProposal2Phase(proposal2PhasePK);
    }

    /**
    * Deletes the proposal2 phase with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposal2PhasePK the primary key of the proposal2 phase
    * @return the proposal2 phase that was removed
    * @throws PortalException if a proposal2 phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal2Phase deleteProposal2Phase(
        com.ext.portlet.service.persistence.Proposal2PhasePK proposal2PhasePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteProposal2Phase(proposal2PhasePK);
    }

    /**
    * Deletes the proposal2 phase from the database. Also notifies the appropriate model listeners.
    *
    * @param proposal2Phase the proposal2 phase
    * @return the proposal2 phase that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal2Phase deleteProposal2Phase(
        com.ext.portlet.model.Proposal2Phase proposal2Phase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteProposal2Phase(proposal2Phase);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.Proposal2Phase fetchProposal2Phase(
        com.ext.portlet.service.persistence.Proposal2PhasePK proposal2PhasePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchProposal2Phase(proposal2PhasePK);
    }

    /**
    * Returns the proposal2 phase with the primary key.
    *
    * @param proposal2PhasePK the primary key of the proposal2 phase
    * @return the proposal2 phase
    * @throws PortalException if a proposal2 phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal2Phase getProposal2Phase(
        com.ext.portlet.service.persistence.Proposal2PhasePK proposal2PhasePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposal2Phase(proposal2PhasePK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal2 phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal2 phases
    * @param end the upper bound of the range of proposal2 phases (not inclusive)
    * @return the range of proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Proposal2Phase> getProposal2Phases(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposal2Phases(start, end);
    }

    /**
    * Returns the number of proposal2 phases.
    *
    * @return the number of proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public static int getProposal2PhasesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposal2PhasesCount();
    }

    /**
    * Updates the proposal2 phase in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposal2Phase the proposal2 phase
    * @return the proposal2 phase that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal2Phase updateProposal2Phase(
        com.ext.portlet.model.Proposal2Phase proposal2Phase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateProposal2Phase(proposal2Phase);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static com.ext.portlet.model.Proposal2Phase create(long proposalId,
        long contestPhaseId) {
        return getService().create(proposalId, contestPhaseId);
    }

    public static com.ext.portlet.model.Proposal2Phase create(long proposalId,
        long contestPhaseId, int versionFrom, int versionTo)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .create(proposalId, contestPhaseId, versionFrom, versionTo);
    }

    public static com.ext.portlet.model.Proposal2Phase getByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getByProposalIdContestPhaseId(proposalId, contestPhaseId);
    }

    public static java.util.List<com.ext.portlet.model.Proposal2Phase> getByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getByProposalId(proposalId);
    }

    public static int getLatestProposalVersionInActiveContest(
        java.lang.Long proposalId) throws java.lang.Exception {
        return getService().getLatestProposalVersionInActiveContest(proposalId);
    }

    public static com.ext.portlet.model.ContestPhase getLatestContestPhaseInContest(
        java.lang.Long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getLatestContestPhaseInContest(proposalId);
    }

    public static com.ext.portlet.model.Contest getCurrentContestForProposal(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCurrentContestForProposal(proposalId);
    }

    public static com.ext.portlet.model.Proposal2Phase getForVersion(
        java.lang.Long proposalId, int proposalVersionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getForVersion(proposalId, proposalVersionId);
    }

    public static com.ext.portlet.model.Proposal2Phase getForVersion(
        com.ext.portlet.model.ProposalVersion proposalVersion)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getForVersion(proposalVersion);
    }

    public static java.util.List<java.lang.Long> getContestPhasesForProposal(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhasesForProposal(proposalId);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhase> getActiveContestPhasesForProposal(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getActiveContestPhasesForProposal(proposalId);
    }

    public static java.util.List<com.ext.portlet.model.Proposal2Phase> getByContestPhaseId(
        long contestPhaseId) throws java.lang.Exception {
        return getService().getByContestPhaseId(contestPhaseId);
    }

    public static void clearService() {
        _service = null;
    }

    public static Proposal2PhaseLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    Proposal2PhaseLocalService.class.getName());

            if (invokableLocalService instanceof Proposal2PhaseLocalService) {
                _service = (Proposal2PhaseLocalService) invokableLocalService;
            } else {
                _service = new Proposal2PhaseLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(Proposal2PhaseLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(Proposal2PhaseLocalService service) {
    }
}
