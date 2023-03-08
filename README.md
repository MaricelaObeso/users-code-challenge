# users-code-challenge

Hi!

This repository is for sharing the result of "Users REST API Code Challenge". 
The operations that are enabled for a User are:

1. Get a User by ID
2. Create new User (By default as Active)
3. Update existing User (Just FirstName, LastName, DateOfBirth)
4. Delete users (Mark it as INACTIVE)
5. Get all users (Just Active Users)
 
 
Here is the statement to create User table at MySQL.

CREATE TABLE testDB.User ( 
  id int(11) NOT NULL AUTO_INCREMENT, 
  first_name varchar(45), 
  last_name varchar(45), 
  date_of_birth date,  
  status varchar(20),  
  PRIMARY KEY (id) );

 
 Sample Calls:


1. Create new User (By default as Active)
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data '    {
        "firstName": "Mari",
        "lastName": "Obeso",
        "dateOfBirth": "2000-01-01T06:00:00.000+0000"
    }'
    
 
2. Get a User by ID
curl --location 'http://localhost:8080/users/1'



3. Update existing User (Just FirstName, LastName, DateOfBirth)
curl --location --request PUT 'http://localhost:8080/users/1' \
--header 'Content-Type: application/json' \
--data '    {
        "firstName": "Mari",
        "lastName": "Obeso1",
        "dateOfBirth": "2000-01-01T06:00:00.000+0000"
    }'

4. Delete users (Mark it as INACTIVE)
curl --location --request DELETE 'http://localhost:8080/users/22'

5.Get all users
curl --location --request GET 'http://localhost:8080/users' 
