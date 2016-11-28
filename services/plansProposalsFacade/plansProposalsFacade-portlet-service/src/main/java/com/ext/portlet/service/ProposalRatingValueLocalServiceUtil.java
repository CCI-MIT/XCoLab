package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ProposalRatingValue. This utility wraps
 * {@link com.ext.portlet.service.impl.ProposalRatingValueLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingValueLocalService
 * @see com.ext.portlet.service.base.ProposalRatingValueLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalRatingValueLocalServiceImpl
 * @generated
 */
public class ProposalRatingValueLocalServiceUtil {
    private static ProposalRatingValueLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalRatingValueLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the proposal rating value to the database. Also notifies the appropriate model listeners.
    *
    * @param proposalRatingValue the proposal rating value
    * @return the proposal rating value that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalRatingValue addProposalRatingValue(
        com.ext.portlet.model.ProposalRatingValue proposalRatingValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addProposalRatingValue(proposalRatingValue);
    }

    /**
    * Creates a new proposal rating value with the primary key. Does not add the proposal rating value to the database.
    *
    * @param id the primary key for the new proposal rating value
    * @return the new proposal rating value
    */
    public static com.ext.portlet.model.ProposalRatingValue createProposalRatingValue(
        long id) {
        return getService().createProposalRatingValue(id);
    }

    /**
    * Deletes the proposal rating value with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal rating value
    * @return the proposal rating value that was removed
    * @throws PortalException if a proposal rating value with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalRatingValue deleteProposalRatingValue(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteProposalRatingValue(id);
    }

    /**
    * Deletes the proposal rating value from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalRatingValue the proposal rating value
    * @return the proposal rating value that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalRatingValue deleteProposalRatingValue(
        com.ext.portlet.model.ProposalRatingValue proposalRatingValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteProposalRatingValue(proposalRatingValue);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.ProposalRatingValue fetchProposalRatingValue(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchProposalRatingValue(id);
    }

    /**
    * Returns the proposal rating value with the primary key.
    *
    * @param id the primary key of the proposal rating value
    * @return the proposal rating value
    * @throws PortalException if a proposal rating value with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalRatingValue getProposalRatingValue(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalRatingValue(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal rating values.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal rating values
    * @param end the upper bound of the range of proposal rating values (not inclusive)
    * @return the range of proposal rating values
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalRatingValue> getProposalRatingValues(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalRatingValues(start, end);
    }

    /**
    * Returns the number of proposal rating values.
    *
    * @return the number of proposal rating values
    * @throws SystemException if a system exception occurred
    */
    public static int getProposalRatingValuesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalRatingValuesCount();
    }

    /**
    * Updates the proposal rating value in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalRatingValue the proposal rating value
    * @return the proposal rating value that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalRatingValue updateProposalRatingValue(
        com.ext.portlet.model.ProposalRatingValue proposalRatingValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateProposalRatingValue(proposalRatingValue);
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

    public static void clearService() {
        _service = null;
    }

    public static ProposalRatingValueLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalRatingValueLocalService.class.getName());

            if (invokableLocalService instanceof ProposalRatingValueLocalService) {
                _service = (ProposalRatingValueLocalService) invokableLocalService;
            } else {
                _service = new ProposalRatingValueLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ProposalRatingValueLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ProposalRatingValueLocalService service) {
    }
}
