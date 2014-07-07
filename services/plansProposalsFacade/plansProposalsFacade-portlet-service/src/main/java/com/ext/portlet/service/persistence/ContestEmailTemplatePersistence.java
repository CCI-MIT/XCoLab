package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestEmailTemplate;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest email template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestEmailTemplatePersistenceImpl
 * @see ContestEmailTemplateUtil
 * @generated
 */
public interface ContestEmailTemplatePersistence extends BasePersistence<ContestEmailTemplate> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestEmailTemplateUtil} to access the contest email template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the contest email template in the entity cache if it is enabled.
    *
    * @param contestEmailTemplate the contest email template
    */
    public void cacheResult(
        com.ext.portlet.model.ContestEmailTemplate contestEmailTemplate);

    /**
    * Caches the contest email templates in the entity cache if it is enabled.
    *
    * @param contestEmailTemplates the contest email templates
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ContestEmailTemplate> contestEmailTemplates);

    /**
    * Creates a new contest email template with the primary key. Does not add the contest email template to the database.
    *
    * @param type the primary key for the new contest email template
    * @return the new contest email template
    */
    public com.ext.portlet.model.ContestEmailTemplate create(
        java.lang.String type);

    /**
    * Removes the contest email template with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param type the primary key of the contest email template
    * @return the contest email template that was removed
    * @throws com.ext.portlet.NoSuchContestEmailTemplateException if a contest email template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestEmailTemplate remove(
        java.lang.String type)
        throws com.ext.portlet.NoSuchContestEmailTemplateException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ContestEmailTemplate updateImpl(
        com.ext.portlet.model.ContestEmailTemplate contestEmailTemplate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest email template with the primary key or throws a {@link com.ext.portlet.NoSuchContestEmailTemplateException} if it could not be found.
    *
    * @param type the primary key of the contest email template
    * @return the contest email template
    * @throws com.ext.portlet.NoSuchContestEmailTemplateException if a contest email template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestEmailTemplate findByPrimaryKey(
        java.lang.String type)
        throws com.ext.portlet.NoSuchContestEmailTemplateException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest email template with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param type the primary key of the contest email template
    * @return the contest email template, or <code>null</code> if a contest email template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestEmailTemplate fetchByPrimaryKey(
        java.lang.String type)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest email templates.
    *
    * @return the contest email templates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestEmailTemplate> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ContestEmailTemplate> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ContestEmailTemplate> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest email templates from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest email templates.
    *
    * @return the number of contest email templates
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
