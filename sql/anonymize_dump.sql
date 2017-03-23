-- Anonymizes all sensitive fields of a database dump of the XCoLab

-- User information and emails
update members_Member set emailAddress  = CONCAT('test+u', id_, '@example.com'),
  screenName = CONCAT('user', id_), firstName = CONCAT('User ', id_), lastName = 'CoLab', openId = '',
  loginDate = '2009-08-19 01:00:00',  passwordModifiedDate = '2009-08-19 01:00:00',
  modifiedDate = '2009-08-19 01:00:00', createDate = '2009-08-19 01:00:00', loginIP = '127.0.0.1',
  facebookId = 0, hashedPassword = '{SHA-1}NS2PVQOqtxe9YlFzf3xNa8/6XDo=', shortBio = '';

-- delete all messages
delete from xcolab_Message;
delete from xcolab_MessageRecipientStatus;
delete from xcolab_MessagingUserPreferences;

delete from xcolab_ActivitySubscription;
delete from xcolab_AnalyticsUserEvent;

delete from xcolab_BalloonLink;
delete from xcolab_BalloonText;
delete from xcolab_BalloonUserTracking;

-- session and login information
delete from xcolab_TrackedVisit;
delete from xcolab_TrackedVisitor2User;
delete from xcolab_LoginLog;