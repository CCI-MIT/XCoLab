package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanTemplateSection;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan template section service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplateSectionPersistenceImpl
 * @see PlanTemplateSectionUtil
 * @generated
 */
public interface PlanTemplateSectionPersistence extends BasePersistence<PlanTemplateSection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanTemplateSectionUtil} to access the plan template section persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the plan template sections where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @return the matching plan template sections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanTemplateSection> findByPlanTemplateId(
        long planTemplateId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.PlanTemplateSection> findByPlanTemplateId(
        long planTemplateId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.PlanTemplateSection> findByPlanTemplateId(
        long planTemplateId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan template section in the ordered set where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan template section
    * @throws com.ext.portlet.NoSuchPlanTemplateSectionException if a matching plan template section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplateSection findByPlanTemplateId_First(
        long planTemplateId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanTemplateSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan template section in the ordered set where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan template section, or <code>null</code> if a matching plan template section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplateSection fetchByPlanTemplateId_First(
        long planTemplateId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan template section in the ordered set where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan template section
    * @throws com.ext.portlet.NoSuchPlanTemplateSectionException if a matching plan template section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplateSection findByPlanTemplateId_Last(
        long planTemplateId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanTemplateSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan template section in the ordered set where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan template section, or <code>null</code> if a matching plan template section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplateSection fetchByPlanTemplateId_Last(
        long planTemplateId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.PlanTemplateSection[] findByPlanTemplateId_PrevAndNext(
        PlanTemplateSectionPK planTemplateSectionPK, long planTemplateId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanTemplateSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan template sections where planTemplateId = &#63; from the database.
    *
    * @param planTemplateId the plan template ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanTemplateId(long planTemplateId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan template sections where planTemplateId = &#63;.
    *
    * @param planTemplateId the plan template ID
    * @return the number of matching plan template sections
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanTemplateId(long planTemplateId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plan template section in the entity cache if it is enabled.
    *
    * @param planTemplateSection the plan template section
    */
    public void cacheResult(
        com.ext.portlet.model.PlanTemplateSection planTemplateSection);

    /**
    * Caches the plan template sections in the entity cache if it is enabled.
    *
    * @param planTemplateSections the plan template sections
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanTemplateSection> planTemplateSections);

    /**
    * Creates a new plan template section with the primary key. Does not add the plan template section to the database.
    *
    * @param planTemplateSectionPK the primary key for the new plan template section
    * @return the new plan template section
    */
    public com.ext.portlet.model.PlanTemplateSection create(
        PlanTemplateSectionPK planTemplateSectionPK);

    /**
    * Removes the plan template section with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTemplateSectionPK the primary key of the plan template section
    * @return the plan template section that was removed
    * @throws com.ext.portlet.NoSuchPlanTemplateSectionException if a plan template section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplateSection remove(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.ext.portlet.NoSuchPlanTemplateSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanTemplateSection updateImpl(
        com.ext.portlet.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan template section with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTemplateSectionException} if it could not be found.
    *
    * @param planTemplateSectionPK the primary key of the plan template section
    * @return the plan template section
    * @throws com.ext.portlet.NoSuchPlanTemplateSectionException if a plan template section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplateSection findByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.ext.portlet.NoSuchPlanTemplateSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan template section with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planTemplateSectionPK the primary key of the plan template section
    * @return the plan template section, or <code>null</code> if a plan template section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplateSection fetchByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan template sections.
    *
    * @return the plan template sections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanTemplateSection> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.PlanTemplateSection> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.PlanTemplateSection> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan template sections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan template sections.
    *
    * @return the number of plan template sections
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
