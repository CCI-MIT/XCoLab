package org.xcolab.portlets.admintasks.migration.persistence;

import java.util.List;

import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;

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

    public static long getContestPhaseRibbonTypeIdForRibbon(int ribbon, String hoverText){
        
        DynamicQuery proposalRibbonQuery = DynamicQueryFactoryUtil.forClass(ContestPhaseRibbonType.class, portletClassLoader);
        proposalRibbonQuery.add(PropertyFactoryUtil.forName("ribbon").eq(ribbon));
        proposalRibbonQuery.add(PropertyFactoryUtil.forName("hoverText").eq(hoverText));

        List<ContestPhaseRibbonType> ribbonTypes = null;
        try{
            ribbonTypes = ContestPhaseRibbonTypeLocalServiceUtil.dynamicQuery(proposalRibbonQuery);
        } catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        if (ribbonTypes == null || ribbonTypes.isEmpty()) return -1;
        return ribbonTypes.get(0).getId();
    }

    public static boolean associateProposalWithRibbon(long proposalId, long typeId, long contestPhaseId){
        try{
            Proposal2Phase proposal2Phase = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposalId, contestPhaseId);
            proposal2Phase.setRibbonTypeId(typeId);
            Proposal2PhaseLocalServiceUtil.updateProposal2Phase(proposal2Phase);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ContestPhaseRibbonType createNewContestPhaseRibbonType(String ribbon, String hoverText){
        ContestPhaseRibbonType ribbonType = null;
        try{
            ribbonType = ContestPhaseRibbonTypeLocalServiceUtil.createContestPhaseRibbonType(
                    CounterLocalServiceUtil.increment(ContestPhaseRibbonType.class.getName())
            );
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        ribbonType.setRibbon(Integer.valueOf(ribbon));
        ribbonType.setHoverText(hoverText);

        try{
            ContestPhaseRibbonTypeLocalServiceUtil.addContestPhaseRibbonType(ribbonType);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return ribbonType;
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
