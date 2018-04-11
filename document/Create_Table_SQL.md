# CREATE TABLE SQL

CREATE TABLE User(<br>
<tb>    name VARCHAR(32) NOT NULL,<br>
    id VARCHAR(32) NOT NULL,<br>
    password VARCHAR(32) NOT NULL,<br>
    code CHAR(64) NOT NULL,<br>
    gender INT(1) NOT NULL,<br>
    PRIMARY KEY(id,code)<br>
);<br>
<br>
CREATE TABLE Baby(<br>
    code CHAR(64) NOT NULL,<br>
    baby_name VARCHAR(32) NOT NULL,<br>
    gender INT(1) NOT NULL,<br>
    PRIMARY KEY(code,baby_name)<br>
);<br>
CREATE TABLE Parent(<br>
    mother_id VARCHAR(32) DEFAULT NULL,<br>
    father_id VARCHAR(32) DEFAULT NULL,<br>
    code CHAR(64) NOT NULL<br>
    PRIMARY KEY(code)<br>
);<br>
CREATE TABLE Baby_Diary(<br>
    code CHAR(64) NOT NULL,<br>
    baby_name VARCHAR(32) NOT NULL,<br>
    date_time DATETIME NOT NULL,<br>
    subject VARCHAR(64) NOT NULL,<br>
    diary TEXT DEFAULT NULL,<br>
    PRIMARY KEY(code,baby_name,date_time)<br>
);<br>
CREATE TABLE Growth_Data(<br>
    code CHAR(64) NOT NULL,<br>
    baby_name VARCHAR(32) NOT NULL,<br>
    weight INT(2),<br>
    height INT(3),<br>
    date DATE NOT NULL,<br>
    PRIMARY KEY(code,baby_name,date)<br>
);<br>
<br>
CREATE TABLE BABYBOOK_STORY(<br>
    code CHAR(64) NOT NULL,<br>
    baby_name VARCHAR(32) NOT NULL,<br>
    story TEXT DEFAULT NULL,<br>
    index INT(1) NOT NULL,<br>
    PRIMARY KEY(code,baby_name,index)<br>
);<br>

CREATE TABLE IMAGE(<br>
    code CHAR(64) NOT NULL,<br>
    baby_name VARCHAR(32) NOT NULL,<br>
    date_time DATETIME NOT NULL,<br>
    index INT(1) NOT NULL,<br>
    img_path TEXT NOT NULL,<br>
    PRIMARY KEY(code,baby_name,date_time,index)<br>
);<br>
<br>
CREATE TABLE PLAN(<br>
    code CHAR(64) NOT NULL,<br>
    content TEXT DEFAULT NULL,<br>
    date DATE NOT NULL,<br>
    PRIMARY KEY(code,date)<br>
);<br>
<br>
CREATE TABLE VIDEO(<br>
    code CHAR(64) NOT NULL,<br>
    baby_name VARCHAR(32) NOT NULL,<br>
    video_path TEXT NOT NULL,<br>
    PRIMARY KEY(code,baby_name)<br>
);<br>
