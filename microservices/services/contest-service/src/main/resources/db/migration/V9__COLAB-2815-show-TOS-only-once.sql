CREATE TABLE IF NOT EXISTS contest__tos_agreement
(
  contest_id BIGINT(20) NOT NULL,
  user_id BIGINT(20) NOT NULL,
  agreed TINYINT(4) DEFAULT NULL,
  PRIMARY KEY (contest_id, user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
