package com.ext.utils.userInput.service;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanItemLocalService;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.utils.userInput.UserInputException;
import com.ext.utils.userInput.UserInputFilter;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

public class UserInputFilterUtil {
    private static UserInputFilter _service;
    

    public void setService(UserInputFilter service) {
        MethodCache.remove(UserInputFilter.class);

        _service = service;

        ReferenceRegistry.registerReference(UserInputFilterUtil.class, "_service");
        MethodCache.remove(UserInputFilter.class);
    }

    public static String filterHtml(String html) throws UserInputException {
        return getService().filterHtml(html);
    }
    
    public static UserInputFilter getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    UserInputFilter.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    UserInputFilter.class.getName(), portletClassLoader);

            _service = new UserInputFilterClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanItemLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlanItemLocalService.class);
        }

        return _service;
    }
}
