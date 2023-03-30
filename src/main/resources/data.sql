    INSERT INTO ADMIN (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME) VALUES
    ('Michelle@gmail.com', '$2a$11$svNYIAuMP/WqpzOdbn642e9v1XY2meAcrcEGNaCiJO96ZmXlfeYbS', 'Michelle',	'Steenvoorden'),
      ('ss@gmail.com', '$2a$11$i.BlTECJY6QxkuJE5fhTV.bONCsh1NmMgur2iFKH0gWNNHuXwume2', 'seb', 'sti');




    INSERT INTO SPECIES (NAME, SUNLIGHT, WATER, NUTRITION, READY_TO_EAT, SHORT_INFO, FULL_INFO) VALUES
    ('Biff Tomat', 'Direct Sunlight', 5, 14, 45, 'Biff tomat is one of the most popular tomato species enjoyed in the world', 'LOOOOOng list of all the specific info there is about BIFF TOMAT'),
    ('CutieBerry', 'Moderate Sunlight', 2, 240, 75, 'Wild Strawberries are the parent species of Strawberries', 'OOOOOOOO LOOOOOng JOOOOHNSON list of all the specific info there is about STRAWBERRIES');

    INSERT INTO PLANT (PLANT_NAME, SPECIES_ID, ADMIN_ID) VALUES
        ('Tomat', 1, 1),
        ('Davids Tomat', 1, 1),
        ('Inte Tomat', 1, 1),
        ('Harry potter', 2, 1);


