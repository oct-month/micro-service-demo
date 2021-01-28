--CREATE DATABASE IF NOT EXISTS db_user;
--USE db_user;

CREATE TABLE IF NOT EXISTS pe_user (
	id INT UNSIGNED auto_increment NOT NULL,
	username varchar(32) NOT NULL,
	password varchar(32) NOT NULL,
	real_name varchar(32) NULL,
	mobile varchar(32) NULL,
	email varchar(32) NULL,
	CONSTRAINT pe_user_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
