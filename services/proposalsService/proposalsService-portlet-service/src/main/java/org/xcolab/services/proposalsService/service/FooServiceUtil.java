package org.xcolab.services.proposalsService.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the foo remote service. This utility wraps {@link org.xcolab.services.proposalsService.service.impl.FooServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FooService
 * @see org.xcolab.services.proposalsService.service.base.FooServiceBaseImpl
 * @see org.xcolab.services.proposalsService.service.impl.FooServiceImpl
 * @generated
 */
public class FooServiceUtil {
    private static FooService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.xcolab.services.proposalsService.service.impl.FooServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static FooService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    FooService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    FooService.class.getName(), portletClassLoader);

            _service = new FooServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(FooServiceUtil.class, "_service");
            MethodCache.remove(FooService.class);
        }

        return _service;
    }

    public void setService(FooService service) {
        MethodCache.remove(FooService.class);

        _service = service;

        ReferenceRegistry.registerReference(FooServiceUtil.class, "_service");
        MethodCache.remove(FooService.class);
    }
}
