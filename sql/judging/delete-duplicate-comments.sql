DELETE FROM xcolab_DiscussionMessage WHERE authorId = 10144 AND TRIM(body) = "" AND createDate > "2014-05-08 10:00:00";

DELETE FROM
	xcolab_DiscussionMessage
WHERE
	pk IN (
		SELECT
			pk
		FROM (
			SELECT
				pk
			FROM
				xcolab_DiscussionMessage
			WHERE
				authorId = 10144 AND
				createDate > "2014-05-08 10:00:00" AND
				(
					body IN (
						SELECT
							body
						FROM
							xcolab_DiscussionMessage
						WHERE
							authorId = 10144 AND
							createDate > "2014-05-08 10:00:00"
						GROUP BY
							body
						HAVING COUNT(*) > 1
					) AND
					pk NOT IN 
					(
						SELECT
							MAX(pk) AS pk
						FROM
							xcolab_DiscussionMessage
						WHERE
							authorId = 10144 AND
							createDate > "2014-05-08 10:00:00"
						GROUP BY
							body
						HAVING COUNT(*) > 1
					)
				)
		) AS duplicateCommentPks
	)
;