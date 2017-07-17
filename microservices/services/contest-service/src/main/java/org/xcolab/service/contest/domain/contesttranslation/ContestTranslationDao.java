package org.xcolab.service.contest.domain.contesttranslation;

import org.xcolab.model.tables.pojos.ContestTranslation;

import java.util.List;
import java.util.Optional;

public interface ContestTranslationDao {

    ContestTranslation create(ContestTranslation translation);

    boolean update(ContestTranslation translation);

    Optional<ContestTranslation> get(long contestId, String lang);

    List<ContestTranslation> listByContestId(long contestId);
}
