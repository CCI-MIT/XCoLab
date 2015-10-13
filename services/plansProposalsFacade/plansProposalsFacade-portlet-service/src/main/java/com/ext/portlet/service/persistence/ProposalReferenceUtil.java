package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalReference;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the proposal reference service. This utility wraps {@link ProposalReferencePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalReferencePersistence
 * @see ProposalReferencePersistenceImpl
 * @generated
 */
public class ProposalReferenceUtil {
    private static ProposalReferencePersistence _persistence;

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
    public static void clearCache(ProposalReference proposalReference) {
        getPersistence().clearCache(proposalReference);
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
    public static List<ProposalReference> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProposalReference> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProposalReference> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ProposalReference update(ProposalReference proposalReference)
        throws SystemException {
        return getPersistence().update(proposalReference);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ProposalReference update(
        ProposalReference proposalReference, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(proposalReference, serviceContext);
    }

    /**
    * Returns all the proposal references where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching proposal references
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalReference> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId);
    }

    /**
    * Returns a range of all the proposal references where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal references
    * @param end the upper bound of the range of proposal references (not inclusive)
    * @return the range of matching proposal references
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalReference> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal references where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal references
    * @param end the upper bound of the range of proposal references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal references
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalReference> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId(proposalId, start, end, orderByComparator);
    }

    /**
    * Returns the first proposal reference in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal reference
    * @throws com.ext.portlet.NoSuchProposalReferenceException if a matching proposal reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalReferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the first proposal reference in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal reference, or <code>null</code> if a matching proposal reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the last proposal reference in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal reference
    * @throws com.ext.portlet.NoSuchProposalReferenceException if a matching proposal reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalReferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the last proposal reference in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal reference, or <code>null</code> if a matching proposal reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the proposal references before and after the current proposal reference in the ordered set where proposalId = &#63;.
    *
    * @param proposalReferencePK the primary key of the current proposal reference
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal reference
    * @throws com.ext.portlet.NoSuchProposalReferenceException if a proposal reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference[] findByProposalId_PrevAndNext(
        ProposalReferencePK proposalReferencePK, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalReferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_PrevAndNext(proposalReferencePK,
            proposalId, orderByComparator);
    }

    /**
    * Removes all the proposal references where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByProposalId(proposalId);
    }

    /**
    * Returns the number of proposal references where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching proposal references
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByProposalId(proposalId);
    }

    /**
    * Returns all the proposal references where subProposalId = &#63;.
    *
    * @param subProposalId the sub proposal ID
    * @return the matching proposal references
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalReference> findBySubProposalId(
        long subProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySubProposalId(subProposalId);
    }

    /**
    * Returns a range of all the proposal references where subProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param subProposalId the sub proposal ID
    * @param start the lower bound of the range of proposal references
    * @param end the upper bound of the range of proposal references (not inclusive)
    * @return the range of matching proposal references
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalReference> findBySubProposalId(
        long subProposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySubProposalId(subProposalId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal references where subProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param subProposalId the sub proposal ID
    * @param start the lower bound of the range of proposal references
    * @param end the upper bound of the range of proposal references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal references
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalReference> findBySubProposalId(
        long subProposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubProposalId(subProposalId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first proposal reference in the ordered set where subProposalId = &#63;.
    *
    * @param subProposalId the sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal reference
    * @throws com.ext.portlet.NoSuchProposalReferenceException if a matching proposal reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference findBySubProposalId_First(
        long subProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalReferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubProposalId_First(subProposalId, orderByComparator);
    }

    /**
    * Returns the first proposal reference in the ordered set where subProposalId = &#63;.
    *
    * @param subProposalId the sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal reference, or <code>null</code> if a matching proposal reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference fetchBySubProposalId_First(
        long subProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySubProposalId_First(subProposalId, orderByComparator);
    }

    /**
    * Returns the last proposal reference in the ordered set where subProposalId = &#63;.
    *
    * @param subProposalId the sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal reference
    * @throws com.ext.portlet.NoSuchProposalReferenceException if a matching proposal reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference findBySubProposalId_Last(
        long subProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalReferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubProposalId_Last(subProposalId, orderByComparator);
    }

    /**
    * Returns the last proposal reference in the ordered set where subProposalId = &#63;.
    *
    * @param subProposalId the sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal reference, or <code>null</code> if a matching proposal reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference fetchBySubProposalId_Last(
        long subProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySubProposalId_Last(subProposalId, orderByComparator);
    }

    /**
    * Returns the proposal references before and after the current proposal reference in the ordered set where subProposalId = &#63;.
    *
    * @param proposalReferencePK the primary key of the current proposal reference
    * @param subProposalId the sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal reference
    * @throws com.ext.portlet.NoSuchProposalReferenceException if a proposal reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference[] findBySubProposalId_PrevAndNext(
        ProposalReferencePK proposalReferencePK, long subProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalReferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubProposalId_PrevAndNext(proposalReferencePK,
            subProposalId, orderByComparator);
    }

    /**
    * Removes all the proposal references where subProposalId = &#63; from the database.
    *
    * @param subProposalId the sub proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySubProposalId(long subProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySubProposalId(subProposalId);
    }

    /**
    * Returns the number of proposal references where subProposalId = &#63;.
    *
    * @param subProposalId the sub proposal ID
    * @return the number of matching proposal references
    * @throws SystemException if a system exception occurred
    */
    public static int countBySubProposalId(long subProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySubProposalId(subProposalId);
    }

    /**
    * Caches the proposal reference in the entity cache if it is enabled.
    *
    * @param proposalReference the proposal reference
    */
    public static void cacheResult(
        com.ext.portlet.model.ProposalReference proposalReference) {
        getPersistence().cacheResult(proposalReference);
    }

    /**
    * Caches the proposal references in the entity cache if it is enabled.
    *
    * @param proposalReferences the proposal references
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalReference> proposalReferences) {
        getPersistence().cacheResult(proposalReferences);
    }

    /**
    * Creates a new proposal reference with the primary key. Does not add the proposal reference to the database.
    *
    * @param proposalReferencePK the primary key for the new proposal reference
    * @return the new proposal reference
    */
    public static com.ext.portlet.model.ProposalReference create(
        ProposalReferencePK proposalReferencePK) {
        return getPersistence().create(proposalReferencePK);
    }

    /**
    * Removes the proposal reference with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalReferencePK the primary key of the proposal reference
    * @return the proposal reference that was removed
    * @throws com.ext.portlet.NoSuchProposalReferenceException if a proposal reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference remove(
        ProposalReferencePK proposalReferencePK)
        throws com.ext.portlet.NoSuchProposalReferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(proposalReferencePK);
    }

    public static com.ext.portlet.model.ProposalReference updateImpl(
        com.ext.portlet.model.ProposalReference proposalReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(proposalReference);
    }

    /**
    * Returns the proposal reference with the primary key or throws a {@link com.ext.portlet.NoSuchProposalReferenceException} if it could not be found.
    *
    * @param proposalReferencePK the primary key of the proposal reference
    * @return the proposal reference
    * @throws com.ext.portlet.NoSuchProposalReferenceException if a proposal reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference findByPrimaryKey(
        ProposalReferencePK proposalReferencePK)
        throws com.ext.portlet.NoSuchProposalReferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(proposalReferencePK);
    }

    /**
    * Returns the proposal reference with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param proposalReferencePK the primary key of the proposal reference
    * @return the proposal reference, or <code>null</code> if a proposal reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalReference fetchByPrimaryKey(
        ProposalReferencePK proposalReferencePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(proposalReferencePK);
    }

    /**
    * Returns all the proposal references.
    *
    * @return the proposal references
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalReference> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the proposal references.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal references
    * @param end the upper bound of the range of proposal references (not inclusive)
    * @return the range of proposal references
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalReference> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the proposal references.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal references
    * @param end the upper bound of the range of proposal references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal references
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalReference> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the proposal references from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of proposal references.
    *
    * @return the number of proposal references
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProposalReferencePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProposalReferencePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalReferencePersistence.class.getName());

            ReferenceRegistry.registerReference(ProposalReferenceUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProposalReferencePersistence persistence) {
    }
}
