package org.xcolab.service.contest.domain.contesttranslation;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IContestTranslation;
import org.xcolab.client.contest.pojo.tables.pojos.ContestTranslation;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST_TRANSLATION;

@Repository
public class ContestTranslationDaoImpl implements ContestTranslationDao {

    private final DSLContext dslContext;

    @Autowired
    public ContestTranslationDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public ContestTranslation create(ContestTranslation translation) {
        dslContext.insertInto(CONTEST_TRANSLATION)
                .set(CONTEST_TRANSLATION.CONTEST_ID, translation.getContestId())
                .set(CONTEST_TRANSLATION.LANG, translation.getLang())
                .set(CONTEST_TRANSLATION.TITLE, translation.getTitle())
                .set(CONTEST_TRANSLATION.QUESTION, translation.getQuestion())
                .set(CONTEST_TRANSLATION.DESCRIPTION, translation.getDescription())
                .set(CONTEST_TRANSLATION.CREATED_AT, DSL.currentTimestamp())
                .set(CONTEST_TRANSLATION.UPDATED_AT, DSL.currentTimestamp())
                .set(CONTEST_TRANSLATION.AUTHOR_USER_ID, translation.getAuthorUserId())
                .execute();
        return translation;
    }

    @Override
    public boolean update(ContestTranslation translation) {
        return dslContext.update(CONTEST_TRANSLATION)
                .set(CONTEST_TRANSLATION.TITLE, translation.getTitle())
                .set(CONTEST_TRANSLATION.QUESTION, translation.getQuestion())
                .set(CONTEST_TRANSLATION.DESCRIPTION, translation.getDescription())
                .set(CONTEST_TRANSLATION.UPDATED_AT, DSL.currentTimestamp())
                .set(CONTEST_TRANSLATION.AUTHOR_USER_ID, translation.getAuthorUserId())
                .where(CONTEST_TRANSLATION.CONTEST_ID.eq(translation.getContestId())
                        .and(CONTEST_TRANSLATION.LANG.equalIgnoreCase(translation.getLang())))
                .execute() > 0;
    }

    @Override
    public boolean exists(long contestId, String lang) {
        return dslContext.fetchExists(DSL.select()
                .from(CONTEST_TRANSLATION)
                .where(CONTEST_TRANSLATION.CONTEST_ID.eq(contestId)
                        .and(CONTEST_TRANSLATION.LANG.equalIgnoreCase(lang))));
    }

    @Override
    public Optional<ContestTranslation> get(long contestId, String lang) {
        final Record record = dslContext.select().from(CONTEST_TRANSLATION)
                .where(CONTEST_TRANSLATION.CONTEST_ID.eq(contestId)
                        .and(CONTEST_TRANSLATION.LANG.equalIgnoreCase(lang))).fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestTranslation.class));
    }

    @Override
    public List<ContestTranslation> listByContestId(long contestId) {
        return dslContext.select()
                .from(CONTEST_TRANSLATION)
                .where(CONTEST_TRANSLATION.CONTEST_ID.eq(contestId))
                .orderBy(CONTEST_TRANSLATION.LANG)
                .fetchInto(ContestTranslation.class);
    }
}
