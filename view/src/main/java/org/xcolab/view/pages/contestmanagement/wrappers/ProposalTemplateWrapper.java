package org.xcolab.view.pages.contestmanagement.wrappers;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.client.contest.pojo.templates.PlanTemplateSection;
import org.xcolab.commons.html.LabelValue;
import org.xcolab.view.pages.contestmanagement.utils.ProposalTemplateLifecycleUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProposalTemplateWrapper {

    private List<SectionDefinitionWrapper> sections;
    private Integer numberOfSections;
    private PlanTemplate planTemplate;
    private String templateName;
    private Long planTemplateId;
    private Boolean updateExistingSections = false;
    private Boolean createNew = false;

    public ProposalTemplateWrapper() {
    }

    public ProposalTemplateWrapper(Long planTemplateId) {
        this.planTemplate = PlanTemplateClientUtil.getPlanTemplate(planTemplateId);
        populateExistingProposalTemplateSections();
    }

    private void populateExistingProposalTemplateSections() {
        sections = new ArrayList<>();
        if (planTemplate != null) {
            for (PlanSectionDefinition planSectionDefinition : PlanTemplateClientUtil
                    .getPlanSectionDefinitionByPlanTemplateId(planTemplate.getId(), null)) {
                if (!planSectionDefinition.getLocked()) {
                    sections.add(new SectionDefinitionWrapper(planSectionDefinition,
                            planTemplate.getId()));
                }
            }
        }
        Collections.sort(sections);
        addDummySection();
    }

    private void addDummySection() {
        SectionDefinitionWrapper sectionDefinitionWrapper = new SectionDefinitionWrapper();
        sectionDefinitionWrapper.setTemplateSection(true);
        sectionDefinitionWrapper.setTitle("");
        sectionDefinitionWrapper.setContent("");
        sectionDefinitionWrapper.setLevel(0L);
        sectionDefinitionWrapper.setPointType(0L);
        sectionDefinitionWrapper.setPointPercentage("0");
        sections.add(sectionDefinitionWrapper);
    }

    public static List<LabelValue> getAllPlanTemplateSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();

        for (PlanTemplate planTemplateItem : PlanTemplateClientUtil.getPlanTemplates()) {
            selectItems.add(new LabelValue(planTemplateItem.getId(), planTemplateItem.getName()));
        }

        return selectItems;
    }

    public Long getPlanTemplateId() {
        if (planTemplate != null) {
            return planTemplate.getId();
        }
        return planTemplateId;
    }

    public void setPlanTemplateId(Long planTemplateId) {
        this.planTemplateId = planTemplateId;
        initPlanTemplate(planTemplateId);
    }

    private void initPlanTemplate(Long planTemplateId) {
        this.planTemplate = PlanTemplateClientUtil.getPlanTemplate(planTemplateId);
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

    public void setPlanTemplate(PlanTemplate planTemplate) {
        this.planTemplate = planTemplate;
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
        if (sections != null) {
            return sections.size();
        }
        return numberOfSections;
    }

    public void setNumberOfSections(int numberOfSections) {
        this.numberOfSections = numberOfSections;
    }

    public String getTemplateName() {
        if (planTemplate != null && planTemplate.getName() != null) {
            this.templateName = planTemplate.getName();
        }
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void persist() {
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

    public void removeDeletedSections() {
        Set<Long> remainingPlanSectionDefinitionIds = new HashSet<>();
        List<SectionDefinitionWrapper> removedSectionDefinitions = new ArrayList<>();
        for (SectionDefinitionWrapper sectionBaseDefinition : sections) {
            if (StringUtils.isEmpty(sectionBaseDefinition.getTitle())
                    && !sectionBaseDefinition.isTemplateSection()) {
                removedSectionDefinitions.add(sectionBaseDefinition);
            } else {
                remainingPlanSectionDefinitionIds.add(sectionBaseDefinition.getId());
            }
        }


        List<PlanSectionDefinition> planSectionDefinitions = PlanTemplateClientUtil
                .getPlanSectionDefinitionByPlanTemplateId(planTemplate.getId(), null);
        for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if (!remainingPlanSectionDefinitionIds.contains(planSectionDefinition.getId())) {
                if (!ProposalTemplateLifecycleUtil
                        .isPlanSectionDefinitionUsedInOtherTemplate(
                                planSectionDefinition.getId(),
                                planTemplate.getId())) {
                    PlanTemplateClientUtil
                            .deletePlanSectionDefinition(planSectionDefinition.getId());
                }
                PlanTemplateClientUtil
                        .deletePlanTemplateSection(planTemplate.getId(),
                                planSectionDefinition.getId());
            }
        }

        sections.removeAll(removedSectionDefinitions);

    }

    private void removeTemplateSection() {
        SectionDefinitionWrapper templateSectionDefinitionWrapper = new SectionDefinitionWrapper();
        for (SectionDefinitionWrapper sectionBaseDefinition : sections) {
            if (sectionBaseDefinition.isTemplateSection()) {
                templateSectionDefinitionWrapper = sectionBaseDefinition;
                break;
            }
        }
        sections.remove(templateSectionDefinitionWrapper);
    }

    private void duplicateExistingPlanTemplate() {

        planTemplate.setId(null);
        PlanTemplate newPlanTemplate = PlanTemplateClientUtil.createPlanTemplate(planTemplate);
        planTemplateId = newPlanTemplate.getId();
        planTemplate = newPlanTemplate;

        for (SectionDefinitionWrapper section : sections) {
            section.setId(null);
        }

    }

    private void addSectionsToProposalTemplate() {
        for (SectionDefinitionWrapper sectionDefinitionWrapper : sections) {
            createOrUpdateIfExistsPlanTemplateSection(sectionDefinitionWrapper);
        }
    }

    private void createOrUpdateIfExistsPlanTemplateSection(
            SectionDefinitionWrapper sectionDefinitionWrapper) {
        boolean wasUpdated = false;
        Long planTemplateId = planTemplate.getId();
        Long sectionDefinitionId = sectionDefinitionWrapper.getId();
        Integer weight = sectionDefinitionWrapper.getWeight();

        List<PlanTemplateSection> planTemplateSectionsWithSectionDefinition =
                PlanTemplateClientUtil
                        .getPlanTemplateSectionByPlanSectionDefinitionId(
                                sectionDefinitionWrapper.getId());

        for (PlanTemplateSection planTemplateSection : planTemplateSectionsWithSectionDefinition) {
            if (planTemplateSection.getPlanTemplateId().longValue() == planTemplateId) {
                planTemplateSection.setWeight(weight);
                PlanTemplateClientUtil
                        .updatePlanTemplateSection(planTemplateSection);

                wasUpdated = true;
                break;
            }
        }

        if (!wasUpdated) {
            PlanTemplateSection pts = new PlanTemplateSection();
            pts.setPlanSectionId(sectionDefinitionId);
            pts.setPlanTemplateId(planTemplateId);
            pts.setWeight(weight);

            PlanTemplateClientUtil.createPlanTemplateSection(pts);
        }

    }

    private void updatePlanTemplateTitle() {
        if (planTemplate != null && templateName != null) {
            planTemplate.setName(templateName);

            PlanTemplateClientUtil.updatePlanTemplate(planTemplate);

        }
    }

    public List<Contest> getContestsUsingTemplate() {

        Long planTemplateId = planTemplate.getId();
        List<Contest> contestsUsingSelectedTemplateList =
                ContestClientUtil.getContestsByPlanTemplateId(planTemplateId);

        List<Contest> contestsUsingSelectedTemplate = new ArrayList<>();
        contestsUsingSelectedTemplate.addAll(contestsUsingSelectedTemplateList);

        return contestsUsingSelectedTemplate;
    }

}
