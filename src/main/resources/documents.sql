CREATE TABLE IF NOT EXISTS `documents` (
    name varchar(64) UNIQUE,
    author bigint NOT NULL,
    content longtext NOT NULL
);