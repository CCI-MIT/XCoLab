package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PointsDistributionConfiguration;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the points distribution configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointsDistributionConfigurationPersistenceImpl
 * @see PointsDistributionConfigurationUtil
 * @generated
 */
public interface PointsDistributionConfigurationPersistence
    extends BasePersistence<PointsDistributionConfiguration> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PointsDistributionConfigurationUtil} to access the points distribution configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the points distribution configurations where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @return the matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetUserId(
        long targetUserId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the points distribution configurations where targetUserId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetUserId the target user ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetUserId(
        long targetUserId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the points distribution configurations where targetUserId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetUserId the target user ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetUserId(
        long targetUserId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first points distribution configuration in the ordered set where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByTargetUserId_First(
        long targetUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first points distribution configuration in the ordered set where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByTargetUserId_First(
        long targetUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last points distribution configuration in the ordered set where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByTargetUserId_Last(
        long targetUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last points distribution configuration in the ordered set where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByTargetUserId_Last(
        long targetUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where targetUserId = &#63;.
    *
    * @param id the primary key of the current points distribution configuration
    * @param targetUserId the target user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration[] findByTargetUserId_PrevAndNext(
        long id, long targetUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the points distribution configurations where targetUserId = &#63; from the database.
    *
    * @param targetUserId the target user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByTargetUserId(long targetUserId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of points distribution configurations where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @return the number of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public int countByTargetUserId(long targetUserId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the points distribution configurations where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @return the matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetSubProposalId(
        long targetSubProposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the points distribution configurations where targetSubProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetSubProposalId(
        long targetSubProposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the points distribution configurations where targetSubProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetSubProposalId(
        long targetSubProposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first points distribution configuration in the ordered set where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByTargetSubProposalId_First(
        long targetSubProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first points distribution configuration in the ordered set where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByTargetSubProposalId_First(
        long targetSubProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last points distribution configuration in the ordered set where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByTargetSubProposalId_Last(
        long targetSubProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last points distribution configuration in the ordered set where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByTargetSubProposalId_Last(
        long targetSubProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where targetSubProposalId = &#63;.
    *
    * @param id the primary key of the current points distribution configuration
    * @param targetSubProposalId the target sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration[] findByTargetSubProposalId_PrevAndNext(
        long id, long targetSubProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the points distribution configurations where targetSubProposalId = &#63; from the database.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByTargetSubProposalId(long targetSubProposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of points distribution configurations where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @return the number of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public int countByTargetSubProposalId(long targetSubProposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the points distribution configurations where targetPlanSectionDefinitionId = &#63;.
    *
    * @param targetPlanSectionDefinitionId the target plan section definition ID
    * @return the matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the points distribution configurations where targetPlanSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetPlanSectionDefinitionId the target plan section definition ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the points distribution configurations where targetPlanSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetPlanSectionDefinitionId the target plan section definition ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first points distribution configuration in the ordered set where targetPlanSectionDefinitionId = &#63;.
    *
    * @param targetPlanSectionDefinitionId the target plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByTargetPlanSectionDefinitionId_First(
        long targetPlanSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first points distribution configuration in the ordered set where targetPlanSectionDefinitionId = &#63;.
    *
    * @param targetPlanSectionDefinitionId the target plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByTargetPlanSectionDefinitionId_First(
        long targetPlanSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last points distribution configuration in the ordered set where targetPlanSectionDefinitionId = &#63;.
    *
    * @param targetPlanSectionDefinitionId the target plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByTargetPlanSectionDefinitionId_Last(
        long targetPlanSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last points distribution configuration in the ordered set where targetPlanSectionDefinitionId = &#63;.
    *
    * @param targetPlanSectionDefinitionId the target plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByTargetPlanSectionDefinitionId_Last(
        long targetPlanSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where targetPlanSectionDefinitionId = &#63;.
    *
    * @param id the primary key of the current points distribution configuration
    * @param targetPlanSectionDefinitionId the target plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration[] findByTargetPlanSectionDefinitionId_PrevAndNext(
        long id, long targetPlanSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the points distribution configurations where targetPlanSectionDefinitionId = &#63; from the database.
    *
    * @param targetPlanSectionDefinitionId the target plan section definition ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of points distribution configurations where targetPlanSectionDefinitionId = &#63;.
    *
    * @param targetPlanSectionDefinitionId the target plan section definition ID
    * @return the number of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public int countByTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the points distribution configurations where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the points distribution configurations where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the points distribution configurations where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first points distribution configuration in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first points distribution configuration in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last points distribution configuration in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last points distribution configuration in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where proposalId = &#63;.
    *
    * @param id the primary key of the current points distribution configuration
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration[] findByProposalId_PrevAndNext(
        long id, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the points distribution configurations where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of points distribution configurations where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @return the matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalIdPointTypeId(
        long proposalId, long pointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalIdPointTypeId(
        long proposalId, long pointTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalIdPointTypeId(
        long proposalId, long pointTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByProposalIdPointTypeId_First(
        long proposalId, long pointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByProposalIdPointTypeId_First(
        long proposalId, long pointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByProposalIdPointTypeId_Last(
        long proposalId, long pointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByProposalIdPointTypeId_Last(
        long proposalId, long pointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param id the primary key of the current points distribution configuration
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration[] findByProposalIdPointTypeId_PrevAndNext(
        long id, long proposalId, long pointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProposalIdPointTypeId(long proposalId, long pointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @return the number of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalIdPointTypeId(long proposalId, long pointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the points distribution configuration in the entity cache if it is enabled.
    *
    * @param pointsDistributionConfiguration the points distribution configuration
    */
    public void cacheResult(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration);

    /**
    * Caches the points distribution configurations in the entity cache if it is enabled.
    *
    * @param pointsDistributionConfigurations the points distribution configurations
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> pointsDistributionConfigurations);

    /**
    * Creates a new points distribution configuration with the primary key. Does not add the points distribution configuration to the database.
    *
    * @param id the primary key for the new points distribution configuration
    * @return the new points distribution configuration
    */
    public com.ext.portlet.model.PointsDistributionConfiguration create(long id);

    /**
    * Removes the points distribution configuration with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the points distribution configuration
    * @return the points distribution configuration that was removed
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration remove(long id)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PointsDistributionConfiguration updateImpl(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the points distribution configuration with the primary key or throws a {@link com.ext.portlet.NoSuchPointsDistributionConfigurationException} if it could not be found.
    *
    * @param id the primary key of the points distribution configuration
    * @return the points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the points distribution configuration with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the points distribution configuration
    * @return the points distribution configuration, or <code>null</code> if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointsDistributionConfiguration fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the points distribution configurations.
    *
    * @return the points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the points distribution configurations.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the points distribution configurations.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the points distribution configurations from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of points distribution configurations.
    *
    * @return the number of points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
