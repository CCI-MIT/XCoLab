package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalRatingValue;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the proposal rating value service. This utility wraps {@link ProposalRatingValuePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingValuePersistence
 * @see ProposalRatingValuePersistenceImpl
 * @generated
 */
public class ProposalRatingValueUtil {
    private static ProposalRatingValuePersistence _persistence;

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
    public static void clearCache(ProposalRatingValue proposalRatingValue) {
        getPersistence().clearCache(proposalRatingValue);
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
    public static List<ProposalRatingValue> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProposalRatingValue> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProposalRatingValue> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ProposalRatingValue update(
        ProposalRatingValue proposalRatingValue) throws SystemException {
        return getPersistence().update(proposalRatingValue);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ProposalRatingValue update(
        ProposalRatingValue proposalRatingValue, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(proposalRatingValue, serviceContext);
    }

    /**
    * Caches the proposal rating value in the entity cache if it is enabled.
    *
    * @param proposalRatingValue the proposal rating value
    */
    public static void cacheResult(
        com.ext.portlet.model.ProposalRatingValue proposalRatingValue) {
        getPersistence().cacheResult(proposalRatingValue);
    }

    /**
    * Caches the proposal rating values in the entity cache if it is enabled.
    *
    * @param proposalRatingValues the proposal rating values
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalRatingValue> proposalRatingValues) {
        getPersistence().cacheResult(proposalRatingValues);
    }

    /**
    * Creates a new proposal rating value with the primary key. Does not add the proposal rating value to the database.
    *
    * @param id the primary key for the new proposal rating value
    * @return the new proposal rating value
    */
    public static com.ext.portlet.model.ProposalRatingValue create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the proposal rating value with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal rating value
    * @return the proposal rating value that was removed
    * @throws com.ext.portlet.NoSuchProposalRatingValueException if a proposal rating value with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalRatingValue remove(long id)
        throws com.ext.portlet.NoSuchProposalRatingValueException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.ProposalRatingValue updateImpl(
        com.ext.portlet.model.ProposalRatingValue proposalRatingValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(proposalRatingValue);
    }

    /**
    * Returns the proposal rating value with the primary key or throws a {@link com.ext.portlet.NoSuchProposalRatingValueException} if it could not be found.
    *
    * @param id the primary key of the proposal rating value
    * @return the proposal rating value
    * @throws com.ext.portlet.NoSuchProposalRatingValueException if a proposal rating value with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalRatingValue findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchProposalRatingValueException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the proposal rating value with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the proposal rating value
    * @return the proposal rating value, or <code>null</code> if a proposal rating value with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalRatingValue fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the proposal rating values.
    *
    * @return the proposal rating values
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalRatingValue> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the proposal rating values.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal rating values
    * @param end the upper bound of the range of proposal rating values (not inclusive)
    * @return the range of proposal rating values
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalRatingValue> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the proposal rating values.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal rating values
    * @param end the upper bound of the range of proposal rating values (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal rating values
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalRatingValue> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the proposal rating values from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of proposal rating values.
    *
    * @return the number of proposal rating values
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProposalRatingValuePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProposalRatingValuePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalRatingValuePersistence.class.getName());

            ReferenceRegistry.registerReference(ProposalRatingValueUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProposalRatingValuePersistence persistence) {
    }
}
