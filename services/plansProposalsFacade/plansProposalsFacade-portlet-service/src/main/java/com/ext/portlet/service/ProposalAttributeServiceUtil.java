package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal attribute remote service. This utility wraps {@link com.ext.portlet.service.impl.ProposalAttributeServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributeService
 * @see com.ext.portlet.service.base.ProposalAttributeServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalAttributeServiceImpl
 * @generated
 */
public class ProposalAttributeServiceUtil {
    private static ProposalAttributeService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalAttributeServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ProposalAttributeService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalAttributeService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ProposalAttributeService.class.getName(), portletClassLoader);

            _service = new ProposalAttributeServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ProposalAttributeServiceUtil.class,
                "_service");
            MethodCache.remove(ProposalAttributeService.class);
        }

        return _service;
    }

    public void setService(ProposalAttributeService service) {
        MethodCache.remove(ProposalAttributeService.class);

        _service = service;

        ReferenceRegistry.registerReference(ProposalAttributeServiceUtil.class,
            "_service");
        MethodCache.remove(ProposalAttributeService.class);
    }
}
