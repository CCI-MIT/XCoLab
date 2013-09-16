package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal version local service. This utility wraps {@link com.ext.portlet.service.impl.ProposalVersionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVersionLocalService
 * @see com.ext.portlet.service.base.ProposalVersionLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalVersionLocalServiceImpl
 * @generated
 */
public class ProposalVersionLocalServiceUtil {
    private static ProposalVersionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalVersionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the proposal version to the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVersion the proposal version
    * @return the proposal version that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion addProposalVersion(
        com.ext.portlet.model.ProposalVersion proposalVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addProposalVersion(proposalVersion);
    }

    /**
    * Creates a new proposal version with the primary key. Does not add the proposal version to the database.
    *
    * @param proposalVersionPK the primary key for the new proposal version
    * @return the new proposal version
    */
    public static com.ext.portlet.model.ProposalVersion createProposalVersion(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK) {
        return getService().createProposalVersion(proposalVersionPK);
    }

    /**
    * Deletes the proposal version with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVersionPK the primary key of the proposal version
    * @throws PortalException if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteProposalVersion(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteProposalVersion(proposalVersionPK);
    }

    /**
    * Deletes the proposal version from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVersion the proposal version
    * @throws SystemException if a system exception occurred
    */
    public static void deleteProposalVersion(
        com.ext.portlet.model.ProposalVersion proposalVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteProposalVersion(proposalVersion);
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

    public static com.ext.portlet.model.ProposalVersion fetchProposalVersion(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchProposalVersion(proposalVersionPK);
    }

    /**
    * Returns the proposal version with the primary key.
    *
    * @param proposalVersionPK the primary key of the proposal version
    * @return the proposal version
    * @throws PortalException if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion getProposalVersion(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalVersion(proposalVersionPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal versions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal versions
    * @param end the upper bound of the range of proposal versions (not inclusive)
    * @return the range of proposal versions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVersion> getProposalVersions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalVersions(start, end);
    }

    /**
    * Returns the number of proposal versions.
    *
    * @return the number of proposal versions
    * @throws SystemException if a system exception occurred
    */
    public static int getProposalVersionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalVersionsCount();
    }

    /**
    * Updates the proposal version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalVersion the proposal version
    * @return the proposal version that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion updateProposalVersion(
        com.ext.portlet.model.ProposalVersion proposalVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateProposalVersion(proposalVersion);
    }

    /**
    * Updates the proposal version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalVersion the proposal version
    * @param merge whether to merge the proposal version with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the proposal version that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion updateProposalVersion(
        com.ext.portlet.model.ProposalVersion proposalVersion, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateProposalVersion(proposalVersion, merge);
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

    public static ProposalVersionLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalVersionLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ProposalVersionLocalService.class.getName(),
                    portletClassLoader);

            _service = new ProposalVersionLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ProposalVersionLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ProposalVersionLocalService.class);
        }

        return _service;
    }

    public void setService(ProposalVersionLocalService service) {
        MethodCache.remove(ProposalVersionLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ProposalVersionLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ProposalVersionLocalService.class);
    }
}
