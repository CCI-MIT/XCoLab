package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelPosition;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the model position service. This utility wraps {@link ModelPositionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelPositionPersistence
 * @see ModelPositionPersistenceImpl
 * @generated
 */
public class ModelPositionUtil {
    private static ModelPositionPersistence _persistence;

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
    public static void clearCache(ModelPosition modelPosition) {
        getPersistence().clearCache(modelPosition);
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
    public static List<ModelPosition> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ModelPosition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ModelPosition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ModelPosition update(ModelPosition modelPosition)
        throws SystemException {
        return getPersistence().update(modelPosition);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ModelPosition update(ModelPosition modelPosition,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(modelPosition, serviceContext);
    }

    /**
    * Returns all the model positions where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the matching model positions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelPosition> findByModelId(
        long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId(modelId);
    }

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
    public static java.util.List<com.ext.portlet.model.ModelPosition> findByModelId(
        long modelId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId(modelId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.ModelPosition> findByModelId(
        long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelId(modelId, start, end, orderByComparator);
    }

    /**
    * Returns the first model position in the ordered set where modelId = &#63;.
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model position
    * @throws com.ext.portlet.NoSuchModelPositionException if a matching model position could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelPosition findByModelId_First(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId_First(modelId, orderByComparator);
    }

    /**
    * Returns the first model position in the ordered set where modelId = &#63;.
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model position, or <code>null</code> if a matching model position could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelPosition fetchByModelId_First(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByModelId_First(modelId, orderByComparator);
    }

    /**
    * Returns the last model position in the ordered set where modelId = &#63;.
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model position
    * @throws com.ext.portlet.NoSuchModelPositionException if a matching model position could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelPosition findByModelId_Last(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId_Last(modelId, orderByComparator);
    }

    /**
    * Returns the last model position in the ordered set where modelId = &#63;.
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model position, or <code>null</code> if a matching model position could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelPosition fetchByModelId_Last(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByModelId_Last(modelId, orderByComparator);
    }

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
    public static com.ext.portlet.model.ModelPosition[] findByModelId_PrevAndNext(
        long id, long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelId_PrevAndNext(id, modelId, orderByComparator);
    }

    /**
    * Removes all the model positions where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByModelId(modelId);
    }

    /**
    * Returns the number of model positions where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model positions
    * @throws SystemException if a system exception occurred
    */
    public static int countByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByModelId(modelId);
    }

    /**
    * Caches the model position in the entity cache if it is enabled.
    *
    * @param modelPosition the model position
    */
    public static void cacheResult(
        com.ext.portlet.model.ModelPosition modelPosition) {
        getPersistence().cacheResult(modelPosition);
    }

    /**
    * Caches the model positions in the entity cache if it is enabled.
    *
    * @param modelPositions the model positions
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ModelPosition> modelPositions) {
        getPersistence().cacheResult(modelPositions);
    }

    /**
    * Creates a new model position with the primary key. Does not add the model position to the database.
    *
    * @param id the primary key for the new model position
    * @return the new model position
    */
    public static com.ext.portlet.model.ModelPosition create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the model position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the model position
    * @return the model position that was removed
    * @throws com.ext.portlet.NoSuchModelPositionException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelPosition remove(long id)
        throws com.ext.portlet.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.ModelPosition updateImpl(
        com.ext.portlet.model.ModelPosition modelPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(modelPosition);
    }

    /**
    * Returns the model position with the primary key or throws a {@link com.ext.portlet.NoSuchModelPositionException} if it could not be found.
    *
    * @param id the primary key of the model position
    * @return the model position
    * @throws com.ext.portlet.NoSuchModelPositionException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelPosition findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchModelPositionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the model position with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the model position
    * @return the model position, or <code>null</code> if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelPosition fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the model positions.
    *
    * @return the model positions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelPosition> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.ext.portlet.model.ModelPosition> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.ModelPosition> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the model positions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of model positions.
    *
    * @return the number of model positions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ModelPositionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ModelPositionPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ModelPositionPersistence.class.getName());

            ReferenceRegistry.registerReference(ModelPositionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ModelPositionPersistence persistence) {
    }
}
