package org.xcolab.service.contest.domain.contesttranslation;

import org.xcolab.client.contest.pojo.tables.pojos.ContestTranslation;

import java.util.List;
import java.util.Optional;

public interface ContestTranslationDao {

    ContestTranslation create(ContestTranslation translation);

    boolean update(ContestTranslation translation);

    boolean exists(long contestId, String lang);

    Optional<ContestTranslation> get(long contestId, String lang);

    List<ContestTranslation> listByContestId(long contestId);
}
