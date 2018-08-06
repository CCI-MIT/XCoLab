drop table comment_Category if exists;
drop table comment_Comment if exists;
drop table comment_Thread if exists;
drop table comment_CategoryGroup if exists;

create table comment_Category
(
	categoryId bigint not null auto_increment
    primary key,
	groupId bigint null,
	authorId bigint null,
	name varchar(75) null,
	description text null,
	createDate datetime not null,
	deletedDate datetime null,
	sort int null,
	isQuiet tinyint default '0' null
);

create table comment_CategoryGroup
(
	groupId bigint not null auto_increment
		primary key,
	description text null,
	url varchar(200) null,
	isQuiet tinyint default '0' null
);

create table comment_Comment
(
	commentId bigint not null auto_increment
		primary key,
	threadId bigint not null,
	authorId bigint null,
	createDate datetime null,
	modifiedDate datetime null,
	deletedDate datetime null,
	content text null
);

create table comment_Thread
(
	threadId bigint not null auto_increment
		primary key,
	categoryId bigint null,
	authorId bigint not null,
	title varchar(255) not null,
	createDate datetime not null,
	deletedDate datetime null,
	isQuiet tinyint default '0' null
);
