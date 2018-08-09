rename table comment_Category to comment__category;
rename table comment_CategoryGroup to comment__category_group;
rename table comment_Comment to comment__comment;
rename table comment_Thread to comment__thread;

ALTER TABLE comment__category CHANGE categoryId id bigint(20) NOT NULL auto_increment;
ALTER TABLE comment__category CHANGE groupId group_id bigint(20);
ALTER TABLE comment__category CHANGE authorId author_user_id bigint(20);
ALTER TABLE comment__category CHANGE createDate created_at datetime NOT NULL;
ALTER TABLE comment__category CHANGE deletedDate deleted_at datetime;
ALTER TABLE comment__category CHANGE isQuiet is_quiet tinyint(4) DEFAULT '0';

ALTER TABLE comment__category_group CHANGE groupId id bigint(20) NOT NULL auto_increment;
ALTER TABLE comment__category_group CHANGE isQuiet is_quiet tinyint(4) DEFAULT '0';

ALTER TABLE comment__comment CHANGE commentId id bigint(20) NOT NULL auto_increment;
ALTER TABLE comment__comment CHANGE threadId thread_id bigint(20) NOT NULL;
ALTER TABLE comment__comment CHANGE authorId author_user_id bigint(20);
ALTER TABLE comment__comment CHANGE createDate created_at datetime;
ALTER TABLE comment__comment CHANGE modifiedDate updated_at datetime;
ALTER TABLE comment__comment CHANGE deletedDate deleted_at datetime;

ALTER TABLE comment__thread CHANGE threadId id bigint(20) NOT NULL auto_increment;
ALTER TABLE comment__thread CHANGE categoryId category_id bigint(20);
ALTER TABLE comment__thread CHANGE authorId author_user_id bigint(20) NOT NULL;
ALTER TABLE comment__thread CHANGE createDate created_at datetime NOT NULL;
ALTER TABLE comment__thread CHANGE deletedDate deleted_at datetime;
ALTER TABLE comment__thread CHANGE isQuiet is_quiet tinyint(4) DEFAULT '0';
