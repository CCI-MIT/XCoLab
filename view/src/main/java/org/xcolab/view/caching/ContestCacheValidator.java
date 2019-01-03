package org.xcolab.view.caching;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.http.caching.validation.AbstractCacheValidator;

public class ContestCacheValidator extends AbstractCacheValidator<Contest> {

    public ContestCacheValidator() {
        super(Contest.class);
    }

    @Override
    public boolean isValid(Contest entity) {
        return entity.getId() != null;
    }
}
