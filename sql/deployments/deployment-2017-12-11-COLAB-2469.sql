ALTER TABLE activities_ActivityEntry DROP `activityEntryTitle`;
ALTER TABLE activities_ActivityEntry DROP `activityEntryBody`;

ALTER TABLE activities_ActivityEntry ADD activityCategory VARCHAR(30) NULL;
ALTER TABLE activities_ActivityEntry ADD activityType VARCHAR(75) NULL;
ALTER TABLE activities_ActivityEntry
  MODIFY COLUMN activityCategory VARCHAR(30) AFTER createDate,
  MODIFY COLUMN activityType VARCHAR(75) AFTER activityCategory;
