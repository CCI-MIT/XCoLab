ALTER TABLE activities_ActivityEntry DROP `activityEntryTitle`;
ALTER TABLE activities_ActivityEntry DROP `activityEntryBody`;

ALTER TABLE activities_ActivityEntry ADD activityType VARCHAR(30) NULL;
ALTER TABLE activities_ActivityEntry ADD activitySubType VARCHAR(75) NULL;
ALTER TABLE activities_ActivityEntry
  MODIFY COLUMN activityType VARCHAR(30) AFTER createDate,
  MODIFY COLUMN activitySubType VARCHAR(75) AFTER activityType;
