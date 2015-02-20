package org.xcolab.portlets.contestmanagement.beans;

import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplateSection;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Thomas on 2/13/2015.
 */
public class SectionDefinitionBean implements Serializable{

    //@NotBlank(message = "The contest description must be at least 5 characters and not more than 140 characters.")
    //@Length(min = 5, max = 140, message = "The contest question must be at least 5 characters and not more than 140 characters.")
    // TODO split in core bean and rest
    private Long id;
    private Long sectionDefinitionId;
    private String type = "";
    private String title = "";
    private String defaultText = "";
    private String helpText = "";
    private Integer characterLimit = 200;
    private Long focusAreaId;
    private Long level;
    private String content = "";
    private boolean locked = false;
    private boolean deletable = true;
    private boolean isSectionNew = false;
    private boolean templateSection = false;
    private int weight;

    public SectionDefinitionBean() {
    }

    public SectionDefinitionBean(PlanSectionDefinition planSectionDefinition){
        initPlanSectionDefinition(planSectionDefinition);
    }

    public SectionDefinitionBean(PlanSectionDefinition planSectionDefinition, Long planTemplateId) throws Exception{
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

    private void initPlanSectionDefinition(PlanSectionDefinition planSectionDefinition){
        this.sectionDefinitionId = planSectionDefinition.getId();
        this.type = planSectionDefinition.getType();
        this.title = planSectionDefinition.getTitle();
        this.defaultText = planSectionDefinition.getDefaultText();
        this.helpText = planSectionDefinition.getHelpText();
        this.characterLimit = planSectionDefinition.getCharacterLimit();
        this.focusAreaId = planSectionDefinition.getFocusAreaId();
        this.locked = planSectionDefinition.getLocked();
        this.level = planSectionDefinition.getTier();
    }

    public SectionDefinitionBean(String title) {
        this.title = title;
    }

    public SectionDefinitionBean(String title, boolean deletable) {
        this.title = title;
        this.deletable = deletable;
    }

    public SectionDefinitionBean(String title, Integer characterLimit, String helpText, String content) {
        this.title = title;
        this.characterLimit = characterLimit;
        this.helpText = helpText;
        this.content = content;
    }

    public SectionDefinitionBean(String title, Integer characterLimit, String helpText, String content, boolean deletable) {
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

    public Integer getCharacterLimit() {
        return characterLimit;
    }

    public void setCharacterLimit(Integer characterLimit) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getFocusAreaId() {
        return focusAreaId;
    }

    public void setFocusAreaId(Long focusAreaId) {
        this.focusAreaId = focusAreaId;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Long getSectionDefinitionId() {
        return sectionDefinitionId;
    }

    public void setSectionDefinitionId(Long sectionDefinitionId) {
        this.sectionDefinitionId = sectionDefinitionId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public static final Comparator<SectionDefinitionBean> sectionListComparator = new MyComparator();

    static class MyComparator implements Comparator<SectionDefinitionBean>{

        @Override
        public int compare(SectionDefinitionBean o1, SectionDefinitionBean o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

}
