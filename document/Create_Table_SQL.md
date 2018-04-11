# CREATE TABLE SQL

CREATE TABLE User(
    name VARCHAR(32) NOT NULL,
    id VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    code CHAR(64) NOT NULL,
    gender INT(1) NOT NULL,
    PRIMARY KEY(id,code)
);

CREATE TABLE Baby(
    code CHAR(64) NOT NULL,
    baby_name VARCHAR(32) NOT NULL,
    gender INT(1) NOT NULL,
    PRIMARY KEY(code,baby_name)
);
CREATE TABLE Parent(
    mother_id VARCHAR(32) DEFAULT NULL,
    father_id VARCHAR(32) DEFAULT NULL,
    code CHAR(64) NOT NULL
    PRIMARY KEY(code)
);
CREATE TABLE Baby_Diary(
    code CHAR(64) NOT NULL,
    baby_name VARCHAR(32) NOT NULL,
    date_time DATETIME NOT NULL,
    subject VARCHAR(64) NOT NULL,
    diary TEXT DEFAULT NULL,
    PRIMARY KEY(code,baby_name,date_time)

);
CREATE TABLE Growth_Data(
    code CHAR(64) NOT NULL,
    baby_name VARCHAR(32) NOT NULL,
    weight INT(2),
    height INT(3),
    date DATE NOT NULL,
    PRIMARY KEY(code,baby_name,date)
);

CREATE TABLE BABYBOOK_STORY(
    code CHAR(64) NOT NULL,
    baby_name VARCHAR(32) NOT NULL,
    story TEXT DEFAULT NULL,
    index INT(1) NOT NULL,
    PRIMARY KEY(code,baby_name,index)
);

CREATE TABLE IMAGE(
    code CHAR(64) NOT NULL,
    baby_name VARCHAR(32) NOT NULL,
    date_time DATETIME NOT NULL,
    index INT(1) NOT NULL,
    img_path TEXT NOT NULL,
    PRIMARY KEY(code,baby_name,date_time,index)
);

CREATE TABLE PLAN(
    code CHAR(64) NOT NULL,
    content TEXT DEFAULT NULL,
    date DATE NOT NULL,
    PRIMARY KEY(code,date)
);

CREATE TABLE VIDEO(
    code CHAR(64) NOT NULL,
    baby_name VARCHAR(32) NOT NULL,
    video_path TEXT NOT NULL,
    PRIMARY KEY(code,baby_name)
);
