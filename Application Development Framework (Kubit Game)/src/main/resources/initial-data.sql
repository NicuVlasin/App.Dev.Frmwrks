INSERT INTO player VALUES (1,'paul', '$2a$10$oSFRUXcRbw3OU7.YfUWp.OtqmLW95o4xPdg9sPFymr45aML8QSuda', 1000);
INSERT INTO player VALUES (2,'nicu', '$2a$10$oSFRUXcRbw3OU7.YfUWp.OtqmLW95o4xPdg9sPFymr45aML8QSuda', 1000);

INSERT INTO equipment VALUES
(1,'Mace', 3,0,1,15,'melee'),
(2,'Mace', 5,2,2,50,'melee'),
(3,'Mace', 12,4,3,180,'melee'),
(4,'Mace', 20,6,4,300,'melee'),
(5,'Crossbow', 5,0,1,15,'range'),
(6,'Crossbow', 15,0,2,80,'range'),
(7,'Crossbow', 20,0,3,120,'range'),
(8,'Crossbow', 25,0,4,350,'range'),
(9,'Armor', 0, 10,1,15,'armour'),
(10,'Armor',0, 20,2,50,'armour'),
(11,'Armor',0, 30,3,150,'armour'),
(12,'Armor',0, 40,4,200,'armour'),
(13,'Shield',0,12,1 ,15, 'shield'),
(14,'Shield',0,24,2 ,30, 'shield'),
(15,'Shield',0,36,3 ,50, 'shield'),
(16,'Shield',0,80,4 ,120, 'shield');

INSERT INTO player_current_items VALUES
(1,1,1,5,13,null),
(2,2,1,5,13,null)

-- INSERT INTO new_equipment VALUES (1, 'Blade', 7, 150),(2, 'Axe', 6, 200),(3, 'Shotgun', 20, 500),(4, 'Assalt Rifle', 15, 1000),(5, 'Sword of flame', 100, 80);
