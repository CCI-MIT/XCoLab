package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service interface for Xcolab_User. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see Xcolab_UserLocalServiceUtil
 * @see com.ext.portlet.service.base.Xcolab_UserLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.Xcolab_UserLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface Xcolab_UserLocalService extends BaseLocalService,
    InvokableLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link Xcolab_UserLocalServiceUtil} to access the xcolab_ user local service. Add custom service methods to {@link com.ext.portlet.service.impl.Xcolab_UserLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

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

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenName(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleName(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSince(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCount(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByPoints(
        int begin, int end, java.lang.String filter, boolean ascendingOrder);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategoryFilter, boolean ascendingOrder);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<java.lang.Long> getUserActivityCount(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.liferay.portal.model.User> findUsersByLoginIP(
        java.lang.String loginIP);
}
