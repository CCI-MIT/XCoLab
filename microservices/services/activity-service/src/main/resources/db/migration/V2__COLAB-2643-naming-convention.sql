rename table activities_ActivityEntry to activity__activity_entry;
rename table xcolab_ActivitySubscription to activity__activity_subscription;

ALTER TABLE activity__activity_subscription CHANGE pk id bigint(20) NOT NULL auto_increment;
ALTER TABLE activity__activity_subscription CHANGE receiverId receiver_user_id bigint(20);
ALTER TABLE activity__activity_subscription CHANGE activityCategory activity_category varchar(30) NOT NULL;
ALTER TABLE activity__activity_subscription CHANGE categoryId category_id bigint(20) NOT NULL;
ALTER TABLE activity__activity_subscription CHANGE classNameId class_name_id bigint(20);
ALTER TABLE activity__activity_subscription CHANGE classPK class_pk bigint(20);
ALTER TABLE activity__activity_subscription CHANGE automaticSubscriptionCounter automatic_subscription_counter int(11);
ALTER TABLE activity__activity_subscription CHANGE extraData extra_data varchar(256);
ALTER TABLE activity__activity_subscription CHANGE createDate created_at datetime;
ALTER TABLE activity__activity_subscription CHANGE modifiedDate updated_at datetime;

ALTER TABLE activity__activity_entry CHANGE activityEntryId id bigint(20) NOT NULL auto_increment;
ALTER TABLE activity__activity_entry CHANGE memberId user_id bigint(20) NOT NULL;
ALTER TABLE activity__activity_entry CHANGE createDate created_at datetime NOT NULL;
ALTER TABLE activity__activity_entry CHANGE activityCategory activity_category varchar(30);
ALTER TABLE activity__activity_entry CHANGE activityType activity_type varchar(75);
ALTER TABLE activity__activity_entry CHANGE categoryId category_id bigint(20) NOT NULL;
ALTER TABLE activity__activity_entry CHANGE additionalId additional_id bigint(20);
ALTER TABLE activity__activity_entry CHANGE primaryType primary_type bigint(20);
ALTER TABLE activity__activity_entry CHANGE secondaryType secondary_type bigint(20);
ALTER TABLE activity__activity_entry CHANGE classPrimaryKey class_primary_key bigint(20);
ALTER TABLE activity__activity_entry CHANGE extraData extra_data varchar(75);
