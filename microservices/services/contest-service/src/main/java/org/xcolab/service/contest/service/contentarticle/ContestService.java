package org.xcolab.service.contest.service.contentarticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.service.contest.domain.contentarticle.ContestDao;

@Service
public class ContestService {

    private final ContestDao contestDao;

    @Autowired
    public ContestService(ContestDao contestDao) {
        this.contestDao = contestDao;
    }
}
