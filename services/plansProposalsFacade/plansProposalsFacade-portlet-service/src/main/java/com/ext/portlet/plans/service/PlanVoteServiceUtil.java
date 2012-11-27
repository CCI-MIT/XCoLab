package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan vote remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanVoteServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanVoteService
 * @see com.ext.portlet.plans.service.base.PlanVoteServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanVoteServiceImpl
 * @generated
 */
public class PlanVoteServiceUtil {
    private static PlanVoteService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanVoteServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanVoteService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanVoteService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanVoteService.class.getName(), portletClassLoader);

            _service = new PlanVoteServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanVoteServiceUtil.class,
                "_service");
            MethodCache.remove(PlanVoteService.class);
        }

        return _service;
    }

    public void setService(PlanVoteService service) {
        MethodCache.remove(PlanVoteService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanVoteServiceUtil.class,
            "_service");
        MethodCache.remove(PlanVoteService.class);
    }
}
