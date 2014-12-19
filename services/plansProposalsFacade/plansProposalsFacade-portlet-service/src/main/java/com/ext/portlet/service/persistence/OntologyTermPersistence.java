package com.ext.portlet.service.persistence;

import com.ext.portlet.model.OntologyTerm;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ontology term service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermPersistenceImpl
 * @see OntologyTermUtil
 * @generated
 */
public interface OntologyTermPersistence extends BasePersistence<OntologyTerm> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link OntologyTermUtil} to access the ontology term persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ontology terms where parentId = &#63;.
    *
    * @param parentId the parent ID
    * @return the matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findByParentId(
        long parentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology terms where parentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param parentId the parent ID
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @return the range of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findByParentId(
        long parentId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology terms where parentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param parentId the parent ID
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findByParentId(
        long parentId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term in the ordered set where parentId = &#63;.
    *
    * @param parentId the parent ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm findByParentId_First(
        long parentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term in the ordered set where parentId = &#63;.
    *
    * @param parentId the parent ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term, or <code>null</code> if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm fetchByParentId_First(
        long parentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term in the ordered set where parentId = &#63;.
    *
    * @param parentId the parent ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm findByParentId_Last(
        long parentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term in the ordered set where parentId = &#63;.
    *
    * @param parentId the parent ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term, or <code>null</code> if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm fetchByParentId_Last(
        long parentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology terms before and after the current ontology term in the ordered set where parentId = &#63;.
    *
    * @param id the primary key of the current ontology term
    * @param parentId the parent ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm[] findByParentId_PrevAndNext(
        long id, long parentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology terms where parentId = &#63; from the database.
    *
    * @param parentId the parent ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByParentId(long parentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology terms where parentId = &#63;.
    *
    * @param parentId the parent ID
    * @return the number of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public int countByParentId(long parentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ontology terms where parentId = &#63; and ontologySpaceId = &#63;.
    *
    * @param parentId the parent ID
    * @param ontologySpaceId the ontology space ID
    * @return the matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findByParentIdSpaceId(
        long parentId, long ontologySpaceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology terms where parentId = &#63; and ontologySpaceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param parentId the parent ID
    * @param ontologySpaceId the ontology space ID
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @return the range of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findByParentIdSpaceId(
        long parentId, long ontologySpaceId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology terms where parentId = &#63; and ontologySpaceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param parentId the parent ID
    * @param ontologySpaceId the ontology space ID
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findByParentIdSpaceId(
        long parentId, long ontologySpaceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term in the ordered set where parentId = &#63; and ontologySpaceId = &#63;.
    *
    * @param parentId the parent ID
    * @param ontologySpaceId the ontology space ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm findByParentIdSpaceId_First(
        long parentId, long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term in the ordered set where parentId = &#63; and ontologySpaceId = &#63;.
    *
    * @param parentId the parent ID
    * @param ontologySpaceId the ontology space ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term, or <code>null</code> if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm fetchByParentIdSpaceId_First(
        long parentId, long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term in the ordered set where parentId = &#63; and ontologySpaceId = &#63;.
    *
    * @param parentId the parent ID
    * @param ontologySpaceId the ontology space ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm findByParentIdSpaceId_Last(
        long parentId, long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term in the ordered set where parentId = &#63; and ontologySpaceId = &#63;.
    *
    * @param parentId the parent ID
    * @param ontologySpaceId the ontology space ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term, or <code>null</code> if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm fetchByParentIdSpaceId_Last(
        long parentId, long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology terms before and after the current ontology term in the ordered set where parentId = &#63; and ontologySpaceId = &#63;.
    *
    * @param id the primary key of the current ontology term
    * @param parentId the parent ID
    * @param ontologySpaceId the ontology space ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm[] findByParentIdSpaceId_PrevAndNext(
        long id, long parentId, long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology terms where parentId = &#63; and ontologySpaceId = &#63; from the database.
    *
    * @param parentId the parent ID
    * @param ontologySpaceId the ontology space ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByParentIdSpaceId(long parentId, long ontologySpaceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology terms where parentId = &#63; and ontologySpaceId = &#63;.
    *
    * @param parentId the parent ID
    * @param ontologySpaceId the ontology space ID
    * @return the number of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public int countByParentIdSpaceId(long parentId, long ontologySpaceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ontology terms where ontologySpaceId = &#63;.
    *
    * @param ontologySpaceId the ontology space ID
    * @return the matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findBySpaceId(
        long ontologySpaceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology terms where ontologySpaceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ontologySpaceId the ontology space ID
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @return the range of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findBySpaceId(
        long ontologySpaceId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology terms where ontologySpaceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ontologySpaceId the ontology space ID
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findBySpaceId(
        long ontologySpaceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term in the ordered set where ontologySpaceId = &#63;.
    *
    * @param ontologySpaceId the ontology space ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm findBySpaceId_First(
        long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term in the ordered set where ontologySpaceId = &#63;.
    *
    * @param ontologySpaceId the ontology space ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term, or <code>null</code> if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm fetchBySpaceId_First(
        long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term in the ordered set where ontologySpaceId = &#63;.
    *
    * @param ontologySpaceId the ontology space ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm findBySpaceId_Last(
        long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term in the ordered set where ontologySpaceId = &#63;.
    *
    * @param ontologySpaceId the ontology space ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term, or <code>null</code> if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm fetchBySpaceId_Last(
        long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology terms before and after the current ontology term in the ordered set where ontologySpaceId = &#63;.
    *
    * @param id the primary key of the current ontology term
    * @param ontologySpaceId the ontology space ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm[] findBySpaceId_PrevAndNext(
        long id, long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology terms where ontologySpaceId = &#63; from the database.
    *
    * @param ontologySpaceId the ontology space ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySpaceId(long ontologySpaceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology terms where ontologySpaceId = &#63;.
    *
    * @param ontologySpaceId the ontology space ID
    * @return the number of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public int countBySpaceId(long ontologySpaceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ontology terms where name = &#63;.
    *
    * @param name the name
    * @return the matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology terms where name = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param name the name
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @return the range of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findByName(
        java.lang.String name, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology terms where name = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param name the name
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findByName(
        java.lang.String name, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term in the ordered set where name = &#63;.
    *
    * @param name the name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm findByName_First(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ontology term in the ordered set where name = &#63;.
    *
    * @param name the name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ontology term, or <code>null</code> if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm fetchByName_First(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term in the ordered set where name = &#63;.
    *
    * @param name the name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm findByName_Last(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ontology term in the ordered set where name = &#63;.
    *
    * @param name the name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ontology term, or <code>null</code> if a matching ontology term could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm fetchByName_Last(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology terms before and after the current ontology term in the ordered set where name = &#63;.
    *
    * @param id the primary key of the current ontology term
    * @param name the name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm[] findByName_PrevAndNext(
        long id, java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology terms where name = &#63; from the database.
    *
    * @param name the name
    * @throws SystemException if a system exception occurred
    */
    public void removeByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology terms where name = &#63;.
    *
    * @param name the name
    * @return the number of matching ontology terms
    * @throws SystemException if a system exception occurred
    */
    public int countByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the ontology term in the entity cache if it is enabled.
    *
    * @param ontologyTerm the ontology term
    */
    public void cacheResult(com.ext.portlet.model.OntologyTerm ontologyTerm);

    /**
    * Caches the ontology terms in the entity cache if it is enabled.
    *
    * @param ontologyTerms the ontology terms
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.OntologyTerm> ontologyTerms);

    /**
    * Creates a new ontology term with the primary key. Does not add the ontology term to the database.
    *
    * @param id the primary key for the new ontology term
    * @return the new ontology term
    */
    public com.ext.portlet.model.OntologyTerm create(long id);

    /**
    * Removes the ontology term with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the ontology term
    * @return the ontology term that was removed
    * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm remove(long id)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.OntologyTerm updateImpl(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology term with the primary key or throws a {@link com.ext.portlet.NoSuchOntologyTermException} if it could not be found.
    *
    * @param id the primary key of the ontology term
    * @return the ontology term
    * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchOntologyTermException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology term with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the ontology term
    * @return the ontology term, or <code>null</code> if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ontology terms.
    *
    * @return the ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology terms.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @return the range of ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology terms.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologyTerm> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology terms from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology terms.
    *
    * @return the number of ontology terms
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
