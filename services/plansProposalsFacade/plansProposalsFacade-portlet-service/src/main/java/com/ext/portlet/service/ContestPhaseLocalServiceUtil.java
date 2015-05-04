package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ContestPhase. This utility wraps
 * {@link com.ext.portlet.service.impl.ContestPhaseLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseLocalService
 * @see com.ext.portlet.service.base.ContestPhaseLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ContestPhaseLocalServiceImpl
 * @generated
 */
public class ContestPhaseLocalServiceUtil {
    private static ContestPhaseLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ContestPhaseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the contest phase to the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhase the contest phase
    * @return the contest phase that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase addContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addContestPhase(contestPhase);
    }

    /**
    * Creates a new contest phase with the primary key. Does not add the contest phase to the database.
    *
    * @param ContestPhasePK the primary key for the new contest phase
    * @return the new contest phase
    */
    public static com.ext.portlet.model.ContestPhase createContestPhase(
        long ContestPhasePK) {
        return getService().createContestPhase(ContestPhasePK);
    }

    /**
    * Deletes the contest phase with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase that was removed
    * @throws PortalException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase deleteContestPhase(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteContestPhase(ContestPhasePK);
    }

    /**
    * Deletes the contest phase from the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhase the contest phase
    * @return the contest phase that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase deleteContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteContestPhase(contestPhase);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.ContestPhase fetchContestPhase(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchContestPhase(ContestPhasePK);
    }

    /**
    * Returns the contest phase with the primary key.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase
    * @throws PortalException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase getContestPhase(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhase(ContestPhasePK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> getContestPhases(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhases(start, end);
    }

    /**
    * Returns the number of contest phases.
    *
    * @return the number of contest phases
    * @throws SystemException if a system exception occurred
    */
    public static int getContestPhasesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhasesCount();
    }

    /**
    * Updates the contest phase in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhase the contest phase
    * @return the contest phase that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase updateContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestPhase(contestPhase);
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

    /**
    * This can be used by unit tests to set a different clock than the standard one
    */
    public static void overrideClock(org.xcolab.utils.Clock clock) {
        getService().overrideClock(clock);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlans(contestPhase);
    }

    public static com.ext.portlet.contests.ContestStatus getContestStatus(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestStatus(contestPhase);
    }

    public static java.lang.String getContestStatusStr(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestStatusStr(contestPhase);
    }

    public static java.util.List<java.lang.String> getPhaseColumns(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPhaseColumns(contestPhase);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhaseColumn> getPhaseColumnsRaw(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPhaseColumnsRaw(contestPhase);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhase> getPreviousPhases(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPreviousPhases(contestPhase);
    }

    public static com.ext.portlet.model.ContestPhase getNextContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getNextContestPhase(contestPhase);
    }

    public static boolean getPhaseActive(
        com.ext.portlet.model.ContestPhase contestPhase) {
        return getService().getPhaseActive(contestPhase);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhase> getPhasesForContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPhasesForContest(contest);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhase> getPhasesForContest(
        long contestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPhasesForContest(contestPK);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhase> getPhasesForContestScheduleId(
        long contestScheduleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPhasesForContestScheduleId(contestScheduleId);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhase> getPhasesForContestScheduleIdAndContest(
        long contestScheduleId, long contestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPhasesForContestScheduleIdAndContest(contestScheduleId,
            contestPK);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhase> getPhasesForContestScheduleIdAndPhaseType(
        long contestScheduleId, long contestPhaseType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPhasesForContestScheduleIdAndPhaseType(contestScheduleId,
            contestPhaseType);
    }

    public static com.ext.portlet.model.ContestPhase getActivePhaseForContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getActivePhaseForContest(contest);
    }

    /**
    * from ContestPhaseImpl *
    */
    public static com.ext.portlet.model.Contest getContest(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContest(contestPhase);
    }

    public static java.lang.String getName(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getName(contestPhase);
    }

    public static void promoteProposal(long proposalId, long nextPhaseId,
        long currentPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().promoteProposal(proposalId, nextPhaseId, currentPhaseId);
    }

    /**
    * Method responsible for autopromotion of proposals between phases.
    *
    * @throws SystemException
    * @throws PortalException
    */
    public static void autoPromoteProposals()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().autoPromoteProposals();
    }

    /**
    * Creates a new contest phase object by copying all attributes of the original contest phase
    *
    * @param originalPhase     The contest phase to copy
    * @return
    */
    public static com.ext.portlet.model.ContestPhase createFromContestPhase(
        com.ext.portlet.model.ContestPhase originalPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createFromContestPhase(originalPhase);
    }

    public static void forcePromotionOfProposalInPhase(
        com.ext.portlet.model.Proposal p,
        com.ext.portlet.model.ContestPhase phase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().forcePromotionOfProposalInPhase(p, phase);
    }

    public static int getNumberOfProposalsForJudge(
        com.liferay.portal.model.User judge,
        com.ext.portlet.model.ContestPhase phase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getNumberOfProposalsForJudge(judge, phase);
    }

    public static void clearService() {
        _service = null;
    }

    public static ContestPhaseLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestPhaseLocalService.class.getName());

            if (invokableLocalService instanceof ContestPhaseLocalService) {
                _service = (ContestPhaseLocalService) invokableLocalService;
            } else {
                _service = new ContestPhaseLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ContestPhaseLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ContestPhaseLocalService service) {
    }
}
