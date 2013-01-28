package com.ext.portlet.service.persistence;

import com.ext.portlet.model.OntologyTermEntity;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ontology term entity service. This utility wraps {@link OntologyTermEntityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntityPersistence
 * @see OntologyTermEntityPersistenceImpl
 * @generated
 */
public class OntologyTermEntityUtil {
    private static OntologyTermEntityPersistence _persistence;

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
    public static void clearCache(OntologyTermEntity ontologyTermEntity) {
        getPersistence().clearCache(ontologyTermEntity);
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
    public static List<OntologyTermEntity> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<OntologyTermEntity> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<OntologyTermEntity> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static OntologyTermEntity update(
        OntologyTermEntity ontologyTermEntity, boolean merge)
        throws SystemException {
        return getPersistence().update(ontologyTermEntity, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static OntologyTermEntity update(
        OntologyTermEntity ontologyTermEntity, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ontologyTermEntity, merge, serviceContext);
    }

    /**
    * Caches the ontology term entity in the entity cache if it is enabled.
    *
    * @param ontologyTermEntity the ontology term entity
    */
    public static void cacheResult(
        com.ext.portlet.model.OntologyTermEntity ontologyTermEntity) {
        getPersistence().cacheResult(ontologyTermEntity);
    }

    /**
    * Caches the ontology term entities in the entity cache if it is enabled.
    *
    * @param ontologyTermEntities the ontology term entities
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.OntologyTermEntity> ontologyTermEntities) {
        getPersistence().cacheResult(ontologyTermEntities);
    }

    /**
    * Creates a new ontology term entity with the primary key. Does not add the ontology term entity to the database.
    *
    * @param id the primary key for the new ontology term entity
    * @return the new ontology term entity
    */
    public static com.ext.portlet.model.OntologyTermEntity create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the ontology term entity with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the ontology term entity
    * @return the ontology term entity that was removed
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity remove(long id)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.OntologyTermEntity updateImpl(
        com.ext.portlet.model.OntologyTermEntity ontologyTermEntity,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ontologyTermEntity, merge);
    }

    /**
    * Returns the ontology term entity with the primary key or throws a {@link com.ext.portlet.NoSuchOntologyTermEntityException} if it could not be found.
    *
    * @param id the primary key of the ontology term entity
    * @return the ontology term entity
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the ontology term entity with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the ontology term entity
    * @return the ontology term entity, or <code>null</code> if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the ontology term entities where classNameId = &#63;.
    *
    * @param classNameId the class name ID
    * @return the matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByClassNameId(
        long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByClassNameId(classNameId);
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByClassNameId(
        long classNameId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByClassNameId(classNameId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByClassNameId(
        long classNameId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameId(classNameId, start, end, orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity findByClassNameId_First(
        long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameId_First(classNameId, orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity findByClassNameId_Last(
        long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameId_Last(classNameId, orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity[] findByClassNameId_PrevAndNext(
        long id, long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameId_PrevAndNext(id, classNameId,
            orderByComparator);
    }

    /**
    * Returns all the ontology term entities where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByClassNameIdClassPk(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByClassNameIdClassPk(classNameId, classPK);
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByClassNameIdClassPk(
        long classNameId, long classPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPk(classNameId, classPK, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByClassNameIdClassPk(
        long classNameId, long classPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPk(classNameId, classPK, start, end,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity findByClassNameIdClassPk_First(
        long classNameId, long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPk_First(classNameId, classPK,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity findByClassNameIdClassPk_Last(
        long classNameId, long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPk_Last(classNameId, classPK,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity[] findByClassNameIdClassPk_PrevAndNext(
        long id, long classNameId, long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPk_PrevAndNext(id, classNameId,
            classPK, orderByComparator);
    }

    /**
    * Returns all the ontology term entities where termId = &#63;.
    *
    * @param termId the term ID
    * @return the matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByTermId(
        long termId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTermId(termId);
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByTermId(
        long termId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTermId(termId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByTermId(
        long termId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTermId(termId, start, end, orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity findByTermId_First(
        long termId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTermId_First(termId, orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity findByTermId_Last(
        long termId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTermId_Last(termId, orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity[] findByTermId_PrevAndNext(
        long id, long termId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTermId_PrevAndNext(id, termId, orderByComparator);
    }

    /**
    * Returns all the ontology term entities where termId = &#63; and classNameId = &#63;.
    *
    * @param termId the term ID
    * @param classNameId the class name ID
    * @return the matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByTermIdClassNameId(
        long termId, long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTermIdClassNameId(termId, classNameId);
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByTermIdClassNameId(
        long termId, long classNameId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTermIdClassNameId(termId, classNameId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findByTermIdClassNameId(
        long termId, long classNameId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTermIdClassNameId(termId, classNameId, start, end,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity findByTermIdClassNameId_First(
        long termId, long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTermIdClassNameId_First(termId, classNameId,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity findByTermIdClassNameId_Last(
        long termId, long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTermIdClassNameId_Last(termId, classNameId,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity[] findByTermIdClassNameId_PrevAndNext(
        long id, long termId, long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermEntityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTermIdClassNameId_PrevAndNext(id, termId,
            classNameId, orderByComparator);
    }

    /**
    * Returns all the ontology term entities.
    *
    * @return the ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ontology term entities where classNameId = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByClassNameId(long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByClassNameId(classNameId);
    }

    /**
    * Removes all the ontology term entities where classNameId = &#63; and classPK = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @throws SystemException if a system exception occurred
    */
    public static void removeByClassNameIdClassPk(long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByClassNameIdClassPk(classNameId, classPK);
    }

    /**
    * Removes all the ontology term entities where termId = &#63; from the database.
    *
    * @param termId the term ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTermId(long termId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByTermId(termId);
    }

    /**
    * Removes all the ontology term entities where termId = &#63; and classNameId = &#63; from the database.
    *
    * @param termId the term ID
    * @param classNameId the class name ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTermIdClassNameId(long termId, long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByTermIdClassNameId(termId, classNameId);
    }

    /**
    * Removes all the ontology term entities from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ontology term entities where classNameId = &#63;.
    *
    * @param classNameId the class name ID
    * @return the number of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static int countByClassNameId(long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByClassNameId(classNameId);
    }

    /**
    * Returns the number of ontology term entities where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the number of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static int countByClassNameIdClassPk(long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByClassNameIdClassPk(classNameId, classPK);
    }

    /**
    * Returns the number of ontology term entities where termId = &#63;.
    *
    * @param termId the term ID
    * @return the number of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static int countByTermId(long termId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByTermId(termId);
    }

    /**
    * Returns the number of ontology term entities where termId = &#63; and classNameId = &#63;.
    *
    * @param termId the term ID
    * @param classNameId the class name ID
    * @return the number of matching ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static int countByTermIdClassNameId(long termId, long classNameId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByTermIdClassNameId(termId, classNameId);
    }

    /**
    * Returns the number of ontology term entities.
    *
    * @return the number of ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static OntologyTermEntityPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (OntologyTermEntityPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    OntologyTermEntityPersistence.class.getName());

            ReferenceRegistry.registerReference(OntologyTermEntityUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(OntologyTermEntityPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(OntologyTermEntityUtil.class,
            "_persistence");
    }
}
