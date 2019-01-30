package org.xcolab.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.search.ISearchClient;
import org.xcolab.client.search.StaticSearchContext;

@Component
@Profile("!test")
public class StaticInjector {

    @Autowired
    public StaticInjector(ISearchClient searchClient) {
        StaticSearchContext.setClients(searchClient);
    }
}
