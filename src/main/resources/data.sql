    INSERT INTO ADMIN (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME) VALUES
    ('Michelle@gmail.com', '$2a$11$svNYIAuMP/WqpzOdbn642e9v1XY2meAcrcEGNaCiJO96ZmXlfeYbS', 'Michelle',	'Steenvoorden'),
    ('ss@gmail.com', '$2a$11$i.BlTECJY6QxkuJE5fhTV.bONCsh1NmMgur2iFKH0gWNNHuXwume2', 'seb', 'sti'),
    ('david@gmail.com', '$2a$11$vpfutz1f5Gj6JpVGPHGcQO06H0Pl1RqDwu8r7tqX3t2QDnL2WUF/y', 'David', 'Mortensen'),
    ('jesper@godmail.com', '$2a$11$i1qrYHADEQHaQ6sV2ck8s.CCcIxAHwY6VdW/eMMvukpaDRVXzqWuC', 'Jesper', 'Hallelujah Johnsson');


    INSERT INTO SPECIES (NAME, SUNLIGHT, WATER, NUTRITION, READY_TO_EAT, SHORT_INFO, FULL_INFO) VALUES
    ('Tomat', 'Direct Sunlight', 5, 14, 45, 'Biff tomat is one of the most popular tomato species enjoyed in the world', 'LOOOOOng list of all the specific info there is about BIFF TOMAT'),
    ('Fragaria', 'Moderate Sunlight', 2, 240, 75, 'Wild Strawberries are the parent species of Strawberries Wild Strawberries are the parent species of Strawberries Wild Strawberries are the parent species of Strawberries', 'OOOOOOOO LOOOOOng JOOOOHNSON list of all the specific info there is about STRAWBERRIES');

    INSERT INTO PLANT (PLANT_NAME, CREATED, SPECIES_ID, ADMIN_ID) VALUES
        ('Tomat', '2023-02-22', 1, 3),
        ('Davids Tomat', '2023-03-26', 1, 3),
        ('Inte Tomat', '2023-03-13', 1, 1),
        ('Jordgubbe', '2023-03-12',2, 1);


