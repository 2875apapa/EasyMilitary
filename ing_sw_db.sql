USE database_ing_sw;

CREATE TABLE Grado (
	id INT NOT NULL,
    nome VARCHAR(30),
    PRIMARY KEY (id))
ENGINE = 'InnoDB';

INSERT INTO grado VALUES (0, "Soldato");
INSERT INTO grado VALUES (1, "Caporale");
INSERT INTO grado VALUES (2, "Caporal Maggiore");
INSERT INTO grado VALUES (3, "1° Caporal Maggiore");
INSERT INTO grado VALUES (4, "Caporal Maggiore Scelto");
INSERT INTO grado VALUES (5, "Caporal Maggiore Capo");
INSERT INTO grado VALUES (6, "Caporal Magiore Capo Scelto");
INSERT INTO grado VALUES (7, "Sergente");
INSERT INTO grado VALUES (8, "Sergente Maggiore");
INSERT INTO grado VALUES (9, "Sergente Maggiore Capo");
INSERT INTO grado VALUES (10, "Maresciallo");
INSERT INTO grado VALUES (11, "Maresciallo Ordinario");
INSERT INTO grado VALUES (12, "Maresciallo Capo");
INSERT INTO grado VALUES (13, "Primo Maresciallo");
INSERT INTO grado VALUES (14, "Luogotenente");
INSERT INTO grado VALUES (15, "Sottotenente");
INSERT INTO grado VALUES (16, "Tenente");
INSERT INTO grado VALUES (17, "Capitano");
INSERT INTO grado VALUES (18, "Maggiore");
INSERT INTO grado VALUES (19, "Tenente Colonello");
INSERT INTO grado VALUES (20, "Colonello");

CREATE TABLE Militare (
	matricola VARCHAR(12) UNIQUE,
    cognome VARCHAR(25),
    nome VARCHAR(20),
    data_nascita DATE,
    luogo_nascita VARCHAR (20),
    grado INT,
    ab_armi BOOL,
    inuse BOOL,
    INDEX grado_idx (grado),
    PRIMARY KEY (matricola),
    FOREIGN KEY (grado) REFERENCES Grado (id))
ENGINE = 'InnoDB';

CREATE TABLE Assenze (
	id INT NOT NULL AUTO_INCREMENT,
    tipo VARCHAR (10),
    data_inizio DATE,
    data_fine DATE,
    militare VARCHAR(12),
    INDEX militare_idx (militare),
    PRIMARY KEY (id),
    FOREIGN KEY (militare) REFERENCES Militare (matricola))
ENGINE = 'InnoDB';

CREATE TABLE Patenti (
	cod_patente BIGINT NOT NULL AUTO_INCREMENT,
    militare VARCHAR(12),
    tipo_patente INT,
    data_conseguimento DATE,
    data_scadenza DATE,
	INDEX militare_idx (militare),
    PRIMARY KEY (cod_patente),
    FOREIGN KEY (militare) REFERENCES Militare (matricola))
ENGINE = 'InnoDB';

CREATE TABLE TipoServizio (
	id INT NOT NULL AUTO_INCREMENT,
    nome_servizio VARCHAR(20),
    armato BOOL,
    durata INT,
    riposo INT,
    num_militari INT,
    num_mezzi INT,
    PRIMARY KEY (id))
ENGINE = 'InnoDB';

CREATE TABLE Giorni (
	id INT NOT NULL AUTO_INCREMENT,
    numero INT,
    ora_inizio TIME,
    ora_fine TIME,
    tipo_servizio INT NOT NULL,
	INDEX tiposervizio_idx (tipo_servizio),
    PRIMARY KEY (id),
    FOREIGN KEY (tipo_servizio) REFERENCES TipoServizio (id))
ENGINE = 'InnoDB';

CREATE TABLE Mezzo
(
	targa VARCHAR(9) NOT NULL UNIQUE,
    marca VARCHAR(20),
    modello VARCHAR(20),
	tipo BOOL,
    patente INT,
    stato BOOL,
    inuse BOOL,
    PRIMARY KEY (targa))
ENGINE = 'InnoDB';

CREATE TABLE Ruoli (
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR (20),
    grado INT, 
    tipo_servizio INT NOT NULL,
    guida BOOL,
	INDEX tiposervizio_idx (tipo_servizio),
    INDEX grado_idx (grado),
    PRIMARY KEY (id),
    FOREIGN KEY (tipo_servizio) REFERENCES TipoServizio (id),
    FOREIGN KEY (grado) REFERENCES Grado (id))
ENGINE = 'InnoDB';

CREATE TABLE Servizio (
	id BIGINT NOT NULL AUTO_INCREMENT,
    tipo_servizio INT NOT NULL,
    data_inizio DATE,
    data_fine DATE,
    INDEX tiposervizio_idx (tipo_servizio),
    PRIMARY KEY (id),
    FOREIGN KEY (tipo_servizio) REFERENCES TipoServizio (id))
ENGINE = 'InnoDB';

CREATE TABLE Comandata (
	cod BIGINT NOT NULL AUTO_INCREMENT,
    tipo BOOLEAN,
    dinizio DATE,
    tinizio TIME,
    dfine DATE,
    tfine TIME,
    destinazione VARCHAR(35),
    mezzo VARCHAR(9) NOT NULL UNIQUE,
    critik_mezzo BOOL,
    num_militari INT,
    INDEX mezzo_idx (mezzo),
    PRIMARY KEY (cod),
    FOREIGN KEY (mezzo) REFERENCES Mezzo (targa))
ENGINE = 'InnoDB';

CREATE TABLE Ass_mil (
	id BIGINT NOT NULL AUTO_INCREMENT,
    servizio BIGINT NOT NULL,
    ruolo VARCHAR(20),
    militare VARCHAR(12),
    critik BOOL,
    INDEX servizio_idx (servizio),
    INDEX militare_idx (militare),
    PRIMARY KEY (id),
    FOREIGN KEY (servizio) REFERENCES Servizio (id),
    FOREIGN KEY (militare) REFERENCES Militare (matricola))
ENGINE='InnoDB';

CREATE TABLE Ass_mezzo (
	id BIGINT NOT NULL AUTO_INCREMENT,
    servizio BIGINT NOT NULL,
    mezzo VARCHAR(9),
    critik BOOL,
    INDEX servizio_idx (servizio),
    INDEX mezzo_idx (mezzo),
    PRIMARY KEY (id),
    FOREIGN KEY (servizio) REFERENCES Servizio (id),
    FOREIGN KEY (mezzo) REFERENCES Mezzo (targa))
ENGINE='InnoDB';

CREATE TABLE Ass_com (
	id BIGINT NOT NULL AUTO_INCREMENT,
    comandata BIGINT NOT NULL,
    militare VARCHAR(12),
    critik BOOL,
    INDEX comandata_idx (comandata),
    INDEX militare_idx (militare),
    PRIMARY KEY (id),
    FOREIGN KEY (comandata) REFERENCES Comandata (cod),
    FOREIGN KEY (militare) REFERENCES Militare (matricola))
ENGINE='InnoDB';