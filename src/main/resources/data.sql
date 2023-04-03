    INSERT INTO ADMIN (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME) VALUES
    ('Michelle@gmail.com', '$2a$11$svNYIAuMP/WqpzOdbn642e9v1XY2meAcrcEGNaCiJO96ZmXlfeYbS', 'Michelle',	'Steenvoorden'),
    ('ss@gmail.com', '$2a$11$i.BlTECJY6QxkuJE5fhTV.bONCsh1NmMgur2iFKH0gWNNHuXwume2', 'Sebastian', 'Stillesjö'),
    ('david@gmail.com', '$2a$11$vpfutz1f5Gj6JpVGPHGcQO06H0Pl1RqDwu8r7tqX3t2QDnL2WUF/y', 'David', 'Mortensen'),
    ('jesper@godmail.com', '$2a$11$i1qrYHADEQHaQ6sV2ck8s.CCcIxAHwY6VdW/eMMvukpaDRVXzqWuC', 'Jesper', 'Hallelujah Johnsson');


    INSERT INTO SPECIES (NAME, LATIN_NAME, SUNLIGHT, SOIL, WATER, REPOT, NUTRITION, READY_TO_EAT, SHORT_INFO) VALUES
    ('Strawberries', 'Fragaria', 'Direct sunlight', 'Vegetable soil with cow manure', 14, 30, 14, 120, 'Strawberries can get planted early in the spring and can be harvested between June and August.'),
    ('Cherry Tomatoes - Primagold', 'Solanum lycopersicum var. cerasiforme', 'Direct sunlight', 'Vegetable soil', 1, 30, 7, 120, 'A bountiful, compact cherry tomato that does not need to be plucked or tied up. Produces sweet little tomatoes all summer long. Perfect for the balcony, terrace or in the kitchen window. Grow indoors.');



    INSERT INTO PLANT (PLANT_NAME, CREATED, SPECIES_ID, ADMIN_ID) VALUES
        ('Berra', '2023-04-03', 2, 2),
        ('Tomas', '2023-03-20', 1, 2),
        ('Ulla', '2023-03-10', 2, 2),
        ('Mammy', '2023-03-02',1, 2),
        ('Olle', '2023-02-20',1, 2);


