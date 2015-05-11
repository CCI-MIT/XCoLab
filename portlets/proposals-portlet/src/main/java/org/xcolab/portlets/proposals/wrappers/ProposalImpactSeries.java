package org.xcolab.portlets.proposals.wrappers;

/**
 * Created by kmang on 13/03/15.
 */

import com.ext.portlet.NoSuchImpactDefaultSeriesException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalImpactAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.ImpactDefaultSeries;
import com.ext.portlet.model.ImpactDefaultSeriesData;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesDataLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil;
import com.ext.portlet.service.ImpactIterationLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.ext.portlet.service.persistence.ImpactIterationUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.enums.MemberRoleChoiceAlgorithm;
import org.xcolab.portlets.proposals.utils.ProposalImpactUtil;
import org.xcolab.portlets.proposals.utils.ProposalImpactValueFilterAlgorithm;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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

    private List<ImpactIteration> impactIterations;
    private OntologyTerm whatTerm;
    private OntologyTerm whereTerm;
    private FocusArea focusArea;
    private Proposal proposal;
    private ProposalVersion lastModifiedVersion;
    private Map<String, ProposalImpactSeriesValues> seriesTypeToSeriesMap;
    private Map<String, Boolean> seriesTypeToEditableMap;

    private ImpactDefaultSeries bauSeries;
//    private ImpactDefaultSeries ddppSeries;

    private ProposalImpactSeriesValues resultValues;

    protected ProposalImpactSeries(Contest contest, Proposal proposal, FocusArea focusArea, boolean loadData) throws SystemException, PortalException {
        this.seriesTypeToSeriesMap = new HashMap<>();
        this.seriesTypeToEditableMap = new HashMap<>();
        this.focusArea = focusArea;
        this.proposal = proposal;
        this.whatTerm = ProposalImpactUtil.getWhatTerm(focusArea);
        this.whereTerm = ProposalImpactUtil.getWhereTerm(focusArea);

        this.impactIterations = ContestLocalServiceUtil.getContestImpactIterations(contest);
        // Retrieve static serieses
        bauSeries = ImpactDefaultSeriesLocalServiceUtil.getImpactDefaultSeriesWithFocusAreaAndName(focusArea, SERIES_TYPE_BAU_KEY);
        addSeriesWithType(bauSeries, false);

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

    public void addSeriesWithType(ImpactDefaultSeries defaultSeries, boolean editable) throws SystemException {
        List<ImpactDefaultSeriesData> seriesDataList = ImpactDefaultSeriesDataLocalServiceUtil.
                getDefaultSeriesDataBySeriesId(defaultSeries.getSeriesId());
        addSeriesWithType(defaultSeries.getName(), seriesDataList, editable);
    }

    public Map<String, ProposalImpactSeriesValues> getSeriesTypeToSeriesMap() {
        return seriesTypeToSeriesMap;
    }

    public ProposalImpactSeriesValues getSeriesValuesForType(String seriesType) throws PortalException, SystemException {
        return seriesTypeToSeriesMap.get(seriesType);
    }

    public ProposalImpactSeriesValues getResultSeriesValues() throws PortalException, SystemException {
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
        for (String seriesType : seriesTypeToEditableMap.keySet()) {
            if (seriesTypeToEditableMap.get(seriesType)) {
                ProposalImpactSeriesValues seriesValues = this.seriesTypeToSeriesMap.get(seriesType);
                for (ImpactIteration iteration : impactIterations) {
                    double filteredValue = ProposalImpactValueFilterAlgorithm.filterValueForImpactSeriesType(seriesValues.getValueForYear(iteration.getYear()), seriesType);
                    ProposalLocalServiceUtil.setAttribute(author.getUserId(), proposal.getProposalId(), seriesType,
                            focusArea.getId(), "", iteration.getYear(), filteredValue);
                }

            }
        }
    }

    public JSONObject toJSONObject() throws SystemException, PortalException {
        JSONObject returnObject = JSONFactoryUtil.createJSONObject();
        JSONObject serieses = JSONFactoryUtil.createJSONObject();

        returnObject.put("focusAreaId", getFocusArea().getId());

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
        for (String seriesType : seriesTypeToSeriesMap.keySet()) {
            ProposalImpactSeriesValues seriesValues = seriesTypeToSeriesMap.get(seriesType);
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

    public JSONObject toJSONObjectByFiltering(Set<String> filteredSeriesNames) throws PortalException, SystemException {
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

    private void loadEditableData() throws SystemException, PortalException {
        // Get default serieses
        List<ImpactDefaultSeries> impactDefaultSerieses =
                ImpactDefaultSeriesLocalServiceUtil.getAllImpactDefaultSeriesWithFocusArea(focusArea);

        // TODO create query to filter by additionalId?
        List<ProposalAttribute> impactProposalAttributes =
                ProposalLocalServiceUtil.getImpactProposalAttributes(proposal);

        for (ImpactDefaultSeries defaultSeries : impactDefaultSerieses) {
            boolean foundEnteredData = false;

            // Look for already entered data
            if (defaultSeries.isEditable()) {

                // TODO write a separate finder for the proposal attribute that is being searched
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
    private void calculateResultSeriesValues() throws SystemException, PortalException {
        resultValues = new ProposalImpactSeriesValues();
        for (ImpactIteration impactIteration : impactIterations) {
            int currentYear = impactIteration.getYear();

            double bauValue =
                    ImpactDefaultSeriesDataLocalServiceUtil.getDefaultSeriesDataBySeriesIdAndYear(bauSeries.getSeriesId(),
                            (int) currentYear).getValue();

            double reductionRate = seriesTypeToSeriesMap.get(ProposalImpactAttributeKeys.IMPACT_REDUCTION).getValueForYear(currentYear);
            double adoptionRate = seriesTypeToSeriesMap.get(ProposalImpactAttributeKeys.IMPACT_ADOPTION_RATE).getValueForYear(currentYear);

            // Round to 2 decimal digits
            double resultValue = Math.round((bauValue * (reductionRate * 0.01 * adoptionRate * 0.01)) * 100.0) / 100.0;
            resultValues.putSeriesValue(currentYear, resultValue);
        }
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
            return UserLocalServiceUtil.getUser(lastModifiedVersion.getAuthorId());
        } catch (Exception e) {
            return null;
        }
    }

    public Date getUpdatedDate() {
        return lastModifiedVersion.getCreateDate();
    }
}