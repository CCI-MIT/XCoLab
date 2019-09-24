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

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.SortColumn;
import org.xcolab.model.tables.MemberCategoryTable;
import org.xcolab.model.tables.UserRoleTable;
import org.xcolab.model.tables.UserTable;
import org.xcolab.model.tables.records.UserRecord;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.coalesce;
import static org.jooq.impl.DSL.concat;
import static org.jooq.impl.DSL.countDistinct;
import static org.jooq.impl.DSL.ltrim;
import static org.jooq.impl.DSL.max;
import static org.jooq.impl.DSL.nullif;
import static org.jooq.impl.DSL.sum;
import static org.jooq.impl.DSL.val;
import static org.xcolab.model.Tables.ACTIVITY_ENTRY;
import static org.xcolab.model.Tables.LOGIN_LOG;
import static org.xcolab.model.Tables.MEMBER_CATEGORY;
import static org.xcolab.model.Tables.POINTS;
import static org.xcolab.model.Tables.USER;
import static org.xcolab.model.Tables.USER_ROLE;

@Repository
public class UserDaoImpl implements UserDao {

    private final DSLContext dslContext;

    @Autowired
    public UserDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<UserWrapper> findByGiven(PaginationHelper paginationHelper, String partialName,
            String partialEmail, String roleName, String email, String screenName, Long facebookId,
            String googleId, String colabSsoId, String climateXId, List<Long> roleIds) {
        final UserTable member = USER.as("member");
        final UserRoleTable usersRoles = USER_ROLE.as("userRole");
        final MemberCategoryTable memberCategory = MEMBER_CATEGORY.as("memberCategory");

        final SelectQuery<Record> query = dslContext.selectDistinct(member.fields())
                .from(member)
                .where(member.STATUS.eq(0))
                .getQuery();

        if (roleName != null || roleIds != null) {
            query.addJoin(usersRoles, member.ID.equal(usersRoles.USER_ID));
        }
        if (roleName != null) {
            query.addJoin(memberCategory, memberCategory.ROLE_ID.equal(usersRoles.ROLE_ID));
        }

        if (partialName != null || partialEmail != null) {
            addSearchCondition(partialName, partialEmail, query,member);
        }
        if (roleName != null) {
            query.addConditions(memberCategory.DISPLAY_NAME.eq(roleName));
        }
        if (roleIds != null) {
            query.addConditions(usersRoles.ROLE_ID.in(roleIds));
        }
        if (screenName != null) {
            query.addConditions(member.SCREEN_NAME.eq(screenName));
        }
        if (email != null) {
            query.addConditions(member.EMAIL_ADDRESS.eq(email));
        }
        if (facebookId != null) {
            query.addConditions(member.FACEBOOK_ID.eq(facebookId));
        }
        if (googleId != null) {
            query.addConditions(member.GOOGLE_ID.eq(googleId));
        }
        if (colabSsoId != null) {
            query.addConditions(member.COLAB_SSO_ID.eq(colabSsoId));
        }
        if (climateXId != null) {
            query.addConditions(member.CLIMATE_X_ID.eq(climateXId));
        }
        if (roleName != null) {
            UserRoleTable userRoleInner = USER_ROLE.as("userRoleInner");
            MemberCategoryTable memberCategoryInner = MEMBER_CATEGORY.as("memberCategoryInner");

            query.addConditions(usersRoles.ROLE_ID.eq(
                    dslContext.select(userRoleInner.ROLE_ID)
                    .from(userRoleInner)
                    .innerJoin(memberCategoryInner).on(userRoleInner.ROLE_ID.eq(memberCategoryInner.ROLE_ID))
                    .where(userRoleInner.USER_ID.eq(member.ID))
                    .orderBy(memberCategoryInner.SORT_ORDER.desc())
                    .limit(0,1)
            ));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "createdAt":
                    query.addOrderBy(sortColumn.isAscending()
                            ? member.CREATED_AT.asc() : member.CREATED_AT.desc());
                    break;
                case "screenName":
                    query.addOrderBy(sortColumn.isAscending()
                            ? member.SCREEN_NAME.asc() : member.SCREEN_NAME.desc());
                    break;
                case "displayName":
                    Field<String> displayName = getDisplayName(member);
                    query.addOrderBy(sortColumn.isAscending()
                            ? displayName.asc() :  displayName.desc());
                    break;
                case "activityCount":
                    //TODO COLAB-2608: this property is owned by the activity-service
                    Field<Object> activityCount = this.dslContext.selectCount()
                            .from(ACTIVITY_ENTRY)
                            .where(ACTIVITY_ENTRY.USER_ID.equal(member.ID))
                            .asField("activityCount");
                    query.addSelect(activityCount);
                    query.addSelect(member.fields());
                    query.addOrderBy(sortColumn.isAscending()
                            ? activityCount.asc() : activityCount.desc());
                    break;
                    //TODO COLAB-2607: this sorting doesn't work
                case "roleName":
                    Field<Object> roleNameField = dslContext.select(max(MEMBER_CATEGORY.SORT_ORDER))
                            .from(MEMBER_CATEGORY)
                            .join(USER_ROLE).on(USER_ROLE.ROLE_ID.eq(MEMBER_CATEGORY.ROLE_ID))
                            .where(USER_ROLE.USER_ID.eq(member.ID))
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
                                    .where(POINTS.USER_ID.equal(member.ID))
                                    .asField("points");
                    query.addSelect(points);
                    query.addOrderBy(sortColumn.isAscending()
                            ? points.asc() : points.desc());
                    break;
                default:
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(UserWrapper.class);
    }

    private Field<String> getDisplayName(UserTable member) {
        // Concatenate first name and last name to full name. If the full name is more than just a
        // space, return the full name. Else, return the screen name.
        Field<String> firstName = coalesce(ltrim(member.FIRST_NAME), "");
        Field<String> lastName = coalesce(ltrim(member.LAST_NAME), "");
        Field<String> fullName = ltrim(concat(firstName, val(" "), lastName));
        fullName = nullif(fullName, " ");
        Field<String> screenName = member.SCREEN_NAME;
        return coalesce(fullName, screenName);
    }

    private void addSearchCondition(String partialName, String partialEmail,
            SelectQuery<?> query, UserTable memberTable) {
        Condition searchCondition = DSL.falseCondition();
        if (partialName != null) {
            String[] searchTerms = partialName.split("\\s");
            for (String searchTerm : searchTerms) {
                searchCondition = searchCondition
                        .or(memberTable.SCREEN_NAME.contains(searchTerm))
                        .or(memberTable.FIRST_NAME.contains(searchTerm))
                        .or(memberTable.LAST_NAME.contains(searchTerm));
            }
        }
        if (partialEmail != null) {
            searchCondition = searchCondition.or(memberTable.EMAIL_ADDRESS.contains(partialEmail));
        }
        query.addConditions(searchCondition);
    }

    @Override
    public List<UserWrapper> findByIp(String ip) {
        final SelectQuery<Record> query = dslContext
                .selectDistinct(USER.fields())
                .from(USER)
                .join(LOGIN_LOG).on(LOGIN_LOG.USER_ID.equal(USER.ID))
                .where(LOGIN_LOG.IP_ADDRESS.eq(ip))
                .getQuery();
        return query.fetchInto(UserWrapper.class);
    }

    @Override
    public List<UserWrapper> findByScreenNameName(String name) {
        final SelectQuery<Record> query = dslContext.select()
                .from(USER)
                .where(USER.SCREEN_NAME.like("%"+name+"%"))
                .or(USER.FIRST_NAME.like("%"+name+"%"))
                .and(USER.STATUS.eq(0))
                .orderBy(USER.SCREEN_NAME)
                .getQuery();
        return query.fetchInto(UserWrapper.class);
    }

    @Override
    public int countByGiven(String partialName, String partialEmail, String roleName) {

        final UserTable memberTable = USER.as("member");
        final UserRoleTable usersRoles = USER_ROLE.as("usersRoles");
        final MemberCategoryTable memberCategory = MEMBER_CATEGORY.as("memberCategory");
        final SelectQuery<Record1<Integer>> query = dslContext.select(countDistinct(memberTable.ID))
                .from(memberTable)
                .join(usersRoles).on(memberTable.ID.equal(usersRoles.USER_ID))
                .join(memberCategory).on(memberCategory.ROLE_ID.equal(usersRoles.ROLE_ID))
                .where(memberTable.STATUS.eq(0))
                .getQuery();

        if (partialName != null || partialEmail != null) {
            addSearchCondition(partialName, partialEmail, query,memberTable);
        }
        if (roleName != null) {
            query.addConditions(memberCategory.DISPLAY_NAME.eq(roleName));
        }
        return query.fetchOne().into(Integer.class);
    }

    @Override
    public Optional<UserWrapper> getUser(long userId) {
        final Record memberRecord = dslContext.select()
                .from(USER)
                .where(USER.ID.eq(userId))
                .fetchOne();
        if (memberRecord == null) {
            return Optional.empty();
        }
        return Optional.of(memberRecord.into(UserWrapper.class));
    }

    @Override
    public boolean updatePassword(long userId, String hashedPassword) {
        return dslContext.update(USER)
                .set(USER.HASHED_PASSWORD, hashedPassword)
                .set(USER.PASSWORD_UPDATED_AT, DSL.currentTimestamp())
                .set(USER.UPDATED_AT, DSL.currentTimestamp())
                .set(USER.FORGOT_PASSWORD_TOKEN, (String) null)
                .set(USER.FORGOT_PASSWORD_TOKEN_EXPIRE_TIME, (Timestamp) null)
                .where(USER.ID.eq(userId))
                .execute() > 0;
    }


    @Override
    public boolean isScreenNameTaken(String screenName) {
        return dslContext.selectCount()
                .from(USER)
                .where(USER.SCREEN_NAME.eq(screenName))
                .fetchOne(0, Integer.class) > 0;
    }

    @Override
    public boolean isEmailUsed(String email) {
        return dslContext.selectCount()
                .from(USER)
                .where(USER.EMAIL_ADDRESS.eq(email))
                .fetchOne(0, Integer.class) > 0;
    }

    @Override
    public Optional<UserWrapper> findOneByScreenName(String screenName) {
        final Record record = dslContext.select()
                .from(USER)
                .where(USER.SCREEN_NAME.eq(screenName))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(UserWrapper.class));
    }

    @Override
    public Optional<UserWrapper> findOneByEmail(String email) {
        final Record record = dslContext.select()
                .from(USER)
                .where(USER.EMAIL_ADDRESS.eq(email))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(UserWrapper.class));
    }

    @Override
    public Optional<UserWrapper> findOneByLoginTokenId(String loginTokenId) {
        final Record record = dslContext.select()
                .from(USER)
                .where(USER.LOGIN_TOKEN_ID.eq(loginTokenId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(UserWrapper.class));
    }

    @Override
    public Optional<UserWrapper> findOneByForgotPasswordHash(String newPasswordToken) {
        final Record record = dslContext.select()
                .from(USER)
                .where(USER.FORGOT_PASSWORD_TOKEN.eq(newPasswordToken))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(UserWrapper.class));
    }

    @Override
    public boolean updateUser(UserWrapper member) {

        return this.dslContext.update(USER)
                .set(USER.UUID, member.getUuid())
                .set(USER.UPDATED_AT, DSL.currentTimestamp())
                .set(USER.SCREEN_NAME, member.getScreenName())
                .set(USER.EMAIL_ADDRESS, member.getEmailAddress())
                .set(USER.IS_EMAIL_CONFIRMED, member.isIsEmailConfirmed())
                .set(USER.IS_EMAIL_BOUNCED, member.isIsEmailBounced())
                .set(USER.OPEN_ID, member.getOpenId())
                .set(USER.DEFAULT_LOCALE, member.getDefaultLocale())
                .set(USER.FIRST_NAME, member.getFirstName())
                .set(USER.LAST_NAME, member.getLastName())
                .set(USER.LOGIN_DATE, member.getLoginDate())
                .set(USER.LOGIN_IP, member.getLoginIp())
                .set(USER.FACEBOOK_ID, member.getFacebookId())
                .set(USER.GOOGLE_ID, member.getGoogleId())
                .set(USER.COLAB_SSO_ID, member.getColabSsoId())
                .set(USER.CLIMATE_X_ID, member.getClimateXId())
                .set(USER.SHORT_BIO, member.getShortBio())
                .set(USER.AUTO_REGISTERED_MEMBER_STATUS, member.getAutoRegisteredMemberStatus())
                .set(USER.DEFAULT_LOCALE, member.getDefaultLocale())
                .set(USER.COUNTRY, member.getCountry())
                .set(USER.STATUS, member.getStatus())
                .set(USER.PORTRAIT_FILE_ENTRY_ID, member.getPortraitFileEntryId())
                .set(USER.FORGOT_PASSWORD_TOKEN, member.getForgotPasswordToken())
                .set(USER.FORGOT_PASSWORD_TOKEN_EXPIRE_TIME, member.getForgotPasswordTokenExpireTime())
                .set(USER.LOGIN_TOKEN_ID, member.getLoginTokenId())
                .set(USER.LOGIN_TOKEN_KEY, member.getLoginTokenKey())
                .set(USER.LOGIN_TOKEN_EXPIRATION_DATE, member.getLoginTokenExpirationDate())
                .where(USER.ID.equal(member.getId()))
                .execute() > 0;
    }

    @Override
    public UserWrapper createUser(UserWrapper member) {
        final Optional<UserRecord> memberRecord =
                dslContext.insertInto(USER)
                        .set(USER.UUID, member.getUuid())
                        .set(USER.SCREEN_NAME, member.getScreenName())
                        .set(USER.EMAIL_ADDRESS, member.getEmailAddress())
                        .set(USER.IS_EMAIL_CONFIRMED, member.isIsEmailConfirmed())
                        .set(USER.IS_EMAIL_BOUNCED, member.isIsEmailBounced())
                        .set(USER.OPEN_ID, member.getOpenId())
                        .set(USER.DEFAULT_LOCALE, member.getDefaultLocale())
                        .set(USER.FIRST_NAME, member.getFirstName())
                        .set(USER.LAST_NAME, member.getLastName())
                        .set(USER.LOGIN_DATE, member.getLoginDate())
                        .set(USER.LOGIN_IP, member.getLoginIp())
                        .set(USER.HASHED_PASSWORD, member.getHashedPassword())
                        .set(USER.FACEBOOK_ID, member.getFacebookId())
                        .set(USER.GOOGLE_ID, member.getGoogleId())
                        .set(USER.COLAB_SSO_ID, member.getColabSsoId())
                        .set(USER.CLIMATE_X_ID, member.getClimateXId())
                        .set(USER.SHORT_BIO, member.getShortBio())
                        .set(USER.AUTO_REGISTERED_MEMBER_STATUS, member.getAutoRegisteredMemberStatus())
                        .set(USER.DEFAULT_LOCALE, member.getDefaultLocale())
                        .set(USER.COUNTRY, member.getCountry())
                        .set(USER.STATUS, member.getStatus())
                        .set(USER.PORTRAIT_FILE_ENTRY_ID, member.getPortraitFileEntryId())
                        .set(USER.FORGOT_PASSWORD_TOKEN, member.getForgotPasswordToken())
                        .set(USER.FORGOT_PASSWORD_TOKEN_EXPIRE_TIME, member.getForgotPasswordTokenExpireTime())
                        .set(USER.LOGIN_TOKEN_ID, member.getLoginTokenId())
                        .set(USER.LOGIN_TOKEN_KEY, member.getLoginTokenKey())
                        .set(USER.LOGIN_TOKEN_EXPIRATION_DATE, member.getLoginTokenExpirationDate())
                        .set(USER.CREATED_AT, DSL.currentTimestamp())
                        .set(USER.UPDATED_AT, DSL.currentTimestamp())
                        .returning(USER.ID)
                        .fetchOptional();
        if (!memberRecord.isPresent()) {
            throw new IllegalStateException("Could not fetch generated ID");
        }
        member.setId(memberRecord.get().getValue(USER.ID));
        return member;
    }

    @Override
    public Integer getUserMaterializedPoints(Long userId) {
        return this.dslContext.select(sum(POINTS.MATERIALIZED_POINTS))
                .from(POINTS).where(POINTS.USER_ID.equal(userId)).fetchOne(0, Integer.class);
    }

    @Override
    public Integer getUserHypotheticalPoints(Long userId) {
        return dslContext.select(sum(POINTS.HYPOTHETICAL_POINTS))
                .from(POINTS).where(POINTS.USER_ID.eq(userId)).fetchOne(0, Integer.class);
    }
}
