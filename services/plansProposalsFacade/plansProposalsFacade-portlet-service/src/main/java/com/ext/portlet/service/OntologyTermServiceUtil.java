package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the ontology term remote service. This utility wraps {@link com.ext.portlet.service.impl.OntologyTermServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermService
 * @see com.ext.portlet.service.base.OntologyTermServiceBaseImpl
 * @see com.ext.portlet.service.impl.OntologyTermServiceImpl
 * @generated
 */
public class OntologyTermServiceUtil {
    private static OntologyTermService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.OntologyTermServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static OntologyTermService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    OntologyTermService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    OntologyTermService.class.getName(), portletClassLoader);

            _service = new OntologyTermServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(OntologyTermServiceUtil.class,
                "_service");
            MethodCache.remove(OntologyTermService.class);
        }

        return _service;
    }

    public void setService(OntologyTermService service) {
        MethodCache.remove(OntologyTermService.class);

        _service = service;

        ReferenceRegistry.registerReference(OntologyTermServiceUtil.class,
            "_service");
        MethodCache.remove(OntologyTermService.class);
    }
}
