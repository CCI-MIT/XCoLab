package org.xcolab.service.tracking.domain.balloontext;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.tracking.pojo.IBalloonText;
import org.xcolab.client.tracking.pojo.tables.pojos.BalloonText;
import org.xcolab.model.tables.records.BalloonTextRecord;
import org.xcolab.service.tracking.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.BALLOON_TEXT;

@Repository
public class BalloonTextDaoImpl implements BalloonTextDao {

    private final DSLContext dslContext;

    @Autowired
    public BalloonTextDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public IBalloonText getBalloonText(Long id) throws NotFoundException {
        final Record record = dslContext.select()
                .from(BALLOON_TEXT)
                .where(BALLOON_TEXT.ID.eq(id)).fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(BalloonText.class);

    }

    @Override
    public List<IBalloonText> getEnabledBalloonTexts() {
        return dslContext.select()
                .from(BALLOON_TEXT)
                .where(BALLOON_TEXT.ENABLED.eq(true))
                .fetchInto(BalloonText.class);
    }

    @Override
    public boolean update(IBalloonText balloonText) {
        return dslContext.update(BALLOON_TEXT)
                .set(BALLOON_TEXT.ID, balloonText.getId())
                .set(BALLOON_TEXT.NAME, balloonText.getName())
                .set(BALLOON_TEXT.TEXT_BEFORE_FORM, balloonText.getTextBeforeForm())
                .set(BALLOON_TEXT.TEXT_BEFORE_SHARE_BUTTONS, balloonText.getTextBeforeShareButtons())
                .set(BALLOON_TEXT.EMAIL_TEMPLATE, balloonText.getEmailTemplate())
                .set(BALLOON_TEXT.EMAIL_SUBJECT_TEMPLATE, balloonText.getEmailSubjectTemplate())
                .set(BALLOON_TEXT.SHARE_TITLE, balloonText.getShareTitle())
                .set(BALLOON_TEXT.SHARE_DESCRIPTION, balloonText.getShareDescription())
                .set(BALLOON_TEXT.ENABLED, balloonText.isEnabled())
                .where(BALLOON_TEXT.ID.eq(balloonText.getId()))
                .execute() > 0;
    }

    @Override
    public IBalloonText create(IBalloonText balloonText) {
        BalloonTextRecord ret = this.dslContext.insertInto(BALLOON_TEXT)
                .set(BALLOON_TEXT.NAME, balloonText.getName())
                .set(BALLOON_TEXT.TEXT_BEFORE_FORM, balloonText.getTextBeforeForm())
                .set(BALLOON_TEXT.TEXT_BEFORE_SHARE_BUTTONS, balloonText.getTextBeforeShareButtons())
                .set(BALLOON_TEXT.EMAIL_TEMPLATE, balloonText.getEmailTemplate())
                .set(BALLOON_TEXT.EMAIL_SUBJECT_TEMPLATE, balloonText.getEmailSubjectTemplate())
                .set(BALLOON_TEXT.SHARE_TITLE, balloonText.getShareTitle())
                .set(BALLOON_TEXT.SHARE_DESCRIPTION, balloonText.getShareDescription())
                .set(BALLOON_TEXT.ENABLED, balloonText.isEnabled())

                .returning(BALLOON_TEXT.ID)
                .fetchOne();

        if (ret != null) {
            return balloonText;
        } else {
            return null;
        }
    }
    @Override
    public boolean delete(Long id) {
        return this.dslContext.delete(BALLOON_TEXT)
                .where(BALLOON_TEXT.ID.eq(id))
                .execute() > 0;
    }
}
