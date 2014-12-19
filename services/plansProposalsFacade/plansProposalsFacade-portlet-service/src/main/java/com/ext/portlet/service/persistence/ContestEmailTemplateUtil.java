package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestEmailTemplate;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contest email template service. This utility wraps {@link ContestEmailTemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestEmailTemplatePersistence
 * @see ContestEmailTemplatePersistenceImpl
 * @generated
 */
public class ContestEmailTemplateUtil {
    private static ContestEmailTemplatePersistence _persistence;

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
    public static void clearCache(ContestEmailTemplate contestEmailTemplate) {
        getPersistence().clearCache(contestEmailTemplate);
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
    public static List<ContestEmailTemplate> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ContestEmailTemplate> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ContestEmailTemplate> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ContestEmailTemplate update(
        ContestEmailTemplate contestEmailTemplate) throws SystemException {
        return getPersistence().update(contestEmailTemplate);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ContestEmailTemplate update(
        ContestEmailTemplate contestEmailTemplate, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(contestEmailTemplate, serviceContext);
    }

    /**
    * Caches the contest email template in the entity cache if it is enabled.
    *
    * @param contestEmailTemplate the contest email template
    */
    public static void cacheResult(
        com.ext.portlet.model.ContestEmailTemplate contestEmailTemplate) {
        getPersistence().cacheResult(contestEmailTemplate);
    }

    /**
    * Caches the contest email templates in the entity cache if it is enabled.
    *
    * @param contestEmailTemplates the contest email templates
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ContestEmailTemplate> contestEmailTemplates) {
        getPersistence().cacheResult(contestEmailTemplates);
    }

    /**
    * Creates a new contest email template with the primary key. Does not add the contest email template to the database.
    *
    * @param type the primary key for the new contest email template
    * @return the new contest email template
    */
    public static com.ext.portlet.model.ContestEmailTemplate create(
        java.lang.String type) {
        return getPersistence().create(type);
    }

    /**
    * Removes the contest email template with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param type the primary key of the contest email template
    * @return the contest email template that was removed
    * @throws com.ext.portlet.NoSuchContestEmailTemplateException if a contest email template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestEmailTemplate remove(
        java.lang.String type)
        throws com.ext.portlet.NoSuchContestEmailTemplateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(type);
    }

    public static com.ext.portlet.model.ContestEmailTemplate updateImpl(
        com.ext.portlet.model.ContestEmailTemplate contestEmailTemplate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contestEmailTemplate);
    }

    /**
    * Returns the contest email template with the primary key or throws a {@link com.ext.portlet.NoSuchContestEmailTemplateException} if it could not be found.
    *
    * @param type the primary key of the contest email template
    * @return the contest email template
    * @throws com.ext.portlet.NoSuchContestEmailTemplateException if a contest email template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestEmailTemplate findByPrimaryKey(
        java.lang.String type)
        throws com.ext.portlet.NoSuchContestEmailTemplateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(type);
    }

    /**
    * Returns the contest email template with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param type the primary key of the contest email template
    * @return the contest email template, or <code>null</code> if a contest email template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestEmailTemplate fetchByPrimaryKey(
        java.lang.String type)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(type);
    }

    /**
    * Returns all the contest email templates.
    *
    * @return the contest email templates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestEmailTemplate> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the contest email templates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestEmailTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest email templates
    * @param end the upper bound of the range of contest email templates (not inclusive)
    * @return the range of contest email templates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestEmailTemplate> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the contest email templates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestEmailTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest email templates
    * @param end the upper bound of the range of contest email templates (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest email templates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestEmailTemplate> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contest email templates from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contest email templates.
    *
    * @return the number of contest email templates
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContestEmailTemplatePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContestEmailTemplatePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ContestEmailTemplatePersistence.class.getName());

            ReferenceRegistry.registerReference(ContestEmailTemplateUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ContestEmailTemplatePersistence persistence) {
    }
}
