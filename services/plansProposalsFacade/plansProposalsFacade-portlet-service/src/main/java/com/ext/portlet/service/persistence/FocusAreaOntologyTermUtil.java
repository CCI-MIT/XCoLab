package com.ext.portlet.service.persistence;

import com.ext.portlet.model.FocusAreaOntologyTerm;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the focus area ontology term service. This utility wraps {@link FocusAreaOntologyTermPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTermPersistence
 * @see FocusAreaOntologyTermPersistenceImpl
 * @generated
 */
public class FocusAreaOntologyTermUtil {
    private static FocusAreaOntologyTermPersistence _persistence;

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
    public static void clearCache(FocusAreaOntologyTerm focusAreaOntologyTerm) {
        getPersistence().clearCache(focusAreaOntologyTerm);
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
    public static List<FocusAreaOntologyTerm> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<FocusAreaOntologyTerm> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<FocusAreaOntologyTerm> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static FocusAreaOntologyTerm update(
        FocusAreaOntologyTerm focusAreaOntologyTerm) throws SystemException {
        return getPersistence().update(focusAreaOntologyTerm);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static FocusAreaOntologyTerm update(
        FocusAreaOntologyTerm focusAreaOntologyTerm,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(focusAreaOntologyTerm, serviceContext);
    }

    /**
    * Returns all the focus area ontology terms where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @return the matching focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findByFocusAreaId(
        long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByFocusAreaId(focusAreaId);
    }

    /**
    * Returns a range of all the focus area ontology terms where focusAreaId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.FocusAreaOntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param focusAreaId the focus area ID
    * @param start the lower bound of the range of focus area ontology terms
    * @param end the upper bound of the range of focus area ontology terms (not inclusive)
    * @return the range of matching focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findByFocusAreaId(
        long focusAreaId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByFocusAreaId(focusAreaId, start, end);
    }

    /**
    * Returns an ordered range of all the focus area ontology terms where focusAreaId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.FocusAreaOntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param focusAreaId the focus area ID
    * @param start the lower bound of the range of focus area ontology terms
    * @param end the upper bound of the range of focus area ontology terms (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findByFocusAreaId(
        long focusAreaId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFocusAreaId(focusAreaId, start, end, orderByComparator);
    }

    /**
    * Returns the first focus area ontology term in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching focus area ontology term
    * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a matching focus area ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.FocusAreaOntologyTerm findByFocusAreaId_First(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFocusAreaId_First(focusAreaId, orderByComparator);
    }

    /**
    * Returns the first focus area ontology term in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching focus area ontology term, or <code>null</code> if a matching focus area ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.FocusAreaOntologyTerm fetchByFocusAreaId_First(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFocusAreaId_First(focusAreaId, orderByComparator);
    }

    /**
    * Returns the last focus area ontology term in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching focus area ontology term
    * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a matching focus area ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.FocusAreaOntologyTerm findByFocusAreaId_Last(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFocusAreaId_Last(focusAreaId, orderByComparator);
    }

    /**
    * Returns the last focus area ontology term in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching focus area ontology term, or <code>null</code> if a matching focus area ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.FocusAreaOntologyTerm fetchByFocusAreaId_Last(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFocusAreaId_Last(focusAreaId, orderByComparator);
    }

    /**
    * Returns the focus area ontology terms before and after the current focus area ontology term in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaOntologyTermPK the primary key of the current focus area ontology term
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next focus area ontology term
    * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.FocusAreaOntologyTerm[] findByFocusAreaId_PrevAndNext(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK,
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFocusAreaId_PrevAndNext(focusAreaOntologyTermPK,
            focusAreaId, orderByComparator);
    }

    /**
    * Removes all the focus area ontology terms where focusAreaId = &#63; from the database.
    *
    * @param focusAreaId the focus area ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByFocusAreaId(long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByFocusAreaId(focusAreaId);
    }

    /**
    * Returns the number of focus area ontology terms where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @return the number of matching focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public static int countByFocusAreaId(long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByFocusAreaId(focusAreaId);
    }

    /**
    * Caches the focus area ontology term in the entity cache if it is enabled.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    */
    public static void cacheResult(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm) {
        getPersistence().cacheResult(focusAreaOntologyTerm);
    }

    /**
    * Caches the focus area ontology terms in the entity cache if it is enabled.
    *
    * @param focusAreaOntologyTerms the focus area ontology terms
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> focusAreaOntologyTerms) {
        getPersistence().cacheResult(focusAreaOntologyTerms);
    }

    /**
    * Creates a new focus area ontology term with the primary key. Does not add the focus area ontology term to the database.
    *
    * @param focusAreaOntologyTermPK the primary key for the new focus area ontology term
    * @return the new focus area ontology term
    */
    public static com.ext.portlet.model.FocusAreaOntologyTerm create(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK) {
        return getPersistence().create(focusAreaOntologyTermPK);
    }

    /**
    * Removes the focus area ontology term with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @return the focus area ontology term that was removed
    * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.FocusAreaOntologyTerm remove(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.ext.portlet.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(focusAreaOntologyTermPK);
    }

    public static com.ext.portlet.model.FocusAreaOntologyTerm updateImpl(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(focusAreaOntologyTerm);
    }

    /**
    * Returns the focus area ontology term with the primary key or throws a {@link com.ext.portlet.NoSuchFocusAreaOntologyTermException} if it could not be found.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @return the focus area ontology term
    * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.FocusAreaOntologyTerm findByPrimaryKey(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.ext.portlet.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(focusAreaOntologyTermPK);
    }

    /**
    * Returns the focus area ontology term with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @return the focus area ontology term, or <code>null</code> if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.FocusAreaOntologyTerm fetchByPrimaryKey(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(focusAreaOntologyTermPK);
    }

    /**
    * Returns all the focus area ontology terms.
    *
    * @return the focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the focus area ontology terms.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.FocusAreaOntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of focus area ontology terms
    * @param end the upper bound of the range of focus area ontology terms (not inclusive)
    * @return the range of focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the focus area ontology terms.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.FocusAreaOntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of focus area ontology terms
    * @param end the upper bound of the range of focus area ontology terms (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the focus area ontology terms from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of focus area ontology terms.
    *
    * @return the number of focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static FocusAreaOntologyTermPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (FocusAreaOntologyTermPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    FocusAreaOntologyTermPersistence.class.getName());

            ReferenceRegistry.registerReference(FocusAreaOntologyTermUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(FocusAreaOntologyTermPersistence persistence) {
    }
}
