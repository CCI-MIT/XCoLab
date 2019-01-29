package org.xcolab.view.pages.contestmanagement.beans;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.view.pages.contestmanagement.utils.ContestResourcesHtmlParserUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.SectionDefinitionWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ContestResourcesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormat.forPattern("EEEE, MMM dd, yyyy 'at' HH:mm:ss a zzzz");

    private static final String[] BASE_SECTION_TITLES = {
            "Background", "Key Issues", "Judging Criteria",
            "Prizes", "Resources for <proposal/> Authors"
    };
    private static final List<String> IGNORE_SECTION_TITLES = Arrays.asList("Overview", "\u00a0");
    private static final int START_INDEX_OF_BOTTOM_SECTIONS = 4;
    private static final String SECTION_TEXT_DEFAULT = "";
    private static final String SECTION_HELP_TEXT_BACKGROUND =
            "This section expands on the homepage description and includes:<br />\n" +
                    "- An overview of the sector/topic and why it is important for addressing "
                    + "climate change;<br />\n"
                    +
                    "- Relevant facts and figures (please note that it is important this section "
                    + "is rooted in scientific facts and is consistent with the findings of the "
                    + "scientific community, such as those found in the "
                    + "<a href=\"http://www.ipcc.ch/report/ar5/\">IPCC Fifth Assessment "
                    + "Report</a>.);<br />\n"
                    +
                    "- Why are ideas being sought and what could be possible if these challenges "
                    + "were overcome;<br />\n"
                    +
                    "- A brief statement of the contest’s focus (which will be expanded upon in "
                    + "the Judging Criteria section, below).<br />";
    private static final String SECTION_HELP_TEXT_KEY_ISSUES =
            "This section provides readers with a sense of where the leaders in this field are "
                    + "looking – what are the current challenges or opportunities that people in "
                    + "this sector are talking about and working to solve? What recent "
                    + "developments have impacted the sector? For example, this section could "
                    + "reference the impact of the U.S.-China climate agreement on the sector, "
                    + "the automotive industry’s focus on self-driving cars, or the dramatic "
                    + "increase of investment in energy storage.";
    private static final String SECTION_HELP_TEXT_PRIZES =
            "All contest winners will be recognized by the Climate CoLab and eligible for its "
                    + "Grand Prize.  If your contest team is offering any additional prizes, "
                    + "please state them here.";
    private static final String SECTION_HELP_TEXT_JUDGING_CRITERIA =
            "<p>This section clarifies what kinds of proposals the contest is seeking and is "
                    + "usually written in bullet points.</p>\n"
                    +
                    "<p><strong>For Climate CoLab contests:</strong> The judging criteria are "
                    + "typically standard across all Climate CoLab contests, though your contest "
                    + "team is welcome to add text below the section, “The Judges will favor "
                    + "proposals that also effectively…</p>\n"
                    +
                    "<p><strong>For partner contests:</strong> Here is where you can define: what"
                    + " kinds of solutions are being sought? (community projects, business "
                    + "models, web interfaces/apps, policy solutions, new technologies, etc.); "
                    + "what is the scope of use? (local, national, international, etc.); what "
                    + "stage of development? (all ideas welcome, fully implementable and scalable"
                    + " proposals only, etc.)</p>";
    private static final String SECTION_HELP_TEXT_REFERENCES =
            "Free, web-accessible references to help guide authors in preparing their proposals.";
    private static final String[] BASE_SECTIONS_HELP_TEXT = {
            SECTION_HELP_TEXT_BACKGROUND,
            SECTION_HELP_TEXT_KEY_ISSUES,
            SECTION_HELP_TEXT_JUDGING_CRITERIA,
            SECTION_HELP_TEXT_PRIZES,
            SECTION_HELP_TEXT_REFERENCES
    };

    private static final String OVERVIEW_VIEW_PROPOSALS_TITLE = "View <proposals/>:";
    private static final String OVERVIEW_VIEW_PROPOSALS_CONTENT =
            "<a href=\"<colab-url/><contest-link-url/>\" "
                    + "target=\"_blank\"><colab-url/><contest-link-url/></a>";
    private static final String OVERVIEW_SUBMIT_PROPOSALS_TITLE = "Submit <proposals/>:";
    private static final String OVERVIEW_SUBMIT_PROPOSALS_CONTENT =
            "<a href=\"<colab-url/><contest-link-url/>/createProposal\" "
                    + "target=\"_blank\"><colab-url/><contest-link-url/>/createProposal</a>";
    private static final String OVERVIEW_RULES_TITLE = "Rules:";
    private static final String OVERVIEW_DEADLINE_TITLE = "Deadline:";
    private static final String OVERVIEW_JUDGING_CRITERIA_PRIZES_TITLE =
            "Judging Criteria & Prizes:";
    private static final String OVERVIEW_RULES_CONTENT =
            "All entrants must agree to the <rules-link/> and "
                    +
                    "<a href=\"/wiki/Terms+of+use\" target=\"_blank\">Terms of Use</a>";


    private final List<SectionDefinitionWrapper> baseSections;
    private final ContestResourcesHtmlParserUtil contestResourcesHtmlParserUtil;
    private final ContestType contestType;
    private List<SectionDefinitionWrapper> additionalSections;
    private List<SectionDefinitionWrapper> sections;
    private HashMap<String, String> overviewSectionValues;
    private int numberOfSections;

    @SuppressWarnings("unused")
    public ContestResourcesBean() {
        this(StaticAdminContext.getContestTypeClient().getContestType(
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get()));
    }

    public ContestResourcesBean(ContestType contestType) {
        this.contestType = contestType;
        sections = new ArrayList<>();
        baseSections = new ArrayList<>();
        additionalSections = new ArrayList<>();
        contestResourcesHtmlParserUtil = new ContestResourcesHtmlParserUtil(contestType);
        contestResourcesHtmlParserUtil.setBaseSectionTitles(BASE_SECTION_TITLES);
        contestResourcesHtmlParserUtil.setIgnoreSectionTitles(IGNORE_SECTION_TITLES);
    }

    public void splitSections() {
        int endOfAdditionalSections = numberOfSections - START_INDEX_OF_BOTTOM_SECTIONS;
        List<SectionDefinitionWrapper> baseSectionsTop =
                sections.subList(0, START_INDEX_OF_BOTTOM_SECTIONS);
        List<SectionDefinitionWrapper> baseSectionBottom =
                sections.subList(endOfAdditionalSections, sections.size());
        baseSections.addAll(baseSectionsTop);
        baseSections.addAll(baseSectionBottom);
        additionalSections = sections.subList(START_INDEX_OF_BOTTOM_SECTIONS, endOfAdditionalSections);
    }

    public String getSectionsAsHtml() {
        removeDeletedSections();
        incooperateNewSections();

        StringBuilder sectionsAsHtmlString = new StringBuilder();
        String overviewSection = ContestResourcesHtmlParserUtil
                .getOverviewSectionAsHtmlString(overviewSectionValues);

        sectionsAsHtmlString.append(overviewSection);
        for (SectionDefinitionWrapper sectionBaseDefinition : sections) {
            if (!sectionBaseDefinition.getContent().isEmpty()) {
                String sectionAsHtmlString =
                        ContestResourcesHtmlParserUtil
                                .getSectionAsHtmlString(sectionBaseDefinition);
                sectionsAsHtmlString.append(sectionAsHtmlString);
            }
        }
        return sectionsAsHtmlString.toString();
    }

    private void removeDeletedSections() {
        List<SectionDefinitionWrapper> removedSectionDefinitions = new ArrayList<>();
        for (SectionDefinitionWrapper sectionBaseDefinition : sections) {
            if (sectionBaseDefinition.getTitle().isEmpty()
                    && sectionBaseDefinition.getContent().isEmpty()
                    && !sectionBaseDefinition.isTemplateSection()) {
                removedSectionDefinitions.add(sectionBaseDefinition);
            }
        }

        sections.removeAll(removedSectionDefinitions);
    }

    private void incooperateNewSections() {
        SectionDefinitionWrapper templateSectionDefinitionWrapper = new SectionDefinitionWrapper();
        for (SectionDefinitionWrapper sectionBaseDefinition : sections) {
            if (sectionBaseDefinition.isTemplateSection()) {
                templateSectionDefinitionWrapper = sectionBaseDefinition;
                break;
            }
        }
        int indexOfDummySectionBaseDefinition = sections.indexOf(templateSectionDefinitionWrapper);
        sections.remove(templateSectionDefinitionWrapper);
        Collections.rotate(sections.subList(indexOfDummySectionBaseDefinition - 1, sections.size()),
                -1);
    }

    public void fillSectionsWithContent(String resourcesContent) {
        contestResourcesHtmlParserUtil.parseDocument(resourcesContent);
        fillBaseSectionsWithContent();
        fillAdditionalSectionsWithContent();
        composeSectionsList();
    }

    private void fillBaseSectionsWithContent() {
        HashMap<String, String> baseSectionsContent =
                contestResourcesHtmlParserUtil.getBaseSections();
        int index = 0;
        for (String baseContestSectionTitle : baseSectionsContent.keySet()) {
            SectionDefinitionWrapper sectionDefinitionWrapper =
                    new SectionDefinitionWrapper(baseContestSectionTitle, 0,
                            BASE_SECTIONS_HELP_TEXT[index],
                            baseSectionsContent.get(baseContestSectionTitle), false);
            baseSections.add(sectionDefinitionWrapper);
            index++;
        }
    }

    private void fillAdditionalSectionsWithContent() {
        HashMap<String, String> additionalSectionsContent =
                contestResourcesHtmlParserUtil.getAdditionalSections();
        for (String additionalContestSectionTitle : additionalSectionsContent.keySet()) {
            SectionDefinitionWrapper sectionDefinitionWrapper =
                    new SectionDefinitionWrapper(additionalContestSectionTitle, 0,
                            SECTION_TEXT_DEFAULT,
                            additionalSectionsContent.get(additionalContestSectionTitle));
            additionalSections.add(sectionDefinitionWrapper);
        }
    }

    private void composeSectionsList() {
        sections.clear();
        List<SectionDefinitionWrapper> baseSectionsTop =
                baseSections.subList(0, START_INDEX_OF_BOTTOM_SECTIONS);
        List<SectionDefinitionWrapper> baseSectionBottom =
                baseSections.subList(START_INDEX_OF_BOTTOM_SECTIONS, baseSections.size());
        sections.addAll(baseSectionsTop);
        sections.addAll(additionalSections);
        sections.addAll(baseSectionBottom);
        numberOfSections = sections.size();
        addTemplateSectionWithDefaultsToSectionsList();
    }

    private void addTemplateSectionWithDefaultsToSectionsList() {
        SectionDefinitionWrapper sectionDefinitionWrapper = new SectionDefinitionWrapper();
        sectionDefinitionWrapper.setTemplateSection(true);
        sections.add(sectionDefinitionWrapper);
    }

    public List<SectionDefinitionWrapper> getSections() {
        return sections;
    }

    public void setSections(List<SectionDefinitionWrapper> sections) {
        this.sections = sections;
    }

    public int getNumberOfSections() {
        return numberOfSections;
    }

    public void setNumberOfSections(int numberOfSections) {
        this.numberOfSections = numberOfSections;
        createEmptySectionsList();
    }

    private void createEmptySectionsList() {
        addBaseSectionsWithDefaultsToSectionsList();
        addAdditionalSectionsWithDefaultsToSectionsList();
    }

    private void addBaseSectionsWithDefaultsToSectionsList() {
        HashMap<String, String> baseSectionsContent =
                contestResourcesHtmlParserUtil.getBaseSections();
        for (String baseContestSectionTitle : baseSectionsContent.keySet()) {
            SectionDefinitionWrapper sectionDefinitionWrapper =
                    new SectionDefinitionWrapper(baseContestSectionTitle, false);
            sections.add(sectionDefinitionWrapper);
        }
    }

    private void addAdditionalSectionsWithDefaultsToSectionsList() {
        while (sections.size() < numberOfSections) {
            SectionDefinitionWrapper sectionDefinitionWrapper = new SectionDefinitionWrapper();
            sections.add(sectionDefinitionWrapper);
        }
    }

    public void fillOverviewSectionContent(ContestWrapper contest) {
        List<ContestPhaseWrapper> contestPhaseList = StaticContestContext.getContestClient()
                .getAllContestPhases(contest.getId());
        String proposalSubmissionEndDate = "";
        for (ContestPhaseWrapper contestPhase : contestPhaseList) {
            Long contestPhaseType = contestPhase.getContestPhaseTypeId();
            if (contestPhaseType == 1L) {
                final DateTimeFormatter dateTimeFormatterWithTimeZone = DATE_TIME_FORMAT
                        .withZone(
                                DateTimeZone
                                        .forID(ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID
                                                .get()));
                boolean phaseHasNoEnd = (contestPhase.getPhaseEndDate() == null);
                if (phaseHasNoEnd) {
                    proposalSubmissionEndDate = "This contest has no deadline.";
                } else {
                    proposalSubmissionEndDate = new DateTime(contestPhase.getPhaseEndDate())
                            .toString(dateTimeFormatterWithTimeZone);
                }
                break;
            }
        }
        overviewSectionValues = new LinkedHashMap<>();
        overviewSectionValues.put("Question:", contest.getQuestion());

        final String contestLinkUrl = contest.getContestLinkUrl();
        final String overviewViewProposalsContent = resolvePlaceholders(OVERVIEW_VIEW_PROPOSALS_CONTENT);
        overviewSectionValues.put(resolvePlaceholders(OVERVIEW_VIEW_PROPOSALS_TITLE),
                overviewViewProposalsContent.replace("<contest-link-url/>", contestLinkUrl));
        final String overviewSubmitProposalsContent = resolvePlaceholders(OVERVIEW_SUBMIT_PROPOSALS_CONTENT);
        overviewSectionValues.put(resolvePlaceholders(OVERVIEW_SUBMIT_PROPOSALS_TITLE),
                overviewSubmitProposalsContent.replace("<contest-link-url/>", contestLinkUrl));

        final String rulesLink = "<a href=\"" + contestType.getRulesPageUrl()
                + "\" target=\"_blank\">" + contestType.getRulesPageName() + "</a>";
        final String overviewRulesContent = OVERVIEW_RULES_CONTENT
                .replace("<rules-link/>", rulesLink);
        overviewSectionValues.put(OVERVIEW_RULES_TITLE, overviewRulesContent);

        overviewSectionValues.put(OVERVIEW_DEADLINE_TITLE, proposalSubmissionEndDate);

        for (SectionDefinitionWrapper section : sections) {
            if (section.getTitle().equals("Judging Criteria") && StringUtils
                    .isNotBlank(section.getContent())) {
                overviewSectionValues.put(OVERVIEW_JUDGING_CRITERIA_PRIZES_TITLE, "See below.");
            }
        }

    }

    private String resolvePlaceholders(String string) {
        final String stringWithConstants = TemplateReplacementUtil.replacePlatformConstants(string);
        return TemplateReplacementUtil.replaceContestTypeStrings(stringWithConstants, contestType);
    }
}




