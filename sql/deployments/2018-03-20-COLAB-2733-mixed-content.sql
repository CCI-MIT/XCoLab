-- Content articles
update xcolab_ContentArticleVersion set content = replace(content,'http://climatecolab.org','https://www.climatecolab.org');
update xcolab_ContentArticleVersion set content = replace(content,'http://www.climatecolab.org','https://www.climatecolab.org');
update xcolab_ContentArticleVersion set content = replace(content,'https://climatecolab.org','https://www.climatecolab.org');
update xcolab_ContentArticleVersion set content = replace(content,'http://i.imgur.com','https://i.imgur.com');
update xcolab_ContentArticleVersion set content = replace(content,'http://imgur.com','https://i.imgur.com');
update xcolab_ContentArticleVersion set content = replace(content,'http://youtube.com','https://www.youtube.com');
update xcolab_ContentArticleVersion set content = replace(content,'http://energy.mit.edu','https://energy.mit.edu');
update xcolab_ContentArticleVersion set content = replace(content,'http://lae.mit.edu','https://lae.mit.edu');
update xcolab_ContentArticleVersion set content = replace(content,'http://environment.yale.edu','https://environment.yale.edu');
update xcolab_ContentArticleVersion set content = replace(content,'http://static3.businessinsider.com','https://static3.businessinsider.com');

-- Comments
update comment_Comment set content = replace(content,'http://climatecolab.org','https://www.climatecolab.org');
update comment_Comment set content = replace(content,'http://www.climatecolab.org','https://www.climatecolab.org');
update comment_Comment set content = replace(content,'https://climatecolab.org','https://www.climatecolab.org');
update comment_Comment set content = replace(content,'http://i.imgur.com','https://i.imgur.com');
update comment_Comment set content = replace(content,'http://imgur.com','https://i.imgur.com');
update comment_Comment set content = replace(content,'http://youtube.com','https://www.youtube.com');
update comment_Comment set content = replace(content,'http://energy.mit.edu','https://energy.mit.edu');
update comment_Comment set content = replace(content,'http://lae.mit.edu','https://lae.mit.edu');
update comment_Comment set content = replace(content,'http://environment.yale.edu','https://environment.yale.edu');
update comment_Comment set content = replace(content,'http://static3.businessinsider.com','https://static3.businessinsider.com');
