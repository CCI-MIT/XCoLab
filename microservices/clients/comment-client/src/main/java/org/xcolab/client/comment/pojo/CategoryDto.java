package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.util.http.client.RestService;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CategoryDto extends AbstractCategory {

    public CategoryDto() {
    }

    public CategoryDto(AbstractCategory category) {
        super(category);
    }

    public Category toPojo(RestService commentService) {
        return new Category(this, commentService);
    }

    public static List<Category> toPojos(List<CategoryDto> categoryDtos, RestService commentService) {
        final List<Category>  categories = new ArrayList<>(categoryDtos.size());
        for (CategoryDto categoryDto : categoryDtos) {
            categories.add(categoryDto.toPojo(commentService));
        }
        return categories;
    }
}
