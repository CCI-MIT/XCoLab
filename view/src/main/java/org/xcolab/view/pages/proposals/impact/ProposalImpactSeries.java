package org.xcolab.view.pages.proposals.impact;

import org.json.JSONArray;
import org.json.JSONObject;

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
import org.xcolab.client.proposals.enums.ImpactSeriesType;
import org.xcolab.client.proposals.helpers.ProposalAttributeHelper;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    private final Map<ImpactSeriesType, ProposalImpactSeriesValues> seriesTypeToSeriesMap;
    private final Map<ImpactSeriesType, Boolean> seriesTypeToEditableMap;
    private final ImpactDefaultSeries bauSeries;
    private Proposal proposalWrapper;
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
        this.proposalWrapper = proposal;
        this.whatTerm = ProposalImpactUtil.getWhatTerm(focusArea);
        this.whereTerm = ProposalImpactUtil.getWhereTerm(focusArea);
        this.impactIterations = ImpactClientUtil.getContestImpactIterations(contest);
        // Retrieve static serieses
        bauSeries = OntologyClientUtil
                .getImpactDefaultSeriesByFocusAreaAndSeriesName(focusArea.getId_(), SERIES_TYPE_BAU_KEY);
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
        final ImpactSeriesType seriesType = ImpactSeriesType.valueOf(defaultSeries.getName());
        addSeriesWithType(seriesType, seriesDataList, editable);
    }

    private void addSeriesWithType(ImpactSeriesType seriesType, List<ImpactDefaultSeriesData> seriesDataList,
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

        final ProposalAttributeHelper proposalAttributeHelper =
                proposal.getProposalAttributeHelper();

        for (ImpactDefaultSeries defaultSeries : impactDefaultSerieses) {

            // Look for already entered data
            if (defaultSeries.getEditable()) {

                boolean foundEnteredData = false;
                final ImpactSeriesType seriesType =
                        ImpactSeriesType.valueOf(defaultSeries.getName());
                for (ImpactIteration iteration : impactIterations) {
                    String attributeName = defaultSeries.getName() + "_" + iteration.getYear();
                    final ProposalAttribute attribute = proposalAttributeHelper
                            .getAttributeOrNull(attributeName, focusArea.getId_());
                    if (attribute != null) {
                        foundEnteredData = true;
                        addSeriesValueWithType(seriesType, iteration.getYear(),
                                attribute.getRealValue());

                        // Set author and modification date
                        this.lastModifiedVersion = ProposalClientUtil
                                .getProposalVersionByProposalIdVersion(proposal.getProposalId(),
                                attribute.getVersion());
                    }
                }

                // Use default data if not entered
                if (!foundEnteredData) {
                    List<ImpactDefaultSeriesData> defaultSeriesDataList =
                            OntologyClientUtil.getImpactDefaultSeriesDataBySeries(
                                    defaultSeries.getSeriesId());
                    addSeriesWithType(seriesType, defaultSeriesDataList, true);
                }
            }
        }
    }

    public void addSeriesValueWithType(ImpactSeriesType seriesType, int year, double value) {
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

            final ImpactSeriesType seriesType = ImpactSeriesType.valueOf(defaultSeries.getName());
            seriesTypeToEditableMap.put(seriesType, true);
            JSONObject seriesValues = json.getJSONObject(defaultSeries.getName());
            for (ImpactIteration iteration : impactIterations) {
                if (Double.isNaN(seriesValues.getDouble(iteration.getYear() + ""))) {
                    throw new RuntimeException(
                            "Could not parse value for year " + iteration.getYear());
                }
                addSeriesValueWithType(seriesType, iteration.getYear(),
                        seriesValues.getDouble(iteration.getYear() + ""));
            }
        }
    }

    public Map<ImpactSeriesType, ProposalImpactSeriesValues> getSeriesTypeToSeriesMap() {
        return seriesTypeToSeriesMap;
    }

    public ProposalImpactSeriesValues getSeriesValuesForType(ImpactSeriesType seriesType) {
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
                    seriesTypeToSeriesMap.get(ImpactSeriesType.IMPACT_REDUCTION)
                            .getValueForYear(currentYear);
            double adoptionRate =
                    seriesTypeToSeriesMap.get(ImpactSeriesType.IMPACT_ADOPTION_RATE)
                            .getValueForYear(currentYear);

            // Round to 2 decimal digits
            double resultValue =
                    Math.round((bauValue * (reductionRate * 0.01 * adoptionRate * 0.01)) * 100.0)
                            / 100.0;
            resultValues.putSeriesValue(currentYear, resultValue);
        }
    }

    public boolean getIsEditableForSeriesType(ImpactSeriesType seriesType) {
        return seriesTypeToEditableMap.get(seriesType);
    }

    public void persistWithAuthor(Member author) {
        // Persist all editable attributes
        Integer version = null;
        for (Map.Entry<ImpactSeriesType, Boolean> entry : seriesTypeToEditableMap.entrySet()) {
            final ImpactSeriesType seriesType = entry.getKey();
            final Boolean isEditable = entry.getValue();
            if (isEditable) {
                ProposalImpactSeriesValues seriesValues =
                        this.seriesTypeToSeriesMap.get(seriesType);
                for (ImpactIteration iteration : impactIterations) {
                    double filteredValue = ProposalImpactValueFilterAlgorithm
                            .filterValueForImpactSeriesType(
                                    seriesValues.getValueForYear(iteration.getYear()), seriesType.name());
                    version = ProposalAttributeClientUtil
                            .setProposalAttribute(author.getUserId(), proposal.getProposalId(),
                                    seriesType.getAttributeName(iteration.getYear()),
                                    focusArea.getId_(), "", null,
                                    filteredValue, version).getVersion();
                }

            }
        }
    }

    public JSONObject toJSONObjectByFiltering(Set<String> filteredSeriesNames) {
        JSONObject jsonObject = toJSONObject();
        JSONObject newSeriesObject = new JSONObject();
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
        JSONObject returnObject = new JSONObject();
        JSONObject serieses = new JSONObject();

        returnObject.put("focusAreaId", getFocusArea().getId_());

        if (getSeriesAuthor() != null && getUpdatedDate() != null) {
            // Author info
            JSONObject authorObject = new JSONObject();
            returnObject.put("author", authorObject);
            authorObject.put("userId", getSeriesAuthor().getUserId());
            MemberRoleChoiceAlgorithm impactRoleChoiceAlgorithm =
                    MemberRoleChoiceAlgorithm.proposalImpactTabAlgorithm;
            final String authorDescription =
                    impactRoleChoiceAlgorithm.getUserRoleDescription(this.getSeriesAuthor()) + " "
                            + this.getSeriesAuthor().getFullName();
            authorObject.put("name", authorDescription);

            // update date
            DateFormat dateFormatter = new SimpleDateFormat("MMMMMM d, yyyy, KK:mm a zzzz");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
            returnObject.put("updateDate", dateFormatter.format(getUpdatedDate()));
        }

        returnObject.put("serieses", serieses);
        for (Map.Entry<ImpactSeriesType, ProposalImpactSeriesValues> entry : seriesTypeToSeriesMap
                .entrySet()) {
            ImpactSeriesType seriesType = entry.getKey();
            final ProposalImpactSeriesValues seriesValues = seriesTypeToSeriesMap.get(seriesType);

            ImpactDefaultSeries defaultSeries = OntologyClientUtil
                    .getImpactDefaultSeriesByFocusAreaAndSeriesName(focusArea.getId_(), seriesType.name());

            JSONObject series = new JSONObject();
            series.put("name", defaultSeries.getName());
            series.put("description", defaultSeries.getDescription());
            series.put("editable", defaultSeries.getEditable());
            series.put("values", seriesValues.toJSONArrayWithIteration(impactIterations));
            serieses.put(defaultSeries.getName(), series);
        }

        JSONArray iterations = new JSONArray();
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

    public Proposal getProposalWrapper() {
        return proposalWrapper;
    }

    public void setProposalWrapper(Proposal proposalWrapper) {
        this.proposalWrapper = proposalWrapper;
    }

    public OntologyTerm getWhatTerm() {
        return whatTerm;
    }

    public OntologyTerm getWhereTerm() {
        return whereTerm;
    }
}
