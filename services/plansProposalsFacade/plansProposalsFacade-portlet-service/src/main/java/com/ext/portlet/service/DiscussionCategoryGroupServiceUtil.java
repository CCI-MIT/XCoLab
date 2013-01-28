package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the discussion category group remote service. This utility wraps {@link com.ext.portlet.service.impl.DiscussionCategoryGroupServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryGroupService
 * @see com.ext.portlet.service.base.DiscussionCategoryGroupServiceBaseImpl
 * @see com.ext.portlet.service.impl.DiscussionCategoryGroupServiceImpl
 * @generated
 */
public class DiscussionCategoryGroupServiceUtil {
    private static DiscussionCategoryGroupService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.DiscussionCategoryGroupServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static DiscussionCategoryGroupService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    DiscussionCategoryGroupService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    DiscussionCategoryGroupService.class.getName(),
                    portletClassLoader);

            _service = new DiscussionCategoryGroupServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(DiscussionCategoryGroupServiceUtil.class,
                "_service");
            MethodCache.remove(DiscussionCategoryGroupService.class);
        }

        return _service;
    }

    public void setService(DiscussionCategoryGroupService service) {
        MethodCache.remove(DiscussionCategoryGroupService.class);

        _service = service;

        ReferenceRegistry.registerReference(DiscussionCategoryGroupServiceUtil.class,
            "_service");
        MethodCache.remove(DiscussionCategoryGroupService.class);
    }
}
