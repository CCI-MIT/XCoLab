package org.xcolab.service.contest.domain.contesttranslation;

import org.xcolab.client.contest.pojo.IContestTranslation;

import java.util.List;
import java.util.Optional;

public interface ContestTranslationDao {

    IContestTranslation create(IContestTranslation translation);

    boolean update(IContestTranslation translation);

    boolean exists(long contestId, String lang);

    Optional<IContestTranslation> get(long contestId, String lang);

    List<IContestTranslation> listByContestId(long contestId);
}
