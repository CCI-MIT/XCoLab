rename table xcolab_ColabEmail to email__outgoing_email;

ALTER TABLE email__outgoing_email CHANGE colabEmailId id bigint(20) NOT NULL auto_increment;
ALTER TABLE email__outgoing_email CHANGE emailSubject email_subject varchar(191) NOT NULL COMMENT '  ';
ALTER TABLE email__outgoing_email CHANGE emailBody email_body text NOT NULL COMMENT '      ';
ALTER TABLE email__outgoing_email CHANGE emailTo email_to varchar(191) NOT NULL;
ALTER TABLE email__outgoing_email CHANGE emailFrom email_from varchar(191) NOT NULL;
ALTER TABLE email__outgoing_email CHANGE dateSent sent_at datetime NOT NULL;
ALTER TABLE email__outgoing_email CHANGE referenceId reference_id bigint(20);
ALTER TABLE email__outgoing_email CHANGE emailBodyHash email_body_hash varchar(64) NOT NULL;
ALTER TABLE email__outgoing_email CHANGE sent was_sent tinyint(1) DEFAULT '0';
