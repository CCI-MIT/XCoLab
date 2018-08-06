package org.xcolab.view.pages.contestmanagement.wrappers;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ProposalTemplateClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDefinition;
import org.xcolab.client.contest.pojo.templates.ProposalTemplate;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSection;
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
    private ProposalTemplate planTemplate;
    private String templateName;
    private Long planTemplateId;
    private Boolean updateExistingSections = false;
    private Boolean createNew = false;

    public ProposalTemplateWrapper() {
    }

    public ProposalTemplateWrapper(Long planTemplateId) {
        this.planTemplate = ProposalTemplateClientUtil.getPlanTemplate(planTemplateId);
        populateExistingProposalTemplateSections();
    }

    private void populateExistingProposalTemplateSections() {
        sections = new ArrayList<>();
        if (planTemplate != null) {
            for (ProposalTemplateSectionDefinition planSectionDefinition : ProposalTemplateClientUtil
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

        for (ProposalTemplate planTemplateItem : ProposalTemplateClientUtil.getPlanTemplates()) {
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
        this.planTemplate = ProposalTemplateClientUtil.getPlanTemplate(planTemplateId);
    }

    public Boolean getUpdateExistingSections() {
        return updateExistingSections;
    }

    public void setUpdateExistingTemplate(Boolean updateExistingSections) {
        this.updateExistingSections = updateExistingSections;
    }

    public ProposalTemplate getPlanTemplate() {
        return planTemplate;
    }

    public void setPlanTemplate(ProposalTemplate planTemplate) {
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


        List<ProposalTemplateSectionDefinition> planSectionDefinitions = ProposalTemplateClientUtil
                .getPlanSectionDefinitionByPlanTemplateId(planTemplate.getId(), null);
        for (ProposalTemplateSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if (!remainingPlanSectionDefinitionIds.contains(planSectionDefinition.getId())) {
                if (!ProposalTemplateLifecycleUtil
                        .isPlanSectionDefinitionUsedInOtherTemplate(
                                planSectionDefinition.getId(),
                                planTemplate.getId())) {
                    ProposalTemplateClientUtil
                            .deletePlanSectionDefinition(planSectionDefinition.getId());
                }
                ProposalTemplateClientUtil
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
        ProposalTemplate newPlanTemplate = ProposalTemplateClientUtil.createPlanTemplate(planTemplate);
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

        List<ProposalTemplateSection> planTemplateSectionsWithSectionDefinition =
                ProposalTemplateClientUtil
                        .getPlanTemplateSectionByPlanSectionDefinitionId(
                                sectionDefinitionWrapper.getId());

        for (ProposalTemplateSection planTemplateSection : planTemplateSectionsWithSectionDefinition) {
            if (planTemplateSection.getId().longValue() == planTemplateId) {
                planTemplateSection.setWeight(weight);
                ProposalTemplateClientUtil
                        .updatePlanTemplateSection(planTemplateSection);

                wasUpdated = true;
                break;
            }
        }

        if (!wasUpdated) {
            ProposalTemplateSection pts = new ProposalTemplateSection();
            pts.setSectionDefinitionId(sectionDefinitionId);
            pts.setId(planTemplateId);
            pts.setWeight(weight);

            ProposalTemplateClientUtil.createPlanTemplateSection(pts);
        }

    }

    private void updatePlanTemplateTitle() {
        if (planTemplate != null && templateName != null) {
            planTemplate.setName(templateName);

            ProposalTemplateClientUtil.updatePlanTemplate(planTemplate);

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
