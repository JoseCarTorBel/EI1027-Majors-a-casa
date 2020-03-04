

INSERT INTO socialworker VALUES ('20914826Y','SW_Joaquin Gonzalez','603548721', 'al375824', 'patata');

INSERT INTO volunteer VALUES ('20904567S','V_Pablo','Sanahuja Leira','675678765','1999-05-09',NULL,'AvenidaMilFlores#Alcora#Castellon','N','al375833@uji.es', 'al375833', 'michaeljackson');

INSERT INTO hobbies VALUES ('20904567S','Tocar la Trompeta');

INSERT INTO elderlypeople VALUES ('20987655T','EP_Jose Carlos','Torro Belda','al375822@uji.es','RondaLaGicgueña#Ontinyete#Alicante','Necesito alguien que me alimente','1950-09-01','20914826Y', 'al375822', 'vivaelpouclar');

INSERT INTO book VALUES ('20904567S','20987655T','1','2020-01-01','2020-12-01',true);

INSERT INTO company VALUES ('A2091','EmpresasJoaquin','Joaquin','603548721','al375824@uji.es','Rnd Mgd#Castellon de la plana#Castellon');

INSERT INTO request VALUES ('R001','O','Dar de comer','2020-01-01','2020-12-01',false,'2022-01-01','20987655T');

INSERT INTO contract VALUES ('R001','A2091','Dar de comer','2020-01-01','2021-01-01',23);

INSERT INTO invoice VALUES ('F001','20987655T',240,'2010-02-02');

INSERT INTO line VALUES ('F001','R001','Dar de comer al iaio',23);

