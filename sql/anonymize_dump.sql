-- anonymizing db dump
-- Anonymizes all sensitive fields of a database dump of the XCoLab
--
-- author: Johannes Bachhuber
-- created: Aug 10, 2015
-- last modified: Aug 10, 2015

-- User information and emails
update User_ set emailAddress = CONCAT("test+u", userId, "@example.com"), screenName = CONCAT("user", userId), firstName = CONCAT("User ", userId), lastName = "CoLab", reminderQueryAnswer = "", openId = "", greeting = "Welcome friend!", middleName = "", loginDate = '2009-08-19 01:00:00', lastLoginDate = '2009-08-19 01:00:00', lastFailedLoginDate = '2009-08-19 01:00:00', reminderQueryQuestion = "", passwordModifiedDate = '2009-08-19 01:00:00', modifiedDate = '2009-08-19 01:00:00', createDate = '2009-08-19 01:00:00', loginIP = "127.0.0.1", lastLoginIP = "127.0.0.1", facebookId = NULL, digest = NULL, password_ = '{SHA-1}NS2PVQOqtxe9YlFzf3xNa8/6XDo=', passwordEncrypted = 1;
update Address set userName = "Tim", street1 = "Massachusetts Avenue", city = "Cambridge", zip = "02139";
update Contact_ set birthday = '2000-01-01 00:00:00', male = 0, emailAddress = CONCAT("test+u", userId, "@example.com"), firstName = CONCAT("User ", userId), lastName = "CoLab", middleName = "", twitterSn = "", facebookSn = "", aimSn = "", smsSn = "", icqSn = "", jabberSn = "", msnSn = "", myspaceSn = "", skypeSn = "", ymSn = "", employeeStatusId = "", employeeNumber = "", jobTitle = "";
update emailtoignore set email = "test@example.com";
delete from UserFacebookMapping;
update xcolab_EmailList set email = "test@example.com";
update xcolab.ExpandoValue set data_ = "" where columnId in (SELECT columnId FROM xcolab.ExpandoColumn where name = "aboutMe"); -- deletes user bios

-- delete all messages
delete from xcolab_Message;
delete from xcolab_MessagingIgnoredRecipients;
delete from xcolab_MessagingMessage;
delete from xcolab_MessagingMessageConversion;
delete from xcolab_MessagingMessageRecipient;

-- session and login information
delete from xcolab_TrackedVisit;
update xcolab_LoginLog set ipAddress = "127.0.0.1", city = "", country = "", createDate = 0;