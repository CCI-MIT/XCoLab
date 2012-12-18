package com.ext.portlet.ontology.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the focus area ontology term local service. This utility wraps {@link com.ext.portlet.ontology.service.impl.FocusAreaOntologyTermLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTermLocalService
 * @see com.ext.portlet.ontology.service.base.FocusAreaOntologyTermLocalServiceBaseImpl
 * @see com.ext.portlet.ontology.service.impl.FocusAreaOntologyTermLocalServiceImpl
 * @generated
 */
public class FocusAreaOntologyTermLocalServiceUtil {
    private static FocusAreaOntologyTermLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.ontology.service.impl.FocusAreaOntologyTermLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the focus area ontology term to the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @return the focus area ontology term that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm addFocusAreaOntologyTerm(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addFocusAreaOntologyTerm(focusAreaOntologyTerm);
    }

    /**
    * Creates a new focus area ontology term with the primary key. Does not add the focus area ontology term to the database.
    *
    * @param focusAreaOntologyTermPK the primary key for the new focus area ontology term
    * @return the new focus area ontology term
    */
    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm createFocusAreaOntologyTerm(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK) {
        return getService().createFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    /**
    * Deletes the focus area ontology term with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @throws PortalException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteFocusAreaOntologyTerm(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    /**
    * Deletes the focus area ontology term from the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @throws SystemException if a system exception occurred
    */
    public static void deleteFocusAreaOntologyTerm(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteFocusAreaOntologyTerm(focusAreaOntologyTerm);
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

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm fetchFocusAreaOntologyTerm(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    /**
    * Returns the focus area ontology term with the primary key.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @return the focus area ontology term
    * @throws PortalException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm getFocusAreaOntologyTerm(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

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
    public static java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> getFocusAreaOntologyTerms(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFocusAreaOntologyTerms(start, end);
    }

    /**
    * Returns the number of focus area ontology terms.
    *
    * @return the number of focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    public static int getFocusAreaOntologyTermsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFocusAreaOntologyTermsCount();
    }

    /**
    * Updates the focus area ontology term in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @return the focus area ontology term that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm updateFocusAreaOntologyTerm(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateFocusAreaOntologyTerm(focusAreaOntologyTerm);
    }

    /**
    * Updates the focus area ontology term in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @param merge whether to merge the focus area ontology term with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the focus area ontology term that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm updateFocusAreaOntologyTerm(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateFocusAreaOntologyTerm(focusAreaOntologyTerm, merge);
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

    public static java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findTermsByFocusArea(
        java.lang.Long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findTermsByFocusArea(focusAreaId);
    }

    public static void addAreaTerm(java.lang.Long focusAreaId,
        java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().addAreaTerm(focusAreaId, termId);
    }

    public static void removeAreaTerm(java.lang.Long focusAreaId,
        java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().removeAreaTerm(focusAreaId, termId);
    }

    public static void store(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm faot)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(faot);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm getTerm(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm faot)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getTerm(faot);
    }

    public static com.ext.portlet.ontology.model.FocusArea getArea(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm faot)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getArea(faot);
    }

    public static void clearService() {
        _service = null;
    }

    public static FocusAreaOntologyTermLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    FocusAreaOntologyTermLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    FocusAreaOntologyTermLocalService.class.getName(),
                    portletClassLoader);

            _service = new FocusAreaOntologyTermLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(FocusAreaOntologyTermLocalServiceUtil.class,
                "_service");
            MethodCache.remove(FocusAreaOntologyTermLocalService.class);
        }

        return _service;
    }

    public void setService(FocusAreaOntologyTermLocalService service) {
        MethodCache.remove(FocusAreaOntologyTermLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(FocusAreaOntologyTermLocalServiceUtil.class,
            "_service");
        MethodCache.remove(FocusAreaOntologyTermLocalService.class);
    }
}
