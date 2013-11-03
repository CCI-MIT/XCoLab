package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal remote service. This utility wraps {@link com.ext.portlet.service.impl.ProposalServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalService
 * @see com.ext.portlet.service.base.ProposalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalServiceImpl
 * @generated
 */
public class ProposalServiceUtil {
    private static ProposalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static com.liferay.portal.kernel.json.JSONObject getProposalVersions(
        long contestPhaseId, long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getProposalVersions(contestPhaseId, proposalId, start, end);
    }

    public static com.liferay.portal.kernel.json.JSONObject getProposalVersions(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalVersions(proposalId, start, end);
    }

    public static void clearService() {
        _service = null;
    }

    public static ProposalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ProposalService.class.getName(), portletClassLoader);

            _service = new ProposalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ProposalServiceUtil.class,
                "_service");
            MethodCache.remove(ProposalService.class);
        }

        return _service;
    }

    public void setService(ProposalService service) {
        MethodCache.remove(ProposalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ProposalServiceUtil.class,
            "_service");
        MethodCache.remove(ProposalService.class);
    }
}
