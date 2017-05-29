drop table members_Member if exists;
drop table Users_Roles if exists;
drop table xcolab_MemberCategory if exists;

create table members_Member
(
	id_ bigint not null
		primary key,
	screenName varchar(42) null,
	emailAddress varchar(75) null,
	isEmailConfirmed tinyint(4) default '0' not null,
	isEmailBounced tinyint(4) default '0',
	firstName varchar(75) null,
	lastName varchar(75) null,
	hashedPassword varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	passwordModifiedDate datetime null,
	country varchar(75) null,
	state varchar(50) null,
	shortBio text null,
	facebookId bigint null,
	googleId varchar(50) null,
	openId varchar(255) null,
	loginIP varchar(75) null,
	loginDate datetime null,
	status int default '0' null,
	forgotPasswordToken varchar(255) null,
	forgotPasswordTokenExpireTime datetime null,
	portraitFileEntryId bigint null,
	reportKarma int default '100' null,
	autoRegisteredMemberStatus int(4) default '0' null,
	uuid varchar(40) null,
	loginTokenId varchar(75) null,
	loginTokenKey varchar(75) null,
	loginTokenExpirationDate datetime null,
	constraint IX_XCOLAB_MEMBERS_SCREEN_NAME
		unique (screenName),
	constraint IX_XCOLAB_MEMBERS_EMAIL_ADDRESS
		unique (emailAddress)
);

create table Users_Roles
(
	userId bigint not null,
	roleId bigint not null,
	primary key (userId, roleId)
);

create table xcolab_MemberCategory
(
	roleId bigint not null
		primary key,
	displayName varchar(75) null,
	categoryName varchar(75) null,
	sortOrder bigint null,
	showInList tinyint null,
	imageName varchar(75) null,
	description varchar(2048) null
);
