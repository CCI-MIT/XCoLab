rename table flagging_Report to moderation__report;
rename table flagging_ReportTarget to moderation__report_target;

ALTER TABLE moderation__report CHANGE reportId id bigint(20) NOT NULL auto_increment;
ALTER TABLE moderation__report CHANGE reporterMemberId reporter_user_id bigint(20) NOT NULL;
ALTER TABLE moderation__report CHANGE targetType target_type varchar(50) NOT NULL;
ALTER TABLE moderation__report CHANGE targetId target_id bigint(20) NOT NULL;
ALTER TABLE moderation__report CHANGE targetAdditionalId target_additional_id bigint(20) NOT NULL DEFAULT '0';
ALTER TABLE moderation__report CHANGE managerAction manager_action varchar(50) NOT NULL DEFAULT 'PENDING';
ALTER TABLE moderation__report CHANGE managerMemberId manager_user_id bigint(20);
ALTER TABLE moderation__report CHANGE managerActionDate manager_action_date datetime;
ALTER TABLE moderation__report CHANGE createDate created_at datetime;

ALTER TABLE moderation__report_target CHANGE reportTargetId id bigint(20) NOT NULL auto_increment;
ALTER TABLE moderation__report_target CHANGE notificationThreshold notification_threshold int(11) NOT NULL DEFAULT '0';
ALTER TABLE moderation__report_target CHANGE screeningThreshold screening_threshold int(11) NOT NULL DEFAULT '-1';
