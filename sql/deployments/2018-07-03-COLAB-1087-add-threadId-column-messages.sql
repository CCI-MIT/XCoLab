--Update message table
ALTER TABLE xcolab_MessageRecipientStatus
ADD COLUMN threadId BIGINT AFTER userId;
CREATE INDEX xcolab_MessageRecipientStatus_threadId_index ON xcolab_MessageRecipientStatus (threadId);
