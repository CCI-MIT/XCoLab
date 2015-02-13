package org.xcolab.wrapper.proposal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang3.StringUtils;
import org.xcolab.commons.beans.SortFilterPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProposalsSortFilterBean {
    private final List<ProposalWrapper> proposals;
    private final SortFilterPage sortFilterPage;
    private Comparator<ProposalWrapper> proposalComparator;
    
    private List<ProposalWrapper> proposalsWithRibbons = new ArrayList<ProposalWrapper>();
    private List<ProposalWrapper> proposalsNormal = new ArrayList<ProposalWrapper>();
    

    public ProposalsSortFilterBean(List<ProposalWrapper> proposals, final SortFilterPage sortFilterPage) throws PortalException, SystemException {
        super();
        this.sortFilterPage = sortFilterPage;
        this.proposals = proposals;
        
        // sort proposals
        if (sortFilterPage != null && StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
            if (sortFilterPage.getSortColumn().equalsIgnoreCase("NAME")) proposalComparator = ProposalsColumn.NAME.getComparator();
            if (sortFilterPage.getSortColumn().equalsIgnoreCase("AUTHOR")) proposalComparator = ProposalsColumn.AUTHOR.getComparator();
            if (sortFilterPage.getSortColumn().equalsIgnoreCase("COMMENTS")) proposalComparator = ProposalsColumn.COMMENTS.getComparator();
            if (sortFilterPage.getSortColumn().equalsIgnoreCase("CONTRIBUTORS")) proposalComparator = ProposalsColumn.CONTRIBUTORS.getComparator();
            if (sortFilterPage.getSortColumn().equalsIgnoreCase("MODIFIED")) proposalComparator = ProposalsColumn.MODIFIED.getComparator();
            if (sortFilterPage.getSortColumn().equalsIgnoreCase("SUPPORTERS")) proposalComparator = ProposalsColumn.SUPPORTERS.getComparator();
            if (sortFilterPage.getSortColumn().equalsIgnoreCase("VOTES")) proposalComparator = ProposalsColumn.VOTES.getComparator();
            if (sortFilterPage.getSortColumn().equalsIgnoreCase("JUDGESTATUS")) proposalComparator = ProposalsColumn.JUDGESTATUS.getComparator();
            if (sortFilterPage.getSortColumn().equalsIgnoreCase("SCREENINGSTATUS")) proposalComparator = ProposalsColumn.SCREENINGSTATUS.getComparator();

            if (sortFilterPage.getSortColumn().equalsIgnoreCase("OVERALLSTATUS")) proposalComparator = ProposalsColumn.OVERALLSTATUS.getComparator();
        }

        
        if (StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
            proposalComparator = ProposalsColumn.valueOf(sortFilterPage.getSortColumn()).getComparator();
        }
        else {
            proposalComparator = ProposalsColumn.MODIFIED.getComparator();
            sortFilterPage.setSortAscending(!sortFilterPage.isSortAscending()); // default sort is date DESC
        }
        
        
        Collections.sort(this.proposals, new Comparator<ProposalWrapper>() {
            @Override
            public int compare(ProposalWrapper o1, ProposalWrapper o2) {
                if (StringUtils.isBlank(sortFilterPage.getSortColumn())) {
                    try {
                        int ribbonDiff = o1.getRibbon() - o2.getRibbon();
                        if (ribbonDiff != 0) {
                            return ribbonDiff;
                        }
                    }
                    catch (Exception e) {
                        _log.error("can't compare proposals", e);
                    }
                }
                int ret = proposalComparator.compare(o1, o2);

                return sortFilterPage.isSortAscending() ? ret : - ret;
            }
        });
        
        for (ProposalWrapper contest: this.proposals) {
            if (contest.getRibbon() > 0) proposalsWithRibbons.add(contest);
            else proposalsNormal.add(contest);
        }
    }

    
    
    public List<ProposalWrapper> getProposalsWithRibbons() {
        return proposalsWithRibbons;
    }



    public void setProposalsWithRibbons(List<ProposalWrapper> proposalsWithRibbons) {
        this.proposalsWithRibbons = proposalsWithRibbons;
    }

    public List<ProposalWrapper> getProposalsNormal() {
        return proposalsNormal;
    }



    public void setProposalsNormal(List<ProposalWrapper> proposalsNormal) {
        this.proposalsNormal = proposalsNormal;
    }



    public List<ProposalWrapper> getProposals() {
        return proposals;
    }



    private final static Log _log = LogFactoryUtil.getLog(ProposalsSortFilterBean.class);
}
