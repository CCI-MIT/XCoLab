package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest team member remote service. This utility wraps {@link com.ext.portlet.service.impl.ContestTeamMemberServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberService
 * @see com.ext.portlet.service.base.ContestTeamMemberServiceBaseImpl
 * @see com.ext.portlet.service.impl.ContestTeamMemberServiceImpl
 * @generated
 */
public class ContestTeamMemberServiceUtil {
    private static ContestTeamMemberService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ContestTeamMemberServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ContestTeamMemberService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestTeamMemberService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestTeamMemberService.class.getName(), portletClassLoader);

            _service = new ContestTeamMemberServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestTeamMemberServiceUtil.class,
                "_service");
            MethodCache.remove(ContestTeamMemberService.class);
        }

        return _service;
    }

    public void setService(ContestTeamMemberService service) {
        MethodCache.remove(ContestTeamMemberService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestTeamMemberServiceUtil.class,
            "_service");
        MethodCache.remove(ContestTeamMemberService.class);
    }
}
