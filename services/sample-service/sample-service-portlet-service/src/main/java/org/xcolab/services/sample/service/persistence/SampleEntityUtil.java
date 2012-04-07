package org.xcolab.services.sample.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.xcolab.services.sample.model.SampleEntity;

import java.util.List;

/**
 * The persistence utility for the sample entity service. This utility wraps {@link SampleEntityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SampleEntityPersistence
 * @see SampleEntityPersistenceImpl
 * @generated
 */
public class SampleEntityUtil {
    private static SampleEntityPersistence _persistence;

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
    public static void clearCache(SampleEntity sampleEntity) {
        getPersistence().clearCache(sampleEntity);
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
    public static List<SampleEntity> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<SampleEntity> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<SampleEntity> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static SampleEntity update(SampleEntity sampleEntity, boolean merge)
        throws SystemException {
        return getPersistence().update(sampleEntity, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static SampleEntity update(SampleEntity sampleEntity, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(sampleEntity, merge, serviceContext);
    }

    /**
    * Caches the sample entity in the entity cache if it is enabled.
    *
    * @param sampleEntity the sample entity
    */
    public static void cacheResult(
        org.xcolab.services.sample.model.SampleEntity sampleEntity) {
        getPersistence().cacheResult(sampleEntity);
    }

    /**
    * Caches the sample entities in the entity cache if it is enabled.
    *
    * @param sampleEntities the sample entities
    */
    public static void cacheResult(
        java.util.List<org.xcolab.services.sample.model.SampleEntity> sampleEntities) {
        getPersistence().cacheResult(sampleEntities);
    }

    /**
    * Creates a new sample entity with the primary key. Does not add the sample entity to the database.
    *
    * @param id the primary key for the new sample entity
    * @return the new sample entity
    */
    public static org.xcolab.services.sample.model.SampleEntity create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the sample entity with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the sample entity
    * @return the sample entity that was removed
    * @throws org.xcolab.services.sample.NoSuchSampleEntityException if a sample entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.xcolab.services.sample.model.SampleEntity remove(long id)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.xcolab.services.sample.NoSuchSampleEntityException {
        return getPersistence().remove(id);
    }

    public static org.xcolab.services.sample.model.SampleEntity updateImpl(
        org.xcolab.services.sample.model.SampleEntity sampleEntity,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(sampleEntity, merge);
    }

    /**
    * Returns the sample entity with the primary key or throws a {@link org.xcolab.services.sample.NoSuchSampleEntityException} if it could not be found.
    *
    * @param id the primary key of the sample entity
    * @return the sample entity
    * @throws org.xcolab.services.sample.NoSuchSampleEntityException if a sample entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.xcolab.services.sample.model.SampleEntity findByPrimaryKey(
        long id)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.xcolab.services.sample.NoSuchSampleEntityException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the sample entity with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the sample entity
    * @return the sample entity, or <code>null</code> if a sample entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.xcolab.services.sample.model.SampleEntity fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the sample entities.
    *
    * @return the sample entities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.xcolab.services.sample.model.SampleEntity> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the sample entities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of sample entities
    * @param end the upper bound of the range of sample entities (not inclusive)
    * @return the range of sample entities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.xcolab.services.sample.model.SampleEntity> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the sample entities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of sample entities
    * @param end the upper bound of the range of sample entities (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of sample entities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.xcolab.services.sample.model.SampleEntity> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the sample entities from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of sample entities.
    *
    * @return the number of sample entities
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static SampleEntityPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (SampleEntityPersistence) PortletBeanLocatorUtil.locate(org.xcolab.services.sample.service.ClpSerializer.getServletContextName(),
                    SampleEntityPersistence.class.getName());

            ReferenceRegistry.registerReference(SampleEntityUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(SampleEntityPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(SampleEntityUtil.class,
            "_persistence");
    }
}
