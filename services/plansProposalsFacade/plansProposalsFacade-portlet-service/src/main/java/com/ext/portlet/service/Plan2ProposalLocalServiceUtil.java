package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan2 proposal local service. This utility wraps {@link com.ext.portlet.service.impl.Plan2ProposalLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Plan2ProposalLocalService
 * @see com.ext.portlet.service.base.Plan2ProposalLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.Plan2ProposalLocalServiceImpl
 * @generated
 */
public class Plan2ProposalLocalServiceUtil {
    private static Plan2ProposalLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.Plan2ProposalLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan2 proposal to the database. Also notifies the appropriate model listeners.
    *
    * @param plan2Proposal the plan2 proposal
    * @return the plan2 proposal that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Plan2Proposal addPlan2Proposal(
        com.ext.portlet.model.Plan2Proposal plan2Proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlan2Proposal(plan2Proposal);
    }

    /**
    * Creates a new plan2 proposal with the primary key. Does not add the plan2 proposal to the database.
    *
    * @param planId the primary key for the new plan2 proposal
    * @return the new plan2 proposal
    */
    public static com.ext.portlet.model.Plan2Proposal createPlan2Proposal(
        long planId) {
        return getService().createPlan2Proposal(planId);
    }

    /**
    * Deletes the plan2 proposal with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planId the primary key of the plan2 proposal
    * @throws PortalException if a plan2 proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlan2Proposal(long planId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlan2Proposal(planId);
    }

    /**
    * Deletes the plan2 proposal from the database. Also notifies the appropriate model listeners.
    *
    * @param plan2Proposal the plan2 proposal
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlan2Proposal(
        com.ext.portlet.model.Plan2Proposal plan2Proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlan2Proposal(plan2Proposal);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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

    public static com.ext.portlet.model.Plan2Proposal fetchPlan2Proposal(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlan2Proposal(planId);
    }

    /**
    * Returns the plan2 proposal with the primary key.
    *
    * @param planId the primary key of the plan2 proposal
    * @return the plan2 proposal
    * @throws PortalException if a plan2 proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Plan2Proposal getPlan2Proposal(
        long planId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlan2Proposal(planId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan2 proposals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan2 proposals
    * @param end the upper bound of the range of plan2 proposals (not inclusive)
    * @return the range of plan2 proposals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Plan2Proposal> getPlan2Proposals(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlan2Proposals(start, end);
    }

    /**
    * Returns the number of plan2 proposals.
    *
    * @return the number of plan2 proposals
    * @throws SystemException if a system exception occurred
    */
    public static int getPlan2ProposalsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlan2ProposalsCount();
    }

    /**
    * Updates the plan2 proposal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plan2Proposal the plan2 proposal
    * @return the plan2 proposal that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Plan2Proposal updatePlan2Proposal(
        com.ext.portlet.model.Plan2Proposal plan2Proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlan2Proposal(plan2Proposal);
    }

    /**
    * Updates the plan2 proposal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plan2Proposal the plan2 proposal
    * @param merge whether to merge the plan2 proposal with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan2 proposal that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Plan2Proposal updatePlan2Proposal(
        com.ext.portlet.model.Plan2Proposal plan2Proposal, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlan2Proposal(plan2Proposal, merge);
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

    public static void clearService() {
        _service = null;
    }

    public static Plan2ProposalLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    Plan2ProposalLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    Plan2ProposalLocalService.class.getName(),
                    portletClassLoader);

            _service = new Plan2ProposalLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(Plan2ProposalLocalServiceUtil.class,
                "_service");
            MethodCache.remove(Plan2ProposalLocalService.class);
        }

        return _service;
    }

    public void setService(Plan2ProposalLocalService service) {
        MethodCache.remove(Plan2ProposalLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(Plan2ProposalLocalServiceUtil.class,
            "_service");
        MethodCache.remove(Plan2ProposalLocalService.class);
    }
}
