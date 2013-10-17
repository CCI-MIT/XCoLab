package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal contest phase attribute remote service. This utility wraps {@link com.ext.portlet.service.impl.ProposalContestPhaseAttributeServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeService
 * @see com.ext.portlet.service.base.ProposalContestPhaseAttributeServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalContestPhaseAttributeServiceImpl
 * @generated
 */
public class ProposalContestPhaseAttributeServiceUtil {
    private static ProposalContestPhaseAttributeService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalContestPhaseAttributeServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ProposalContestPhaseAttributeService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalContestPhaseAttributeService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ProposalContestPhaseAttributeService.class.getName(),
                    portletClassLoader);

            _service = new ProposalContestPhaseAttributeServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ProposalContestPhaseAttributeServiceUtil.class,
                "_service");
            MethodCache.remove(ProposalContestPhaseAttributeService.class);
        }

        return _service;
    }

    public void setService(ProposalContestPhaseAttributeService service) {
        MethodCache.remove(ProposalContestPhaseAttributeService.class);

        _service = service;

        ReferenceRegistry.registerReference(ProposalContestPhaseAttributeServiceUtil.class,
            "_service");
        MethodCache.remove(ProposalContestPhaseAttributeService.class);
    }
}
