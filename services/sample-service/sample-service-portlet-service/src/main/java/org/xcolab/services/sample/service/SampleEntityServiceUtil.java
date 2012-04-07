package org.xcolab.services.sample.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the sample entity remote service. This utility wraps {@link org.xcolab.services.sample.service.impl.SampleEntityServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SampleEntityService
 * @see org.xcolab.services.sample.service.base.SampleEntityServiceBaseImpl
 * @see org.xcolab.services.sample.service.impl.SampleEntityServiceImpl
 * @generated
 */
public class SampleEntityServiceUtil {
    private static SampleEntityService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.xcolab.services.sample.service.impl.SampleEntityServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static SampleEntityService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    SampleEntityService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    SampleEntityService.class.getName(), portletClassLoader);

            _service = new SampleEntityServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(SampleEntityServiceUtil.class,
                "_service");
            MethodCache.remove(SampleEntityService.class);
        }

        return _service;
    }

    public void setService(SampleEntityService service) {
        MethodCache.remove(SampleEntityService.class);

        _service = service;

        ReferenceRegistry.registerReference(SampleEntityServiceUtil.class,
            "_service");
        MethodCache.remove(SampleEntityService.class);
    }
}
