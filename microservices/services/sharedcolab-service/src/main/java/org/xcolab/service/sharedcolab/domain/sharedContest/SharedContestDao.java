package org.xcolab.service.sharedcolab.domain.sharedContest;

import com.sun.jersey.api.NotFoundException;

import org.xcolab.model.tables.pojos.SharedContest;

public interface SharedContestDao {

    SharedContest get(Long sharedContestId) throws NotFoundException;

    SharedContest create(SharedContest sharedContest);

    boolean update(SharedContest sharedContest);
}
