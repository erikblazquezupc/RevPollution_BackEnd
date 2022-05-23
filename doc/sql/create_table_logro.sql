CREATE TABLE Logro (
	id int auto_increment,
	name VARCHAR(20),
	tier ENUM('bronce', 'plata', 'oro'),
	cond TEXT,
	min_value int,
	statistic VARCHAR(30),
	activated BOOL,
	PRIMARY KEY (id),
	UNIQUE (name, tier)
)