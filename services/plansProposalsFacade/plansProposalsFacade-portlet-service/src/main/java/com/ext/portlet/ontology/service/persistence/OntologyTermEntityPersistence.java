package com.ext.portlet.ontology.service.persistence;

import com.ext.portlet.ontology.model.OntologyTermEntity;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ontology term entity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntityPersistenceImpl
 * @see OntologyTermEntityUtil
 * @generated
 */
public interface OntologyTermEntityPersistence extends BasePersistence<OntologyTermEntity> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link OntologyTermEntityUtil} to access the ontology term entity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ontology term entity in the entity cache if it is enabled.
    *
    * @param ontologyTermEntity the ontology term entity
    */
    public void cacheResult(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity);

    /**
    * Caches the ontology term entities in the entity cache if it is enabled.
    *
    * @param ontologyTermEntities the ontology term entities
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> ontologyTermEntities);

    /**
    * Creates a new ontology term entity with the primary key. Does not add the ontology term entity to the database.
    *
    * @param id the primary key for the new ontology term entity
    * @return the new ontology term entity
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity create(
        java.lang.Long id);

    /**
    * Removes the ontology term entity with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the ontology term entity
    * @return the ontology term entity that was removed
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity remove(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity updateImpl(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology term entity with the primary key or throws a {@link com.ext.portlet.ontology.NoSuchOntologyTermEntityException} if it could not be found.
    *
    * @param id the primary key of the ontology term entity
    * @return the ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology term entity with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the ontology term entity
    * @return the ontology term entity, or <code>null</code> if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ontology term entities where classNameId = &#63;.
    *
    * @param classNameId the class name ID
    * @return the matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameId(
        java.lang.Long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology term entities where classNameId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @return the range of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameId(
        java.lang.Long classNameId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology term entities where classNameId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameId(
        java.lang.Long classNameId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term entity in the ordered set where classNameId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameId_First(
        java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term entity in the ordered set where classNameId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameId_Last(
        java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology term entities before and after the current ontology term entity in the ordered set where classNameId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current ontology term entity
    * @param classNameId the class name ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity[] findByClassNameId_PrevAndNext(
        java.lang.Long id, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ontology term entities where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameIdClassPk(
        java.lang.Long classNameId, java.lang.Long classPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology term entities where classNameId = &#63; and classPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @return the range of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameIdClassPk(
        java.lang.Long classNameId, java.lang.Long classPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology term entities where classNameId = &#63; and classPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameIdClassPk(
        java.lang.Long classNameId, java.lang.Long classPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term entity in the ordered set where classNameId = &#63; and classPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameIdClassPk_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term entity in the ordered set where classNameId = &#63; and classPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameIdClassPk_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology term entities before and after the current ontology term entity in the ordered set where classNameId = &#63; and classPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current ontology term entity
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity[] findByClassNameIdClassPk_PrevAndNext(
        java.lang.Long id, java.lang.Long classNameId, java.lang.Long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ontology term entities where termId = &#63;.
    *
    * @param termId the term ID
    * @return the matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermId(
        java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology term entities where termId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param termId the term ID
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @return the range of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermId(
        java.lang.Long termId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology term entities where termId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param termId the term ID
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermId(
        java.lang.Long termId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term entity in the ordered set where termId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param termId the term ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity findByTermId_First(
        java.lang.Long termId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term entity in the ordered set where termId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param termId the term ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity findByTermId_Last(
        java.lang.Long termId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology term entities before and after the current ontology term entity in the ordered set where termId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current ontology term entity
    * @param termId the term ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity[] findByTermId_PrevAndNext(
        java.lang.Long id, java.lang.Long termId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ontology term entities where termId = &#63; and classNameId = &#63;.
    *
    * @param termId the term ID
    * @param classNameId the class name ID
    * @return the matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermIdClassNameId(
        java.lang.Long termId, java.lang.Long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology term entities where termId = &#63; and classNameId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param termId the term ID
    * @param classNameId the class name ID
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @return the range of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermIdClassNameId(
        java.lang.Long termId, java.lang.Long classNameId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology term entities where termId = &#63; and classNameId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param termId the term ID
    * @param classNameId the class name ID
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermIdClassNameId(
        java.lang.Long termId, java.lang.Long classNameId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term entity in the ordered set where termId = &#63; and classNameId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param termId the term ID
    * @param classNameId the class name ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity findByTermIdClassNameId_First(
        java.lang.Long termId, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term entity in the ordered set where termId = &#63; and classNameId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param termId the term ID
    * @param classNameId the class name ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity findByTermIdClassNameId_Last(
        java.lang.Long termId, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology term entities before and after the current ontology term entity in the ordered set where termId = &#63; and classNameId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current ontology term entity
    * @param termId the term ID
    * @param classNameId the class name ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ontology term entity
    * @throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity[] findByTermIdClassNameId_PrevAndNext(
        java.lang.Long id, java.lang.Long termId, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ontology term entities.
    *
    * @return the ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology term entities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @return the range of ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology term entities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology term entities where classNameId = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByClassNameId(java.lang.Long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology term entities where classNameId = &#63; and classPK = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @throws SystemException if a system exception occurred
    */
    public void removeByClassNameIdClassPk(java.lang.Long classNameId,
        java.lang.Long classPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology term entities where termId = &#63; from the database.
    *
    * @param termId the term ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByTermId(java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology term entities where termId = &#63; and classNameId = &#63; from the database.
    *
    * @param termId the term ID
    * @param classNameId the class name ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByTermIdClassNameId(java.lang.Long termId,
        java.lang.Long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology term entities from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology term entities where classNameId = &#63;.
    *
    * @param classNameId the class name ID
    * @return the number of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public int countByClassNameId(java.lang.Long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology term entities where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the number of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public int countByClassNameIdClassPk(java.lang.Long classNameId,
        java.lang.Long classPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology term entities where termId = &#63;.
    *
    * @param termId the term ID
    * @return the number of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public int countByTermId(java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology term entities where termId = &#63; and classNameId = &#63;.
    *
    * @param termId the term ID
    * @param classNameId the class name ID
    * @return the number of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public int countByTermIdClassNameId(java.lang.Long termId,
        java.lang.Long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology term entities.
    *
    * @return the number of ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
