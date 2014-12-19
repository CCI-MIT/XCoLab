package com.ext.portlet.service.impl;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.ext.portlet.service.base.ProposalSupporterLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalSupporterPK;
import com.ext.portlet.service.persistence.ProposalSupporterUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.kernel.bean.BeanLocatorException;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the proposal supporter local service.
 * <p/>
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalSupporterLocalService} interface.
 * <p/>
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalSupporterLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalSupporterLocalServiceUtil
 */
public class ProposalSupporterLocalServiceImpl
        extends ProposalSupporterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalSupporterLocalServiceUtil} to access the proposal supporter local service.
     */

    private static final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";

    public ProposalSupporter create(long proposalID, long userID) {
        return createProposalSupporter(new ProposalSupporterPK(proposalID, userID));
    }


    public List<ProposalSupporter> getProposals(long userId) throws PortalException, com.liferay.portal.kernel.exception.SystemException {
        try {
            final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";
            DynamicQuery dq = DynamicQueryFactoryUtil.forClass(ProposalSupporter.class, (ClassLoader) PortletBeanLocatorUtil.locate(
                    ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader"));
            dq.add(PropertyFactoryUtil.forName("primaryKey.userId").eq(userId));
            return (List<ProposalSupporter>) ProposalSupporterLocalServiceUtil.dynamicQuery(dq);
        } catch (Throwable e) {
            System.out.println("got exception:"+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getSupportingUsersForProposal(long proposalId) throws SystemException, com.liferay.portal.kernel.exception.PortalException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ProposalSupporter.class,
                (ClassLoader) PortletBeanLocatorUtil.locate(ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader"));
        query.add(PropertyFactoryUtil.forName("proposalId").eq(proposalId));
        List<ProposalSupporter> supporters = ProposalLocalServiceUtil.dynamicQuery(query);

        List<User> users = new ArrayList<>();
        for (ProposalSupporter supporter : supporters) {
            users.add(UserLocalServiceUtil.getUser(supporter.getUserId()));
        }

        return users;
    }

    public List<ProposalSupporter> getProposalSupportersForProposals(List<Proposal> proposals) throws SystemException {
        if (proposals.size() == 0) {
            return new ArrayList<>();
        }
        List<Long> proposalIds = new ArrayList<>();
        for (Proposal proposal : proposals) {
            proposalIds.add(proposal.getProposalId());
        }

        DynamicQuery proposalSupportQuery = DynamicQueryFactoryUtil.forClass(ProposalSupporter.class);
        proposalSupportQuery.add(PropertyFactoryUtil.forName("primaryKey.proposalId").in(proposalIds));
        return proposalSupporterLocalService.dynamicQuery(proposalSupportQuery);
    }
}
