package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.ProposalImpactAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import org.xcolab.portlets.proposals.exceptions.ProposalImpactDataParserException;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactSeries;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactSeriesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to parse inline proposal impact data (tab-separated Excel string) to a
 * ProposalImpactSeriesList object.
 *
 * Used for the IAF fellow impact series edit feature
 *
 * Created by kmang on 04/06/15.
 */
public class ProposalImpactDataParser {
    private static final Log _log = LogFactoryUtil.getLog(ProposalImpactDataParser.class);

    private static final String EXCEL_SERIES_TYPE_BAU_KEY = "Business as usual";
    private static final String EXCEL_SERIES_TYPE_REDUCTION_KEY = "emission reduction";
    private static final String EXCEL_SERIES_TYPE_ADOPTION_RATE_KEY = "Adoption rate";
    private static final String EXCEL_SERIES_TYPE_RESULT_KEY = "Estimated CO2 reduction";
    private static final String[] excelSeriesTypeKeys = {EXCEL_SERIES_TYPE_BAU_KEY, EXCEL_SERIES_TYPE_REDUCTION_KEY, EXCEL_SERIES_TYPE_ADOPTION_RATE_KEY, EXCEL_SERIES_TYPE_REDUCTION_KEY};

    private static final Map<String, String> excelTermToOntologyTermNameMap;
    static {
        excelTermToOntologyTermNameMap = new HashMap<>();
        excelTermToOntologyTermNameMap.put("Transport", "Transportation");
        excelTermToOntologyTermNameMap.put("EnergySupply", "Energy supply");
        excelTermToOntologyTermNameMap.put("Other", "Land use & other sectors");
        excelTermToOntologyTermNameMap.put("All other developed countries", "Other developed countries");
        excelTermToOntologyTermNameMap.put("All other developing countries", "Other developing countries");
    }

    private static final Map<String, String> excelSeriesTypeToSeriesTypeMap;
    static {
        excelSeriesTypeToSeriesTypeMap = new HashMap<>(excelSeriesTypeKeys.length);
        excelSeriesTypeToSeriesTypeMap.put(EXCEL_SERIES_TYPE_BAU_KEY, ProposalImpactSeries.SERIES_TYPE_BAU_KEY); // we don't need this one
        excelSeriesTypeToSeriesTypeMap.put(EXCEL_SERIES_TYPE_REDUCTION_KEY, ProposalImpactAttributeKeys.IMPACT_REDUCTION);
        excelSeriesTypeToSeriesTypeMap.put(EXCEL_SERIES_TYPE_ADOPTION_RATE_KEY, ProposalImpactAttributeKeys.IMPACT_ADOPTION_RATE);
        excelSeriesTypeToSeriesTypeMap.put(EXCEL_SERIES_TYPE_RESULT_KEY, ""); // we don't need this one
    }

    private String tabSeparatedString;
    private Proposal proposal;
    private Contest contest;


    /**
     * Creates a new ProposalImpactDataParser object with the input String (tab-separated string copy-pasted from Excel)
     * and the Contest of the proposal in question.
     *
     * @param tabSeparatedString
     * @param contest
     */
    public ProposalImpactDataParser(String tabSeparatedString, Proposal proposal, Contest contest) {
        this.tabSeparatedString = tabSeparatedString;
        this.proposal = proposal;
        this.contest = contest;

    }

    public ProposalImpactSeriesList parse() throws PortalException, SystemException, ProposalImpactDataParserException {
        String[] inputLines = getLines(tabSeparatedString);
        List<String> parsedSeriesTypeKeys = getParsedSeriesTypeKeys(inputLines);
        List<Long> iterationYears = getParsedIterationYears(inputLines);

        return parseExcelData(Arrays.copyOfRange(inputLines, 2, inputLines.length), parsedSeriesTypeKeys, iterationYears);
    }

    private List<String> getParsedSeriesTypeKeys(String[] inputLines) throws ProposalImpactDataParserException {
        if (ArrayUtil.isEmpty(inputLines)) {
            return null;
        }

        String headLine = inputLines[0];
        List<String> seriesTypes = new ArrayList<>();
        for (String headLineColumn : getTabbedStrings(headLine)) {
            boolean foundMatch = false;
            // Ignore empty columns (tabs)
            if (Validator.isNotNull(headLineColumn)) {
                // Try to match excelSeriesType from input
                for (String excelSeriesKey : excelSeriesTypeToSeriesTypeMap.keySet()) {
                    if (headLineColumn.contains(excelSeriesKey)) {
                        String seriesType = excelSeriesTypeToSeriesTypeMap.get(excelSeriesKey);
                        seriesTypes.add(seriesType);

                        foundMatch = true;
                        break;
                    }
                }

                if (!foundMatch) {
                    throw new ProposalImpactDataParserException("Could not match data column '" + headLineColumn + "'");
                }
            }
        }

        return seriesTypes;
    }

    private List<Long> getParsedIterationYears(String[] inputLines) {
        if (ArrayUtil.isEmpty(inputLines)) {
            return null;
        }

        String iterationYearLine = inputLines[1];
        List<Long> iterationYears = new ArrayList<>();
        for (String iterationYearColumn : getTabbedStrings(iterationYearLine)) {
            // Ignore empty columns (tabs)
            if (Validator.isNotNull(iterationYearColumn)) {
                try {
                    long iterationYear = Long.parseLong(iterationYearColumn);

                    // Ignore duplicates
                    if (iterationYears.contains(iterationYear)) {
                        break;
                    }

                    iterationYears.add(iterationYear);

                } catch (NumberFormatException e) {
                    _log.error("Could not parse string to iteration year '" + iterationYearColumn + "' of line 1 '" + iterationYearLine + "'", e);
                    throw e;
                }
            }
        }

        return iterationYears;
    }

    private ProposalImpactSeriesList parseExcelData(String[] inputLines,
                                                    List<String> parsedSeriesTypeKeys,
                                                    List<Long> iterationYears) throws SystemException, PortalException, ProposalImpactDataParserException {

        ProposalImpactSeriesList seriesList = new ProposalImpactSeriesList(this.contest, this.proposal);
        ProposalImpactUtil proposalImpactUtil = new ProposalImpactUtil(contest);

        OntologyTerm regionTerm = null;
        int inputLineNumber = 2;
        for (String inputLine : inputLines) {
            String[] dataStrings = getTabbedStrings(inputLine);

            // We need at least the region+sector and one full iteration of years
            if (ArrayUtil.isNotEmpty(dataStrings) && dataStrings.length >= 2 + iterationYears.size()) {
                OntologyTerm sectorTerm;
                try {
                    // Some of the first columns are empty - use old region term instead
                    if (Validator.isNotNull(dataStrings[0])) {
                        regionTerm = getOntologyTermByName(dataStrings[0]);
                    }

                    sectorTerm = getOntologyTermByName(dataStrings[1]);
                } catch (Exception e) {
                    _log.error(e.getMessage() + " on line " + inputLineNumber);
                    throw new ProposalImpactDataParserException(e);
                }

                FocusArea focusArea = proposalImpactUtil.getFocusAreaAssociatedWithTerms(sectorTerm, regionTerm);
                ProposalImpactSeries newProposalImpactSeries = new ProposalImpactSeries(contest, proposal, focusArea);

                // Write the new impact series values
                for (int idx = 2; idx < dataStrings.length; idx++) {
                    String dataString = dataStrings[idx];
                    String currentSeriesType = parsedSeriesTypeKeys.get(((idx - 2) / iterationYears.size()));

                    // If the seriesType is not set we just ignore all upcoming values
                    if (Validator.isNull(currentSeriesType)) {
                        idx += iterationYears.size() - 1;
                        continue;
                    }

                    // Empty lines are not tolerated - abort
                    if (Validator.isNull(dataString)) {
                        throw new ProposalImpactDataParserException("Empty data column detected in line '" + inputLine);
                    }

                    long year = iterationYears.get((idx - 2) % iterationYears.size());

                    try {
                        setProposalImpactSeriesValue(newProposalImpactSeries, currentSeriesType, year, dataString);
                    } catch (NumberFormatException e) {
                        _log.error("Could not parse string '" + dataString + "' to double value");
                        throw new ProposalImpactDataParserException("\"Could not parse string '\" + dataString + \"' to double value\"", e);
                    }
                }

                seriesList.addProposalImpactSeries(newProposalImpactSeries);

            } else {
                throw new ProposalImpactDataParserException("Could not parse data line '\" + StringUtils.join(dataStrings, \",\") + \"'; line too short");
            }

        }

        return seriesList;
    }

    private void setProposalImpactSeriesValue(ProposalImpactSeries proposalImpactSeries, String seriesType, long year, String valueString) {
        double value = parseDataValue(seriesType, valueString);
        proposalImpactSeries.addSeriesValueWithType(seriesType, (int)year, value);
    }

    private double parseDataValue(String seriesType, String valueString) {
        // percentage value
        if (valueString.contains("%")) {
            return Double.parseDouble(valueString.substring(0, valueString.length() - 1));
        } else {
            double value = Double.parseDouble(valueString.substring(0, valueString.length() - 1));

            // Interpret impact reduction and adoption values as ratios
            if ((seriesType.equals(ProposalImpactAttributeKeys.IMPACT_REDUCTION) || seriesType.equals(ProposalImpactAttributeKeys.IMPACT_ADOPTION_RATE))) {
                return value * 100.;    // impact reduction and adoption rate values are stored as percentage values (0-100%)
            } else {
                return value;
            }
        }
    }

    private OntologyTerm getOntologyTermByName(String name) throws SystemException, ProposalImpactDataParserException {
        // Use mapped name value if it exists
        if (Validator.isNotNull(excelTermToOntologyTermNameMap.get(name))) {
            name = excelTermToOntologyTermNameMap.get(name);
        }

        List<OntologyTerm> ontologyTerms = OntologyTermLocalServiceUtil.findByOntologyTermName(name);
        if (Validator.isNull(ontologyTerms) || ontologyTerms.size() == 0) {
            throw new ProposalImpactDataParserException("Could not match ontology term with name '" + name + "'");
        }

        return ontologyTerms.get(0);
    }

    private String[] getTabbedStrings(String inputLine) {
        return inputLine.split("\\t", -1);
    }

    private String[] getLines(String inputLines) {
        return inputLines.split("\\n", -1);
    }
}
