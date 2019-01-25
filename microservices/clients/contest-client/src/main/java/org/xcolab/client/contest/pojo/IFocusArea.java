package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;

@JsonDeserialize(as = FocusAreaWrapper.class)
public interface IFocusArea {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);
}
