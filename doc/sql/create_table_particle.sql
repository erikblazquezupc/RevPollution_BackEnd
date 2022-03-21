CREATE TABLE Particle (
	name VARCHAR(20),
	unit ENUM('um/m3', 'mg/m3', 'ng/m3', 'ppm'),
	PRIMARY KEY (name)
)