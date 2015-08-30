package com.ext.portlet.service.impl.mock;

import java.util.List;
import java.util.Locale;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalService;

/**
 * <p>A mock class that realizes a counter contract.</p>
 * 
 * @author janusz
 *
 */
public class UserLocalServiceMock implements UserLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link UserLocalServiceUtil} to access the user local service. Add custom service methods to {@link com.liferay.portal.service.impl.UserLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the user to the database. Also notifies the appropriate model listeners.
    *
    * @param user the user
    * @return the user that was added
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User addUser(
        com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Creates a new user with the primary key. Does not add the user to the database.
    *
    * @param userId the primary key for the new user
    * @return the new user
    */
    public com.liferay.portal.model.User createUser(long userId) {
        return null;
    }


    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User fetchUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the primary key.
    *
    * @param userId the primary key of the user
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUser(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns a range of all the users.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @return the range of users
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUsers(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the number of users.
    *
    * @return the number of users
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUsersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Updates the user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param user the user
    * @return the user that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateUser(
        com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param user the user
    * @param merge whether to merge the user with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the user that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateUser(
        com.liferay.portal.model.User user, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return null;
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
    }

    /**
    * Adds the user to the default groups, unless the user is already in these
    * groups. The default groups can be specified in
    * <code>portal.properties</code> with the key
    * <code>admin.default.group.names</code>.
    *
    * @param userId the primary key of the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void addDefaultGroups(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds the user to the default roles, unless the user already has these
    * roles. The default roles can be specified in
    * <code>portal.properties</code> with the key
    * <code>admin.default.role.names</code>.
    *
    * @param userId the primary key of the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void addDefaultRoles(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds the user to the default user groups, unless the user is already in
    * these user groups. The default user groups can be specified in
    * <code>portal.properties</code> with the property
    * <code>admin.default.user.group.names</code>.
    *
    * @param userId the primary key of the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void addDefaultUserGroups(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds the users to the group.
    *
    * @param groupId the primary key of the group
    * @param userIds the primary keys of the users
    * @throws PortalException if a group or user with the primary key could not
    be found
    * @throws SystemException if a system exception occurred
    */
    public void addGroupUsers(long groupId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds the users to the organization.
    *
    * @param organizationId the primary key of the organization
    * @param userIds the primary keys of the users
    * @throws PortalException if an organization or user with the primary key
    could not be found
    * @throws SystemException if a system exception occurred
    */
    public void addOrganizationUsers(long organizationId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Assigns the password policy to the users, removing any other currently
    * assigned password policies.
    *
    * @param passwordPolicyId the primary key of the password policy
    * @param userIds the primary keys of the users
    * @throws SystemException if a system exception occurred
    */
    public void addPasswordPolicyUsers(long passwordPolicyId, long[] userIds)
        throws com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds the users to the role.
    *
    * @param roleId the primary key of the role
    * @param userIds the primary keys of the users
    * @throws PortalException if a role or user with the primary key could not
    be found
    * @throws SystemException if a system exception occurred
    */
    public void addRoleUsers(long roleId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds the users to the team.
    *
    * @param teamId the primary key of the team
    * @param userIds the primary keys of the users
    * @throws PortalException if a team or user with the primary key could not
    be found
    * @throws SystemException if a system exception occurred
    */
    public void addTeamUsers(long teamId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds a user.
    *
    * <p>
    * This method handles the creation and bookkeeping of the user including
    * its resources, metadata, and internal data structures. It is not
    * necessary to make subsequent calls to any methods to setup default
    * groups, resources, etc.
    * </p>
    *
    * @param creatorUserId the primary key of the creator
    * @param companyId the primary key of the user's company
    * @param autoPassword whether a password should be automatically generated
    for the user
    * @param password1 the user's password
    * @param password2 the user's password confirmation
    * @param autoScreenName whether a screen name should be automatically
    generated for the user
    * @param screenName the user's screen name
    * @param emailAddress the user's email address
    * @param facebookId the user's facebook ID
    * @param openId the user's OpenID
    * @param locale the user's locale
    * @param firstName the user's first name
    * @param middleName the user's middle name
    * @param lastName the user's last name
    * @param prefixId the user's name prefix ID
    * @param suffixId the user's name suffix ID
    * @param male whether the user is male
    * @param birthdayMonth the user's birthday month (0-based, meaning 0 for
    January)
    * @param birthdayDay the user's birthday day
    * @param birthdayYear the user's birthday year
    * @param jobTitle the user's job title
    * @param groupIds the primary keys of the user's groups
    * @param organizationIds the primary keys of the user's organizations
    * @param roleIds the primary keys of the roles this user possesses
    * @param userGroupIds the primary keys of the user's user groups
    * @param sendEmail whether to send the user an email notification about
    their new account
    * @param serviceContext the user's service context (optionally
    <code>null</code>). Can set the universally unique identifier
    (with the <code>uuid</code> attribute), asset category IDs, asset
    tag names, and expando bridge attributes for the user.
    * @return the new user
    * @throws PortalException if the user's information was invalid
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User addUser(long creatorUserId,
        long companyId, boolean autoPassword, java.lang.String password1,
        java.lang.String password2, boolean autoScreenName,
        java.lang.String screenName, java.lang.String emailAddress,
        long facebookId, java.lang.String openId, java.util.Locale locale,
        java.lang.String firstName, java.lang.String middleName,
        java.lang.String lastName, int prefixId, int suffixId, boolean male,
        int birthdayMonth, int birthdayDay, int birthdayYear,
        java.lang.String jobTitle, long[] groupIds, long[] organizationIds,
        long[] roleIds, long[] userGroupIds, boolean sendEmail,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Adds the users to the user group.
    *
    * @param userGroupId the primary key of the user group
    * @param userIds the primary keys of the users
    * @throws PortalException if a user group or user with the primary could
    could not be found
    * @throws SystemException if a system exception occurred
    */
    public void addUserGroupUsers(long userGroupId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds a user with workflow.
    *
    * <p>
    * This method handles the creation and bookkeeping of the user including
    * its resources, metadata, and internal data structures. It is not
    * necessary to make subsequent calls to any methods to setup default
    * groups, resources, etc.
    * </p>
    *
    * @param creatorUserId the primary key of the creator
    * @param companyId the primary key of the user's company
    * @param autoPassword whether a password should be automatically generated
    for the user
    * @param password1 the user's password
    * @param password2 the user's password confirmation
    * @param autoScreenName whether a screen name should be automatically
    generated for the user
    * @param screenName the user's screen name
    * @param emailAddress the user's email address
    * @param facebookId the user's facebook ID
    * @param openId the user's OpenID
    * @param locale the user's locale
    * @param firstName the user's first name
    * @param middleName the user's middle name
    * @param lastName the user's last name
    * @param prefixId the user's name prefix ID
    * @param suffixId the user's name suffix ID
    * @param male whether the user is male
    * @param birthdayMonth the user's birthday month (0-based, meaning 0 for
    January)
    * @param birthdayDay the user's birthday day
    * @param birthdayYear the user's birthday year
    * @param jobTitle the user's job title
    * @param groupIds the primary keys of the user's groups
    * @param organizationIds the primary keys of the user's organizations
    * @param roleIds the primary keys of the roles this user possesses
    * @param userGroupIds the primary keys of the user's user groups
    * @param sendEmail whether to send the user an email notification about
    their new account
    * @param serviceContext the user's service context (optionally
    <code>null</code>). Can set the universally unique identifier
    (with the <code>uuid</code> attribute), asset category IDs, asset
    tag names, and expando bridge attributes for the user.
    * @return the new user
    * @throws PortalException if the user's information was invalid
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User addUserWithWorkflow(
        long creatorUserId, long companyId, boolean autoPassword,
        java.lang.String password1, java.lang.String password2,
        boolean autoScreenName, java.lang.String screenName,
        java.lang.String emailAddress, long facebookId,
        java.lang.String openId, java.util.Locale locale,
        java.lang.String firstName, java.lang.String middleName,
        java.lang.String lastName, int prefixId, int suffixId, boolean male,
        int birthdayMonth, int birthdayDay, int birthdayYear,
        java.lang.String jobTitle, long[] groupIds, long[] organizationIds,
        long[] roleIds, long[] userGroupIds, boolean sendEmail,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Attempts to authenticate the user by their email address and password,
    * while using the AuthPipeline.
    *
    * @param companyId the primary key of the user's company
    * @param emailAddress the user's email address
    * @param password the user's password
    * @param headerMap the header map from the authentication request
    * @param parameterMap the parameter map from the authentication request
    * @param resultsMap the map of authentication results (may be nil). After
    a succesful authentication the user's primary key will be placed
    under the key <code>userId</code>.
    * @return the authentication status. This can be {@link
    com.liferay.portal.security.auth.Authenticator#FAILURE}
    indicating that the user's credentials are invalid, {@link
    com.liferay.portal.security.auth.Authenticator#SUCCESS}
    indicating a successful login, or {@link
    com.liferay.portal.security.auth.Authenticator#DNE} indicating
    that a user with that login does not exist.
    * @throws PortalException if <code>emailAddress</code> or
    <code>password</code> was <code>null</code>
    * @throws SystemException if a system exception occurred
    * @see com.liferay.portal.security.auth.AuthPipeline
    */
    public int authenticateByEmailAddress(long companyId,
        java.lang.String emailAddress, java.lang.String password,
        java.util.Map<java.lang.String, java.lang.String[]> headerMap,
        java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
        java.util.Map<java.lang.String, java.lang.Object> resultsMap)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Attempts to authenticate the user by their screen name and password,
    * while using the AuthPipeline.
    *
    * @param companyId the primary key of the user's company
    * @param screenName the user's screen name
    * @param password the user's password
    * @param headerMap the header map from the authentication request
    * @param parameterMap the parameter map from the authentication request
    * @param resultsMap the map of authentication results (may be nil). After
    a succesful authentication the user's primary key will be placed
    under the key <code>userId</code>.
    * @return the authentication status. This can be {@link
    com.liferay.portal.security.auth.Authenticator#FAILURE}
    indicating that the user's credentials are invalid, {@link
    com.liferay.portal.security.auth.Authenticator#SUCCESS}
    indicating a successful login, or {@link
    com.liferay.portal.security.auth.Authenticator#DNE} indicating
    that a user with that login does not exist.
    * @throws PortalException if <code>screenName</code> or
    <code>password</code> was <code>null</code>
    * @throws SystemException if a system exception occurred
    * @see com.liferay.portal.security.auth.AuthPipeline
    */
    public int authenticateByScreenName(long companyId,
        java.lang.String screenName, java.lang.String password,
        java.util.Map<java.lang.String, java.lang.String[]> headerMap,
        java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
        java.util.Map<java.lang.String, java.lang.Object> resultsMap)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Attempts to authenticate the user by their primary key and password,
    * while using the AuthPipeline.
    *
    * @param companyId the primary key of the user's company
    * @param userId the user's primary key
    * @param password the user's password
    * @param headerMap the header map from the authentication request
    * @param parameterMap the parameter map from the authentication request
    * @param resultsMap the map of authentication results (may be nil). After
    a succesful authentication the user's primary key will be placed
    under the key <code>userId</code>.
    * @return the authentication status. This can be {@link
    com.liferay.portal.security.auth.Authenticator#FAILURE}
    indicating that the user's credentials are invalid, {@link
    com.liferay.portal.security.auth.Authenticator#SUCCESS}
    indicating a successful login, or {@link
    com.liferay.portal.security.auth.Authenticator#DNE} indicating
    that a user with that login does not exist.
    * @throws PortalException if <code>userId</code> or <code>password</code>
    was <code>null</code>
    * @throws SystemException if a system exception occurred
    * @see com.liferay.portal.security.auth.AuthPipeline
    */
    public int authenticateByUserId(long companyId, long userId,
        java.lang.String password,
        java.util.Map<java.lang.String, java.lang.String[]> headerMap,
        java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
        java.util.Map<java.lang.String, java.lang.Object> resultsMap)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Attempts to authenticate the user using HTTP basic access authentication,
    * without using the AuthPipeline. Primarily used for authenticating users
    * of <code>tunnel-web</code>.
    *
    * <p>
    * Authentication type specifies what <code>login</code> contains.The valid
    * values are:
    * </p>
    *
    * <ul>
    * <li>
    * <code>CompanyConstants.AUTH_TYPE_EA</code> - <code>login</code> is the
    * user's email address
    * </li>
    * <li>
    * <code>CompanyConstants.AUTH_TYPE_SN</code> - <code>login</code> is the
    * user's screen name
    * </li>
    * <li>
    * <code>CompanyConstants.AUTH_TYPE_ID</code> - <code>login</code> is the
    * user's primary key
    * </li>
    * </ul>
    *
    * @param companyId the primary key of the user's company
    * @param authType the type of authentication to perform
    * @param login either the user's email address, screen name, or primary
    key depending on the value of <code>authType</code>
    * @param password the user's password
    * @return the authentication status. This can be {@link
    com.liferay.portal.security.auth.Authenticator#FAILURE}
    indicating that the user's credentials are invalid, {@link
    com.liferay.portal.security.auth.Authenticator#SUCCESS}
    indicating a successful login, or {@link
    com.liferay.portal.security.auth.Authenticator#DNE} indicating
    that a user with that login does not exist.
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public long authenticateForBasic(long companyId, java.lang.String authType,
        java.lang.String login, java.lang.String password)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Attempts to authenticate the user using HTTP digest access
    * authentication, without using the AuthPipeline. Primarily used for
    * authenticating users of <code>tunnel-web</code>.
    *
    * @param companyId the primary key of the user's company
    * @param username either the user's email address, screen name, or primary
    key
    * @param realm unused
    * @param nonce the number used once
    * @param method the request method
    * @param uri the request URI
    * @param response the authentication response hash
    * @return the user's primary key if authentication is succesful;
    <code>0</code> otherwise
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public long authenticateForDigest(long companyId,
        java.lang.String username, java.lang.String realm,
        java.lang.String nonce, java.lang.String method, java.lang.String uri,
        java.lang.String response)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Attempts to authenticate the user using JAAS credentials, without using
    * the AuthPipeline.
    *
    * @param userId the primary key of the user
    * @param encPassword the encrypted password
    * @return <code>true</code> if authentication is successful;
    <code>false</code> otherwise
    */
    public boolean authenticateForJAAS(long userId, java.lang.String encPassword) {
        return false;
    }

    /**
    * Checks if the user is currently locked out based on the password policy,
    * and performs maintenance on the user's lockout and failed login data.
    *
    * @param user the user
    * @throws PortalException if the user was determined to still be locked out
    * @throws SystemException if a system exception occurred
    */
    public void checkLockout(com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds a failed login attempt to the user and updates the user's last
    * failed login date.
    *
    * @param user the user
    * @throws SystemException if a system exception occurred
    */
    public void checkLoginFailure(com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds a failed login attempt to the user with the email address and
    * updates the user's last failed login date.
    *
    * @param companyId the primary key of the user's company
    * @param emailAddress the user's email address
    * @throws PortalException if a user with the email address could not be
    found
    * @throws SystemException if a system exception occurred
    */
    public void checkLoginFailureByEmailAddress(long companyId,
        java.lang.String emailAddress)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds a failed login attempt to the user and updates the user's last
    * failed login date.
    *
    * @param userId the primary key of the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void checkLoginFailureById(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Adds a failed login attempt to the user with the screen name and updates
    * the user's last failed login date.
    *
    * @param companyId the primary key of the user's company
    * @param screenName the user's screen name
    * @throws PortalException if a user with the screen name could not be found
    * @throws SystemException if a system exception occurred
    */
    public void checkLoginFailureByScreenName(long companyId,
        java.lang.String screenName)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Checks if the user's password is expired based on the password policy,
    * and performs maintenance on the user's grace login and password reset
    * data.
    *
    * @param user the user
    * @throws PortalException if the user's password has expired and the grace
    login limit has been exceeded
    * @throws SystemException if a system exception occurred
    */
    public void checkPasswordExpired(com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes all the users from the organization.
    *
    * @param organizationId the primary key of the organization
    * @throws SystemException if a system exception occurred
    */
    public void clearOrganizationUsers(long organizationId)
        throws com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes all the users from the user group.
    *
    * @param userGroupId the primary key of the user group
    * @throws SystemException if a system exception occurred
    */
    public void clearUserGroupUsers(long userGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Completes the user's registration by generating a password and sending
    * the confirmation email.
    *
    * @param user the user
    * @param serviceContext the user's service context. Can set whether a
    password should be generated (with the <code>autoPassword</code>
    attribute) and whether the confirmation email should be sent
    (with the <code>sendEmail</code> attribute) for the user.
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void completeUserRegistration(com.liferay.portal.model.User user,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Decrypts the user's primary key and password from their encrypted forms.
    * Used for decrypting a user's credentials from the values stored in an
    * automatic login cookie.
    *
    * @param companyId the primary key of the user's company
    * @param name the encrypted primary key of the user
    * @param password the encrypted password of the user
    * @return the user's primary key and password
    * @throws PortalException if a user with the primary key could not be found
    or if the user's password was incorrect
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.kernel.util.KeyValuePair decryptUserId(
        long companyId, java.lang.String name, java.lang.String password)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Deletes the user's portrait image.
    *
    * @param userId the primary key of the user
    * @throws PortalException if a user with the primary key could not be found
    or if the user's portrait could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePortrait(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes the user from the role.
    *
    * @param roleId the primary key of the role
    * @param userId the primary key of the user
    * @throws PortalException if a role or user with the primary key could not
    be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteRoleUser(long roleId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes the user from the user group.
    *
    * @param userGroupId the primary key of the user group
    * @param userId the primary key of the user
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void deleteUserGroupUser(long userGroupId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Encrypts the primary key of the user. Used when encrypting the user's
    * credentials for storage in an automatic login cookie.
    *
    * @param name the primary key of the user
    * @return the user's encrypted primary key
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String encryptUserId(java.lang.String name)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the primary key.
    *
    * @param userId the primary key of the user
    * @return the user with the primary key, or <code>null</code> if a user
    with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User fetchUserById(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the screen name.
    *
    * @param companyId the primary key of the user's company
    * @param screenName the user's screen name
    * @return the user with the screen name, or <code>null</code> if a user
    with the screen name could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User fetchUserByScreenName(long companyId,
        java.lang.String screenName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns a range of all the users belonging to the company.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param companyId the primary key of the company
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @return the range of users belonging to the company
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getCompanyUsers(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the number of users belonging to the company.
    *
    * @param companyId the primary key of the company
    * @return the number of users belonging to the company
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getCompanyUsersCount(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the default user for the company.
    *
    * @param companyId the primary key of the company
    * @return the default user for the company
    * @throws PortalException if a default user for the company could not be
    found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getDefaultUser(long companyId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the primary key of the default user for the company.
    *
    * @param companyId the primary key of the company
    * @return the primary key of the default user for the company
    * @throws PortalException if a default user for the company could not be
    found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getDefaultUserId(long companyId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the primary keys of all the users belonging to the group.
    *
    * @param groupId the primary key of the group
    * @return the primary keys of the users belonging to the group
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long[] getGroupUserIds(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns all the users belonging to the group.
    *
    * @param groupId the primary key of the group
    * @return the users belonging to the group
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getGroupUsers(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the number of users belonging to the group.
    *
    * @param groupId the primary key of the group
    * @return the number of users belonging to the group
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getGroupUsersCount(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the number of users with the status belonging to the group.
    *
    * @param groupId the primary key of the group
    * @param status the workflow status
    * @return the number of users with the status belonging to the group
    * @throws PortalException if a group with the primary key could not be
    found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getGroupUsersCount(long groupId, int status)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns all the users who have not had any announcements of the type
    * delivered, excluding the default user.
    *
    * @param type the type of announcement
    * @return the users who have not had any annoucements of the type delivered
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getNoAnnouncementsDeliveries(
        java.lang.String type)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns all the users who do not have any contacts.
    *
    * @return the users who do not have any contacts
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getNoContacts()
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns all the users who do not belong to any groups, excluding the
    * default user.
    *
    * @return the users who do not belong to any groups
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getNoGroups()
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the primary keys of all the users belonging to the organization.
    *
    * @param organizationId the primary key of the organization
    * @return the primary keys of the users belonging to the organization
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long[] getOrganizationUserIds(long organizationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns all the users belonging to the organization.
    *
    * @param organizationId the primary key of the organization
    * @return the users belonging to the organization
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getOrganizationUsers(
        long organizationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the number of users belonging to the organization.
    *
    * @param organizationId the primary key of the organization
    * @return the number of users belonging to the organization
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getOrganizationUsersCount(long organizationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the number of users with the status belonging to the
    * organization.
    *
    * @param organizationId the primary key of the organization
    * @param status the workflow status
    * @return the number of users with the status belonging to the organization
    * @throws PortalException if an organization with the primary key could not
    be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getOrganizationUsersCount(long organizationId, int status)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the primary keys of all the users belonging to the role.
    *
    * @param roleId the primary key of the role
    * @return the primary keys of the users belonging to the role
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long[] getRoleUserIds(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns all the users belonging to the role.
    *
    * @param roleId the primary key of the role
    * @return the users belonging to the role
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getRoleUsers(
        long roleId) throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns a range of all the users belonging to the role.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param roleId the primary key of the role
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @return the range of users belonging to the role
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getRoleUsers(
        long roleId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the number of users belonging to the role.
    *
    * @param roleId the primary key of the role
    * @return the number of users belonging to the role
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getRoleUsersCount(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the number of users with the status belonging to the role.
    *
    * @param roleId the primary key of the role
    * @param status the workflow status
    * @return the number of users with the status belonging to the role
    * @throws PortalException if an role with the primary key could not be
    found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getRoleUsersCount(long roleId, int status)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns an ordered range of all the users with a social relation of the
    * type with the user.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param userId the primary key of the user
    * @param type the type of social relation. The possible types can be found
    in {@link
    com.liferay.portlet.social.model.SocialRelationConstants}.
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @param obc the comparator to order the users by (optionally
    <code>null</code>)
    * @return the ordered range of users with a social relation of the type
    with the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getSocialUsers(
        long userId, int type, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns an ordered range of all the users with a social relation with the
    * user.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param userId the primary key of the user
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @param obc the comparator to order the users by (optionally
    <code>null</code>)
    * @return the ordered range of users with a social relation with the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getSocialUsers(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns an ordered range of all the users with a mutual social relation
    * of the type with both of the given users.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param userId1 the primary key of the first user
    * @param userId2 the primary key of the second user
    * @param type the type of social relation. The possible types can be found
    in {@link
    com.liferay.portlet.social.model.SocialRelationConstants}.
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @param obc the comparator to order the users by (optionally
    <code>null</code>)
    * @return the ordered range of users with a mutual social relation of the
    type with the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getSocialUsers(
        long userId1, long userId2, int type, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns an ordered range of all the users with a mutual social relation
    * with both of the given users.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param userId1 the primary key of the first user
    * @param userId2 the primary key of the second user
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @param obc the comparator to order the users by (optionally
    <code>null</code>)
    * @return the ordered range of users with a mutual social relation with the
    user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getSocialUsers(
        long userId1, long userId2, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the number of users with a social relation with the user.
    *
    * @param userId the primary key of the user
    * @return the number of users with a social relation with the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getSocialUsersCount(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the number of users with a social relation of the type with the
    * user.
    *
    * @param userId the primary key of the user
    * @param type the type of social relation. The possible types can be found
    in {@link
    com.liferay.portlet.social.model.SocialRelationConstants}.
    * @return the number of users with a social relation of the type with the
    user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getSocialUsersCount(long userId, int type)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the number of users with a mutual social relation with both of
    * the given users.
    *
    * @param userId1 the primary key of the first user
    * @param userId2 the primary key of the second user
    * @return the number of users with a mutual social relation with the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getSocialUsersCount(long userId1, long userId2)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the number of users with a mutual social relation of the type
    * with both of the given users.
    *
    * @param userId1 the primary key of the first user
    * @param userId2 the primary key of the second user
    * @param type the type of social relation. The possible types can be found
    in {@link
    com.liferay.portlet.social.model.SocialRelationConstants}.
    * @return the number of users with a mutual social relation of the type
    with the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getSocialUsersCount(long userId1, long userId2, int type)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the user with the contact ID.
    *
    * @param contactId the user's contact ID
    * @return the user with the contact ID
    * @throws PortalException if a user with the contact ID could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUserByContactId(long contactId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the email address.
    *
    * @param companyId the primary key of the user's company
    * @param emailAddress the user's email address
    * @return the user with the email address
    * @throws PortalException if a user with the email address could not be
    found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUserByEmailAddress(long companyId,
        java.lang.String emailAddress)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the Facebook ID.
    *
    * @param companyId the primary key of the user's company
    * @param facebookId the user's Facebook ID
    * @return the user with the Facebook ID
    * @throws PortalException if a user with the Facebook ID could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUserByFacebookId(long companyId,
        long facebookId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the primary key.
    *
    * @param userId the primary key of the user
    * @return the user with the primary key
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUserById(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the primary key from the company.
    *
    * @param companyId the primary key of the user's company
    * @param userId the primary key of the user
    * @return the user with the primary key
    * @throws PortalException if a user with the primary key from the company
    could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUserById(long companyId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the OpenID.
    *
    * @param companyId the primary key of the user's company
    * @param openId the user's OpenID
    * @return the user with the OpenID
    * @throws PortalException if a user with the OpenID could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUserByOpenId(long companyId,
        java.lang.String openId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the portrait ID.
    *
    * @param portraitId the user's portrait ID
    * @return the user with the portrait ID
    * @throws PortalException if a user with the portrait ID could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUserByPortraitId(long portraitId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the screen name.
    *
    * @param companyId the primary key of the user's company
    * @param screenName the user's screen name
    * @return the user with the screen name
    * @throws PortalException if a user with the screen name could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUserByScreenName(long companyId,
        java.lang.String screenName)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the user with the universally unique identifier.
    *
    * @param uuid the user's universally unique identifier
    * @return the user with the universally unique identifier
    * @throws PortalException if a user with the universally unique identifier
    could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUserByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns all the users belonging to the user group.
    *
    * @param userGroupId the primary key of the user group
    * @return the users belonging to the user group
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getUserGroupUsers(
        long userGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the number of users belonging to the user group.
    *
    * @param userGroupId the primary key of the user group
    * @return the number of users belonging to the user group
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUserGroupUsersCount(long userGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the number of users with the status belonging to the user group.
    *
    * @param userGroupId the primary key of the user group
    * @param status the workflow status
    * @return the number of users with the status belonging to the user group
    * @throws PortalException if a user group with the primary key could not be
    found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUserGroupUsersCount(long userGroupId, int status)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the primary key of the user with the email address.
    *
    * @param companyId the primary key of the user's company
    * @param emailAddress the user's email address
    * @return the primary key of the user with the email address
    * @throws PortalException if a user with the email address could not be
    found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getUserIdByEmailAddress(long companyId,
        java.lang.String emailAddress)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the primary key of the user with the screen name.
    *
    * @param companyId the primary key of the user's company
    * @param screenName the user's screen name
    * @return the primary key of the user with the screen name
    * @throws PortalException if a user with the screen name could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getUserIdByScreenName(long companyId,
        java.lang.String screenName)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns <code>true</code> if the user is a member of the group.
    *
    * @param groupId the primary key of the group
    * @param userId the primary key of the user
    * @return <code>true</code> if the user is a member of the group;
    <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasGroupUser(long groupId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return false;
    }

    /**
    * Returns <code>true</code> if the user is a member of the organization.
    *
    * @param organizationId the primary key of the organization
    * @param userId the primary key of the user
    * @return <code>true</code> if the user is a member of the organization;
    <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasOrganizationUser(long organizationId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return false;
    }

    /**
    * Returns <code>true</code> if the password policy has been assigned to the
    * user.
    *
    * @param passwordPolicyId the primary key of the password policy
    * @param userId the primary key of the user
    * @return <code>true</code> if the password policy is assigned to the user;
    <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasPasswordPolicyUser(long passwordPolicyId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return false;
    }

    /**
    * Returns <code>true</code> if the user is a member of the role.
    *
    * @param roleId the primary key of the role
    * @param userId the primary key of the user
    * @return <code>true</code> if the user is a member of the role;
    <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasRoleUser(long roleId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return false;
    }

    /**
    * Returns <code>true</code> if the user has the role with the name,
    * optionally through inheritance.
    *
    * @param companyId the primary key of the role's company
    * @param name the name of the role (must be a regular role, not an
    organization, site or provider role)
    * @param userId the primary key of the user
    * @param inherited whether to include roles inherited from organizations,
    sites, etc.
    * @return <code>true</code> if the user has the role; <code>false</code>
    otherwise
    * @throws PortalException if a role with the name could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasRoleUser(long companyId, java.lang.String name,
        long userId, boolean inherited)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return false;
    }

    /**
    * Returns <code>true</code> if the user is a member of the team.
    *
    * @param teamId the primary key of the team
    * @param userId the primary key of the user
    * @return <code>true</code> if the user is a member of the team;
    <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasTeamUser(long teamId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return false;
    }

    /**
    * Returns <code>true</code> if the user is a member of the user group.
    *
    * @param userGroupId the primary key of the user group
    * @param userId the primary key of the user
    * @return <code>true</code> if the user is a member of the user group;
    <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserGroupUser(long userGroupId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return false;
    }

    /**
    * Returns <code>true</code> if the user's password is expired.
    *
    * @param user the user
    * @return <code>true</code> if the user's password is expired;
    <code>false</code> otherwise
    * @throws PortalException if the password policy for the user could not be
    found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isPasswordExpired(com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return false;
    }

    /**
    * Returns <code>true</code> if the user's password is expiring soon.
    *
    * @param user the user
    * @return <code>true</code> if the user's password is expiring soon;
    <code>false</code> otherwise
    * @throws PortalException if the password policy for the user could not be
    found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isPasswordExpiringSoon(com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return false;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User loadGetDefaultUser(long companyId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns an ordered range of all the users who match the keywords and
    * status, without using the indexer. It is preferable to use the indexed
    * version {@link #search(long, String, int, LinkedHashMap, int, int, Sort)}
    * instead of this method wherever possible for performance reasons.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param companyId the primary key of the user's company
    * @param keywords the keywords (space separated), which may occur in the
    user's first name, middle name, last name, screen name, or email
    address
    * @param status the workflow status
    * @param params the finder parameters (optionally <code>null</code>). For
    more information see {@link
    com.liferay.portal.service.persistence.UserFinder}.
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @param obc the comparator to order the users by (optionally
    <code>null</code>)
    * @return the matching users
    * @throws SystemException if a system exception occurred
    * @see com.liferay.portal.service.persistence.UserFinder
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> search(
        long companyId, java.lang.String keywords, int status,
        java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns an ordered range of all the users who match the keywords and
    * status, using the indexer. It is preferable to use this method instead of
    * the non-indexed version whenever possible for performance reasons.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param companyId the primary key of the user's company
    * @param keywords the keywords (space separated), which may occur in the
    user's first name, middle name, last name, screen name, or email
    address
    * @param status the workflow status
    * @param params the indexer parameters (optionally <code>null</code>). For
    more information see {@link
    com.liferay.portlet.usersadmin.util.UserIndexer}.
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @param sort the field and direction to sort by (optionally
    <code>null</code>)
    * @return the matching users
    * @throws SystemException if a system exception occurred
    * @see com.liferay.portlet.usersadmin.util.UserIndexer
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.kernel.search.Hits search(long companyId,
        java.lang.String keywords, int status,
        java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
        int start, int end, com.liferay.portal.kernel.search.Sort sort)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns an ordered range of all the users with the status, and whose
    * first name, middle name, last name, screen name, and email address match
    * the keywords specified for them, without using the indexer. It is
    * preferable to use the indexed version {@link #search(long, String,
    * String, String, String, String, int, LinkedHashMap, boolean, int, int,
    * Sort)} instead of this method wherever possible for performance reasons.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param companyId the primary key of the user's company
    * @param firstName the first name keywords (space separated)
    * @param middleName the middle name keywords
    * @param lastName the last name keywords
    * @param screenName the screen name keywords
    * @param emailAddress the email address keywords
    * @param status the workflow status
    * @param params the finder parameters (optionally <code>null</code>). For
    more information see {@link
    com.liferay.portal.service.persistence.UserFinder}.
    * @param andSearch whether every field must match its keywords, or just
    one field. For example, &quot;users with the first name 'bob' and
    last name 'smith'&quot; vs &quot;users with the first name 'bob'
    or the last name 'smith'&quot;.
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @param obc the comparator to order the users by (optionally
    <code>null</code>)
    * @return the matching users
    * @throws SystemException if a system exception occurred
    * @see com.liferay.portal.service.persistence.UserFinder
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> search(
        long companyId, java.lang.String firstName,
        java.lang.String middleName, java.lang.String lastName,
        java.lang.String screenName, java.lang.String emailAddress, int status,
        java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
        boolean andSearch, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns an ordered range of all the users with the status, and whose
    * first name, middle name, last name, screen name, and email address match
    * the keywords specified for them, using the indexer. It is preferable to
    * use this method instead of the non-indexed version whenever possible for
    * performance reasons.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param companyId the primary key of the user's company
    * @param firstName the first name keywords (space separated)
    * @param middleName the middle name keywords
    * @param lastName the last name keywords
    * @param screenName the screen name keywords
    * @param emailAddress the email address keywords
    * @param status the workflow status
    * @param params the indexer parameters (optionally <code>null</code>). For
    more information see {@link
    com.liferay.portlet.usersadmin.util.UserIndexer}.
    * @param andSearch whether every field must match its keywords, or just
    one field. For example, &quot;users with the first name 'bob' and
    last name 'smith'&quot; vs &quot;users with the first name 'bob'
    or the last name 'smith'&quot;.
    * @param start the lower bound of the range of users
    * @param end the upper bound of the range of users (not inclusive)
    * @param sort the field and direction to sort by (optionally
    <code>null</code>)
    * @return the matching users
    * @throws SystemException if a system exception occurred
    * @see com.liferay.portlet.usersadmin.util.UserIndexer
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.kernel.search.Hits search(long companyId,
        java.lang.String firstName, java.lang.String middleName,
        java.lang.String lastName, java.lang.String screenName,
        java.lang.String emailAddress, int status,
        java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
        boolean andSearch, int start, int end,
        com.liferay.portal.kernel.search.Sort sort)
        throws com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Returns the number of users who match the keywords and status.
    *
    * @param companyId the primary key of the user's company
    * @param keywords the keywords (space separated), which may occur in the
    user's first name, middle name, last name, screen name, or email
    address
    * @param status the workflow status
    * @param params the finder parameters (optionally <code>null</code>). For
    more information see {@link
    com.liferay.portal.service.persistence.UserFinder}.
    * @return the number matching users
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int searchCount(long companyId, java.lang.String keywords,
        int status,
        java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
        throws com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Returns the number of users with the status, and whose first name, middle
    * name, last name, screen name, and email address match the keywords
    * specified for them.
    *
    * @param companyId the primary key of the user's company
    * @param firstName the first name keywords (space separated)
    * @param middleName the middle name keywords
    * @param lastName the last name keywords
    * @param screenName the screen name keywords
    * @param emailAddress the email address keywords
    * @param status the workflow status
    * @param params the finder parameters (optionally <code>null</code>). For
    more information see {@link
    com.liferay.portal.service.persistence.UserFinder}.
    * @param andSearch whether every field must match its keywords, or just
    one field. For example, &quot;users with the first name 'bob' and
    last name 'smith'&quot; vs &quot;users with the first name 'bob'
    or the last name 'smith'&quot;.
    * @return the number of matching users
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int searchCount(long companyId, java.lang.String firstName,
        java.lang.String middleName, java.lang.String lastName,
        java.lang.String screenName, java.lang.String emailAddress, int status,
        java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
        boolean andSearch)
        throws com.liferay.portal.kernel.exception.SystemException {
        return 0;
    }

    /**
    * Sends an email address verification to the user.
    *
    * @param user the verification email recipient
    * @param emailAddress the recipient's email address
    * @param serviceContext the service context. Must set the portal URL, main
    path, primary key of the layout, remote address, remote host, and
    agent for the user.
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void sendEmailAddressVerification(
        com.liferay.portal.model.User user, java.lang.String emailAddress,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Sends the password email to the user with the email address. The content
    * of this email can be specified in <code>portal.properties</code> with the
    * <code>admin.email.password</code> keys.
    *
    * @param companyId the primary key of the user's company
    * @param emailAddress the user's email address
    * @param fromName the name of the individual that the email should be from
    * @param fromAddress the address of the individual that the email should
    be from
    * @param subject the email subject. If <code>null</code>, the subject
    specified in <code>portal.properties</code> will be used.
    * @param body the email body. If <code>null</code>, the body specified in
    <code>portal.properties</code> will be used.
    * @param serviceContext the user's service context
    * @throws PortalException if a user with the email address could not be
    found
    * @throws SystemException if a system exception occurred
    */
    public void sendPassword(long companyId, java.lang.String emailAddress,
        java.lang.String fromName, java.lang.String fromAddress,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Sets the users in the role, removing and adding users to the role as
    * necessary.
    *
    * @param roleId the primary key of the role
    * @param userIds the primary keys of the users
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void setRoleUsers(long roleId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Sets the users in the user group, removing and adding users to the user
    * group as necessary.
    *
    * @param userGroupId the primary key of the user group
    * @param userIds the primary keys of the users
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void setUserGroupUsers(long userGroupId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes the users from the group.
    *
    * @param groupId the primary key of the group
    * @param userIds the primary keys of the users
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void unsetGroupUsers(long groupId, long[] userIds,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes the users from the organization.
    *
    * @param organizationId the primary key of the organization
    * @param userIds the primary keys of the users
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void unsetOrganizationUsers(long organizationId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes the users from the password policy.
    *
    * @param passwordPolicyId the primary key of the password policy
    * @param userIds the primary keys of the users
    * @throws SystemException if a system exception occurred
    */
    public void unsetPasswordPolicyUsers(long passwordPolicyId, long[] userIds)
        throws com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes the users from the role.
    *
    * @param roleId the primary key of the role
    * @param users the users
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void unsetRoleUsers(long roleId,
        java.util.List<com.liferay.portal.model.User> users)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes the users from the role.
    *
    * @param roleId the primary key of the role
    * @param userIds the primary keys of the users
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void unsetRoleUsers(long roleId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes the users from the team.
    *
    * @param teamId the primary key of the team
    * @param userIds the primary keys of the users
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void unsetTeamUsers(long teamId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Removes the users from the user group.
    *
    * @param userGroupId the primary key of the user group
    * @param userIds the primary keys of the users
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void unsetUserGroupUsers(long userGroupId, long[] userIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Updates whether the user has agreed to the terms of use.
    *
    * @param userId the primary key of the user
    * @param agreedToTermsOfUse whether the user has agreet to the terms of
    use
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateAgreedToTermsOfUse(long userId,
        boolean agreedToTermsOfUse)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's asset with the new asset categories and tag names,
    * removing and adding asset categories and tag names as necessary.
    *
    * @param userId the primary key of the user
    * @param user ID the primary key of the user
    * @param assetCategoryIds the primary key's of the new asset categories
    * @param assetTagNames the new asset tag names
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void updateAsset(long userId, com.liferay.portal.model.User user,
        long[] assetCategoryIds, java.lang.String[] assetTagNames)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Updates the user's creation date.
    *
    * @param userId the primary key of the user
    * @param createDate the new creation date
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateCreateDate(long userId,
        java.util.Date createDate)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's email address.
    *
    * @param userId the primary key of the user
    * @param password the user's password
    * @param emailAddress1 the user's new email address
    * @param emailAddress2 the user's new email address confirmation
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateEmailAddress(long userId,
        java.lang.String password, java.lang.String emailAddress1,
        java.lang.String emailAddress2)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's email address or sends verification email.
    *
    * @param userId the primary key of the user
    * @param password the user's password
    * @param emailAddress1 the user's new email address
    * @param emailAddress2 the user's new email address confirmation
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateEmailAddress(long userId,
        java.lang.String password, java.lang.String emailAddress1,
        java.lang.String emailAddress2,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates whether the user has verified email address.
    *
    * @param userId the primary key of the user
    * @param emailAddressVerified whether the user has verified email address
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateEmailAddressVerified(
        long userId, boolean emailAddressVerified)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's Facebook ID.
    *
    * @param userId the primary key of the user
    * @param facebookId the user's new Facebook ID
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateFacebookId(long userId,
        long facebookId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Sets the groups the user is in, removing and adding groups as necessary.
    *
    * @param userId the primary key of the user
    * @param newGroupIds the primary keys of the groups
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public void updateGroups(long userId, long[] newGroupIds,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Updates a user account that was automatically created when a guest user
    * participated in an action (e.g. posting a comment) and only provided his
    * name and email address.
    *
    * @param creatorUserId the primary key of the creator
    * @param companyId the primary key of the user's company
    * @param autoPassword whether a password should be automatically generated
    for the user
    * @param password1 the user's password
    * @param password2 the user's password confirmation
    * @param autoScreenName whether a screen name should be automatically
    generated for the user
    * @param screenName the user's screen name
    * @param emailAddress the user's email address
    * @param facebookId the user's facebook ID
    * @param openId the user's OpenID
    * @param locale the user's locale
    * @param firstName the user's first name
    * @param middleName the user's middle name
    * @param lastName the user's last name
    * @param prefixId the user's name prefix ID
    * @param suffixId the user's name suffix ID
    * @param male whether the user is male
    * @param birthdayMonth the user's birthday month (0-based, meaning 0 for
    January)
    * @param birthdayDay the user's birthday day
    * @param birthdayYear the user's birthday year
    * @param jobTitle the user's job title
    * @param updateUserInformation whether to update the user's information
    * @param sendEmail whether to send the user an email notification about
    their new account
    * @param serviceContext the user's service context (optionally
    <code>null</code>). Can set expando bridge attributes for the
    user.
    * @return the user
    * @throws PortalException if the user's information was invalid
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateIncompleteUser(
        long creatorUserId, long companyId, boolean autoPassword,
        java.lang.String password1, java.lang.String password2,
        boolean autoScreenName, java.lang.String screenName,
        java.lang.String emailAddress, long facebookId,
        java.lang.String openId, java.util.Locale locale,
        java.lang.String firstName, java.lang.String middleName,
        java.lang.String lastName, int prefixId, int suffixId, boolean male,
        int birthdayMonth, int birthdayDay, int birthdayYear,
        java.lang.String jobTitle, boolean updateUserInformation,
        boolean sendEmail,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's job title.
    *
    * @param userId the primary key of the user
    * @param jobTitle the user's job title
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    or if a contact could not be found matching the user's contact ID
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateJobTitle(long userId,
        java.lang.String jobTitle)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's last login with the current time and the IP address.
    *
    * @param userId the primary key of the user
    * @param loginIP the IP address the user logged in from
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateLastLogin(long userId,
        java.lang.String loginIP)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates whether the user is locked out from logging in.
    *
    * @param user the user
    * @param lockout whether the user is locked out
    * @return the user
    * @throws PortalException if a portal exception occurred
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateLockout(
        com.liferay.portal.model.User user, boolean lockout)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates whether the user is locked out from logging in.
    *
    * @param companyId the primary key of the user's company
    * @param emailAddress the user's email address
    * @param lockout whether the user is locked out
    * @return the user
    * @throws PortalException if a user with the email address could not be
    found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateLockoutByEmailAddress(
        long companyId, java.lang.String emailAddress, boolean lockout)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates whether the user is locked out from logging in.
    *
    * @param userId the primary key of the user
    * @param lockout whether the user is locked out
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateLockoutById(long userId,
        boolean lockout)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates whether the user is locked out from logging in.
    *
    * @param companyId the primary key of the user's company
    * @param screenName the user's screen name
    * @param lockout whether the user is locked out
    * @return the user
    * @throws PortalException if a user with the screen name could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateLockoutByScreenName(
        long companyId, java.lang.String screenName, boolean lockout)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's modified date.
    *
    * @param userId the primary key of the user
    * @param modifiedDate the new modified date
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateModifiedDate(long userId,
        java.util.Date modifiedDate)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's OpenID.
    *
    * @param userId the primary key of the user
    * @param openId the new OpenID
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateOpenId(long userId,
        java.lang.String openId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Sets the organizations that the user is in, removing and adding
    * organizations as necessary.
    *
    * @param userId the primary key of the user
    * @param newOrganizationIds the primary keys of the organizations
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void updateOrganizations(long userId, long[] newOrganizationIds,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

    /**
    * Updates the user's password without tracking or validation of the change.
    *
    * @param userId the primary key of the user
    * @param password1 the user's new password
    * @param password2 the user's new password confirmation
    * @param passwordReset whether the user should be asked to reset their
    password the next time they log in
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updatePassword(long userId,
        java.lang.String password1, java.lang.String password2,
        boolean passwordReset)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's password, optionally with tracking and validation of
    * the change.
    *
    * @param userId the primary key of the user
    * @param password1 the user's new password
    * @param password2 the user's new password confirmation
    * @param passwordReset whether the user should be asked to reset their
    password the next time they login
    * @param silentUpdate whether the password should be updated without being
    tracked, or validated. Primarily used for password imports.
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updatePassword(long userId,
        java.lang.String password1, java.lang.String password2,
        boolean passwordReset, boolean silentUpdate)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's password with manually input information. This method
    * should only be used when performing maintenance.
    *
    * @param userId the primary key of the user
    * @param password the user's new password
    * @param passwordEncrypted the user's new encrypted password
    * @param passwordReset whether the user should be asked to reset their
    password the next time they login
    * @param passwordModifiedDate the new password modified date
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updatePasswordManually(long userId,
        java.lang.String password, boolean passwordEncrypted,
        boolean passwordReset, java.util.Date passwordModifiedDate)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates whether the user should be asked to reset their password the next
    * time they login.
    *
    * @param userId the primary key of the user
    * @param passwordReset whether the user should be asked to reset their
    password the next time they login
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updatePasswordReset(long userId,
        boolean passwordReset)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's portrait image.
    *
    * @param userId the primary key of the user
    * @param bytes the new portrait image data
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    or if the new portrait was invalid
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updatePortrait(long userId,
        byte[] bytes)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's password reset question and answer.
    *
    * @param userId the primary key of the user
    * @param question the user's new password reset question
    * @param answer the user's new password reset answer
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    or if the new question or answer were invalid
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateReminderQuery(long userId,
        java.lang.String question, java.lang.String answer)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's screen name.
    *
    * @param userId the primary key of the user
    * @param screenName the user's new screen name
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    or if the new screen name was invalid
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateScreenName(long userId,
        java.lang.String screenName)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Updates the user's workflow status.
    *
    * @param userId the primary key of the user
    * @param status the user's new workflow status
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateStatus(long userId, int status)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    @Override
    public User updateStatus(long l, int i, ServiceContext serviceContext) throws PortalException, SystemException {
        return null;
    }

    /**
    * Updates the user.
    *
    * @param userId the primary key of the user
    * @param oldPassword the user's old password
    * @param newPassword1 the user's new password (optionally
    <code>null</code>)
    * @param newPassword2 the user's new password confirmation (optionally
    <code>null</code>)
    * @param passwordReset whether the user should be asked to reset their
    password the next time they login
    * @param reminderQueryQuestion the user's new password reset question
    * @param reminderQueryAnswer the user's new password reset answer
    * @param screenName the user's new screen name
    * @param emailAddress the user's new email address
    * @param facebookId the user's new Facebook ID
    * @param openId the user's new OpenID
    * @param languageId the user's new language ID
    * @param timeZoneId the user's new time zone ID
    * @param greeting the user's new greeting
    * @param comments the user's new comments
    * @param firstName the user's new first name
    * @param middleName the user's new middle name
    * @param lastName the user's new last name
    * @param prefixId the user's new name prefix ID
    * @param suffixId the user's new name suffix ID
    * @param male whether user is male
    * @param birthdayMonth the user's new birthday month (0-based, meaning 0
    for January)
    * @param birthdayDay the user's new birthday day
    * @param birthdayYear the user's birthday year
    * @param smsSn the user's new SMS screen name
    * @param aimSn the user's new AIM screen name
    * @param facebookSn the user's new Facebook screen name
    * @param icqSn the user's new ICQ screen name
    * @param jabberSn the user's new Jabber screen name
    * @param msnSn the user's new MSN screen name
    * @param mySpaceSn the user's new MySpace screen name
    * @param skypeSn the user's new Skype screen name
    * @param twitterSn the user's new Twitter screen name
    * @param ymSn the user's new Yahoo! Messenger screen name
    * @param jobTitle the user's new job title
    * @param groupIds the primary keys of the user's groups
    * @param organizationIds the primary keys of the user's organizations
    * @param roleIds the primary keys of the user's roles
    * @param userGroupRoles the user user's group roles
    * @param userGroupIds the primary keys of the user's user groups
    * @param serviceContext the user's service context (optionally
    <code>null</code>). Can set the universally unique identifier
    (with the <code>uuid</code> attribute), asset category IDs, asset
    tag names, and expando bridge attributes for the user.
    * @return the user
    * @throws PortalException if a user with the primary key could not be found
    or if the new information was invalid
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.portal.model.User updateUser(long userId,
        java.lang.String oldPassword, java.lang.String newPassword1,
        java.lang.String newPassword2, boolean passwordReset,
        java.lang.String reminderQueryQuestion,
        java.lang.String reminderQueryAnswer, java.lang.String screenName,
        java.lang.String emailAddress, long facebookId,
        java.lang.String openId, java.lang.String languageId,
        java.lang.String timeZoneId, java.lang.String greeting,
        java.lang.String comments, java.lang.String firstName,
        java.lang.String middleName, java.lang.String lastName, int prefixId,
        int suffixId, boolean male, int birthdayMonth, int birthdayDay,
        int birthdayYear, java.lang.String smsSn, java.lang.String aimSn,
        java.lang.String facebookSn, java.lang.String icqSn,
        java.lang.String jabberSn, java.lang.String msnSn,
        java.lang.String mySpaceSn, java.lang.String skypeSn,
        java.lang.String twitterSn, java.lang.String ymSn,
        java.lang.String jobTitle, long[] groupIds, long[] organizationIds,
        long[] roleIds,
        java.util.List<com.liferay.portal.model.UserGroupRole> userGroupRoles,
        long[] userGroupIds,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return null;
    }

    /**
    * Verifies the email address of the ticket.
    *
    * @param ticketKey the ticket key
    * @throws PortalException if a ticket matching the ticket key could not be
    found, if the ticket has expired, if the ticket is an email
    address ticket, or if the email address is invalid
    * @throws SystemException if a system exception occurred
    */
    public void verifyEmailAddress(java.lang.String ticketKey)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
    }

	@Override
	public DynamicQuery dynamicQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUuidAndCompanyId(String uuid, long companyId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserGroupUser(long userGroupId, User user)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserGroupUsers(long userGroupId, long[] userIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserGroupUsers(long userGroupId, List<User> Users)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
			Projection projection) throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User fetchUserByUuidAndCompanyId(String uuid, long companyId)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addGroupUser(long groupId, long userId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGroupUser(long groupId, User user) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGroupUsers(long groupId, List<User> Users)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearGroupUsers(long groupId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroupUser(long groupId, long userId)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroupUser(long groupId, User user) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroupUsers(long groupId, long[] userIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroupUsers(long groupId, List<User> Users)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getGroupUsers(long groupId, int start, int end)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getGroupUsers(long groupId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasGroupUsers(long groupId) throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGroupUsers(long groupId, long[] userIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOrganizationUser(long organizationId, long userId)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOrganizationUser(long organizationId, User user)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOrganizationUsers(long organizationId, List<User> Users)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrganizationUser(long organizationId, long userId)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrganizationUser(long organizationId, User user)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrganizationUsers(long organizationId, long[] userIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrganizationUsers(long organizationId, List<User> Users)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getOrganizationUsers(long organizationId, int start,
			int end) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getOrganizationUsers(long organizationId, int start,
			int end, OrderByComparator orderByComparator)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasOrganizationUsers(long organizationId)
			throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setOrganizationUsers(long organizationId, long[] userIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRoleUser(long roleId, long userId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRoleUser(long roleId, User user) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRoleUsers(long roleId, List<User> Users)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearRoleUsers(long roleId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoleUser(long roleId, User user) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoleUsers(long roleId, long[] userIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoleUsers(long roleId, List<User> Users)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getRoleUsers(long roleId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRoleUsers(long roleId) throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addTeamUser(long teamId, long userId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTeamUser(long teamId, User user) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTeamUsers(long teamId, List<User> Users)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearTeamUsers(long teamId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTeamUser(long teamId, long userId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTeamUser(long teamId, User user) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTeamUsers(long teamId, long[] userIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTeamUsers(long teamId, List<User> Users)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getTeamUsers(long teamId) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getTeamUsers(long teamId, int start, int end)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getTeamUsers(long teamId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTeamUsersCount(long teamId) throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasTeamUsers(long teamId) throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTeamUsers(long teamId, long[] userIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserGroupUser(long userGroupId, long userId)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserGroupUser(long userGroupId, User user)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserGroupUsers(long userGroupId, List<User> Users)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getUserGroupUsers(long userGroupId, int start, int end)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserGroupUsers(long userGroupId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasUserGroupUsers(long userGroupId) throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User addDefaultAdminUser(long companyId, String screenName,
			String emailAddress, Locale locale, String firstName,
			String middleName, String lastName) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User fetchUserByEmailAddress(long companyId, String emailAddress)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User fetchUserByFacebookId(long companyId, long facebookId)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User fetchUserByOpenId(long companyId, String openId)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getInheritedRoleUsers(long roleId, int start, int end,
			OrderByComparator obc) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unsetGroupTeamsUsers(long groupId, long[] userIds)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User deleteUser(long userId) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(User user) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

}
