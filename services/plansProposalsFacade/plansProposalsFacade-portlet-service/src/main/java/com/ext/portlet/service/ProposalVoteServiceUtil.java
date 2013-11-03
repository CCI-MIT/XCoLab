package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal vote remote service. This utility wraps {@link com.ext.portlet.service.impl.ProposalVoteServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVoteService
 * @see com.ext.portlet.service.base.ProposalVoteServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalVoteServiceImpl
 * @generated
 */
public class ProposalVoteServiceUtil {
    private static ProposalVoteService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalVoteServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ProposalVoteService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalVoteService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ProposalVoteService.class.getName(), portletClassLoader);

            _service = new ProposalVoteServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ProposalVoteServiceUtil.class,
                "_service");
            MethodCache.remove(ProposalVoteService.class);
        }

        return _service;
    }

    public void setService(ProposalVoteService service) {
        MethodCache.remove(ProposalVoteService.class);

        _service = service;

        ReferenceRegistry.registerReference(ProposalVoteServiceUtil.class,
            "_service");
        MethodCache.remove(ProposalVoteService.class);
    }
}
