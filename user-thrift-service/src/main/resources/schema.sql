--CREATE DATABASE IF NOT EXISTS db_user DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
--USE db_user;

CREATE TABLE IF NOT EXISTS pe_user (
	id INT auto_increment NOT NULL,
	username varchar(32) NOT NULL,
	password varchar(32) NOT NULL,
	real_name varchar(32) NULL,
	mobile varchar(32) NULL,
	email varchar(32) NULL,
	CONSTRAINT pe_user_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS pe_teacher (
	user_id INT NOT NULL,
	introduction varchar(64) NULL,
	stars int UNSIGNED NULL,
	CONSTRAINT pe_teacher_PK PRIMARY KEY (user_id),
	CONSTRAINT pe_teacher_FK FOREIGN KEY (user_id) REFERENCES pe_user(id) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
