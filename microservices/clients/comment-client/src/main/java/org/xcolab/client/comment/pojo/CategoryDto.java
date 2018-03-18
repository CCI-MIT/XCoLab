package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DataTransferObject;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CategoryDto extends AbstractCategory implements DataTransferObject<Category> {

    public CategoryDto() {
    }

    public CategoryDto(AbstractCategory category) {
        super(category);
    }

    @Override
    public Category toPojo(ServiceNamespace serviceNamespace) {
        return new Category(this, serviceNamespace);
    }
}
