package org.xcolab.service.members.domain.member;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.MemberCategoryTable;
import org.xcolab.model.tables.Users_RolesTable;
import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.Users_Roles;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.countDistinct;
import static org.jooq.impl.DSL.max;
import static org.jooq.impl.DSL.sum;
import static org.xcolab.model.Tables.ACTIVITY_ENTRY;
import static org.xcolab.model.Tables.LOGIN_LOG;
import static org.xcolab.model.Tables.MEMBER;
import static org.xcolab.model.Tables.MEMBER_CATEGORY;
import static org.xcolab.model.Tables.POINTS;
import static org.xcolab.model.Tables.USERS_ROLES;

@Repository
public class MemberDaoImpl implements MemberDao {

    private final DSLContext dslContext;

    @Autowired
    public MemberDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<Member> findByGiven(PaginationHelper paginationHelper, String partialName,
            String partialEmail, String roleName, String email, String screenName, Long facebookId,
            String googleId, List<Long> roleIds) {
        final SelectQuery<Record> query = dslContext.selectDistinct(MEMBER.fields())
                .from(MEMBER)
                .where(MEMBER.STATUS.eq(0))
                .getQuery();

        if (roleName != null || roleIds != null) {
            query.addJoin(USERS_ROLES, MEMBER.ID_.equal(USERS_ROLES.USER_ID));
        }
        if (roleName != null) {
            query.addJoin(MEMBER_CATEGORY, MEMBER_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID));
        }

        if (partialName != null || partialEmail != null) {
            addSearchCondition(partialName, partialEmail, query);
        }
        if (roleName != null) {
            query.addConditions(MEMBER_CATEGORY.DISPLAY_NAME.eq(roleName));
        }
        if (roleIds != null) {
            query.addConditions(USERS_ROLES.ROLE_ID.in(roleIds));
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
        if (googleId != null) {
            query.addConditions(MEMBER.GOOGLE_ID.eq(googleId));
        }
        if (roleName != null) {
            Users_RolesTable uc = USERS_ROLES.as("ur");
            MemberCategoryTable mc = MEMBER_CATEGORY.as("mc");

            query.addConditions(USERS_ROLES.ROLE_ID.eq(
                    dslContext.select(USERS_ROLES.ROLE_ID)
                    .from(uc)
                    .innerJoin(mc).on(uc.ROLE_ID.eq(mc.ROLE_ID))
                    .where(uc.USER_ID.eq(MEMBER.ID_))
                    .orderBy(mc.SORT_ORDER.desc())
                    .limit(0,1)
            ));
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
                    //TODO: this property is owned by the activities-service
                    Field<Object> activityCount = this.dslContext.selectCount()
                            .from(ACTIVITY_ENTRY)
                            .where(ACTIVITY_ENTRY.MEMBER_ID.equal(MEMBER.ID_))
                            .asField("activityCount");
                    query.addSelect(activityCount);
                    query.addSelect(MEMBER.fields());
                    query.addOrderBy(sortColumn.isAscending()
                            ? activityCount.asc() : activityCount.desc());
                    break;
                    //TODO: this sorting doesn't work
                case "roleName":
                    Field<Object> roleNameField = dslContext.select(max(MEMBER_CATEGORY.SORT_ORDER))
                            .from(MEMBER_CATEGORY)
                            .join(USERS_ROLES).on(USERS_ROLES.ROLE_ID.eq(MEMBER_CATEGORY.ROLE_ID))
                            .where(USERS_ROLES.USER_ID.eq(MEMBER.ID_))
                            .asField("roleName");
                    query.addSelect(roleNameField);
                    query.addOrderBy(sortColumn.isAscending()
                            ? roleNameField.asc()
                            : roleNameField.desc());
                    break;
                case "points":
                    Field<Object> points =
                            this.dslContext.select(sum(POINTS.MATERIALIZED_POINTS))
                                    .from(POINTS)
                                    .where(POINTS.USER_ID.equal(MEMBER.ID_))
                                    .asField("points");
                    query.addSelect(points);
                    query.addOrderBy(sortColumn.isAscending()
                            ? points.asc() : points.desc());
                    break;
                default:
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Member.class);
    }

    private void addSearchCondition(String partialName, String partialEmail,
            SelectQuery<?> query) {
        Condition searchCondition = DSL.falseCondition();
        if (partialName != null) {
            String[] searchTerms = partialName.split("\\s");
            for (String searchTerm : searchTerms) {
                searchCondition = searchCondition
                        .or(MEMBER.SCREEN_NAME.contains(searchTerm))
                        .or(MEMBER.FIRST_NAME.contains(searchTerm))
                        .or(MEMBER.LAST_NAME.contains(searchTerm));
            }
        }
        if (partialEmail != null) {
            searchCondition = searchCondition.or(MEMBER.EMAIL_ADDRESS.contains(partialEmail));
        }
        query.addConditions(searchCondition);
    }

    @Override
    public List<Member> findByIp(String ip) {
        final SelectQuery<Record> query = dslContext.select()
                .from(MEMBER)
                .join(LOGIN_LOG).on(LOGIN_LOG.USER_ID.equal(MEMBER.ID_))
                .where(LOGIN_LOG.IP_ADDRESS.eq(ip)).getQuery();
        return query.fetchInto(Member.class);
    }

    @Override
    public List<Member> findByScreenNameName(String name) {
        final SelectQuery<Record> query = dslContext.select()
                .from(MEMBER)
                .where(MEMBER.SCREEN_NAME.like("%"+name+"%"))
                .or(MEMBER.FIRST_NAME.like("%"+name+"%"))
                .and(MEMBER.STATUS.eq(0))
                .orderBy(MEMBER.SCREEN_NAME)
                .getQuery();
        return query.fetchInto(Member.class);
    }

    @Override
    public int countByGiven(String partialName, String partialEmail, String roleName) {
        final SelectQuery<Record1<Integer>> query = dslContext.select(countDistinct(MEMBER.ID_))
                .from(MEMBER)
                .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                .join(MEMBER_CATEGORY).on(MEMBER_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .where(MEMBER.STATUS.eq(0))
                .getQuery();

        if (partialName != null || partialEmail != null) {
            addSearchCondition(partialName, partialEmail, query);
        }
        if (roleName != null) {
            query.addConditions(MEMBER_CATEGORY.DISPLAY_NAME.eq(roleName));
        }
        return query.fetchOne().into(Integer.class);
    }

    @Override
    public Optional<Member> getMember(long memberId) {
        final Record memberRecord = dslContext.select()
                .from(MEMBER)
                .where(MEMBER.ID_.eq(memberId))
                .fetchOne();
        if (memberRecord == null) {
            return Optional.empty();
        }
        return Optional.of(memberRecord.into(Member.class));
    }

    @Override
    public boolean updatePassword(long memberId, String hashedPassword) {
        return dslContext.update(MEMBER)
                .set(MEMBER.HASHED_PASSWORD, hashedPassword)
                .set(MEMBER.PASSWORD_MODIFIED_DATE, DSL.currentTimestamp())
                .set(MEMBER.MODIFIED_DATE, DSL.currentTimestamp())
                .set(MEMBER.FORGOT_PASSWORD_TOKEN, (String) null)
                .set(MEMBER.FORGOT_PASSWORD_TOKEN_EXPIRE_TIME, (Timestamp) null)
                .where(MEMBER.ID_.eq(memberId))
                .execute() > 0;
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
    public Optional<Member> findOneByScreenName(String screenName) {
        final Record record = dslContext.select()
                .from(MEMBER)
                .where(MEMBER.SCREEN_NAME.eq(screenName))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(Member.class));
    }

    @Override
    public Optional<Member> findOneByEmail(String email) {
        final Record record = dslContext.select()
                .from(MEMBER)
                .where(MEMBER.EMAIL_ADDRESS.eq(email))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(Member.class));
    }

    @Override
    public Optional<Member> findOneByLoginTokenId(String loginTokenId) {
        final Record record = dslContext.select()
                .from(MEMBER)
                .where(MEMBER.LOGIN_TOKEN_ID.eq(loginTokenId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(Member.class));
    }

    @Override
    public Optional<Member> findOneByForgotPasswordHash(String newPasswordToken) {
        final Record record = dslContext.select()
                .from(MEMBER)
                .where(MEMBER.FORGOT_PASSWORD_TOKEN.eq(newPasswordToken))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(Member.class));
    }

    @Override
    public boolean updateMember(Member member) {

        return this.dslContext.update(MEMBER)
                .set(MEMBER.UUID, member.getUuid())
                .set(MEMBER.CREATE_DATE, member.getCreateDate())
                .set(MEMBER.MODIFIED_DATE, DSL.currentTimestamp())
                .set(MEMBER.SCREEN_NAME, member.getScreenName())
                .set(MEMBER.EMAIL_ADDRESS, member.getEmailAddress())
                .set(MEMBER.IS_EMAIL_CONFIRMED, member.getIsEmailConfirmed())
                .set(MEMBER.OPEN_ID, member.getOpenId())
                .set(MEMBER.DEFAULT_LOCALE, member.getDefaultLocale())
                .set(MEMBER.FIRST_NAME, member.getFirstName())
                .set(MEMBER.LAST_NAME, member.getLastName())
                .set(MEMBER.LOGIN_DATE, member.getLoginDate())
                .set(MEMBER.LOGIN_IP, member.getLoginIP())
                .set(MEMBER.FACEBOOK_ID, member.getFacebookId())
                .set(MEMBER.GOOGLE_ID, member.getGoogleId())
                .set(MEMBER.SHORT_BIO, member.getShortBio())
                .set(MEMBER.AUTO_REGISTERED_MEMBER_STATUS, member.getAutoRegisteredMemberStatus())
                .set(MEMBER.DEFAULT_LOCALE, member.getDefaultLocale())
                .set(MEMBER.COUNTRY, member.getCountry())
                .set(MEMBER.STATUS, member.getStatus())
                .set(MEMBER.PORTRAIT_FILE_ENTRY_ID, member.getPortraitFileEntryId())
                .set(MEMBER.FORGOT_PASSWORD_TOKEN, member.getForgotPasswordToken())
                .set(MEMBER.FORGOT_PASSWORD_TOKEN_EXPIRE_TIME, member.getForgotPasswordTokenExpireTime())
                .set(MEMBER.LOGIN_TOKEN_ID, member.getLoginTokenId())
                .set(MEMBER.LOGIN_TOKEN_KEY, member.getLoginTokenKey())
                .set(MEMBER.LOGIN_TOKEN_EXPIRATION_DATE, member.getLoginTokenExpirationDate())
                .where(MEMBER.ID_.equal(member.getId_()))
                .execute() > 0;
    }

    @Override
    public void createMember(String screenName, String hashedPassword, String email,
            String firstName, String lastName, String shortBio, String country, Long facebookId,
            String openId, Long imageId, Long liferayUserId, String googleId,String defaultLocale) {
        this.dslContext.insertInto(MEMBER)
                .set(MEMBER.ID_, liferayUserId)
                .set(MEMBER.SCREEN_NAME, screenName)
                .set(MEMBER.HASHED_PASSWORD, hashedPassword)
                .set(MEMBER.EMAIL_ADDRESS, email)
                .set(MEMBER.FIRST_NAME, firstName)
                .set(MEMBER.LAST_NAME, lastName)
                .set(MEMBER.FACEBOOK_ID, facebookId)
                .set(MEMBER.GOOGLE_ID, googleId)
                .set(MEMBER.OPEN_ID, openId)
                .set(MEMBER.SHORT_BIO, shortBio)
                .set(MEMBER.DEFAULT_LOCALE, defaultLocale)
                .set(MEMBER.COUNTRY, country)
                .set(MEMBER.PORTRAIT_FILE_ENTRY_ID, imageId)
                .set(MEMBER.CREATE_DATE, new Timestamp(Instant.now().toEpochMilli()))
                .set(MEMBER.MODIFIED_DATE, new Timestamp(Instant.now().toEpochMilli()))
                .execute();
    }

    @Override
    public Integer getMemberMaterializedPoints(Long memberId) {
        return this.dslContext.select(sum(POINTS.MATERIALIZED_POINTS))
                .from(POINTS).where(POINTS.USER_ID.equal(memberId)).fetchOne(0, Integer.class);
    }

    @Override
    public Integer getMemberHypotheticalPoints(Long memberId) {
        return dslContext.select(sum(POINTS.HYPOTHETICAL_POINTS))
                .from(POINTS).where(POINTS.USER_ID.eq(memberId)).fetchOne(0, Integer.class);
    }
}
