INSERT INTO socialworker VALUES ('20914826Y','SW_Joaquin Gonzalez','603548721', 'SW_JoaquinGonzalez', 'SW_JoaquinGonzalez');
INSERT INTO socialworker VALUES ('20917765R','SW_Pinto Ibañez','623517255', 'SW_PintoIbañez', 'SW_PintoIbañez');
INSERT INTO socialworker VALUES ('20919929E','SW_Carlos Rodriguez','766542432', 'SW_CarlosRodriguez', 'SW_CarlosRodriguez');
INSERT INTO socialworker VALUES ('20099920W','SW_Laura Torro','644333123', 'SW_LauraTorro', 'SW_LauraTorro');
INSERT INTO socialworker VALUES ('19029093W','SW_Marta Lacueva','666291873', 'SW_MartaLacueva', 'SW_MartaLacueva');

INSERT INTO person VALUES ('V_Pablo','Sanahuja Leira','20904567S','675678765','1999-02-09','Ronda Vinatea, Castellon de la plana, Castellon','al375833@uji.es','v_pablo', 'v_pablo');
INSERT INTO person VALUES ('V_Juan','Gonzalez Lopez','20764726T','679564357','1998-01-09','Calle sagrada familia, Valencia, Valencia','juanito@yahoo.es','v_juan', 'v_juan');
INSERT INTO person VALUES ('V_Jaime','Ibañez Torro','23457855W','692458891','2000-09-09','Avenida Vinatea, Alcora, Castellon','jaimito22@yahoo.es','v_jaime', 'v_jaime');
INSERT INTO person VALUES ('V_Pepe','Reina Beltran','20998812E','622198744','1997-11-09','Plaza la paz, Castellon de la plana, Castellon','pepereina@uji.es','v_pepe', 'v_pepe');
INSERT INTO person VALUES ('V_Laura','Martinez Palotes','20912333Y','623317367','2000-11-09','Plaza la lola, Alcora, Castellon','laurita@uji.es','v_laura', 'v_laura');
INSERT INTO person VALUES ('V_Marcos','Kinto Trujillo','20998877E','655442123','2010-10-16','Plaza Juan, Valencia, Valencia','marquitosss@uji.es','v_marcos', 'v_marcos');
INSERT INTO person VALUES ('V_Lucia','De la cueva Mijatres','20112233Y','655223991','2001-11-20','Plaza Jaime I, Vinaroz, Castellon','luciadelacu@uji.es','v_lucia', 'v_lucia');


INSERT INTO person VALUES ('EP_Jose Carlos','Torro Belda','20654491U','672945673','1950-02-04','Ronda Vinatea, Ontinyete, Alicante','josecarlos@uji.es','EP_Jose_Carlos', 'EP_Jose_Carlos');
INSERT INTO person VALUES ('EP_Joaquin','Gonzalez Alvarez','20884391O','627367564','1940-01-03','Calle pepe, Ontinyete, Alicante','joaquin@uji.es','EP_Joaquin', 'EP_Joaquin');
INSERT INTO person VALUES ('EP_Maria','Peris Alonso','20119392I','612737283','1935-06-01','Calle egipcia, Ontinyete, Alicante','maria23@uji.es','EP_Maria', 'EP_Maria');
INSERT INTO person VALUES ('EP_Marta','Lopez Marza','20119290R','675672934','1937-11-20','Avenida casalduch, Castellon de la plana, Castellon','marta@uji.es','EP_Marta', 'EP_Marta');
INSERT INTO person VALUES ('EP_Cristina','Sanahuja Leira','20917888Y','624378234','1953-10-26','Calle la calle, Ontinyete, Alicante',null,'EP_Cristina', 'EP_Cristina');

INSERT INTO volunteer VALUES ('20904567S','2023-01-01','A');
INSERT INTO volunteer VALUES ('20764726T','2021-01-01','R');
INSERT INTO volunteer VALUES ('23457855W','2024-01-01','A');
INSERT INTO volunteer VALUES ('20998812E','2027-01-01','P');
INSERT INTO volunteer VALUES ('20912333Y','2021-01-01','A');
INSERT INTO volunteer VALUES ('20998877E',null,'P');
INSERT INTO volunteer VALUES ('20112233Y',null,'A');

INSERT INTO hobbies VALUES ('20904567S','Tocar la Trompeta');
INSERT INTO hobbies VALUES ('20904567S','Correr');
INSERT INTO hobbies VALUES ('20764726T','Nadar');
INSERT INTO hobbies VALUES ('20764726T','Pasear por la montaña');
INSERT INTO hobbies VALUES ('23457855W','Bailar');
INSERT INTO hobbies VALUES ('20998812E','Dormir');
INSERT INTO hobbies VALUES ('20912333Y','Ver la TV');

INSERT INTO elderlypeople VALUES ('20654491U','Tengo hambre','20914826Y');
INSERT INTO elderlypeople VALUES ('20884391O','Necesito ayuda',null);
INSERT INTO elderlypeople VALUES ('20119392I','No se cocinar',null);
INSERT INTO elderlypeople VALUES ('20119290R','Necesito que me limpien','19029093W');
INSERT INTO elderlypeople VALUES ('20917888Y','Tengo mucho dinero',null);

INSERT INTO disponibility VALUES ('20904567S','20654491U',1,'2020-01-01','2020-12-01','A');
INSERT INTO disponibility VALUES ('20904567S',null,2,'2020-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20904567S',null,3,'2020-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20904567S',null,5,'2020-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20764726T','20884391O',1,'2020-01-02','2020-12-01','A');
INSERT INTO disponibility VALUES ('23457855W','20884391O',3,'2020-01-02','2020-12-01','A');
INSERT INTO disponibility VALUES ('20904567S','20884391O',4,'2020-01-02','2020-12-01','R');
INSERT INTO disponibility VALUES ('20904567S','20884391O',6,'2020-01-02','2020-12-01','P');
INSERT INTO disponibility VALUES ('23457855W','20119392I',3,'2020-01-01','2020-12-01','A');
INSERT INTO disponibility VALUES ('20998812E','20119290R',4,'2020-01-01','2020-12-01','R');
INSERT INTO disponibility VALUES ('20998877E',null,4,'2020-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20112233Y',null,1,'2019-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20998877E',null,3,'2020-01-01','2020-12-01','P');
INSERT INTO disponibility VALUES ('20112233Y',null,2,'2019-01-01','2020-12-01','P');

INSERT INTO company VALUES ('G34672398','Catering Miravete','12345678G','Vicente Miravete','647352635','vmiravete@gmail.com','Calle Catalunya, Castelló de la Plana, Castellón',null,null);
INSERT INTO company VALUES ('T46372819','Dra González','45632178F','Sandra González','657489325','sandragonzalez@gmail.com','Calle Rafalafena, Castelló de la Plana, Castellon',null,null);
INSERT INTO company VALUES ('R36271856','Limpiezas Pedro','Pedro Alonso','14253698A','678543234','pedroalonso@gmail.com','Calle Mozart, Castelló de la Plana, Castellon','C_Limiezas','C_Limiezas');
INSERT INTO company VALUES ('U68352416','Conchi Limpiezas','Concepción García','25361482A','687935268','conchigarcia@gmail.com','Calle de Arriba, Castelló de la Plana, Castellon','C_Irco','C_Irco');
INSERT INTO company VALUES ('L84736273','Irco','María del Mar Álvarez','14578965A','689543765','maralvarez@irco.es','Gran Vía, Castelló de la Plana, Castellon',null,null);


INSERT INTO contract VALUES ('G34672398',0,'2020-01-01','2021-01-01',130);
INSERT INTO contract VALUES ('T46372819',2,'2020-01-01','2021-01-01',20);
INSERT INTO contract VALUES ('L84736273',0,'2020-01-01','2021-01-01',130);
INSERT INTO contract VALUES ('R36271856',1,'2020-01-01','2021-01-01',10);
INSERT INTO contract VALUES ('U68352416',1,'2020-01-01','2021-01-01',10);




INSERT INTO invoice VALUES ('F001','20654491U',130,'2010-02-02');
INSERT INTO invoice VALUES ('F002','20884391O',20,'2010-02-02');
INSERT INTO invoice VALUES ('F003','20119392I',130,'2010-02-02');
INSERT INTO invoice VALUES ('F004','20119290R',10,'2010-02-02');
INSERT INTO invoice VALUES ('F005','20917888Y',10,'2010-02-02');


INSERT INTO request VALUES ('R002','P',0,'2020-01-01',null,false,'2022-01-01','20654491U','G34672398');
INSERT INTO request VALUES ('R003','A',2,'2020-03-01','2020-04-01',false,'2022-01-01','20884391O','T46372819');
INSERT INTO request VALUES ('R004','A',0,'2020-02-14','2020-04-01',false,'2022-01-01','20119392I','L84736273');
INSERT INTO request VALUES ('R005','R',1,'2020-01-12','2020-04-01',false,'2022-01-01','20119290R','R36271856');
INSERT INTO request VALUES ('R006','P',1,'2020-02-04',null,false,'2022-01-01','20917888Y','U68352416');



INSERT INTO line VALUES ('F001','R002','Catering',130);
INSERT INTO line VALUES ('F002','R003','Atención Médica',20);
INSERT INTO line VALUES ('F003','R004','Catering',130);
INSERT INTO line VALUES ('F004','R005','Limpieza',10);
INSERT INTO line VALUES ('F005','R006','Limpieza',10);

INSERT INTO CASUSERS VALUES ('casManager','casManager','ResponsableContratacion');
INSERT INTO CASUSERS VALUES ('casCommitee','casCommitee','Comite');
INSERT INTO CASUSERS VALUES ('casVolunteer','casVolunteer','SupervisorVolunatris');
