    INSERT INTO ADMIN (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME) VALUES
    ('Michelle@gmail.com', '$2a$11$svNYIAuMP/WqpzOdbn642e9v1XY2meAcrcEGNaCiJO96ZmXlfeYbS', 'Michelle',	'Steenvoorden'),
    ('ss@gmail.com', '$2a$11$i.BlTECJY6QxkuJE5fhTV.bONCsh1NmMgur2iFKH0gWNNHuXwume2', 'seb', 'sti'),
    ('david@gmail.com', '$2a$11$vpfutz1f5Gj6JpVGPHGcQO06H0Pl1RqDwu8r7tqX3t2QDnL2WUF/y', 'David', 'Mortensen'),
    ('jesper@godmail.com', '$2a$11$i1qrYHADEQHaQ6sV2ck8s.CCcIxAHwY6VdW/eMMvukpaDRVXzqWuC', 'Jesper', 'Hallelujah Johnsson');


    INSERT INTO SPECIES (NAME, LATIN_NAME, SUNLIGHT, WATER, NUTRITION, READY_TO_EAT, SHORT_INFO, FULL_INFO) VALUES
    ('Redskin Peppers','Capsicum annuum var. annuum', 'Direct sunlight', 2, 0, 155, 'Redskin peppers is round and compact and is optimal for pots. Peppers can be pre-planted in march and needs to be repotted. It loves warm places and sunlight and needs protection from heavy wind.', 'Long desc'),
    ('Early Carrots', 'Daucus', 'Moderate sunlight', 1, 240, 100, 'These type of carrots get planted in the spring and can be harvested continuously under the summer months.', 'Long desc'),
    ('Chili', 'Capsicum annuum', 'Direct sunlight', 2, 0, 186, 'Chili can be planted all year around, but needs extra light in the winter. Chili can be pre-planted in march and needs to be repotted. It loves warm places and sunlight and needs protection from heavy wind.', 'Long desc'),
    ('Strawberries', 'Fragaria', 'Direct sunlight', 3, 0, 120, 'Strawberries can get planted in early in the spring and can be harvested between june and august.', 'Long desc'),
    ('Sweetpeas', 'Pisum sativum','Direct sunlight', 2, 0, 60, 'Sweetpeas is easy to handle. You should plant it with some weeks apart, because the plant have a short life and this extends the season. The more you harvest, the more the plant produces.', 'Long desc'),
    ('Cherry Tomatoes - Primagold', 'Solanum lycopersicum var. cerasiforme', 'Direct sunlight', 1, 0, 120, 'A bountiful, compact cherry tomato that does not need to be plucked or tied up. Produces sweet little tomatoes all summer long. Perfect for the balcony, terrace or in the kitchen window. Grow indoors.', 'Long desc'),
    ('Zucchini', 'Cucurbita pepo', 'Direct sunlight', 1, 0, 120, 'Zucchini can get planted in March or April and you can do the first harvest in july.', 'Long desc');


    INSERT INTO PLANT (PLANT_NAME, CREATED, SPECIES_ID, ADMIN_ID) VALUES
        ('Berra', '2023-02-22', 3, 2),
        ('Tomas', '2023-03-26', 1, 2),
        ('Ulla', '2023-03-13', 2, 2),
        ('Mammy', '2023-03-12',4, 2);


