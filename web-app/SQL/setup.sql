/* SQL to set up the Databse */

USE socioapp;

CREATE TABLE users (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(20) NOT NULL UNIQUE,
	email VARCHAR(320) NOT NULL UNIQUE,
	password CHAR(41) NOT NULL,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	admin TINYINT(1) DEFAULT 0);
	
CREATE TABLE subjects (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	phone_num VARCHAR(13) NOT NULL,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	data_cache TEXT;
	
CREATE TABLE contacts (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	subject_id INT UNSIGNED NOT NULL);
	
CREATE TABLE surveys (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255)),
	created DATETIME,
	first_q INT UNSIGNED NOT NULL,
	/* field for each day; holds times in 24 hour format separtated by commas */
	mo VARCHAR(255),
	tu VARCHAR(255),
	we VARCHAR(255),
	th VARCHAR(255),
	fr VARCHAR(255),
	sa VARCHAR(255),
	su VARCHAR(255));
	
CREATE TABLE questions (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	text TEXT);
	
CREATE TABLE branches (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	prev_q INT UNSIGNED NOT NULL, /*foreign keys*/
	next_q INT UNSIGNED NOT NULL);
	
CREATE TABLE conditions (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	branch_id INT UNSIGNED NOT NULL,
	prereq_q INT UNSIGNED NOT NULL,
	req_choice INT UNSIGNED NOT NULL);
	
CREATE TABLE choices (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	text VARCHAR(255),
	question_id INT UNSIGNED);
	
CREATE TABLE answers (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	question_id INT UNSIGNED NOT NULL,
	subject_id INT UNSIGNED NOT NULL,
	choice_id INT UNSIGNED,
	ans_text TEXT,
	created DATETIME);
	
	
CREATE TABLE location_actions (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	subject_id INT UNSIGNED NOT NULL,
	created DATETIME NOT NULL,
	longitude DOUBLE NOT NULL,
	lattitude DOUBLE NOT NULL);
	
CREATE TABLE call_actions (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	subject_id INT UNSIGNED NOT NULL,
	contact_id INT UNSIGNED NOT NULL,
	created DATETIME NOT NULL,
	act_type ENUM('call', 'text') NOT NULL DEFAULT 'call',
	duration SMALLINT UNSIGNED
	/* other things? */);
	
CREATE TABLE status_changes (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	subject_id INT UNSIGNED NOT NULL,
	created DATETIME NOT NULL,
	status ENUM('enabled', 'disbled') NOT NULL,
	feature ENUM('gps', 'call log', 'text log', 'app') NOT NULL);
