package org.xcolab.service.proposal.domain.group;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.Group_;
import org.xcolab.model.tables.records.Group_Record;

import static org.xcolab.model.Tables.GROUP_;

@Repository
public class GroupDaoImpl implements GroupDao {
    @Autowired
    private DSLContext dslContext;


    @Override
    public Group_ create(Group_ group_) {

        Group_Record ret = this.dslContext.insertInto(GROUP_)
                .set(GROUP_.GROUP_ID, group_.getGroupId())
                .set(GROUP_.COMPANY_ID, group_.getCompanyId())
                .set(GROUP_.CREATOR_USER_ID, group_.getCreatorUserId())
                .set(GROUP_.CLASS_NAME_ID, group_.getClassNameId())
                .set(GROUP_.CLASS_PK, group_.getClassPK())
                .set(GROUP_.PARENT_GROUP_ID, group_.getParentGroupId())
                .set(GROUP_.LIVE_GROUP_ID, group_.getLiveGroupId())
                .set(GROUP_.NAME, group_.getName())
                .set(GROUP_.DESCRIPTION, group_.getDescription())
                .set(GROUP_.TYPE_, group_.getType_())
                .set(GROUP_.TYPE_SETTINGS, group_.getTypeSettings())
                .set(GROUP_.FRIENDLY_URL, group_.getFriendlyURL())
                .set(GROUP_.ACTIVE_, group_.getActive_())
                .set(GROUP_.SITE, group_.getSite())
                .set(GROUP_.UUID, group_.getUuid_())
                .set(GROUP_.TREE_PATH, group_.getTreePath())
                .set(GROUP_.MANUAL_MEMBERSHIP, group_.getManualMembership())
                .set(GROUP_.MEMBERSHIP_RESTRICTION, group_.getMembershipRestriction())
                .set(GROUP_.REMOTE_STAGING_GROUP_COUNT, group_.getRemoteStagingGroupCount())
                .returning(GROUP_.GROUP_ID)
                .fetchOne();
        if (ret != null) {
            group_.setGroupId(ret.getValue(GROUP_.GROUP_ID));
            return group_;
        } else {
            return null;
        }

    }

    @Override
    public boolean update(Group_ group_) {
        return dslContext.update(GROUP_)
                .set(GROUP_.COMPANY_ID, group_.getCompanyId())
                .set(GROUP_.CREATOR_USER_ID, group_.getCreatorUserId())
                .set(GROUP_.CLASS_NAME_ID, group_.getClassNameId())
                .set(GROUP_.CLASS_PK, group_.getClassPK())
                .set(GROUP_.PARENT_GROUP_ID, group_.getParentGroupId())
                .set(GROUP_.LIVE_GROUP_ID, group_.getLiveGroupId())
                .set(GROUP_.NAME, group_.getName())
                .set(GROUP_.DESCRIPTION, group_.getDescription())
                .set(GROUP_.TYPE_, group_.getType_())
                .set(GROUP_.TYPE_SETTINGS, group_.getTypeSettings())
                .set(GROUP_.FRIENDLY_URL, group_.getFriendlyURL())
                .set(GROUP_.ACTIVE_, group_.getActive_())
                .set(GROUP_.SITE, group_.getSite())
                .set(GROUP_.UUID, group_.getUuid_())
                .set(GROUP_.TREE_PATH, group_.getTreePath())
                .set(GROUP_.MANUAL_MEMBERSHIP, group_.getManualMembership())
                .set(GROUP_.MEMBERSHIP_RESTRICTION, group_.getMembershipRestriction())
                .set(GROUP_.REMOTE_STAGING_GROUP_COUNT, group_.getRemoteStagingGroupCount())
                .where(GROUP_.GROUP_ID.eq(group_.getGroupId()))
                .execute() > 0;
    }




}
