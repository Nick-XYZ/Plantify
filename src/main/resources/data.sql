    INSERT INTO ADMIN (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME) VALUES
    ('demo@gmail.com', '$2a$11$CEZxqNL.L88BLBcqOzNJMeX20kXvoakGKgPYduS6jBg24mI.ZMTia', 'demo',	'demolast'),
    ('Michelle@gmail.com', '$2a$11$svNYIAuMP/WqpzOdbn642e9v1XY2meAcrcEGNaCiJO96ZmXlfeYbS', 'Michelle',	'Steenvoorden'),
    ('ss@gmail.com', '$2a$11$i.BlTECJY6QxkuJE5fhTV.bONCsh1NmMgur2iFKH0gWNNHuXwume2', 'Sebastian', 'Stillesjö'),
    ('david@gmail.com', '$2a$11$vpfutz1f5Gj6JpVGPHGcQO06H0Pl1RqDwu8r7tqX3t2QDnL2WUF/y', 'David', 'Mortensen'),
    ('jesper@godmail.com', '$2a$11$i1qrYHADEQHaQ6sV2ck8s.CCcIxAHwY6VdW/eMMvukpaDRVXzqWuC', 'Jesper', 'Hallelujah Johnsson');


    INSERT INTO SPECIES (NAME, LATIN_NAME, SUNLIGHT, SOIL, WATER, REPOT, NUTRITION, READY_TO_EAT, SOWING_PERIOD) VALUES
    ('Cherry Tomato', 'Solanum lycopersicum var. cerasiforme', 'direct sunlight', 'vegetable soil', 1, 30, 7, 120, 'from late February to mid-March if you will be growing your crop in a greenhouse, or from late March to early April if they will be outside.'),
    ('Strawberry', 'Fragaria × ananassa', 'direct sunlight', 'vegetable soil with cow manure', 14, 30, 14, 120, 'from April to early May.'),
    ('Sweet pea','Pisum sativum','direct sunlight','soil', 2,30, 5,60,'from October and April. For best results aim for late October/November or late February/March as temperatures and light levels are less than ideal in midwinter.'),
    ('Early Carrot','Daucus  carota','moderate sunlight', 'soil', 1,240, 30 ,100,'from February or March under cloches or covered with fleece. The main outdoor sowing season is from April to early July.'),
    ('Chili','Capsicum annuum','direct sunlight', 'soil', 1,20,3,186, 'all year around,but needs extra light in the winter.');



    INSERT INTO PLANT (PLANT_NAME, CREATED, SPECIES_ID, ADMIN_ID) VALUES
        ('Ronak', '2023-04-03', 2, 1),
        ('Robin', '2023-03-20', 5, 1),
        ('Ulf', '2023-03-10', 5, 1),
        ('Avi', '2023-03-02',5, 1),
        ('Eira', '2023-04-03', 4, 1),
        ('Fredrik', '2023-03-20', 1, 1),
        ('Lucas', '2023-03-29', 1, 1),
        ('Kalle', '2023-03-02',1, 1),
        ('Björn', '2023-04-03', 3, 1),
        ('Jonathan H', '2023-03-20', 3, 1),
        ('Nicolas', '2023-03-10', 4, 1),
        ('Johannes', '2023-03-02',2, 1),
        ('Johan', '2023-04-03', 2, 1),
        ('Mahsa', '2023-03-20', 1, 1),
        ('Joakim', '2023-03-10', 2, 1),
        ('Tony', '2023-03-02',1, 1),
        ('Markus', '2023-03-02',1, 1),
        ('David', '2023-04-03', 2, 1),
        ('Omid', '2023-03-20', 1, 1),
        ('Fatema', '2023-03-10', 2, 1),
        ('Andreas', '2023-03-02',5, 1),
        ('Oscar', '2023-04-03', 2, 1),
        ('Helena', '2023-03-20', 1, 1),
        ('Michelle', '2023-03-10', 3, 1),
        ('Sebastian', '2023-03-02',4, 1),
        ('Jonathan T', '2023-04-03', 2, 1),
        ('Jesper', '2023-03-20', 2, 1),
        ('Carin', '2023-03-10', 2, 1),
        ('Zirha', '2023-03-02',1, 1);


