package org.xcolab.view.pages.proposals.wrappers;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.proposals.ProposalRibbon;
import org.xcolab.view.pages.proposals.utils.ProposalsColumn;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProposalsSortFilterBean {

    private final List<Proposal> proposals;
    private Comparator<Proposal> proposalComparator;
    
    private List<Proposal> proposalsWithRibbons = new ArrayList<>();
    private List<Proposal> proposalsNormal = new ArrayList<>();

    public ProposalsSortFilterBean(List<Proposal> proposals, final SortFilterPage sortFilterPage) {
        super();
        this.proposals = proposals;

        if (sortFilterPage == null) {
            throw new IllegalArgumentException("sortFilterPage was null");
        }
        
        // sort proposals
        if (StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
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
                default:
                    throw new IllegalArgumentException("Unknown sort column");
            }
        }
        
        if (StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
            proposalComparator = ProposalsColumn.valueOf(sortFilterPage.getSortColumn()).getComparator();
        }
        else {
            proposalComparator = ProposalsColumn.MODIFIED.getComparator();
            sortFilterPage.setSortAscending(!sortFilterPage.isSortAscending()); // default sort is date DESC
        }

        if(this.proposals!=null&& !this.proposals.isEmpty()) {

            Collections.sort(this.proposals, new Comparator<Proposal>() {
                @Override
                public int compare(Proposal o1, Proposal o2) {
                    if (StringUtils.isBlank(sortFilterPage.getSortColumn())) {
                        final ProposalRibbon ribbon1 = o1.getRibbonWrapper();
                        final ProposalRibbon ribbon2 = o2.getRibbonWrapper();

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

            for (Proposal contest : this.proposals) {
                if (contest.getRibbonWrapper().getRibbon() > 0) {
                    proposalsWithRibbons.add(contest);
                } else {
                    proposalsNormal.add(contest);
                }
            }
        }
    }

    public List<Proposal> getProposalsWithRibbons() {
        return proposalsWithRibbons;
    }

    public void setProposalsWithRibbons(List<Proposal> proposalsWithRibbons) {
        this.proposalsWithRibbons = proposalsWithRibbons;
    }

    public List<Proposal> getProposalsNormal() {
        return proposalsNormal;
    }

    public void setProposalsNormal(List<Proposal> proposalsNormal) {
        this.proposalsNormal = proposalsNormal;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }
}
