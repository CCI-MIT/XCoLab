package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for OntologyTerm. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermLocalServiceUtil
 * @see com.ext.portlet.service.base.OntologyTermLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.OntologyTermLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface OntologyTermLocalService extends BaseLocalService,
    InvokableLocalService, PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link OntologyTermLocalServiceUtil} to access the ontology term local service. Add custom service methods to {@link com.ext.portlet.service.impl.OntologyTermLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the ontology term to the database. Also notifies the appropriate model listeners.
    *
    * @param ontologyTerm the ontology term
    * @return the ontology term that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm addOntologyTerm(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new ontology term with the primary key. Does not add the ontology term to the database.
    *
    * @param id the primary key for the new ontology term
    * @return the new ontology term
    */
    public com.ext.portlet.model.OntologyTerm createOntologyTerm(long id);

    /**
    * Deletes the ontology term with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the ontology term
    * @return the ontology term that was removed
    * @throws PortalException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm deleteOntologyTerm(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the ontology term from the database. Also notifies the appropriate model listeners.
    *
    * @param ontologyTerm the ontology term
    * @return the ontology term that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm deleteOntologyTerm(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.OntologyTerm fetchOntologyTerm(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology term with the primary key.
    *
    * @param id the primary key of the ontology term
    * @return the ontology term
    * @throws PortalException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.OntologyTerm getOntologyTerm(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology terms.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @return the range of ontology terms
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.OntologyTerm> getOntologyTerms(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology terms.
    *
    * @return the number of ontology terms
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getOntologyTermsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the ontology term in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ontologyTerm the ontology term
    * @return the ontology term that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologyTerm updateOntologyTerm(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
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

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

    public java.util.List<com.ext.portlet.model.OntologyTerm> findByParentId(
        java.lang.Long parentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.model.OntologyTerm> findByParentIdSpaceId(
        java.lang.Long parentId, java.lang.Long spaceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.OntologyTerm createTerm(
        java.lang.Long parentId, java.lang.String name, java.lang.Long spaceId,
        java.lang.String descriptionUrl)
        throws com.liferay.portal.kernel.exception.SystemException;

    public int countChildTerms(java.lang.Long parentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void clearClassTags(java.lang.Class clasz, java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void store(com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.OntologyTerm getParent(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getChildTermsCount(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.OntologyTerm> getChildTerms(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.OntologyTerm> getAllDescendantTerms(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.OntologySpace getSpace(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void tagClass(com.ext.portlet.model.OntologyTerm ontologyTerm,
        java.lang.Class clasz, java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<java.lang.Long> findTagedIdsForClass(
        com.ext.portlet.model.OntologyTerm ontologyTerm, java.lang.Class clasz)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Boolean isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(
        java.lang.Long focusAreaId, java.lang.Long ontologyTermId)
        throws java.lang.Exception;
}
