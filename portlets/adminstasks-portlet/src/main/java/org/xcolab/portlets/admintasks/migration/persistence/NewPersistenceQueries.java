package org.xcolab.portlets.admintasks.migration.persistence;

import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalContestPhaseAttributeType;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 10/1/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewPersistenceQueries {
    private static final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";
    private static ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(
            ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader");

    public static long getProposalContestPhaseAttributeTypeIdForRibbon(String ribbon, String hoverText){
        DynamicQuery proposalRibbonQuery = DynamicQueryFactoryUtil.forClass(ProposalContestPhaseAttributeType.class, portletClassLoader);
        proposalRibbonQuery.add(PropertyFactoryUtil.forName("ribbon").eq(ribbon));
        proposalRibbonQuery.add(PropertyFactoryUtil.forName("hoverText").eq(hoverText));

        List<ProposalContestPhaseAttributeType> ribbonTypes = null;
        try{
            ribbonTypes = ProposalContestPhaseAttributeTypeLocalServiceUtil.dynamicQuery(proposalRibbonQuery);
        } catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        if (ribbonTypes == null || ribbonTypes.isEmpty()) return -1;
        return ribbonTypes.get(0).getId();
    }

    public static boolean createNewProposalContestPhaseAttribute(long proposalId, long typeId, long contestPhaseId){
        try{
            ProposalContestPhaseAttribute attr =
                    ProposalContestPhaseAttributeLocalServiceUtil.createProposalContestPhaseAttribute(
                            CounterLocalServiceUtil.increment(ProposalContestPhaseAttribute.class.getName()
                            ));
            attr.setProposalId(proposalId);
            attr.setTypeId(typeId);
            attr.setContestPhaseId(contestPhaseId);
            ProposalContestPhaseAttributeLocalServiceUtil.updateProposalContestPhaseAttribute(attr);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ProposalContestPhaseAttributeType createNewProposalContestPhaseAttributeType(String ribbon, String hoverText){
        ProposalContestPhaseAttributeType attributeType = null;
        try{
            attributeType = ProposalContestPhaseAttributeTypeLocalServiceUtil.createProposalContestPhaseAttributeType(
                    CounterLocalServiceUtil.increment(ProposalContestPhaseAttributeType.class.getName())
            );
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        attributeType.setRibbon(ribbon);
        attributeType.setHoverText(hoverText);

        try{
            ProposalContestPhaseAttributeTypeLocalServiceUtil.updateProposalContestPhaseAttributeType(attributeType);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return attributeType;
    }

    public static ProposalVersion getLatestVersionForProposal(Proposal proposal){
        // get latest version of a proposal
        DynamicQuery versionQuery = DynamicQueryFactoryUtil.forClass(ProposalVersion.class, portletClassLoader);
        versionQuery.add(PropertyFactoryUtil.forName("primaryKey.proposalId").eq(proposal.getProposalId()));
        versionQuery.addOrder(OrderFactoryUtil.desc("primaryKey.version"));
        List<ProposalVersion> versions;
        try{
            versions = ProposalVersionLocalServiceUtil.dynamicQuery(versionQuery);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return  (versions != null && !versions.isEmpty()) ? versions.get(0) : null;
    }

}
