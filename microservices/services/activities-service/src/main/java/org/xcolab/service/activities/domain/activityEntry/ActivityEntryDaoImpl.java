package org.xcolab.service.activities.domain.activityEntry;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.model.tables.records.ActivityEntryRecord;

import static org.xcolab.model.Tables.ACTIVITY_ENTRY;

@Repository
public class ActivityEntryDaoImpl implements ActivityEntryDao {

    @Autowired
    private DSLContext dslContext;

    public ActivityEntry create(ActivityEntry activityEntry) {
        ActivityEntryRecord ret = this.dslContext.insertInto(ACTIVITY_ENTRY)
                .set(ACTIVITY_ENTRY.MEMBER_ID, activityEntry.getMemberId())
                .set(ACTIVITY_ENTRY.CREATE_DATE, activityEntry.getCreateDate())
                .set(ACTIVITY_ENTRY.PRIMARY_TYPE, activityEntry.getPrimaryType())
                .set(ACTIVITY_ENTRY.SECONDARY_TYPE, activityEntry.getSecondaryType())
                .set(ACTIVITY_ENTRY.CLASS_PRIMARY_KEY, activityEntry.getClassPrimaryKey())
                .set(ACTIVITY_ENTRY.EXTRA_DATA, activityEntry.getExtraData())
                .set(ACTIVITY_ENTRY.ACTIVITY_ENTRY_TITLE, activityEntry.getActivityEntryTitle())
                .set(ACTIVITY_ENTRY.ACTIVITY_ENTRY_BODY, activityEntry.getActivityEntryBody())
                .set(ACTIVITY_ENTRY.ACTIVITY_ENTRY_NAME, activityEntry.getActivityEntryName())
                .returning(ACTIVITY_ENTRY.ACTIVITY_ENTRY_ID)
                .fetchOne();
        if (ret != null) {
            activityEntry.setActivityEntryId(ret.getValue(ACTIVITY_ENTRY.ACTIVITY_ENTRY_ID));
            return activityEntry;
        } else {
            return null;
        }
    }
}
