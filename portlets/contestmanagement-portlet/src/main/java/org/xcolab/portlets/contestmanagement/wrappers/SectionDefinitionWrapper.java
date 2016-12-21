package org.xcolab.portlets.contestmanagement.wrappers;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.templates.PlanTemplateSection;
import org.xcolab.client.proposals.PointsClientUtil;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfiguration;
import org.xcolab.entity.utils.enums.OntologySpaceEnum;
import org.xcolab.util.IdListUtil;
import org.xcolab.client.contest.OntologyTermToFocusAreaMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SectionDefinitionWrapper implements Serializable {

    private Long id;
    private String type = "";
    private String additionalIds = "";
    private String allowedValues = "";
    private String title = "";
    private String defaultText = "";
    private String helpText = "";
    private int characterLimit = 200;
    private long focusAreaId;
    private long level;
    private long pointType;
    private String pointPercentage;
    private String content = "";
    private boolean locked;
    private boolean deletable = true;
    private boolean isSectionNew;
    private boolean templateSection;
    private boolean contestIntegrationRelevance;
    private int weight;

    private List<Long> allowedContestTypeIds = new ArrayList<>();

    private List<Long> whatTermIds = new ArrayList<>();
    private List<Long> whereTermIds = new ArrayList<>();
    private List<Long> whoTermIds = new ArrayList<>();
    private List<Long> howTermIds = new ArrayList<>();

    public SectionDefinitionWrapper() {
    }

    public SectionDefinitionWrapper(PlanSectionDefinition planSectionDefinition) {
        initPlanSectionDefinition(planSectionDefinition);
    }

    public SectionDefinitionWrapper(PlanSectionDefinition planSectionDefinition,
                                    Long planTemplateId) {

        initPlanSectionDefinition(planSectionDefinition);

        List<PlanTemplateSection> planTemplateSections =
                PlanTemplateClientUtil.getPlanTemplateSectionByPlanTemplateId(planTemplateId);

        // TODO very inefficient, add finder to service layer
        for (PlanTemplateSection planTemplateSection : planTemplateSections) {
            if (Objects.equals(
                    planTemplateSection.getPlanSectionId(), planSectionDefinition.getId_())) {
                initPlanTemplateSection(planTemplateSection);
                break;
            }
        }

        initPlanSectionDefinition(planSectionDefinition);

    }

    private void initPlanTemplateSection(PlanTemplateSection planTemplateSection) {
        this.weight = planTemplateSection.getWeight();
    }

    private void initPlanSectionDefinition(PlanSectionDefinition planSectionDefinition) {
        this.id = planSectionDefinition.getId_();
        this.type = planSectionDefinition.getType_();
        this.title = planSectionDefinition.getTitle();
        this.defaultText = planSectionDefinition.getDefaultText();
        this.helpText = planSectionDefinition.getHelpText();
        this.characterLimit = planSectionDefinition.getCharacterLimit();
        this.focusAreaId = planSectionDefinition.getFocusAreaId();
        this.additionalIds = planSectionDefinition.getAdditionalIds();
        this.allowedValues = planSectionDefinition.getAllowedValues();
        this.locked = planSectionDefinition.getLocked();
        this.level = planSectionDefinition.getTier();
        this.contestIntegrationRelevance = planSectionDefinition.getContestIntegrationRelevance();
        this.allowedContestTypeIds = IdListUtil.getIdsFromString(planSectionDefinition.getAllowedContestTypeIds());


        PointsDistributionConfiguration pdc =
                PointsClientUtil.getPointsDistributionConfigurationByTargetPlanSectionDefinitionId(id);
        if( pdc != null ) {
            this.pointPercentage = Double.toString(pdc.getPercentage());
            this.pointType = pdc.getPointTypeId();
        }else{
            this.pointPercentage = "0";
            this.pointType = 0L;
        }
        /*
                PointsDistributionConfigurationClient.getPointsDistributionConfigurationByTargetPlanSectionDefinitionId(id);
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
        if (this.focusAreaId != 0) {
            FocusArea focusArea = OntologyClientUtil.getFocusArea(this.focusAreaId);

            OntologySpace space = OntologyClientUtil
                    .getOntologySpace(OntologySpaceEnum.WHAT.getSpaceId());
            List<OntologyTerm> terms =
                    OntologyClientUtil
                            .getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea,
                                    space);
            this.whatTermIds = getIdsFromOntologyTerms(terms);

            space = OntologyClientUtil
                    .getOntologySpace(OntologySpaceEnum.WHERE.getSpaceId());
            terms = OntologyClientUtil
                    .getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
            this.whereTermIds = getIdsFromOntologyTerms(terms);

            space = OntologyClientUtil
                    .getOntologySpace(OntologySpaceEnum.WHO.getSpaceId());
            terms = OntologyClientUtil
                    .getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
            this.whoTermIds = getIdsFromOntologyTerms(terms);

            space = OntologyClientUtil
                    .getOntologySpace(OntologySpaceEnum.HOW.getSpaceId());
            terms = OntologyClientUtil
                    .getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
            this.howTermIds = getIdsFromOntologyTerms(terms);
        }

    }

    public SectionDefinitionWrapper(String title) {
        this.title = title;
    }

    public SectionDefinitionWrapper(String title, boolean deletable) {
        this.title = title;
        this.deletable = deletable;
    }

    public SectionDefinitionWrapper(String title, Integer characterLimit, String helpText, String content) {
        this.title = title;
        this.characterLimit = characterLimit;
        this.helpText = helpText;
        this.content = content;
    }

    public SectionDefinitionWrapper(String title, Integer characterLimit, String helpText, String content,
                                    boolean deletable) {
        this.title = title;
        this.characterLimit = characterLimit;
        this.helpText = helpText;
        this.content = content;
        this.deletable = deletable;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeletable() {
        return deletable;
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

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
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

    public void setFocusAreaId(long focusAreaId) {
        this.focusAreaId = focusAreaId;
    }

    public long getFocusAreaId() {
        if (ontologyTermsSet() && getFocusAreaViaOntologyTerms()!=null) {
            focusAreaId = getFocusAreaViaOntologyTerms().getId_();
        }
        return focusAreaId;
    }

    private boolean ontologyTermsSet() {
        return !(CollectionUtils.isEmpty(getWhatTermIds()) || CollectionUtils.isEmpty(getWhereTermIds())
                || CollectionUtils.isEmpty(getWhoTermIds()) || CollectionUtils.isEmpty(getHowTermIds()));
    }

    public String getAdditionalIds() {
        return additionalIds;
    }

    public void setAdditionalIds(String additionalIds) {
        this.additionalIds = additionalIds;
    }

    public List<Long> getAllowedContestTypeIds() {
        return allowedContestTypeIds;
    }

    public void setAllowedContestTypeIds(List<Long> allowedContestTypeIds) {
        this.allowedContestTypeIds = allowedContestTypeIds;
    }

    public Long getPointType() {
        return pointType;
    }

    public void setPointType(Long pointType) {
        this.pointType = pointType;
    }

    public String getPointPercentage() {
        return pointPercentage;
    }

    public void setPointPercentage(String percentage) {
        this.pointPercentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(String allowedValues) {
        this.allowedValues = allowedValues;
    }

    static class MyComparator implements Comparator<SectionDefinitionWrapper> {

        @Override
        public int compare(SectionDefinitionWrapper o1, SectionDefinitionWrapper o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

    private FocusArea getFocusAreaViaOntologyTerms() {
        List<OntologyTerm> termsToBeMatched = getAllSelectedOntologyTerms();

        OntologyTermToFocusAreaMapper focusAreaMapper = new OntologyTermToFocusAreaMapper(termsToBeMatched);
        FocusArea focusArea = focusAreaMapper.getFocusAreaMatchingTermsExactly();

        if (focusArea != null) {
            focusArea = createNewFocusAreaWithTerms(termsToBeMatched);
        }

        return focusArea;
    }

    private FocusArea createNewFocusAreaWithTerms(List<OntologyTerm> focusAreaOntologyTerms) {
        FocusArea newFocusArea = new FocusArea();

        newFocusArea.setName("created for planSectionDefinition '" + this.title + "'");

        newFocusArea = OntologyClientUtil.createFocusArea(newFocusArea);

        for (OntologyTerm ontologyTerm : focusAreaOntologyTerms) {
            OntologyClientUtil
                    .addOntologyTermsToFocusAreaByOntologyTermId(newFocusArea.getId_(), ontologyTerm.getId_());
        }

        return newFocusArea;

    }

    private List<Long> getIdsFromOntologyTerms(List<OntologyTerm> ontologyTerms) {
        List<Long> ids = new ArrayList<>(ontologyTerms.size());
        for (OntologyTerm term : ontologyTerms) {
            ids.add(term.getId_());
        }

        return ids;
    }

    private List<OntologyTerm> getAllSelectedOntologyTerms() {

        List[] ontologyTermIdLists = {
                getWhatTermIds(), getWhereTermIds(), getWhoTermIds(), getHowTermIds()
        };

        List<OntologyTerm> selectedOntologyTerms = new ArrayList<>();
        for (List<Long> ontologyTermIds : ontologyTermIdLists) {
            for (Long ontologyTermId : ontologyTermIds) {
                selectedOntologyTerms
                        .add(OntologyClientUtil.getOntologyTerm(ontologyTermId));
            }
        }
        return selectedOntologyTerms;

    }

    public void persist(boolean createNew) {
        PlanSectionDefinition psd;
        PointsDistributionConfiguration pdc = null;
        if (id == null || createNew) {
            psd = new PlanSectionDefinition();

            psd.setType_(this.getType());
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
            psd.setLocked(false);

            psd = PlanTemplateClientUtil.createPlanSectionDefinition(psd);
            id = psd.getId_();
        } else {

            psd = PlanTemplateClientUtil.getPlanSectionDefinition(id);
            pdc = PointsClientUtil.getPointsDistributionConfigurationByTargetPlanSectionDefinitionId(id);


            psd.setType_(this.getType());
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
            psd.setLocked(false);


            if (pdc != null) {
                if (pointType == 0L) {
                    PointsClientUtil
                            .deletePointsDistributionConfiguration(pdc.getId_());
                } else {
                    pdc.setPercentage(Double.valueOf(pointPercentage));
                    pdc.setPointTypeId(pointType);
                    pdc.setTargetPlanSectionDefinitionId(id);
                    PointsClientUtil.updatePointsDistributionConfiguration(pdc);
            /*
                if (pdc != null) {
                    PointsDistributionConfigurationClient
                            .deletePointsDistributionConfiguration(pdc.getId_());
                }
            } else {
                if (pdc != null) {
                    pdc.setPercentage(Double.valueOf(pointPercentage));
                    pdc.setPointTypeId(pointType);
                    pdc.setTargetPlanSectionDefinitionId(id);

                    PointsDistributionConfigurationClient.updatePointsDistributionConfiguration(pdc);
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
                pdc.setTargetPlanSectionDefinitionId(id);
                pdc = PointsClientUtil.createPointsDistributionConfiguration(pdc);
            }
        }

        PlanTemplateClientUtil.updatePlanSectionDefinition(psd);

    }

    public boolean hasUpdates() {
        PlanSectionDefinition psd = PlanTemplateClientUtil
                .getPlanSectionDefinition(id);
        return !this.equals(new SectionDefinitionWrapper(psd));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(contestIntegrationRelevance)
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
                .append(other.getFocusAreaId(), this.getFocusAreaId())
                .append(other.getLevel(), this.getLevel())
                .append(other.getPointType(), this.getPointType())
                .append(other.getPointPercentage(), this.getPointPercentage())
                .append(other.getAllowedValues(), this.getAllowedValues()).isEquals();
    }
}
