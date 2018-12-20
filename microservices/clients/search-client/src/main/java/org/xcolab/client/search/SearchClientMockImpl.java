package org.xcolab.client.search;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.search.pojo.ISearchPojo;

import java.util.List;


@Component
@Profile("test")
public class SearchClientMockImpl implements ISearchClient {

    @Override
    public List<ISearchPojo> search(Integer startRecord, Integer limitRecord, String filter,
            String query) {
        return null;
    }

    @Override
    public Integer searchCount(String sort, String query) {
        return null;
    }
}
