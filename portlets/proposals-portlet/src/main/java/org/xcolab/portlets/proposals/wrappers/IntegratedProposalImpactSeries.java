package org.xcolab.portlets.proposals.wrappers;




import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.OntologyTerm;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.proposals.utils.SectorTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mente on 23/03/15.
 */
public class IntegratedProposalImpactSeries {

    private final static Long WHAT_ONTOLOGY_SPACE_ID = 103L;
    private final static Long WHERE_ONTOLOGY_SPACE_ID = 104L;

    /*
    Add key ProposalImpactSeries.SERIES_TYPE_DDPP_KEY to this array to include DDPP series type
     */
    private static final String[] REFERENCE_SERIES_TYPES = new String[] {
            ProposalImpactSeries.SERIES_TYPE_BAU_KEY,
            };

    private static final String[] REFERENCE_SERIES_TYPE_DESCRIPTIONS = new String[] {
            "Business as usual (BAU)",
            };

    private Map<String, String> seriesTypeToDescriptionMap;

    private Map<String, ProposalImpactSeriesValues> seriesTypeToAggregatedSeriesMap;

    private ProposalImpactSeriesValues resultSeriesValues;

    private Proposal proposal;

    private OntologyTerm regionOntologyTerm;

    public IntegratedProposalImpactSeries(Proposal proposal, Contest contest) throws PortalException, SystemException {
        try{
            org.xcolab.client.contest.pojo.Contest contestMicro = ContestClient.getContest(contest.getContestPK());
            ContestWrapper contestWrapper = new ContestWrapper(contestMicro);
            this.regionOntologyTerm = contestWrapper.getWhere().get(0);
            this.proposal = proposal;
            this.resultSeriesValues = new ProposalImpactSeriesValues();
            boolean global = contest.getContestTier() == ContestTier.GLOBAL.getTierType();
            calculateIntegratedImpactSeries(global);
        }catch (ContestNotFoundException ignored){

        }

    }

    public IntegratedProposalImpactSeries(Proposal proposal) throws PortalException, SystemException {
        this.proposal = proposal;
        this.resultSeriesValues = new ProposalImpactSeriesValues();
        calculateIntegratedImpactSeries(false);
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

    private void calculateIntegratedImpactSeries(boolean global) throws SystemException, PortalException {
        Set<Proposal> referencedSubProposals = getSubProposalsOnContestTier(proposal, ContestTier.BASIC.getTierType());
        seriesTypeToAggregatedSeriesMap = new LinkedHashMap<>(REFERENCE_SERIES_TYPES.length);
        seriesTypeToDescriptionMap = new LinkedHashMap<>(REFERENCE_SERIES_TYPES.length);

        // Init series values
        int index = 0;
        for (String seriesType : REFERENCE_SERIES_TYPES) {
            seriesTypeToAggregatedSeriesMap.put(seriesType, new ProposalImpactSeriesValues());
            seriesTypeToDescriptionMap.put(seriesType, REFERENCE_SERIES_TYPE_DESCRIPTIONS[index++]);
        }

        for (SectorTypes seriesType : SectorTypes.values()) {
            seriesTypeToAggregatedSeriesMap.put(seriesType.getSectorOntologyTermId().toString(), new ProposalImpactSeriesValues());
            seriesTypeToDescriptionMap.put(seriesType.getSectorOntologyTermId().toString(), seriesType.getSectorName());
        }

        List<Long> sectorOntologyTermIds = new ArrayList<>();
        for(SectorTypes seriesType : SectorTypes.values()) {
            sectorOntologyTermIds.add(seriesType.getSectorOntologyTermId());
        }

        Map<String, ProposalImpactSeriesValues> aggregatedSeriesValues;
        Map<String, ProposalImpactSeriesValues> sectorsProposalAggregatedSeriesValues;

        for (Proposal referencedSubProposal : referencedSubProposals) {
            try {

                ProposalImpactSeriesList impactSeriesList = new ProposalImpactSeriesList(referencedSubProposal);

                aggregatedSeriesValues = global ?
                        impactSeriesList.getAggregatedSeriesValues(Arrays.asList(REFERENCE_SERIES_TYPES)) :
                        impactSeriesList.getAggregatedSeriesValues(Arrays.asList(REFERENCE_SERIES_TYPES), regionOntologyTerm);

                addUpProposalImpactDefaultSeriesValues(aggregatedSeriesValues);

                addUpProposalImpactAggregatedSeriesValues(aggregatedSeriesValues);

                sectorsProposalAggregatedSeriesValues = global ?
                        impactSeriesList.getAggregatedWeightedSeriesValuesBySectorOntologyTermIds(sectorOntologyTermIds) :
                        impactSeriesList.getAggregatedSeriesValuesByRegionAndSectorOntologyTermIds(regionOntologyTerm.getId_(), sectorOntologyTermIds);

                addUpProposalImpactSectorSeriesValues(sectorsProposalAggregatedSeriesValues);
            }catch (ContestNotFoundException cnf){

            }
        }
    }


    private void  addUpProposalImpactDefaultSeriesValues(Map<String, ProposalImpactSeriesValues> basicProposalAggregatedSeriesValues){
        for (String seriesType : REFERENCE_SERIES_TYPES) {
            ProposalImpactSeriesValues aggregatedSeriesValues =
                    seriesTypeToAggregatedSeriesMap.get(seriesType);
            ProposalImpactSeriesValues basicProposalSeriesValues =
                    basicProposalAggregatedSeriesValues.get(seriesType);
            aggregatedSeriesValues.addImpactSeriesValues(basicProposalSeriesValues);
        }
    }

    private void  addUpProposalImpactSectorSeriesValues(Map<String, ProposalImpactSeriesValues> sectorsProposalAggregatedSeriesValues){
        for (SectorTypes seriesType : SectorTypes.values()) {
            ProposalImpactSeriesValues aggregatedSeriesValues =
                    seriesTypeToAggregatedSeriesMap.get(seriesType.getSectorOntologyTermId().toString());
            ProposalImpactSeriesValues sectorProposalAggregatedSeriesValues =
                    sectorsProposalAggregatedSeriesValues.get(seriesType.getSectorOntologyTermId().toString());
            aggregatedSeriesValues.addImpactSeriesValues(sectorProposalAggregatedSeriesValues);
        }
    }

    private void  addUpProposalImpactAggregatedSeriesValues(Map<String, ProposalImpactSeriesValues> basicProposalAggregatedSeriesValues){
        ProposalImpactSeriesValues basicProposalResultSeriesValues = basicProposalAggregatedSeriesValues.get(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY);
        resultSeriesValues.addImpactSeriesValues(basicProposalResultSeriesValues);
    }

    public static Set<Proposal> getSubProposalsOnContestTier(Proposal proposal, Long contestTierId) throws SystemException, PortalException {
        Set<Proposal> subProposalsOnContestTier = new HashSet<>();
        getSubProposalsOnContestTier(Arrays.asList(proposal), subProposalsOnContestTier, contestTierId);
        return subProposalsOnContestTier;
    }

    public static void getSubProposalsOnContestTier(List<Proposal> proposals, Set<Proposal> subProposalsOnContestTier, Long contestTierId) throws SystemException, PortalException {
        if (proposals.size() > 0) {
            for (Proposal proposal : proposals) {
                try {
                    Contest contestOfProposal = ProposalsClient.getLatestContestInProposal(proposal.getProposalId());
                    if (contestTierId == contestOfProposal.getContestTier()) {
                        subProposalsOnContestTier.addAll(proposals);
                    } else {
                        List<Proposal> subProposals = ProposalsClient.getContestIntegrationRelevantSubproposals(proposal.getProposalId());
                        getSubProposalsOnContestTier(subProposals, subProposalsOnContestTier, contestTierId);
                    }
                }catch (ContestNotFoundException ignored){

                }
            }
        }
    }
}
