package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.ImpactDefaultSeries;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A collection of ProposalImpactSeries representing all data series of each sector-region pair.
 *
 * Created by kmang on 12/03/15.
 */
public class ProposalImpactSeriesList {
    private List<ProposalImpactSeries> impactSerieses;

    public ProposalImpactSeriesList(Proposal proposal) throws SystemException, PortalException {
        this (ProposalLocalServiceUtil.getLatestProposalContest(proposal.getProposalId()), proposal);
    }

    public ProposalImpactSeriesList(Contest contest, Proposal proposal)
            throws PortalException, SystemException {

        this.impactSerieses = new ArrayList<ProposalImpactSeries>();
        for (FocusArea focusArea : ProposalLocalServiceUtil.getImpactProposalFocusAreas(proposal)) {
            // Get the impact series for the respective focus area
            this.impactSerieses.add(new ProposalImpactSeries(contest, proposal, focusArea));
        }

        Collections.sort(impactSerieses, new Comparator<ProposalImpactSeries>() {
            // Sort by whatTerm name and whereTerm name
            @Override
            public int compare(ProposalImpactSeries o1, ProposalImpactSeries o2) {
                int diff = o1.getWhatTerm().getName().compareTo(o2.getWhatTerm().getName());

                if (diff == 0) {
                    return o1.getWhereTerm().getName().compareTo(o2.getWhereTerm().getName());
                }

                return diff;
            }
        });
    }

    public List<ProposalImpactSeries> getImpactSerieses() {
        return impactSerieses;
    }

    public FocusArea getFocusAreaForTerms(OntologyTerm whatTerm, OntologyTerm whereTerm) {
        for (ProposalImpactSeries impactSeries : impactSerieses) {
            if (impactSeries.getWhatTerm().getId() == whatTerm.getId() &&
                    impactSeries.getWhereTerm().getId() == whereTerm.getId()) {

                return impactSeries.getFocusArea();
            }
        }

        return null;
    }

    /**
     * Returns a map containing aggregated proposal impact series values of all sector-region pairs of a proposal
     *
     * @param seriesTypes           seriesTypes that are being calculated
     * @return                      A map containing a ProposalImpactSeriesValues object for each impact series type
     * @throws SystemException
     * @throws PortalException
     */
    public Map<String, ProposalImpactSeriesValues> getAggregatedSeriesValues(List<String> seriesTypes) throws SystemException, PortalException {
        Map<String, ProposalImpactSeriesValues> seriesTypeToSeriesSumMap = new HashMap<>(seriesTypes.size());

        for (String seriesType : seriesTypes) {
            seriesTypeToSeriesSumMap.put(seriesType, new ProposalImpactSeriesValues());
        }
        seriesTypeToSeriesSumMap.put(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY, new ProposalImpactSeriesValues());

        for (ProposalImpactSeries impactSeries : impactSerieses) {
            for (String seriesType : seriesTypes) {
                ProposalImpactSeriesValues integratedSeriesValues = seriesTypeToSeriesSumMap.get(seriesType);
                ProposalImpactSeriesValues impactSeriesValues = impactSeries.getSeriesValuesForType(seriesType);

                // Add up all the impact series values
                integratedSeriesValues.addImpactSeriesValues(impactSeriesValues);
            }

            // Aggregate result impact series
            ProposalImpactSeriesValues integratedSeriesValues = seriesTypeToSeriesSumMap.get(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY);
            ProposalImpactSeriesValues impactSeriesValues = impactSeries.getResultSeriesValues();
            integratedSeriesValues.addImpactSeriesValues(impactSeriesValues);
        }

        return seriesTypeToSeriesSumMap;
    }
}
