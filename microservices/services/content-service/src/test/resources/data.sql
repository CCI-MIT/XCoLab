INSERT INTO content__content_article (id,author_user_id,created_at,max_version_id,folder_id,edit_role_group_id,view_role_group_id,visible) VALUES (2,2000335,'2016-04-12 17:28:45',4778,5,NULL,NULL,1);
INSERT INTO content__content_article (id,author_user_id,created_at,max_version_id,folder_id,edit_role_group_id,view_role_group_id,visible) VALUES (3,2000335,'2016-04-12 17:28:45',4778,5,NULL,NULL,1);
INSERT INTO content__content_article_version (id,article_id,folder_id,author_user_id,created_at,title,content) VALUES (6,2,5,2000335,'2016-04-12 17:28:45','Landing page top news','\n\n\n	<h2>Top News</h2>\n</div>\n');
INSERT INTO content__content_article_version (id,article_id,folder_id,author_user_id,created_at,title,content) VALUES (1566,2,5,2000335,'2016-05-16 17:04:45','Landing page top news','<h2>Top News</h2>\n\n');
INSERT INTO content__content_article_version (id,article_id,folder_id,author_user_id,created_at,title,content) VALUES (1567,3,5,2000335,'2016-05-16 17:04:45','Landing page top news','<h2>Top News</h2>\n\n');
INSERT INTO content__content_folder (id,name,description,parent_folder_id) VALUES (2,'Static Content','Displays static content that should be editable on the site',1);
INSERT INTO content__content_folder (id,name,description,parent_folder_id) VALUES (3,'Static Content','Displays static content that should be editable on the site',2);

INSERT INTO content__file_entry (id,created_at,file_extension,file_name,file_size) VALUES (901,'2013-01-01 00:00:00','jpeg',' ',10);
INSERT INTO content__content_page (id,title,menu_article_id,content_article_id,created_at,updated_at) VALUES (2,'crowdsourcing',1550,7,'2017-03-10 02:00:00','2017-03-10 02:00:00');
