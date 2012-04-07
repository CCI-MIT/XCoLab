package org.xcolab.services.sample.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.xcolab.services.sample.model.SampleEntity;

/**
 * The persistence interface for the sample entity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SampleEntityPersistenceImpl
 * @see SampleEntityUtil
 * @generated
 */
public interface SampleEntityPersistence extends BasePersistence<SampleEntity> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link SampleEntityUtil} to access the sample entity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the sample entity in the entity cache if it is enabled.
    *
    * @param sampleEntity the sample entity
    */
    public void cacheResult(
        org.xcolab.services.sample.model.SampleEntity sampleEntity);

    /**
    * Caches the sample entities in the entity cache if it is enabled.
    *
    * @param sampleEntities the sample entities
    */
    public void cacheResult(
        java.util.List<org.xcolab.services.sample.model.SampleEntity> sampleEntities);

    /**
    * Creates a new sample entity with the primary key. Does not add the sample entity to the database.
    *
    * @param id the primary key for the new sample entity
    * @return the new sample entity
    */
    public org.xcolab.services.sample.model.SampleEntity create(long id);

    /**
    * Removes the sample entity with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the sample entity
    * @return the sample entity that was removed
    * @throws org.xcolab.services.sample.NoSuchSampleEntityException if a sample entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.sample.model.SampleEntity remove(long id)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.xcolab.services.sample.NoSuchSampleEntityException;

    public org.xcolab.services.sample.model.SampleEntity updateImpl(
        org.xcolab.services.sample.model.SampleEntity sampleEntity,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the sample entity with the primary key or throws a {@link org.xcolab.services.sample.NoSuchSampleEntityException} if it could not be found.
    *
    * @param id the primary key of the sample entity
    * @return the sample entity
    * @throws org.xcolab.services.sample.NoSuchSampleEntityException if a sample entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.sample.model.SampleEntity findByPrimaryKey(
        long id)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.xcolab.services.sample.NoSuchSampleEntityException;

    /**
    * Returns the sample entity with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the sample entity
    * @return the sample entity, or <code>null</code> if a sample entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.sample.model.SampleEntity fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the sample entities.
    *
    * @return the sample entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.xcolab.services.sample.model.SampleEntity> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.xcolab.services.sample.model.SampleEntity> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.xcolab.services.sample.model.SampleEntity> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the sample entities from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sample entities.
    *
    * @return the number of sample entities
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
