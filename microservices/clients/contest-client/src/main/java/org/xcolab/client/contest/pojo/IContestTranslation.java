package org.xcolab.client.contest.pojo;

import java.sql.Timestamp;

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
