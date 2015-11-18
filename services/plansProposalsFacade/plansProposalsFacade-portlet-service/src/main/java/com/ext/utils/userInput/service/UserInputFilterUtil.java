package com.ext.utils.userInput.service;

import com.ext.portlet.service.ClpSerializer;
import com.ext.utils.userInput.UserInputException;
import com.ext.utils.userInput.UserInputFilter;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

public class UserInputFilterUtil {
    private static UserInputFilter _service;
    

    public void setService(UserInputFilter service) {
    }

    public static String filterHtml(String html) throws UserInputException {
        return getService().filterHtml(html);
    }
    
    public static void clearService() {
        _service = null;
    }

    public static UserInputFilter getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    UserInputFilter.class.getName());

            if (invokableLocalService instanceof UserInputFilter) {
                _service = (UserInputFilter) invokableLocalService;
            } else {
            	_service = new UserInputFilterClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(UserInputFilter.class,
                "_service");
        }

        return _service;
    }

}
