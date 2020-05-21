INSERT INTO socialworker VALUES ('20914826Y','SW_Joaquin Gonzalez','603548721', 'SW_JoaquinGonzalez', 'SW_JoaquinGonzalez');
INSERT INTO socialworker VALUES ('20917765R','SW_Pinto Ibañez','623517255', 'SW_PintoIbañez', 'SW_PintoIbañez');
INSERT INTO socialworker VALUES ('20919929E','SW_Carlos Rodriguez','766542432', 'SW_CarlosRodriguez', 'SW_CarlosRodriguez');
INSERT INTO socialworker VALUES ('20099920W','SW_Laura Torro','644333123', 'SW_LauraTorro', 'SW_LauraTorro');
INSERT INTO socialworker VALUES ('19029093W','SW_Marta Lacueva','666291873', 'SW_MartaLacueva', 'SW_MartaLacueva');
INSERT INTO socialworker VALUES ('87937929S','SW_Maria Rubio','6253532873', 'SW_MariaRubio', 'SW_MariaRubio');
INSERT INTO socialworker VALUES ('23928409E','SW_Pablo Gracia','632532874', 'SW_PabloGracia', 'SW_PabloGracia');
INSERT INTO socialworker VALUES ('12398819Q','SW_Laura Martinez','8763647282', 'SW_LauraMartinez', 'SW_LauraMartinez');


INSERT INTO person VALUES ('V_Pablo','Sanahuja Leira','20904567S','675678765','1999-02-09','Ronda Vinatea 23 1B, Castellon de la plana, Castellon','al375833@uji.es','v_pablo', 'v_pablo');
INSERT INTO person VALUES ('V_Juan','Gonzalez Lopez','20764726T','679564357','1998-01-09','Calle sagrada familia S/N , Valencia, Valencia','juanito@yahoo.es','v_juan', 'v_juan');
INSERT INTO person VALUES ('V_Jaime','Ibañez Torro','23457855W','692458891','2000-09-09','Avenida Vinatea, Alcora 1 PB, Castellon','jaimito22@yahoo.es','v_jaime', 'v_jaime');
INSERT INTO person VALUES ('V_Pepe','Reina Beltran','20998812E','622198744','1997-11-09','Plaza la paz numero8 piso2 puerta6, Castellon de la plana, Castellon','pepereina@uji.es','v_pepe', 'v_pepe');
INSERT INTO person VALUES ('V_Laura','Martinez Palotes','20912333Y','623317367','2000-11-09','Plaza la lola 9 1 P2, Alcora, Castellon','laurita@uji.es','v_laura', 'v_laura');
INSERT INTO person VALUES ('V_Marcos','Kinto Trujillo','20998877E','655442123','2010-10-16','Plaza Juan 40 6B, Valencia, Valencia','marquitosss@uji.es','v_marcos', 'v_marcos');
INSERT INTO person VALUES ('V_Lucia','De la cueva Mijatres','20112233Y','655223991','2001-11-20','Plaza Jaime I 2 17U, Vinaroz, Castellon','luciadelacu@uji.es','v_lucia', 'v_lucia');


INSERT INTO person VALUES ('EP_Jose Carlos','Torro Belda','20654491U','672945673','1950-02-04','Ronda Vinatea 56 1B, Ontinyete, Alicante','josecarlos@uji.es','EP_Jose_Carlos', 'EP_Jose_Carlos');
INSERT INTO person VALUES ('EP_Joaquin','Gonzalez Alvarez','20884391O','627367564','1940-01-03','Calle pepe 1 5B, Ontinyete, Alicante','joaquin@uji.es','EP_Joaquin', 'EP_Joaquin');
INSERT INTO person VALUES ('EP_Maria','Peris Alonso','20119392I','612737283','1935-06-01','Calle egipcia 4, Ontinyete, Alicante','maria23@uji.es','EP_Maria', 'EP_Maria');
INSERT INTO person VALUES ('EP_Marta','Lopez Marza','20119290R','675672934','1937-11-20','Avenida casalduch 1A Castellon de la plana, Castellon','marta@uji.es','EP_Marta', 'EP_Marta');
INSERT INTO person VALUES ('EP_Cristina','Sanahuja Leira','20917888Y','624378234','1953-10-26','Calle la calle S/N, Ontinyete, Alicante',null,'EP_Cristina', 'EP_Cristina');
INSERT INTO person VALUES ('EP_Juanlu','Perez Llanos ','34758384E','675432345','1953-10-26','Calle la calle 9 3p3, Valencia, Valencia',null,'EP_Juanlu', 'EP_Juanlu');
INSERT INTO person VALUES ('EP_Ibai','Etxebarrieta Ybarra ','34567654R','675432345','1953-10-26','Calle la calle 8 2A, Vila-Real, Castellon',null,'EP_Ibai', 'EP_Ibai');

INSERT INTO volunteer VALUES ('20904567S',null,'A');
INSERT INTO volunteer VALUES ('20764726T','2021-01-01','R');
INSERT INTO volunteer VALUES ('23457855W','2024-01-01','A');
INSERT INTO volunteer VALUES ('20998812E','2027-01-01','A');
INSERT INTO volunteer VALUES ('20912333Y','2021-01-01','A');
INSERT INTO volunteer VALUES ('20998877E',null,'P');
INSERT INTO volunteer VALUES ('20112233Y',null,'A');

INSERT INTO hobbies VALUES ('20904567S','Tocar la Trompeta');
INSERT INTO hobbies VALUES ('20904567S','Jugar al Golf');
INSERT INTO hobbies VALUES ('20904567S','Bailar');
INSERT INTO hobbies VALUES ('20904567S','Correr');
INSERT INTO hobbies VALUES ('20764726T','Nadar');
INSERT INTO hobbies VALUES ('20764726T','Pasear por la montaña');
INSERT INTO hobbies VALUES ('23457855W','Bailar');
INSERT INTO hobbies VALUES ('20998812E','Dormir');
INSERT INTO hobbies VALUES ('20912333Y','Ver la TV');

INSERT INTO elderlypeople VALUES ('20654491U','A','Tengo hambre','20914826Y');
INSERT INTO elderlypeople VALUES ('20884391O','A','Necesito ayuda',null);
INSERT INTO elderlypeople VALUES ('20119392I','A','No se cocinar',null);
INSERT INTO elderlypeople VALUES ('20119290R','A','Necesito que me limpien','19029093W');
INSERT INTO elderlypeople VALUES ('20917888Y','A','Tengo mucho dinero',null);
INSERT INTO elderlypeople VALUES ('34758384E','R','No me hace mucha falta',null);
INSERT INTO elderlypeople VALUES ('34567654R','P','Necesito compañía',null);

INSERT INTO disponibility VALUES ('20904567S','20654491U',1,CAST('9:00' AS TIME),'2020-01-01','2020-12-01','A');
INSERT INTO disponibility VALUES ('20904567S',null,2,CAST('7:00' AS TIME),'2020-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20904567S',null,3,CAST('3:00' AS TIME),'2020-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20904567S',null,5,CAST('5:00' AS TIME),'2020-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20764726T','20884391O',1,CAST('1:00' AS TIME),'2020-01-02','2020-12-01','A');
INSERT INTO disponibility VALUES ('23457855W','20884391O',3,CAST('10:00' AS TIME),'2020-01-02','2020-12-01','A');
INSERT INTO disponibility VALUES ('20904567S','20884391O',4,CAST('11:00' AS TIME),'2020-01-02','2020-12-01','R');
INSERT INTO disponibility VALUES ('20904567S','20884391O',6,CAST('11:00' AS TIME),'2020-01-02','2020-12-01','P');
INSERT INTO disponibility VALUES ('23457855W','20119392I',7,CAST('12:00' AS TIME),'2020-01-01','2020-12-01','A');
INSERT INTO disponibility VALUES ('20998812E','20119290R',4,CAST('11:00' AS TIME),'2020-01-01','2020-12-01','R');
INSERT INTO disponibility VALUES ('20998877E',null,4,CAST('9:00' AS TIME),'2020-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20112233Y',null,1,CAST('9:00' AS TIME),'2019-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20998877E',null,3,CAST('9:00' AS TIME),'2020-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20112233Y',null,2,CAST('9:00' AS TIME),'2019-01-01','2020-12-01','P');

INSERT INTO company VALUES ('G34672398','Catering Miravete','12345678G','Vicente Miravete','647352635','vmiravete@gmail.com','Calle Catalunya 3, Castelló de la Plana, Castellón',null,null,NOW());
INSERT INTO company VALUES ('T46372819','Dra González','45632178F','Sandra González','657489325','sandragonzalez@gmail.com','Calle Rafalafena 4, Castelló de la Plana, Castellon',null,null,NOW());
INSERT INTO company VALUES ('R36271856','Limpiezas Pedro','14253698A','Pedro Alonso','678543234','pedroalonso@gmail.com','Calle Mozart 1 1B, Castelló de la Plana, Castellon','C_Limiezas','C_Limiezas',NOW());
INSERT INTO company VALUES ('U68352416','Conchi Limpiezas','25361482A','Concepción García','687935268','conchigarcia@gmail.com','Calle de Arriba 89 P3, Castelló de la Plana, Castellon','C_Irco','C_Irco',NOW());
INSERT INTO company VALUES ('L84736273','Irco','689543765','María del Mar Álvarez','14578965A','maralvarez@irco.es','Gran Vía 2 P1, Castelló de la Plana, Castellon',null,null,NOW());
INSERT INTO company VALUES ('12234565G','Mico Ferrandiz','48602841J','Raul Micó','657930556','contratos@micoferrandiz.es','Poligono Altet, Ontinyent','mico','mico',NOW());


INSERT INTO contract VALUES ('020200101G34672398','G34672398',0,'2020-01-01','2021-01-01',130,'Dilluns;Dimarts;Dimecres;Dijous;Divendres',CAST('12:00' AS TIME), CAST('14:30' AS TIME));
INSERT INTO contract VALUES ('2020200101T46372819','T46372819',2,'2020-01-01','2021-01-01',20,'Dilluns; Divendres',CAST('9:00' AS TIME), CAST('13:00' AS TIME) );
INSERT INTO contract VALUES ('020200101L84736273','L84736273',0,'2020-01-01','2021-01-01',130,'Dissabte;Diumenge',CAST('12:00' AS TIME), CAST('15:00' AS TIME));
INSERT INTO contract VALUES ('120200101R36271856','R36271856',1,'2020-01-01','2021-01-01',10, 'Dilluns; Dimecres; Divendres',CAST('9:00' AS TIME), CAST('13:00' AS TIME));
INSERT INTO contract VALUES ('120200101U68352416','U68352416',1,'2020-01-01','2021-01-01',10, 'Dimarts; Dijous',CAST('7:30' AS TIME), CAST('15:30' AS TIME));



INSERT INTO invoice VALUES ('F001','20654491U',130,'2010-02-02');
INSERT INTO invoice VALUES ('F002','20884391O',20,'2010-02-02');
INSERT INTO invoice VALUES ('F003','20119392I',130,'2010-02-02');
INSERT INTO invoice VALUES ('F004','20119290R',10,'2010-02-02');
INSERT INTO invoice VALUES ('F005','20917888Y',10,'2010-02-02');


INSERT INTO request VALUES ('R002','P',0,'2020-01-01',null,false,'2022-01-01',CAST('9:00' AS TIME),130,'20654491U','020200101G34672398');
INSERT INTO request VALUES ('R003','A',2,'2020-03-01','2020-04-01',false,'2022-01-01',CAST('9:00' AS TIME),20,'20884391O','2020200101T46372819');
INSERT INTO request VALUES ('R004','A',0,'2020-02-14','2020-04-01',false,'2022-01-01',CAST('9:00' AS TIME),130,'20119392I','020200101L84736273');
INSERT INTO request VALUES ('R005','R',1,'2020-01-12','2020-04-01',true,'2022-01-01',CAST('9:00' AS TIME),10,'20119290R','120200101R36271856');
INSERT INTO request VALUES ('R006','P',1,'2020-02-04',null,false,'2022-01-01',CAST('9:00' AS TIME),10,'20917888Y','120200101U68352416');
INSERT INTO request VALUES ('R007','P',0,'2020-03-01',null,false,'2022-01-01',CAST('14:00' AS TIME),130,'20884391O','020200101G34672398');
INSERT INTO request VALUES ('R008','A',1,'2020-03-01','2020-04-01',true,'2020-04-10',CAST('10:00' AS TIME),130,'20884391O','120200101U68352416');




INSERT INTO line VALUES ('F001','R002','Catering',130);
INSERT INTO line VALUES ('F002','R003','Atención Médica',20);
INSERT INTO line VALUES ('F003','R004','Catering',130);
INSERT INTO line VALUES ('F004','R005','Limpieza',10);
INSERT INTO line VALUES ('F005','R006','Limpieza',10);

INSERT INTO CASUSERS VALUES ('casManager','casManager','ResponsableContratacion');
INSERT INTO CASUSERS VALUES ('casCommitee','casCommitee','Comite');
INSERT INTO CASUSERS VALUES ('casVolunteer','casVolunteer','SupervisorVolunatris');
