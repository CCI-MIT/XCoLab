package com.ext.utils.iptranslation.service;

import com.ext.portlet.service.ClpSerializer;
import com.ext.utils.iptranslation.IpTranslationService;
import com.ext.utils.iptranslation.Location;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

public class IpTranslationServiceUtil {
    private static IpTranslationService _service;
    

    public void setService(IpTranslationService service) {
    }

    public static Location getLocationForIp(String ip) throws Exception {
    	return getService().getLocationForIp(ip);
    }
    public static void reloadLocationAndBlockData() throws Exception {
    	getService().reloadLocationAndBlockData();
    }
    
    public static void clearService() {
        _service = null;
    }

    public static IpTranslationService getService() {
        if (_service == null) {
        	Object tmp = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
        			IpTranslationService.class.getName());
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
            		IpTranslationService.class.getName());

            if (invokableLocalService instanceof IpTranslationService) {
                _service = (IpTranslationService) invokableLocalService;
            } else {
            	_service = new IpTranslationServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(IpTranslationServiceUtil.class,
                "_service");
        }

        return _service;
    }

}
