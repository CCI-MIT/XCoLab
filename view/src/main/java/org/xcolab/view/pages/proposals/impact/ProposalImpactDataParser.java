package org.xcolab.view.pages.proposals.impact;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.enums.ImpactSeriesType;
import org.xcolab.view.pages.proposals.exceptions.ProposalImpactDataParserException;

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
 */
public class ProposalImpactDataParser {

    private static final Logger _log = LoggerFactory.getLogger(ProposalImpactDataParser.class);

    private static final String EXCEL_SERIES_TYPE_BAU_KEY = "Business as usual";
    private static final String EXCEL_SERIES_TYPE_REDUCTION_KEY = "emission reduction";
    private static final String EXCEL_SERIES_TYPE_ADOPTION_RATE_KEY = "Adoption rate";
    private static final String EXCEL_SERIES_TYPE_RESULT_KEY = "Estimated CO2 reduction";
    private static final String[] excelSeriesTypeKeys =
            {EXCEL_SERIES_TYPE_BAU_KEY, EXCEL_SERIES_TYPE_REDUCTION_KEY,
                    EXCEL_SERIES_TYPE_ADOPTION_RATE_KEY, EXCEL_SERIES_TYPE_REDUCTION_KEY};

    private static final Map<String, String> excelTermToOntologyTermNameMap;
    private static final Map<String, String> excelSeriesTypeToSeriesTypeMap;

    static {
        excelTermToOntologyTermNameMap = new HashMap<>();
        excelTermToOntologyTermNameMap.put("Transport", "Transportation");
        excelTermToOntologyTermNameMap.put("EnergySupply", "Energy supply");
        excelTermToOntologyTermNameMap.put("Other", "Land use & other sectors");
        excelTermToOntologyTermNameMap
                .put("All other developed countries", "Other developed countries");
        excelTermToOntologyTermNameMap
                .put("All other developing countries", "Other developing countries");
    }

    static {
        excelSeriesTypeToSeriesTypeMap = new HashMap<>(excelSeriesTypeKeys.length);
        excelSeriesTypeToSeriesTypeMap.put(EXCEL_SERIES_TYPE_BAU_KEY,
                ProposalImpactSeries.SERIES_TYPE_BAU_KEY); // we don't need this one
        excelSeriesTypeToSeriesTypeMap
                .put(EXCEL_SERIES_TYPE_REDUCTION_KEY, ImpactSeriesType.IMPACT_REDUCTION.name());
        excelSeriesTypeToSeriesTypeMap.put(EXCEL_SERIES_TYPE_ADOPTION_RATE_KEY,
                ImpactSeriesType.IMPACT_ADOPTION_RATE.name());
        excelSeriesTypeToSeriesTypeMap
                .put(EXCEL_SERIES_TYPE_RESULT_KEY, ""); // we don't need this one
    }

    private final String tabSeparatedString;
    private final ProposalWrapper proposal;
    private final ContestWrapper contest;


    /**
     * Creates a new ProposalImpactDataParser object with the input String (tab-separated string
     * copy-pasted from Excel) and the Contest of the proposal in question.
     */
    public ProposalImpactDataParser(String tabSeparatedString, ProposalWrapper proposal, ContestWrapper contest) {
        this.tabSeparatedString = tabSeparatedString;
        this.proposal = proposal;
        this.contest = contest;

    }

    public ProposalImpactSeriesList parse() throws ProposalImpactDataParserException {
        String[] inputLines = getLines(tabSeparatedString);
        List<String> parsedSeriesTypeKeys = getParsedSeriesTypeKeys(inputLines);
        List<Long> iterationYears = getParsedIterationYears(inputLines);

        return parseExcelData(Arrays.copyOfRange(inputLines, 2, inputLines.length),
                parsedSeriesTypeKeys, iterationYears);
    }

    private List<String> getParsedSeriesTypeKeys(String[] inputLines)
            throws ProposalImpactDataParserException {
        if (ArrayUtils.isEmpty(inputLines)) {
            return null;
        }

        String headLine = inputLines[0];
        List<String> seriesTypes = new ArrayList<>();
        for (String headLineColumn : getTabbedStrings(headLine)) {
            // Ignore empty columns (tabs)
            if (headLineColumn != null) {
                // Try to match excelSeriesType from input
                boolean foundMatch = false;
                for (Map.Entry<String, String> entry : excelSeriesTypeToSeriesTypeMap.entrySet()) {
                    if (headLineColumn.contains(entry.getKey())) {
                        String seriesType = entry.getValue();
                        seriesTypes.add(seriesType);

                        foundMatch = true;
                        break;
                    }
                }

                if (!foundMatch) {
                    throw new ProposalImpactDataParserException(
                            "Could not match data column '" + headLineColumn + "'");
                }
            }
        }

        return seriesTypes;
    }

    private String[] getTabbedStrings(String inputLine) {
        return inputLine.split("\\t", -1);
    }

    private List<Long> getParsedIterationYears(String[] inputLines) {
        if (ArrayUtils.isEmpty(inputLines)) {
            return null;
        }

        String iterationYearLine = inputLines[1];
        List<Long> iterationYears = new ArrayList<>();
        for (String iterationYearColumn : getTabbedStrings(iterationYearLine)) {
            // Ignore empty columns (tabs)
            if (iterationYearColumn != null) {
                try {
                    long iterationYear = Long.parseLong(iterationYearColumn);

                    // Ignore duplicates
                    if (iterationYears.contains(iterationYear)) {
                        break;
                    }

                    iterationYears.add(iterationYear);

                } catch (NumberFormatException e) {
                    _log.error("Could not parse string to iteration year '{}' of line 1 '{}'",
                            iterationYearColumn, iterationYearLine, e);
                    throw e;
                }
            }
        }

        return iterationYears;
    }

    private ProposalImpactSeriesList parseExcelData(String[] inputLines,
            List<String> parsedSeriesTypeKeys, List<Long> iterationYears)
            throws ProposalImpactDataParserException {

        ProposalImpactSeriesList seriesList =
                new ProposalImpactSeriesList(this.contest, this.proposal);
        ProposalImpactUtil proposalImpactUtil = new ProposalImpactUtil(contest);

        OntologyTermWrapper regionTerm = null;
        int inputLineNumber = 2;
        for (String inputLine : inputLines) {
            String[] dataStrings = getTabbedStrings(inputLine);

            // We need at least the region+sector and one full iteration of years
            if (ArrayUtils.isNotEmpty(dataStrings) && dataStrings.length >= 2 + iterationYears
                    .size()) {
                OntologyTermWrapper sectorTerm;
                try {
                    // Some of the first columns are empty - use old region term instead
                    if (dataStrings[0] != null) {
                        regionTerm = getOntologyTermByName(dataStrings[0]);
                    }

                    sectorTerm = getOntologyTermByName(dataStrings[1]);
                } catch (ProposalImpactDataParserException e) {
                    _log.error("{} on line {}", e.getMessage(), inputLineNumber);
                    throw new ProposalImpactDataParserException(e);
                }

                FocusAreaWrapper focusArea =
                        proposalImpactUtil.getFocusAreaAssociatedWithTerms(sectorTerm, regionTerm);
                ProposalImpactSeries newProposalImpactSeries =
                        new ProposalImpactSeries(contest, proposal, focusArea);

                // Write the new impact series values
                for (int idx = 2; idx < dataStrings.length; idx++) {
                    String dataString = dataStrings[idx];
                    String currentSeriesType =
                            parsedSeriesTypeKeys.get(((idx - 2) / iterationYears.size()));

                    // If the seriesType is not set we just ignore all upcoming values
                    if (currentSeriesType == null) {
                        idx += iterationYears.size() - 1;
                        continue;
                    }

                    // Empty lines are not tolerated - abort
                    if (dataString == null) {
                        throw new ProposalImpactDataParserException(
                                "Empty data column detected in line '" + inputLine);
                    }

                    long year = iterationYears.get((idx - 2) % iterationYears.size());

                    try {
                        setProposalImpactSeriesValue(newProposalImpactSeries,
                                ImpactSeriesType.valueOf(currentSeriesType), year, dataString);
                    } catch (NumberFormatException e) {
                        _log.error("Could not parse string '{}' on line {} to double value",
                                dataString, inputLineNumber);
                        throw new ProposalImpactDataParserException(
                                "\"Could not parse string '" + dataString + "' to double value\"",
                                e);
                    } catch (ProposalImpactDataParserException e) {
                        _log.error("Input Line {}: {}", inputLineNumber, e.getMessage());
                        throw e;
                    }
                }

                seriesList.addProposalImpactSeries(newProposalImpactSeries);

            } else {
                throw new ProposalImpactDataParserException(
                        "Could not parse data line '" + StringUtils.join(dataStrings, ",")
                                + "'; line too short");
            }

            inputLineNumber++;
        }

        return seriesList;
    }

    private void setProposalImpactSeriesValue(ProposalImpactSeries proposalImpactSeries,
            ImpactSeriesType seriesType, long year, String valueString)
            throws ProposalImpactDataParserException {
        double value = parseDataValue(seriesType, valueString);
        proposalImpactSeries.addSeriesValueWithType(seriesType, (int) year, value);
    }

    private double parseDataValue(ImpactSeriesType seriesType, String valueString)
            throws ProposalImpactDataParserException {
        // Remove comma-thousand separators
        String decimalStringValue = valueString.replaceAll(",", "");

        // percentage value
        if (decimalStringValue.contains("%")) {
            double value = Double.parseDouble(
                    decimalStringValue.substring(0, decimalStringValue.length() - 1));
            if (value < 0 || value > 100) {
                throw new ProposalImpactDataParserException(
                        "Percentage value '" + value + "' out of bounds!");
            }
            return value;
        } else {
            double value = Double.parseDouble(decimalStringValue);

            // Interpret impact reduction and adoption values as ratios
            if ((seriesType.equals(ImpactSeriesType.IMPACT_REDUCTION)
                    || seriesType.equals(ImpactSeriesType.IMPACT_ADOPTION_RATE))) {
                return value * 100.;    // impact reduction and adoption rate values are stored as percentage values (0-100%)
            } else {
                return value;
            }
        }
    }

    private OntologyTermWrapper getOntologyTermByName(String name)
            throws ProposalImpactDataParserException {
        // Use mapped name value if it exists
        if (excelTermToOntologyTermNameMap.get(name) != null) {
            name = excelTermToOntologyTermNameMap.get(name);
        }

        List<OntologyTermWrapper> ontologyTerms = StaticContestContext.getOntologyClient()
                .getOntologyTermsByName(name);
        if (ontologyTerms == null || ontologyTerms.isEmpty()) {
            throw new ProposalImpactDataParserException(
                    "Could not match ontology term with name '" + name + "'");
        }

        return ontologyTerms.get(0);
    }

    private String[] getLines(String inputLines) {
        return inputLines.split("\\n", -1);
    }
}
