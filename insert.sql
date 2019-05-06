INSERT INTO Customer
VALUES ('12345678','Ron Burgundy','478 Brandywine Lane, Miami, FL','11-FEB-1976','7482947592','S0002');
INSERT INTO Customer
VALUES ('12344929','Ron Nguyen','300 Brandywine Lane, Miami, FL','15-MAR-1999','7482489534','S0002');
INSERT INTO Customer
VALUES ('12322454','Ron Alberto','903 Brandywine Lane, Miami, FL','13-APR-1990','7482328949','S0003');

INSERT INTO Prescription
VALUES ('P0001','02-APR-2019','3 doses','Taken as instructed','oral','Y','BRD1-10%','D000001','S0002','12345678');
INSERT INTO Prescription
VALUES ('P0002','05-FEB-2018','2 doses','Taken as instructed','oral','Y','BRD1-10%','D000001','S0002','12345678');
INSERT INTO Prescription
VALUES ('P0003','06-APR-2019','5 doses','Taken as instructed','oral','Y','BRD2-3%','D000493','S0003','12322454');
INSERT INTO Prescription
VALUES ('P0004','02-DEC-2018','10 doses','Taken as instructed','oral','Y','BRD4-0.05%','D000493','S0003','12322454');
INSERT INTO Prescription
VALUES ('P0005','01-JAN-2010','5 doses','Taken as instructed','oral','Y','BRD6-15%','D000013','S0002','12344929');
INSERT INTO Prescription
VALUES ('P0006','03-MAY-2011','2 doses','Taken as instructed','oral','Y','BRD4-0.05%','D000493','S0003','12385930');

INSERT INTO Refill 
VALUES ('6','P0001','03-APR-2019','90');
INSERT INTO Refill
VALUES ('4','P0002','03-APR-2018','50');
INSERT INTO Refill
VALUES ('5','P0003','10-APR-2019','56');
INSERT INTO Refill
VALUES ('2','P0004','20-JAN-2019','23');
INSERT INTO Refill
VALUES ('1','P0005','03-MAY-2012','65');
INSERT INTO Refill
VALUES ('4','P0006','02-JUN-2014','42');


INSERT INTO Staff
VALUES ('S0002','Dr. David Phong','321 Brandywine Lane, Miami, FL');
INSERT INTO Staff
VALUES ('S0003','Dr. Elaine Pham','322 Brandywine Lane, Miami, FL');


INSERT INTO Manufacturer 
VALUES ('M0053','Manufacturer1','329 Brandywine Lane, Miami, FL','BRD1-10%');
INSERT INTO Manufacturer
VALUES ('M0054','Manufacturer2','320 Brandy Lane, Miami, FL','BRD2-3%');
INSERT INTO Manufacturer
VALUES ('M0056','Manufacturer3','450 Brandy Lane, Miami, FL','BRD3-0.05%');
INSERT INTO Manufacturer
VALUES ('M0057','Manufacturer4','420 THC Lane, Miami, FL','BRD4-0.05%');
INSERT INTO Manufacturer
VALUES ('M0058','Manufacturer5','550 Nicotine Drive, Miami, FL','BRD5-0.05%');
INSERT INTO Manufacturer
VALUES ('M0059','Manufacturer6','600 Amphetamine Court, Miami, FL','BRD6-15%');
INSERT INTO Manufacturer
VALUES ('M0060','Manufacturer7','795 Miller Rd, Miami, FL','BRD7-0.025%');

INSERT INTO Distributor
VALUES ('DIS0001','Distributor1','485 Ocean Dr, Miami, FL');
INSERT INTO Distributor
VALUES ('DIS0002','Distributor2','456 Ocean Dr, Miami, FL');
INSERT INTO Distributor
VALUES ('DIS0003','Distributor3','469 Ocean Dr, Miami, FL');

INSERT INTO Seller
VALUES ('DIS0001','BRD1-10%');
INSERT INTO Seller
VALUES ('DIS0002','BRD1-10%');
INSERT INTO Seller
VALUES ('DIS0003','BRD1-10%');
INSERT INTO Seller
VALUES ('DIS0001','BRD2-3%');
INSERT INTO Seller
VALUES ('DIS0001','BRD3-0.05%');
INSERT INTO Seller
VALUES ('DIS0001','BRD4-0.05%');
INSERT INTO Seller
VALUES ('DIS0002','BRD5-0.05%');
INSERT INTO Seller
VALUES ('DIS0002','BRD6-15%');
INSERT INTO Seller
VALUES ('DIS0003','BRD7-0.025%');

INSERT INTO Compound
VALUES ('BRD1-10%','D000001','capsule','10mg','10%','primaryIngredient1','secondaryIngredient1','M0053','Take as instructed','oral','Y');
INSERT INTO Compound
VALUES ('BRD2-3%','D000023','capsule','15mg','3%','primaryIngredient2','secondaryIngredient2','M0054','Take as instructed','oral','Y');
INSERT INTO Compound
VALUES ('BRD3-0.05%','D000493','capsule','18mg','.05%','primaryIngredient3','secondaryIngredient3','M0056','Take as instructed','oral','N');
INSERT INTO Compound
VALUES ('BRD4-0.05%','D000493','capsule','18mg','.05%','primaryIngredient3','secondaryIngredient3','M0057','Take as instructed','oral','Y');
INSERT INTO Compound
VALUES ('BRD5-0.05%','D000493','capsule','20mg','.05%','primaryIngredient3','secondaryIngredient3','M0058','Take as instructed','oral','Y');
INSERT INTO Compound
VALUES ('BRD6-15%','D000013','capsule','40mg','15%','primaryIngredient5','secondaryIngredient5','M0059','Take as instructed','oral','Y');
INSERT INTO Compound
VALUES ('BRD7-0.025%','D003431','gel','10ml','.025%','primaryIngredient6','secondaryIngredient6','M0060','Take as instructed','oral','N');
