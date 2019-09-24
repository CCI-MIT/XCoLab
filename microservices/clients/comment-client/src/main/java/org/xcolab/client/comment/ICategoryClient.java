package org.xcolab.client.comment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.client.comment.pojo.ICategoryGroup;

import java.util.List;

@FeignClient("xcolab-comment-service")
public interface ICategoryClient {

    @GetMapping("/categories")
    List<ICategory> listCategories(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "limitRecord", required = false) Integer limitRecord,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "authorUserId", required = false) Long authorUserId,
            @RequestParam(value = "groupId", required = false) Long groupId);

    default List<ICategory> listCategories(int startRecord, int limitRecord, long groupId) {
        return listCategories(startRecord, limitRecord, "sort", null, groupId);
    }

    @GetMapping("/categories/{categoryId}")
    ICategory getCategory(@PathVariable("categoryId") Long categoryId)
            throws CategoryNotFoundException;

    @PutMapping("/categories")
    boolean updateCategory(@RequestBody ICategory category);

    @PostMapping("/categories")
    ICategory createCategory(@RequestBody ICategory category);

    @GetMapping("/groups/{groupId}")
    ICategoryGroup getCategoryGroup(@PathVariable("groupId") Long groupId)
            throws CategoryGroupNotFoundException;
}
