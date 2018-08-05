INSERT INTO comment__category_group (id, description, url, isQuiet) VALUES (701, 'Test Category', '/discussion', 0);

INSERT INTO comment__category (id, groupId, authorUserId, name, description, createdAt, deletedAt, sort, isQuiet) VALUES (101, 701, 10144, 'General', 'Description', '2010-10-02 00:00:00', null, null, 0);

INSERT INTO comment__thread (id, categoryId, authorUserId, title, createdAt, deletedAt, isQuiet) VALUES (201, 101, 164395, 'Thread title', '2013-05-25 14:30:52', null, 0);

INSERT INTO comment__comment (id, threadId, authorUserId, createdAt, updatedAt, deletedAt, content) VALUES (301, 201, 164395, '2010-08-04 16:00:00', null, null, 'Comment content');
INSERT INTO comment__comment (id, threadId, authorUserId, createdAt, updatedAt, deletedAt, content) VALUES (302, 201, 12345, '2010-08-04 16:36:37', null, null, 'Comment content 2');
INSERT INTO comment__comment (id, threadId, authorUserId, createdAt, updatedAt, deletedAt, content) VALUES (351, 201, 10144, '2010-09-05 16:38:45', null, '2010-09-05 16:38:45', 'Deleted comment');
