package org.xcolab.portlets.proposals.wrappers;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang3.StringUtils;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.portlets.proposals.utils.ProposalsColumn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProposalsSortFilterBean {
    private final static Log _log = LogFactoryUtil.getLog(ProposalsSortFilterBean.class);

    private final List<ProposalWrapper> proposals;
    private Comparator<ProposalWrapper> proposalComparator;
    
    private List<ProposalWrapper> proposalsWithRibbons = new ArrayList<>();
    private List<ProposalWrapper> proposalsNormal = new ArrayList<>();

    public ProposalsSortFilterBean(List<ProposalWrapper> proposals, final SortFilterPage sortFilterPage) throws PortalException, SystemException {
        super();
        this.proposals = proposals;
        
        // sort proposals
        if (sortFilterPage != null && StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
            switch (sortFilterPage.getSortColumn().toUpperCase()) {
                case "NAME":
                    proposalComparator = ProposalsColumn.NAME.getComparator(); break;
                case "AUTHOR":
                    proposalComparator = ProposalsColumn.AUTHOR.getComparator(); break;
                case "COMMENTS":
                    proposalComparator = ProposalsColumn.COMMENTS.getComparator(); break;
                case "CONTRIBUTORS":
                    proposalComparator = ProposalsColumn.CONTRIBUTORS.getComparator(); break;
                case "MODIFIED":
                    proposalComparator = ProposalsColumn.MODIFIED.getComparator(); break;
                case "SUPPORTERS":
                    proposalComparator = ProposalsColumn.SUPPORTERS.getComparator(); break;
                case "VOTES":
                    proposalComparator = ProposalsColumn.VOTES.getComparator(); break;
                case "JUDGESTATUS":
                    proposalComparator = ProposalsColumn.JUDGESTATUS.getComparator(); break;
                case "SCREENINGSTATUS":
                    proposalComparator = ProposalsColumn.SCREENINGSTATUS.getComparator(); break;
                case "OVERALLSTATUS":
                    proposalComparator = ProposalsColumn.OVERALLSTATUS.getComparator(); break;
            }
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
                    final RibbonWrapper ribbon1 = o1.getRibbonWrapper();
                    final RibbonWrapper ribbon2 = o2.getRibbonWrapper();

                    int sortOrderDiff = ribbon1.getSortOrder() - ribbon2.getSortOrder();
                    if (sortOrderDiff != 0) {
                        return sortOrderDiff;
                    }

                    int ribbonDiff = ribbon1.getRibbon() - ribbon2.getRibbon();
                    if (ribbonDiff != 0) {
                        return ribbonDiff;
                    }
                }
                if (sortFilterPage.isSortAscending()) {
                    return proposalComparator.compare(o1, o2);
                }
                return proposalComparator.compare(o2, o1);
            }
        });
        
        for (ProposalWrapper contest: this.proposals) {
            if (contest.getRibbonWrapper().getRibbon() > 0) {
                proposalsWithRibbons.add(contest);
            } else {
                proposalsNormal.add(contest);
            }
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
}
