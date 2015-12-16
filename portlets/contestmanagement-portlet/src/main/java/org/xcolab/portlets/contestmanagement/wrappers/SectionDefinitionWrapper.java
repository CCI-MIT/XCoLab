package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.NoSuchFocusAreaException;
import com.ext.portlet.NoSuchPointsDistributionConfigurationException;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplateSection;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.xcolab.enums.OntologySpaceEnum;
import org.xcolab.utils.IdListUtil;
import org.xcolab.utils.OntologyTermToFocusAreaMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Thomas on 2/13/2015.
 */
public class SectionDefinitionWrapper implements Serializable {
    private final static Log _log = LogFactoryUtil.getLog(SectionDefinitionWrapper.class);

    private Long id;
    private String type = "";
    private String additionalIds = "";
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

    public SectionDefinitionWrapper(PlanSectionDefinition planSectionDefinition) throws PortalException, SystemException {
        initPlanSectionDefinition(planSectionDefinition);
    }

    public SectionDefinitionWrapper(PlanSectionDefinition planSectionDefinition, Long planTemplateId) throws SystemException, PortalException {
        initPlanSectionDefinition(planSectionDefinition);

        List<PlanTemplateSection> planTemplateSections =
                PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(planTemplateId);

        // TODO very inefficient, add finder to service layer
        for(PlanTemplateSection planTemplateSection : planTemplateSections){
            if(planTemplateSection.getPlanSectionId() == planSectionDefinition.getId()) {
                initPlanTemplateSection(planTemplateSection);
                break;
            }
        }

        initPlanSectionDefinition(planSectionDefinition);
    }

    private void initPlanTemplateSection(PlanTemplateSection planTemplateSection){
        this.weight = planTemplateSection.getWeight();
    }

    private void initPlanSectionDefinition(PlanSectionDefinition planSectionDefinition) throws SystemException, PortalException {
        this.id = planSectionDefinition.getId();
        this.type = planSectionDefinition.getType();
        this.title = planSectionDefinition.getTitle();
        this.defaultText = planSectionDefinition.getDefaultText();
        this.helpText = planSectionDefinition.getHelpText();
        this.characterLimit = planSectionDefinition.getCharacterLimit();
        this.focusAreaId = planSectionDefinition.getFocusAreaId();
        this.additionalIds = planSectionDefinition.getAdditionalIds();
        this.locked = planSectionDefinition.getLocked();
        this.level = planSectionDefinition.getTier();
        this.contestIntegrationRelevance = planSectionDefinition.getContestIntegrationRelevance();
        this.allowedContestTypeIds = IdListUtil.getIdsFromString(planSectionDefinition.getAllowedContestTypeIds());

        try {
            PointsDistributionConfiguration pdc = PointsDistributionConfigurationLocalServiceUtil.getByPlanSectionDefinitionId(id);
            this.pointPercentage = Double.toString(pdc.getPercentage());
            this.pointType = pdc.getPointTypeId();
        } catch (NoSuchPointsDistributionConfigurationException e) {
            this.pointPercentage = "0";
            this.pointType = 0L;
        }

        try {
            initOntologyTermIdsWithFocusAreaId();
        } catch (NoSuchFocusAreaException e){
            _log.warn(e);
        }
    }

    private void initOntologyTermIdsWithFocusAreaId() throws SystemException, PortalException {
        if (this.focusAreaId != 0) {
            FocusArea focusArea = FocusAreaLocalServiceUtil.getFocusArea(this.focusAreaId);

            OntologySpace space = OntologySpaceLocalServiceUtil.getOntologySpace(OntologySpaceEnum.WHAT.getSpaceId());
            List<OntologyTerm> terms = FocusAreaLocalServiceUtil.getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
            this.whatTermIds = getIdsFromOntologyTerms(terms);

            space = OntologySpaceLocalServiceUtil.getOntologySpace(OntologySpaceEnum.WHERE.getSpaceId());
            terms = FocusAreaLocalServiceUtil.getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
            this.whereTermIds = getIdsFromOntologyTerms(terms);

            space = OntologySpaceLocalServiceUtil.getOntologySpace(OntologySpaceEnum.WHO.getSpaceId());
            terms = FocusAreaLocalServiceUtil.getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
            this.whoTermIds = getIdsFromOntologyTerms(terms);

            space = OntologySpaceLocalServiceUtil.getOntologySpace(OntologySpaceEnum.HOW.getSpaceId());
            terms = FocusAreaLocalServiceUtil.getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, space);
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

    public SectionDefinitionWrapper(String title, Integer characterLimit, String helpText, String content, boolean deletable) {
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

    public long getFocusAreaId(){
        try {
            if (ontologyTermsSet()) {
                focusAreaId = getFocusAreaViaOntologyTerms().getId();
            }
            return focusAreaId;
        } catch (SystemException | PortalException e) {
            _log.warn("Could not get focus area id", e);
        }

        return 0L;
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

    static class MyComparator implements Comparator<SectionDefinitionWrapper>{

        @Override
        public int compare(SectionDefinitionWrapper o1, SectionDefinitionWrapper o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

    private FocusArea getFocusAreaViaOntologyTerms() throws SystemException, PortalException {
        List<OntologyTerm> termsToBeMatched = getAllSelectedOntologyTerms();

        OntologyTermToFocusAreaMapper focusAreaMapper = new OntologyTermToFocusAreaMapper(termsToBeMatched);
        FocusArea focusArea = focusAreaMapper.getFocusAreaMatchingTermsExactly();

        if (Validator.isNull(focusArea)) {
            focusArea = createNewFocusAreaWithTerms(termsToBeMatched);
        }

        return focusArea;
    }

    private FocusArea createNewFocusAreaWithTerms(List<OntologyTerm> focusAreaOntologyTerms) throws SystemException, PortalException {
        FocusArea newFocusArea = FocusAreaLocalServiceUtil.createFocusArea(CounterLocalServiceUtil.increment(FocusArea.class.getName()));
        newFocusArea.setName("created for planSectionDefinition '" + this.title + "'");
        for (OntologyTerm ontologyTerm : focusAreaOntologyTerms) {
            FocusAreaLocalServiceUtil.addTerm(newFocusArea, ontologyTerm.getId());
        }

        FocusAreaLocalServiceUtil.store(newFocusArea);
        return newFocusArea;
    }

    private List<Long> getIdsFromOntologyTerms(List<OntologyTerm> ontologyTerms) {
        List<Long> ids = new ArrayList<>(ontologyTerms.size());
        for (OntologyTerm term : ontologyTerms) {
            ids.add(term.getId());
        }

        return ids;
    }

    private List<OntologyTerm> getAllSelectedOntologyTerms() throws SystemException, PortalException {
        List[] ontologyTermIdLists = {
                getWhatTermIds(), getWhereTermIds(), getWhoTermIds(), getHowTermIds()
        };

        List<OntologyTerm> selectedOntologyTerms = new ArrayList<>();
        for (List<Long> ontologyTermIds : ontologyTermIdLists) {
            for (Long ontologyTermId : ontologyTermIds) {
                selectedOntologyTerms.add(OntologyTermLocalServiceUtil.getOntologyTerm(ontologyTermId));
            }
        }

        return selectedOntologyTerms;
    }

    public void persist(boolean createNew) throws SystemException, PortalException {
        PlanSectionDefinition psd;
        PointsDistributionConfiguration pdc = null;
        if (id == null || createNew) {
            psd = PlanSectionDefinitionLocalServiceUtil.
                    createPlanSectionDefinition(CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName()));
            id = psd.getId();
        } else {
            psd = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(id);

            try {
                pdc = PointsDistributionConfigurationLocalServiceUtil.getByPlanSectionDefinitionId(id);
                if (pointType == 0L) {
                    PointsDistributionConfigurationLocalServiceUtil.deletePointsDistributionConfiguration(pdc);
                } else {
                    pdc.setPercentage(Double.valueOf(pointPercentage));
                    pdc.setPointTypeId(pointType);
                    pdc.setTargetPlanSectionDefinitionId(id);
                    pdc.persist();
                }
            } catch (NoSuchPointsDistributionConfigurationException ignored) { }
        }
        if (pdc == null) {
            if (pointType > 0L) {
                pdc = PointsDistributionConfigurationLocalServiceUtil.createPointsDistributionConfiguration(
                        CounterLocalServiceUtil.increment(PointsDistributionConfiguration.class.getName()));
                pdc.setPercentage(Double.valueOf(pointPercentage));
                pdc.setPointTypeId(pointType);
                pdc.setTargetPlanSectionDefinitionId(id);
                pdc.persist();
            }
        }
        psd.setType(this.getType());
        psd.setTitle(this.getTitle());
        psd.setDefaultText(this.getDefaultText());
        psd.setCharacterLimit(this.getCharacterLimit());
        psd.setHelpText(this.getHelpText());
        psd.setTier(this.getLevel());
        psd.setFocusAreaId(this.getFocusAreaId());
        psd.setAdditionalIds(this.getAdditionalIds());
        psd.setAllowedContestTypeIds(IdListUtil.getStringFromIds(this.getAllowedContestTypeIds()));
        psd.setContestIntegrationRelevance(this.isContestIntegrationRelevance());
        psd.persist();
    }

    public boolean hasUpdates() throws SystemException, PortalException {
        PlanSectionDefinition psd = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(id);
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
                .append(additionalIds).toHashCode();
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
                .append(other.getPointPercentage(), this.getPointPercentage()).isEquals();
    }
}
