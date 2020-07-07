  create table if not exists Rubric(
      rubric_id INT AUTO_INCREMENT PRIMARY KEY,
      name      VARCHAR(50),
      version   INT(10)
  );

  ALTER TABLE Rubric AUTO_INCREMENT = 1;

  create table if not exists Person(
                         person_id int AUTO_INCREMENT primary key,
                         name Varchar(25) NOT NULL,
                         password Varchar(25) NOT NULL,
                         version INT,
                         address_fk_id INT
  );

  ALTER TABLE Person AUTO_INCREMENT = 1;

  create table if not exists Address(
                          address_id int AUTO_INCREMENT primary key,
                          city Varchar(25) NOT NULL,
                          street Varchar(25) NOT NULL,
                          version INT,
                          house_number int NOT NULL
   );

  ALTER TABLE Address AUTO_INCREMENT = 1;

  create table if not exists Email(
                        email_id int unsigned not NULL AUTO_INCREMENT primary key,
                        email Varchar(25) NOT NULL UNIQUE,
                        version INT,
                        person_fk_id INT
  );

  ALTER TABLE Email AUTO_INCREMENT = 1;

  create table if not exists Phone(
                        phone_id int AUTO_INCREMENT primary key,
                        phone_number int UNIQUE,
                        version INT,
                        person_fk_id INT
  );

  ALTER TABLE Phone AUTO_INCREMENT = 1;

  create table if not exists Ad(
                     ad_id int primary key AUTO_INCREMENT,
                     title Varchar(25) NOT NULL,
                     date Date NOT NULL,
                     text Varchar(25) NOT NULL,
                     price DECIMAL NOT NULL,
                     active BOOLEAN,
                     version INT,
                     rubric_fk_id INT,
                     person_fk_id INT
  );

  ALTER TABLE Ad AUTO_INCREMENT = 1;

  create table if not exists SuitableAd(
                                   suitable_id int primary key AUTO_INCREMENT,
                                   title Varchar(25) NOT NULL,
                                   priceFrom DECIMAL,
                                   priceTo DECIMAL,
                                   version INT,
                                   rubric_fk_id INT,
                                   person_fk_id INT
  );

  ALTER TABLE SuitableAd AUTO_INCREMENT = 1;



  ALTER TABLE Person
      ADD FOREIGN KEY (address_fk_id) REFERENCES Address(address_id);

  ALTER TABLE Ad
      ADD FOREIGN KEY (rubric_fk_id) REFERENCES Rubric(rubric_id);

  ALTER TABLE Ad
     ADD FOREIGN KEY (person_fk_id) REFERENCES Person(person_id);

  ALTER TABLE Email
      ADD FOREIGN KEY (person_fk_id) REFERENCES Person(person_id);

  ALTER TABLE Phone
      ADD FOREIGN KEY (person_fk_id) REFERENCES Person(person_id);

  ALTER TABLE SuitableAd
      ADD FOREIGN KEY (person_fk_id) REFERENCES Person(person_id);

  ALTER TABLE SuitableAd
      ADD FOREIGN KEY (rubric_fk_id) REFERENCES Rubric(rubric_id);