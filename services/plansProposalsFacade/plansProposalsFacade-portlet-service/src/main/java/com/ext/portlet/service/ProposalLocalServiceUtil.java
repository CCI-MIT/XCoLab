package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Proposal. This utility wraps
 * {@link com.ext.portlet.service.impl.ProposalLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalLocalService
 * @see com.ext.portlet.service.base.ProposalLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalLocalServiceImpl
 * @generated
 */
public class ProposalLocalServiceUtil {
    private static ProposalLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the proposal to the database. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @return the proposal that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal addProposal(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addProposal(proposal);
    }

    /**
    * Creates a new proposal with the primary key. Does not add the proposal to the database.
    *
    * @param proposalId the primary key for the new proposal
    * @return the new proposal
    */
    public static com.ext.portlet.model.Proposal createProposal(long proposalId) {
        return getService().createProposal(proposalId);
    }

    /**
    * Deletes the proposal with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalId the primary key of the proposal
    * @return the proposal that was removed
    * @throws PortalException if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal deleteProposal(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteProposal(proposalId);
    }

    /**
    * Deletes the proposal from the database. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @return the proposal that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal deleteProposal(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteProposal(proposal);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.Proposal fetchProposal(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchProposal(proposalId);
    }

    /**
    * Returns the proposal with the primary key.
    *
    * @param proposalId the primary key of the proposal
    * @return the proposal
    * @throws PortalException if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal getProposal(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposal(proposalId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposals
    * @param end the upper bound of the range of proposals (not inclusive)
    * @return the range of proposals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Proposal> getProposals(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposals(start, end);
    }

    /**
    * Returns the number of proposals.
    *
    * @return the number of proposals
    * @throws SystemException if a system exception occurred
    */
    public static int getProposalsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalsCount();
    }

    /**
    * Updates the proposal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @return the proposal that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal updateProposal(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateProposal(proposal);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    /**
    * <p>
    * Creates new proposal, initializes it and associates it with contest phase. All related entities are
    * created:
    * </p>
    * <ul>
    * <li>liferay group</li>
    * <li>discussion for proposal comments</li>
    * <li>discussion for judges</li>
    * <li>discussion for advisors</li>
    * <li>discussion for</li>
    * </ul>
    * <p>
    * Creation of all entities is wrapped into a transaction.
    * </p>
    *
    * @param authorId       id of proposal author
    * @param contestPhaseId id of a contestPhase
    * @return created proposal
    * @throws SystemException in case of a Liferay error
    * @throws PortalException in case of a Liferay error
    * @author janusz
    */
    public static com.ext.portlet.model.Proposal create(long authorId,
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().create(authorId, contestPhaseId);
    }

    /**
    * <p>
    * Creates new proposal, initializes it and associates it with contest phase. All related entities are
    * created:
    * </p>
    * <ul>
    * <li>liferay group</li>
    * <li>discussion for proposal comments</li>
    * <li>discussion for judges</li>
    * <li>discussion for advisors</li>
    * <li>discussion for</li>
    * </ul>
    * <p>
    * Creation of all entities is wrapped into a transaction.
    * </p>
    *
    * @param authorId       id of proposal author
    * @param contestPhaseId id of a contestPhase
    * @return created proposal
    * @throws SystemException in case of a Liferay error
    * @throws PortalException in case of a Liferay error
    * @author janusz
    */
    public static com.ext.portlet.model.Proposal create(long authorId,
        long contestPhaseId, long proposalId, boolean publishActivity)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .create(authorId, contestPhaseId, proposalId, publishActivity);
    }

    public static void setVisibility(java.lang.Long proposalId,
        java.lang.Boolean visibility, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setVisibility(proposalId, visibility, authorId);
    }

    /**
    * <p>Sets attribute value and creates new version for a proposal that reflects the change</p>
    * <p>The algorithm for setting an attribute value is as follows:</p>
    * <ol>
    * <li>new proposal version is created</li>
    * <li>for each attribute that was already present in the proposal (excluding the one that is currently being set)
    * it is copied to the new version</li>
    * <li>for attribute that is being set it's value (if present) isn't copied to the new version as it gets new value</li>
    * </ol>
    *
    * @param authorId      id of a change author
    * @param proposalId    id of a proposal
    * @param attributeName name of an attribute
    * @param additionalId  additional id for an attribute
    * @param stringValue   string value for an attribute
    * @param numericValue  numeric value for an attribute
    * @param realValue     double value for an attribute
    * @return ProposalAttribute that represents newly set attribute
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public static com.ext.portlet.model.ProposalAttribute setAttribute(
        long authorId, long proposalId, java.lang.String attributeName,
        long additionalId, java.lang.String stringValue, long numericValue,
        double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .setAttribute(authorId, proposalId, attributeName,
            additionalId, stringValue, numericValue, realValue);
    }

    /**
    * <p>Sets attribute value and creates new version for a proposal that reflects the change</p>
    * <p>The algorithm for setting an attribute value is as follows:</p>
    * <ol>
    * <li>new proposal version is created</li>
    * <li>for each attribute that was already present in the proposal (excluding the one that is currently being set)
    * it is copied to the new version</li>
    * <li>for attribute that is being set it's value (if present) isn't copied to the new version as it gets new val`ue</li>
    * </ol>
    *
    * @param authorId      id of a change author
    * @param proposalId    id of a proposal
    * @param attributeName name of an attribute
    * @param additionalId  additional id for an attribute
    * @param stringValue   string value for an attribute
    * @param numericValue  numeric value for an attribute
    * @param realValue     double value for an attribute
    * @param updatedDate   date of update
    * @return ProposalAttribute that represents newly set attribute
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author patrickhiesel
    */
    public static com.ext.portlet.model.ProposalAttribute setAttribute(
        long authorId, long proposalId, java.lang.String attributeName,
        long additionalId, java.lang.String stringValue, long numericValue,
        double realValue, java.util.Date updatedDate, boolean publishActivity)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .setAttribute(authorId, proposalId, attributeName,
            additionalId, stringValue, numericValue, realValue, updatedDate,
            publishActivity);
    }

    /**
    * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
    * it uses nulls/zeros for unspecified values</p>
    *
    * @param authorId
    * @param proposalId
    * @param attributeName
    * @param stringValue
    * @return
    * @throws PortalException
    * @throws SystemException
    */
    public static com.ext.portlet.model.ProposalAttribute setAttribute(
        long authorId, long proposalId, java.lang.String attributeName,
        java.lang.String stringValue, long numericValue, double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .setAttribute(authorId, proposalId, attributeName,
            stringValue, numericValue, realValue);
    }

    /**
    * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
    * it uses nulls/zeros for unspecified values</p>
    *
    * @param authorId
    * @param proposalId
    * @param attributeName
    * @param additionalId
    * @param stringValue
    * @return
    * @throws PortalException
    * @throws SystemException
    */
    public static com.ext.portlet.model.ProposalAttribute setAttribute(
        long authorId, long proposalId, java.lang.String attributeName,
        long additionalId, java.lang.String stringValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .setAttribute(authorId, proposalId, attributeName,
            additionalId, stringValue);
    }

    /**
    * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
    * it uses nulls/zeros for unspecified values</p>
    *
    * @param authorId
    * @param proposalId
    * @param attributeName
    * @param stringValue
    * @return
    * @throws PortalException
    * @throws SystemException
    */
    public static com.ext.portlet.model.ProposalAttribute setAttribute(
        long authorId, long proposalId, java.lang.String attributeName,
        java.lang.String stringValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .setAttribute(authorId, proposalId, attributeName,
            stringValue);
    }

    /**
    * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
    * it uses nulls/zeros for unspecified values</p>
    *
    * @param authorId
    * @param proposalId
    * @param attributeName
    * @param additionalId
    * @param numericValue
    * @return
    * @throws PortalException
    * @throws SystemException
    */
    public static com.ext.portlet.model.ProposalAttribute setAttribute(
        long authorId, long proposalId, java.lang.String attributeName,
        long additionalId, long numericValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .setAttribute(authorId, proposalId, attributeName,
            additionalId, numericValue);
    }

    /**
    * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
    * it uses nulls/zeros for unspecified values</p>
    *
    * @param authorId
    * @param proposalId
    * @param attributeName
    * @param numericValue
    * @return
    * @throws PortalException
    * @throws SystemException
    */
    public static com.ext.portlet.model.ProposalAttribute setAttribute(
        long authorId, long proposalId, java.lang.String attributeName,
        long numericValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .setAttribute(authorId, proposalId, attributeName,
            numericValue);
    }

    /**
    * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
    * it uses nulls/zeros for unspecified values</p>
    *
    * @param authorId
    * @param proposalId
    * @param attributeName
    * @param additionalId
    * @param realValue
    * @return
    * @throws PortalException
    * @throws SystemException
    */
    public static com.ext.portlet.model.ProposalAttribute setAttribute(
        long authorId, long proposalId, java.lang.String attributeName,
        long additionalId, double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .setAttribute(authorId, proposalId, attributeName,
            additionalId, realValue);
    }

    /**
    * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
    * it uses nulls/zeros for unspecified values</p>
    *
    * @param authorId
    * @param proposalId
    * @param attributeName
    * @param realValue
    * @return
    * @throws PortalException
    * @throws SystemException
    */
    public static com.ext.portlet.model.ProposalAttribute setAttribute(
        long authorId, long proposalId, java.lang.String attributeName,
        double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .setAttribute(authorId, proposalId, attributeName, realValue);
    }

    /**
    * <p>Returns all attributes for current version of a proposal.</p>
    *
    * @param proposalId id of a proposal
    * @return list of proposal attributes for current version of a proposal
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttribute> getAttributes(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getAttributes(proposalId);
    }

    /**
    * <p>Returns all attributes for given version of a proposal.</p>
    *
    * @param proposalId id of a proposal
    * @param version    version number of a proposal
    * @return list of proposal attributes for current version of a proposal
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttribute> getAttributes(
        long proposalId, int version)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getAttributes(proposalId, version);
    }

    /**
    * <p>Returns an attribute for current version of a proposal.</p>
    *
    * @param proposalId    id of a proposal
    * @param attributeName name of an attribute
    * @param additionalId  additionalId of an attribute
    * @return proposal attribute
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public static com.ext.portlet.model.ProposalAttribute getAttribute(
        long proposalId, java.lang.String attributeName, long additionalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getAttribute(proposalId, attributeName, additionalId);
    }

    /**
    * <p>Returns an attribute for concrete version of a proposal.</p>
    *
    * @param proposalId    id of a proposal
    * @param version       version of a proposal
    * @param attributeName name of an attribute
    * @param additionalId  additionalId of an attribute
    * @return proposal attribute
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public static com.ext.portlet.model.ProposalAttribute getAttribute(
        long proposalId, int version, java.lang.String attributeName,
        long additionalId)
        throws com.ext.portlet.NoSuchProposalAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getAttribute(proposalId, version, attributeName,
            additionalId);
    }

    /**
    * <p>Removes a proposal attribute. All other proposal attributes in the current version are being promoted to the next version.</p>
    *
    * @param authorId
    * @param attributeToDelete
    * @param publishActivity
    * @throws SystemException
    * @throws PortalException
    */
    public static void removeAttribute(long authorId,
        com.ext.portlet.model.ProposalAttribute attributeToDelete,
        boolean publishActivity)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .removeAttribute(authorId, attributeToDelete, publishActivity);
    }

    /**
    * <p>Removes a proposal attribute. This method is currently only used for the Proposal impact feature to delete already saved proposal impact serieses.</p>
    *
    * @param authorId
    * @param attributeToDelete
    * @throws PortalException
    * @throws SystemException
    */
    public static void removeAttribute(long authorId,
        com.ext.portlet.model.ProposalAttribute attributeToDelete)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().removeAttribute(authorId, attributeToDelete);
    }

    /**
    * <p>Returns a list of all proposal version descriptors.</p>
    *
    * @param proposalId id of a proposal
    * @return list of proposal versions covering entire change history for a proposal
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public static java.util.List<com.ext.portlet.model.ProposalVersion> getProposalVersions(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalVersions(proposalId);
    }

    /**
    * <p>Returns a concrete proposal version descriptor.</p>
    *
    * @param proposalId id of a proposal
    * @param version    version of a proposal
    * @return proposal version descriptor
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public static com.ext.portlet.model.ProposalVersion getProposalVersion(
        long proposalId, int version)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalVersion(proposalId, version);
    }

    /**
    * <p>Returns a list of proposals associated with given contest phase</p>
    *
    * @param contestPhaseId id of a contest phase
    * @return list of proposals from given contest phase
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static java.util.List<com.ext.portlet.model.Proposal> getProposalsInContestPhase(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalsInContestPhase(contestPhaseId);
    }

    public static java.util.List<com.ext.portlet.model.Proposal> getProposalsInContestPhase(
        long contestPhaseId, java.lang.String sortProperty,
        boolean sortAscending, int start, int end)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getProposalsInContestPhase(contestPhaseId, sortProperty,
            sortAscending, start, end);
    }

    /**
    * <p>Returns a list of proposals associated with the given contest phase which are both generally visible and visible in the given contest phase</p>
    *
    * @param contestPhaseId id of a contest phase
    * @return list of proposals from given contest phase
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static java.util.List<com.ext.portlet.model.Proposal> getActiveProposalsInContestPhase(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getActiveProposalsInContestPhase(contestPhaseId);
    }

    /**
    * <p>Returns a list of proposals associated with given contest</p>
    *
    * @param contestId id of a contest phase
    * @return list of proposals from given contest
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static java.util.List<com.ext.portlet.model.Proposal> getProposalsInContest(
        long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalsInContest(contestId);
    }

    /**
    * Retrieves all proposals for which a user is either the author or member of the author group (proposals to which a user has contributed)
    *
    * @param userId The userId of the user
    * @return A list of proposals the user has contributed to
    * @throws SystemException
    */
    public static java.util.List<com.ext.portlet.model.Proposal> getUserProposals(
        long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getUserProposals(userId);
    }

    /**
    * <p>Returns count of proposals associated with given contest phase</p>
    *
    * @param contestPhaseId id of a contest phase
    * @return count of proposals from given contest phase
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static long countProposalsInContestPhase(long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().countProposalsInContestPhase(contestPhaseId);
    }

    /**
    * <p>Returns list of proposal team members</p>
    *
    * @param proposalId proposal id
    * @return list of proposal team members
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static java.util.List<com.liferay.portal.model.User> getMembers(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getMembers(proposalId);
    }

    /**
    * <p>Returns list of proposal supporters</p>
    *
    * @param proposalId proposal id
    * @return list of proposal supporters
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static java.util.List<com.liferay.portal.model.User> getSupporters(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getSupporters(proposalId);
    }

    /**
    * <p>Returns number of proposal supporters</p>
    *
    * @param proposalId proposal id
    * @return number of proposal supporters
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static int getSupportersCount(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getSupportersCount(proposalId);
    }

    /**
    * <p>Returns true if user is a proposal supporter, false otherwise.</p>
    *
    * @param proposalId proposal id
    * @return true if user is a proposal supporter, false otherwise
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static boolean isSupporter(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isSupporter(proposalId, userId);
    }

    /**
    * <p>Adds supporter to a proposal</p>
    *
    * @param proposalId id of a proposal
    * @param userId     id of a supported to be added
    * @throws SystemException in case of an LR error
    * @throws PortalException
    */
    public static void addSupporter(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().addSupporter(proposalId, userId);
    }

    /**
    * <p>Adds supporter to a proposal</p>
    *
    * @param proposalId id of a proposal
    * @param userId     id of a supported to be added
    * @throws SystemException in case of an LR error
    * @throws PortalException
    */
    public static void addSupporter(long proposalId, long userId,
        boolean publishActivity)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().addSupporter(proposalId, userId, publishActivity);
    }

    /**
    * <p>Retracts support from a proposal</p>
    *
    * @param proposalId id of a proposal
    * @param userId     id of a supported to be removed
    * @throws SystemException in case of an LR error
    * @throws PortalException
    */
    public static void removeSupporter(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().removeSupporter(proposalId, userId);
    }

    /**
    * <p>Returns list of users that have voted for a proposal in given contest phase</p>
    *
    * @param proposalId     proposal id
    * @param contestPhaseId contest phase id
    * @return list of proposal voters
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static java.util.List<com.liferay.portal.model.User> getVoters(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getVoters(proposalId, contestPhaseId);
    }

    /**
    * <p>Return number of users that have voted for a proposal in given contest phase</p>
    *
    * @param proposalId     proposal id
    * @param contestPhaseId contest phase id
    * @return number of votes
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static long getVotesCount(long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getVotesCount(proposalId, contestPhaseId);
    }

    /**
    * <p>Adds a user vote to a proposal in context of given contest phase. If user has already voted
    * for different proposal in this phase, then that vote is removed first. User has only one vote
    * in one contestPhase.</p>
    *
    * @param proposalId     id of a proposal
    * @param contestPhaseId id of a contest phase
    * @param userId         id of an user
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static void addVote(long proposalId, long contestPhaseId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().addVote(proposalId, contestPhaseId, userId);
    }

    /**
    * <p>Adds a user vote to a proposal in context of given contest phase. If user has already voted
    * for different proposal in this phase, then that vote is removed first. User has only one vote
    * in one contestPhase.</p>
    *
    * @param proposalId     id of a proposal
    * @param contestPhaseId id of a contest phase
    * @param userId         id of an user
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static void addVote(long proposalId, long contestPhaseId,
        long userId, boolean publishActivity)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().addVote(proposalId, contestPhaseId, userId, publishActivity);
    }

    /**
    * <p>Retracts user vote in context of a contest phase.</p>
    *
    * @param contestPhaseId id of a contest phase
    * @param userId         id of an user
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static void removeVote(long contestPhaseId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().removeVote(contestPhaseId, userId);
    }

    /**
    * <p>Returns number of comments in discussion associated with this proposal</p>
    *
    * @param proposalId proposal id
    * @return number of comments
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static long getCommentsCount(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCommentsCount(proposalId);
    }

    /**
    * <p>Tells if user is a member of a proposal team</p>
    *
    * @param proposalId id of a proposal
    * @param userId     id of an user
    * @return true if user is a member of given proposal team, false otherwise
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static boolean isUserAMember(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isUserAMember(proposalId, userId);
    }

    /**
    * <p>Returns true if proposal is open (so it can be edited by any user).</p>
    *
    * @param proposalId id of proposal
    * @return true if plan is open, false otherwise
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public static boolean isOpen(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isOpen(proposalId);
    }

    /**
    * <p>Returns all team membership requests for a proposal.</p>
    *
    * @param proposalId proposal id
    * @return list of membership requests
    * @throws SystemException in case of LR error
    * @throws PortalException in case of LR error
    */
    public static java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getMembershipRequests(proposalId);
    }

    /**
    * <p>Sends a request to join proposal team</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @param comment    optional comment
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static com.liferay.portal.model.MembershipRequest addMembershipRequest(
        long proposalId, long userId, java.lang.String comment)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().addMembershipRequest(proposalId, userId, comment);
    }

    /**
    * <p>Remove a user from a proposal team</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static void removeUserFromTeam(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().removeUserFromTeam(proposalId, userId);
    }

    /**
    * <p>Denies user as a member of proposal team</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static void dennyMembershipRequest(long proposalId, long userId,
        long membershipRequestId, java.lang.String reply, long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .dennyMembershipRequest(proposalId, userId, membershipRequestId,
            reply, updateAuthorId);
    }

    /**
    * <p>Approves user as a member of proposal team</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static void approveMembershipRequest(long proposalId,
        java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .approveMembershipRequest(proposalId, userId, request, reply,
            updateAuthorId);
    }

    /**
    * <p>Tells if user has requested membership of given plan</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @return true if user has requested membership, false otherwise
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static boolean hasUserRequestedMembership(long proposalId,
        long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().hasUserRequestedMembership(proposalId, userId);
    }

    /**
    * <p>Adds user to a proposal team if proposal is open and user is not a member already</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static void joinIfNotAMemberAndProposalIsOpen(long proposalId,
        long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().joinIfNotAMemberAndProposalIsOpen(proposalId, userId);
    }

    /**
    * <p>Returns true if user is subscribed to given proposal</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @return true if user has subscribed to a proposal, false otherwise
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static boolean isSubscribed(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isSubscribed(proposalId, userId);
    }

    /**
    * <p>Subscribes user to a proposal</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static void subscribe(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().subscribe(proposalId, userId);
    }

    /**
    * <p>Subscribes user to a proposal (supports manual and automatic subscriptions).
    * Automatic subscription is created when user is being subscribed indirectly
    * (ie. when new proposal is created in a contest to which user is subscribed). </p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @param automatic  if this is an automatic subscription
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static void subscribe(long proposalId, long userId, boolean automatic)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().subscribe(proposalId, userId, automatic);
    }

    /**
    * <p>Unsubscribes user from given proposal</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static void unsubscribe(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().unsubscribe(proposalId, userId);
    }

    /**
    * <p>Unsubscribes user from given proposal (supports removal of automatic subscriptions).
    * If user is unsubscribing manually then subscription is removed without any conditions,
    * but if this is removal of an automatic subscription then a "automaticSubscriptionCounter"
    * is decreased by 1 for this subscription and if it reaches 0 then subscription is removed. </p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @param automatic  if this is an automatic subscription
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public static void unsubscribe(long proposalId, long userId,
        boolean automatic)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().unsubscribe(proposalId, userId, automatic);
    }

    /**
    * <p>Returns true if user has voted for given proposal in context of a contest phase</p>
    *
    * @param proposalId     proposal id
    * @param contestPhaseId contest phase id
    * @param userId         user id
    * @return true if user has voted for proposal in context of a contest phase
    * @throws SystemException
    */
    public static boolean hasUserVoted(long proposalId, long contestPhaseId,
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().hasUserVoted(proposalId, contestPhaseId, userId);
    }

    /**
    * Returns number of proposals that user supports
    *
    * @param userId
    * @return
    * @throws SystemException
    */
    public static int getUserSupportedProposalsCount(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUserSupportedProposalsCount(userId);
    }

    /**
    * Returns number of proposals that user has given his vote to
    *
    * @param userId
    * @return
    * @throws SystemException
    */
    public static int getUserVotedProposalsCount(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUserVotedProposalsCount(userId);
    }

    public static java.util.List<com.ext.portlet.model.Proposal> getModifiedAfter(
        java.util.Date date)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModifiedAfter(date);
    }

    /**
    * Sends out the judges' review about the proposal's advance decision as a CoLab message notification to all proposal
    * contributers
    *
    * @param proposal      The proposal for which the notification should be sent
    * @param contestPhase  The contestPhase in which the proposal is in
    * @param request       A PortletRequest object to extract the Portal's base URL (may be null - choose default portal URL in that case)
    */
    public static void contestPhasePromotionEmailNotifyProposalContributors(
        com.ext.portlet.model.Proposal proposal,
        com.ext.portlet.model.ContestPhase contestPhase,
        javax.portlet.PortletRequest request)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException,
            com.liferay.util.mail.MailEngineException,
            javax.mail.internet.AddressException {
        getService()
            .contestPhasePromotionEmailNotifyProposalContributors(proposal,
            contestPhase, request);
    }

    /**
    * Posts the judges' review about the proposal's advance decision on the proposal's comment thread
    *
    * @param proposal  The proposal for which the notification should be sent
    */
    public static void contestPhasePromotionCommentNotifyProposalContributors(
        com.ext.portlet.model.Proposal proposal,
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .contestPhasePromotionCommentNotifyProposalContributors(proposal,
            contestPhase);
    }

    public static java.lang.Long getDiscussionIdAndGenerateIfNull(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionIdAndGenerateIfNull(proposal);
    }

    /**
    * Returns the URL link address for the passed proposal and contest
    *
    * @param contest  The contest object in which the proposal was written
    * @param proposal The proposal object (must not be null)
    * @return Proposal URL as String
    */
    public static java.lang.String getProposalLinkUrl(
        com.ext.portlet.model.Contest contest,
        com.ext.portlet.model.Proposal proposal) {
        return getService().getProposalLinkUrl(contest, proposal);
    }

    /**
    * Returns the URL link address for the passed proposal, contest and contestPhase
    *
    * @param contest       The contest object in which the proposal was written
    * @param proposal      The proposal object
    * @param contestPhase  The associated contestPhase of the proposal
    * @return Proposal URL as String
    */
    public static java.lang.String getProposalLinkUrl(
        com.ext.portlet.model.Contest contest,
        com.ext.portlet.model.Proposal proposal,
        com.ext.portlet.model.ContestPhase contestPhase) {
        return getService().getProposalLinkUrl(contest, proposal, contestPhase);
    }

    /**
    * Returns list of proposals referenced by given proposal
    *
    * @param proposalId                        The proposal for which subproposals should be returned
    * @param includeProposalsInSameContest     Specifies whether linked proposals in the same contest as the passed proposal
    should be included in the result or not
    * @return collection of referenced proposals
    */
    public static java.util.List<com.ext.portlet.model.Proposal> getSubproposals(
        long proposalId, boolean includeProposalsInSameContest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getSubproposals(proposalId, includeProposalsInSameContest);
    }

    /**
    * Returns latest contest phase to which proposal was submited
    *
    * @param proposalId id of a proposal
    * @return last contest phase to which proposal was submited
    * @throws PortalException
    * @throws SystemException
    */
    public static com.ext.portlet.model.ContestPhase getLatestProposalContestPhase(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getLatestProposalContestPhase(proposalId);
    }

    /**
    * Returns latest contest to which proposal was submited
    *
    * @param proposalId id of a proposal
    * @return last contest to which proposal was submited
    * @throws PortalException
    * @throws SystemException
    */
    public static com.ext.portlet.model.Contest getLatestProposalContest(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getLatestProposalContest(proposalId);
    }

    public static java.util.List<com.ext.portlet.model.ProposalAttribute> getImpactProposalAttributes(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactProposalAttributes(proposal);
    }

    public static java.util.List<com.ext.portlet.model.ProposalAttribute> getImpactProposalAttributes(
        com.ext.portlet.model.Proposal proposal,
        com.ext.portlet.model.FocusArea focusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactProposalAttributes(proposal, focusArea);
    }

    /**
    * Returns all focus areas, for which entered proposal impact data is available
    *
    * @param proposal
    * @return
    */
    public static java.util.List<com.ext.portlet.model.FocusArea> getImpactProposalFocusAreas(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactProposalFocusAreas(proposal);
    }

    public static void clearService() {
        _service = null;
    }

    public static ProposalLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalLocalService.class.getName());

            if (invokableLocalService instanceof ProposalLocalService) {
                _service = (ProposalLocalService) invokableLocalService;
            } else {
                _service = new ProposalLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ProposalLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ProposalLocalService service) {
    }
}
