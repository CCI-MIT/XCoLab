package org.xcolab.client.search;

import org.springframework.util.Assert;

public class StaticSearchContext {

    private static ISearchClient searchClient;

    public static void setClients(ISearchClient searchClient) {
        Assert.notNull(searchClient, "searchClient must not be null!");
        StaticSearchContext.searchClient = searchClient;
    }

    public static ISearchClient getSearchClient() {
        return searchClient;
    }
}
