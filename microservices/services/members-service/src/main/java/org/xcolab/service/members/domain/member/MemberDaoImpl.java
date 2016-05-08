package org.xcolab.service.members.domain.member;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.jooq.impl.DSL.countDistinct;
import static org.jooq.impl.DSL.sum;
import static org.xcolab.model.Tables.MEMBER;
import static org.xcolab.model.Tables.POINTS;
import static org.xcolab.model.Tables.ROLES_CATEGORY;
import static org.xcolab.model.Tables.SOCIAL_ACTIVITY;
import static org.xcolab.model.Tables.USERS_ROLES;

@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<Member> findByGiven(PaginationHelper paginationHelper, String partialName,
            String roleName, String email, String screenName, Long facebookId, String openId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(MEMBER)
                .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .getQuery();

        if (partialName != null) {
            query.addConditions(MEMBER.SCREEN_NAME.contains(partialName)
                    .or(MEMBER.FIRST_NAME.contains(partialName))
                    .or(MEMBER.LAST_NAME.contains(partialName)));
        }
        if (roleName != null) {
            query.addConditions(ROLES_CATEGORY.CATEGORY_NAME.eq(roleName));
        }
        if (screenName != null) {
            query.addConditions(MEMBER.SCREEN_NAME.eq(screenName));
        }
        if (email != null) {
            query.addConditions(MEMBER.EMAIL_ADDRESS.eq(email));
        }
        if (facebookId != null) {
            query.addConditions(MEMBER.FACEBOOK_ID.eq(facebookId));
        }
        if (openId != null) {
            query.addConditions(MEMBER.OPEN_ID.eq(openId));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "createDate":
                    query.addOrderBy(sortColumn.isAscending()
                            ? MEMBER.CREATE_DATE.asc() : MEMBER.CREATE_DATE.desc());
                    break;
                case "screenName":
                    query.addOrderBy(sortColumn.isAscending()
                            ? MEMBER.SCREEN_NAME.asc() : MEMBER.SCREEN_NAME.desc());
                    break;
                case "activityCount":
                    Field<Object> activityCount = this.dslContext.selectCount()
                            .from(SOCIAL_ACTIVITY)
                            .where(SOCIAL_ACTIVITY.USER_ID.equal(MEMBER.ID_))
                            .asField("activityCount");
                    query.addSelect(activityCount);
                    query.addSelect(MEMBER.fields());
                    query.addOrderBy(sortColumn.isAscending()
                            ? activityCount.asc() : activityCount.desc());
                    break;
                case "roleName":
                    query.addOrderBy(sortColumn.isAscending()
                            ? ROLES_CATEGORY.CATEGORY_NAME.asc()
                            : ROLES_CATEGORY.CATEGORY_NAME.desc());
                    break;
                case "roleOrdinal":
                    query.addOrderBy(sortColumn.isAscending()
                            ? ROLES_CATEGORY.ROLE_ORDINAL.asc()
                            : ROLES_CATEGORY.ROLE_ORDINAL.desc());
                    break;
                case "points":
                default:
                    Field<Object> points =
                            this.dslContext.select(sum(POINTS.MATERIALIZED_POINTS))
                                    .from(POINTS)
                                    .where(POINTS.USER_ID.equal(MEMBER.ID_))
                                    .asField("points");
                    query.addSelect(points);
                    query.addSelect(MEMBER.fields());
                    query.addOrderBy(sortColumn.isAscending()
                            ? points.asc() : points.desc());
                    break;
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord());
        return query.fetchInto(Member.class);
    }


    @Override
    public Member getMember(long memberId) throws NotFoundException {
        final Record memberRecord = dslContext.select()
                .from(MEMBER)
                .where(MEMBER.ID_.eq(memberId))
                .fetchOne();
        if (memberRecord == null) {
            throw new NotFoundException("Member with id " + memberId + " does not exist");
        }
        return memberRecord.into(Member.class);
    }


    @Override
    public Integer countMembers(String filter) {
        if (filter != null && !filter.isEmpty()) {
            return this.dslContext.select(countDistinct(MEMBER.ID_))
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(MEMBER.SCREEN_NAME.contains(filter))
                    .or(MEMBER.FIRST_NAME.contains(filter))
                    .or(MEMBER.LAST_NAME.contains(filter))
                    .and(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%")).fetchOne(0, Integer.class);
        } else {
            return this.dslContext.select(countDistinct(MEMBER.ID_))
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%")).fetchOne(0, Integer.class);
        }
    }

    @Override
    public Integer countMembersFilteredByCategory(String filter, String roleName) {
        if (filter != null && !filter.isEmpty()) {
            return this.dslContext.select(countDistinct(MEMBER.ID_))
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(MEMBER.SCREEN_NAME.contains(filter))
                    .or(MEMBER.FIRST_NAME.contains(filter))
                    .or(MEMBER.LAST_NAME.contains(filter))
                    .and(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                    .fetchOne(0, Integer.class);

        } else {
            return this.dslContext.select(countDistinct(MEMBER.ID_))
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                    .fetchOne(0, Integer.class);
        }
    }

    @Override
    public Member findOneByScreenName(String screenName) {
        return dslContext.select()
                .from(MEMBER)
                .where(MEMBER.SCREEN_NAME.eq(screenName))
                .fetchOne().into(Member.class);
    }

    @Override
    public Member findOneByEmail(String email) {
        return dslContext.select()
                .from(MEMBER)
                .where(MEMBER.EMAIL_ADDRESS.eq(email))
                .fetchOne().into(Member.class);
    }

    @Override
    public void updateMember(Member member) {

        this.dslContext.update(MEMBER)
                .set(MEMBER.CREATE_DATE, member.getCreateDate())
                .set(MEMBER.MODIFIED_DATE, member.getModifiedDate())
                .set(MEMBER.HASHED_PASSWORD, member.getHashedPassword())
                .set(MEMBER.PASSWORD_MODIFIED_DATE, member.getPasswordModifiedDate())
                .set(MEMBER.SCREEN_NAME, member.getScreenName())
                .set(MEMBER.EMAIL_ADDRESS, member.getEmailAddress())
                .set(MEMBER.OPEN_ID, member.getOpenId())
                .set(MEMBER.FIRST_NAME, member.getFirstName())
                .set(MEMBER.LAST_NAME, member.getLastName())
                .set(MEMBER.LOGIN_DATE, member.getLoginDate())
                .set(MEMBER.LOGIN_IP, member.getLoginIP())
                .set(MEMBER.FACEBOOK_ID, member.getFacebookId())
                .set(MEMBER.SHORT_BIO, member.getShortBio())
                .set(MEMBER.COUNTRY, member.getCountry())
                .where(MEMBER.ID_.equal(member.getId_()))
                .execute();
    }

    @Override
    public void createMember(String screenName, String hashedPassword, String email, String firstName, String lastName,
            String shortBio, String country, Long facebookId, String openId, Long liferayUserId) {
        this.dslContext.insertInto(MEMBER)
                .set(MEMBER.ID_, liferayUserId)
                .set(MEMBER.SCREEN_NAME, screenName)
                .set(MEMBER.HASHED_PASSWORD, hashedPassword)
                .set(MEMBER.EMAIL_ADDRESS, email)
                .set(MEMBER.FIRST_NAME, firstName)
                .set(MEMBER.LAST_NAME, lastName)
                .set(MEMBER.FACEBOOK_ID, facebookId)
                .set(MEMBER.OPEN_ID, openId)
                .set(MEMBER.SHORT_BIO, shortBio)
                .set(MEMBER.COUNTRY, country)
                .set(MEMBER.CREATE_DATE, new Timestamp(Instant.now().toEpochMilli()))
                .set(MEMBER.MODIFIED_DATE, new Timestamp(Instant.now().toEpochMilli()))
                .execute();
    }

    @Override
    public boolean isScreenNameTaken(String screenName) {
        return dslContext.selectCount()
                .from(MEMBER)
                .where(MEMBER.SCREEN_NAME.eq(screenName))
                .fetchOne(0, Integer.class) > 0;
    }

    @Override
    public boolean isEmailUsed(String email) {
        return dslContext.selectCount()
                .from(MEMBER)
                .where(MEMBER.EMAIL_ADDRESS.eq(email))
                .fetchOne(0, Integer.class) > 0;
    }

    @Override
    public Integer getMemberMaterializedPoints(Long memberId) {
        return this.dslContext.select(sum(POINTS.MATERIALIZED_POINTS))
                .from(POINTS).where(POINTS.USER_ID.equal(memberId)).fetchOne(0, Integer.class);
    }

    @Override
    public Integer getMemberActivityCount(Long memberId) {
        return this.dslContext.selectCount()
                .from(SOCIAL_ACTIVITY).where(SOCIAL_ACTIVITY.USER_ID.equal(memberId))
                .fetchOne(0, Integer.class);
    }
}