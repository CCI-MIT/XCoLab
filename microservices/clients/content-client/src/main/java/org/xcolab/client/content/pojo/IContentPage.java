package org.xcolab.client.content.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.content.pojo.tables.pojos.ContentPage;

import java.sql.Timestamp;

@JsonDeserialize(as = ContentPage.class)
public interface IContentPage {

    Long getId();

    void setId(Long id);

    String getTitle();

    void setTitle(String title);

    String getMetaDescription();

    void setMetaDescription(String metaDescription);

    Long getMenuArticleId();

    void setMenuArticleId(Long menuArticleId);

    Long getContentArticleId();

    void setContentArticleId(Long contentArticleId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);
}
