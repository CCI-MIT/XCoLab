package com.ext.portlet.service.impl.helper.points;

/**
 * Created by Klemens Mang on 21/09/15.
 */

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PointDistributionTarget;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper data structure for the test points test suite
 */
public class ContestTierDataStructure {
    /**
     * Generic data structure that maps from a proposal nr to a generic collection
     * @param <T>
     */
    public class ProposalToCollection<T> {
        Map<Integer, List<T>> proposalToCollection;

        public ProposalToCollection() {
            proposalToCollection = new HashMap<Integer, List<T>>();
        }

        public void setProposal(int proposalNr) {
            proposalToCollection.put(proposalNr, new ArrayList<T>());
        }

        public void setProposalCollection(int proposalNumber, List<T> collection) {
            setProposal(proposalNumber);
            getProposalToCollection(proposalNumber).addAll(collection);
        }

        public List<T> getProposalToCollection(int proposalNr) {
            return proposalToCollection.get(proposalNr);
        }
    }

    /**
     * Map Collection encapsulating the ProposalTeamMember list per proposalNr
     */
    public class ProposalTeamMembers extends ProposalToCollection<User>{
        public void setProposalTeamMembers(int proposalNumber, List<User> teamMembers) {
            setProposal(proposalNumber);
            getProposalTeamMembers(proposalNumber).addAll(teamMembers);
        }

        public List<User> getProposalTeamMembers(int proposalNr) {
            return proposalToCollection.get(proposalNr);
        }
    }

    /**
     * Map collection encapsulating the DistributionConfiguration list per proposalNr
     */
    public class ProposalPointsDistributionConfigurations extends ProposalToCollection<DistributionConfiguration> {
        public void setPointsDistributionConfigurations(int proposalNumber, List<DistributionConfiguration> configurations) {
            setProposal(proposalNumber);
            getProposalToCollection(proposalNumber).addAll(configurations);
        }

        public void addDistributionConfiguration(int proposalNumber, DistributionConfiguration configuration) {
            List<DistributionConfiguration> configurations = getProposalToCollection(proposalNumber);
            if (configurations == null) {
                setProposal(proposalNumber);
                configurations = getProposalToCollection(proposalNumber);
            }

            configurations.add(configuration);
        }

        public List<DistributionConfiguration> getDistributionConfigurations(int proposalNumber) {
            return proposalToCollection.get(proposalNumber);
        }

        public int getSize() {
            return proposalToCollection.size();
        }
    }

    /**
     * Class that encapsulates a proposal reference reference to another proposal in a particular contestTier, contestNr, proposalNr and proposalId
     */
    public class ProposalLink {
        int toTierNr;
        int toContestNr;
        int toProposalNr;
        long toProposalId;

        public ProposalLink(int toTierNr, int toContestNr, int toProposalNr, long toProposalId) {
            this.toTierNr = toTierNr;
            this.toContestNr = toContestNr;
            this.toProposalNr = toProposalNr;
            this.toProposalId = toProposalId;
        }

        public int getToTierNr() {
            return toTierNr;
        }

        public int getToContestNr() {
            return toContestNr;
        }

        public int getToProposalNr() {
            return toProposalNr;
        }

        public long getToProposalId() {
            return toProposalId;
        }
    }

    public class ProposalLinks extends ProposalToCollection<ProposalLink> {
        public List<ProposalLink> getProposalLinkList(int proposalNr) {
            if (proposalToCollection.get(proposalNr) == null) {
                proposalToCollection.put(proposalNr, new ArrayList<ProposalLink>());
            }
            return proposalToCollection.get(proposalNr);
        }

        public void addProposalLink(int fromProposalNr, int toTierNr, int toContestNr, int toProposalNr, long toProposalId) {
            getProposalLinkList(fromProposalNr).add(new ProposalLink(toTierNr, toContestNr, toProposalNr, toProposalId));
        }
    }

    public class ProposalPointDistributionTargets extends ProposalToCollection<PointDistributionTarget> {
        public void setPointDistributionTargets(int proposalNumber, List<PointDistributionTarget> targets) {
            setProposal(proposalNumber);
            getProposalToCollection(proposalNumber).addAll(targets);
        }

        public void addPointDistributionTarget(int proposalNumber, PointDistributionTarget target) {
            List<PointDistributionTarget> targets = getProposalToCollection(proposalNumber);
            if (targets == null) {
                setProposal(proposalNumber);
                targets = getProposalToCollection(proposalNumber);
            }

            targets.add(target);
        }

        public List<PointDistributionTarget> getPointDistributionTargets(int proposalNumber) {
            return proposalToCollection.get(proposalNumber);
        }

        public int getSize() {
            return proposalToCollection.size();
        }
    }

    Map<Integer, ProposalTeamMembers> contestToProposalTeamMembersMap;
    Map<Integer, List<ContestPhase>> contestToContestPhasesMap;
    Map<Integer, ProposalLinks> contestToProposalLinks;
    Map<Integer, List<Proposal>> contestToProposals;
    Map<Integer, List<Integer>> contestToProposalsInLastPhaseMap;
    Map<Integer, ProposalPointsDistributionConfigurations> contestToProposalPointsDistributionConfigurationsMap;
    Map<Integer, ProposalPointDistributionTargets> contestToProposalPointsDistributionTargetsMap;
    List<Contest> contests;
    int amountOfProposals;

    public ContestTierDataStructure(int amountOfProposals) {
        this.amountOfProposals = amountOfProposals;
        contestToProposalTeamMembersMap = new HashMap<>();
        contestToContestPhasesMap = new HashMap<>();
        contestToProposalLinks = new HashMap<>();
        contestToProposals = new HashMap<>();
        contestToProposalsInLastPhaseMap = new HashMap<>();
        contestToProposalPointsDistributionConfigurationsMap = new HashMap<>();
        contestToProposalPointsDistributionTargetsMap = new HashMap<>();
        contests = new ArrayList<Contest>();
    }

    /**
     * Get a ProposalTeamMembers data structure for the given contestNr
     * @param contestNr
     * @return
     */
    public ProposalTeamMembers getProposalTeamMembers(int contestNr) {
        if (contestToProposalTeamMembersMap.get(contestNr) == null) {
            contestToProposalTeamMembersMap.put(contestNr, new ProposalTeamMembers());
        }
        return contestToProposalTeamMembersMap.get(contestNr);
    }

    public List<ContestPhase> getContestPhases(int contestNr) {
        if (contestToContestPhasesMap.get(contestNr) == null) {
            contestToContestPhasesMap.put(contestNr, new ArrayList<ContestPhase>());
        }
        return contestToContestPhasesMap.get(contestNr);
    }

    public ProposalLinks getProposalLinks(int contestNr) {
        if (contestToProposalLinks.get(contestNr) == null) {
            contestToProposalLinks.put((contestNr), new ProposalLinks());
        }
        return contestToProposalLinks.get(contestNr);
    }

    public List<Proposal> getProposals(int contestNr) {
        if (contestToProposals.get(contestNr) == null) {
            contestToProposals.put(contestNr, new ArrayList<Proposal>());
        }
        return contestToProposals.get(contestNr);
    }

    public List<Integer> getProposalsInLastPhase(int contestNr) {
        if (contestToProposalsInLastPhaseMap.get(contestNr) == null) {
            contestToProposalsInLastPhaseMap.put(contestNr, new ArrayList<Integer>());
        }
        return contestToProposalsInLastPhaseMap.get(contestNr);
    }

    public ProposalPointsDistributionConfigurations getProposalPointsDistributionConfigurations(int contestNr) {
        if (contestToProposalPointsDistributionConfigurationsMap.get(contestNr) == null) {
            contestToProposalPointsDistributionConfigurationsMap.put(contestNr, new ProposalPointsDistributionConfigurations());
        }
        return contestToProposalPointsDistributionConfigurationsMap.get(contestNr);
    }

    public void removeAllProposalPointsDistributionConfigurations(int contestNr) {
        contestToProposalPointsDistributionConfigurationsMap.remove(contestNr);
    }

    public List<Contest> getContests() {
        return contests;
    }

    public Contest getContest(int contestNr) {
        return contests.get(contestNr);
    }

    public int getAmountOfProposals() {
        return amountOfProposals;
    }

    public ProposalPointDistributionTargets getProposalPointDistributionTargets(int contestNr) {
        if (contestToProposalPointsDistributionTargetsMap.get(contestNr) == null) {
            contestToProposalPointsDistributionTargetsMap.put(contestNr, new ProposalPointDistributionTargets());
        }

        return contestToProposalPointsDistributionTargetsMap.get(contestNr);
    }

    public void addContest(Contest contest) {
        contests.add(contest);
    }

    public void addPointsDistributionConfiguration(int contestNr, int proposalNr, DistributionConfiguration configuration) {
        getProposalPointsDistributionConfigurations(contestNr).addDistributionConfiguration(proposalNr, configuration);
    }

    public void addPointDistributionTarget(int contestNr, int proposalNr, PointDistributionTarget target) {
        getProposalPointDistributionTargets(contestNr).addPointDistributionTarget(proposalNr, target);
    }

    public void removeAllPointDistributionTargets(int contestNr) {
        contestToProposalPointsDistributionTargetsMap.remove(contestNr);
    }
}
