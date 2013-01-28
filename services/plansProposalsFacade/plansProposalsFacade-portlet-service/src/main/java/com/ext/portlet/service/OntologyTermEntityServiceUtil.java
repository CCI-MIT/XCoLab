package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the ontology term entity remote service. This utility wraps {@link com.ext.portlet.service.impl.OntologyTermEntityServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntityService
 * @see com.ext.portlet.service.base.OntologyTermEntityServiceBaseImpl
 * @see com.ext.portlet.service.impl.OntologyTermEntityServiceImpl
 * @generated
 */
public class OntologyTermEntityServiceUtil {
    private static OntologyTermEntityService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.OntologyTermEntityServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static OntologyTermEntityService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    OntologyTermEntityService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    OntologyTermEntityService.class.getName(),
                    portletClassLoader);

            _service = new OntologyTermEntityServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(OntologyTermEntityServiceUtil.class,
                "_service");
            MethodCache.remove(OntologyTermEntityService.class);
        }

        return _service;
    }

    public void setService(OntologyTermEntityService service) {
        MethodCache.remove(OntologyTermEntityService.class);

        _service = service;

        ReferenceRegistry.registerReference(OntologyTermEntityServiceUtil.class,
            "_service");
        MethodCache.remove(OntologyTermEntityService.class);
    }
}
