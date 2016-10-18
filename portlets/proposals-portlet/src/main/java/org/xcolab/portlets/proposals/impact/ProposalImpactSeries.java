package org.xcolab.portlets.proposals.impact;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.xcolab.client.contest.ImpactClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeries;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.impact.ImpactIteration;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.util.MemberRoleChoiceAlgorithm;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalImpactAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;

/**
 * ProposalImpactSeries represents the data series for one sector-region pair.
 */
public class ProposalImpactSeries {

    // Static value field names
    public static final String SERIES_TYPE_BAU_KEY = "BAU";
    //    public static final String SERIES_TYPE_DDPP_KEY = "DDPP";
    public static final String SERIES_TYPE_RESULT_KEY = "RESULT";

    private final List<ImpactIteration> impactIterations;
    private final OntologyTerm whatTerm;
    private final OntologyTerm whereTerm;
    private final FocusArea focusArea;
    private final Proposal proposal;
    private final Map<String, ProposalImpactSeriesValues> seriesTypeToSeriesMap;
    private final Map<String, Boolean> seriesTypeToEditableMap;
    private final ImpactDefaultSeries bauSeries;
    private ProposalWrapper proposalWrapper;
    private ProposalVersion lastModifiedVersion;
    //    private ImpactDefaultSeries ddppSeries;
    private ProposalImpactSeriesValues resultValues;

    public ProposalImpactSeries(Contest contest, Proposal proposal, FocusArea focusArea) {
        this(contest, proposal, focusArea, true);
    }

    private ProposalImpactSeries(Contest contest, Proposal proposal, FocusArea focusArea,
            boolean loadData) {
        this.seriesTypeToSeriesMap = new HashMap<>();
        this.seriesTypeToEditableMap = new HashMap<>();
        this.focusArea = focusArea;
        this.proposal = proposal;
        this.proposalWrapper = new ProposalWrapper(proposal);
        this.whatTerm = ProposalImpactUtil.getWhatTerm(focusArea);
        this.whereTerm = ProposalImpactUtil.getWhereTerm(focusArea);
        this.impactIterations = ImpactClientUtil.getContestImpactIterations(contest);
        // Retrieve static serieses
        bauSeries = OntologyClientUtil
                .getImpactDefaultSeriesByFocusAreaName(focusArea.getId_(), SERIES_TYPE_BAU_KEY);
        addSeriesWithType(bauSeries, false, false);

        //        ddppSeries = ImpactDefaultSeriesLocalServiceUtil
        // .getImpactDefaultSeriesWithFocusAreaAndName(focusArea, SERIES_TYPE_DDPP_KEY);
        //        addSeriesWithType(ddppSeries, false);

        if (loadData) {
            loadEditableData();
        }
    }

    private void addSeriesWithType(ImpactDefaultSeries defaultSeries, boolean editable,
            boolean invertSeriesSign) {
        List<ImpactDefaultSeriesData> seriesDataList = OntologyClientUtil
                .getImpactDefaultSeriesDataBySeries(defaultSeries.getSeriesId());
        if (invertSeriesSign) {
            for (ImpactDefaultSeriesData seriesData : seriesDataList) {
                if (seriesData.getValue() != null) {
                    seriesData.setValue(seriesData.getValue() * (-1.0));
                }
            }
        }
        addSeriesWithType(defaultSeries.getName(), seriesDataList, editable);
    }

    private void addSeriesWithType(String seriesType, List<ImpactDefaultSeriesData> seriesDataList,
            boolean editable) {
        ProposalImpactSeriesValues seriesValues = new ProposalImpactSeriesValues();
        seriesTypeToSeriesMap.put(seriesType, seriesValues);
        seriesTypeToEditableMap.put(seriesType, editable);

        for (ImpactDefaultSeriesData defaultDataPoint : seriesDataList) {
            seriesValues.putSeriesValue(defaultDataPoint.getYear(), defaultDataPoint.getValue());
        }
    }

    private void loadEditableData() {
        // Get default serieses
        List<ImpactDefaultSeries> impactDefaultSerieses =
                OntologyClientUtil.getAllmpactDefaultSeriesByFocusArea(focusArea.getId_());

        // TODO create query to filter by additionalId?
        List<ProposalAttribute> impactProposalAttributes =
                ProposalAttributeClientUtil.getImpactProposalAttributes(proposal);

        for (ImpactDefaultSeries defaultSeries : impactDefaultSerieses) {

            // Look for already entered data
            if (defaultSeries.getEditable()) {

                // TODO write a separate finder for the proposal attribute that is being searched
                boolean foundEnteredData = false;
                for (ProposalAttribute attribute : impactProposalAttributes) {
                    if (attribute.getName().equals(defaultSeries.getName()) && Objects
                            .equals(attribute.getAdditionalId(), focusArea.getId_())) {
                        foundEnteredData = true;
                        addSeriesValueWithType(attribute.getName(),
                                attribute.getNumericValue().intValue(),
                                attribute.getRealValue());

                        // Set author and modification date
                        this.lastModifiedVersion = ProposalClientUtil
                                .getProposalVersionByProposalIdVersion(proposal.getProposalId(),
                                        attribute.getVersionWhenCreated());
                    }
                }

                // Use default data if not entered
                if (!foundEnteredData) {
                    List<ImpactDefaultSeriesData> defaultSeriesDataList =
                            OntologyClientUtil.getImpactDefaultSeriesDataBySeries(
                                    defaultSeries.getSeriesId());
                    addSeriesWithType(defaultSeries.getName(), defaultSeriesDataList, true);
                }
            }
        }
    }

    public void addSeriesValueWithType(String seriesType, int year, double value) {
        ProposalImpactSeriesValues seriesValues = seriesTypeToSeriesMap.get(seriesType);
        if (seriesValues == null) {
            seriesValues = new ProposalImpactSeriesValues();
            seriesTypeToSeriesMap.put(seriesType, seriesValues);
            seriesTypeToEditableMap.put(seriesType, true);
        }

        seriesValues.putSeriesValue(year, value);
    }

    public ProposalImpactSeries(Contest contest, Proposal proposal, FocusArea focusArea,
            JSONObject json) {
        this(contest, proposal, focusArea, false);

        for (ImpactDefaultSeries defaultSeries : OntologyClientUtil
                .getAllmpactDefaultSeriesByFocusArea(focusArea.getId_())) {
            if (!defaultSeries.getEditable()) {
                continue;
            }

            seriesTypeToEditableMap.put(defaultSeries.getName(), true);
            JSONObject seriesValues = json.getJSONObject(defaultSeries.getName());
            for (ImpactIteration iteration : impactIterations) {
                if (Double.isNaN(seriesValues.getDouble(iteration.getYear() + ""))) {
                    throw new RuntimeException(
                            "Could not parse value for year " + iteration.getYear());
                }
                addSeriesValueWithType(defaultSeries.getName(), iteration.getYear(),
                        seriesValues.getDouble(iteration.getYear() + ""));
            }
        }
    }

    public Map<String, ProposalImpactSeriesValues> getSeriesTypeToSeriesMap() {
        return seriesTypeToSeriesMap;
    }

    public ProposalImpactSeriesValues getSeriesValuesForType(String seriesType) {
        return seriesTypeToSeriesMap.get(seriesType);
    }

    public ProposalImpactSeriesValues getResultSeriesValues() {
        if (resultValues == null) {
            calculateResultSeriesValues();
        }

        return resultValues;
    }

    /**
     * Calculate the result values for each time point in the Iteration
     */
    private void calculateResultSeriesValues() {
        resultValues = new ProposalImpactSeriesValues();
        for (ImpactIteration impactIteration : impactIterations) {
            int currentYear = impactIteration.getYear();

            double bauValue =
                    OntologyClientUtil
                            .getImpactDefaultSeriesDataBySeriesIdAndYear(bauSeries.getSeriesId(),
                                    currentYear).getValue();

            double reductionRate =
                    seriesTypeToSeriesMap.get(ProposalImpactAttributeKeys.IMPACT_REDUCTION)
                            .getValueForYear(currentYear);
            double adoptionRate =
                    seriesTypeToSeriesMap.get(ProposalImpactAttributeKeys.IMPACT_ADOPTION_RATE)
                            .getValueForYear(currentYear);

            // Round to 2 decimal digits
            double resultValue =
                    Math.round((bauValue * (reductionRate * 0.01 * adoptionRate * 0.01)) * 100.0)
                            / 100.0;
            resultValues.putSeriesValue(currentYear, resultValue);
        }
    }

    public boolean getIsEditableForSeriesType(String seriesType) {
        return seriesTypeToEditableMap.get(seriesType);
    }

    public void persistWithAuthor(Member author) {
        // Persist all editable attributes
        for (Map.Entry<String, Boolean> entry : seriesTypeToEditableMap.entrySet()) {
            final String seriesType = entry.getKey();
            final Boolean isEditable = entry.getValue();
            if (isEditable) {
                ProposalImpactSeriesValues seriesValues =
                        this.seriesTypeToSeriesMap.get(seriesType);
                for (ImpactIteration iteration : impactIterations) {
                    double filteredValue = ProposalImpactValueFilterAlgorithm
                            .filterValueForImpactSeriesType(
                                    seriesValues.getValueForYear(iteration.getYear()), seriesType);
                    ProposalAttributeClientUtil
                            .setProposalAttribute(author.getUserId(), proposal.getProposalId(),
                                    seriesType,
                                    focusArea.getId_(), "", new Long(iteration.getYear()),
                                    filteredValue);
                }

            }
        }
    }

    public JSONObject toJSONObjectByFiltering(Set<String> filteredSeriesNames) {
        JSONObject jsonObject = toJSONObject();
        JSONObject newSeriesObject = JSONFactoryUtil.createJSONObject();
        Iterator<String> seriesNameIterator = jsonObject.getJSONObject("serieses").keys();
        while (seriesNameIterator.hasNext()) {
            String seriesName = seriesNameIterator.next();
            // Only pass series objects whoms name is not included in the filter set
            if (!filteredSeriesNames.contains(seriesName)) {
                newSeriesObject.put(seriesName,
                        jsonObject.getJSONObject("serieses").getJSONObject(seriesName));
            }
        }

        jsonObject.put("serieses", newSeriesObject);
        return jsonObject;
    }

    public JSONObject toJSONObject() {
        JSONObject returnObject = JSONFactoryUtil.createJSONObject();
        JSONObject serieses = JSONFactoryUtil.createJSONObject();

        returnObject.put("focusAreaId", getFocusArea().getId_());

        if (getSeriesAuthor() != null && getUpdatedDate() != null) {
            // Author info
            JSONObject authorObject = JSONFactoryUtil.createJSONObject();
            returnObject.put("author", authorObject);
            authorObject.put("userId", getSeriesAuthor().getUserId());
            MemberRoleChoiceAlgorithm impactRoleChoiceAlgorithm =
                    MemberRoleChoiceAlgorithm.proposalImpactTabAlgorithm;
            final String authorDescription =
                    impactRoleChoiceAlgorithm.getUserRoleDescription(this.getSeriesAuthor()) + " "
                            + this.getSeriesAuthor().getFullName();
            authorObject.put("name", authorDescription);

            // update date
            DateFormat dateFormatter = new SimpleDateFormat("MMMMMM d, YYYY, KK:mm a zzzz");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
            returnObject.put("updateDate", dateFormatter.format(getUpdatedDate()));
        }

        returnObject.put("serieses", serieses);
        for (Map.Entry<String, ProposalImpactSeriesValues> entry : seriesTypeToSeriesMap
                .entrySet()) {
            String seriesType = entry.getKey();
            final ProposalImpactSeriesValues seriesValues = seriesTypeToSeriesMap.get(seriesType);

            ImpactDefaultSeries defaultSeries = OntologyClientUtil
                    .getImpactDefaultSeriesByFocusAreaName(focusArea.getId_(), seriesType);

            JSONObject series = JSONFactoryUtil.createJSONObject();
            series.put("name", defaultSeries.getName());
            series.put("description", defaultSeries.getDescription());
            series.put("editable", defaultSeries.getEditable());
            series.put("values", seriesValues.toJSONArrayWithIteration(impactIterations));
            serieses.put(defaultSeries.getName(), series);
        }

        JSONArray iterations = JSONFactoryUtil.createJSONArray();
        for (ImpactIteration impactIteration : impactIterations) {
            iterations.put(impactIteration.getYear());
        }
        returnObject.put("iterations", iterations);
        returnObject.put("success", true);

        return returnObject;
    }

    public FocusArea getFocusArea() {
        return focusArea;
    }

    private Member getSeriesAuthor() {
        if (lastModifiedVersion != null) {
            return MembersClient.getMemberUnchecked(lastModifiedVersion.getAuthorId());
        }
        return null;
    }

    public Date getUpdatedDate() {
        return lastModifiedVersion.getCreateDate();
    }

    public ProposalWrapper getProposalWrapper() {
        return proposalWrapper;
    }

    public void setProposalWrapper(ProposalWrapper proposalWrapper) {
        this.proposalWrapper = proposalWrapper;
    }

    public OntologyTerm getWhatTerm() {
        return whatTerm;
    }

    public OntologyTerm getWhereTerm() {
        return whereTerm;
    }
}