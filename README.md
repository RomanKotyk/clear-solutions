**The task has two parts:**
1. Using the resources listed below learn what is RESTful API and what are the best practices to implement it 
2. According to the requirements implement the RESTful API based on the web Spring Boot application: controller, responsible for the resource named Users. 

**Resources:**
RESTful API Design. Best Practices in a Nutshell.
Error Handling for REST with Spring | Baeldung
Testing in Spring Boot | Baeldung
Testing | Spring

**Requirements:**
1. It has the following fields:<br>
  1.1. Email (required). Add validation against email pattern<br>
  1.2. First name (required)<br>
  1.3. Last name (required)<br>
  1.4. Birth date (required). Value must be earlier than current date<br>
  1.5. Address (optional)<br>
  1.6. Phone number (optional)<br>
2. It has the following functionality:<br>
  2.1. Create user. It allows to register users who are more than [18] years old. The value [18] should be taken from properties file.<br>
  2.2. Update one/some user fields<br>
  2.3. Update all user fields<br>
  2.4. Delete user<br>
  2.5. Search for users by birth date range. Add the validation which checks that “From” is less than “To”.  Should return a list of objects<br>
3. Code is covered by unit tests using Spring 
4. Code has error handling for REST
5. API responses are in JSON format
6. Use of database is not necessary. The data persistence layer is not required.
7. Any version of Spring Boot. Java version of your choice
8. You can use Spring Initializer utility to create the project: Spring Initializr
