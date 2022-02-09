

INSERT INTO pathology (id, id_group, name_pathology, name_group) VALUES
(16,4,'Médecine d''urgence','Médecine d''urgence'),
(28,5,'Médecin généraliste','Médecin généraliste'),
(41,5,'Médecine respiratoire','Médecine respiratoire'),
(45,7,'Cardiologie pédiatrique','Cardiologie pédiatrique'),
(70,12,'Chirurgie cardiothoracique','Chirurgie cardiothoracique'),
(71,12,'Chirurgie générale','Chirurgie générale'),
(72,12,'Neurochirurgie','Neurochirurgie'),
(75,12,'Chirurgie pédiatrique','Chirurgie pédiatrique'),
(76,12,'Chirurgie plastique','Chirurgie plastique'),
(77,12,'Traumatologie et chirurgie orthopédique','Traumatologie et chirurgie orthopédique'),
(79,12,'Chirurgie vasculaire','Chirurgie vasculaire');

INSERT INTO hospital (id,id_zone, name, address, latitude, longitude,  is_available_bed) VALUES
(1,24, 'CH CHATEAUDUN','Route de Jallans 28200 CHATEAUDUN',48.0743644,1.3582561,true),
(2,24, 'CH VENDOME - MONTOIRE','96 rue Poterie 41100 VENDOME',47.7941772,1.0630365,true),
(3,24, 'CH BLOIS SIMONE VEIL','Mail Pierre Charlot 41000 BLOIS',47.6018423,1.3435976,true),
(4,24, 'CH ROMORANTIN-LANTHENAY','96 Rue des Capucins 41200 ROMORANTIN LANTHENAY',47.357574,1.733981,true),
(5,24, 'CHRU CLOCHEVILLE - TOURS','49 Bd Bélanger 37000 TOURS',47.389633,0.6803135,true),
(6,24, 'CHRU TROUSSEAU - CHAMBRAY','AV DE LA REPUBLIQUE 37170 CHAMBRAY LES TOURS',47.3499547,0.7115988,true),
(7,24, 'PÔLE SANTÉ LÉONARD DE VINCI','1 Av Alexandre Minkowski 37170 CHAMBRAY LES TOURS',47.3341618,0.6879063,true),
(8,24, 'CH PAUL MARTINAIS - LOCHES','1 Rue du Dr Paul Martinais 37600 LOCHES',47.1295054,1.0017053,true),
(9,24, 'CHR ORLÉANS - HÔPITAL DE LA SOURCE','14 Av. de l''Hôpital 45100 ORLEANS',47.8353634,1.9201458,true),
(10,24,'ORELIANCE - REINE BLANCHE','555 Av. Jacqueline Auriol 45770 SARAN',47.9401154,1.8797335,true),
(11,24,'CHU BRETONNEAU - TOURS','2, boulevard tonnellé 37000 Tours',47.3914052,0.6681828,true),
(12,24,'CH AGGLOMERATION MONTARGOISE','658 Rue des Bourgoins 45200 AMILLY',47.9972668,2.7729858,true),
(13,52,'CH Le Mans',' 194 Av. Rubillard, 72000 Le Mans',48.013743,0.1762351,false);


INSERT INTO hospital_pathology (id, id_hospital, id_pathology, available_beds) VALUES
(1,1,16,10),
(2,2,16,9),
(3,3,16,10),
(4,3,70,9),
(5,3,72,10),
(6,3,75,10),
(7,3,77,10),
(8,4,16,10),
(9,4,75,10),
(10,4,77,10),
(11,5,16,10),
(12,6,16,10),
(13,6,70,10),
(14,6,72,10),
(15,6,77,10),
(16,7,16,10),
(17,7,77,10),
(18,8,16,10),
(19,9,16,10),
(20,9,70,10),
(21,9,72,10),
(22,9,75,10),
(23,9,77,10),
(24,10,16,10),
(25,10,77,10),
(26,11,16,10),
(27,11,70,10),
(28,11,72,10),
(29,11,77,10),
(30,12,16,10),
(31,5,75,10),
(32,3,41,0),
(33,13,41,0);


INSERT INTO emergency_log (id, id_zone, id_responder, id_patient, patient_last_name, 
patient_first_name, patient_gender,patient_age, patient_address,patient_latitude, patient_longitude,
id_pathology ,id_hospital ,hospital_name, hospital_address,hospital_latitude ,hospital_longitude ,
instructions,id_hospital_service,hospital_service_name, distance) 
VALUES
(1,11,1,1,'Morteau','Magalie','F',40,'20 rue du Progrès 41360 Lunay',47.8106061, 0.9128109,
70,1,'CH CHATEAUDUN','Route de Jallans 28205 CHATEAUDUN CEDEX',48.0743644,1.3582561,
'blblabla jkjjkj ','1','Cardio',100);

