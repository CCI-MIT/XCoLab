package com.ext.portlet.service.persistence;

import com.ext.portlet.model.FocusAreaOntologyTerm;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the focus area ontology term service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTermPersistenceImpl
 * @see FocusAreaOntologyTermUtil
 * @generated
 */
public interface FocusAreaOntologyTermPersistence extends BasePersistence<FocusAreaOntologyTerm> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FocusAreaOntologyTermUtil} to access the focus area ontology term persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the focus area ontology terms where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @return the matching focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findByFocusAreaId(
        long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findByFocusAreaId(
        long focusAreaId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findByFocusAreaId(
        long focusAreaId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first focus area ontology term in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching focus area ontology term
    * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a matching focus area ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm findByFocusAreaId_First(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first focus area ontology term in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching focus area ontology term, or <code>null</code> if a matching focus area ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm fetchByFocusAreaId_First(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last focus area ontology term in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching focus area ontology term
    * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a matching focus area ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm findByFocusAreaId_Last(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last focus area ontology term in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching focus area ontology term, or <code>null</code> if a matching focus area ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm fetchByFocusAreaId_Last(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.FocusAreaOntologyTerm[] findByFocusAreaId_PrevAndNext(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK, long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the focus area ontology terms where focusAreaId = &#63; from the database.
    *
    * @param focusAreaId the focus area ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByFocusAreaId(long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of focus area ontology terms where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @return the number of matching focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public int countByFocusAreaId(long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the focus area ontology term in the entity cache if it is enabled.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    */
    public void cacheResult(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm);

    /**
    * Caches the focus area ontology terms in the entity cache if it is enabled.
    *
    * @param focusAreaOntologyTerms the focus area ontology terms
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> focusAreaOntologyTerms);

    /**
    * Creates a new focus area ontology term with the primary key. Does not add the focus area ontology term to the database.
    *
    * @param focusAreaOntologyTermPK the primary key for the new focus area ontology term
    * @return the new focus area ontology term
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm create(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK);

    /**
    * Removes the focus area ontology term with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @return the focus area ontology term that was removed
    * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm remove(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.ext.portlet.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.FocusAreaOntologyTerm updateImpl(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the focus area ontology term with the primary key or throws a {@link com.ext.portlet.NoSuchFocusAreaOntologyTermException} if it could not be found.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @return the focus area ontology term
    * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm findByPrimaryKey(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.ext.portlet.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the focus area ontology term with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @return the focus area ontology term, or <code>null</code> if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm fetchByPrimaryKey(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the focus area ontology terms.
    *
    * @return the focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the focus area ontology terms from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of focus area ontology terms.
    *
    * @return the number of focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
