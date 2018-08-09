INSERT INTO comment__category_group (id, description, url, is_quiet) VALUES (701, 'Test Category', '/discussion', 0);

INSERT INTO comment__category (id, group_id, author_user_id, name, description, created_at, deleted_at, sort, is_quiet) VALUES (101, 701, 10144, 'General', 'Description', '2010-10-02 00:00:00', null, null, 0);

INSERT INTO comment__thread (id, category_id, author_user_id, title, created_at, deleted_at, is_quiet) VALUES (201, 101, 164395, 'Thread title', '2013-05-25 14:30:52', null, 0);

INSERT INTO comment__comment (id, thread_id, author_user_id, created_at, updated_at, deleted_at, content) VALUES (301, 201, 164395, '2010-08-04 16:00:00', null, null, 'Comment content');
INSERT INTO comment__comment (id, thread_id, author_user_id, created_at, updated_at, deleted_at, content) VALUES (302, 201, 12345, '2010-08-04 16:36:37', null, null, 'Comment content 2');
INSERT INTO comment__comment (id, thread_id, author_user_id, created_at, updated_at, deleted_at, content) VALUES (351, 201, 10144, '2010-09-05 16:38:45', null, '2010-09-05 16:38:45', 'Deleted comment');
