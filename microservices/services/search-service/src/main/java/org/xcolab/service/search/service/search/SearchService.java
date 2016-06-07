package org.xcolab.service.search.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.service.search.domain.SearchDao;

@Service
public class SearchService {

    private final SearchDao searchDao;

    @Autowired
    public SearchService(SearchDao searchDao) {
        this.searchDao = searchDao;
    }
}