-- CREATE DATABASE IF NOT EXISTS db_course DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
-- USE db_course;

CREATE TABLE IF NOT EXISTS pe_course (
	id INT auto_increment NOT NULL,
	title varchar(64) NULL,
	description varchar(512) NULL,
	CONSTRAINT pe_course_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS pr_user_course (
	user_id INT NOT NULL,
	course_id INT NOT NULL,
	CONSTRAINT pr_user_course_PK PRIMARY KEY (user_id,course_id),
	CONSTRAINT pr_user_course_FK_course FOREIGN KEY (course_id) REFERENCES db_course.pe_course(id) ON UPDATE CASCADE,
	CONSTRAINT pr_user_course_FK_user FOREIGN KEY (user_id) REFERENCES db_user.pe_user(id) ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
