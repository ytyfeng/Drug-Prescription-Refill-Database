CREATE TABLE Staff (
licenseNo VARCHAR(20) NOT NULL,
name VARCHAR(30) NOT NULL,
address VARCHAR(50) NOT NULL,
PRIMARY KEY (licenseNo));

CREATE TABLE Customer (
taxNo VARCHAR(12) NOT NULL,
name VARCHAR(30) NOT NULL,
address VARCHAR(50) NOT NULL,
DOB DATE NOT NULL,
telephone VARCHAR(16) NOT NULL,
licenseNo VARCHAR(20) NOT NULL,
PRIMARY KEY (taxNo),
FOREIGN KEY (licenseNo) REFERENCES Staff);

CREATE TABLE Compound (
brandName VARCHAR(20) NOT NULL,
diagnosticID VARCHAR(20) NOT NULL,
form VARCHAR(20) NOT NULL,
sizeAmount VARCHAR(20) NOT NULL,
strength VARCHAR(20) NOT NULL,
primaryIngredient VARCHAR(40) NOT NULL,
secondaryIngredient VARCHAR(40) NOT NULL,
manufacturerNo VARCHAR(10) NOT NULL,
instructions VARCHAR(240) NOT NULL,
routeofAdministration VARCHAR(50) NOT NULL,
genericSubstitute CHAR(1) NOT NULL CHECK (genericSubstitute in ('Y', 'N')),
PRIMARY KEY (brandName),
FOREIGN KEY (manufacturerNo) REFERENCES Manufacturer); 

ALTER TABLE Compound
 ADD FOREIGN KEY (manufacturerNo) REFERENCES Manufacturer;

ALTER TABLE Customer 
Add FOREIGN KEY (prescriptionNo) REFERENCES Prescription;


CREATE TABLE Manufacturer (
manufacturerNo VARCHAR(10) NOT NULL,
name VARCHAR(20) NOT NULL,
address VARCHAR(50) NOT NULL,
brandName VARCHAR(20) NOT NULL,
PRIMARY KEY (manufacturerNo),
FOREIGN KEY (brandName) REFERENCES Compound);

CREATE TABLE Prescription (
prescriptionNo VARCHAR(24) NOT NULL,
dateFiled DATE NOT NULL,
quantity VARCHAR(40) NOT NULL,
instructions VARCHAR(240) NOT NULL,
routeofAdministration VARCHAR(50) NOT NULL,
genericOrWritten CHAR(1) NOT NULL CHECK (genericOrWritten in ('Y', 'N')),
brandName VARCHAR(20) NOT NULL,
diagnosticID VARCHAR(20) NOT NULL,
licenseNO VARCHAR(20) NOT NULL,
taxNo VARCHAR(12) NOT NULL,
PRIMARY KEY (prescriptionNo),
FOREIGN KEY (brandName) REFERENCES Compound
ON DELETE CASCADE,
FOREIGN KEY (licenseNo) REFERENCES Staff
ON DELETE CASCADE,
FOREIGN KEY (taxNo) REFERENCES Customer
ON DELETE CASCADE);

CREATE TABLE Distributor (
distributorNo VARCHAR(10) NOT NULL,
name VARCHAR(20) NOT NULL,
address VARCHAR(50) NOT NULL,
PRIMARY KEY (distributorNo));

CREATE TABLE Seller (
distributorNo VARCHAR(10) NOT NULL,
brandName VARCHAR(20) NOT NULL,
PRIMARY KEY (distributorNo, brandName),
FOREIGN KEY (distributorNo) REFERENCES Distributor,
FOREIGN KEY (brandName) REFERENCES Compound);

CREATE TABLE Refill (
refillNum INTEGER NOT NULL,
prescriptionNo VARCHAR(24) NOT NULL,
refillDate DATE NOT NULL,
price INTEGER NOT NULL,
PRIMARY KEY (prescriptionNo),
FOREIGN KEY (prescriptionNo) REFERENCES Prescription,
CONSTRAINT refillNum CHECK (refillNum >= 1));

