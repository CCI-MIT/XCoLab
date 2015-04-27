package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalContestPhaseAttribute;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal contest phase attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributePersistenceImpl
 * @see ProposalContestPhaseAttributeUtil
 * @generated
 */
public interface ProposalContestPhaseAttributePersistence
    extends BasePersistence<ProposalContestPhaseAttribute> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalContestPhaseAttributeUtil} to access the proposal contest phase attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeException} if it could not be found.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param name the name
    * @param additionalId the additional ID
    * @return the matching proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute findByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, java.lang.String name,
        long additionalId)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param name the name
    * @param additionalId the additional ID
    * @return the matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, java.lang.String name,
        long additionalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param name the name
    * @param additionalId the additional ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, java.lang.String name,
        long additionalId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param name the name
    * @param additionalId the additional ID
    * @return the proposal contest phase attribute that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute removeByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, java.lang.String name,
        long additionalId)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param name the name
    * @param additionalId the additional ID
    * @return the number of matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, java.lang.String name,
        long additionalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @return the matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @return the range of matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute findByProposalIdContestPhaseId_First(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchByProposalIdContestPhaseId_First(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute findByProposalIdContestPhaseId_Last(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchByProposalIdContestPhaseId_Last(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attributes before and after the current proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param id the primary key of the current proposal contest phase attribute
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute[] findByProposalIdContestPhaseId_PrevAndNext(
        long id, long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @return the number of matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal contest phase attributes where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @return the matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findByContestPhaseId(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal contest phase attributes where contestPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestPhaseId the contest phase ID
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @return the range of matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findByContestPhaseId(
        long contestPhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal contest phase attributes where contestPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestPhaseId the contest phase ID
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findByContestPhaseId(
        long contestPhaseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal contest phase attribute in the ordered set where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute findByContestPhaseId_First(
        long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal contest phase attribute in the ordered set where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchByContestPhaseId_First(
        long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal contest phase attribute in the ordered set where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute findByContestPhaseId_Last(
        long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal contest phase attribute in the ordered set where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchByContestPhaseId_Last(
        long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attributes before and after the current proposal contest phase attribute in the ordered set where contestPhaseId = &#63;.
    *
    * @param id the primary key of the current proposal contest phase attribute
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute[] findByContestPhaseId_PrevAndNext(
        long id, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal contest phase attributes where contestPhaseId = &#63; from the database.
    *
    * @param contestPhaseId the contest phase ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestPhaseId(long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal contest phase attributes where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @return the number of matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public int countByContestPhaseId(long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal contest phase attributes where contestPhaseId = &#63; and proposalId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param proposalId the proposal ID
    * @return the matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findByContestPhaseIdAndProposalId(
        long contestPhaseId, long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal contest phase attributes where contestPhaseId = &#63; and proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestPhaseId the contest phase ID
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @return the range of matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findByContestPhaseIdAndProposalId(
        long contestPhaseId, long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal contest phase attributes where contestPhaseId = &#63; and proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestPhaseId the contest phase ID
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findByContestPhaseIdAndProposalId(
        long contestPhaseId, long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal contest phase attribute in the ordered set where contestPhaseId = &#63; and proposalId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute findByContestPhaseIdAndProposalId_First(
        long contestPhaseId, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal contest phase attribute in the ordered set where contestPhaseId = &#63; and proposalId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchByContestPhaseIdAndProposalId_First(
        long contestPhaseId, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal contest phase attribute in the ordered set where contestPhaseId = &#63; and proposalId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute findByContestPhaseIdAndProposalId_Last(
        long contestPhaseId, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal contest phase attribute in the ordered set where contestPhaseId = &#63; and proposalId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchByContestPhaseIdAndProposalId_Last(
        long contestPhaseId, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attributes before and after the current proposal contest phase attribute in the ordered set where contestPhaseId = &#63; and proposalId = &#63;.
    *
    * @param id the primary key of the current proposal contest phase attribute
    * @param contestPhaseId the contest phase ID
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute[] findByContestPhaseIdAndProposalId_PrevAndNext(
        long id, long contestPhaseId, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal contest phase attributes where contestPhaseId = &#63; and proposalId = &#63; from the database.
    *
    * @param contestPhaseId the contest phase ID
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestPhaseIdAndProposalId(long contestPhaseId,
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal contest phase attributes where contestPhaseId = &#63; and proposalId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param proposalId the proposal ID
    * @return the number of matching proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public int countByContestPhaseIdAndProposalId(long contestPhaseId,
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the proposal contest phase attribute in the entity cache if it is enabled.
    *
    * @param proposalContestPhaseAttribute the proposal contest phase attribute
    */
    public void cacheResult(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute);

    /**
    * Caches the proposal contest phase attributes in the entity cache if it is enabled.
    *
    * @param proposalContestPhaseAttributes the proposal contest phase attributes
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> proposalContestPhaseAttributes);

    /**
    * Creates a new proposal contest phase attribute with the primary key. Does not add the proposal contest phase attribute to the database.
    *
    * @param id the primary key for the new proposal contest phase attribute
    * @return the new proposal contest phase attribute
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute create(long id);

    /**
    * Removes the proposal contest phase attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal contest phase attribute
    * @return the proposal contest phase attribute that was removed
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute remove(long id)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalContestPhaseAttribute updateImpl(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attribute with the primary key or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeException} if it could not be found.
    *
    * @param id the primary key of the proposal contest phase attribute
    * @return the proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the proposal contest phase attribute
    * @return the proposal contest phase attribute, or <code>null</code> if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal contest phase attributes.
    *
    * @return the proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal contest phase attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @return the range of proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal contest phase attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal contest phase attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal contest phase attributes.
    *
    * @return the number of proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
