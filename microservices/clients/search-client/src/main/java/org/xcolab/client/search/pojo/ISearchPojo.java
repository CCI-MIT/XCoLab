package org.xcolab.client.search.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.xcolab.client.search.pojo.tables.pojos.SearchPojo;

import java.io.Serializable;

@JsonDeserialize(as = SearchPojo.class)
public interface ISearchPojo extends Serializable {

    Double getRelevance();

    void setRelevance(Double relevance);

    Long getClassPrimaryKey();

    void setClassPrimaryKey(Long classPrimaryKey);

    Long getSearchTypeId();

    void setSearchTypeId(Long searchTypeId);
}
