package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the ontology term entity local service. This utility wraps {@link com.ext.portlet.service.impl.OntologyTermEntityLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntityLocalService
 * @see com.ext.portlet.service.base.OntologyTermEntityLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.OntologyTermEntityLocalServiceImpl
 * @generated
 */
public class OntologyTermEntityLocalServiceUtil {
    private static OntologyTermEntityLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.OntologyTermEntityLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the ontology term entity to the database. Also notifies the appropriate model listeners.
    *
    * @param ontologyTermEntity the ontology term entity
    * @return the ontology term entity that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity addOntologyTermEntity(
        com.ext.portlet.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addOntologyTermEntity(ontologyTermEntity);
    }

    /**
    * Creates a new ontology term entity with the primary key. Does not add the ontology term entity to the database.
    *
    * @param id the primary key for the new ontology term entity
    * @return the new ontology term entity
    */
    public static com.ext.portlet.model.OntologyTermEntity createOntologyTermEntity(
        long id) {
        return getService().createOntologyTermEntity(id);
    }

    /**
    * Deletes the ontology term entity with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the ontology term entity
    * @throws PortalException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteOntologyTermEntity(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteOntologyTermEntity(id);
    }

    /**
    * Deletes the ontology term entity from the database. Also notifies the appropriate model listeners.
    *
    * @param ontologyTermEntity the ontology term entity
    * @throws SystemException if a system exception occurred
    */
    public static void deleteOntologyTermEntity(
        com.ext.portlet.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteOntologyTermEntity(ontologyTermEntity);
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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

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

    public static com.ext.portlet.model.OntologyTermEntity fetchOntologyTermEntity(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchOntologyTermEntity(id);
    }

    /**
    * Returns the ontology term entity with the primary key.
    *
    * @param id the primary key of the ontology term entity
    * @return the ontology term entity
    * @throws PortalException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity getOntologyTermEntity(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getOntologyTermEntity(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ontology term entities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @return the range of ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.OntologyTermEntity> getOntologyTermEntities(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getOntologyTermEntities(start, end);
    }

    /**
    * Returns the number of ontology term entities.
    *
    * @return the number of ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public static int getOntologyTermEntitiesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getOntologyTermEntitiesCount();
    }

    /**
    * Updates the ontology term entity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ontologyTermEntity the ontology term entity
    * @return the ontology term entity that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity updateOntologyTermEntity(
        com.ext.portlet.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateOntologyTermEntity(ontologyTermEntity);
    }

    /**
    * Updates the ontology term entity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ontologyTermEntity the ontology term entity
    * @param merge whether to merge the ontology term entity with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the ontology term entity that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.OntologyTermEntity updateOntologyTermEntity(
        com.ext.portlet.model.OntologyTermEntity ontologyTermEntity,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateOntologyTermEntity(ontologyTermEntity, merge);
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

    public static java.util.List<java.lang.Long> findTagedIdsForClass(
        java.lang.Long termId, java.lang.Class clasz)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findTagedIdsForClass(termId, clasz);
    }

    public static void store(com.ext.portlet.model.OntologyTermEntity ote)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(ote);
    }

    public static void remove(com.ext.portlet.model.OntologyTermEntity ote)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().remove(ote);
    }

    public static void clearService() {
        _service = null;
    }

    public static OntologyTermEntityLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    OntologyTermEntityLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    OntologyTermEntityLocalService.class.getName(),
                    portletClassLoader);

            _service = new OntologyTermEntityLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(OntologyTermEntityLocalServiceUtil.class,
                "_service");
            MethodCache.remove(OntologyTermEntityLocalService.class);
        }

        return _service;
    }

    public void setService(OntologyTermEntityLocalService service) {
        MethodCache.remove(OntologyTermEntityLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(OntologyTermEntityLocalServiceUtil.class,
            "_service");
        MethodCache.remove(OntologyTermEntityLocalService.class);
    }
}
