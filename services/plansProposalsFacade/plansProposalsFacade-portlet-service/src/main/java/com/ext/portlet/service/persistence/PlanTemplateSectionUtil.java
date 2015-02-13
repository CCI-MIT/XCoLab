package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanTemplateSection;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan template section service. This utility wraps {@link PlanTemplateSectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplateSectionPersistence
 * @see PlanTemplateSectionPersistenceImpl
 * @generated
 */
public class PlanTemplateSectionUtil {
    private static PlanTemplateSectionPersistence _persistence;

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
    public static void clearCache(PlanTemplateSection planTemplateSection) {
        getPersistence().clearCache(planTemplateSection);
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
    public static List<PlanTemplateSection> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanTemplateSection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanTemplateSection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PlanTemplateSection update(
        PlanTemplateSection planTemplateSection) throws SystemException {
        return getPersistence().update(planTemplateSection);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PlanTemplateSection update(
        PlanTemplateSection planTemplateSection, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(planTemplateSection, serviceContext);
    }

    /**
    * Returns all the plan template sections where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @return the matching plan template sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTemplateSection> findByPlanTemplateId(
        long planTemplateId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanTemplateId(planTemplateId);
    }

    /**
    * Returns a range of all the plan template sections where planTemplateId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTemplateSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planTemplateId the plan template ID
    * @param start the lower bound of the range of plan template sections
    * @param end the upper bound of the range of plan template sections (not inclusive)
    * @return the range of matching plan template sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTemplateSection> findByPlanTemplateId(
        long planTemplateId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanTemplateId(planTemplateId, start, end);
    }

    /**
    * Returns an ordered range of all the plan template sections where planTemplateId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTemplateSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planTemplateId the plan template ID
    * @param start the lower bound of the range of plan template sections
    * @param end the upper bound of the range of plan template sections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan template sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTemplateSection> findByPlanTemplateId(
        long planTemplateId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanTemplateId(planTemplateId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first plan template section in the ordered set where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan template section
    * @throws com.ext.portlet.NoSuchPlanTemplateSectionException if a matching plan template section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplateSection findByPlanTemplateId_First(
        long planTemplateId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanTemplateSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanTemplateId_First(planTemplateId, orderByComparator);
    }

    /**
    * Returns the first plan template section in the ordered set where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan template section, or <code>null</code> if a matching plan template section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplateSection fetchByPlanTemplateId_First(
        long planTemplateId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanTemplateId_First(planTemplateId,
            orderByComparator);
    }

    /**
    * Returns the last plan template section in the ordered set where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan template section
    * @throws com.ext.portlet.NoSuchPlanTemplateSectionException if a matching plan template section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplateSection findByPlanTemplateId_Last(
        long planTemplateId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanTemplateSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanTemplateId_Last(planTemplateId, orderByComparator);
    }

    /**
    * Returns the last plan template section in the ordered set where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan template section, or <code>null</code> if a matching plan template section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplateSection fetchByPlanTemplateId_Last(
        long planTemplateId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanTemplateId_Last(planTemplateId, orderByComparator);
    }

    /**
    * Returns the plan template sections before and after the current plan template section in the ordered set where planTemplateId = &#63;.
    *
    * @param planTemplateSectionPK the primary key of the current plan template section
    * @param planTemplateId the plan template ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan template section
    * @throws com.ext.portlet.NoSuchPlanTemplateSectionException if a plan template section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplateSection[] findByPlanTemplateId_PrevAndNext(
        PlanTemplateSectionPK planTemplateSectionPK, long planTemplateId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanTemplateSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanTemplateId_PrevAndNext(planTemplateSectionPK,
            planTemplateId, orderByComparator);
    }

    /**
    * Removes all the plan template sections where planTemplateId = &#63; from the database.
    *
    * @param planTemplateId the plan template ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanTemplateId(long planTemplateId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPlanTemplateId(planTemplateId);
    }

    /**
    * Returns the number of plan template sections where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @return the number of matching plan template sections
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanTemplateId(long planTemplateId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanTemplateId(planTemplateId);
    }

    /**
    * Caches the plan template section in the entity cache if it is enabled.
    *
    * @param planTemplateSection the plan template section
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanTemplateSection planTemplateSection) {
        getPersistence().cacheResult(planTemplateSection);
    }

    /**
    * Caches the plan template sections in the entity cache if it is enabled.
    *
    * @param planTemplateSections the plan template sections
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanTemplateSection> planTemplateSections) {
        getPersistence().cacheResult(planTemplateSections);
    }

    /**
    * Creates a new plan template section with the primary key. Does not add the plan template section to the database.
    *
    * @param planTemplateSectionPK the primary key for the new plan template section
    * @return the new plan template section
    */
    public static com.ext.portlet.model.PlanTemplateSection create(
        PlanTemplateSectionPK planTemplateSectionPK) {
        return getPersistence().create(planTemplateSectionPK);
    }

    /**
    * Removes the plan template section with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTemplateSectionPK the primary key of the plan template section
    * @return the plan template section that was removed
    * @throws com.ext.portlet.NoSuchPlanTemplateSectionException if a plan template section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplateSection remove(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.ext.portlet.NoSuchPlanTemplateSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planTemplateSectionPK);
    }

    public static com.ext.portlet.model.PlanTemplateSection updateImpl(
        com.ext.portlet.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planTemplateSection);
    }

    /**
    * Returns the plan template section with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTemplateSectionException} if it could not be found.
    *
    * @param planTemplateSectionPK the primary key of the plan template section
    * @return the plan template section
    * @throws com.ext.portlet.NoSuchPlanTemplateSectionException if a plan template section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplateSection findByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.ext.portlet.NoSuchPlanTemplateSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planTemplateSectionPK);
    }

    /**
    * Returns the plan template section with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planTemplateSectionPK the primary key of the plan template section
    * @return the plan template section, or <code>null</code> if a plan template section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplateSection fetchByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planTemplateSectionPK);
    }

    /**
    * Returns all the plan template sections.
    *
    * @return the plan template sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTemplateSection> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan template sections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTemplateSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan template sections
    * @param end the upper bound of the range of plan template sections (not inclusive)
    * @return the range of plan template sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTemplateSection> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan template sections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTemplateSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan template sections
    * @param end the upper bound of the range of plan template sections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan template sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTemplateSection> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan template sections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan template sections.
    *
    * @return the number of plan template sections
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanTemplateSectionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanTemplateSectionPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanTemplateSectionPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanTemplateSectionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PlanTemplateSectionPersistence persistence) {
    }
}
