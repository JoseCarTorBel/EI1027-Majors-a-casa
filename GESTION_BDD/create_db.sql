CREATE TABLE socialworker (
        dni      VARCHAR(15),
        name      VARCHAR(50),
        phone     VARCHAR(20),
        username     VARCHAR(20),
        passwd   VARCHAR (20),
        
        CONSTRAINT cp_socialworker PRIMARY KEY (dni),      -- clau primària
        CONSTRAINT calt_socialworker UNIQUE (username)    -- clau alternativa
);

CREATE TABLE volunteer (
        dni      VARCHAR(15),
        name      VARCHAR(50),
        secondname      VARCHAR(50),
        phone     VARCHAR(20),
        dateofbrith     DATE,
	    endDate		DATE,
        postaddress     VARCHAR(50),
        state   VARCHAR (1),
        email     VARCHAR(30),
        username     VARCHAR(20),
        passwd   VARCHAR (20),
        
        
        CONSTRAINT cp_volunteer PRIMARY KEY (dni),      -- clau primària
        CONSTRAINT calt_volunteer1 UNIQUE (email), -- clau alternativa
        CONSTRAINT calt_volunteer2 UNIQUE (username) -- clau alternativa
);

CREATE TABLE hobbies (
        dni      VARCHAR(15),
        hobbie      VARCHAR(50),

        CONSTRAINT cp_hobbies PRIMARY KEY (dni, hobbie),  -- CP
        CONSTRAINT ca_hobbies_vol FOREIGN KEY (dni) REFERENCES volunteer(dni) ON DELETE RESTRICT ON UPDATE CASCADE -- clau aliena a volunteer
);

CREATE TABLE elderlypeople (
        dni      VARCHAR(15),
        name      VARCHAR(50),
        secondname      VARCHAR(50),
        email     VARCHAR(30),
	    phone 		VARCHAR(20),
        postaddress     VARCHAR(50),
        justification      VARCHAR(50),
        dateofbrith     DATE,
        dnisocialworker     VARCHAR(20),
        username     VARCHAR(20),
        passwd   VARCHAR (20),
        
        
        CONSTRAINT cp_elderlypeople PRIMARY KEY (dni),      -- clau primària
        CONSTRAINT calt_elderlypeople1 UNIQUE (email), -- clau alternativa
        CONSTRAINT calt_elderlypeople2 UNIQUE (username), -- clau alternativa
        CONSTRAINT ca_elderlypeople_soc FOREIGN KEY (dnisocialworker) REFERENCES socialworker(dni) ON DELETE RESTRICT ON UPDATE CASCADE -- clau aliena a socialworker
);



CREATE TABLE disponibility (
        dnivolunteer      VARCHAR(15),
        dnielderlypeople      VARCHAR(15),
        dayofweek     VARCHAR(1),
        initialtime     DATE,
        finaltime     DATE,
        open      boolean,
        
        CONSTRAINT cp_book PRIMARY KEY (dnivolunteer, dnielderlypeople),  -- CP
        CONSTRAINT ca_book_vol FOREIGN KEY (dnivolunteer) REFERENCES volunteer(dni) ON DELETE RESTRICT ON UPDATE CASCADE, -- clau aliena a volunteer
        CONSTRAINT ca_book_eld FOREIGN KEY (dnielderlypeople) REFERENCES elderlypeople(dni) ON DELETE RESTRICT ON UPDATE CASCADE -- clau aliena a elderlypeople
);

CREATE TABLE company (
        cif      VARCHAR(15),
        name      VARCHAR(50),
        personalcontact      VARCHAR(50),
        phonecontact     VARCHAR(20),
        email     VARCHAR(20),
        postaddress     VARCHAR(50),
        
        CONSTRAINT cp_company PRIMARY KEY (cif),  -- CP
        CONSTRAINT calt_company UNIQUE (email) -- clau alternativa
   
);

CREATE TABLE request (
        codrequest      VARCHAR(15),
        state   VARCHAR (1),
        servicetype      VARCHAR(50),
        requestdate     DATE,
        approveddate     DATE,
        rejected     BOOLEAN NULL,
        enddate     DATE,
        dnielderlypeople VARCHAR(15),
        
        CONSTRAINT cp_request PRIMARY KEY (codrequest),  -- CP
        CONSTRAINT ca_request_eld FOREIGN KEY (dnielderlypeople) REFERENCES elderlypeople(dni) ON DELETE RESTRICT ON UPDATE CASCADE -- clau aliena a elderlypeople
);

CREATE TABLE contract (
        codrequest      VARCHAR(15),
        cifcompany      VARCHAR(15),
        service      VARCHAR(50),
        initialtime     DATE,
        finaltime     DATE NULL,
        price           INTEGER,
        
        CONSTRAINT cp_contract PRIMARY KEY (codrequest, cifcompany),  -- CP
        CONSTRAINT ca_contract_request FOREIGN KEY (codrequest) REFERENCES request(codrequest) ON DELETE RESTRICT ON UPDATE CASCADE, -- clau aliena a request
        CONSTRAINT ca_contract_company FOREIGN KEY (cifcompany) REFERENCES company(cif) ON DELETE RESTRICT ON UPDATE CASCADE -- clau aliena a company
);

CREATE TABLE . (
        codinvoice      VARCHAR(15),
        dnielderlypeople VARCHAR(15),
        price           INTEGER,
	date		DATE,
        
        CONSTRAINT cp_invoice PRIMARY KEY (codinvoice),  -- CP
        CONSTRAINT ca_invoice_eld FOREIGN KEY (dnielderlypeople) REFERENCES elderlypeople(dni) ON DELETE RESTRICT ON UPDATE CASCADE -- clau aliena a elderlypeople
);

CREATE TABLE line (
        codinvoice      VARCHAR(15),
        codrequest VARCHAR(15),
	    concept VARCHAR(50),
        priceservice           INTEGER,
        
        CONSTRAINT cp_line PRIMARY KEY (codinvoice,codrequest),  -- CP
        CONSTRAINT ca_line_inv FOREIGN KEY (codinvoice) REFERENCES invoice(codinvoice) ON DELETE RESTRICT ON UPDATE CASCADE, -- clau aliena a invoice
        CONSTRAINT ca_line_req FOREIGN KEY (codrequest) REFERENCES request(codrequest) ON DELETE RESTRICT ON UPDATE CASCADE -- clau aliena a request
);

CREATE TABLE CASUSERS(
        username VARCHAR(20),
        passwd VARCHAR(20),
        rol VARCHAR(20),

        CONSTRAINT cp_cas_users PRIMARY KEY (username)  -- CP


);
