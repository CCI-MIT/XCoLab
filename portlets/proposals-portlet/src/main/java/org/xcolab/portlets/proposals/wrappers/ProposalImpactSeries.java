package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.NoSuchImpactDefaultSeriesDataException;
import com.ext.portlet.NoSuchImpactDefaultSeriesException;
import com.ext.portlet.NoSuchProposalVersionException;
import com.ext.portlet.ProposalImpactAttributeKeys;


import com.ext.portlet.model.ImpactDefaultSeries;
import com.ext.portlet.model.ImpactDefaultSeriesData;

import com.ext.portlet.model.OntologyTerm;

import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalVersion;

import com.ext.portlet.service.ImpactDefaultSeriesDataLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.FocusArea;
import org.xcolab.client.contest.pojo.ImpactIteration;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.enums.MemberRoleChoiceAlgorithm;
import org.xcolab.portlets.proposals.utils.ProposalImpactUtil;
import org.xcolab.portlets.proposals.utils.ProposalImpactValueFilterAlgorithm;

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
    private ProposalWrapper proposalWrapper;
    private ProposalVersion lastModifiedVersion;
    private final Map<String, ProposalImpactSeriesValues> seriesTypeToSeriesMap;
    private final Map<String, Boolean> seriesTypeToEditableMap;

    private final ImpactDefaultSeries bauSeries;
//    private ImpactDefaultSeries ddppSeries;

    private ProposalImpactSeriesValues resultValues;

    protected ProposalImpactSeries(Contest contest, Proposal proposal, FocusArea focusArea, boolean loadData) throws SystemException, PortalException {
        this.seriesTypeToSeriesMap = new HashMap<>();
        this.seriesTypeToEditableMap = new HashMap<>();
        this.focusArea = focusArea;
        this.proposal = proposal;
        this.proposalWrapper = new ProposalWrapper(proposal);
        this.whatTerm = ProposalImpactUtil.getWhatTerm(focusArea);
        this.whereTerm = ProposalImpactUtil.getWhereTerm(focusArea);
        this.impactIterations = ContestClient.getContestImpactIterations(contest);
        // Retrieve static serieses
        bauSeries = ImpactDefaultSeriesLocalServiceUtil.getImpactDefaultSeriesWithFocusAreaAndName(focusArea, SERIES_TYPE_BAU_KEY);
        Boolean invertSeriesSign = false;
        addSeriesWithType(bauSeries, false, invertSeriesSign);

//        ddppSeries = ImpactDefaultSeriesLocalServiceUtil.getImpactDefaultSeriesWithFocusAreaAndName(focusArea, SERIES_TYPE_DDPP_KEY);
//        addSeriesWithType(ddppSeries, false);

        if (loadData) {
            loadEditableData();
        }
    }

    public ProposalImpactSeries(Contest contest, Proposal proposal, FocusArea focusArea) throws PortalException, SystemException {
        this(contest, proposal, focusArea, true);
    }

    public ProposalImpactSeries(Contest contest, Proposal proposal, FocusArea focusArea, JSONObject json) throws PortalException, SystemException {
        this(contest, proposal, focusArea, false);

        for (ImpactDefaultSeries defaultSeries : ImpactDefaultSeriesLocalServiceUtil.getAllImpactDefaultSeriesWithFocusArea(focusArea)) {
            if (!defaultSeries.isEditable()) {
                continue;
            }

            seriesTypeToEditableMap.put(defaultSeries.getName(), true);
            JSONObject seriesValues = json.getJSONObject(defaultSeries.getName());
            for (ImpactIteration iteration : impactIterations) {
                if (Double.isNaN(seriesValues.getDouble(iteration.getYear()+""))) {
                    throw new SystemException("Could not parse value for year " + iteration.getYear());
                }
                addSeriesValueWithType(defaultSeries.getName(), iteration.getYear(), seriesValues.getDouble(iteration.getYear()+""));
            }
        }
    }

    public void addSeriesValueWithType(String seriesType, int year, double value) {
        ProposalImpactSeriesValues seriesValues = seriesTypeToSeriesMap.get(seriesType);
        if (Validator.isNull(seriesValues)) {
            seriesValues = new ProposalImpactSeriesValues();
            seriesTypeToSeriesMap.put(seriesType, seriesValues);
            seriesTypeToEditableMap.put(seriesType, true);
        }

        seriesValues.putSeriesValue(year, value);
    }

    public void addSeriesWithType(String seriesType, List<ImpactDefaultSeriesData> seriesDataList, boolean editable) {
        ProposalImpactSeriesValues seriesValues = new ProposalImpactSeriesValues();
        seriesTypeToSeriesMap.put(seriesType, seriesValues);
        seriesTypeToEditableMap.put(seriesType, editable);

        for (ImpactDefaultSeriesData defaultDataPoint : seriesDataList) {
            seriesValues.putSeriesValue(defaultDataPoint.getYear(), defaultDataPoint.getValue());
        }
    }

    public void addSeriesWithType(ImpactDefaultSeries defaultSeries, boolean editable, boolean invertSeriesSign) throws SystemException {
        List<ImpactDefaultSeriesData> seriesDataList = ImpactDefaultSeriesDataLocalServiceUtil.
                getDefaultSeriesDataBySeriesId(defaultSeries.getSeriesId());
        if(invertSeriesSign){
            for (ImpactDefaultSeriesData seriesData : seriesDataList) {
                if(Validator.isNotNull(seriesData.getValue())) {
                    seriesData.setValue(seriesData.getValue() * (-1.0));
                }
            }
        }
        addSeriesWithType(defaultSeries.getName(), seriesDataList, editable);
    }

    public Map<String, ProposalImpactSeriesValues> getSeriesTypeToSeriesMap() {
        return seriesTypeToSeriesMap;
    }

    public ProposalImpactSeriesValues getSeriesValuesForType(String seriesType) throws PortalException, SystemException {
        return seriesTypeToSeriesMap.get(seriesType);
    }

    public ProposalImpactSeriesValues getResultSeriesValues() throws SystemException, NoSuchImpactDefaultSeriesDataException {
        if (Validator.isNull(resultValues)) {
            calculateResultSeriesValues();
        }

        return resultValues;
    }

    public boolean getIsEditableForSeriesType(String seriesType) {
        return seriesTypeToEditableMap.get(seriesType);
    }

    public void persistWithAuthor(User author) throws SystemException, PortalException {
        // Persist all editable attributes
        for (Map.Entry<String, Boolean> entry: seriesTypeToEditableMap.entrySet()) {
            final String seriesType = entry.getKey();
            final Boolean isEditable = entry.getValue();
            if (isEditable) {
                ProposalImpactSeriesValues seriesValues = this.seriesTypeToSeriesMap.get(seriesType);
                for (ImpactIteration iteration : impactIterations) {
                    double filteredValue = ProposalImpactValueFilterAlgorithm.filterValueForImpactSeriesType(seriesValues.getValueForYear(iteration.getYear()), seriesType);
                    ProposalAttributeLocalServiceUtil.setAttribute(author.getUserId(), proposal.getProposalId(), seriesType,
                            focusArea.getId(), "", iteration.getYear(), filteredValue);
                }

            }
        }
    }

    public JSONObject toJSONObject() throws SystemException, NoSuchImpactDefaultSeriesException {
        JSONObject returnObject = JSONFactoryUtil.createJSONObject();
        JSONObject serieses = JSONFactoryUtil.createJSONObject();

        returnObject.put("focusAreaId", getFocusArea().getId_());

        if (Validator.isNotNull(getSeriesAuthor()) && Validator.isNotNull(getUpdatedDate())) {
            // Author info
            JSONObject authorObject = JSONFactoryUtil.createJSONObject();
            returnObject.put("author", authorObject);
            authorObject.put("userId", getSeriesAuthor().getUserId());
            MemberRoleChoiceAlgorithm impactRoleChoiceAlgorithm = MemberRoleChoiceAlgorithm.proposalImpactTabAlgorithm;
            final String authorDescription = impactRoleChoiceAlgorithm.getUserRoleDescription(this.getSeriesAuthor()) + " "
                    + this.getSeriesAuthor().getFullName();
            authorObject.put("name", authorDescription);

            // update date
            DateFormat dateFormatter = new SimpleDateFormat("MMMMMM d, YYYY, KK:mm a zzzz");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
            returnObject.put("updateDate", dateFormatter.format(getUpdatedDate()));
        }

        returnObject.put("serieses", serieses);
        for (Map.Entry<String, ProposalImpactSeriesValues> entry : seriesTypeToSeriesMap.entrySet()) {
            String seriesType = entry.getKey();
            final ProposalImpactSeriesValues seriesValues = seriesTypeToSeriesMap.get(seriesType);

            ImpactDefaultSeries defaultSeries = ImpactDefaultSeriesLocalServiceUtil.getImpactDefaultSeriesWithFocusAreaAndName(focusArea, seriesType);

            JSONObject series = JSONFactoryUtil.createJSONObject();
            series.put("name", defaultSeries.getName());
            series.put("description", defaultSeries.getDescription());
            series.put("editable", defaultSeries.isEditable());
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

    public JSONObject toJSONObjectByFiltering(Set<String> filteredSeriesNames) throws SystemException, NoSuchImpactDefaultSeriesException {
        JSONObject jsonObject = toJSONObject();
        JSONObject newSeriesObject = JSONFactoryUtil.createJSONObject();
        Iterator<String> seriesNameIterator = jsonObject.getJSONObject("serieses").keys();
        while (seriesNameIterator.hasNext()) {
            String seriesName = seriesNameIterator.next();
            // Only pass series objects whoms name is not included in the filter set
            if (!filteredSeriesNames.contains(seriesName)) {
                newSeriesObject.put(seriesName, jsonObject.getJSONObject("serieses").getJSONObject(seriesName));
            }
        }

        jsonObject.put("serieses", newSeriesObject);
        return jsonObject;
    }

    private void loadEditableData() throws SystemException, NoSuchProposalVersionException {
        // Get default serieses
        List<ImpactDefaultSeries> impactDefaultSerieses =
                ImpactDefaultSeriesLocalServiceUtil.getAllImpactDefaultSeriesWithFocusArea(focusArea);

        // TODO create query to filter by additionalId?
        List<ProposalAttribute> impactProposalAttributes =
                ProposalAttributeLocalServiceUtil.getImpactProposalAttributes(proposal);

        for (ImpactDefaultSeries defaultSeries : impactDefaultSerieses) {

            // Look for already entered data
            if (defaultSeries.isEditable()) {

                // TODO write a separate finder for the proposal attribute that is being searched
                boolean foundEnteredData = false;
                for (ProposalAttribute attribute : impactProposalAttributes) {
                    if (attribute.getName().equals(defaultSeries.getName()) && attribute.getAdditionalId() == focusArea.getId()) {
                        foundEnteredData = true;
                        addSeriesValueWithType(attribute.getName(), (int) attribute.getNumericValue(), attribute.getRealValue());

                        // Set author and modification date
                        this.lastModifiedVersion = ProposalVersionLocalServiceUtil.getByProposalIdVersion(proposal.getProposalId(), attribute.getVersionWhenCreated());
                    }
                }

                // Use default data if not entered
                if (!foundEnteredData) {
                    List<ImpactDefaultSeriesData> defaultSeriesDataList =
                            ImpactDefaultSeriesDataLocalServiceUtil.getDefaultSeriesDataBySeriesId(defaultSeries.getSeriesId());
                    addSeriesWithType(defaultSeries.getName(), defaultSeriesDataList, true);
                }
            }
        }
    }

    /**
     * Calculate the result values for each time point in the Iteration
     */
    private void calculateResultSeriesValues() throws SystemException, NoSuchImpactDefaultSeriesDataException {
        resultValues = new ProposalImpactSeriesValues();
        for (ImpactIteration impactIteration : impactIterations) {
            int currentYear = impactIteration.getYear();

            double bauValue =
                    ImpactDefaultSeriesDataLocalServiceUtil.getDefaultSeriesDataBySeriesIdAndYear(bauSeries.getSeriesId(),
                            currentYear).getValue();

            double reductionRate = seriesTypeToSeriesMap.get(ProposalImpactAttributeKeys.IMPACT_REDUCTION).getValueForYear(currentYear);
            double adoptionRate = seriesTypeToSeriesMap.get(ProposalImpactAttributeKeys.IMPACT_ADOPTION_RATE).getValueForYear(currentYear);

            // Round to 2 decimal digits
            double resultValue = Math.round((bauValue * (reductionRate * 0.01 * adoptionRate * 0.01)) * 100.0) / 100.0;
            resultValues.putSeriesValue(currentYear, resultValue);
        }
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

    public FocusArea getFocusArea() {
        return focusArea;
    }

    public User getSeriesAuthor() {
        try {
            if (lastModifiedVersion != null) {
                return UserLocalServiceUtil.getUser(lastModifiedVersion.getAuthorId());
            }
        } catch (SystemException | PortalException ignored) { }
        return null;
    }

    public Date getUpdatedDate() {
        return lastModifiedVersion.getCreateDate();
    }
}