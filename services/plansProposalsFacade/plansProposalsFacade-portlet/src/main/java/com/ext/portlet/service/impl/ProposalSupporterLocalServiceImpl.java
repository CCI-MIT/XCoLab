package com.ext.portlet.service.impl;

import com.ext.portlet.model.ProposalSupporter;
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
}
