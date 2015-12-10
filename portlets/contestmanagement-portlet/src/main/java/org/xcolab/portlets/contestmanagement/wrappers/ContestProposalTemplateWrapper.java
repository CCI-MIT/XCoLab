package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.PlanTemplateSection;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.wrappers.BaseContestWrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Thomas on 2/18/2015.
 */
public class ContestProposalTemplateWrapper {

    private final static Log _log = LogFactoryUtil.getLog(ContestProposalTemplateWrapper.class);
    private List<SectionDefinitionWrapper> sections;
    private Integer numberOfSections;
    private PlanTemplate planTemplate;
    private String templateName;
    private Long planTemplateId;
    private Contest contest;
    private Boolean updateExistingSections = false;
    private Boolean createNew = false;

    public ContestProposalTemplateWrapper(){
    }

    public ContestProposalTemplateWrapper(Long planTemplateId) throws PortalException, SystemException {
        this.planTemplate = PlanTemplateLocalServiceUtil.getPlanTemplate(planTemplateId);
        populateExistingProposalTemplateSections();
    }

    public ContestProposalTemplateWrapper(Contest contest) throws PortalException, SystemException {
        init(contest);
        populateExistingProposalTemplateSections();
    }

    public void setPlanTemplate(PlanTemplate planTemplate) {
        this.planTemplate = planTemplate;
    }

    public Long getPlanTemplateId(){
        if(planTemplate != null) {
            return planTemplate.getId();
        }
        return planTemplateId;
    }

    public void setPlanTemplateId(Long planTemplateId) {
        this.planTemplateId = planTemplateId;
        try {
            initPlanTemplate(planTemplateId);
        } catch (SystemException | PortalException e){
            _log.warn("Failed to set plan template id: " + planTemplateId);
        }
    }

    public void init(Contest contest) throws PortalException, SystemException {
        this.contest = contest;
        this.planTemplate = ContestLocalServiceUtil.getPlanTemplate(contest);
    }

    public void initPlanTemplate(Long planTemplateId) throws PortalException, SystemException {
        this.planTemplate = PlanTemplateLocalServiceUtil.getPlanTemplate(planTemplateId);
    }

    private void populateExistingProposalTemplateSections() throws PortalException, SystemException {
        sections = new ArrayList<>();
        if (planTemplate != null) {
            for (PlanSectionDefinition planSectionDefinition : PlanTemplateLocalServiceUtil.getSections(planTemplate)) {
                if(!planSectionDefinition.isLocked()) {
                    sections.add(new SectionDefinitionWrapper(planSectionDefinition, planTemplate.getId()));
                }
            }
        }
        addDummySection();
    }

    private void addDummySection(){
        SectionDefinitionWrapper sectionDefinitionWrapper = new SectionDefinitionWrapper();
        sectionDefinitionWrapper.setTemplateSection(true);
        sectionDefinitionWrapper.setTitle("");
        sectionDefinitionWrapper.setContent("");
        sectionDefinitionWrapper.setLevel(0L);
        sectionDefinitionWrapper.setPointType(0L);
        sectionDefinitionWrapper.setPointPercentage("0");
        sections.add(sectionDefinitionWrapper);
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

    public List<SectionDefinitionWrapper> getSections() {
        return sections;
    }

    public void setSections(List<SectionDefinitionWrapper> sections) {
        this.sections = sections;
    }

    public Boolean getCreateNew() {
        return createNew;
    }

    public void setCreateNew(Boolean createNew) {
        this.createNew = createNew;
    }

    public int getNumberOfSections() {
        if(sections != null) {
            return sections.size();
        }
        return numberOfSections;
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

    public static PlanTemplate createNewTemplate() throws SystemException {
        Long newPlanTemplateId = CounterLocalServiceUtil.increment(PlanTemplate.class.getName());
        PlanTemplate newPlanTemplate = PlanTemplateLocalServiceUtil.createPlanTemplate(newPlanTemplateId);
        newPlanTemplate.setName("New template");
        newPlanTemplate.persist();
        return newPlanTemplate;
    }

    private void deletePlanSectionDefinitionsOfProposalTemplateIfNotUsedInAnotherTemplate() throws PortalException, SystemException {
        deletePlanSectionDefinitionsOfProposalTemplateIfNotUsedInAnotherTemplate(planTemplate);
    }

    private static void deletePlanSectionDefinitionsOfProposalTemplateIfNotUsedInAnotherTemplate(PlanTemplate planTemplate)
            throws SystemException, PortalException {
        List<PlanSectionDefinition> planSectionDefinitions = PlanTemplateLocalServiceUtil.getSections(planTemplate);
        for(PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if(!isPlanSectionDefinitionUsedInOtherTemplate(planSectionDefinition.getId(), planTemplate.getId())) {
                PlanSectionDefinitionLocalServiceUtil.deletePlanSectionDefinition(planSectionDefinition);
            }
        }
    }

    private static boolean isPlanSectionDefinitionUsedInOtherTemplate(Long planSectionDefinitionId, Long planTemplateId) throws SystemException {
        List<PlanTemplateSection> planTemplateSections = PlanTemplateSectionLocalServiceUtil.findByPlanSectionDefinitionId(planSectionDefinitionId);
        return !(planTemplateSections.size() == 1 && planTemplateSections.get(0).getPlanTemplateId() == planTemplateId) && !planTemplateSections.isEmpty();
    }

    private static void deletePlanTemplateSectionsOfProposalTemplate(Long planTemplateId) throws SystemException {
        List<PlanTemplateSection> planTemplateSections =  PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(planTemplateId);
        for(PlanTemplateSection planTemplateSection : planTemplateSections){
            PlanTemplateSectionLocalServiceUtil.remove(planTemplateSection);
        }
    }

    public static void deleteTemplate(Long templateId) throws PortalException, SystemException {
        PlanTemplate planTemplate = PlanTemplateLocalServiceUtil.getPlanTemplate(templateId);
        deletePlanTemplateSectionsOfProposalTemplate(templateId);
        deletePlanSectionDefinitionsOfProposalTemplateIfNotUsedInAnotherTemplate(planTemplate);
        PlanTemplateLocalServiceUtil.deletePlanTemplate(templateId);
    }

    private void removeTemplateSection(){
        SectionDefinitionWrapper templateSectionDefinitionWrapper = new SectionDefinitionWrapper();
        for(SectionDefinitionWrapper sectionBaseDefinition : sections ){
            if(sectionBaseDefinition.isTemplateSection()) {
                templateSectionDefinitionWrapper = sectionBaseDefinition;
                break;
            }
        }
        sections.remove(templateSectionDefinitionWrapper);
    }

    public void removeDeletedSections() throws PortalException, SystemException {
        Set<Long> remainingPlanSectionDefinitionIds = new HashSet<>();
        List<SectionDefinitionWrapper> removedSectionDefinitions = new ArrayList<>();
        for(SectionDefinitionWrapper sectionBaseDefinition : sections ){
            if((sectionBaseDefinition.getTitle() == null || sectionBaseDefinition.getTitle().isEmpty())
                    && !sectionBaseDefinition.isTemplateSection()){
                removedSectionDefinitions.add(sectionBaseDefinition);
            } else{
                remainingPlanSectionDefinitionIds.add(sectionBaseDefinition.getId());
            }
        }

        List<PlanSectionDefinition> planSectionDefinitions = PlanTemplateLocalServiceUtil.getSections(planTemplate);
        for(PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if(!remainingPlanSectionDefinitionIds.contains(planSectionDefinition.getId())){
                if (!isPlanSectionDefinitionUsedInOtherTemplate(planSectionDefinition.getId(), planTemplate.getId())) {
                    PlanSectionDefinitionLocalServiceUtil.deletePlanSectionDefinition(planSectionDefinition);
                }
                PlanTemplateLocalServiceUtil.removeSection(planTemplate, planSectionDefinition);
            }
        }

        for(SectionDefinitionWrapper removedSectionDefinition : removedSectionDefinitions) {
             sections.remove(removedSectionDefinition);
        }
    }

    public void persist() throws SystemException, PortalException {
        removeDeletedSections();
        removeTemplateSection();

        if (createNew) {
            duplicateExistingPlanTemplate();
            updateExistingSections = false;
        }

        for (SectionDefinitionWrapper sectionBaseDefinition : sections) {
            sectionBaseDefinition.persist(createNew);
        }

        addSectionsToProposalTemplate();
        updatePlanTemplateTitle();
    }

    private void updatePlanTemplateTitle() throws SystemException {
        if(planTemplate != null && templateName != null){
            planTemplate.setName(templateName);
            planTemplate.persist();
        }
    }

    private void duplicateExistingPlanTemplate() throws SystemException, PortalException {
        Long newPlanTemplateId = CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName());
        planTemplate.setId(newPlanTemplateId);
        PlanTemplate newPlanTemplate = PlanTemplateLocalServiceUtil.addPlanTemplate(planTemplate);
        planTemplateId = newPlanTemplate.getId();

        for (SectionDefinitionWrapper section : sections) {
            section.setId(null);
        }
    }

    public void updateNewProposalTemplateSections() throws SystemException, PortalException {
        removeDeletedSections();
        removeTemplateSection();

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
        deletePlanSectionDefinitionsOfProposalTemplateIfNotUsedInAnotherTemplate();
        addSectionsToProposalTemplate();
    }

    private void updatePlanTemplateIdOfContest() throws SystemException {
        if(contest != null) {
            Long newPlanTemplateId = planTemplate.getId();
            contest.setPlanTemplateId(newPlanTemplateId);
            contest.persist();
        }
    }
    private void createProposalTemplate(String title, Long baseTemplateId) throws SystemException {
        planTemplate = PlanTemplateLocalServiceUtil.createPlanTemplate(CounterLocalServiceUtil.increment(PlanTemplate.class.getName()));
        planTemplate.setName(title);
        planTemplate.setBaseTemplateId(baseTemplateId);
        planTemplate.persist();
    }

    private void addSectionsToProposalTemplate() throws PortalException, SystemException {
        for(SectionDefinitionWrapper sectionDefinitionWrapper : sections) {
            createOrUpdateIfExistsPlanTemplateSection(sectionDefinitionWrapper);
        }
    }

    private void createOrUpdateIfExistsPlanTemplateSection(SectionDefinitionWrapper sectionDefinitionWrapper) throws SystemException {

        boolean wasUpdated = false;
        Long planTemplateId = planTemplate.getId();
        Long sectionDefinitionId = sectionDefinitionWrapper.getId();
        Integer weight = sectionDefinitionWrapper.getWeight();

        List<PlanTemplateSection> planTemplateSectionsWithSectionDefinition =
                PlanTemplateSectionLocalServiceUtil.findByPlanSectionDefinitionId(sectionDefinitionWrapper.getId());

        for(PlanTemplateSection planTemplateSection : planTemplateSectionsWithSectionDefinition){
            if(planTemplateSection.getPlanTemplateId() == planTemplateId){
                planTemplateSection.setWeight(weight);
                planTemplateSection.persist();
                PlanTemplateSectionLocalServiceUtil.updatePlanTemplateSection(planTemplateSection);
                wasUpdated = true;
                break;
            }
        }

        if(!wasUpdated) {
            PlanTemplateSection planTemplateSection =
                    PlanTemplateSectionLocalServiceUtil.addPlanTemplateSection(planTemplateId, sectionDefinitionId, weight);
            planTemplateSection.persist(); // TODO check whether necessary
        }

    }

    public static List<LabelValue> getAllPlanTemplateSelectionItems(){
        List<LabelValue> selectItems = new ArrayList<>();
        try {
            for (PlanTemplate planTemplateItem : PlanTemplateLocalServiceUtil.getPlanTemplates(0, Integer.MAX_VALUE)) {
                selectItems.add(new LabelValue(planTemplateItem.getId(), planTemplateItem.getName()));
            }
        } catch (SystemException ignored){ }
        return selectItems;
    }

    public List<BaseContestWrapper> getContestsUsingSelectedTemplate(){
        List<BaseContestWrapper> contestsUsingSelectedTemplate = new ArrayList<>();
        List<Contest> contestsUsingSelectedTemplateList = new ArrayList<>();

        try {
            Long planTemplateId = planTemplate.getId();
            contestsUsingSelectedTemplateList = ContestLocalServiceUtil.getContestsByPlanTemplateId(planTemplateId);
        } catch (SystemException ignored){ }

        for(Contest contest : contestsUsingSelectedTemplateList) {
            contestsUsingSelectedTemplate.add(new BaseContestWrapper(contest));
        }

        return contestsUsingSelectedTemplate;
    }

}
