package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.*;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanSectionDefinitionUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import org.xcolab.portlets.contestmanagement.beans.SectionDefinitionBean;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Thomas on 2/18/2015.
 */
public class ContestProposalTemplateWrapper {

    private List<SectionDefinitionBean> sections;
    private Long forkedPlanTemplateId;
    private Integer numberOfSections; // TODO check might remove?
    private  PlanTemplate planTemplate;
    private Contest contest;

    public ContestProposalTemplateWrapper(){
    }

    public ContestProposalTemplateWrapper(Contest contest) throws Exception{
        init(contest);
        populateExistingProposalTemplateSections();
    }

    public void setPlanTemplate(PlanTemplate planTemplate) {
    }

    public void init(Contest contest) throws Exception{
        this.contest = contest;
        this.planTemplate = ContestLocalServiceUtil.getPlanTemplate(contest);
    }

    private void populateExistingProposalTemplateSections() throws Exception{
        sections = new ArrayList<>();
        if (planTemplate != null) {
            for (PlanSectionDefinition planSectionDefinition : PlanTemplateLocalServiceUtil.getSections(planTemplate)) {
                if(!planSectionDefinition.isLocked()) {
                    sections.add(new SectionDefinitionBean(planSectionDefinition, planTemplate.getId()));
                }
            }
        }
        SectionDefinitionBean sectionDefinitionBean = new SectionDefinitionBean();
        sectionDefinitionBean.setTemplateSection(true);
        sectionDefinitionBean.setTitle("");
        sectionDefinitionBean.setContent("");
        sectionDefinitionBean.setLevel(0L);
        sections.add(sectionDefinitionBean);
    }

    private void populateProposalTemplateSectionsFromPlanTemplateId(Long id) throws Exception{
        sections = new ArrayList<>();

        PlanTemplate planTemplate = PlanTemplateLocalServiceUtil.getPlanTemplate(id);
        if (planTemplate != null) {
            for (PlanSectionDefinition planSectionDefinition : PlanTemplateLocalServiceUtil.getSections(planTemplate)) {
                if(!planSectionDefinition.isLocked()) {
                    sections.add(new SectionDefinitionBean(planSectionDefinition, planTemplate.getId()));
                }
            }
        }
        sections.add(new SectionDefinitionBean());
    }

    public List<SectionDefinitionBean> getSections() {
        return sections;
    }

    public void setSections(List<SectionDefinitionBean> sections) {
        this.sections = sections;
    }

    public int getNumberOfSections() {
        return sections.size();
    }

    public void setNumberOfSections(int numberOfSections) {
        this.numberOfSections = numberOfSections;
    }

    private void removeTemplacteSection(){
        SectionDefinitionBean templateSectionDefinitionBean = new SectionDefinitionBean();
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            if(sectionBaseDefinition.isTemplateSection()) {
                templateSectionDefinitionBean = sectionBaseDefinition;
                break;
            }
        }
        //int indexOfDummySectionBaseDefinition = sections.indexOf(templateSectionDefinitionBean);
        sections.remove(templateSectionDefinitionBean);
        //Collections.rotate(sections.subList(indexOfDummySectionBaseDefinition - 1, sections.size()), -1);
    }

    public void removeDeletedSections(){
        List<SectionDefinitionBean> removedSectionDefinitions = new ArrayList<>();
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            if((sectionBaseDefinition.getTitle() == null || sectionBaseDefinition.getTitle().isEmpty())
                    && !sectionBaseDefinition.isTemplateSection()){
                removedSectionDefinitions.add(sectionBaseDefinition);
            }
        }

        for(SectionDefinitionBean removedSectionDefinition : removedSectionDefinitions) {
            sections.remove(removedSectionDefinition);
        }
    }

    public void createSectionDefinitonsForNewSections() throws Exception{

        List<SectionDefinitionBean> removedSectionDefinitions = new ArrayList<>();
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            if(sectionBaseDefinition.getSectionDefinitionId() == null){
                PlanSectionDefinition planSectionDefinition = PlanSectionDefinitionLocalServiceUtil.
                        createPlanSectionDefinition(CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName()));

                planSectionDefinition.setType(sectionBaseDefinition.getType());
                planSectionDefinition.setTitle(sectionBaseDefinition.getTitle());
                planSectionDefinition.setDefaultText(sectionBaseDefinition.getDefaultText());
                planSectionDefinition.setCharacterLimit(sectionBaseDefinition.getCharacterLimit());
                planSectionDefinition.setHelpText(sectionBaseDefinition.getHelpText());
                planSectionDefinition.setTier(sectionBaseDefinition.getLevel());
                // TODO planSectionDefinition.setFocusAreaId(sectionBaseDefinition.getFocusAreaId());
                planSectionDefinition.persist();
                sectionBaseDefinition.setSectionDefinitionId(planSectionDefinition.getId());
            }
        }

    }

    public void updateNewProposalTemplateSections() throws Exception{
        removeDeletedSections();
        removeTemplacteSection();
        createSectionDefinitonsForNewSections();

        Long baseTemplateId =  planTemplate.getBaseTemplateId();

        if(baseTemplateId == 0){
            String contestTitle = contest.getContestShortName();
            String baseProposalTemplateTitle = planTemplate.getName();
            String newProposalTemplateTitle = "Base template - " + baseProposalTemplateTitle + "- adapted for contest: " + contestTitle;
            createProposalTemplate(newProposalTemplateTitle, planTemplate.getId());
            Long newPlanTemplateId = planTemplate.getId();
            contest.setPlanTemplateId(newPlanTemplateId);
            contest.persist();
        }

        removeExistingSectionsFromProposalTemplate();
        addSectionsToProposalTemplate();
    }

    private void createProposalTemplate(String title, Long baseTemplateId) throws Exception{
        planTemplate = PlanTemplateLocalServiceUtil.createPlanTemplate(CounterLocalServiceUtil.increment(PlanTemplate.class.getName()));
        planTemplate.setName(title);
        planTemplate.setBaseTemplateId(baseTemplateId);
        planTemplate.persist();
    }

    private void addSectionsToProposalTemplate() throws Exception{
        for(SectionDefinitionBean sectionDefinitionBean: sections) {
            createPlanTemplateSection(sectionDefinitionBean);
        }
    }

    private void createPlanTemplateSection(SectionDefinitionBean sectionDefinitionBean) throws Exception{

        Long planTemplateId = planTemplate.getId();
        Long sectionId = sectionDefinitionBean.getSectionDefinitionId();
        Integer weight = sectionDefinitionBean.getWeight();

        PlanTemplateSection planTemplateSection =
                PlanTemplateSectionLocalServiceUtil.addPlanTemplateSection(planTemplateId, sectionId, weight);
        planTemplateSection.persist(); // TODO check whether necessary
    }

    private void removeExistingSectionsFromProposalTemplate() throws Exception{
        Long proposalTemplateId = planTemplate.getId();
        List<PlanTemplateSection> planTemplateSections =
                PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(proposalTemplateId);
        for(PlanTemplateSection planTemplateSection : planTemplateSections){
            PlanTemplateSectionLocalServiceUtil.deletePlanTemplateSection(planTemplateSection);
        }
    }

}
