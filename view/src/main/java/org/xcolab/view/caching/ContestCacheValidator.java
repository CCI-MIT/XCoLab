package org.xcolab.view.caching;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.util.http.caching.validation.AbstractCacheValidator;

public class ContestCacheValidator extends AbstractCacheValidator<ContestWrapper> {

    public ContestCacheValidator() {
        super(ContestWrapper.class);
    }

    @Override
    public boolean isValid(ContestWrapper entity) {
        return entity.getId() != null;
    }
}
