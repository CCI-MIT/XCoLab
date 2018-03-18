package org.xcolab.client.search.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final TypeProvider<SearchPojo> TYPES =
            new TypeProvider<>(SearchPojo.class,
                    new ParameterizedTypeReference<List<SearchPojo>>() {
                    });

    private Double relevance;

    private Long classPrimaryKey;

    private Long searchTypeId;


    public Double getRelevance() {
        return relevance;
    }

    public void setRelevance(Double relevance) {
        this.relevance = relevance;
    }

    public Long getClassPrimaryKey() {
        return classPrimaryKey;
    }

    public void setClassPrimaryKey(Long classPrimaryKey) {
        this.classPrimaryKey = classPrimaryKey;
    }

    public Long getSearchTypeId() {
        return searchTypeId;
    }

    public void setSearchTypeId(Long searchType) {
        searchTypeId = searchType;
    }

    @Override
    public String toString() {
        return "SearchPojo{" + "relevance=" + relevance + ", classPrimaryKey=" + classPrimaryKey
                + ", searchTypeId=" + searchTypeId + '}';
    }
}
