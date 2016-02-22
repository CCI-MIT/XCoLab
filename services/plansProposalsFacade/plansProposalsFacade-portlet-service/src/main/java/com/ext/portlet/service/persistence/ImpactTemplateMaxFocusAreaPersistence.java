package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactTemplateMaxFocusArea;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the impact template max focus area service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateMaxFocusAreaPersistenceImpl
 * @see ImpactTemplateMaxFocusAreaUtil
 * @generated
 */
public interface ImpactTemplateMaxFocusAreaPersistence extends BasePersistence<ImpactTemplateMaxFocusArea> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImpactTemplateMaxFocusAreaUtil} to access the impact template max focus area persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the impact template max focus areas where focusAreaListId = &#63;.
    *
    * @param focusAreaListId the focus area list ID
    * @return the matching impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactTemplateMaxFocusArea> findByFocusAreaListId(
        long focusAreaListId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the impact template max focus areas where focusAreaListId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param focusAreaListId the focus area list ID
    * @param start the lower bound of the range of impact template max focus areas
    * @param end the upper bound of the range of impact template max focus areas (not inclusive)
    * @return the range of matching impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactTemplateMaxFocusArea> findByFocusAreaListId(
        long focusAreaListId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the impact template max focus areas where focusAreaListId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param focusAreaListId the focus area list ID
    * @param start the lower bound of the range of impact template max focus areas
    * @param end the upper bound of the range of impact template max focus areas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactTemplateMaxFocusArea> findByFocusAreaListId(
        long focusAreaListId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first impact template max focus area in the ordered set where focusAreaListId = &#63;.
    *
    * @param focusAreaListId the focus area list ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact template max focus area
    * @throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException if a matching impact template max focus area could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea findByFocusAreaListId_First(
        long focusAreaListId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first impact template max focus area in the ordered set where focusAreaListId = &#63;.
    *
    * @param focusAreaListId the focus area list ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact template max focus area, or <code>null</code> if a matching impact template max focus area could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea fetchByFocusAreaListId_First(
        long focusAreaListId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last impact template max focus area in the ordered set where focusAreaListId = &#63;.
    *
    * @param focusAreaListId the focus area list ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact template max focus area
    * @throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException if a matching impact template max focus area could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea findByFocusAreaListId_Last(
        long focusAreaListId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last impact template max focus area in the ordered set where focusAreaListId = &#63;.
    *
    * @param focusAreaListId the focus area list ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact template max focus area, or <code>null</code> if a matching impact template max focus area could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea fetchByFocusAreaListId_Last(
        long focusAreaListId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact template max focus areas before and after the current impact template max focus area in the ordered set where focusAreaListId = &#63;.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key of the current impact template max focus area
    * @param focusAreaListId the focus area list ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next impact template max focus area
    * @throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException if a impact template max focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea[] findByFocusAreaListId_PrevAndNext(
        ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK,
        long focusAreaListId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the impact template max focus areas where focusAreaListId = &#63; from the database.
    *
    * @param focusAreaListId the focus area list ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByFocusAreaListId(long focusAreaListId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of impact template max focus areas where focusAreaListId = &#63;.
    *
    * @param focusAreaListId the focus area list ID
    * @return the number of matching impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    public int countByFocusAreaListId(long focusAreaListId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the impact template max focus area in the entity cache if it is enabled.
    *
    * @param impactTemplateMaxFocusArea the impact template max focus area
    */
    public void cacheResult(
        com.ext.portlet.model.ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea);

    /**
    * Caches the impact template max focus areas in the entity cache if it is enabled.
    *
    * @param impactTemplateMaxFocusAreas the impact template max focus areas
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ImpactTemplateMaxFocusArea> impactTemplateMaxFocusAreas);

    /**
    * Creates a new impact template max focus area with the primary key. Does not add the impact template max focus area to the database.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key for the new impact template max focus area
    * @return the new impact template max focus area
    */
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea create(
        ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK);

    /**
    * Removes the impact template max focus area with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key of the impact template max focus area
    * @return the impact template max focus area that was removed
    * @throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException if a impact template max focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea remove(
        ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ImpactTemplateMaxFocusArea updateImpl(
        com.ext.portlet.model.ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact template max focus area with the primary key or throws a {@link com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException} if it could not be found.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key of the impact template max focus area
    * @return the impact template max focus area
    * @throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException if a impact template max focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea findByPrimaryKey(
        ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact template max focus area with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key of the impact template max focus area
    * @return the impact template max focus area, or <code>null</code> if a impact template max focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea fetchByPrimaryKey(
        ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the impact template max focus areas.
    *
    * @return the impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactTemplateMaxFocusArea> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the impact template max focus areas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template max focus areas
    * @param end the upper bound of the range of impact template max focus areas (not inclusive)
    * @return the range of impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactTemplateMaxFocusArea> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the impact template max focus areas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template max focus areas
    * @param end the upper bound of the range of impact template max focus areas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactTemplateMaxFocusArea> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the impact template max focus areas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of impact template max focus areas.
    *
    * @return the number of impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
