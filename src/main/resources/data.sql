DELETE FROM event;
DELETE FROM pet;
DELETE FRMO owner;

INSERT INTO `owner` VALUES (1,'Jens','Andersen','Mimersgade 143'),(2,'Arne','Hansen','Strandvejen 12'),(3,'Hanne','Jensen','Oluf Palmes Allé 98 1. th');
INSERT INTO `pet` VALUES (1,'Fiddo','2015-02-01','Dog',NULL,1),(2,'Hannibal','2013-05-10','Dog',NULL,1),(3,'Elvis','2010-08-08','Cat',NULL,3),(4,'Sam','2012-01-05','Rabbit','2015-07-07',2);
INSERT INTO `event` VALUES (1,4,'Death','Died of age','2015-07-07'),(2,2,'Broken bone','Ribbs broken','2015-02-08'),(3,3,'Medicine','Antibiotics for wound on back leg','2015-03-10');
