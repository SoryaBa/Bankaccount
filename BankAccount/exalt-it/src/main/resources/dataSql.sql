CREATE DATABASE  IF NOT EXISTS db_banque;
USE db_banque;

CREATE TABLE IF NOT EXISTS client(
							id INT PRIMARY KEY,
							nom VARCHAR(50),
							prenom VARCHAR(50),
							email VARCHAR (300) UNIQUE,
							numero_voie VARCHAR(50),
							nom_voie VARCHAR(500),
							ville VARCHAR(100),
							codepostal VARCHAR (50),
							pays VARCHAR(100)							
							) ENGINE=INNODB DEFAULT CHARSET=utf8;
							
CREATE TABLE IF NOT EXISTS compte(
							id INT PRIMARY KEY,
							description VARCHAR(200),
							libelle VARCHAR(100),
							solde DOUBLE
							)ENGINE=INNODB DEFAULT CHARSET=utf8;
							
CREATE TABLE IF NOT EXISTS linked_cpt_client(
							id_client INT NOT NULL,
							id_compte INT NOT NULL,	
							PRIMARY KEY(id_client, id_compte),
							FOREIGN KEY (id_client) REFERENCES client (id) ON UPDATE RESTRICT ON DELETE CASCADE,
							FOREIGN KEY (id_compte) REFERENCES compte (id) ON UPDATE RESTRICT ON DELETE CASCADE
							)ENGINE=INNODB DEFAULT CHARSET=utf8;
							
CREATE TABLE IF NOT EXISTS  operation(
							id INT PRIMARY KEY,
							id_client INT NOT NULL,
							id_compte INT NOT NULL,
							datecreation TIMESTAMP NOT NULL,
							debut DOUBLE,
							credit DOUBLE,
							message VARCHAR(300),							
							FOREIGN KEY (id_client) REFERENCES client (id) ON UPDATE RESTRICT ON DELETE CASCADE,
							FOREIGN KEY (id_compte) REFERENCES compte (id) ON UPDATE RESTRICT ON DELETE CASCADE
							)ENGINE=INNODB DEFAULT CHARSET=utf8;	