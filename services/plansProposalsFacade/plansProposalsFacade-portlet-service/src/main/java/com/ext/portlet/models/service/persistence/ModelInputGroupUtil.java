package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.model.ModelInputGroup;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the model input group service. This utility wraps {@link ModelInputGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputGroupPersistence
 * @see ModelInputGroupPersistenceImpl
 * @generated
 */
public class ModelInputGroupUtil {
    private static ModelInputGroupPersistence _persistence;

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
    public static void clearCache(ModelInputGroup modelInputGroup) {
        getPersistence().clearCache(modelInputGroup);
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
    public static List<ModelInputGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ModelInputGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ModelInputGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ModelInputGroup update(ModelInputGroup modelInputGroup,
        boolean merge) throws SystemException {
        return getPersistence().update(modelInputGroup, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ModelInputGroup update(ModelInputGroup modelInputGroup,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(modelInputGroup, merge, serviceContext);
    }

    /**
    * Caches the model input group in the entity cache if it is enabled.
    *
    * @param modelInputGroup the model input group
    */
    public static void cacheResult(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup) {
        getPersistence().cacheResult(modelInputGroup);
    }

    /**
    * Caches the model input groups in the entity cache if it is enabled.
    *
    * @param modelInputGroups the model input groups
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelInputGroup> modelInputGroups) {
        getPersistence().cacheResult(modelInputGroups);
    }

    /**
    * Creates a new model input group with the primary key. Does not add the model input group to the database.
    *
    * @param modelInputGroupPK the primary key for the new model input group
    * @return the new model input group
    */
    public static com.ext.portlet.models.model.ModelInputGroup create(
        long modelInputGroupPK) {
        return getPersistence().create(modelInputGroupPK);
    }

    /**
    * Removes the model input group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroupPK the primary key of the model input group
    * @return the model input group that was removed
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputGroup remove(
        long modelInputGroupPK)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(modelInputGroupPK);
    }

    public static com.ext.portlet.models.model.ModelInputGroup updateImpl(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(modelInputGroup, merge);
    }

    /**
    * Returns the model input group with the primary key or throws a {@link com.ext.portlet.models.NoSuchModelInputGroupException} if it could not be found.
    *
    * @param modelInputGroupPK the primary key of the model input group
    * @return the model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputGroup findByPrimaryKey(
        long modelInputGroupPK)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(modelInputGroupPK);
    }

    /**
    * Returns the model input group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelInputGroupPK the primary key of the model input group
    * @return the model input group, or <code>null</code> if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputGroup fetchByPrimaryKey(
        long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(modelInputGroupPK);
    }

    /**
    * Returns all the model input groups where parentGroupPK = &#63;.
    *
    * @param parentGroupPK the parent group p k
    * @return the matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByparentModelId(
        long parentGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByparentModelId(parentGroupPK);
    }

    /**
    * Returns a range of all the model input groups where parentGroupPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param parentGroupPK the parent group p k
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @return the range of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByparentModelId(
        long parentGroupPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByparentModelId(parentGroupPK, start, end);
    }

    /**
    * Returns an ordered range of all the model input groups where parentGroupPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param parentGroupPK the parent group p k
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByparentModelId(
        long parentGroupPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByparentModelId(parentGroupPK, start, end,
            orderByComparator);
    }

    /**
    * Returns the first model input group in the ordered set where parentGroupPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param parentGroupPK the parent group p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a matching model input group could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputGroup findByparentModelId_First(
        long parentGroupPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByparentModelId_First(parentGroupPK, orderByComparator);
    }

    /**
    * Returns the last model input group in the ordered set where parentGroupPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param parentGroupPK the parent group p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a matching model input group could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputGroup findByparentModelId_Last(
        long parentGroupPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByparentModelId_Last(parentGroupPK, orderByComparator);
    }

    /**
    * Returns the model input groups before and after the current model input group in the ordered set where parentGroupPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelInputGroupPK the primary key of the current model input group
    * @param parentGroupPK the parent group p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputGroup[] findByparentModelId_PrevAndNext(
        long modelInputGroupPK, long parentGroupPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByparentModelId_PrevAndNext(modelInputGroupPK,
            parentGroupPK, orderByComparator);
    }

    /**
    * Returns all the model input groups where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId(modelId);
    }

    /**
    * Returns a range of all the model input groups where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @return the range of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        long modelId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId(modelId, start, end);
    }

    /**
    * Returns an ordered range of all the model input groups where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelId(modelId, start, end, orderByComparator);
    }

    /**
    * Returns the first model input group in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a matching model input group could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputGroup findByModelId_First(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId_First(modelId, orderByComparator);
    }

    /**
    * Returns the last model input group in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a matching model input group could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputGroup findByModelId_Last(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId_Last(modelId, orderByComparator);
    }

    /**
    * Returns the model input groups before and after the current model input group in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelInputGroupPK the primary key of the current model input group
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputGroup[] findByModelId_PrevAndNext(
        long modelInputGroupPK, long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelId_PrevAndNext(modelInputGroupPK, modelId,
            orderByComparator);
    }

    /**
    * Returns all the model input groups.
    *
    * @return the model input groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the model input groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @return the range of model input groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the model input groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model input groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the model input groups where parentGroupPK = &#63; from the database.
    *
    * @param parentGroupPK the parent group p k
    * @throws SystemException if a system exception occurred
    */
    public static void removeByparentModelId(long parentGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByparentModelId(parentGroupPK);
    }

    /**
    * Removes all the model input groups where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByModelId(modelId);
    }

    /**
    * Removes all the model input groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of model input groups where parentGroupPK = &#63;.
    *
    * @param parentGroupPK the parent group p k
    * @return the number of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public static int countByparentModelId(long parentGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByparentModelId(parentGroupPK);
    }

    /**
    * Returns the number of model input groups where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public static int countByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByModelId(modelId);
    }

    /**
    * Returns the number of model input groups.
    *
    * @return the number of model input groups
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ModelInputGroupPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ModelInputGroupPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.models.service.ClpSerializer.getServletContextName(),
                    ModelInputGroupPersistence.class.getName());

            ReferenceRegistry.registerReference(ModelInputGroupUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ModelInputGroupPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ModelInputGroupUtil.class,
            "_persistence");
    }
}
