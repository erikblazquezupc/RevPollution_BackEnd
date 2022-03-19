CREATE TABLE Concentration (
	idStation int,
	nameParticle VARCHAR(20),
	instant DATETIME,
	PRIMARY KEY (idStation, nameParticle, instant),
	FOREIGN KEY (idStation) REFERENCES Station(idStation),
	FOREIGN KEY (nameParticle) REFERENCES Particle(name)
)
