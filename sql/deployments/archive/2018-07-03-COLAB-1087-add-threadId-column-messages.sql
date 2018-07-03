--Update message table
ALTER TABLE xcolab_Message
ADD COLUMN threadId BIGINT AUTO_INCREMENT AFTER repliesTo;
