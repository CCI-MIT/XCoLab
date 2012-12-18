package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.model.ModelCategory;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the model category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelCategoryPersistenceImpl
 * @see ModelCategoryUtil
 * @generated
 */
public interface ModelCategoryPersistence extends BasePersistence<ModelCategory> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModelCategoryUtil} to access the model category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the model category in the entity cache if it is enabled.
    *
    * @param modelCategory the model category
    */
    public void cacheResult(
        com.ext.portlet.models.model.ModelCategory modelCategory);

    /**
    * Caches the model categories in the entity cache if it is enabled.
    *
    * @param modelCategories the model categories
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelCategory> modelCategories);

    /**
    * Creates a new model category with the primary key. Does not add the model category to the database.
    *
    * @param modelCategoryPK the primary key for the new model category
    * @return the new model category
    */
    public com.ext.portlet.models.model.ModelCategory create(
        long modelCategoryPK);

    /**
    * Removes the model category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelCategoryPK the primary key of the model category
    * @return the model category that was removed
    * @throws com.ext.portlet.models.NoSuchModelCategoryException if a model category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelCategory remove(
        long modelCategoryPK)
        throws com.ext.portlet.models.NoSuchModelCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.models.model.ModelCategory updateImpl(
        com.ext.portlet.models.model.ModelCategory modelCategory, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model category with the primary key or throws a {@link com.ext.portlet.models.NoSuchModelCategoryException} if it could not be found.
    *
    * @param modelCategoryPK the primary key of the model category
    * @return the model category
    * @throws com.ext.portlet.models.NoSuchModelCategoryException if a model category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelCategory findByPrimaryKey(
        long modelCategoryPK)
        throws com.ext.portlet.models.NoSuchModelCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model category with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelCategoryPK the primary key of the model category
    * @return the model category, or <code>null</code> if a model category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelCategory fetchByPrimaryKey(
        long modelCategoryPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model categories.
    *
    * @return the model categories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelCategory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model categories
    * @param end the upper bound of the range of model categories (not inclusive)
    * @return the range of model categories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelCategory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model categories
    * @param end the upper bound of the range of model categories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model categories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelCategory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model categories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model categories.
    *
    * @return the number of model categories
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
