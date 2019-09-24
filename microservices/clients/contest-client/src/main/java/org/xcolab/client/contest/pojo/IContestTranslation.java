package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ContestTranslation;

import java.sql.Timestamp;

@JsonDeserialize(as = ContestTranslation.class)
public interface IContestTranslation {

    Long getContestId();

    void setContestId(Long contestId);

    String getLang();

    void setLang(String lang);

    String getQuestion();

    void setQuestion(String question);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);
}
