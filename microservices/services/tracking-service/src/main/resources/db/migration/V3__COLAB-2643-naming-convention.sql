rename table xcolab_BalloonLink to tracking__balloon_link;
rename table xcolab_BalloonText to tracking__balloon_text;
rename table xcolab_BalloonUserTracking to tracking__balloon_user_tracking;
rename table xcolab_TrackedVisit to tracking__tracked_visit;
rename table xcolab_TrackedVisitor2User to tracking__tracked_visitor;

ALTER TABLE tracking__balloon_link CHANGE uuid_ uuid varchar(75) NOT NULL;
ALTER TABLE tracking__balloon_link CHANGE targetUrl target_url varchar(75);
ALTER TABLE tracking__balloon_link CHANGE balloonUserUuid balloon_user_uuid varchar(75);
ALTER TABLE tracking__balloon_link CHANGE createDate created_at datetime;

ALTER TABLE tracking__balloon_text CHANGE id_ id bigint(20) NOT NULL auto_increment;
ALTER TABLE tracking__balloon_text CHANGE textBeforeForm text_before_form longtext;
ALTER TABLE tracking__balloon_text CHANGE textBeforeShareButtons text_before_share_buttons longtext;
ALTER TABLE tracking__balloon_text CHANGE emailTemplate email_template longtext;
ALTER TABLE tracking__balloon_text CHANGE emailSubjectTemplate email_subject_template varchar(255);
ALTER TABLE tracking__balloon_text CHANGE shareTitle share_title varchar(255);
ALTER TABLE tracking__balloon_text CHANGE shareDescription share_description text;

ALTER TABLE tracking__balloon_user_tracking CHANGE uuid_ uuid varchar(75) NOT NULL;
ALTER TABLE tracking__balloon_user_tracking CHANGE createDate created_at datetime;
ALTER TABLE tracking__balloon_user_tracking CHANGE registrationDate registration_date datetime;
ALTER TABLE tracking__balloon_user_tracking CHANGE formFiledDate form_filed_date datetime;
ALTER TABLE tracking__balloon_user_tracking CHANGE userId user_id bigint(20);
ALTER TABLE tracking__balloon_user_tracking CHANGE balloonTextId balloon_text_id bigint(20);
ALTER TABLE tracking__balloon_user_tracking CHANGE extraData extra_data longtext;
ALTER TABLE tracking__balloon_user_tracking CHANGE balloonLinkUuid balloon_link_uuid varchar(75);
ALTER TABLE tracking__balloon_user_tracking CHANGE balloonLinkContext balloon_link_context varchar(75);
ALTER TABLE tracking__balloon_user_tracking CHANGE userAgent user_agent varchar(500);

ALTER TABLE tracking__tracked_visit CHANGE id_ id bigint(20) NOT NULL auto_increment;
ALTER TABLE tracking__tracked_visit CHANGE uuid_ visitor_uuid varchar(36);
ALTER TABLE tracking__tracked_visit CHANGE createDate created_at datetime;

ALTER TABLE tracking__tracked_visitor CHANGE uuid_ uuid varchar(36) NOT NULL;
ALTER TABLE tracking__tracked_visitor CHANGE userId user_id bigint(20);
ALTER TABLE tracking__tracked_visitor CHANGE createDate created_at datetime;
