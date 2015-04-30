package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactTemplateFocusAreaList;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the impact template focus area list service. This utility wraps {@link ImpactTemplateFocusAreaListPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateFocusAreaListPersistence
 * @see ImpactTemplateFocusAreaListPersistenceImpl
 * @generated
 */
public class ImpactTemplateFocusAreaListUtil {
    private static ImpactTemplateFocusAreaListPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList) {
        getPersistence().clearCache(impactTemplateFocusAreaList);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ImpactTemplateFocusAreaList> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImpactTemplateFocusAreaList> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImpactTemplateFocusAreaList> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ImpactTemplateFocusAreaList update(
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList)
        throws SystemException {
        return getPersistence().update(impactTemplateFocusAreaList);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ImpactTemplateFocusAreaList update(
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(impactTemplateFocusAreaList, serviceContext);
    }

    /**
    * Caches the impact template focus area list in the entity cache if it is enabled.
    *
    * @param impactTemplateFocusAreaList the impact template focus area list
    */
    public static void cacheResult(
        com.ext.portlet.model.ImpactTemplateFocusAreaList impactTemplateFocusAreaList) {
        getPersistence().cacheResult(impactTemplateFocusAreaList);
    }

    /**
    * Caches the impact template focus area lists in the entity cache if it is enabled.
    *
    * @param impactTemplateFocusAreaLists the impact template focus area lists
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ImpactTemplateFocusAreaList> impactTemplateFocusAreaLists) {
        getPersistence().cacheResult(impactTemplateFocusAreaLists);
    }

    /**
    * Creates a new impact template focus area list with the primary key. Does not add the impact template focus area list to the database.
    *
    * @param focusAreaListId the primary key for the new impact template focus area list
    * @return the new impact template focus area list
    */
    public static com.ext.portlet.model.ImpactTemplateFocusAreaList create(
        long focusAreaListId) {
        return getPersistence().create(focusAreaListId);
    }

    /**
    * Removes the impact template focus area list with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaListId the primary key of the impact template focus area list
    * @return the impact template focus area list that was removed
    * @throws com.ext.portlet.NoSuchImpactTemplateFocusAreaListException if a impact template focus area list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateFocusAreaList remove(
        long focusAreaListId)
        throws com.ext.portlet.NoSuchImpactTemplateFocusAreaListException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(focusAreaListId);
    }

    public static com.ext.portlet.model.ImpactTemplateFocusAreaList updateImpl(
        com.ext.portlet.model.ImpactTemplateFocusAreaList impactTemplateFocusAreaList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(impactTemplateFocusAreaList);
    }

    /**
    * Returns the impact template focus area list with the primary key or throws a {@link com.ext.portlet.NoSuchImpactTemplateFocusAreaListException} if it could not be found.
    *
    * @param focusAreaListId the primary key of the impact template focus area list
    * @return the impact template focus area list
    * @throws com.ext.portlet.NoSuchImpactTemplateFocusAreaListException if a impact template focus area list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateFocusAreaList findByPrimaryKey(
        long focusAreaListId)
        throws com.ext.portlet.NoSuchImpactTemplateFocusAreaListException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(focusAreaListId);
    }

    /**
    * Returns the impact template focus area list with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param focusAreaListId the primary key of the impact template focus area list
    * @return the impact template focus area list, or <code>null</code> if a impact template focus area list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateFocusAreaList fetchByPrimaryKey(
        long focusAreaListId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(focusAreaListId);
    }

    /**
    * Returns all the impact template focus area lists.
    *
    * @return the impact template focus area lists
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactTemplateFocusAreaList> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the impact template focus area lists.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateFocusAreaListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template focus area lists
    * @param end the upper bound of the range of impact template focus area lists (not inclusive)
    * @return the range of impact template focus area lists
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactTemplateFocusAreaList> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the impact template focus area lists.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateFocusAreaListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template focus area lists
    * @param end the upper bound of the range of impact template focus area lists (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of impact template focus area lists
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactTemplateFocusAreaList> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the impact template focus area lists from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of impact template focus area lists.
    *
    * @return the number of impact template focus area lists
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImpactTemplateFocusAreaListPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImpactTemplateFocusAreaListPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ImpactTemplateFocusAreaListPersistence.class.getName());

            ReferenceRegistry.registerReference(ImpactTemplateFocusAreaListUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        ImpactTemplateFocusAreaListPersistence persistence) {
    }
}
