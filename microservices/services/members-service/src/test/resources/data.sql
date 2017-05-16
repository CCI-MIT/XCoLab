INSERT INTO members_Member (id_, screenName, emailAddress, isEmailConfirmed, firstName, lastName, hashedPassword, createDate, modifiedDate, passwordModifiedDate, country, shortBio, facebookId, googleId, openId, loginIP, loginDate, status, forgotPasswordToken, forgotPasswordTokenExpireTime, portraitFileEntryId, reportKarma, autoRegisteredMemberStatus, uuid, loginTokenId, loginTokenKey, loginTokenExpirationDate)
    VALUES (10144, 'admin', 'admin+u10144@example.com', 0, 'Admin', 'CoLab', 'PBKDF2_160_128000_g81/ioSNrXo=_pRj14MUWBMdoAVhbI7pqUlGdrII=', '2009-08-19 01:00:00', '2009-08-19 01:00:00', '2009-08-19 01:00:00', 'United States', '', 0, null, '', '127.0.0.1', '2009-08-19 01:00:00', 0, null, null, 0, 100, 0, null, null, null, null);
INSERT INTO members_Member (id_, screenName, emailAddress, isEmailConfirmed, firstName, lastName, hashedPassword, createDate, modifiedDate, passwordModifiedDate, country, shortBio, facebookId, googleId, openId, loginIP, loginDate, status, forgotPasswordToken, forgotPasswordTokenExpireTime, portraitFileEntryId, reportKarma, autoRegisteredMemberStatus, uuid, loginTokenId, loginTokenKey, loginTokenExpirationDate)
    VALUES (10145, 'member', 'member+u10145@example.com', 0, 'Member', 'CoLab', 'PBKDF2_160_128000_g81/ioSNrXo=_pRj14MUWBMdoAVhbI7pqUlGdrII=', '2009-08-19 01:00:00', '2009-08-19 01:00:00', '2009-08-19 01:00:00', 'United States', '', 0, null, '', '127.0.0.1', '2009-08-19 01:00:00', 0, null, null, 0, 100, 0, null, null, null, null);
    INSERT INTO members_Member (id_, screenName, emailAddress, isEmailConfirmed, firstName, lastName, hashedPassword, createDate, modifiedDate, passwordModifiedDate, country, shortBio, facebookId, googleId, openId, loginIP, loginDate, status, forgotPasswordToken, forgotPasswordTokenExpireTime, portraitFileEntryId, reportKarma, autoRegisteredMemberStatus, uuid, loginTokenId, loginTokenKey, loginTokenExpirationDate)
    VALUES (10146, 'deletedMember', 'member+u10146@example.com', 0, 'Member', 'CoLab', 'PBKDF2_160_128000_g81/ioSNrXo=_pRj14MUWBMdoAVhbI7pqUlGdrII=', '2009-08-19 01:00:00', '2009-08-19 01:00:00', '2009-08-19 01:00:00', 'United States', '', 0, null, '', '127.0.0.1', '2009-08-19 01:00:00', 5, null, null, 0, 100, 0, null, null, null, null);

INSERT INTO Users_Roles (userId, roleId) VALUES (10144, 10118);
INSERT INTO Users_Roles (userId, roleId) VALUES (10144, 10122);
INSERT INTO Users_Roles (userId, roleId) VALUES (10145, 10122);
INSERT INTO Users_Roles (userId, roleId) VALUES (10146, 10122);

INSERT INTO xcolab_MemberCategory (roleId, displayName, categoryName, sortOrder, showInList, imageName, description)
  VALUES (10118, 'Admin', 'Admin', 1, 0, 'icon_mem-staff', '');
INSERT INTO xcolab_MemberCategory (roleId, displayName, categoryName, sortOrder, showInList, imageName, description)
  VALUES (10122, 'Member', 'Members', 2, 1, 'icon_mem-member', '');
