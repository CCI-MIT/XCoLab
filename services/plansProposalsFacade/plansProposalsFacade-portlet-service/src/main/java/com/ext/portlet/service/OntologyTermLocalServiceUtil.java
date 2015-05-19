package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for OntologyTerm. This utility wraps
 * {@link com.ext.portlet.service.impl.OntologyTermLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermLocalService
 * @see com.ext.portlet.service.base.OntologyTermLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.OntologyTermLocalServiceImpl
 * @generated
 */
public class OntologyTermLocalServiceUtil {
    private static OntologyTermLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.OntologyTermLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the ontology term to the database. Also notifies the appropriate model listeners.
    *
    * @param ontologyTerm the ontology term
    * @return the ontology term that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTerm addOntologyTerm(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addOntologyTerm(ontologyTerm);
    }

    /**
    * Creates a new ontology term with the primary key. Does not add the ontology term to the database.
    *
    * @param id the primary key for the new ontology term
    * @return the new ontology term
    */
    public static com.ext.portlet.model.OntologyTerm createOntologyTerm(long id) {
        return getService().createOntologyTerm(id);
    }

    /**
    * Deletes the ontology term with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the ontology term
    * @return the ontology term that was removed
    * @throws PortalException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTerm deleteOntologyTerm(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteOntologyTerm(id);
    }

    /**
    * Deletes the ontology term from the database. Also notifies the appropriate model listeners.
    *
    * @param ontologyTerm the ontology term
    * @return the ontology term that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTerm deleteOntologyTerm(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteOntologyTerm(ontologyTerm);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.OntologyTerm fetchOntologyTerm(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchOntologyTerm(id);
    }

    /**
    * Returns the ontology term with the primary key.
    *
    * @param id the primary key of the ontology term
    * @return the ontology term
    * @throws PortalException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTerm getOntologyTerm(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getOntologyTerm(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

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
    public static java.util.List<com.ext.portlet.model.OntologyTerm> getOntologyTerms(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getOntologyTerms(start, end);
    }

    /**
    * Returns the number of ontology terms.
    *
    * @return the number of ontology terms
    * @throws SystemException if a system exception occurred
    */
    public static int getOntologyTermsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getOntologyTermsCount();
    }

    /**
    * Updates the ontology term in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ontologyTerm the ontology term
    * @return the ontology term that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTerm updateOntologyTerm(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateOntologyTerm(ontologyTerm);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.util.List<com.ext.portlet.model.OntologyTerm> findByParentId(
        java.lang.Long parentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByParentId(parentId);
    }

    public static java.util.List<com.ext.portlet.model.OntologyTerm> findByParentIdSpaceId(
        java.lang.Long parentId, java.lang.Long spaceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByParentIdSpaceId(parentId, spaceId);
    }

    public static com.ext.portlet.model.OntologyTerm createTerm(
        java.lang.Long parentId, java.lang.String name, java.lang.Long spaceId,
        java.lang.String descriptionUrl)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createTerm(parentId, name, spaceId, descriptionUrl);
    }

    public static int countChildTerms(java.lang.Long parentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countChildTerms(parentId);
    }

    public static void clearClassTags(java.lang.Class clasz, java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().clearClassTags(clasz, id);
    }

    public static void store(com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(ontologyTerm);
    }

    public static com.ext.portlet.model.OntologyTerm getParent(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getParent(ontologyTerm);
    }

    public static int getChildTermsCount(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getChildTermsCount(ontologyTerm);
    }

    public static java.util.List<com.ext.portlet.model.OntologyTerm> getChildTerms(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getChildTerms(ontologyTerm);
    }

    public static java.util.List<com.ext.portlet.model.OntologyTerm> getAllDescendantTerms(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllDescendantTerms(ontologyTerm);
    }

    public static com.ext.portlet.model.OntologySpace getSpace(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getSpace(ontologyTerm);
    }

    public static void tagClass(
        com.ext.portlet.model.OntologyTerm ontologyTerm, java.lang.Class clasz,
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().tagClass(ontologyTerm, clasz, id);
    }

    public static java.util.List<java.lang.Long> findTagedIdsForClass(
        com.ext.portlet.model.OntologyTerm ontologyTerm, java.lang.Class clasz)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findTagedIdsForClass(ontologyTerm, clasz);
    }

    public static java.lang.Boolean isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(
        java.lang.Long focusAreaId, java.lang.Long ontologyTermId)
        throws java.lang.Exception {
        return getService()
                   .isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(focusAreaId,
            ontologyTermId);
    }

    public static void clearService() {
        _service = null;
    }

    public static OntologyTermLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    OntologyTermLocalService.class.getName());

            if (invokableLocalService instanceof OntologyTermLocalService) {
                _service = (OntologyTermLocalService) invokableLocalService;
            } else {
                _service = new OntologyTermLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(OntologyTermLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(OntologyTermLocalService service) {
    }
}
