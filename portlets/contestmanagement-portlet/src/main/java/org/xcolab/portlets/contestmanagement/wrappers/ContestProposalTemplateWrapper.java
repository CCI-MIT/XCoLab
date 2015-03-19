package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.*;
import org.xcolab.portlets.contestmanagement.beans.SectionDefinitionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2/18/2015.
 */
public class ContestProposalTemplateWrapper {

    private List<SectionDefinitionBean> sections;
    private Long forkedPlanTemplateId;
    private Integer numberOfSections; // TODO check might remove?
    private PlanTemplate planTemplate;
    private String templateName;
    private Contest contest;
    private static final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";

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

    public String getTemplateName() {
        if(planTemplate != null && planTemplate.getName() != null) {
            this.templateName = planTemplate.getName();
        }
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
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

    public void createSectionDefinitionsForNewSections() throws Exception{
        List<SectionDefinitionBean> removedSectionDefinitions = new ArrayList<>();
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            if(sectionBaseDefinition.getSectionDefinitionId() == null){
                createPlanSectionDefinitionFromSectionDefinitionBean(sectionBaseDefinition);
            }
        }
    }

    private void createPlanSectionDefinitionFromSectionDefinitionBean(SectionDefinitionBean sectionBaseDefinition) throws Exception{
        PlanSectionDefinition planSectionDefinition = PlanSectionDefinitionLocalServiceUtil.
                createPlanSectionDefinition(CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName()));
        setPlanSectionDefinitionFromSectionDefinitionBean(planSectionDefinition, sectionBaseDefinition);

        planSectionDefinition.persist();
        sectionBaseDefinition.setSectionDefinitionId(planSectionDefinition.getId());
    }

    private void setPlanSectionDefinitionFromSectionDefinitionBean
            (PlanSectionDefinition planSectionDefinition, SectionDefinitionBean sectionDefinitionBean){
        planSectionDefinition.setType(sectionDefinitionBean.getType());
        planSectionDefinition.setTitle(sectionDefinitionBean.getTitle());
        planSectionDefinition.setDefaultText(sectionDefinitionBean.getDefaultText());
        planSectionDefinition.setCharacterLimit(sectionDefinitionBean.getCharacterLimit());
        planSectionDefinition.setHelpText(sectionDefinitionBean.getHelpText());
        planSectionDefinition.setTier(sectionDefinitionBean.getLevel());
        // TODO planSectionDefinition.setFocusAreaId(sectionBaseDefinition.getFocusAreaId());
    }

    public void updateNewProposalTemplateSections() throws Exception{
        removeDeletedSections();
        removeTemplacteSection();
        createSectionDefinitionsForNewSections();

        if(planTemplate != null) {
            Long baseTemplateId = planTemplate.getBaseTemplateId();
            if (baseTemplateId == 0) {
                String contestTitle = contest.getContestShortName();
                String baseProposalTemplateTitle = planTemplate.getName();
                String newProposalTemplateTitle;

                if (templateName.equals(baseProposalTemplateTitle)) {
                    newProposalTemplateTitle = "Base template - " + baseProposalTemplateTitle + "- adapted for contest: " + contestTitle;

                } else {
                    newProposalTemplateTitle = templateName;
                }

                createProposalTemplate(newProposalTemplateTitle, planTemplate.getId());
                Long newPlanTemplateId = planTemplate.getId();
                contest.setPlanTemplateId(newPlanTemplateId);
                contest.persist();
            }

            if (!planTemplate.getName().equals(templateName)) {
                planTemplate.setName(templateName);
                planTemplate.persist();
                PlanTemplateLocalServiceUtil.updatePlanTemplate(planTemplate);
            }
        } else {
            // TODO remove duplicates
            String newProposalTemplateTitle = "Template for: " + contest.getContestShortName();
            if(!templateName.isEmpty()){
                newProposalTemplateTitle = templateName;
            }
            createProposalTemplate(newProposalTemplateTitle, 0L);
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
            if(isSectionDifferentFromItsDefinition(sectionDefinitionBean)) {
                updateSectionOrCreateNewIfPartOfBaseTemplate(sectionDefinitionBean);
            }
            createPlanTemplateSection(sectionDefinitionBean);
        }
    }

    private boolean isSectionDifferentFromItsDefinition(SectionDefinitionBean sectionDefinitionBean){
        boolean isSectionDifferentFromDefinition = false;
        try {
            PlanSectionDefinition planSectionDefinition =
                    PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(sectionDefinitionBean.getSectionDefinitionId());

            isSectionDifferentFromDefinition = !(
                    planSectionDefinition.getTitle().equalsIgnoreCase(sectionDefinitionBean.getTitle()) &&
                    planSectionDefinition.getType().equalsIgnoreCase(sectionDefinitionBean.getType()) &&
                    planSectionDefinition.getDefaultText().equalsIgnoreCase(sectionDefinitionBean.getDefaultText()) &&
                    planSectionDefinition.getHelpText().equalsIgnoreCase(sectionDefinitionBean.getHelpText()) &&
                    planSectionDefinition.getCharacterLimit() == sectionDefinitionBean.getCharacterLimit());
            /*Map<String, Object> stringObjectMap = planSectionDefinition.getModelAttributes();
            for(Map.Entry<String, Object> entry : stringObjectMap.entrySet()){
            }*/

        } catch(Exception e){
        }
        return isSectionDifferentFromDefinition;
    }

    private void updateSectionOrCreateNewIfPartOfBaseTemplate(SectionDefinitionBean sectionDefinitionBean) throws Exception{
        if(planTemplate.getBaseTemplateId() == 0 || isSectionIdPartOfBaseProposalTemplate(sectionDefinitionBean)){
            createPlanSectionDefinitionFromSectionDefinitionBean(sectionDefinitionBean);
        } else {
            updatePlanSectionDefinition(sectionDefinitionBean);
        }
    }

    private boolean isSectionIdPartOfBaseProposalTemplateOld(SectionDefinitionBean sectionDefinitionBean) throws Exception{
        ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(
                ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader");

        Long planTemplateId = new Long(planTemplate.getBaseTemplateId());
        Long planSectionId = new Long(sectionDefinitionBean.getSectionDefinitionId());
        // TODO check why class not found exception occurs,
        // for now this function is replaced by isSectionIdPartOfBaseProposalTemplate
        DynamicQuery queryCountSectionIdInBaseProposalTemplate =
                DynamicQueryFactoryUtil.forClass(PlanTemplateSection.class, portletClassLoader)
                        .add(PropertyFactoryUtil.forName("planTemplateId").eq(planTemplateId))
                        .add(PropertyFactoryUtil.forName("planSectionId").eq(planSectionId))
                        .setProjection(ProjectionFactoryUtil.count("planTemplateId"));

        List queryResult = PlanTemplateSectionLocalServiceUtil.dynamicQuery(queryCountSectionIdInBaseProposalTemplate);
        Long sectionCount = (Long) queryResult.get(0);
        return sectionCount > 0;
    }

    private boolean isSectionIdPartOfBaseProposalTemplate(SectionDefinitionBean sectionDefinitionBean) throws Exception{
        boolean isSectionIdPartOfBaseProposalTemplate = false;
        Long planTemplateId = planTemplate.getBaseTemplateId();
        Long planSectionId = sectionDefinitionBean.getSectionDefinitionId();
        List<PlanTemplateSection> planTemplateSections = PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(planTemplateId);
        for(PlanTemplateSection planTemplateSection : planTemplateSections){
            if(planSectionId.equals(planTemplateSection.getPlanSectionId())){
                isSectionIdPartOfBaseProposalTemplate = true;
            }

        }
        return isSectionIdPartOfBaseProposalTemplate;
    }

    private void updatePlanSectionDefinition(SectionDefinitionBean sectionDefinitionBean) throws Exception{

        PlanSectionDefinition planSectionDefinition =
                PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(sectionDefinitionBean.getId());

        setPlanSectionDefinitionFromSectionDefinitionBean(planSectionDefinition, sectionDefinitionBean);
        planSectionDefinition.persist();
        PlanSectionDefinitionLocalServiceUtil.updatePlanSectionDefinition(planSectionDefinition);
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
