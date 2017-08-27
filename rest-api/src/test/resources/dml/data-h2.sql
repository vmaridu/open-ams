INSERT INTO `user` VALUES ('admin','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'admin@openams.com','2015-04-10 16:27:25',NULL,NULL),('bioins','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'bioins@openams.com','2015-04-10 16:27:25',NULL,NULL),('brock.sosa','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'brock.sosa@openams.org','2015-04-10 16:27:25',NULL,NULL),('bruno.noble','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'bruno.noble@openams.org','2015-04-10 16:27:25',NULL,NULL),('chmins','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'chmins@openams.com','2015-04-10 16:27:25',NULL,NULL),('curran.roach','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'curran.roach@openams.org','2015-04-10 16:27:25',NULL,NULL),('ethan.flores','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'ethan.flores@openams.org','2015-04-10 16:27:25',NULL,NULL),('evan.patrick','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'evan.patrick@openams.org','2015-04-10 16:27:25',NULL,NULL),('gannon.pierce','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'gannon.pierce@openams.org','2015-04-10 16:27:25',NULL,NULL),('ivor.dejesus','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'ivor.dejesus@openams.org','2015-04-10 16:27:25',NULL,NULL),('jamal.guzman','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'jamal.guzman@openams.org','2015-04-10 16:27:25',NULL,NULL),('matins','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'matins@openams.com','2015-04-10 16:27:25',NULL,NULL),('nathan.ochoa','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'nathan.ochoa@openams.org','2015-04-10 16:27:25',NULL,NULL),('oscar.wilkinson','$2a$10$PaxeIIwmmk8O7eGVrUTwS.ggUndq3jZ4gJ6tB4wLQL4KrGNyJhRaq',NULL,1,'oscar.wilkinson@openams.org','2015-04-10 16:27:25',NULL,NULL);


INSERT INTO `role` VALUES ('1de070ee-0afb-471e-8c56-bd0f948e580a','STUDENT'),('5a7b781f-2988-49a0-8edb-183cfdb75022','STAFF'),('f3da7697-5e77-42e2-8c56-30db84793073','ADMIN');


INSERT INTO `user_in_role` VALUES ('brock.sosa','1de070ee-0afb-471e-8c56-bd0f948e580a'),('bruno.noble','1de070ee-0afb-471e-8c56-bd0f948e580a'),('curran.roach','1de070ee-0afb-471e-8c56-bd0f948e580a'),('ethan.flores','1de070ee-0afb-471e-8c56-bd0f948e580a'),('evan.patrick','1de070ee-0afb-471e-8c56-bd0f948e580a'),('gannon.pierce','1de070ee-0afb-471e-8c56-bd0f948e580a'),('ivor.dejesus','1de070ee-0afb-471e-8c56-bd0f948e580a'),('jamal.guzman','1de070ee-0afb-471e-8c56-bd0f948e580a'),('nathan.ochoa','1de070ee-0afb-471e-8c56-bd0f948e580a'),('oscar.wilkinson','1de070ee-0afb-471e-8c56-bd0f948e580a'),('bioins','5a7b781f-2988-49a0-8edb-183cfdb75022'),('chmins','5a7b781f-2988-49a0-8edb-183cfdb75022'),('matins','5a7b781f-2988-49a0-8edb-183cfdb75022'),('admin','f3da7697-5e77-42e2-8c56-30db84793073');


INSERT INTO `contact` VALUES ('04439135-980c-4fef-b1b7-361b02d0c4d7','gannon.pierce','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('05833b18-0212-4019-baa1-b23de2e70077','brock.sosa','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('06dcf0c1-4cd1-423e-9fff-a8179f79278f','oscar.wilkinson','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('16a8f75c-f3ba-4b9c-ba6f-757262cce25f','ivor.dejesus','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('24204da9-83eb-4f22-a67b-1efe03ed8cd3','bruno.noble','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('2bbd0efe-92e5-4111-9fa3-746b73cf868f','curran.roach','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('4dbb060c-847b-4bf1-a427-a14258cf8cea','Mac Appleseed','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('6219f46b-6129-4471-a4d5-e5d5868c33a7','nathan.ochoa','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('8860d809-85b3-455c-9eba-716864eba2e3','William Scott','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('8bb90124-86db-457e-9d03-e1c0be34d456','evan.patrick','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('b6da7e22-cdb9-4ad4-bbfe-b0aa2d74c422','Venkata Maridu','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('bc4fc197-3cb1-4578-89de-624fd2bf4a62','ethan.flores','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('bd9616db-a7e6-4caf-bef2-a5ddda8e3e4a','jamal.guzman','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes'),('c32fed32-e6cd-481b-bbf1-64657c400b2a','Tom Hanks','000-000-0000','000-000-0000','test_email@test.com','Test Avenue','Test Line 2','1','Test Street','TestCity','TestState','Test Country',0,'test notes');


INSERT INTO `person` VALUES ('4a0ba7b9-d6f3-463d-86bd-5ce2b9de31a2','bioins','Mac',NULL,'Appleseed','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'4dbb060c-847b-4bf1-a427-a14258cf8cea','4dbb060c-847b-4bf1-a427-a14258cf8cea',NULL),('4b5e8a49-7c9e-470d-94b1-02c02fd71d61','bruno.noble','Bruno',NULL,'Noble','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'24204da9-83eb-4f22-a67b-1efe03ed8cd3','24204da9-83eb-4f22-a67b-1efe03ed8cd3',NULL),('50347a98-e100-4f1c-8b66-6d05b1965288','chmins','Tom',NULL,'Hanks','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,'c32fed32-e6cd-481b-bbf1-64657c400b2a','8860d809-85b3-455c-9eba-716864eba2e3',NULL),('5316b9b4-9e8b-4fb6-9550-081d0d1532f0','brock.sosa','Brock',NULL,'Sosa','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'05833b18-0212-4019-baa1-b23de2e70077','05833b18-0212-4019-baa1-b23de2e70077',NULL),('653e0ed8-d74f-464e-8502-cb51a9aa81f0','evan.patrick','Evan',NULL,'Patrick','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'8bb90124-86db-457e-9d03-e1c0be34d456','8bb90124-86db-457e-9d03-e1c0be34d456',NULL),('6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','matins','William',NULL,'Scott','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'8860d809-85b3-455c-9eba-716864eba2e3','b6da7e22-cdb9-4ad4-bbfe-b0aa2d74c422',NULL),('851150b0-14bf-4383-b174-375dc885000d','admin','Venkata',NULL,'Maridu','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'b6da7e22-cdb9-4ad4-bbfe-b0aa2d74c422','b6da7e22-cdb9-4ad4-bbfe-b0aa2d74c422',NULL),('8994f295-aec2-4d78-916b-5fb1ee2dfde5','oscar.wilkinson','Oscar',NULL,'Wilkinson','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'06dcf0c1-4cd1-423e-9fff-a8179f79278f','06dcf0c1-4cd1-423e-9fff-a8179f79278f',NULL),('8b13d8d7-08b5-496e-8285-df20df73bf45','gannon.pierce','Gannon',NULL,'Pierce','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'04439135-980c-4fef-b1b7-361b02d0c4d7','04439135-980c-4fef-b1b7-361b02d0c4d7',NULL),('9d208436-ff6c-4470-b1e5-85e0edf5117d','nathan.ochoa','Nathan',NULL,'Ochoa','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'6219f46b-6129-4471-a4d5-e5d5868c33a7','6219f46b-6129-4471-a4d5-e5d5868c33a7',NULL),('9ef13b0e-3b04-48f0-937d-fe772e2eaf67','curran.roach','Curran',NULL,'Roach','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'2bbd0efe-92e5-4111-9fa3-746b73cf868f','2bbd0efe-92e5-4111-9fa3-746b73cf868f',NULL),('abba7c7a-2795-4fe6-a263-002d9e727776','jamal.guzman','Jamal',NULL,'Guzman','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'bd9616db-a7e6-4caf-bef2-a5ddda8e3e4a','bd9616db-a7e6-4caf-bef2-a5ddda8e3e4a',NULL),('ce5d0eee-bd57-4262-b100-15ed324c9c70','ethan.flores','Ethan',NULL,'Fores','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'bc4fc197-3cb1-4578-89de-624fd2bf4a62','bc4fc197-3cb1-4578-89de-624fd2bf4a62',NULL),('ee2271dc-4579-4cab-83df-ebbf23a8b9d7','ivor.dejesus','Ivor',NULL,'Dejesus','Mr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'c32fed32-e6cd-481b-bbf1-64657c400b2a','16a8f75c-f3ba-4b9c-ba6f-757262cce25f',NULL);


INSERT INTO `staff` VALUES ('4a0ba7b9-d6f3-463d-86bd-5ce2b9de31a2','BIO_STF_1','Teacher',NULL),('50347a98-e100-4f1c-8b66-6d05b1965288','CHM_STF_1','Teacher',NULL),('6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','MAT_STF_1','Teacher',NULL),('851150b0-14bf-4383-b174-375dc885000d','ADMIN_1','Admin',NULL);


INSERT INTO `student` VALUES ('4b5e8a49-7c9e-470d-94b1-02c02fd71d61','17_K10_A_001',NULL,'K10'),('5316b9b4-9e8b-4fb6-9550-081d0d1532f0','17_K10_A_008',NULL,'K10'),('653e0ed8-d74f-464e-8502-cb51a9aa81f0','17_K10_A_004',NULL,'K10'),('8994f295-aec2-4d78-916b-5fb1ee2dfde5','17_K11_A_002',NULL,'K11'),('8b13d8d7-08b5-496e-8285-df20df73bf45','17_K10_A_005',NULL,'K10'),('9d208436-ff6c-4470-b1e5-85e0edf5117d','17_K11_A_001',NULL,'K11'),('9ef13b0e-3b04-48f0-937d-fe772e2eaf67','17_K10_A_002',NULL,'K10'),('abba7c7a-2795-4fe6-a263-002d9e727776','17_K10_A_007',NULL,'K10'),('ce5d0eee-bd57-4262-b100-15ed324c9c70','17_K10_A_003',NULL,'K10'),('ee2271dc-4579-4cab-83df-ebbf23a8b9d7','17_K10_A_006',NULL,'K10');


INSERT INTO `course` VALUES ('0b735b88-1b26-4b7b-8f5c-7f3939ecfb2a','K11 MATH','MATH','K11 Mathematics',NULL,'2015-04-10 16:28:25'),('0f3d2b66-f9d3-4d74-9948-a34ec706cb5f','K10 MATH','MATH','K10 Mathematics',NULL,'2015-04-10 16:27:25'),('20f4152a-0725-4c0d-8d3b-b568a347c30b','K10 CHEM','CHEM','K10 Chemistry',NULL,'2015-04-10 16:29:25'),('91a17ac6-29ae-49aa-86c3-680bf77e2130','K10 BIO','BIO','K10 Biology',NULL,'2015-04-10 16:30:25');


INSERT INTO `course_schedule` VALUES ('0f638ef4-6e6f-4c13-a6dd-8b213ab40efe','20f4152a-0725-4c0d-8d3b-b568a347c30b','50347a98-e100-4f1c-8b66-6d05b1965288','K10 CHEM MORN',1,'2017','2017-03-20','2018-02-12','10:00:00','12:00:00','ROOM 103','K10 Chemistry Morning Class','2017-03-20 11:00:00'),('47d41bdf-e433-4ee8-8d0b-0c0b4f529c69','91a17ac6-29ae-49aa-86c3-680bf77e2130','4a0ba7b9-d6f3-463d-86bd-5ce2b9de31a2','K10 BIO EVEN',1,'2017','2017-03-20','2018-02-12','16:00:00','18:00:00','ROOM 102','K10 Biology Evening Class','2017-03-20 10:00:00'),('73605391-2c06-4f13-9ee5-ce29d42055b0','0f3d2b66-f9d3-4d74-9948-a34ec706cb5f','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','K10 MATH MORN',1,'2017','2017-03-20','2018-02-12','10:00:00','12:00:00','ROOM 102','K10 Math Morning Class','2017-03-20 13:00:00'),('7f249c50-9ec1-44df-84d2-c117c196516b','91a17ac6-29ae-49aa-86c3-680bf77e2130','4a0ba7b9-d6f3-463d-86bd-5ce2b9de31a2','K10 BIO MORN',1,'2017','2017-03-20','2018-02-12','10:00:00','12:00:00','ROOM 102','K10 Biology Morning Class','2017-03-20 09:00:00'),('a730db46-26c2-4a70-8150-375a7934caa4','0b735b88-1b26-4b7b-8f5c-7f3939ecfb2a','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','K11 MATH MORN',1,'2017','2017-03-20','2018-02-12','10:00:00','12:00:00','ROOM 103','K11 Math Morning Class','2017-03-20 12:00:00');


INSERT INTO `student_course_enrollment` VALUES ('00517f38-ffab-4acb-b947-f1ee0d052fbb','a730db46-26c2-4a70-8150-375a7934caa4','8994f295-aec2-4d78-916b-5fb1ee2dfde5','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('0901cc4c-248d-473e-89dc-2afb495c29e4','47d41bdf-e433-4ee8-8d0b-0c0b4f529c69','4b5e8a49-7c9e-470d-94b1-02c02fd71d61','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('3335a06a-4081-4f24-8e5d-c8923d69ff6d','73605391-2c06-4f13-9ee5-ce29d42055b0','ee2271dc-4579-4cab-83df-ebbf23a8b9d7','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('3405af61-8543-4344-93af-edbf3080b7f9','0f638ef4-6e6f-4c13-a6dd-8b213ab40efe','9ef13b0e-3b04-48f0-937d-fe772e2eaf67','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('636f9a8e-91b2-492b-bc91-03cd81b24fc4','7f249c50-9ec1-44df-84d2-c117c196516b','abba7c7a-2795-4fe6-a263-002d9e727776','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('64b00ac8-3282-463f-83c6-9b00dba89bce','0f638ef4-6e6f-4c13-a6dd-8b213ab40efe','4b5e8a49-7c9e-470d-94b1-02c02fd71d61','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('8b9dfcfc-6c62-46ab-9408-a21b2cb8f128','73605391-2c06-4f13-9ee5-ce29d42055b0','abba7c7a-2795-4fe6-a263-002d9e727776','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('a85eabe6-2ce2-44e5-b7cb-189a735e6d1f','73605391-2c06-4f13-9ee5-ce29d42055b0','653e0ed8-d74f-464e-8502-cb51a9aa81f0','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('b2441c24-5632-445d-9153-15654dc73b43','a730db46-26c2-4a70-8150-375a7934caa4','9d208436-ff6c-4470-b1e5-85e0edf5117d','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('b3b11956-e6a4-4aaf-91d6-9afcdb528bbe','73605391-2c06-4f13-9ee5-ce29d42055b0','8b13d8d7-08b5-496e-8285-df20df73bf45','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('b7031929-8392-4d21-b544-442a81cfa29d','47d41bdf-e433-4ee8-8d0b-0c0b4f529c69','ce5d0eee-bd57-4262-b100-15ed324c9c70','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('b779a835-1a38-495b-99fa-809edb7a6d65','47d41bdf-e433-4ee8-8d0b-0c0b4f529c69','5316b9b4-9e8b-4fb6-9550-081d0d1532f0','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('d23ac922-0cf6-4d8f-b431-cfaf3f2e1389','7f249c50-9ec1-44df-84d2-c117c196516b','ee2271dc-4579-4cab-83df-ebbf23a8b9d7','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('d30763d1-a2d3-4fac-aed6-ad35d7e8e787','0f638ef4-6e6f-4c13-a6dd-8b213ab40efe','ce5d0eee-bd57-4262-b100-15ed324c9c70','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('da9630ea-b246-434f-9433-6e02d17ec146','7f249c50-9ec1-44df-84d2-c117c196516b','653e0ed8-d74f-464e-8502-cb51a9aa81f0','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('eb4b2eeb-0881-4bc4-9267-72e02c46a995','47d41bdf-e433-4ee8-8d0b-0c0b4f529c69','9ef13b0e-3b04-48f0-937d-fe772e2eaf67','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('f33e49be-ef65-422f-a705-ef58a4dfef6a','7f249c50-9ec1-44df-84d2-c117c196516b','8b13d8d7-08b5-496e-8285-df20df73bf45','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25'),('faea9d70-f42e-48c4-ae19-2f30b878ace8','0f638ef4-6e6f-4c13-a6dd-8b213ab40efe','5316b9b4-9e8b-4fb6-9550-081d0d1532f0','2015-04-10 16:29:25',1,NULL,NULL,'2017-03-22 16:29:25');


INSERT INTO `attendance_by` VALUES ('40011a5c-9e2f-476c-b004-5a765c8609d4','2015-07-17 10:00:00','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','a730db46-26c2-4a70-8150-375a7934caa4',NULL);
INSERT INTO `attendance_by` VALUES ('40011a5c-9e2f-476c-b004-5a765c8609e5','2015-07-18 10:00:00','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','a730db46-26c2-4a70-8150-375a7934caa4',NULL);
INSERT INTO `attendance_by` VALUES ('40011a5c-9e2f-476c-b004-5a765c8609g6','2015-07-19 10:00:00','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','a730db46-26c2-4a70-8150-375a7934caa4',NULL);
INSERT INTO `attendance_by` VALUES ('40011a5c-9e2f-476c-b004-5a765c8609h8','2015-07-20 10:00:00','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','a730db46-26c2-4a70-8150-375a7934caa4',NULL);
INSERT INTO `attendance_by` VALUES ('40011a5c-9e2f-476c-b004-5a765c8609i9','2015-07-21 10:00:00','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','a730db46-26c2-4a70-8150-375a7934caa4',NULL);

INSERT INTO `attendance_by` VALUES ('40011a5c-9e2f-476c-b004-5a765c8609k1','2015-07-17 10:00:00','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','7f249c50-9ec1-44df-84d2-c117c196516b',NULL);
INSERT INTO `attendance_by` VALUES ('40011a5c-9e2f-476c-b004-5a765c8609k2','2015-07-18 10:00:00','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','7f249c50-9ec1-44df-84d2-c117c196516b',NULL);
INSERT INTO `attendance_by` VALUES ('40011a5c-9e2f-476c-b004-5a765c8609k3','2015-07-19 10:00:00','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','7f249c50-9ec1-44df-84d2-c117c196516b',NULL);
INSERT INTO `attendance_by` VALUES ('40011a5c-9e2f-476c-b004-5a765c8609k4','2015-07-20 10:00:00','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','7f249c50-9ec1-44df-84d2-c117c196516b',NULL);
INSERT INTO `attendance_by` VALUES ('40011a5c-9e2f-476c-b004-5a765c8609k5','2015-07-21 10:00:00','6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c','7f249c50-9ec1-44df-84d2-c117c196516b',NULL);


INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696110','00517f38-ffab-4acb-b947-f1ee0d052fbb',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609d4');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696111','b2441c24-5632-445d-9153-15654dc73b43','SICK LEAVE',2,'40011a5c-9e2f-476c-b004-5a765c8609d4');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696112','00517f38-ffab-4acb-b947-f1ee0d052fbb','ABSENT',1,'40011a5c-9e2f-476c-b004-5a765c8609e5');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696113','b2441c24-5632-445d-9153-15654dc73b43',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609e5');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696114','00517f38-ffab-4acb-b947-f1ee0d052fbb',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609g6');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696115','b2441c24-5632-445d-9153-15654dc73b43',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609g6');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696116','00517f38-ffab-4acb-b947-f1ee0d052fbb','ABSENT',1,'40011a5c-9e2f-476c-b004-5a765c8609h8');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696117','b2441c24-5632-445d-9153-15654dc73b43',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609h8');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696118','00517f38-ffab-4acb-b947-f1ee0d052fbb','ON LEAVE',2,'40011a5c-9e2f-476c-b004-5a765c8609i9');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696119','b2441c24-5632-445d-9153-15654dc73b43',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609i9');

INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696131','636f9a8e-91b2-492b-bc91-03cd81b24fc4',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k1');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696132','d23ac922-0cf6-4d8f-b431-cfaf3f2e1389',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k1');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696133','da9630ea-b246-434f-9433-6e02d17ec146',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k1');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696134','f33e49be-ef65-422f-a705-ef58a4dfef6a',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k1');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696141','636f9a8e-91b2-492b-bc91-03cd81b24fc4',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k2');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696142','d23ac922-0cf6-4d8f-b431-cfaf3f2e1389',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k2');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696143','da9630ea-b246-434f-9433-6e02d17ec146',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k2');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696144','f33e49be-ef65-422f-a705-ef58a4dfef6a',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k2');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696151','636f9a8e-91b2-492b-bc91-03cd81b24fc4',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k3');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696152','d23ac922-0cf6-4d8f-b431-cfaf3f2e1389',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k3');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696153','da9630ea-b246-434f-9433-6e02d17ec146',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k3');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696154','f33e49be-ef65-422f-a705-ef58a4dfef6a',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k3');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696161','636f9a8e-91b2-492b-bc91-03cd81b24fc4',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k4');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696162','d23ac922-0cf6-4d8f-b431-cfaf3f2e1389',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k4');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696163','da9630ea-b246-434f-9433-6e02d17ec146',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k4');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696164','f33e49be-ef65-422f-a705-ef58a4dfef6a',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k4');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696171','636f9a8e-91b2-492b-bc91-03cd81b24fc4',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k5');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696172','d23ac922-0cf6-4d8f-b431-cfaf3f2e1389',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k5');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696173','da9630ea-b246-434f-9433-6e02d17ec146',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k5');
INSERT INTO `attendance` VALUES ('e66d394f-cb35-4d6a-9531-b4f0cd696174','f33e49be-ef65-422f-a705-ef58a4dfef6a',NULL,0,'40011a5c-9e2f-476c-b004-5a765c8609k5');


INSERT INTO `fee` VALUES ('1f346a3e-ae95-4927-9e96-c5f7a016ad0e','Oscar Wilkinson QUART FEE','8994f295-aec2-4d78-916b-5fb1ee2dfde5','2017',3000.45,NULL,'2017-02-02 00:00:00'),('3bf0e5ca-26ba-42c2-97cd-698337db62e1','Nathan Ochoa QUART FEE','9d208436-ff6c-4470-b1e5-85e0edf5117d','2017',3000.45,NULL,'2017-02-02 00:00:00');


INSERT INTO `payment` VALUES ('40c8860b-4647-4845-80fc-f47b3b45139b','Oscar Wilkinson QUART Payment','1f346a3e-ae95-4927-9e96-c5f7a016ad0e',3000.45,NULL,'2017-02-02 00:00:00'),('0f13a6f6-ce3f-4999-8ce3-072f03493e9c','Nathan Ochoa QUART Payment','3bf0e5ca-26ba-42c2-97cd-698337db62e1',2000.15,NULL,'2017-02-02 00:00:00');


INSERT INTO `test` VALUES ('60c3c5e9-a0ed-4538-a50c-d28bb05e1c23','K11 Math Unit Test','0b735b88-1b26-4b7b-8f5c-7f3939ecfb2a','UNIT','2017-03-21 11:00:00','2017-03-21 13:00:00','50',NULL,NULL,'2017-03-24 11:00:00');


INSERT INTO `test_score` VALUES ('2911a461-9899-4105-af6b-d0f6aef341f3','60c3c5e9-a0ed-4538-a50c-d28bb05e1c23','9d208436-ff6c-4470-b1e5-85e0edf5117d',NULL,NULL,'B',36,NULL,'2017-03-24 11:00:00'),('af592d13-72a5-4588-8ae9-763dc9af2437','60c3c5e9-a0ed-4538-a50c-d28bb05e1c23','8994f295-aec2-4d78-916b-5fb1ee2dfde5',NULL,NULL,'A',49,NULL,'2017-03-24 11:00:00');


INSERT INTO `school_schedule` VALUES ('75969fed-185e-4112-bfc3-e8164fb49ed7','Summer Holidays',NULL,'2017-06-02 00:00:00','2017-07-02 00:00:00','851150b0-14bf-4383-b174-375dc885000d','2017-02-02 00:00:00');
