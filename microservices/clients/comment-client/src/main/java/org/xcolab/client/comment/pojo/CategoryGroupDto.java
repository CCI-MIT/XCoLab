package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CategoryGroupDto extends AbstractCategoryGroup
        implements DataTransferObject<CategoryGroup> {

    public CategoryGroupDto() {
    }

    public CategoryGroupDto(AbstractCategoryGroup categoryGroup) {
        super(categoryGroup);
    }

    @Override
    public CategoryGroup toPojo(ServiceNamespace serviceNamespace) {
        return new CategoryGroup(this, serviceNamespace);
    }
}
