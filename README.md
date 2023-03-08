# users-code-challenge

Users REST API to:

Get a user by ID
Create new users (By default as Active)
Update existing users (Just update basic info (FirstName, LastName, DateOfBirth))
Delete users (Mark it as INACTIVE)
Get all users (Just show Active users)
 
 
User database table at MySQL.

CREATE TABLE testDB.User ( 
  id int(11) NOT NULL AUTO_INCREMENT, 
  first_name varchar(45), 
  last_name varchar(45), 
  date_of_birth date,  
  status varchar(20),  
  PRIMARY KEY (id) );

 

