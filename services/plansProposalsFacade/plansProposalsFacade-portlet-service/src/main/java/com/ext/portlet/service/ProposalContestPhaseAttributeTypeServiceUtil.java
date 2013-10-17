package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal contest phase attribute type remote service. This utility wraps {@link com.ext.portlet.service.impl.ProposalContestPhaseAttributeTypeServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeTypeService
 * @see com.ext.portlet.service.base.ProposalContestPhaseAttributeTypeServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalContestPhaseAttributeTypeServiceImpl
 * @generated
 */
public class ProposalContestPhaseAttributeTypeServiceUtil {
    private static ProposalContestPhaseAttributeTypeService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalContestPhaseAttributeTypeServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ProposalContestPhaseAttributeTypeService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalContestPhaseAttributeTypeService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ProposalContestPhaseAttributeTypeService.class.getName(),
                    portletClassLoader);

            _service = new ProposalContestPhaseAttributeTypeServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ProposalContestPhaseAttributeTypeServiceUtil.class,
                "_service");
            MethodCache.remove(ProposalContestPhaseAttributeTypeService.class);
        }

        return _service;
    }

    public void setService(ProposalContestPhaseAttributeTypeService service) {
        MethodCache.remove(ProposalContestPhaseAttributeTypeService.class);

        _service = service;

        ReferenceRegistry.registerReference(ProposalContestPhaseAttributeTypeServiceUtil.class,
            "_service");
        MethodCache.remove(ProposalContestPhaseAttributeTypeService.class);
    }
}
