package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mente on 23/03/15.
 */
public class IntegratedProposalImpactSeries {

    private static final String[] SERIES_TYPES = new String[] {ProposalImpactSeries.SERIES_TYPE_BAU_KEY,
            ProposalImpactSeries.SERIES_TYPE_DDPP_KEY};

    private Map<String, ProposalImpactSeriesValues> seriesTypeToAggregatedSeriesMap;

    private ProposalImpactSeriesValues resultSeriesValues;

    private Proposal proposal;

    public IntegratedProposalImpactSeries(Proposal proposal) throws PortalException, SystemException {
        this.proposal = proposal;
        this.resultSeriesValues = new ProposalImpactSeriesValues();

        calculateIntegratedImpactSeries();
    }

    public Map<String, ProposalImpactSeriesValues> getSeriesTypeToAggregatedSeriesMap() {
        return seriesTypeToAggregatedSeriesMap;
    }

    public ProposalImpactSeriesValues getResultSeriesValues() {
        return resultSeriesValues;
    }

    private void calculateIntegratedImpactSeries() throws SystemException, PortalException {
        List<Proposal> referencedProposals = ProposalLocalServiceUtil.getSubproposals(proposal.getProposalId(), true);

        seriesTypeToAggregatedSeriesMap = new HashMap<>(SERIES_TYPES.length);
        // Init series values
        for (String seriesType : SERIES_TYPES) {
            seriesTypeToAggregatedSeriesMap.put(seriesType, new ProposalImpactSeriesValues());
        }

        // Sum over all referenced proposals
        for (Proposal referencedProposal : referencedProposals) {
            ProposalImpactSeriesList impactSeriesList = new ProposalImpactSeriesList(referencedProposal);

            // ProposalImpactSeriesValues object for each series type
            Map<String, ProposalImpactSeriesValues> basicProposalAggregatedSeriesValues = impactSeriesList.getAggregatedSeriesValues(Arrays.asList(SERIES_TYPES));

            for (String seriesType : SERIES_TYPES) {
                // Add up proposal impact series values
                ProposalImpactSeriesValues aggregatedSeriesValues = seriesTypeToAggregatedSeriesMap.get(seriesType);
                ProposalImpactSeriesValues basicProposalSeriesValues = basicProposalAggregatedSeriesValues.get(seriesType);
                aggregatedSeriesValues.addImpactSeriesValues(basicProposalSeriesValues);
            }

            // Add up proposal impact result series values
            ProposalImpactSeriesValues basicProposalResultSeriesValues = basicProposalAggregatedSeriesValues.get(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY);
            resultSeriesValues.addImpactSeriesValues(basicProposalResultSeriesValues);
        }
    }
}
