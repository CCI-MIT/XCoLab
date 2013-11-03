package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal attribute type remote service. This utility wraps {@link com.ext.portlet.service.impl.ProposalAttributeTypeServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributeTypeService
 * @see com.ext.portlet.service.base.ProposalAttributeTypeServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalAttributeTypeServiceImpl
 * @generated
 */
public class ProposalAttributeTypeServiceUtil {
    private static ProposalAttributeTypeService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalAttributeTypeServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ProposalAttributeTypeService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalAttributeTypeService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ProposalAttributeTypeService.class.getName(),
                    portletClassLoader);

            _service = new ProposalAttributeTypeServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ProposalAttributeTypeServiceUtil.class,
                "_service");
            MethodCache.remove(ProposalAttributeTypeService.class);
        }

        return _service;
    }

    public void setService(ProposalAttributeTypeService service) {
        MethodCache.remove(ProposalAttributeTypeService.class);

        _service = service;

        ReferenceRegistry.registerReference(ProposalAttributeTypeServiceUtil.class,
            "_service");
        MethodCache.remove(ProposalAttributeTypeService.class);
    }
}
