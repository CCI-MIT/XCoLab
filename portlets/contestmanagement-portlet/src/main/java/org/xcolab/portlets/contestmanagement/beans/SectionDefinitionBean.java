package org.xcolab.portlets.contestmanagement.beans;

import java.io.Serializable;

/**
 * Created by Thomas on 2/13/2015.
 */
public class SectionDefinitionBean implements Serializable{

    //@NotBlank(message = "The contest description must be at least 5 characters and not more than 140 characters.")
    //@Length(min = 5, max = 140, message = "The contest question must be at least 5 characters and not more than 140 characters.")
    private String title;
    private Integer characterLimit;
    private String helpText;
    private String content;
    private boolean deletable = true;
    private boolean isSectionNew = false;
    private boolean templateSection = false;

    public SectionDefinitionBean() {
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
}
