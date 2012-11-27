package com.ext.portlet.discussions.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the discussion message flag local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlagLocalServiceUtil
 * @see com.ext.portlet.discussions.service.base.DiscussionMessageFlagLocalServiceBaseImpl
 * @see com.ext.portlet.discussions.service.impl.DiscussionMessageFlagLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DiscussionMessageFlagLocalService
    extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DiscussionMessageFlagLocalServiceUtil} to access the discussion message flag local service. Add custom service methods to {@link com.ext.portlet.discussions.service.impl.DiscussionMessageFlagLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the discussion message flag to the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessageFlag the discussion message flag
    * @return the discussion message flag that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessageFlag addDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new discussion message flag with the primary key. Does not add the discussion message flag to the database.
    *
    * @param pk the primary key for the new discussion message flag
    * @return the new discussion message flag
    */
    public com.ext.portlet.discussions.model.DiscussionMessageFlag createDiscussionMessageFlag(
        java.lang.Long pk);

    /**
    * Deletes the discussion message flag with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message flag
    * @throws PortalException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteDiscussionMessageFlag(java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the discussion message flag from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessageFlag the discussion message flag
    * @throws SystemException if a system exception occurred
    */
    public void deleteDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.discussions.model.DiscussionMessageFlag fetchDiscussionMessageFlag(
        java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message flag with the primary key.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag
    * @throws PortalException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.discussions.model.DiscussionMessageFlag getDiscussionMessageFlag(
        java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion message flags.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion message flags
    * @param end the upper bound of the range of discussion message flags (not inclusive)
    * @return the range of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> getDiscussionMessageFlags(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion message flags.
    *
    * @return the number of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDiscussionMessageFlagsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the discussion message flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionMessageFlag the discussion message flag
    * @return the discussion message flag that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessageFlag updateDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the discussion message flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionMessageFlag the discussion message flag
    * @param merge whether to merge the discussion message flag with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the discussion message flag that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessageFlag updateDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> findMessageFlags(
        java.lang.Long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessageFlag createFlag(
        java.lang.Long messageId, java.lang.String flagType,
        java.lang.String data, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;
}
