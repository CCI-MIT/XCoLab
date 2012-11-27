package com.ext.portlet.discussions.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the discussion message flag remote service. This utility wraps {@link com.ext.portlet.discussions.service.impl.DiscussionMessageFlagServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlagService
 * @see com.ext.portlet.discussions.service.base.DiscussionMessageFlagServiceBaseImpl
 * @see com.ext.portlet.discussions.service.impl.DiscussionMessageFlagServiceImpl
 * @generated
 */
public class DiscussionMessageFlagServiceUtil {
    private static DiscussionMessageFlagService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.discussions.service.impl.DiscussionMessageFlagServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static DiscussionMessageFlagService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    DiscussionMessageFlagService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    DiscussionMessageFlagService.class.getName(),
                    portletClassLoader);

            _service = new DiscussionMessageFlagServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(DiscussionMessageFlagServiceUtil.class,
                "_service");
            MethodCache.remove(DiscussionMessageFlagService.class);
        }

        return _service;
    }

    public void setService(DiscussionMessageFlagService service) {
        MethodCache.remove(DiscussionMessageFlagService.class);

        _service = service;

        ReferenceRegistry.registerReference(DiscussionMessageFlagServiceUtil.class,
            "_service");
        MethodCache.remove(DiscussionMessageFlagService.class);
    }
}
