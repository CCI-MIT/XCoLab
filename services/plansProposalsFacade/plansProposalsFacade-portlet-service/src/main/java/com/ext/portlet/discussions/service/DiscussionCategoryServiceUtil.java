package com.ext.portlet.discussions.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the discussion category remote service. This utility wraps {@link com.ext.portlet.discussions.service.impl.DiscussionCategoryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryService
 * @see com.ext.portlet.discussions.service.base.DiscussionCategoryServiceBaseImpl
 * @see com.ext.portlet.discussions.service.impl.DiscussionCategoryServiceImpl
 * @generated
 */
public class DiscussionCategoryServiceUtil {
    private static DiscussionCategoryService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.discussions.service.impl.DiscussionCategoryServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static DiscussionCategoryService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    DiscussionCategoryService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    DiscussionCategoryService.class.getName(),
                    portletClassLoader);

            _service = new DiscussionCategoryServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(DiscussionCategoryServiceUtil.class,
                "_service");
            MethodCache.remove(DiscussionCategoryService.class);
        }

        return _service;
    }

    public void setService(DiscussionCategoryService service) {
        MethodCache.remove(DiscussionCategoryService.class);

        _service = service;

        ReferenceRegistry.registerReference(DiscussionCategoryServiceUtil.class,
            "_service");
        MethodCache.remove(DiscussionCategoryService.class);
    }
}
