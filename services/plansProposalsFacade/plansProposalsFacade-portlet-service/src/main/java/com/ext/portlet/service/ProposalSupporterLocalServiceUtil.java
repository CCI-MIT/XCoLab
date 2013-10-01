package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal supporter local service. This utility wraps {@link com.ext.portlet.service.impl.ProposalSupporterLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalSupporterLocalService
 * @see com.ext.portlet.service.base.ProposalSupporterLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalSupporterLocalServiceImpl
 * @generated
 */
public class ProposalSupporterLocalServiceUtil {
    private static ProposalSupporterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalSupporterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the proposal supporter to the database. Also notifies the appropriate model listeners.
    *
    * @param proposalSupporter the proposal supporter
    * @return the proposal supporter that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter addProposalSupporter(
        com.ext.portlet.model.ProposalSupporter proposalSupporter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addProposalSupporter(proposalSupporter);
    }

    /**
    * Creates a new proposal supporter with the primary key. Does not add the proposal supporter to the database.
    *
    * @param proposalSupporterPK the primary key for the new proposal supporter
    * @return the new proposal supporter
    */
    public static com.ext.portlet.model.ProposalSupporter createProposalSupporter(
        com.ext.portlet.service.persistence.ProposalSupporterPK proposalSupporterPK) {
        return getService().createProposalSupporter(proposalSupporterPK);
    }

    /**
    * Deletes the proposal supporter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalSupporterPK the primary key of the proposal supporter
    * @throws PortalException if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteProposalSupporter(
        com.ext.portlet.service.persistence.ProposalSupporterPK proposalSupporterPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteProposalSupporter(proposalSupporterPK);
    }

    /**
    * Deletes the proposal supporter from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalSupporter the proposal supporter
    * @throws SystemException if a system exception occurred
    */
    public static void deleteProposalSupporter(
        com.ext.portlet.model.ProposalSupporter proposalSupporter)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteProposalSupporter(proposalSupporter);
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

    public static com.ext.portlet.model.ProposalSupporter fetchProposalSupporter(
        com.ext.portlet.service.persistence.ProposalSupporterPK proposalSupporterPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchProposalSupporter(proposalSupporterPK);
    }

    /**
    * Returns the proposal supporter with the primary key.
    *
    * @param proposalSupporterPK the primary key of the proposal supporter
    * @return the proposal supporter
    * @throws PortalException if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter getProposalSupporter(
        com.ext.portlet.service.persistence.ProposalSupporterPK proposalSupporterPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalSupporter(proposalSupporterPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal supporters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @return the range of proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalSupporter> getProposalSupporters(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalSupporters(start, end);
    }

    /**
    * Returns the number of proposal supporters.
    *
    * @return the number of proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static int getProposalSupportersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalSupportersCount();
    }

    /**
    * Updates the proposal supporter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalSupporter the proposal supporter
    * @return the proposal supporter that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter updateProposalSupporter(
        com.ext.portlet.model.ProposalSupporter proposalSupporter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateProposalSupporter(proposalSupporter);
    }

    /**
    * Updates the proposal supporter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalSupporter the proposal supporter
    * @param merge whether to merge the proposal supporter with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the proposal supporter that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter updateProposalSupporter(
        com.ext.portlet.model.ProposalSupporter proposalSupporter, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateProposalSupporter(proposalSupporter, merge);
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

    public static com.ext.portlet.model.ProposalSupporter create(
        long proposalID, long userID) {
        return getService().create(proposalID, userID);
    }

    public static void clearService() {
        _service = null;
    }

    public static ProposalSupporterLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalSupporterLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ProposalSupporterLocalService.class.getName(),
                    portletClassLoader);

            _service = new ProposalSupporterLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ProposalSupporterLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ProposalSupporterLocalService.class);
        }

        return _service;
    }

    public void setService(ProposalSupporterLocalService service) {
        MethodCache.remove(ProposalSupporterLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ProposalSupporterLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ProposalSupporterLocalService.class);
    }
}
