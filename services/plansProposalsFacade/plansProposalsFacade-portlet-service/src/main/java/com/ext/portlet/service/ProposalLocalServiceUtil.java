package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal local service. This utility wraps {@link com.ext.portlet.service.impl.ProposalLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
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
    * @throws PortalException if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteProposal(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteProposal(proposalId);
    }

    /**
    * Deletes the proposal from the database. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @throws SystemException if a system exception occurred
    */
    public static void deleteProposal(com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteProposal(proposal);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    * Updates the proposal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @param merge whether to merge the proposal with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the proposal that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Proposal updateProposal(
        com.ext.portlet.model.Proposal proposal, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateProposal(proposal, merge);
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

    /**
    * <p>
    * Creates new proposal initialized to version 1 with one attribute "NAME"
    * set to "Untitled proposal - PROPOSAL_ID". All related entities are
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
    * @param authorId
    id of proposal author
    * @return created proposal
    * @throws SystemException
    in case of a Liferay error
    * @throws PortalException
    in case of a Liferay error
    */
    public static com.ext.portlet.model.Proposal create(long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().create(authorId);
    }

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

    public static java.util.List<com.ext.portlet.model.ProposalAttribute> getAttributes(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getAttributes(proposalId);
    }

    public static java.util.List<com.ext.portlet.model.ProposalAttribute> getAttributes(
        long proposalId, int version)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getAttributes(proposalId, version);
    }

    public static com.ext.portlet.model.ProposalAttribute getAttribute(
        long proposalId, java.lang.String attributeName, long additionalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getAttribute(proposalId, attributeName, additionalId);
    }

    public static com.ext.portlet.model.ProposalAttribute getAttribute(
        long proposalId, int version, java.lang.String attributeName,
        long additionalId)
        throws com.ext.portlet.NoSuchProposalAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getAttribute(proposalId, version, attributeName,
            additionalId);
    }

    public static java.util.List<com.ext.portlet.model.ProposalVersion> getProposalVersions(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalVersions(proposalId);
    }

    public static com.ext.portlet.model.ProposalVersion getProposalVersion(
        long proposalId, int version)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalVersion(proposalId, version);
    }

    public static void clearService() {
        _service = null;
    }

    public static ProposalLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ProposalLocalService.class.getName(), portletClassLoader);

            _service = new ProposalLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ProposalLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ProposalLocalService.class);
        }

        return _service;
    }

    public void setService(ProposalLocalService service) {
        MethodCache.remove(ProposalLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ProposalLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ProposalLocalService.class);
    }
}
