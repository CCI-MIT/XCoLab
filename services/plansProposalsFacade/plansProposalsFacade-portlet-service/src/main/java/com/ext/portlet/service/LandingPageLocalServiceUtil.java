package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the landing page local service. This utility wraps {@link com.ext.portlet.service.impl.LandingPageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LandingPageLocalService
 * @see com.ext.portlet.service.base.LandingPageLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.LandingPageLocalServiceImpl
 * @generated
 */
public class LandingPageLocalServiceUtil {
    private static LandingPageLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.LandingPageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the landing page to the database. Also notifies the appropriate model listeners.
    *
    * @param landingPage the landing page
    * @return the landing page that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.LandingPage addLandingPage(
        com.ext.portlet.model.LandingPage landingPage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addLandingPage(landingPage);
    }

    /**
    * Creates a new landing page with the primary key. Does not add the landing page to the database.
    *
    * @param id the primary key for the new landing page
    * @return the new landing page
    */
    public static com.ext.portlet.model.LandingPage createLandingPage(long id) {
        return getService().createLandingPage(id);
    }

    /**
    * Deletes the landing page with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the landing page
    * @throws PortalException if a landing page with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteLandingPage(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteLandingPage(id);
    }

    /**
    * Deletes the landing page from the database. Also notifies the appropriate model listeners.
    *
    * @param landingPage the landing page
    * @throws SystemException if a system exception occurred
    */
    public static void deleteLandingPage(
        com.ext.portlet.model.LandingPage landingPage)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteLandingPage(landingPage);
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

    public static com.ext.portlet.model.LandingPage fetchLandingPage(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchLandingPage(id);
    }

    /**
    * Returns the landing page with the primary key.
    *
    * @param id the primary key of the landing page
    * @return the landing page
    * @throws PortalException if a landing page with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.LandingPage getLandingPage(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getLandingPage(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the landing pages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of landing pages
    * @param end the upper bound of the range of landing pages (not inclusive)
    * @return the range of landing pages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.LandingPage> getLandingPages(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getLandingPages(start, end);
    }

    /**
    * Returns the number of landing pages.
    *
    * @return the number of landing pages
    * @throws SystemException if a system exception occurred
    */
    public static int getLandingPagesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getLandingPagesCount();
    }

    /**
    * Updates the landing page in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param landingPage the landing page
    * @return the landing page that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.LandingPage updateLandingPage(
        com.ext.portlet.model.LandingPage landingPage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateLandingPage(landingPage);
    }

    /**
    * Updates the landing page in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param landingPage the landing page
    * @param merge whether to merge the landing page with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the landing page that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.LandingPage updateLandingPage(
        com.ext.portlet.model.LandingPage landingPage, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateLandingPage(landingPage, merge);
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

    public static com.ext.portlet.model.LandingPage createNewLandingPage(
        java.lang.String baseUrl, java.lang.String targetUrl)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewLandingPage(baseUrl, targetUrl);
    }

    public static void store(com.ext.portlet.model.LandingPage landingPage)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(landingPage);
    }

    public static void clearService() {
        _service = null;
    }

    public static LandingPageLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    LandingPageLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    LandingPageLocalService.class.getName(), portletClassLoader);

            _service = new LandingPageLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(LandingPageLocalServiceUtil.class,
                "_service");
            MethodCache.remove(LandingPageLocalService.class);
        }

        return _service;
    }

    public void setService(LandingPageLocalService service) {
        MethodCache.remove(LandingPageLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(LandingPageLocalServiceUtil.class,
            "_service");
        MethodCache.remove(LandingPageLocalService.class);
    }
}
