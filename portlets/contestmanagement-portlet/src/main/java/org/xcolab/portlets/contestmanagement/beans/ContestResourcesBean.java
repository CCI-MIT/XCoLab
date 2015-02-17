package org.xcolab.portlets.contestmanagement.beans;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.contestmanagement.utils.ContestResourcesHtmlParserUtil;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Thomas on 2/13/2015.
 */
public class ContestResourcesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SectionDefinitionBean> baseSections;
    private List<SectionDefinitionBean> additionalSections;
    private List<SectionDefinitionBean> sections;
    private HashMap<String, String> overviewSectionValues;
    private ContestResourcesHtmlParserUtil contestResourcesHtmlParserUtil;
    private int numberOfSections;

    private final static String[] BASE_SECTION_TITLES = {"Prizes", "Background", "Contest Guidelines", "References"};
    private final static List<String> IGNORE_SECTION_TITLES = Arrays.asList("Overview", "\u00a0");
    private final static int START_INDEX_OF_BOTTOM_SECTIONS = 1;

    private final static String SECTION_TEXT_DEFAULT = "";
    private final static String SECTION_HELPTEXT_PRIZES = "Awards for contest winners, such as funding, invitations to present at a conference, meetings with key stakeholders, etc";
    private final static String SECTION_HELPTEXT_BACKGROUND = "This section expands on the homepage description (above).<br />\n" +
            "Outline the opportunity and/or challenge the contest seeks to tackle and concludes by stating the contest's focus,\n" +
            "so authors have a clear understanding of the issue and framing for the challenge.<br />\n" +
            "The section briefly answers: Why are ideas being sought for this topic?<br />\n" +
            " Why is it important in addressing climate change?<br />\n" +
            "What are the important problems/key issues being faced?<br />\n" +
            "What could be possible if these challenges were overcome?<br />\n" +
            " It is important this section is rooted in scientific facts and is consistent with<br />\n" +
            "the findings of the scientific community (for example, with the IPCC report).<br />\n" +
            "It is beneficial to include applicable facts and figures";
    private final static String SECTION_HELPTEXT_GUIDELINES = "Clarify what the contest is seeking and can be done in bullet points.<br />\n" +
            "Here you can expand on the type, scope and development of the ideas. For example:<br />\n" +
            "what kinds of solutions? (community projects, business models, web interfaces/apps, policy solutions, new technologies, etc.)<br />\n" +
            " what scope of use? (local, national, international, etc.)<br />\n" +
            " for whom to implement the proposal? (the city only, citizens only, anyone, etc.)<br />\n" +
            "what stage of development? (all ideas welcome, fully implementable and scalable proposals only, etc.)";
    private final static String SECTION_HELPTEXT_REFERENCES = "Free, web-accessible references to help guide authors in preparing their proposals.";

    private final static String[] BASE_SECTIONS_HELPTEXT = {SECTION_HELPTEXT_PRIZES, SECTION_HELPTEXT_BACKGROUND,
            SECTION_HELPTEXT_GUIDELINES, SECTION_HELPTEXT_REFERENCES};


    public ContestResourcesBean() {
        sections = new ArrayList<>();
        baseSections = new ArrayList<>();
        additionalSections = new ArrayList<>();
        contestResourcesHtmlParserUtil = new ContestResourcesHtmlParserUtil();
        contestResourcesHtmlParserUtil.setBaseSectionTitles(BASE_SECTION_TITLES);
        contestResourcesHtmlParserUtil.setIgnoreSectionTitles(IGNORE_SECTION_TITLES);
    }

    public void splitSections(){
        int startOfAdditionalSections = START_INDEX_OF_BOTTOM_SECTIONS;
        int endOfAdditionalSections = numberOfSections - START_INDEX_OF_BOTTOM_SECTIONS;
        List<SectionDefinitionBean> baseSectionsTop = sections.subList(0, START_INDEX_OF_BOTTOM_SECTIONS);
        List<SectionDefinitionBean> baseSectionBottom = sections.subList(endOfAdditionalSections, sections.size());
        baseSections.addAll(baseSectionsTop);
        baseSections.addAll(baseSectionBottom);
        additionalSections = sections.subList(startOfAdditionalSections, endOfAdditionalSections);
    }

    public String getSectionsAsHtml(){
        removeDeletedSections();
        incooperateNewSections();

        StringBuilder sectionsAsHtmlString = new StringBuilder();
        String overviewSection = contestResourcesHtmlParserUtil.getOverviewSectionAsHtmlString(overviewSectionValues);

        sectionsAsHtmlString.append(overviewSection);
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            String sectionAsHtmlString = contestResourcesHtmlParserUtil.getSectionAsHtmlString(sectionBaseDefinition);
            sectionsAsHtmlString.append(sectionAsHtmlString);
        }
        return sectionsAsHtmlString.toString();
    }

    private void incooperateNewSections(){
        SectionDefinitionBean templateSectionDefinitionBean = new SectionDefinitionBean();
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            if(sectionBaseDefinition.isTemplateSection()) {
                templateSectionDefinitionBean = sectionBaseDefinition;
                break;
            }
        }
        int indexOfDummySectionBaseDefinition = sections.indexOf(templateSectionDefinitionBean);
        sections.remove(templateSectionDefinitionBean);
        Collections.rotate(sections.subList(indexOfDummySectionBaseDefinition-1, sections.size()), -1);
    }

    private void removeDeletedSections(){
        List<SectionDefinitionBean> removedSectionDefinitions = new ArrayList<>();
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            if(sectionBaseDefinition.getTitle().isEmpty()
                    && sectionBaseDefinition.getContent().isEmpty()
                    && !sectionBaseDefinition.isTemplateSection()){
                removedSectionDefinitions.add(sectionBaseDefinition);
            }
        }

        for(SectionDefinitionBean removedSectionDefinition : removedSectionDefinitions) {
            sections.remove(removedSectionDefinition);
        }

    }


    public void fillSectionsWithContent(String resourcesContent) throws Exception {
        contestResourcesHtmlParserUtil.parseDocument(resourcesContent);
        fillBaseSectionsWithContent();
        fillAdditionalSectionsWithContent();
        composeSectionsList();
    }

    private void fillBaseSectionsWithContent() {
        HashMap<String, String> baseSectionsContent = contestResourcesHtmlParserUtil.getBaseSections();
        int index = 0;
        for (String baseContestSectionTitle : baseSectionsContent.keySet()) {
            SectionDefinitionBean sectionDefinitionBean =
                    new SectionDefinitionBean(baseContestSectionTitle, 0,
                            BASE_SECTIONS_HELPTEXT[index], baseSectionsContent.get(baseContestSectionTitle), false);
            baseSections.add(sectionDefinitionBean);
            index++;
        }
        Collections.reverse(sections);
    }

    private void addBaseSectionsWithDefaultsToSectionsList() {
        HashMap<String, String> baseSectionsContent = contestResourcesHtmlParserUtil.getBaseSections();
        for (String baseContestSectionTitle : baseSectionsContent.keySet()) {
            SectionDefinitionBean sectionDefinitionBean =
                    new SectionDefinitionBean(baseContestSectionTitle, false);
            sections.add(sectionDefinitionBean);
        }
    }

    private void fillAdditionalSectionsWithContent() {
        HashMap<String, String> additionalSectionsContent = contestResourcesHtmlParserUtil.getAdditionalSections();
        for (String additionalContestSectionTitle : additionalSectionsContent.keySet()) {
            SectionDefinitionBean sectionDefinitionBean =
                    new SectionDefinitionBean(additionalContestSectionTitle, 0,
                            SECTION_TEXT_DEFAULT, additionalSectionsContent.get(additionalContestSectionTitle));
            additionalSections.add(sectionDefinitionBean);
        }
    }

    private void addAdditionalSectionsWithDefaultsToSectionsList() {
        while (sections.size() < numberOfSections ) {
            SectionDefinitionBean sectionDefinitionBean = new SectionDefinitionBean();
            sections.add(sectionDefinitionBean);
        }
    }

    private void addTemplateSectionWithDefaultsToSectionsList(){
        SectionDefinitionBean sectionDefinitionBean = new SectionDefinitionBean();
        sectionDefinitionBean.setTemplateSection(true);
        sections.add(sectionDefinitionBean);
    }

    private void composeSectionsList(){
        sections.clear();
        List<SectionDefinitionBean> baseSectionsTop = baseSections.subList(0, START_INDEX_OF_BOTTOM_SECTIONS);
        List<SectionDefinitionBean> baseSectionBottom = baseSections.subList(START_INDEX_OF_BOTTOM_SECTIONS, baseSections.size());
        sections.addAll(baseSectionsTop);
        sections.addAll(additionalSections);
        sections.addAll(baseSectionBottom);
        numberOfSections = sections.size();
        Collections.reverse(sections);
        addTemplateSectionWithDefaultsToSectionsList();
    }

    private void createEmptySectionsList(){
        addBaseSectionsWithDefaultsToSectionsList();
        addAdditionalSectionsWithDefaultsToSectionsList();
    }

    public List<SectionDefinitionBean> getSections() {
        return sections;
    }

    public void setSections(List<SectionDefinitionBean> sections) {
        this.sections = sections;
    }

    public int getNumberOfSections() {
        return numberOfSections;
    }

    public void setNumberOfSections(int numberOfSections) {
        this.numberOfSections = numberOfSections;
        createEmptySectionsList();
    }

    public void fillOverviewSectionContent(Contest contest) throws SystemException{
        List<ContestPhase> contestPhaseList = ContestPhaseLocalServiceUtil.getPhasesForContest(contest);
        String proposalSubmissionEndDate = "";
        for(ContestPhase contestPhase: contestPhaseList){
            Long contestPhaseType = contestPhase.getContestPhaseType();
            if(contestPhaseType == 1L){
                proposalSubmissionEndDate = contestPhase.getPhaseEndDate().toString();
                break;
            }
        }
        overviewSectionValues = new HashMap<>();
        overviewSectionValues.put("Question:", contest.getContestName());
        overviewSectionValues.put("Submit proposals:", "http://climatecolab.org/web/guest/plans/-/plans/contestId/" + contest.getContestPK());
        overviewSectionValues.put("Deadline:", proposalSubmissionEndDate);
        overviewSectionValues.put("Rules:", "All entrants must agree to the <a href='#'>2014 Contest Rule</a>.");
    }
}




