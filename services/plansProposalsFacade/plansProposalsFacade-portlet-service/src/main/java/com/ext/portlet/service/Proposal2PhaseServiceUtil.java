package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal2 phase remote service. This utility wraps {@link com.ext.portlet.service.impl.Proposal2PhaseServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Proposal2PhaseService
 * @see com.ext.portlet.service.base.Proposal2PhaseServiceBaseImpl
 * @see com.ext.portlet.service.impl.Proposal2PhaseServiceImpl
 * @generated
 */
public class Proposal2PhaseServiceUtil {
    private static Proposal2PhaseService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.Proposal2PhaseServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static Proposal2PhaseService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    Proposal2PhaseService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    Proposal2PhaseService.class.getName(), portletClassLoader);

            _service = new Proposal2PhaseServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(Proposal2PhaseServiceUtil.class,
                "_service");
            MethodCache.remove(Proposal2PhaseService.class);
        }

        return _service;
    }

    public void setService(Proposal2PhaseService service) {
        MethodCache.remove(Proposal2PhaseService.class);

        _service = service;

        ReferenceRegistry.registerReference(Proposal2PhaseServiceUtil.class,
            "_service");
        MethodCache.remove(Proposal2PhaseService.class);
    }
}
