package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.comment.StaticInjectorComment;
import org.xcolab.client.comment.pojo.tables.pojos.CategoryGroup;
import org.xcolab.client.comment.util.ThreadSortColumn;

import java.util.List;

@JsonDeserialize(as = CategoryGroup.class)
public interface ICategoryGroup {

    Long getId();

    void setId(Long id);

    String getDescription();

    void setDescription(String description);

    String getUrl();

    void setUrl(String url);

    Boolean getIsQuiet();

    void setIsQuiet(Boolean isQuiet);

    @JsonIgnore
    default List<IThread> getThreads(ThreadSortColumn sortColumn, boolean ascending) {
        return StaticInjectorComment.getThreadClient().listThreads(0, Integer.MAX_VALUE,
                null, getId(), sortColumn, ascending);
    }

    @JsonIgnore
    default List<ICategory> getCategories() {
        return StaticInjectorComment.getCategoryClient()
                .listCategories(0, Integer.MAX_VALUE, getId());
    }

    @JsonIgnore
    default String getLinkUrl() {
        return getUrl();
    }
}
