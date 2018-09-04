CREATE TABLE IF NOT EXISTS user__sso_client_details
(
    id VARCHAR(50) PRIMARY KEY,
    secret VARCHAR(50),
    scope VARCHAR(50),
    registeredRedirectUri VARCHAR(191)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

