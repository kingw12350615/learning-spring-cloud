INSERT INTO user_details(id,birth_date,name)
VALUES(10001, current_date(), 'Ranga');

INSERT INTO user_details(id,birth_date,name)
VALUES(10002, current_date(), 'Tanya');

INSERT INTO user_details(id,birth_date,name)
VALUES(10003, current_date(), 'Tina');

INSERT INTO post(id,description,user_id)
VALUES(20001, 'I want to learn AWS', 10001);

INSERT INTO post(id,description,user_id)
VALUES(20002, 'I want to learn DevOps', 10001);

INSERT INTO post(id,description,user_id)
VALUES(20003, 'I want to get AWS certified', 10002);

INSERT INTO post(id,description,user_id)
VALUES(20004, 'I want to learn multi cloud', 10002);