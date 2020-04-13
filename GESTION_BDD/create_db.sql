CREATE TABLE socialworker (
        dni      VARCHAR(30),
        name      VARCHAR(50),
        phone     VARCHAR(20),
        username     VARCHAR(20),
        passwd   VARCHAR (20),
        
        CONSTRAINT cp_socialworker PRIMARY KEY (dni),      -- clau primària
        CONSTRAINT calt_socialworker UNIQUE (username)    -- clau alternativa
);

CREATE TABLE person(
	name VARCHAR(50),
	secondname VARCHAR(50),
	dni VARCHAR(30),
	phone VARCHAR(20),
	dateofbirth DATE,
	postaddress VARCHAR(70),
	email 	VARCHAR(30),
	username VARCHAR(20),
	passwd VARCHAR(20),

        CONSTRAINT cp_person PRIMARY KEY (dni) ,     -- clau primària
        CONSTRAINT calt_person UNIQUE  (username)      -- clau primària

);

CREATE TABLE volunteer (
        dni 	VARCHAR(30),
	endDate		DATE,
        state   VARCHAR (1),

        
        
        CONSTRAINT cp_volunteer PRIMARY KEY (dni),      -- clau primària
 	CONSTRAINT ca_volunteer_per FOREIGN KEY (dni) REFERENCES person(dni) ON DELETE CASCADE ON UPDATE CASCADE -- clau aliena a volunteer

);

CREATE TABLE hobbies (
        dni      VARCHAR(30),
        hobbie      VARCHAR(50),

        CONSTRAINT cp_hobbies PRIMARY KEY (dni, hobbie),  -- CP
        CONSTRAINT ca_hobbies_vol FOREIGN KEY (dni) REFERENCES volunteer(dni) ON DELETE CASCADE ON UPDATE CASCADE -- clau aliena a volunteer
);






CREATE TABLE elderlypeople (
        dni      VARCHAR(30),
        justification      VARCHAR(50),
        dnisocialworker     VARCHAR(30) NULL,
        
        CONSTRAINT cp_elderlypeople PRIMARY KEY (dni),      -- clau primària
        CONSTRAINT ca_elderlypeople_per FOREIGN KEY (dni) REFERENCES person(dni) ON DELETE CASCADE ON UPDATE CASCADE, -- clau aliena a elderlypeople

        CONSTRAINT ca_elderlypeople_soc FOREIGN KEY (dnisocialworker) REFERENCES socialworker(dni) ON DELETE CASCADE ON UPDATE CASCADE -- clau aliena a socialworker
);



CREATE TABLE disponibility (
        dnivolunteer      VARCHAR(30),
        dnielderlypeople      VARCHAR(30),
        dayofweek     integer,
        initialtime     DATE,
        finaltime     DATE,
        open      boolean,
        
        CONSTRAINT cp_book PRIMARY KEY (dnivolunteer, dayofweek),  -- CP
        CONSTRAINT ca_book_vol FOREIGN KEY (dnivolunteer) REFERENCES volunteer(dni) ON DELETE CASCADE ON UPDATE CASCADE, -- clau aliena a volunteer
        CONSTRAINT ca_book_eld FOREIGN KEY (dnielderlypeople) REFERENCES elderlypeople(dni) ON DELETE CASCADE ON UPDATE CASCADE -- clau aliena a elderlypeople
);

CREATE TABLE company (
        cif      VARCHAR(30),
        name      VARCHAR(50),
        personalcontact      VARCHAR(50),
        phonecontact     VARCHAR(20),
        email     VARCHAR(40),
        postaddress     VARCHAR(70),
      	username VARCHAR(20),
	    passwd VARCHAR(20),
        
        CONSTRAINT cp_company PRIMARY KEY (cif),  -- CP
        CONSTRAINT calt_company UNIQUE (email) -- clau alternativa
   
);



CREATE TABLE contract (

        cifcompany      VARCHAR(30),
        service      INTEGER,
        initialtime     DATE,
        finaltime     DATE NULL,
        price           INTEGER,

        CONSTRAINT cp_contract PRIMARY KEY (cifcompany),  -- CP
        CONSTRAINT ca_contract_company FOREIGN KEY (cifcompany) REFERENCES company(cif) ON DELETE CASCADE ON UPDATE CASCADE, -- clau aliena a company
        CONSTRAINT serviceIntegrity CHECK (service>=0 AND service<=2)
);

CREATE TABLE invoice (
        codinvoice      VARCHAR(15),
        dnielderlypeople VARCHAR(30),
        price           INTEGER,
	    date		DATE,
        
        CONSTRAINT cp_invoice PRIMARY KEY (codinvoice),  -- CP
        CONSTRAINT ca_invoice_eld FOREIGN KEY (dnielderlypeople) REFERENCES elderlypeople(dni) ON DELETE CASCADE ON UPDATE CASCADE -- clau aliena a elderlypeople
);

CREATE TABLE request (
        codrequest      VARCHAR(15),
        state   VARCHAR (1),
        servicetype      INTEGER,
        requestdate     DATE,
        approveddate     DATE,
        rejected     BOOLEAN NULL,
        enddate     DATE,
        dnielderlypeople VARCHAR(30),
        cifcompany VARCHAR(30),

        CONSTRAINT cp_request PRIMARY KEY (codrequest),  -- CP
        CONSTRAINT ca_request_eld FOREIGN KEY (dnielderlypeople) REFERENCES elderlypeople(dni) ON DELETE CASCADE ON UPDATE CASCADE, -- clau aliena a elderlypeople
        CONSTRAINT ca_contract_cifcompany FOREIGN KEY (cifcompany) REFERENCES contract(cifcompany) ON DELETE CASCADE ON UPDATE CASCADE, -- clau aliena a company
        CONSTRAINT serviceIntegrity CHECK (servicetype>=0 AND servicetype<=2)
);

CREATE TABLE line (
        codinvoice      VARCHAR(15),
        codrequest VARCHAR(15),
	    concept VARCHAR(50),
        priceservice           INTEGER,
        
        CONSTRAINT cp_line PRIMARY KEY (codinvoice,codrequest),  -- CP
        CONSTRAINT ca_line_inv FOREIGN KEY (codinvoice) REFERENCES invoice(codinvoice) ON DELETE CASCADE ON UPDATE CASCADE, -- clau aliena a invoice
        CONSTRAINT ca_line_req FOREIGN KEY (codrequest) REFERENCES request(codrequest) ON DELETE CASCADE ON UPDATE CASCADE -- clau aliena a request
);

CREATE TABLE CASUSERS(
        username VARCHAR(20),
        passwd VARCHAR(20),
        rol VARCHAR(30),

        CONSTRAINT cp_cas_users PRIMARY KEY (username)  -- CP


);
