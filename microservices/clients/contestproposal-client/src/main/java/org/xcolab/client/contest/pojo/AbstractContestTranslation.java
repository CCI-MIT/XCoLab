package org.xcolab.client.contest.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public abstract class AbstractContestTranslation implements Serializable {

    private static final long serialVersionUID = 231088325;

    private Long contestId;
    private String lang;
    private String question;
    private String title;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long authorUserId;

    public AbstractContestTranslation() {}

    public AbstractContestTranslation(AbstractContestTranslation value) {
        this.contestId = value.contestId;
        this.lang = value.lang;
        this.question = value.question;
        this.title = value.title;
        this.description = value.description;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
        this.authorUserId = value.authorUserId;
    }

    public Long getContestId() {
        return this.contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getupdatedAt() {
        return this.updatedAt;
    }

    public void setupdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getAuthorUserId() {
        return this.authorUserId;
    }

    public void setAuthorUserId(Long authorUserId) {
        this.authorUserId = authorUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractContestTranslation)) {
            return false;
        }
        AbstractContestTranslation that = (AbstractContestTranslation) o;
        return Objects.equals(contestId, that.contestId) && Objects.equals(lang, that.lang)
                && Objects.equals(question, that.question)
                && Objects.equals(title, that.title)
                && Objects.equals(description, that.description)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt)
                && Objects.equals(authorUserId, that.authorUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contestId, lang, question, title, description,
                createdAt, updatedAt, authorUserId);
    }

    @Override
    public String toString() {

        return "ContestTranslation (" + contestId + ", " + lang + ", " + question + ", "
                + title + ", " + description + ", " + createdAt + ", "
                + updatedAt + ", " + authorUserId + ")";
    }
}
