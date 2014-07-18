package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PointType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the point type service. This utility wraps {@link PointTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointTypePersistence
 * @see PointTypePersistenceImpl
 * @generated
 */
public class PointTypeUtil {
    private static PointTypePersistence _persistence;

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
    public static void clearCache(PointType pointType) {
        getPersistence().clearCache(pointType);
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
    public static List<PointType> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PointType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PointType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PointType update(PointType pointType)
        throws SystemException {
        return getPersistence().update(pointType);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PointType update(PointType pointType,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(pointType, serviceContext);
    }

    /**
    * Returns all the point types where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @return the matching point types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointType> findByParentPointTypeId(
        long parentPointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByParentPointTypeId(parentPointTypeId);
    }

    /**
    * Returns a range of all the point types where parentPointTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param parentPointTypeId the parent point type ID
    * @param start the lower bound of the range of point types
    * @param end the upper bound of the range of point types (not inclusive)
    * @return the range of matching point types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointType> findByParentPointTypeId(
        long parentPointTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByParentPointTypeId(parentPointTypeId, start, end);
    }

    /**
    * Returns an ordered range of all the point types where parentPointTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param parentPointTypeId the parent point type ID
    * @param start the lower bound of the range of point types
    * @param end the upper bound of the range of point types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching point types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointType> findByParentPointTypeId(
        long parentPointTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByParentPointTypeId(parentPointTypeId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first point type in the ordered set where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point type
    * @throws com.ext.portlet.NoSuchPointTypeException if a matching point type could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointType findByParentPointTypeId_First(
        long parentPointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByParentPointTypeId_First(parentPointTypeId,
            orderByComparator);
    }

    /**
    * Returns the first point type in the ordered set where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point type, or <code>null</code> if a matching point type could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointType fetchByParentPointTypeId_First(
        long parentPointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByParentPointTypeId_First(parentPointTypeId,
            orderByComparator);
    }

    /**
    * Returns the last point type in the ordered set where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point type
    * @throws com.ext.portlet.NoSuchPointTypeException if a matching point type could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointType findByParentPointTypeId_Last(
        long parentPointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByParentPointTypeId_Last(parentPointTypeId,
            orderByComparator);
    }

    /**
    * Returns the last point type in the ordered set where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point type, or <code>null</code> if a matching point type could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointType fetchByParentPointTypeId_Last(
        long parentPointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByParentPointTypeId_Last(parentPointTypeId,
            orderByComparator);
    }

    /**
    * Returns the point types before and after the current point type in the ordered set where parentPointTypeId = &#63;.
    *
    * @param id the primary key of the current point type
    * @param parentPointTypeId the parent point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next point type
    * @throws com.ext.portlet.NoSuchPointTypeException if a point type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointType[] findByParentPointTypeId_PrevAndNext(
        long id, long parentPointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByParentPointTypeId_PrevAndNext(id, parentPointTypeId,
            orderByComparator);
    }

    /**
    * Removes all the point types where parentPointTypeId = &#63; from the database.
    *
    * @param parentPointTypeId the parent point type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByParentPointTypeId(long parentPointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByParentPointTypeId(parentPointTypeId);
    }

    /**
    * Returns the number of point types where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @return the number of matching point types
    * @throws SystemException if a system exception occurred
    */
    public static int countByParentPointTypeId(long parentPointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByParentPointTypeId(parentPointTypeId);
    }

    /**
    * Caches the point type in the entity cache if it is enabled.
    *
    * @param pointType the point type
    */
    public static void cacheResult(com.ext.portlet.model.PointType pointType) {
        getPersistence().cacheResult(pointType);
    }

    /**
    * Caches the point types in the entity cache if it is enabled.
    *
    * @param pointTypes the point types
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PointType> pointTypes) {
        getPersistence().cacheResult(pointTypes);
    }

    /**
    * Creates a new point type with the primary key. Does not add the point type to the database.
    *
    * @param id the primary key for the new point type
    * @return the new point type
    */
    public static com.ext.portlet.model.PointType create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the point type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the point type
    * @return the point type that was removed
    * @throws com.ext.portlet.NoSuchPointTypeException if a point type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointType remove(long id)
        throws com.ext.portlet.NoSuchPointTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PointType updateImpl(
        com.ext.portlet.model.PointType pointType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(pointType);
    }

    /**
    * Returns the point type with the primary key or throws a {@link com.ext.portlet.NoSuchPointTypeException} if it could not be found.
    *
    * @param id the primary key of the point type
    * @return the point type
    * @throws com.ext.portlet.NoSuchPointTypeException if a point type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointType findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPointTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the point type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the point type
    * @return the point type, or <code>null</code> if a point type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointType fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the point types.
    *
    * @return the point types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the point types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of point types
    * @param end the upper bound of the range of point types (not inclusive)
    * @return the range of point types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the point types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of point types
    * @param end the upper bound of the range of point types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of point types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the point types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of point types.
    *
    * @return the number of point types
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PointTypePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PointTypePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PointTypePersistence.class.getName());

            ReferenceRegistry.registerReference(PointTypeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PointTypePersistence persistence) {
    }
}
