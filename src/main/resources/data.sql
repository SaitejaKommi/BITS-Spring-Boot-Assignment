-- 10 students
INSERT INTO student (name, email) VALUES ('Alice Kumar', 'alice.kumar@univ.edu');
INSERT INTO student (name, email) VALUES ('Bob Singh', 'bob.singh@univ.edu');
INSERT INTO student (name, email) VALUES ('Carol Dsouza', 'carol.dsouza@univ.edu');
INSERT INTO student (name, email) VALUES ('David Rao', 'david.rao@univ.edu');
INSERT INTO student (name, email) VALUES ('Elena Patel', 'elena.patel@univ.edu');
INSERT INTO student (name, email) VALUES ('Farhan Khan', 'farhan.khan@univ.edu');
INSERT INTO student (name, email) VALUES ('Grace Thomas', 'grace.thomas@univ.edu');
INSERT INTO student (name, email) VALUES ('Harsh Mehta', 'harsh.mehta@univ.edu');
INSERT INTO student (name, email) VALUES ('Isha Nair', 'isha.nair@univ.edu');
INSERT INTO student (name, email) VALUES ('Jay Verma', 'jay.verma@univ.edu');

-- 10 courses
INSERT INTO course (title, duration) VALUES ('Introduction to Java', '8 weeks');
INSERT INTO course (title, duration) VALUES ('Spring Framework Basics', '6 weeks');
INSERT INTO course (title, duration) VALUES ('Database Systems', '10 weeks');
INSERT INTO course (title, duration) VALUES ('Web Development', '8 weeks');
INSERT INTO course (title, duration) VALUES ('Data Structures', '12 weeks');
INSERT INTO course (title, duration) VALUES ('Software Engineering', '8 weeks');
INSERT INTO course (title, duration) VALUES ('Computer Networks', '9 weeks');
INSERT INTO course (title, duration) VALUES ('Operating Systems', '10 weeks');
INSERT INTO course (title, duration) VALUES ('Cloud Computing', '7 weeks');
INSERT INTO course (title, duration) VALUES ('Machine Learning Intro', '8 weeks');

-- Many-to-many links (student_id, course_id)
INSERT INTO student_course (student_id, course_id) VALUES (1, 1);
INSERT INTO student_course (student_id, course_id) VALUES (1, 2);
INSERT INTO student_course (student_id, course_id) VALUES (2, 2);
INSERT INTO student_course (student_id, course_id) VALUES (2, 3);
INSERT INTO student_course (student_id, course_id) VALUES (3, 1);
INSERT INTO student_course (student_id, course_id) VALUES (3, 4);
INSERT INTO student_course (student_id, course_id) VALUES (4, 5);
INSERT INTO student_course (student_id, course_id) VALUES (5, 6);
INSERT INTO student_course (student_id, course_id) VALUES (5, 7);
INSERT INTO student_course (student_id, course_id) VALUES (6, 8);
INSERT INTO student_course (student_id, course_id) VALUES (7, 9);
INSERT INTO student_course (student_id, course_id) VALUES (8, 10);
INSERT INTO student_course (student_id, course_id) VALUES (9, 3);
INSERT INTO student_course (student_id, course_id) VALUES (9, 5);
INSERT INTO student_course (student_id, course_id) VALUES (10, 1);
INSERT INTO student_course (student_id, course_id) VALUES (10, 10);
