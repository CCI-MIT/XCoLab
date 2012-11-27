package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.model.ModelPosition;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the model position service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelPositionPersistenceImpl
 * @see ModelPositionUtil
 * @generated
 */
public interface ModelPositionPersistence extends BasePersistence<ModelPosition> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModelPositionUtil} to access the model position persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the model position in the entity cache if it is enabled.
    *
    * @param modelPosition the model position
    */
    public void cacheResult(
        com.ext.portlet.models.model.ModelPosition modelPosition);

    /**
    * Caches the model positions in the entity cache if it is enabled.
    *
    * @param modelPositions the model positions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelPosition> modelPositions);

    /**
    * Creates a new model position with the primary key. Does not add the model position to the database.
    *
    * @param id the primary key for the new model position
    * @return the new model position
    */
    public com.ext.portlet.models.model.ModelPosition create(java.lang.Long id);

    /**
    * Removes the model position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the model position
    * @return the model position that was removed
    * @throws com.ext.portlet.models.NoSuchModelPositionException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelPosition remove(java.lang.Long id)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.models.model.ModelPosition updateImpl(
        com.ext.portlet.models.model.ModelPosition modelPosition, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model position with the primary key or throws a {@link com.ext.portlet.models.NoSuchModelPositionException} if it could not be found.
    *
    * @param id the primary key of the model position
    * @return the model position
    * @throws com.ext.portlet.models.NoSuchModelPositionException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelPosition findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model position with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the model position
    * @return the model position, or <code>null</code> if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelPosition fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model positions where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the matching model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelPosition> findByModelId(
        java.lang.Long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model positions where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model positions
    * @param end the upper bound of the range of model positions (not inclusive)
    * @return the range of matching model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelPosition> findByModelId(
        java.lang.Long modelId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model positions where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model positions
    * @param end the upper bound of the range of model positions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelPosition> findByModelId(
        java.lang.Long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first model position in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model position
    * @throws com.ext.portlet.models.NoSuchModelPositionException if a matching model position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelPosition findByModelId_First(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last model position in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model position
    * @throws com.ext.portlet.models.NoSuchModelPositionException if a matching model position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelPosition findByModelId_Last(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model positions before and after the current model position in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current model position
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model position
    * @throws com.ext.portlet.models.NoSuchModelPositionException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelPosition[] findByModelId_PrevAndNext(
        java.lang.Long id, java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model positions.
    *
    * @return the model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelPosition> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model positions
    * @param end the upper bound of the range of model positions (not inclusive)
    * @return the range of model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelPosition> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model positions
    * @param end the upper bound of the range of model positions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelPosition> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model positions where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelId(java.lang.Long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model positions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model positions where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model positions
    * @throws SystemException if a system exception occurred
    */
    public int countByModelId(java.lang.Long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model positions.
    *
    * @return the number of model positions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
