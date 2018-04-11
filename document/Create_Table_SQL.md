# CREATE TABLE SQL

CREATE TABLE User(<br>
    name VARCHAR(32) NOT NULL,<br>
    id VARCHAR(32) NOT NULL,<br>
    password VARCHAR(32) NOT NULL,<br>
    gender INT(1) NOT NULL,<br>
    PRIMARY KEY(id)<br>
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
CREATE TABLE BabyBook_Story(<br>
    code CHAR(64) NOT NULL,<br>
    baby_name VARCHAR(32) NOT NULL,<br>
    story TEXT DEFAULT NULL,<br>
    index INT(1) NOT NULL,<br>
    PRIMARY KEY(code,baby_name,index)<br>
);<br>

CREATE TABLE Image(<br>
    code CHAR(64) NOT NULL,<br>
    baby_name VARCHAR(32) NOT NULL,<br>
    date_time DATETIME NOT NULL,<br>
    index INT(1) NOT NULL,<br>
    img_path TEXT NOT NULL,<br>
    PRIMARY KEY(code,baby_name,date_time,index)<br>
);<br>
<br>
CREATE TABLE Plan(<br>
    code CHAR(64) NOT NULL,<br>
    content TEXT DEFAULT NULL,<br>
    date DATE NOT NULL,<br>
    PRIMARY KEY(code,date)<br>
);<br>
<br>
CREATE TABLE Video(<br>
    code CHAR(64) NOT NULL,<br>
    baby_name VARCHAR(32) NOT NULL,<br>
    video_path TEXT NOT NULL,<br>
    PRIMARY KEY(code,baby_name)<br>
);<br>
