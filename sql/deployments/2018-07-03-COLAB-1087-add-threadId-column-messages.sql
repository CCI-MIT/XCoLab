--Update message table
ALTER TABLE xcolab_MessageRecipientStatus
ADD COLUMN threadId VARCHAR(75) AFTER userId;

UPDATE xcolab_MessageRecipientStatus SET threadId=CONCAT(xcolab_MessageRecipientStatus.messageId,'-',xcolab_MessageRecipientStatus.userId);

CREATE INDEX xcolab_MessageRecipientStatus_threadId_index ON xcolab_MessageRecipientStatus (threadId);
