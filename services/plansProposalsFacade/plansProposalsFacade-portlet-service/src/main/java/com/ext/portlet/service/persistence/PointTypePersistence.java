package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PointType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the point type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointTypePersistenceImpl
 * @see PointTypeUtil
 * @generated
 */
public interface PointTypePersistence extends BasePersistence<PointType> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PointTypeUtil} to access the point type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the point types where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @return the matching point types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointType> findByParentPointTypeId(
        long parentPointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.PointType> findByParentPointTypeId(
        long parentPointTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.PointType> findByParentPointTypeId(
        long parentPointTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first point type in the ordered set where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point type
    * @throws com.ext.portlet.NoSuchPointTypeException if a matching point type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointType findByParentPointTypeId_First(
        long parentPointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first point type in the ordered set where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point type, or <code>null</code> if a matching point type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointType fetchByParentPointTypeId_First(
        long parentPointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last point type in the ordered set where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point type
    * @throws com.ext.portlet.NoSuchPointTypeException if a matching point type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointType findByParentPointTypeId_Last(
        long parentPointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last point type in the ordered set where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point type, or <code>null</code> if a matching point type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointType fetchByParentPointTypeId_Last(
        long parentPointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.PointType[] findByParentPointTypeId_PrevAndNext(
        long id, long parentPointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the point types where parentPointTypeId = &#63; from the database.
    *
    * @param parentPointTypeId the parent point type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByParentPointTypeId(long parentPointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of point types where parentPointTypeId = &#63;.
    *
    * @param parentPointTypeId the parent point type ID
    * @return the number of matching point types
    * @throws SystemException if a system exception occurred
    */
    public int countByParentPointTypeId(long parentPointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the point type in the entity cache if it is enabled.
    *
    * @param pointType the point type
    */
    public void cacheResult(com.ext.portlet.model.PointType pointType);

    /**
    * Caches the point types in the entity cache if it is enabled.
    *
    * @param pointTypes the point types
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PointType> pointTypes);

    /**
    * Creates a new point type with the primary key. Does not add the point type to the database.
    *
    * @param id the primary key for the new point type
    * @return the new point type
    */
    public com.ext.portlet.model.PointType create(long id);

    /**
    * Removes the point type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the point type
    * @return the point type that was removed
    * @throws com.ext.portlet.NoSuchPointTypeException if a point type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointType remove(long id)
        throws com.ext.portlet.NoSuchPointTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PointType updateImpl(
        com.ext.portlet.model.PointType pointType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the point type with the primary key or throws a {@link com.ext.portlet.NoSuchPointTypeException} if it could not be found.
    *
    * @param id the primary key of the point type
    * @return the point type
    * @throws com.ext.portlet.NoSuchPointTypeException if a point type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointType findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPointTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the point type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the point type
    * @return the point type, or <code>null</code> if a point type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointType fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the point types.
    *
    * @return the point types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.PointType> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.PointType> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the point types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of point types.
    *
    * @return the number of point types
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
