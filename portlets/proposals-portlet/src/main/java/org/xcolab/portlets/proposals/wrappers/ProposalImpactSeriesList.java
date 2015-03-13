package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.ImpactDefaultSeries;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil;
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

    public ProposalImpactSeriesList(List<ProposalAttribute> proposalImpactAttributes, List<ImpactIteration> impactIterations)
            throws PortalException, SystemException {

        Map<Long, ProposalImpactSeries> focusAreaIdToImpactSeriesMap = new HashMap<>();

        for (ProposalAttribute attribute : proposalImpactAttributes) {
            // Get the impact series for the respective focus area
            FocusArea focusArea = FocusAreaLocalServiceUtil.getFocusArea(attribute.getAdditionalId());
            ProposalImpactSeries impactSeries = focusAreaIdToImpactSeriesMap.get(focusArea.getId()); // additionalId = focusAreaId
            if (Validator.isNull(impactSeries)) {
                impactSeries = new ProposalImpactSeries(focusArea, impactIterations);
                focusAreaIdToImpactSeriesMap.put(focusArea.getId(), impactSeries);
            }

            // Add impact series value for the specified impact type (BAU,...)
            int year = (int)attribute.getNumericValue();
            double value = attribute.getRealValue();
            String impactSeriesName = attribute.getName();
            impactSeries.addSeriesValueWithType(impactSeriesName, year, value);
        }

        this.impactSerieses = new ArrayList<ProposalImpactSeries>(focusAreaIdToImpactSeriesMap.values());
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
}
