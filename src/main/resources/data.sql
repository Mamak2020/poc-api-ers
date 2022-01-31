

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
(1,11, 'CH CHATEAUDUN','Route de Jallans 28205 CHATEAUDUN CEDEX',48.0743644,1.3582561,true);


INSERT INTO emergency_log (id, id_zone, id_responder, id_patient, patient_last_name, 
patient_first_name, patient_gender,patient_age, patient_address,patient_latitude, patient_longitude,
id_pathology ,id_hospital ,hospital_name, hospital_address,hospital_latitude ,hospital_longitude ,
instructions,id_hospital_service,hospital_service_name, distance) 
VALUES
(1,11,1,1,'Morteau','Magalie','F',40,'111 La Fosse 41360 Lunay',47.833, 0.781,
70,1,'CH CHATEAUDUN','Route de Jallans 28205 CHATEAUDUN CEDEX',48.0743644,1.3582561,
'blblabla jkjjkj ','1','Cardio',100);

INSERT INTO hospital_pathology (id, id_hospital, id_pathology, available_beds) VALUES
(1,1,70, 10);