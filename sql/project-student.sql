CREATE TABLE APP_STUDENT (num NUMBER PRIMARY KEY, name VARCHAR2(20), kor NUMBER, eng NUMBER, mat NUMBER);
CREATE SEQUENCE APP_STUDENT_SEQ;
INSERT INTO APP_STUDENT VALUES(APP_STUDENT_SEQ.NEXTVAL, 'aaa', 20, 10, 20);
SELECT * FROM APP_STUDENT;