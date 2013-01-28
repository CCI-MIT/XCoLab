package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the focus area ontology term local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTermLocalServiceUtil
 * @see com.ext.portlet.service.base.FocusAreaOntologyTermLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.FocusAreaOntologyTermLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface FocusAreaOntologyTermLocalService
    extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FocusAreaOntologyTermLocalServiceUtil} to access the focus area ontology term local service. Add custom service methods to {@link com.ext.portlet.service.impl.FocusAreaOntologyTermLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the focus area ontology term to the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @return the focus area ontology term that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm addFocusAreaOntologyTerm(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new focus area ontology term with the primary key. Does not add the focus area ontology term to the database.
    *
    * @param focusAreaOntologyTermPK the primary key for the new focus area ontology term
    * @return the new focus area ontology term
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm createFocusAreaOntologyTerm(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK);

    /**
    * Deletes the focus area ontology term with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @throws PortalException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteFocusAreaOntologyTerm(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the focus area ontology term from the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @throws SystemException if a system exception occurred
    */
    public void deleteFocusAreaOntologyTerm(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
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
    public com.ext.portlet.model.FocusAreaOntologyTerm fetchFocusAreaOntologyTerm(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the focus area ontology term with the primary key.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @return the focus area ontology term
    * @throws PortalException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.FocusAreaOntologyTerm getFocusAreaOntologyTerm(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the focus area ontology terms.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of focus area ontology terms
    * @param end the upper bound of the range of focus area ontology terms (not inclusive)
    * @return the range of focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> getFocusAreaOntologyTerms(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of focus area ontology terms.
    *
    * @return the number of focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getFocusAreaOntologyTermsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the focus area ontology term in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @return the focus area ontology term that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm updateFocusAreaOntologyTerm(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the focus area ontology term in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @param merge whether to merge the focus area ontology term with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the focus area ontology term that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.FocusAreaOntologyTerm updateFocusAreaOntologyTerm(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm,
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

    public java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findTermsByFocusArea(
        java.lang.Long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void addAreaTerm(java.lang.Long focusAreaId, java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void removeAreaTerm(java.lang.Long focusAreaId, java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void store(com.ext.portlet.model.FocusAreaOntologyTerm faot)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.OntologyTerm getTerm(
        com.ext.portlet.model.FocusAreaOntologyTerm faot)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.FocusArea getArea(
        com.ext.portlet.model.FocusAreaOntologyTerm faot)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;
}
