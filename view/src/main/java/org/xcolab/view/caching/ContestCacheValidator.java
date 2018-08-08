package org.xcolab.view.caching;

import org.xcolab.client.contest.pojo.ContestDto;
import org.xcolab.util.http.caching.validation.AbstractCacheValidator;

public class ContestCacheValidator extends AbstractCacheValidator<ContestDto> {

    public ContestCacheValidator() {
        super(ContestDto.class);
    }

    @Override
    public boolean isValid(ContestDto entity) {
        return entity.getContestPK() != null;
    }
}
