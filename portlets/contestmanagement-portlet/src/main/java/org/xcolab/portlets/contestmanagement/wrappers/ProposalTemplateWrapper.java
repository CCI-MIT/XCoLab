package org.xcolab.portlets.contestmanagement.wrappers;


import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.PlanTemplateClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.PlanTemplate;
import org.xcolab.client.contest.pojo.PlanTemplateSection;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.ProposalTemplateLifecycleUtil;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.wrappers.BaseContestWrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Thomas on 2/18/2015.
 */
public class ProposalTemplateWrapper {

    private final static Log _log = LogFactoryUtil.getLog(ProposalTemplateWrapper.class);
    private List<SectionDefinitionWrapper> sections;
    private Integer numberOfSections;
    private PlanTemplate planTemplate;
    private String templateName;
    private Long planTemplateId;
    private Boolean updateExistingSections = false;
    private Boolean createNew = false;

    public ProposalTemplateWrapper() {
    }

    public ProposalTemplateWrapper(Long planTemplateId) throws PortalException, SystemException {
        this.planTemplate = PlanTemplateClient.getPlanTemplate(planTemplateId);
        populateExistingProposalTemplateSections();
    }

    public void setPlanTemplate(PlanTemplate planTemplate) {
        this.planTemplate = planTemplate;
    }

    public Long getPlanTemplateId() {
        if (planTemplate != null) {
            return planTemplate.getId_();
        }
        return planTemplateId;
    }

    public void setPlanTemplateId(Long planTemplateId) {
        this.planTemplateId = planTemplateId;
        try {
            initPlanTemplate(planTemplateId);
        } catch (SystemException | PortalException e) {
            _log.warn("Failed to set plan template id: " + planTemplateId);
        }
    }

    public void initPlanTemplate(Long planTemplateId) throws PortalException, SystemException {
        this.planTemplate = PlanTemplateClient.getPlanTemplate(planTemplateId);
    }

    private void populateExistingProposalTemplateSections() throws PortalException, SystemException {
        sections = new ArrayList<>();
        if (planTemplate != null) {
            for (PlanSectionDefinition planSectionDefinition : PlanTemplateClient.getPlanSectionDefinitionByPlanTemplateId(planTemplate.getId_(), null)) {
                if (!planSectionDefinition.getLocked()) {
                    sections.add(new SectionDefinitionWrapper(planSectionDefinition, planTemplate.getId_()));
                }
            }
        }
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
            if ((sectionBaseDefinition.getTitle() == null || sectionBaseDefinition.getTitle().isEmpty())
                    && !sectionBaseDefinition.isTemplateSection()) {
                removedSectionDefinitions.add(sectionBaseDefinition);
            } else {
                remainingPlanSectionDefinitionIds.add(sectionBaseDefinition.getId());
            }
        }


            List<PlanSectionDefinition> planSectionDefinitions = PlanTemplateClient
                    .getPlanSectionDefinitionByPlanTemplateId(planTemplate.getId_(), null);
            for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
                if (!remainingPlanSectionDefinitionIds.contains(planSectionDefinition.getId_())) {
                    if (!ProposalTemplateLifecycleUtil
                            .isPlanSectionDefinitionUsedInOtherTemplate(
                                    planSectionDefinition.getId_(),
                                    planTemplate.getId_())) {
                        PlanTemplateClient
                                .deletePlanSectionDefinition(planSectionDefinition.getId_());
                    }
                    PlanTemplateClient.deletePlanTemplateSection(planTemplate.getId_(), planSectionDefinition.getId_());
                }
            }

            for (SectionDefinitionWrapper removedSectionDefinition : removedSectionDefinitions) {
                sections.remove(removedSectionDefinition);
            }

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

        PlanTemplate newPlanTemplate = PlanTemplateClient.createPlanTemplate(planTemplate);
        planTemplateId = newPlanTemplate.getId_();

        for (SectionDefinitionWrapper section : sections) {
            section.setId(null);
        }

    }

    private void addSectionsToProposalTemplate() {
        for (SectionDefinitionWrapper sectionDefinitionWrapper : sections) {
            createOrUpdateIfExistsPlanTemplateSection(sectionDefinitionWrapper);
        }
    }

    private void updatePlanTemplateTitle() {
        if (planTemplate != null && templateName != null) {
            planTemplate.setName(templateName);

            PlanTemplateClient.updatePlanTemplate(planTemplate);


        }
    }

    private void createOrUpdateIfExistsPlanTemplateSection(
            SectionDefinitionWrapper sectionDefinitionWrapper) {
        try {
            boolean wasUpdated = false;
            Long planTemplateId = planTemplate.getId_();
            Long sectionDefinitionId = sectionDefinitionWrapper.getId();
            Integer weight = sectionDefinitionWrapper.getWeight();

            List<PlanTemplateSection> planTemplateSectionsWithSectionDefinition =
                    PlanTemplateClient
                            .getPlanTemplateSectionByPlanSectionDefinitionId(sectionDefinitionWrapper.getId());

            for (PlanTemplateSection planTemplateSection : planTemplateSectionsWithSectionDefinition) {
                if (planTemplateSection.getPlanTemplateId() == planTemplateId) {
                    planTemplateSection.setWeight(weight);
                    PlanTemplateClient
                            .updatePlanTemplateSection(planTemplateSection);

                    wasUpdated = true;
                    break;
                }
            }

            if (!wasUpdated) {
                PlanTemplateSectionLocalServiceUtil
                        .addPlanTemplateSection(planTemplateId, sectionDefinitionId, weight);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public static List<LabelValue> getAllPlanTemplateSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();

            for (PlanTemplate planTemplateItem : PlanTemplateClient.getPlanTemplates()) {
                selectItems.add(new LabelValue(planTemplateItem.getId_(), planTemplateItem.getName()));
            }

        return selectItems;
    }

    public List<BaseContestWrapper> getContestsUsingTemplate() {
        List<BaseContestWrapper> contestsUsingSelectedTemplate = new ArrayList<>();
        List<Contest> contestsUsingSelectedTemplateList = new ArrayList<>();

        Long planTemplateId = planTemplate.getId_();
        contestsUsingSelectedTemplateList = ContestClient.getContestsByPlanTemplateId(planTemplateId);


        for (Contest contest : contestsUsingSelectedTemplateList) {
            contestsUsingSelectedTemplate.add(new BaseContestWrapper(contest));
        }

        return contestsUsingSelectedTemplate;
    }

}
