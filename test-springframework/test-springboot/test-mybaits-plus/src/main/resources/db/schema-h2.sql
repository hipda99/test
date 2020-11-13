DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '����ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '����',
	age INT(11) NULL DEFAULT NULL COMMENT '����',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '����',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS book;

CREATE TABLE book
(
    id INT NOT NULL,
    b_id INT NOT NULL,
    b_name VARCHAR(50),
    remark VARCHAR(200),
    PRIMARY KEY (id)
);
