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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import org.xcolab.portlets.contestmanagement.beans.SectionDefinitionBean;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.utils.IdListUtil;
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

    private void populateProposalTemplateSectionsFromPlanTemplateId(Long id) throws PortalException, SystemException {
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
        SectionDefinitionBean templateSectionDefinitionBean = new SectionDefinitionBean();
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            if(sectionBaseDefinition.isTemplateSection()) {
                templateSectionDefinitionBean = sectionBaseDefinition;
                break;
            }
        }
        sections.remove(templateSectionDefinitionBean);
    }

    public void removeDeletedSections() throws PortalException, SystemException {
        Set<Long> remainingPlanSectionDefinitionIds = new HashSet<>();
        List<SectionDefinitionBean> removedSectionDefinitions = new ArrayList<>();
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            if((sectionBaseDefinition.getTitle() == null || sectionBaseDefinition.getTitle().isEmpty())
                    && !sectionBaseDefinition.isTemplateSection()){
                removedSectionDefinitions.add(sectionBaseDefinition);
            } else{
                remainingPlanSectionDefinitionIds.add(sectionBaseDefinition.getSectionDefinitionId());
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

        for(SectionDefinitionBean removedSectionDefinition : removedSectionDefinitions) {
             sections.remove(removedSectionDefinition);
        }
    }

    public void createSectionDefinitionsForNewSections() throws SystemException {
        for(SectionDefinitionBean sectionBaseDefinition : sections ){
            if(sectionBaseDefinition.getSectionDefinitionId() == null){
                createPlanSectionDefinitionFromSectionDefinitionBean(sectionBaseDefinition);
            }
        }
    }

    private void createPlanSectionDefinitionFromSectionDefinitionBean(SectionDefinitionBean sectionBaseDefinition) throws SystemException {
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
        planSectionDefinition.setAdditionalIds(sectionDefinitionBean.getAdditionalIds());
        planSectionDefinition.setAllowedContestTypeIds(
                IdListUtil.getStringFromIds(sectionDefinitionBean.getAllowedContestTypeIds()));
        planSectionDefinition.setContestIntegrationRelevance(sectionDefinitionBean.isContestIntegrationRelevance());
    }

    public void persist() throws SystemException, PortalException {
        removeDeletedSections();
        removeTemplateSection();
        createSectionDefinitionsForNewSections();

        if(createNew) {
            duplicateExistingPlanTemplate();
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

    private void duplicateExistingPlanTemplate() throws SystemException {
        Long newPlanTemplateId = CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName());
        planTemplate.setId(newPlanTemplateId);
        PlanTemplate newPlanTemplate = PlanTemplateLocalServiceUtil.addPlanTemplate(planTemplate);
        planTemplateId = newPlanTemplate.getId();
    }

    private void duplicateExistingSectionsForPlanTemplate(PlanTemplate newPanTemplate) throws PortalException, SystemException {
        List<PlanSectionDefinition> planSectionDefinitions = PlanTemplateLocalServiceUtil.getSections(planTemplate);
        for(PlanSectionDefinition planSectionDefinition : planSectionDefinitions){
            Long newPlanSectionDefinitionId = CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName());
            planSectionDefinition.setId(newPlanSectionDefinitionId);
            PlanSectionDefinition
                    duplicatedPlanSectionDefinition = PlanSectionDefinitionLocalServiceUtil.addPlanSectionDefinition(planSectionDefinition);
            PlanTemplateLocalServiceUtil.addSection(newPanTemplate, duplicatedPlanSectionDefinition);
        }
    }


    public void updateNewProposalTemplateSections() throws SystemException, PortalException {
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
        for(SectionDefinitionBean sectionDefinitionBean: sections) {
            if(isSectionDifferentFromItsDefinition(sectionDefinitionBean)){
                updateSectionOrCreateNewIfPartOfBaseTemplate(sectionDefinitionBean);
            }
            createOrUpdateIfExistsPlanTemplateSection(sectionDefinitionBean);
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
                    planSectionDefinition.getCharacterLimit() == sectionDefinitionBean.getCharacterLimit() &&
                    planSectionDefinition.getContestIntegrationRelevance() == sectionDefinitionBean.isContestIntegrationRelevance() &&
                    planSectionDefinition.getFocusAreaId() == sectionDefinitionBean.getFocusAreaId() &&
                    planSectionDefinition.getAllowedContestTypeIds().equals(IdListUtil.getStringFromIds(sectionDefinitionBean.getAllowedContestTypeIds())) &&
                    planSectionDefinition.getAdditionalIds().equalsIgnoreCase(sectionDefinitionBean.getAdditionalIds()) &&
                    planSectionDefinition.getTier() == sectionDefinitionBean.getLevel());
        } catch(PortalException | SystemException ignored){ }
        return isSectionDifferentFromDefinition;
    }

    private void updateSectionOrCreateNewIfPartOfBaseTemplate(SectionDefinitionBean sectionDefinitionBean) throws SystemException, PortalException {
        if(!updateExistingSections && planTemplate.getBaseTemplateId() == 0 || isSectionIdPartOfBaseProposalTemplate(sectionDefinitionBean)){
            createPlanSectionDefinitionFromSectionDefinitionBean(sectionDefinitionBean);
        } else {
            updatePlanSectionDefinition(sectionDefinitionBean);
        }
    }

    private boolean isSectionIdPartOfBaseProposalTemplateOld(SectionDefinitionBean sectionDefinitionBean) throws SystemException {

        Long planTemplateId = planTemplate.getBaseTemplateId();
        Long planSectionId = sectionDefinitionBean.getSectionDefinitionId();
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

    private boolean isSectionIdPartOfBaseProposalTemplate(SectionDefinitionBean sectionDefinitionBean) throws SystemException {
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

    private void updatePlanSectionDefinition(SectionDefinitionBean sectionDefinitionBean) throws PortalException, SystemException {

        PlanSectionDefinition planSectionDefinition =
                PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(sectionDefinitionBean.getSectionDefinitionId());

        setPlanSectionDefinitionFromSectionDefinitionBean(planSectionDefinition, sectionDefinitionBean);
        planSectionDefinition.persist();
        PlanSectionDefinitionLocalServiceUtil.updatePlanSectionDefinition(planSectionDefinition);
    }

    private void createOrUpdateIfExistsPlanTemplateSection(SectionDefinitionBean sectionDefinitionBean) throws SystemException {

        boolean wasUpdated = false;
        Long planTemplateId = planTemplate.getId();
        Long sectionId = sectionDefinitionBean.getSectionDefinitionId();
        Integer weight = sectionDefinitionBean.getWeight();

        List<PlanTemplateSection> planTemplateSectionsWithSectionDefinition =
                PlanTemplateSectionLocalServiceUtil.findByPlanSectionDefinitionId(sectionDefinitionBean.getSectionDefinitionId());

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
                    PlanTemplateSectionLocalServiceUtil.addPlanTemplateSection(planTemplateId, sectionId, weight);
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
