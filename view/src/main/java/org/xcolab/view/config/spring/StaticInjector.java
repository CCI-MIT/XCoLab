package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.xcolab.client.search.SearchClient;
import org.xcolab.view.pages.search.paging.SearchDataPage;

@Component
public class StaticInjector implements ApplicationRunner {

    @Autowired
    private SearchClient searchClient;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        SearchDataPage.setSearchClient(searchClient);
    }
}
