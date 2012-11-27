package com.ext.portlet.ontology.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the focus area ontology term remote service. This utility wraps {@link com.ext.portlet.ontology.service.impl.FocusAreaOntologyTermServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTermService
 * @see com.ext.portlet.ontology.service.base.FocusAreaOntologyTermServiceBaseImpl
 * @see com.ext.portlet.ontology.service.impl.FocusAreaOntologyTermServiceImpl
 * @generated
 */
public class FocusAreaOntologyTermServiceUtil {
    private static FocusAreaOntologyTermService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.ontology.service.impl.FocusAreaOntologyTermServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static FocusAreaOntologyTermService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    FocusAreaOntologyTermService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    FocusAreaOntologyTermService.class.getName(),
                    portletClassLoader);

            _service = new FocusAreaOntologyTermServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(FocusAreaOntologyTermServiceUtil.class,
                "_service");
            MethodCache.remove(FocusAreaOntologyTermService.class);
        }

        return _service;
    }

    public void setService(FocusAreaOntologyTermService service) {
        MethodCache.remove(FocusAreaOntologyTermService.class);

        _service = service;

        ReferenceRegistry.registerReference(FocusAreaOntologyTermServiceUtil.class,
            "_service");
        MethodCache.remove(FocusAreaOntologyTermService.class);
    }
}
