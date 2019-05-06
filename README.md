# Drug Prescription Refill Database
A database system for pharmacies to keep track of their customer's prescription and refill information. Implemented using embedded SQL in Java, and Oracle database. \
Given the [case study of a drug prescription refill system](refill.pdf) and the business needs of a pharmacy, I was tasked to create a relational database system that is able to solve those business needs. I firstly created a conceptual model and its ER diagram: \
![](RefinedER.jpg) 
Then I derived those relations and modeled its logical design with data types and constraints, including primary key, foreign key, and general constraints. Those relations are normalized from 2NF to BCNF. Then I created those tables in SQL in my Oracle database. The script used to create tables are in the ```createtable.sql``` file. I inputted some sample data according to constraints established earlier, and the script for inserting data to the database is in the ```insert.sql``` file. After that, I implemented an embedded SQL program in Java with a command-line user interface that allows users to query, update, delete, and insert into the database. The .java files that make up the embedded SQL Java program are: 
```
Main.java
Menu.java
QueryManager.java
UI.java
```
To run, simply type ```make run``` as the ```Makefile``` script would handle the compilation process. \
My database system is able to handle the following sample queries. 
1. List all the active prescriptions of a customer \
![](/Drug-Prescription-DB-outputImages/db1.jpeg) 
2. List the details of all the distributors and manufacturers for a compound 
![](/Drug-Prescription-DB-outputImages/db2.jpeg) 
3. List the details of a compound
![](/Drug-Prescription-DB-outputImages/db3.jpeg) 
4. List the total number of active prescriptions and their detailed information
![](/Drug-Prescription-DB-outputImages/db4.jpeg) 
5. List the number of refills left and the expiration date of a prescription 
![](/Drug-Prescription-DB-outputImages/db5.jpeg) 
6. List all the prescriptions that have compounds not appropriate for the conditions being treated 
![](/Drug-Prescription-DB-outputImages/db6.jpeg) 
7. List all generic substitutions for a compound
![](/Drug-Prescription-DB-outputImages/db7.jpeg) 
8. Update the address information of a physician
![](/Drug-Prescription-DB-outputImages/dbupdate.jpeg) 
9. Delete all prescriptions that are more than five years old
![](/Drug-Prescription-DB-outputImages/dbdelete.jpeg) 
10. Insert a new prescription for a new customer
![](/Drug-Prescription-DB-outputImages/dbinsert.jpeg) 
