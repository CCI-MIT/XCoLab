package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import org.xcolab.portlets.contestmanagement.beans.SectionDefinitionBean;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.wrapper.ContestWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2/18/2015.
 */
public class ContestProposalTemplateWrapper {

    private List<SectionDefinitionBean> sections;
    private Integer numberOfSections;
    private PlanTemplate planTemplate;
    private String templateName;
    private Long planTemplateId;
    private Contest contest;
    private Boolean updateExistingSections = false;
    private Boolean createNew = false;

    public ContestProposalTemplateWrapper(){
    }

    public ContestProposalTemplateWrapper(Long planTemplateId) throws Exception{
        this.planTemplate = PlanTemplateLocalServiceUtil.getPlanTemplate(planTemplateId);
        populateExistingProposalTemplateSections();
    }

    public ContestProposalTemplateWrapper(Contest contest) throws Exception{
        init(contest);
        populateExistingProposalTemplateSections();
    }

    public void setPlanTemplate(PlanTemplate planTemplate) {
        this.planTemplate = planTemplate;
    }

    public Long getPlanTemplateId(){return planTemplate.getId();}

    public void setPlanTemplateId(Long planTemplateId) {
        this.planTemplateId = planTemplateId;
        try {
            initPlanTemplate(planTemplateId);
        } catch (Exception e){
        }
    }

    public void init(Contest contest) throws Exception{
        this.contest = contest;
        this.planTemplate = ContestLocalServiceUtil.getPlanTemplate(contest);
    }

    public void initPlanTemplate(Long planTemplateId) throws Exception{
        this.planTemplate = PlanTemplateLocalServiceUtil.getPlanTemplate(planTemplateId);
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
        addDummySection();
    }

    private void addDummySection(){
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

    public Boolean getUpdateExistingSections() {
        return updateExistingSections;
    }

    public void setUpdateExistingTemplate(Boolean updateExistingSections) {
        this.updateExistingSections = updateExistingSections;
    }

    public PlanTemplate getPlanTemplate() {
        return planTemplate;
    }

    public List<SectionDefinitionBean> getSections() {
        return sections;
    }

    public void setSections(List<SectionDefinitionBean> sections) {
        this.sections = sections;
    }

    public Boolean getCreateNew() {
        return createNew;
    }

    public void setCreateNew(Boolean createNew) {
        this.createNew = createNew;
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

    public static PlanTemplate createNewTemplate() throws Exception{
        Long newPlanTemplateId = CounterLocalServiceUtil.increment(PlanTemplate.class.getName());
        PlanTemplate newPlanTemplate = PlanTemplateLocalServiceUtil.createPlanTemplate(newPlanTemplateId);
        newPlanTemplate.setName("New template");
        newPlanTemplate.persist();
        return newPlanTemplate;
    }

    private static boolean isPlanTemplateSectionUsedInTemplate(Long planSectionDefinitionId) throws Exception{
        List<PlanTemplateSection> planTemplateSections = PlanTemplateSectionLocalServiceUtil.findByPlanSectionDefinitionId(planSectionDefinitionId);
        return !planTemplateSections.isEmpty();
    }

    private static void deletePlanSectionDefinitionsOfProposalTemplateIfNotUsedInAnotherTemplate(PlanTemplate planTemplate) throws Exception{
        List<PlanSectionDefinition> planSectionDefinitions = PlanTemplateLocalServiceUtil.getSections(planTemplate);
        for(PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if(!isPlanTemplateSectionUsedInTemplate(planSectionDefinition.getId())) {
                PlanSectionDefinitionLocalServiceUtil.deletePlanSectionDefinition(planSectionDefinition);
            }
        }
    }

    private static void deletePlanTemplateSectionsOfProposalTemplate(Long planTemplateId) throws Exception{
        List<PlanTemplateSection> planTemplateSections =  PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(planTemplateId);
        for(PlanTemplateSection planTemplateSection : planTemplateSections){
            PlanTemplateSectionLocalServiceUtil.remove(planTemplateSection);
        }
    }

    public static void deleteTemplate(Long templateId) throws Exception{
        PlanTemplate planTemplate = PlanTemplateLocalServiceUtil.getPlanTemplate(templateId);
        deletePlanTemplateSectionsOfProposalTemplate(templateId);
        deletePlanSectionDefinitionsOfProposalTemplateIfNotUsedInAnotherTemplate(planTemplate);
        PlanTemplateLocalServiceUtil.deletePlanTemplate(templateId);
    }

    private void removeTemplateSection(){
        SectionDefinitionBean templateSectionDefinitionBean = new SectionDefinitionBean();
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            if(sectionBaseDefinition.isTemplateSection()) {
                templateSectionDefinitionBean = sectionBaseDefinition;
                break;
            }
        }
        sections.remove(templateSectionDefinitionBean);
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
        planSectionDefinition.setFocusAreaId(sectionDefinitionBean.getFocusAreaId());
    }

    public void persist() throws Exception{
        removeDeletedSections();
        removeTemplateSection();
        createSectionDefinitionsForNewSections();

        if(createNew) {
            duplicateExistingPlanTemplate();
        } else{
            // TODO write sectionId update
            deletePlanSectionDefinitionsOfProposalTemplateIfNotUsedInAnotherTemplate(planTemplate);
        }

        addSectionsToProposalTemplate();
        updatePlanTemplateTitle();
    }

    private void updatePlanTemplateTitle() throws Exception{
        if(planTemplate != null && templateName != null){
            planTemplate.setName(templateName);
            planTemplate.persist();
        }
    }

    private void duplicateExistingPlanTemplate() throws Exception{
        Long newPlanTemplateId = CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName());
        planTemplate.setId(newPlanTemplateId);
        PlanTemplate newPlanTemplate = PlanTemplateLocalServiceUtil.addPlanTemplate(planTemplate);
        planTemplateId = newPlanTemplate.getId();
    }

    private void duplicateExistingSectionsForPlanTemplate(PlanTemplate newPanTemplate) throws Exception{
        List<PlanSectionDefinition> planSectionDefinitions = PlanTemplateLocalServiceUtil.getSections(planTemplate);
        for(PlanSectionDefinition planSectionDefinition : planSectionDefinitions){
            Long newPlanSectionDefinitionId = CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName());
            planSectionDefinition.setId(newPlanSectionDefinitionId);
            PlanSectionDefinition
                    duplicatedPlanSectionDefinition = PlanSectionDefinitionLocalServiceUtil.addPlanSectionDefinition(planSectionDefinition);
            PlanTemplateLocalServiceUtil.addSection(newPanTemplate, duplicatedPlanSectionDefinition);
        }
    }


    public void updateNewProposalTemplateSections() throws Exception{
        removeDeletedSections();
        removeTemplateSection();
        createSectionDefinitionsForNewSections();

        if(planTemplate != null) {
            Long baseTemplateId = planTemplate.getBaseTemplateId();

            if (baseTemplateId == 0) {
                String contestTitle = contest != null ? contest.getContestShortName() : "";
                String baseProposalTemplateTitle = planTemplate.getName();
                String newProposalTemplateTitle;

                if (templateName.equals(baseProposalTemplateTitle)) {
                    newProposalTemplateTitle = "Base template - " + baseProposalTemplateTitle + "- adapted for contest: " + contestTitle;

                } else {
                    newProposalTemplateTitle = templateName;
                }

                createProposalTemplate(newProposalTemplateTitle, planTemplate.getId());
                updatePlanTemplateIdOfContest();
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
            updatePlanTemplateIdOfContest();
        }

        // TODO write sectionId update
        deletePlanSectionDefinitionsOfProposalTemplateIfNotUsedInAnotherTemplate(planTemplate);
        addSectionsToProposalTemplate();
    }


    private void updatePlanTemplateIdOfContest() throws Exception{
        if(contest != null) {
            Long newPlanTemplateId = planTemplate.getId();
            contest.setPlanTemplateId(newPlanTemplateId);
            contest.persist();
        }
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

        } catch(Exception e){
        }
        return isSectionDifferentFromDefinition;
    }

    private void updateSectionOrCreateNewIfPartOfBaseTemplate(SectionDefinitionBean sectionDefinitionBean) throws Exception{
        if(!updateExistingSections && planTemplate.getBaseTemplateId() == 0 || isSectionIdPartOfBaseProposalTemplate(sectionDefinitionBean)){
            createPlanSectionDefinitionFromSectionDefinitionBean(sectionDefinitionBean);
        } else {
            updatePlanSectionDefinition(sectionDefinitionBean);
        }
    }

    private boolean isSectionIdPartOfBaseProposalTemplateOld(SectionDefinitionBean sectionDefinitionBean) throws Exception{

        Long planTemplateId = new Long(planTemplate.getBaseTemplateId());
        Long planSectionId = new Long(sectionDefinitionBean.getSectionDefinitionId());
        // TODO check why class not found exception occurs,
        // for now this function is replaced by isSectionIdPartOfBaseProposalTemplate
        DynamicQuery queryCountSectionIdInBaseProposalTemplate =
                DynamicQueryFactoryUtil.forClass(PlanTemplateSection.class, PortletClassLoaderUtil.getClassLoader())
                        .add(PropertyFactoryUtil.forName("primaryKey.planTemplateId").eq(planTemplateId))
                        .add(PropertyFactoryUtil.forName("primaryKey.planSectionId").eq(planSectionId))
                        .setProjection(ProjectionFactoryUtil.count("primaryKey.planTemplateId"));

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

    public static List<LabelValue> getAllPlanTemplateSelectionItems(){
        List<LabelValue> selectItems = new ArrayList<>();
        try {
            for (PlanTemplate planTemplateItem : PlanTemplateLocalServiceUtil.getPlanTemplates(0, Integer.MAX_VALUE)) {
                selectItems.add(new LabelValue(planTemplateItem.getId(), planTemplateItem.getName()));
            }
        } catch (Exception e){
        }
        return selectItems;
    }

    public List<ContestWrapper> getContestsUsingSelectedTemplate(){
        List<ContestWrapper> contestsUsingSelectedTemplate = new ArrayList<>();
        List<Contest> contestsUsingSelectedTemplateList = new ArrayList<>();

        try {
            Long planTemplateId = planTemplate.getId();
            contestsUsingSelectedTemplateList = ContestLocalServiceUtil.getContestsByPlanTemplateId(planTemplateId);
        } catch (Exception e){
        }

        for(Contest contest : contestsUsingSelectedTemplateList) {
            contestsUsingSelectedTemplate.add(new org.xcolab.wrapper.ContestWrapper(contest));
        }

        return contestsUsingSelectedTemplate;
    }

}
