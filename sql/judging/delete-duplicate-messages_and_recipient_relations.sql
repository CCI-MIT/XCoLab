
ALTER TABLE xcolab_MessageRecipientStatus
ADD CONSTRAINT fk_messagerecipientstatus_messageid
FOREIGN KEY (messageId)
REFERENCES xcolab_Message (messageId)
ON DELETE CASCADE;

SET innodb_lock_wait_timeout=50000;


DELETE FROM
	xcolab_Message
WHERE
	messageId IN (
		SELECT
			messageId
		FROM (
			SELECT
				messageId
			FROM
				xcolab_Message
			WHERE
				fromId = 10144 AND
				createDate > "2014-05-08 10:00:00" AND
				(
					content IN (
						SELECT
							m.content AS content
						FROM
							xcolab_Message m,
							xcolab_MessageRecipientStatus ms
						WHERE
							m.fromId = 10144 AND
							m.createDate > "2014-05-08 10:00:00" AND
							m.messageId = ms.messageId
						GROUP BY
							m.content,
							ms.userId
						HAVING COUNT(*) > 1
					) AND
					messageId NOT IN 
					(
						SELECT
							MAX(m.messageId)
						FROM
							xcolab_Message m,
							xcolab_MessageRecipientStatus ms
						WHERE
							m.fromId = 10144 AND
							m.createDate > "2014-05-08 10:00:00" AND
							m.messageId = ms.messageId
						GROUP BY
							m.content,
							ms.userId
						HAVING COUNT(*) > 1
					)
				)
		) AS duplicateMessages
	)
;

ALTER TABLE xcolab_MessageRecipientStatus
DROP FOREIGN KEY fk_messagerecipientstatus_messageid;