package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelPosition;

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
    * Returns all the model positions where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the matching model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelPosition> findByModelId(
        long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model positions where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model positions
    * @param end the upper bound of the range of model positions (not inclusive)
    * @return the range of matching model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelPosition> findByModelId(
        long modelId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model positions where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model positions
    * @param end the upper bound of the range of model positions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelPosition> findByModelId(
        long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first model position in the ordered set where modelId = &#63;.
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model position
    * @throws com.ext.portlet.NoSuchModelPositionException if a matching model position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition findByModelId_First(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first model position in the ordered set where modelId = &#63;.
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model position, or <code>null</code> if a matching model position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition fetchByModelId_First(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last model position in the ordered set where modelId = &#63;.
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model position
    * @throws com.ext.portlet.NoSuchModelPositionException if a matching model position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition findByModelId_Last(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last model position in the ordered set where modelId = &#63;.
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model position, or <code>null</code> if a matching model position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition fetchByModelId_Last(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model positions before and after the current model position in the ordered set where modelId = &#63;.
    *
    * @param id the primary key of the current model position
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model position
    * @throws com.ext.portlet.NoSuchModelPositionException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition[] findByModelId_PrevAndNext(
        long id, long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model positions where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model positions where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model positions
    * @throws SystemException if a system exception occurred
    */
    public int countByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the model position in the entity cache if it is enabled.
    *
    * @param modelPosition the model position
    */
    public void cacheResult(com.ext.portlet.model.ModelPosition modelPosition);

    /**
    * Caches the model positions in the entity cache if it is enabled.
    *
    * @param modelPositions the model positions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ModelPosition> modelPositions);

    /**
    * Creates a new model position with the primary key. Does not add the model position to the database.
    *
    * @param id the primary key for the new model position
    * @return the new model position
    */
    public com.ext.portlet.model.ModelPosition create(long id);

    /**
    * Removes the model position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the model position
    * @return the model position that was removed
    * @throws com.ext.portlet.NoSuchModelPositionException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition remove(long id)
        throws com.ext.portlet.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ModelPosition updateImpl(
        com.ext.portlet.model.ModelPosition modelPosition)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model position with the primary key or throws a {@link com.ext.portlet.NoSuchModelPositionException} if it could not be found.
    *
    * @param id the primary key of the model position
    * @return the model position
    * @throws com.ext.portlet.NoSuchModelPositionException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model position with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the model position
    * @return the model position, or <code>null</code> if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model positions.
    *
    * @return the model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelPosition> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of model positions
    * @param end the upper bound of the range of model positions (not inclusive)
    * @return the range of model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelPosition> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of model positions
    * @param end the upper bound of the range of model positions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelPosition> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model positions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
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
