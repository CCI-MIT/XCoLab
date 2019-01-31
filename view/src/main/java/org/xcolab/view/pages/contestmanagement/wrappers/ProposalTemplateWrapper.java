package org.xcolab.view.pages.contestmanagement.wrappers;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.IProposalTemplateSection;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalTemplateSection;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
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
    private IProposalTemplate proposalTemplate;
    private String templateName;
    private Long proposalTemplateId;
    private Boolean updateExistingSections = false;
    private Boolean createNew = false;

    public ProposalTemplateWrapper() {
    }

    public ProposalTemplateWrapper(Long proposalTemplateId) {
        this.proposalTemplate = StaticContestContext.getProposalTemplateClient()
                .getProposalTemplate(proposalTemplateId);
        populateExistingProposalTemplateSections();
    }

    private void populateExistingProposalTemplateSections() {
        sections = new ArrayList<>();
        if (proposalTemplate != null) {
            for (ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition :
                    StaticContestContext.getProposalTemplateClient()
                    .getProposalTemplateSectionDefinitionByProposalTemplateId(proposalTemplate.getId(), null)) {
                if (!proposalTemplateSectionDefinition.isLocked()) {
                    sections.add(new SectionDefinitionWrapper(proposalTemplateSectionDefinition,
                            proposalTemplate.getId()));
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

    public static List<LabelValue> getAllProposalTemplateSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();

        for (IProposalTemplate proposalTemplateItem : StaticContestContext
                .getProposalTemplateClient().getProposalTemplates()) {
            selectItems.add(new LabelValue(proposalTemplateItem.getId(), proposalTemplateItem.getName()));
        }

        return selectItems;
    }

    public Long getProposalTemplateId() {
        if (proposalTemplate != null) {
            return proposalTemplate.getId();
        }
        return proposalTemplateId;
    }

    public void setProposalTemplateId(Long proposalTemplateId) {
        this.proposalTemplateId = proposalTemplateId;
        initProposalTemplate(proposalTemplateId);
    }

    private void initProposalTemplate(Long proposalTemplateId) {
        this.proposalTemplate = StaticContestContext.getProposalTemplateClient()
                .getProposalTemplate(proposalTemplateId);
    }

    public Boolean getUpdateExistingSections() {
        return updateExistingSections;
    }

    public void setUpdateExistingTemplate(Boolean updateExistingSections) {
        this.updateExistingSections = updateExistingSections;
    }

    public IProposalTemplate getProposalTemplate() {
        return proposalTemplate;
    }

    public void setProposalTemplate(IProposalTemplate proposalTemplate) {
        this.proposalTemplate = proposalTemplate;
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
        if (proposalTemplate != null && proposalTemplate.getName() != null) {
            this.templateName = proposalTemplate.getName();
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
            duplicateExistingProposalTemplate();
            updateExistingSections = false;
        }

        for (SectionDefinitionWrapper sectionBaseDefinition : sections) {
            sectionBaseDefinition.persist(createNew);
        }

        addSectionsToProposalTemplate();
        updateProposalTemplateTitle();
    }

    public void removeDeletedSections() {
        Set<Long> remainingProposalTemplateSectionDefinitionIds = new HashSet<>();
        List<SectionDefinitionWrapper> removedSectionDefinitions = new ArrayList<>();
        for (SectionDefinitionWrapper sectionBaseDefinition : sections) {
            if (StringUtils.isEmpty(sectionBaseDefinition.getTitle())
                    && !sectionBaseDefinition.isTemplateSection()) {
                removedSectionDefinitions.add(sectionBaseDefinition);
            } else {
                remainingProposalTemplateSectionDefinitionIds.add(sectionBaseDefinition.getId());
            }
        }

        List<ProposalTemplateSectionDefinitionWrapper> proposalTemplateSectionDefinitions =
                StaticContestContext.getProposalTemplateClient()
                .getProposalTemplateSectionDefinitionByProposalTemplateId(proposalTemplate.getId(), null);
        for (ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition : proposalTemplateSectionDefinitions) {
            if (!remainingProposalTemplateSectionDefinitionIds.contains(proposalTemplateSectionDefinition.getId())) {
                if (!ProposalTemplateLifecycleUtil
                        .isProposalTemplateSectionDefinitionUsedInOtherTemplate(
                                proposalTemplateSectionDefinition.getId(),
                                proposalTemplate.getId())) {
                    StaticContestContext.getProposalTemplateClient()
                            .deleteProposalTemplateSectionDefinition(proposalTemplateSectionDefinition.getId());
                }
                StaticContestContext.getProposalTemplateClient()
                        .deleteProposalTemplateSection(proposalTemplate.getId(),
                                proposalTemplateSectionDefinition.getId());
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

    private void duplicateExistingProposalTemplate() {

        proposalTemplate.setId(null);
        IProposalTemplate newProposalTemplate = StaticContestContext.getProposalTemplateClient()
                .createProposalTemplate(proposalTemplate);
        proposalTemplateId = newProposalTemplate.getId();
        proposalTemplate = newProposalTemplate;

        for (SectionDefinitionWrapper section : sections) {
            section.setId(null);
        }

    }

    private void addSectionsToProposalTemplate() {
        for (SectionDefinitionWrapper sectionDefinitionWrapper : sections) {
            createOrUpdateProposalTemplateSection(sectionDefinitionWrapper);
        }
    }

    private void createOrUpdateProposalTemplateSection(
            SectionDefinitionWrapper sectionDefinitionWrapper) {
        long proposalTemplateId = proposalTemplate.getId();
        long sectionDefinitionId = sectionDefinitionWrapper.getId();
        int weight = sectionDefinitionWrapper.getWeight();

        IProposalTemplateSection templateSection = StaticContestContext.getProposalTemplateClient()
                .getProposalTemplateSection(proposalTemplateId, sectionDefinitionId);

        if (templateSection != null) {
            templateSection.setWeight(weight);
            StaticContestContext.getProposalTemplateClient()
                    .updateProposalTemplateSection(templateSection);

        } else {
            IProposalTemplateSection pts = new ProposalTemplateSection();
            pts.setProposalTemplateId(proposalTemplateId);
            pts.setSectionDefinitionId(sectionDefinitionId);
            pts.setWeight(weight);

            StaticContestContext.getProposalTemplateClient().createProposalTemplateSection(pts);
        }
    }

    private void updateProposalTemplateTitle() {
        if (proposalTemplate != null && templateName != null) {
            proposalTemplate.setName(templateName);

            StaticContestContext.getProposalTemplateClient()
                    .updateProposalTemplate(proposalTemplate);
        }
    }

    public List<ContestWrapper> getContestsUsingTemplate() {

        Long proposalTemplateId = proposalTemplate.getId();
        List<ContestWrapper> contestsUsingSelectedTemplateList =
                StaticContestContext.getContestClient()
                        .getContestsByProposalTemplateId(proposalTemplateId);

        List<ContestWrapper> contestsUsingSelectedTemplate = new ArrayList<>();
        contestsUsingSelectedTemplate.addAll(contestsUsingSelectedTemplateList);

        return contestsUsingSelectedTemplate;
    }
}
