package org.xcolab.service.contest.domain.contesttype;

import org.xcolab.model.tables.pojos.ContestType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ContestTypeDao {

    ContestType get(Long id_) throws NotFoundException;
    List<ContestType> findByGiven();
}
