CREATE TABLE UserLogros (
    id int auto_increment,
    idUser int NOT NULL,
	nameLogro VARCHAR(20) NOT NULL,
	tier ENUM('bronce', 'plata', 'oro'),
    points int,

	PRIMARY KEY (id),
    FOREIGN KEY (idUser) REFERENCES User(idUser),
    FOREIGN KEY (nameLogro, tier) REFERENCES Logro(name, tier)
)