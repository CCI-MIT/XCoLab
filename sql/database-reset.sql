delete from members_Member;
INSERT INTO members_Member (id_, screenName, emailAddress, isEmailConfirmed, firstName, lastName, hashedPassword, createDate, modifiedDate, passwordModifiedDate, country, shortBio, facebookId, googleId, openId, loginIP, loginDate, status, forgotPasswordToken, forgotPasswordTokenExpireTime, portraitFileEntryId, reportKarma, autoRegisteredMemberStatus, uuid)
  VALUES (10144, 'admin', 'admin+u10144@example.com', 0, 'Admin', 'CoLab', 'PBKDF2_160_128000_g81/ioSNrXo=_pRj14MUWBMdoAVhbI7pqUlGdrII=', '2009-08-19 01:00:00', '2009-08-19 01:00:00', '2009-08-19 01:00:00', 'United States', '', 0, null, '', '127.0.0.1', '2009-08-19 01:00:00', 0, null, null, 0, 100, 0, null),
    (10145, 'member', 'member+u10145@example.com', 0, 'Member', 'CoLab', 'PBKDF2_160_128000_g81/ioSNrXo=_pRj14MUWBMdoAVhbI7pqUlGdrII=', '2009-08-19 01:00:00', '2009-08-19 01:00:00', '2009-08-19 01:00:00', 'United States', '', 0, null, '', '127.0.0.1', '2009-08-19 01:00:00', 0, null, null, 0, 100, 0, null);
delete from sharedcolab_SharedMember;
INSERT INTO sharedcolab_SharedMember (sharedMemberId, screenName, emailAddress, createDate, colabOrigin)
  VALUES (10144, 'admin', 'admin+u10144@example.com', '2009-08-19 01:00:00', 'XCoLab'),
    (10145, 'member', 'member+u10145@example.com', '2009-08-19 01:00:00', 'XCoLab');
delete from Users_Roles;
INSERT INTO Users_Roles (userId, roleId) VALUES (10144, 10118), (10144, 10122), (10145, 10122);

delete from activities_ActivityEntry;
delete from xcolab_ActivitySubscription;
delete from xcolab_ColabEmail;

delete from sharedcolab_SharedContest;
delete from xcolab_Contest;
delete from xcolab_ContestCollectionCard;
delete from xcolab_ContestDiscussion;
delete from xcolab_ContestPhase;
delete from xcolab_ContestSchedule;
delete from xcolab_ContestTeamMember;

delete from xcolab_Proposal;
delete from xcolab_Proposal2Phase;
delete from xcolab_ProposalAttribute;
delete from xcolab_ProposalContestPhaseAttribute;
delete from xcolab_ProposalMoveHistory;
delete from xcolab_ProposalRating;
delete from xcolab_ProposalRatingValue;
delete from xcolab_ProposalReference;
delete from xcolab_ProposalSupporter;
delete from xcolab_ProposalUnversionedAttribute;
delete from xcolab_ProposalVersion;
delete from xcolab_ProposalVote;
delete from xcolab_ProposalVote_deleted;
delete from xcolab_ProposalVoteRemoved;
delete from MembershipRequest;

delete from xcolab_PlanTemplate;
delete from xcolab_PlanTemplateSection;
delete from xcolab_PlanSectionDefinition;

delete from xcolab_StaffMember;

delete from xcolab_PointsDistributionConfiguration;

delete from flagging_Report;

delete from xcolab_FocusArea where id_ > 2;
delete from xcolab_FocusAreaOntologyTerm;

delete from xcolab_OntologyTerm where id_ not in (1, 2, 3, 1300601);

delete from Group_;
delete from Users_Groups;
-- TODO: this table will be removed soon
delete from UserGroupRole;

delete from xcolab_Points;

delete from xcolab_Message;
delete from xcolab_MessageRecipientStatus;
delete from xcolab_MessagingUserPreferences;

delete from comment_Comment;
delete from comment_Thread;
delete from comment_Category where categoryId not in (12501, 12502);

-- session and login information
delete from xcolab_TrackedVisit;
delete from xcolab_TrackedVisitor2User;
delete from xcolab_BalloonUserTracking;
delete from xcolab_BalloonText;
delete from xcolab_BalloonLink;
delete from xcolab_AnalyticsUserEvent;
delete from xcolab_LoginLog;

delete from files_FileEntry;
delete from filtering_FilteredEntry;

delete from xcolab_ContentPage;
delete from xcolab_ContentArticle;
delete from xcolab_ContentArticleVersion;
delete from xcolab_ContentFolder where contentFolderId > 4;

-- Default content

-- About navigation bar
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible)
  VALUES (1, 10144, '2016-05-11 09:21:08', 1, 2, null, null, 1);
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content)
  VALUES (1, 1, 2, 10144, '2016-07-19 12:38:47', 'Navigation', '<ul>
	<li id="about"><a href="/web/guest/about">About the project</a></li>
</ul>
<script>
var siteForNav=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
$("#"+siteForNav).addClass("c");
</script>');

-- About page
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate)
  VALUES ('about', 1, 2, '2017-03-10 06:00:00', '2017-03-10 06:00:00');
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible)
  VALUES (2, 10144, '2016-05-11 09:21:08', 2, 2, null, null, 1);
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content)
  VALUES (2, 2, 2, 10144, '2016-07-19 12:38:47', 'About', 'About this CoLab.');

-- Footer
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible)
  VALUES (3, 10144, '2016-05-11 09:21:08', 3, 2, null, null, 1);
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content)
  VALUES (3, 3, 2, 10144, '2016-07-19 12:38:47', 'Footer', 'The footer.');

-- Landing page top
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible)
  VALUES (4, 10144, '2016-05-11 09:21:08', 4, 2, null, null, 1);
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content)
  VALUES (4, 4, 2, 10144, '2016-07-19 12:38:47', 'Landing page top', 'Content.');

-- Landing page bottom
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible)
  VALUES (5, 10144, '2016-05-11 09:21:08', 5, 2, null, null, 1);
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content)
  VALUES (5, 5, 2, 10144, '2016-07-19 12:38:47', 'Landing page bottom', 'Content.');

-- Members menu
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible)
  VALUES (6, 10144, '2016-05-11 09:21:08', 6, 2, null, null, 1);
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content)
  VALUES (6, 6, 2, 10144, '2016-07-19 12:38:47', 'Members menu', 'Content.');

-- Discussion menu
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible)
  VALUES (7, 10144, '2016-05-11 09:21:08', 7, 2, null, null, 1);
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content)
  VALUES (7, 7, 2, 10144, '2016-07-19 12:38:47', 'Discussion menu', 'Content.');

delete from xcolab_ConfigurationAttribute where name in ('FOOTER_CONTENT_ARTICLE_ID',
  'LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID', 'LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID',
  'MEMBERS_CONTENT_ARTICLE_ID', 'DISCUSSION_CONTENT_ARTICLE_ID');
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue)
  VALUES ('FOOTER_CONTENT_ARTICLE_ID', 0, 3, ' ', 0),
  ('LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID', 0, 4, ' ', 0),
  ('LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID', 0, 5, ' ', 0),
  ('MEMBERS_CONTENT_ARTICLE_ID', 0, 6, ' ', 0),
  ('DISCUSSION_CONTENT_ARTICLE_ID', 0, 7, ' ', 0);
