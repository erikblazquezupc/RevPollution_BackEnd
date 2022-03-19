CREATE TABLE Concentration (
	idStation int,
	nameParticle VARCHAR(20),
	instant DATETIME,
	PRIMARY KEY (idStation, nameParticle, instant),
	idStation FOREIGN KEY REFERENCES Station(idStation),
	FOREIGN KEY nameParticle REFERENCES Particle(name)
)