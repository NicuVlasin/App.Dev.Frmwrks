CREATE TABLE IF NOT EXISTS player (
  id int(11) NOT NULL AUTO_INCREMENT,
  fullName varchar(100) NOT NULL,
  password varchar(255) NOT NULL,
  balance int(11),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS player_current_items (
  id int NOT NULL AUTO_INCREMENT,
  player_id int NOT NULL,
  melee int,
  range_w int,
  shield int,
  armour int,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS equipment(
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  damage int(3) NOT NULL,
  protection int(3) NOT NULL,
  level int(3) NOT NULL,
  price int(3) NOT NULL,
  type varchar(100) NOT NULL,
  PRIMARY KEY (id)
);
