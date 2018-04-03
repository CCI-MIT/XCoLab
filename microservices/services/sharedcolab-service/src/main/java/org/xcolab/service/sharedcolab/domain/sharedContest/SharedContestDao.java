package org.xcolab.service.sharedcolab.domain.sharedContest;

import org.xcolab.model.tables.pojos.SharedContest;
import org.xcolab.service.sharedcolab.NotFoundException;

import java.util.List;

public interface SharedContestDao {

    SharedContest get(Long sharedContestId) throws NotFoundException;

    SharedContest create(SharedContest sharedContest);

    boolean update(SharedContest sharedContest);

    List<SharedContest> findByGiven(String colabOrigin);
}
