package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class ProposalFinderUtil {
    private static ProposalFinder _finder;

    public static int getProposalMaterializedPoints(long proposalId) {
        return getFinder().getProposalMaterializedPoints(proposalId);
    }

    public static int getProposalHypotheticalPoints(long proposalId) {
        return getFinder().getProposalHypotheticalPoints(proposalId);
    }

    public static ProposalFinder getFinder() {
        if (_finder == null) {
            _finder = (ProposalFinder) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalFinder.class.getName());

            ReferenceRegistry.registerReference(ProposalFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ProposalFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ProposalFinderUtil.class, "_finder");
    }
}
