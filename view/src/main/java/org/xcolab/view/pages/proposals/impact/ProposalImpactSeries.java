package org.xcolab.view.pages.proposals.impact;

import org.json.JSONArray;
import org.json.JSONObject;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IImpactDefaultSeries;
import org.xcolab.client.contest.pojo.IImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.IImpactIteration;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalVersionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.enums.ImpactSeriesType;
import org.xcolab.client.contest.proposals.helpers.ProposalAttributeHelper;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

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

    private final List<IImpactIteration> impactIterations;
    private final OntologyTermWrapper whatTerm;
    private final OntologyTermWrapper whereTerm;
    private final FocusAreaWrapper focusArea;
    private final ProposalWrapper proposal;
    private final Map<ImpactSeriesType, ProposalImpactSeriesValues> seriesTypeToSeriesMap;
    private final Map<ImpactSeriesType, Boolean> seriesTypeToEditableMap;
    private final IImpactDefaultSeries bauSeries;
    private ProposalVersionWrapper lastModifiedVersion;
    //    private ImpactDefaultSeries ddppSeries;
    private ProposalImpactSeriesValues resultValues;

    public ProposalImpactSeries(ContestWrapper contest, ProposalWrapper proposal, FocusAreaWrapper focusArea) {
        this(contest, proposal, focusArea, true);
    }

    private ProposalImpactSeries(ContestWrapper contest, ProposalWrapper proposal, FocusAreaWrapper focusArea,
            boolean loadData) {
        this.seriesTypeToSeriesMap = new HashMap<>();
        this.seriesTypeToEditableMap = new HashMap<>();
        this.focusArea = focusArea;
        this.proposal = proposal;
        this.whatTerm = ProposalImpactUtil.getWhatTerm(focusArea);
        this.whereTerm = ProposalImpactUtil.getWhereTerm(focusArea);
        this.impactIterations = StaticContestContext.getImpactClient().getContestImpactIterations(contest);
        // Retrieve static serieses
        bauSeries = StaticContestContext.getOntologyClient()
                .getImpactDefaultSeriesByFocusAreaName(focusArea.getId(), SERIES_TYPE_BAU_KEY);
        addSeriesWithType(bauSeries, false, false);

        //        ddppSeries = ImpactDefaultSeriesLocalServiceUtil
        // .getImpactDefaultSeriesWithFocusAreaAndName(focusArea, SERIES_TYPE_DDPP_KEY);
        //        addSeriesWithType(ddppSeries, false);

        if (loadData) {
            loadEditableData();
        }
    }

    private void addSeriesWithType(IImpactDefaultSeries defaultSeries, boolean editable,
            boolean invertSeriesSign) {
        List<IImpactDefaultSeriesData> seriesDataList = StaticContestContext.getOntologyClient()
                .getImpactDefaultSeriesDataBySeries(defaultSeries.getSeriesId());
        if (invertSeriesSign) {
            for (IImpactDefaultSeriesData seriesData : seriesDataList) {
                if (seriesData.getValue() != null) {
                    seriesData.setValue(seriesData.getValue() * (-1.0));
                }
            }
        }
        final ImpactSeriesType seriesType = ImpactSeriesType.valueOf(defaultSeries.getName());
        addSeriesWithType(seriesType, seriesDataList, editable);
    }

    private void addSeriesWithType(ImpactSeriesType seriesType, List<IImpactDefaultSeriesData> seriesDataList,
            boolean editable) {
        ProposalImpactSeriesValues seriesValues = new ProposalImpactSeriesValues();
        seriesTypeToSeriesMap.put(seriesType, seriesValues);
        seriesTypeToEditableMap.put(seriesType, editable);

        for (IImpactDefaultSeriesData defaultDataPoint : seriesDataList) {
            seriesValues.putSeriesValue(defaultDataPoint.getYear(), defaultDataPoint.getValue());
        }
    }

    private void loadEditableData() {
        // Get default serieses
        List<IImpactDefaultSeries> impactDefaultSerieses = StaticContestContext.getOntologyClient()
                .getAllmpactDefaultSeriesByFocusArea(focusArea.getId());

        final ProposalAttributeHelper proposalAttributeHelper =
                proposal.getProposalAttributeHelper();

        for (IImpactDefaultSeries defaultSeries : impactDefaultSerieses) {

            // Look for already entered data
            if (defaultSeries.isEditable()) {

                boolean foundEnteredData = false;
                final ImpactSeriesType seriesType =
                        ImpactSeriesType.valueOf(defaultSeries.getName());
                for (IImpactIteration iteration : impactIterations) {
                    String attributeName = defaultSeries.getName() + "_" + iteration.getYear();
                    final ProposalAttribute attribute = proposalAttributeHelper
                            .getAttributeOrNull(attributeName, focusArea.getId());
                    if (attribute != null) {
                        foundEnteredData = true;
                        addSeriesValueWithType(seriesType, iteration.getYear(),
                                attribute.getRealValue());

                        // Set author and modification date
                        this.lastModifiedVersion = StaticProposalContext.getProposalClient()
                                .getProposalVersionByProposalIdVersion(proposal.getId(),
                                attribute.getVersion());
                    }
                }

                // Use default data if not entered
                if (!foundEnteredData) {
                    List<IImpactDefaultSeriesData> defaultSeriesDataList =
                            StaticContestContext.getOntologyClient()
                                    .getImpactDefaultSeriesDataBySeries(defaultSeries.getSeriesId());
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

    public ProposalImpactSeries(ContestWrapper contest, ProposalWrapper proposal, FocusAreaWrapper focusArea,
            JSONObject json) {
        this(contest, proposal, focusArea, false);

        for (IImpactDefaultSeries defaultSeries : StaticContestContext.getOntologyClient()
                .getAllmpactDefaultSeriesByFocusArea(focusArea.getId())) {
            if (!defaultSeries.isEditable()) {
                continue;
            }

            final ImpactSeriesType seriesType = ImpactSeriesType.valueOf(defaultSeries.getName());
            seriesTypeToEditableMap.put(seriesType, true);
            JSONObject seriesValues = json.getJSONObject(defaultSeries.getName());
            for (IImpactIteration iteration : impactIterations) {
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
        for (IImpactIteration impactIteration : impactIterations) {
            int currentYear = impactIteration.getYear();

            double bauValue = StaticContestContext.getOntologyClient()
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

    public void persistWithAuthor(UserWrapper author) {
        // Persist all editable attributes
        Integer version = null;
        for (Map.Entry<ImpactSeriesType, Boolean> entry : seriesTypeToEditableMap.entrySet()) {
            final ImpactSeriesType seriesType = entry.getKey();
            final Boolean isEditable = entry.getValue();
            if (isEditable) {
                ProposalImpactSeriesValues seriesValues =
                        this.seriesTypeToSeriesMap.get(seriesType);
                for (IImpactIteration iteration : impactIterations) {
                    double filteredValue = ProposalImpactValueFilterAlgorithm
                            .filterValueForImpactSeriesType(
                                    seriesValues.getValueForYear(iteration.getYear()), seriesType.name());
                    version = StaticProposalContext.getProposalAttributeClient()
                            .setProposalAttribute(author.getId(), proposal.getId(),
                                    seriesType.getAttributeName(iteration.getYear()),
                                    focusArea.getId(), "", null,
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

        returnObject.put("focusAreaId", getFocusArea().getId());

        if (getSeriesAuthor() != null && getUpdatedDate() != null) {
            // Author info
            JSONObject authorObject = new JSONObject();
            returnObject.put("author", authorObject);
            authorObject.put("userId", getSeriesAuthor().getId());
            authorObject.put("name",  this.getSeriesAuthor().getFullName());

            // update date
            DateFormat dateFormatter = new SimpleDateFormat("MMMMMM d, yyyy, KK:mm a zzzz");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
            returnObject.put("updatedAt", dateFormatter.format(getUpdatedDate()));
        }

        returnObject.put("serieses", serieses);
        for (Map.Entry<ImpactSeriesType, ProposalImpactSeriesValues> entry : seriesTypeToSeriesMap
                .entrySet()) {
            ImpactSeriesType seriesType = entry.getKey();
            final ProposalImpactSeriesValues seriesValues = seriesTypeToSeriesMap.get(seriesType);

            IImpactDefaultSeries defaultSeries = StaticContestContext.getOntologyClient()
                    .getImpactDefaultSeriesByFocusAreaName(focusArea.getId(), seriesType.name());

            JSONObject series = new JSONObject();
            series.put("name", defaultSeries.getName());
            series.put("description", defaultSeries.getDescription());
            series.put("editable", defaultSeries.isEditable());
            series.put("values", seriesValues.toJSONArrayWithIteration(impactIterations));
            serieses.put(defaultSeries.getName(), series);
        }

        JSONArray iterations = new JSONArray();
        for (IImpactIteration impactIteration : impactIterations) {
            iterations.put(impactIteration.getYear());
        }
        returnObject.put("iterations", iterations);
        returnObject.put("success", true);

        return returnObject;
    }

    public FocusAreaWrapper getFocusArea() {
        return focusArea;
    }

    private UserWrapper getSeriesAuthor() {
        if (lastModifiedVersion != null) {
            return StaticUserContext.getUserClient().
                    getMemberUnchecked(lastModifiedVersion.getAuthorUserId());
        }
        return null;
    }

    public Date getUpdatedDate() {
        return lastModifiedVersion.getCreatedAt();
    }

    public ProposalWrapper getProposal() {
        return proposal;
    }

    public OntologyTermWrapper getWhatTerm() {
        return whatTerm;
    }

    public OntologyTermWrapper getWhereTerm() {
        return whereTerm;
    }
}
