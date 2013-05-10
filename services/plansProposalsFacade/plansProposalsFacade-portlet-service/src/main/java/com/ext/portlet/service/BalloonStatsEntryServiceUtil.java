package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the balloon stats entry remote service. This utility wraps {@link com.ext.portlet.service.impl.BalloonStatsEntryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonStatsEntryService
 * @see com.ext.portlet.service.base.BalloonStatsEntryServiceBaseImpl
 * @see com.ext.portlet.service.impl.BalloonStatsEntryServiceImpl
 * @generated
 */
public class BalloonStatsEntryServiceUtil {
    private static BalloonStatsEntryService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.BalloonStatsEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static BalloonStatsEntryService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    BalloonStatsEntryService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    BalloonStatsEntryService.class.getName(), portletClassLoader);

            _service = new BalloonStatsEntryServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(BalloonStatsEntryServiceUtil.class,
                "_service");
            MethodCache.remove(BalloonStatsEntryService.class);
        }

        return _service;
    }

    public void setService(BalloonStatsEntryService service) {
        MethodCache.remove(BalloonStatsEntryService.class);

        _service = service;

        ReferenceRegistry.registerReference(BalloonStatsEntryServiceUtil.class,
            "_service");
        MethodCache.remove(BalloonStatsEntryService.class);
    }
}
