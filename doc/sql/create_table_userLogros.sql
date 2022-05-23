CREATE TABLE UserLogros (
    idUser int,
	nameLogro VARCHAR(20),
	tier ENUM('bronce', 'plata', 'oro'),
    points int,

	PRIMARY KEY (idUser, nameLogro, tier),
    FOREIGN KEY (idUser) REFERENCES User(idUser),
    FOREIGN KEY (nameLogro, tier) REFERENCES Logro(name, tier)
)