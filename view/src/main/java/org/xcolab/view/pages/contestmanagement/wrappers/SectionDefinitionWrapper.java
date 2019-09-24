package org.xcolab.view.pages.contestmanagement.wrappers;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.xcolab.client.contest.OntologyTermToFocusAreaMapper;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.IProposalTemplateSection;
import org.xcolab.client.contest.pojo.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.commons.IdListUtil;
import org.xcolab.view.util.entity.enums.OntologySpaceEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SectionDefinitionWrapper implements Serializable, Comparable {

    private Long id;
    private String type = "";
    private String additionalIds = "";
    private String allowedValues = "";
    private String title = "";
    private String defaultText = "";
    private String helpText = "";
    private int characterLimit = 200;
    private Long focusAreaId;
    private long level;
    private long pointType;
    private String pointPercentage;
    private String content = "";
    private boolean locked;
    private boolean deletable = true;
    private boolean isSectionNew;
    private boolean templateSection;
    private boolean contestIntegrationRelevance;
    private boolean includeInJudgingReport;
    private int weight;

    private List<Long> allowedContestTypeIds = new ArrayList<>();

    private List<Long> whatTermIds = new ArrayList<>();
    private List<Long> whereTermIds = new ArrayList<>();
    private List<Long> whoTermIds = new ArrayList<>();
    private List<Long> howTermIds = new ArrayList<>();

    public SectionDefinitionWrapper() {
    }

    public SectionDefinitionWrapper(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        initProposalTemplateSectionDefinition(proposalTemplateSectionDefinition);
    }

    private void initProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        this.id = proposalTemplateSectionDefinition.getId();
        this.type = proposalTemplateSectionDefinition.getType();
        this.title = proposalTemplateSectionDefinition.getTitle();
        this.defaultText = proposalTemplateSectionDefinition.getDefaultText();
        this.helpText = proposalTemplateSectionDefinition.getHelpText();
        this.characterLimit = proposalTemplateSectionDefinition.getCharacterLimit();
        this.focusAreaId = proposalTemplateSectionDefinition.getFocusAreaId();
        this.additionalIds = proposalTemplateSectionDefinition.getAdditionalIds();
        this.allowedValues = proposalTemplateSectionDefinition.getAllowedValues();
        this.locked = proposalTemplateSectionDefinition.isLocked();
        this.level = proposalTemplateSectionDefinition.getTier();
        this.contestIntegrationRelevance = proposalTemplateSectionDefinition.isContestIntegrationRelevance();
        this.allowedContestTypeIds =
                IdListUtil.getIdsFromString(proposalTemplateSectionDefinition.getAllowedContestTypeIds());


        IPointsDistributionConfiguration pdc = StaticProposalContext.getPointsClient()
                .getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionIdOrNull(id);
        if (pdc != null) {
            this.pointPercentage = Double.toString(pdc.getPercentage());
            this.pointType = pdc.getPointTypeId();
        } else {
            this.pointPercentage = "0";
            this.pointType = 0L;
        }
        /*
                PointsDistributionConfigurationClient
                .getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(id);
        if (pdc != null) {
            this.pointPercentage = Double.toString(pdc.getPercentage());
            this.pointType = pdc.getPointTypeId();
        } else {
            this.pointPercentage = "0";
            this.pointType = 0L;
        }
        */

        initOntologyTermIdsWithFocusAreaId();
    }

    private void initOntologyTermIdsWithFocusAreaId() {
        if (focusAreaId != null) {
            FocusAreaWrapper focusArea = StaticContestContext.getOntologyClient()
                    .getFocusArea(this.focusAreaId);

            OntologySpaceWrapper space = StaticContestContext.getOntologyClient()
                    .getOntologySpace(OntologySpaceEnum.WHAT.getSpaceId());
            List<OntologyTermWrapper> terms = StaticContestContext.getOntologyClient()
                            .getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
            this.whatTermIds = getIdsFromOntologyTerms(terms);

            space = StaticContestContext.getOntologyClient()
                    .getOntologySpace(OntologySpaceEnum.WHERE.getSpaceId());
            terms = StaticContestContext.getOntologyClient()
                    .getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
            this.whereTermIds = getIdsFromOntologyTerms(terms);

            space = StaticContestContext.getOntologyClient()
                    .getOntologySpace(OntologySpaceEnum.WHO.getSpaceId());
            terms = StaticContestContext.getOntologyClient()
                    .getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
            this.whoTermIds = getIdsFromOntologyTerms(terms);

            space = StaticContestContext.getOntologyClient()
                    .getOntologySpace(OntologySpaceEnum.HOW.getSpaceId());
            terms = StaticContestContext.getOntologyClient()
                    .getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
            this.howTermIds = getIdsFromOntologyTerms(terms);
        }
    }

    private List<Long> getIdsFromOntologyTerms(List<OntologyTermWrapper> ontologyTerms) {
        List<Long> ids = new ArrayList<>(ontologyTerms.size());
        for (OntologyTermWrapper term : ontologyTerms) {
            ids.add(term.getId());
        }

        return ids;
    }

    public SectionDefinitionWrapper(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition,
            Long proposalTemplateId) {

        initProposalTemplateSectionDefinition(proposalTemplateSectionDefinition);

        List<IProposalTemplateSection> proposalTemplateSections =
                StaticContestContext.getProposalTemplateClient()
                        .getProposalTemplateSectionsByProposalTemplateId(proposalTemplateId);

        for (IProposalTemplateSection proposalTemplateSection : proposalTemplateSections) {
            if (Objects.equals(
                    proposalTemplateSection.getSectionDefinitionId(), proposalTemplateSectionDefinition.getId())) {
                initProposalTemplateSection(proposalTemplateSection);
                break;
            }
        }

        initProposalTemplateSectionDefinition(proposalTemplateSectionDefinition);
    }

    private void initProposalTemplateSection(IProposalTemplateSection proposalTemplateSection) {
        this.weight = proposalTemplateSection.getWeight();
    }

    public SectionDefinitionWrapper(String title) {
        this.title = title;
    }

    public SectionDefinitionWrapper(String title, boolean deletable) {
        this.title = title;
        this.deletable = deletable;
    }

    public SectionDefinitionWrapper(String title, Integer characterLimit, String helpText,
            String content) {
        this.title = title;
        this.characterLimit = characterLimit;
        this.helpText = helpText;
        this.content = content;
    }

    public SectionDefinitionWrapper(String title, Integer characterLimit, String helpText,
            String content,
            boolean deletable) {
        this.title = title;
        this.characterLimit = characterLimit;
        this.helpText = helpText;
        this.content = content;
        this.deletable = deletable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public boolean isSectionNew() {
        return isSectionNew;
    }

    public void setSectionNew(boolean isSectionNew) {
        this.isSectionNew = isSectionNew;
    }

    public boolean isTemplateSection() {
        return templateSection;
    }

    public void setTemplateSection(boolean templateSection) {
        this.templateSection = templateSection;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void persist(boolean createNew) {
        ProposalTemplateSectionDefinitionWrapper psd;
        IPointsDistributionConfiguration pdc = null;
        if (id == null || createNew) {
            psd = new ProposalTemplateSectionDefinitionWrapper();

            populateProposalTemplateSectionDefinition(psd);

            psd = StaticContestContext.getProposalTemplateClient()
                    .createProposalTemplateSectionDefinition(psd);
            id = psd.getId();
        } else {
            psd = StaticContestContext.getProposalTemplateClient()
                    .getProposalTemplateSectionDefinition(id);
            pdc = StaticProposalContext.getPointsClient()
                    .getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionIdOrNull(id);

            populateProposalTemplateSectionDefinition(psd);

            if (pdc != null) {
                if (pointType == 0L) {
                    StaticProposalContext.getPointsClient()
                            .deletePointsDistributionConfiguration(pdc.getId());
                } else {
                    pdc.setPercentage(Double.valueOf(pointPercentage));
                    pdc.setPointTypeId(pointType);
                    pdc.setTargetProposalTemplateSectionDefinitionId(id);
                    StaticProposalContext.getPointsClient()
                            .updatePointsDistributionConfiguration(pdc);
            /*
                if (pdc != null) {
                    PointsDistributionConfigurationClient
                            .deletePointsDistributionConfiguration(pdc.getId());
                }
            } else {
                if (pdc != null) {
                    pdc.setPercentage(Double.valueOf(pointPercentage));
                    pdc.setPointTypeId(pointType);
                    pdc.setTargetProposalTemplateSectionDefinitionId(id);

                    PointsDistributionConfigurationClient.updatePointsDistributionConfiguration
                    (pdc);
                }
            */
                }

            }
        }
        if (pdc == null) {
            if (pointType > 0L) {
                pdc = new PointsDistributionConfiguration();
                pdc.setPercentage(Double.valueOf(pointPercentage));
                pdc.setPointTypeId(pointType);
                pdc.setTargetProposalTemplateSectionDefinitionId(id);
                pdc = StaticProposalContext.getPointsClient()
                        .createPointsDistributionConfiguration(pdc);
            }
        }

        StaticContestContext.getProposalTemplateClient()
                .updateProposalTemplateSectionDefinition(psd);
    }

    private void populateProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinitionWrapper psd) {
        psd.setType(this.getType());
        psd.setTitle(this.getTitle());
        psd.setDefaultText(this.getDefaultText());
        psd.setCharacterLimit(this.getCharacterLimit());
        psd.setHelpText(this.getHelpText());
        psd.setTier(this.getLevel());
        psd.setFocusAreaId(this.getFocusAreaId());
        psd.setAdditionalIds(this.getAdditionalIds());
        psd.setAllowedValues(this.getAllowedValues());
        psd.setAllowedContestTypeIds(
                IdListUtil.getStringFromIds(this.getAllowedContestTypeIds()));
        psd.setContestIntegrationRelevance(this.isContestIntegrationRelevance());
        psd.setIncludeInJudgingReport(this.isIncludeInJudgingReport());
        psd.setLocked(false);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCharacterLimit() {
        return characterLimit;
    }

    public void setCharacterLimit(int characterLimit) {
        this.characterLimit = characterLimit;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public boolean isContestIntegrationRelevance() {
        return contestIntegrationRelevance;
    }

    public void setContestIntegrationRelevance(boolean contestIntegrationRelevance) {
        this.contestIntegrationRelevance = contestIntegrationRelevance;
    }

    public boolean isIncludeInJudgingReport() {
        return includeInJudgingReport;
    }

    public void setIncludeInJudgingReport(boolean includeInJudgingReport) {
        this.includeInJudgingReport = includeInJudgingReport;
    }

    public Long getFocusAreaId() {
        if (ontologyTermsSet() && getFocusAreaViaOntologyTerms() != null) {
            focusAreaId = getFocusAreaViaOntologyTerms().getId();
        }
        return focusAreaId;
    }

    public void setFocusAreaId(Long focusAreaId) {
        this.focusAreaId = focusAreaId;
    }

    private boolean ontologyTermsSet() {
        return !(CollectionUtils.isEmpty(getWhatTermIds()) || CollectionUtils
                .isEmpty(getWhereTermIds())
                || CollectionUtils.isEmpty(getWhoTermIds()) || CollectionUtils
                .isEmpty(getHowTermIds()));
    }

    public List<Long> getWhatTermIds() {
        return whatTermIds;
    }

    public void setWhatTermIds(List<Long> whatTermIds) {
        this.whatTermIds = whatTermIds;
    }

    public List<Long> getWhereTermIds() {
        return whereTermIds;
    }

    public void setWhereTermIds(List<Long> whereTermIds) {
        this.whereTermIds = whereTermIds;
    }

    public List<Long> getWhoTermIds() {
        return whoTermIds;
    }

    public void setWhoTermIds(List<Long> whoTermIds) {
        this.whoTermIds = whoTermIds;
    }

    public List<Long> getHowTermIds() {
        return howTermIds;
    }

    public void setHowTermIds(List<Long> howTermIds) {
        this.howTermIds = howTermIds;
    }

    private FocusAreaWrapper getFocusAreaViaOntologyTerms() {
        List<OntologyTermWrapper> termsToBeMatched = getAllSelectedOntologyTerms();

        OntologyTermToFocusAreaMapper focusAreaMapper =
                new OntologyTermToFocusAreaMapper(termsToBeMatched);
        FocusAreaWrapper focusArea = focusAreaMapper.getFocusAreaMatchingTermsExactly();

        if (focusArea != null) {
            focusArea = createNewFocusAreaWithTerms(termsToBeMatched);
        }

        return focusArea;
    }

    private FocusAreaWrapper createNewFocusAreaWithTerms(List<OntologyTermWrapper> focusAreaOntologyTerms) {
        FocusAreaWrapper newFocusArea = new FocusAreaWrapper();

        newFocusArea.setName("created for proposalTemplateSectionDefinition '" + this.title + "'");

        newFocusArea = StaticContestContext.getOntologyClient().createFocusArea(newFocusArea);

        for (OntologyTermWrapper ontologyTerm : focusAreaOntologyTerms) {
            StaticContestContext.getOntologyClient()
                    .addOntologyTermsToFocusAreaByOntologyTermId(newFocusArea.getId(),
                            ontologyTerm.getId());
        }

        return newFocusArea;

    }    public Long getPointType() {
        return pointType;
    }

    private List<OntologyTermWrapper> getAllSelectedOntologyTerms() {
        List[] ontologyTermIdLists = {
                getWhatTermIds(), getWhereTermIds(), getWhoTermIds(), getHowTermIds()
        };

        List<OntologyTermWrapper> selectedOntologyTerms = new ArrayList<>();
        for (List<Long> ontologyTermIds : ontologyTermIdLists) {
            for (Long ontologyTermId : ontologyTermIds) {
                selectedOntologyTerms.add(StaticContestContext.getOntologyClient()
                        .getOntologyTerm(ontologyTermId));
            }
        }
        return selectedOntologyTerms;
    }

    public String getAdditionalIds() {
        return additionalIds;
    }    public void setPointType(Long pointType) {
        this.pointType = pointType;
    }

    public void setAdditionalIds(String additionalIds) {
        this.additionalIds = additionalIds;
    }

    public List<Long> getAllowedContestTypeIds() {
        return allowedContestTypeIds;
    }    public String getPointPercentage() {
        return pointPercentage;
    }

    public void setAllowedContestTypeIds(List<Long> allowedContestTypeIds) {
        this.allowedContestTypeIds = allowedContestTypeIds;
    }

    public String getAllowedValues() {
        return allowedValues;
    }

    public void setPointPercentage(String percentage) {
        this.pointPercentage = percentage;
    }

    public void setAllowedValues(String allowedValues) {
        this.allowedValues = allowedValues;
    }

    public boolean hasUpdates() {
        ProposalTemplateSectionDefinitionWrapper psd = StaticContestContext
                .getProposalTemplateClient().getProposalTemplateSectionDefinition(id);
        return !this.equals(new SectionDefinitionWrapper(psd));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(contestIntegrationRelevance)
                .append(includeInJudgingReport)
                .append(level)
                .append(pointType)
                .append(id)
                .append(characterLimit)
                .append(title)
                .append(type)
                .append(defaultText)
                .append(helpText)
                .append(allowedContestTypeIds)
                .append(pointPercentage)
                .append(additionalIds)
                .append(allowedValues).toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof SectionDefinitionWrapper) || o.hashCode() != this.hashCode()) {
            return false;
        }

        SectionDefinitionWrapper other = (SectionDefinitionWrapper) o;

        return new EqualsBuilder()
                .append(other.getAllowedContestTypeIds(), this.getAllowedContestTypeIds())
                .append(other.getTitle(), this.getTitle())
                .append(other.getType(), this.getType())
                .append(other.getDefaultText(), this.getDefaultText())
                .append(other.getHelpText(), this.getHelpText())
                .append(other.getAdditionalIds(), this.getAdditionalIds())
                .append(other.getCharacterLimit(), this.getCharacterLimit())
                .append(other.isContestIntegrationRelevance(), this.isContestIntegrationRelevance())
                .append(other.isIncludeInJudgingReport(), this.isIncludeInJudgingReport())
                .append(other.getFocusAreaId(), this.getFocusAreaId())
                .append(other.getLevel(), this.getLevel())
                .append(other.getPointType(), this.getPointType())
                .append(other.getPointPercentage(), this.getPointPercentage())
                .append(other.getAllowedValues(), this.getAllowedValues()).isEquals();
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof SectionDefinitionWrapper ){
            return this.weight - ((SectionDefinitionWrapper) o).getWeight();
        }
        return 0;
    }
}
