package org.xcolab.view.pages.proposals.impact;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.view.pages.proposals.utils.SectorTypes;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class IntegratedProposalImpactSeries {

    /*
    Add key ProposalImpactSeries.SERIES_TYPE_DDPP_KEY to this array to include DDPP series type
     */
    private static final String[] REFERENCE_SERIES_TYPES = {
            ProposalImpactSeries.SERIES_TYPE_BAU_KEY,
    };

    private static final String[] REFERENCE_SERIES_TYPE_DESCRIPTIONS = {
            "Business as usual (BAU)",
    };

    private Map<String, String> seriesTypeToDescriptionMap;

    private Map<String, ProposalImpactSeriesValues> seriesTypeToAggregatedSeriesMap;

    private final ProposalImpactSeriesValues resultSeriesValues;

    private final Proposal proposal;

    private final OntologyTerm regionOntologyTerm;

    public IntegratedProposalImpactSeries(ProposalContext proposalContext,
            Proposal proposal, Contest contest) {
        this.regionOntologyTerm = contest.getWhere().get(0);
            this.proposal = proposal;
            this.resultSeriesValues = new ProposalImpactSeriesValues();
            boolean global = contest.getContestTier() == ContestTier.GLOBAL.getTierType();
            calculateIntegratedImpactSeries(proposalContext, global);

    }

    public static Set<Proposal> getSubProposalsOnContestTier(ProposalContext proposalContext,
            Proposal proposal, Long contestTierId) {
        Set<Proposal> subProposalsOnContestTier = new HashSet<>();
        getSubProposalsOnContestTier(proposalContext, Collections.singletonList(proposal), subProposalsOnContestTier,
                contestTierId);
        return subProposalsOnContestTier;
    }

    private static void getSubProposalsOnContestTier(ProposalContext proposalContext, List<Proposal> proposals,
            Set<Proposal> subProposalsOnContestTier, Long contestTierId) {
        if (!proposals.isEmpty()) {
            for (Proposal proposal : proposals) {
                try {
                    Contest contestOfProposal =
                            proposalContext.getClients().getProposalClient().getLatestContestInProposal(proposal.getId());
                    if (Objects.equals(contestTierId, contestOfProposal.getContestTier())) {
                        subProposalsOnContestTier.addAll(proposals);
                    } else {
                        List<Proposal> subProposals = ProposalClientUtil
                                .getContestIntegrationRelevantSubproposals(
                                        proposal.getId());
                        getSubProposalsOnContestTier(proposalContext, subProposals, subProposalsOnContestTier,
                                contestTierId);
                    }
                } catch (ContestNotFoundException ignored) {

                }
            }
        }
    }

    public Map<String, ProposalImpactSeriesValues> getSeriesTypeToAggregatedSeriesMap() {
        return seriesTypeToAggregatedSeriesMap;
    }

    public ProposalImpactSeriesValues getResultSeriesValues() {
        return resultSeriesValues;
    }

    public Map<String, String> getSeriesTypeToDescriptionMap() {
        return seriesTypeToDescriptionMap;
    }

    public boolean getEmptySeries() {
        return this.resultSeriesValues.isEmpty();
    }

    private void calculateIntegratedImpactSeries(ProposalContext proposalContext, boolean global) {
        Set<Proposal> referencedSubProposals =
                getSubProposalsOnContestTier(proposalContext, proposal, ContestTier.BASIC.getTierType());
        seriesTypeToAggregatedSeriesMap = new LinkedHashMap<>(REFERENCE_SERIES_TYPES.length);
        seriesTypeToDescriptionMap = new LinkedHashMap<>(REFERENCE_SERIES_TYPES.length);

        // Init series values
        int index = 0;
        for (String seriesType : REFERENCE_SERIES_TYPES) {
            seriesTypeToAggregatedSeriesMap.put(seriesType, new ProposalImpactSeriesValues());
            seriesTypeToDescriptionMap.put(seriesType, REFERENCE_SERIES_TYPE_DESCRIPTIONS[index++]);
        }

        for (SectorTypes seriesType : SectorTypes.values()) {
            seriesTypeToAggregatedSeriesMap.put(seriesType.getSectorOntologyTermId().toString(),
                    new ProposalImpactSeriesValues());
            seriesTypeToDescriptionMap.put(seriesType.getSectorOntologyTermId().toString(),
                    seriesType.getSectorName());
        }

        List<Long> sectorOntologyTermIds = new ArrayList<>();
        for (SectorTypes seriesType : SectorTypes.values()) {
            sectorOntologyTermIds.add(seriesType.getSectorOntologyTermId());
        }

        for (Proposal referencedSubProposal : referencedSubProposals) {
            try {

                ProposalImpactSeriesList impactSeriesList =
                        new ProposalImpactSeriesList(referencedSubProposal);

                Map<String, ProposalImpactSeriesValues> aggregatedSeriesValues = global ?
                        impactSeriesList
                                .getAggregatedSeriesValues(Arrays.asList(REFERENCE_SERIES_TYPES)) :
                        impactSeriesList
                                .getAggregatedSeriesValues(Arrays.asList(REFERENCE_SERIES_TYPES),
                                        regionOntologyTerm);

                addUpProposalImpactDefaultSeriesValues(aggregatedSeriesValues);

                addUpProposalImpactAggregatedSeriesValues(aggregatedSeriesValues);

                Map<String, ProposalImpactSeriesValues> sectorsProposalAggregatedSeriesValues =
                        global ?
                                impactSeriesList
                                        .getAggregatedWeightedSeriesValuesBySectorOntologyTermIds(
                                                sectorOntologyTermIds) :
                                impactSeriesList
                                        .getAggregatedSeriesValuesByRegionAndSectorOntologyTermIds(
                                                regionOntologyTerm.getId(), sectorOntologyTermIds);

                addUpProposalImpactSectorSeriesValues(sectorsProposalAggregatedSeriesValues);
            } catch (ContestNotFoundException cnf) {

            }
        }
    }

    private void addUpProposalImpactDefaultSeriesValues(
            Map<String, ProposalImpactSeriesValues> basicProposalAggregatedSeriesValues) {
        for (String seriesType : REFERENCE_SERIES_TYPES) {
            ProposalImpactSeriesValues aggregatedSeriesValues =
                    seriesTypeToAggregatedSeriesMap.get(seriesType);
            ProposalImpactSeriesValues basicProposalSeriesValues =
                    basicProposalAggregatedSeriesValues.get(seriesType);
            aggregatedSeriesValues.addImpactSeriesValues(basicProposalSeriesValues);
        }
    }

    private void addUpProposalImpactSectorSeriesValues(
            Map<String, ProposalImpactSeriesValues> sectorsProposalAggregatedSeriesValues) {
        for (SectorTypes seriesType : SectorTypes.values()) {
            ProposalImpactSeriesValues aggregatedSeriesValues =
                    seriesTypeToAggregatedSeriesMap
                            .get(seriesType.getSectorOntologyTermId().toString());
            ProposalImpactSeriesValues sectorProposalAggregatedSeriesValues =
                    sectorsProposalAggregatedSeriesValues
                            .get(seriesType.getSectorOntologyTermId().toString());
            aggregatedSeriesValues.addImpactSeriesValues(sectorProposalAggregatedSeriesValues);
        }
    }

    private void addUpProposalImpactAggregatedSeriesValues(
            Map<String, ProposalImpactSeriesValues> basicProposalAggregatedSeriesValues) {
        ProposalImpactSeriesValues basicProposalResultSeriesValues =
                basicProposalAggregatedSeriesValues
                        .get(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY);
        resultSeriesValues.addImpactSeriesValues(basicProposalResultSeriesValues);
    }
}
