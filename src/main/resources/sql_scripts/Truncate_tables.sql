SET @@foreign_key_checks = 0;

TRUNCATE TABLE Person;
TRUNCATE TABLE Phone;
TRUNCATE TABLE Email;
TRUNCATE TABLE Address;
TRUNCATE TABLE Ad;
TRUNCATE TABLE SuitableAd;
TRUNCATE TABLE Rubric;

SET @@foreign_key_checks = 1;