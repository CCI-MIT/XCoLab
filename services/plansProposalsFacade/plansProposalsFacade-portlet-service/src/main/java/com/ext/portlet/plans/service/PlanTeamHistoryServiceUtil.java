package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan team history remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanTeamHistoryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTeamHistoryService
 * @see com.ext.portlet.plans.service.base.PlanTeamHistoryServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanTeamHistoryServiceImpl
 * @generated
 */
public class PlanTeamHistoryServiceUtil {
    private static PlanTeamHistoryService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanTeamHistoryServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanTeamHistoryService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanTeamHistoryService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanTeamHistoryService.class.getName(), portletClassLoader);

            _service = new PlanTeamHistoryServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanTeamHistoryServiceUtil.class,
                "_service");
            MethodCache.remove(PlanTeamHistoryService.class);
        }

        return _service;
    }

    public void setService(PlanTeamHistoryService service) {
        MethodCache.remove(PlanTeamHistoryService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanTeamHistoryServiceUtil.class,
            "_service");
        MethodCache.remove(PlanTeamHistoryService.class);
    }
}
