package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the ontology space remote service. This utility wraps {@link com.ext.portlet.service.impl.OntologySpaceServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologySpaceService
 * @see com.ext.portlet.service.base.OntologySpaceServiceBaseImpl
 * @see com.ext.portlet.service.impl.OntologySpaceServiceImpl
 * @generated
 */
public class OntologySpaceServiceUtil {
    private static OntologySpaceService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.OntologySpaceServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static OntologySpaceService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    OntologySpaceService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    OntologySpaceService.class.getName(), portletClassLoader);

            _service = new OntologySpaceServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(OntologySpaceServiceUtil.class,
                "_service");
            MethodCache.remove(OntologySpaceService.class);
        }

        return _service;
    }

    public void setService(OntologySpaceService service) {
        MethodCache.remove(OntologySpaceService.class);

        _service = service;

        ReferenceRegistry.registerReference(OntologySpaceServiceUtil.class,
            "_service");
        MethodCache.remove(OntologySpaceService.class);
    }
}
