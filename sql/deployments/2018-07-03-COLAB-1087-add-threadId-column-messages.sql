--Update message table
ALTER TABLE xcolab_Message
ADD COLUMN threadId BIGINT AFTER repliesTo;
CREATE INDEX xcolab_Message_threadId_index ON xcolab_Message (threadId);