


INSERT INTO socialworker VALUES ('20914826Y','SW_Joaquin Gonzalez','603548721', 'al375824', 'patata');


INSERT INTO person VALUES ('V_Pablo','Sanahuja Leira','20904567S','675678765','1999-05-09','AvenidaMilFlores, Alcora, Castellon','al375833@uji.es','al375833', 'michaeljackson');

INSERT INTO volunteer VALUES ('20904567S','2023-01-01','N');

INSERT INTO hobbies VALUES ('20904567S','Tocar la Trompeta');


INSERT INTO person VALUES ('EP_Jose Carlos','Torro Belda','20987655T','60897676','1950-09-01','RondaLaGicgueña, Ontinyete, Alicante','al375822@uji.es','al375822', 'michaeljackson2');


INSERT INTO elderlypeople VALUES ('20987655T','Tengo hambre','20914826Y');

INSERT INTO disponibility VALUES ('20904567S','20987655T','1','2020-01-01','2020-12-01',true);

INSERT INTO company VALUES ('A2091','EmpresasJoaquin','Joaquin','603548721','al375824@uji.es','Rnd Mgd#Castellon de la plana#Castellon');

INSERT INTO request VALUES ('R001','O',0,'2020-01-01','2020-12-01',false,'2022-01-01','20987655T');

INSERT INTO contract VALUES ('R001','A2091',0,'2020-01-01','2021-01-01',23);

INSERT INTO invoice VALUES ('F001','20987655T',240,'2010-02-02');

INSERT INTO line VALUES ('F001','R001','Dar de comer al iaio',23);


