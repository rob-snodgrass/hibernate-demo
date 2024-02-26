insert into course(id, name) values (10001, 'JPA tutorial');
insert into course(id, name) values (10002, 'Spring tutorial');
insert into course(id, name) values (10003, 'Spring Boot tutorial');
insert into course(id, name) values (10004, 'Maxine tutorial');

insert into passport(id, number) values (40001, 'E12345');
insert into passport(id, number) values (40002, 'J45821');
insert into passport(id, number) values (40003, 'F87654');

insert into student(id, name, passport_id) values (20001, 'Adam',40001);
insert into student(id, name, passport_id) values (20002, 'Jane',40002);
insert into student(id, name, passport_id) values (20003, 'Maxine',40003);

insert into review(id, rating, description, course_id) values (50001, '5', 'Great course', 10001);
insert into review(id, rating, description, course_id) values (50002, '4', 'Good course', 10001);
insert into review(id, rating, description, course_id) values (50003, '1', 'Yucky course', 10003);

insert into student_course(student_id, course_id) values (20001,10001);
insert into student_course(student_id, course_id) values (20002,10001);
insert into student_course(student_id, course_id) values (20003,10001);
insert into student_course(student_id, course_id) values (20001,10003);