CREATE TABLE User (
	idUser int NOT NULL AUTO_INCREMENT,
	username VARCHAR(20) UNIQUE,
	password TEXT NOT NULL,
	email VARCHAR(50) UNIQUE,
	name VARCHAR(20),
	tel VARCHAR(9),
	img TEXT,
	token TEXT,
	PRIMARY KEY (idUser)
)