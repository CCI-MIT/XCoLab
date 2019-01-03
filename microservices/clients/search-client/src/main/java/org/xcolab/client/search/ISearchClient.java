package org.xcolab.client.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.search.pojo.ISearchPojo;

import java.util.List;

@FeignClient("xcolab-search-service")
public interface ISearchClient {

    @GetMapping("/search")
    List<ISearchPojo> search(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "limitRecord", required = false) Integer limitRecord,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam("query") String query);

    @GetMapping("/search/count")
    Integer searchCount(@RequestParam(value="sort", required = false) String sort,
            @RequestParam("query") String query);

}
