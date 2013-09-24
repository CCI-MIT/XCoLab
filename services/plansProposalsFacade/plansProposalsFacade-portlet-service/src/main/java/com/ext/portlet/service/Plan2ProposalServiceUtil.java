package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan2 proposal remote service. This utility wraps {@link com.ext.portlet.service.impl.Plan2ProposalServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Plan2ProposalService
 * @see com.ext.portlet.service.base.Plan2ProposalServiceBaseImpl
 * @see com.ext.portlet.service.impl.Plan2ProposalServiceImpl
 * @generated
 */
public class Plan2ProposalServiceUtil {
    private static Plan2ProposalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.Plan2ProposalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static Plan2ProposalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    Plan2ProposalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    Plan2ProposalService.class.getName(), portletClassLoader);

            _service = new Plan2ProposalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(Plan2ProposalServiceUtil.class,
                "_service");
            MethodCache.remove(Plan2ProposalService.class);
        }

        return _service;
    }

    public void setService(Plan2ProposalService service) {
        MethodCache.remove(Plan2ProposalService.class);

        _service = service;

        ReferenceRegistry.registerReference(Plan2ProposalServiceUtil.class,
            "_service");
        MethodCache.remove(Plan2ProposalService.class);
    }
}
