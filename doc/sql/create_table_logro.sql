CREATE TABLE Logro (
	nameLogro VARCHAR(20),
	tier ENUM('bronce', 'plata', 'oro'),
	condition VARCHAR(20),
	PRIMARY KEY (nameLogro, tier)
)