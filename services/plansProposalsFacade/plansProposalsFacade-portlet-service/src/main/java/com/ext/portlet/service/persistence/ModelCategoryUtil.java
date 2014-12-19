package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelCategory;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the model category service. This utility wraps {@link ModelCategoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelCategoryPersistence
 * @see ModelCategoryPersistenceImpl
 * @generated
 */
public class ModelCategoryUtil {
    private static ModelCategoryPersistence _persistence;

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
    public static void clearCache(ModelCategory modelCategory) {
        getPersistence().clearCache(modelCategory);
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
    public static List<ModelCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ModelCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ModelCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ModelCategory update(ModelCategory modelCategory)
        throws SystemException {
        return getPersistence().update(modelCategory);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ModelCategory update(ModelCategory modelCategory,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(modelCategory, serviceContext);
    }

    /**
    * Caches the model category in the entity cache if it is enabled.
    *
    * @param modelCategory the model category
    */
    public static void cacheResult(
        com.ext.portlet.model.ModelCategory modelCategory) {
        getPersistence().cacheResult(modelCategory);
    }

    /**
    * Caches the model categories in the entity cache if it is enabled.
    *
    * @param modelCategories the model categories
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ModelCategory> modelCategories) {
        getPersistence().cacheResult(modelCategories);
    }

    /**
    * Creates a new model category with the primary key. Does not add the model category to the database.
    *
    * @param modelCategoryPK the primary key for the new model category
    * @return the new model category
    */
    public static com.ext.portlet.model.ModelCategory create(
        long modelCategoryPK) {
        return getPersistence().create(modelCategoryPK);
    }

    /**
    * Removes the model category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelCategoryPK the primary key of the model category
    * @return the model category that was removed
    * @throws com.ext.portlet.NoSuchModelCategoryException if a model category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelCategory remove(
        long modelCategoryPK)
        throws com.ext.portlet.NoSuchModelCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(modelCategoryPK);
    }

    public static com.ext.portlet.model.ModelCategory updateImpl(
        com.ext.portlet.model.ModelCategory modelCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(modelCategory);
    }

    /**
    * Returns the model category with the primary key or throws a {@link com.ext.portlet.NoSuchModelCategoryException} if it could not be found.
    *
    * @param modelCategoryPK the primary key of the model category
    * @return the model category
    * @throws com.ext.portlet.NoSuchModelCategoryException if a model category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelCategory findByPrimaryKey(
        long modelCategoryPK)
        throws com.ext.portlet.NoSuchModelCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(modelCategoryPK);
    }

    /**
    * Returns the model category with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelCategoryPK the primary key of the model category
    * @return the model category, or <code>null</code> if a model category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelCategory fetchByPrimaryKey(
        long modelCategoryPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(modelCategoryPK);
    }

    /**
    * Returns all the model categories.
    *
    * @return the model categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelCategory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the model categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of model categories
    * @param end the upper bound of the range of model categories (not inclusive)
    * @return the range of model categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelCategory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the model categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of model categories
    * @param end the upper bound of the range of model categories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelCategory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the model categories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of model categories.
    *
    * @return the number of model categories
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ModelCategoryPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ModelCategoryPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ModelCategoryPersistence.class.getName());

            ReferenceRegistry.registerReference(ModelCategoryUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ModelCategoryPersistence persistence) {
    }
}
