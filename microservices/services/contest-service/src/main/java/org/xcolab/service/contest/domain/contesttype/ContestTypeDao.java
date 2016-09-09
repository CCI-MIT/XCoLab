package org.xcolab.service.contest.domain.contesttype;

import org.xcolab.model.tables.pojos.ContestType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ContestTypeDao {

    Optional<ContestType> get(Long id_) throws NotFoundException;
    List<ContestType> findByGiven();
}
