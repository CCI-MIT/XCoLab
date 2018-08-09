rename table files_FileEntry to content__file_entry;
rename table xcolab_ContentArticle to content__content_article;
rename table xcolab_ContentArticleVersion to content__content_article_version;
rename table xcolab_ContentFolder to content__content_folder;
rename table xcolab_ContentPage to content__content_page;


ALTER TABLE content__file_entry CHANGE fileEntryId id bigint(20) NOT NULL auto_increment;
ALTER TABLE content__file_entry CHANGE createDate created_at datetime;
ALTER TABLE content__file_entry CHANGE fileEntryExtension file_extension varchar(10);
ALTER TABLE content__file_entry CHANGE fileEntryName file_name varchar(255);
ALTER TABLE content__file_entry CHANGE fileEntrySize file_size int(11);

ALTER TABLE content__content_article CHANGE contentArticleId id bigint(20) NOT NULL auto_increment;
ALTER TABLE content__content_article CHANGE authorId author_user_id bigint(20);
ALTER TABLE content__content_article CHANGE createDate created_at datetime;
ALTER TABLE content__content_article CHANGE maxVersionId max_version_id bigint(20);
ALTER TABLE content__content_article CHANGE folderId folder_id bigint(20);
ALTER TABLE content__content_article CHANGE editRoleGroupId edit_role_group_id bigint(20);
ALTER TABLE content__content_article CHANGE viewRoleGroupId view_role_group_id bigint(20);

ALTER TABLE content__content_article_version CHANGE contentArticleVersionId id bigint(20) NOT NULL auto_increment;
ALTER TABLE content__content_article_version CHANGE contentArticleId article_id bigint(20);
ALTER TABLE content__content_article_version CHANGE folderId folder_id bigint(20);
ALTER TABLE content__content_article_version CHANGE authorId author_user_id bigint(20);
ALTER TABLE content__content_article_version CHANGE createDate created_at datetime;

ALTER TABLE content__content_folder CHANGE contentFolderId id bigint(20) NOT NULL auto_increment;
ALTER TABLE content__content_folder CHANGE contentFolderName name varchar(255);
ALTER TABLE content__content_folder CHANGE contentFolderDescription description text;
ALTER TABLE content__content_folder CHANGE parentFolderId parent_folder_id bigint(20);

ALTER TABLE content__content_page CHANGE pageId id bigint(11) NOT NULL auto_increment;
ALTER TABLE content__content_page CHANGE metaDescription meta_description varchar(255);
ALTER TABLE content__content_page CHANGE menuArticleId menu_article_id bigint(11);
ALTER TABLE content__content_page CHANGE contentArticleId content_article_id bigint(11) NOT NULL;
ALTER TABLE content__content_page CHANGE createdDate created_at timestamp;
ALTER TABLE content__content_page CHANGE modifiedDate updated_at timestamp;
