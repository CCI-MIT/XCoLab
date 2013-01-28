package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelDiscussion;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the model discussion service. This utility wraps {@link ModelDiscussionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelDiscussionPersistence
 * @see ModelDiscussionPersistenceImpl
 * @generated
 */
public class ModelDiscussionUtil {
    private static ModelDiscussionPersistence _persistence;

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
    public static void clearCache(ModelDiscussion modelDiscussion) {
        getPersistence().clearCache(modelDiscussion);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ModelDiscussion> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ModelDiscussion> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ModelDiscussion> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ModelDiscussion update(ModelDiscussion modelDiscussion,
        boolean merge) throws SystemException {
        return getPersistence().update(modelDiscussion, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ModelDiscussion update(ModelDiscussion modelDiscussion,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(modelDiscussion, merge, serviceContext);
    }

    /**
    * Caches the model discussion in the entity cache if it is enabled.
    *
    * @param modelDiscussion the model discussion
    */
    public static void cacheResult(
        com.ext.portlet.model.ModelDiscussion modelDiscussion) {
        getPersistence().cacheResult(modelDiscussion);
    }

    /**
    * Caches the model discussions in the entity cache if it is enabled.
    *
    * @param modelDiscussions the model discussions
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ModelDiscussion> modelDiscussions) {
        getPersistence().cacheResult(modelDiscussions);
    }

    /**
    * Creates a new model discussion with the primary key. Does not add the model discussion to the database.
    *
    * @param modelDiscussionId the primary key for the new model discussion
    * @return the new model discussion
    */
    public static com.ext.portlet.model.ModelDiscussion create(
        long modelDiscussionId) {
        return getPersistence().create(modelDiscussionId);
    }

    /**
    * Removes the model discussion with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelDiscussionId the primary key of the model discussion
    * @return the model discussion that was removed
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelDiscussion remove(
        long modelDiscussionId)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(modelDiscussionId);
    }

    public static com.ext.portlet.model.ModelDiscussion updateImpl(
        com.ext.portlet.model.ModelDiscussion modelDiscussion, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(modelDiscussion, merge);
    }

    /**
    * Returns the model discussion with the primary key or throws a {@link com.ext.portlet.NoSuchModelDiscussionException} if it could not be found.
    *
    * @param modelDiscussionId the primary key of the model discussion
    * @return the model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelDiscussion findByPrimaryKey(
        long modelDiscussionId)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(modelDiscussionId);
    }

    /**
    * Returns the model discussion with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelDiscussionId the primary key of the model discussion
    * @return the model discussion, or <code>null</code> if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelDiscussion fetchByPrimaryKey(
        long modelDiscussionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(modelDiscussionId);
    }

    /**
    * Returns all the model discussions where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelDiscussion> findByModelId(
        long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId(modelId);
    }

    /**
    * Returns a range of all the model discussions where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @return the range of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelDiscussion> findByModelId(
        long modelId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId(modelId, start, end);
    }

    /**
    * Returns an ordered range of all the model discussions where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelDiscussion> findByModelId(
        long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelId(modelId, start, end, orderByComparator);
    }

    /**
    * Returns the first model discussion in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a matching model discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelDiscussion findByModelId_First(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId_First(modelId, orderByComparator);
    }

    /**
    * Returns the last model discussion in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a matching model discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelDiscussion findByModelId_Last(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId_Last(modelId, orderByComparator);
    }

    /**
    * Returns the model discussions before and after the current model discussion in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelDiscussionId the primary key of the current model discussion
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelDiscussion[] findByModelId_PrevAndNext(
        long modelDiscussionId, long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelId_PrevAndNext(modelDiscussionId, modelId,
            orderByComparator);
    }

    /**
    * Returns all the model discussions where categoryId = &#63;.
    *
    * @param categoryId the category ID
    * @return the matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelDiscussion> findByDiscussionId(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByDiscussionId(categoryId);
    }

    /**
    * Returns a range of all the model discussions where categoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @return the range of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelDiscussion> findByDiscussionId(
        long categoryId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByDiscussionId(categoryId, start, end);
    }

    /**
    * Returns an ordered range of all the model discussions where categoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelDiscussion> findByDiscussionId(
        long categoryId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByDiscussionId(categoryId, start, end, orderByComparator);
    }

    /**
    * Returns the first model discussion in the ordered set where categoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a matching model discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelDiscussion findByDiscussionId_First(
        long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByDiscussionId_First(categoryId, orderByComparator);
    }

    /**
    * Returns the last model discussion in the ordered set where categoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a matching model discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelDiscussion findByDiscussionId_Last(
        long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByDiscussionId_Last(categoryId, orderByComparator);
    }

    /**
    * Returns the model discussions before and after the current model discussion in the ordered set where categoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelDiscussionId the primary key of the current model discussion
    * @param categoryId the category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelDiscussion[] findByDiscussionId_PrevAndNext(
        long modelDiscussionId, long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByDiscussionId_PrevAndNext(modelDiscussionId,
            categoryId, orderByComparator);
    }

    /**
    * Returns all the model discussions.
    *
    * @return the model discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelDiscussion> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the model discussions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @return the range of model discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelDiscussion> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the model discussions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelDiscussion> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the model discussions where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByModelId(modelId);
    }

    /**
    * Removes all the model discussions where categoryId = &#63; from the database.
    *
    * @param categoryId the category ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByDiscussionId(long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByDiscussionId(categoryId);
    }

    /**
    * Removes all the model discussions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of model discussions where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public static int countByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByModelId(modelId);
    }

    /**
    * Returns the number of model discussions where categoryId = &#63;.
    *
    * @param categoryId the category ID
    * @return the number of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public static int countByDiscussionId(long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByDiscussionId(categoryId);
    }

    /**
    * Returns the number of model discussions.
    *
    * @return the number of model discussions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ModelDiscussionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ModelDiscussionPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ModelDiscussionPersistence.class.getName());

            ReferenceRegistry.registerReference(ModelDiscussionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ModelDiscussionPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ModelDiscussionUtil.class,
            "_persistence");
    }
}
