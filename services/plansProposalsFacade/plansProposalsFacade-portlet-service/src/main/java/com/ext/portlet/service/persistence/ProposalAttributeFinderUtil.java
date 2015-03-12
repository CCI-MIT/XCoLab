package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class ProposalAttributeFinderUtil {
    private static ProposalAttributeFinder _finder;

    public static java.util.List<com.ext.portlet.model.ProposalAttribute> findByProposalIdVersionGreaterThanVersionWhenCreatedLessThanNameLikeImpact(
        long proposalId, long version, long versionWhenCreated) {
        return getFinder()
                   .findByProposalIdVersionGreaterThanVersionWhenCreatedLessThanNameLikeImpact(proposalId,
            version, versionWhenCreated);
    }

    public static ProposalAttributeFinder getFinder() {
        if (_finder == null) {
            _finder = (ProposalAttributeFinder) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalAttributeFinder.class.getName());

            ReferenceRegistry.registerReference(ProposalAttributeFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ProposalAttributeFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ProposalAttributeFinderUtil.class,
            "_finder");
    }
}
