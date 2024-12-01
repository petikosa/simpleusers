# Documentation

### Setup requirements

* Use Java 21
* Install Postgre 17 - the installation includes pgAdmin4. Use it to create a table called "users" with columns: id (primary, not null), name, username and password (varchar, not null)
* Test with Postman - exported tests are included in simpleusers.postman_collection.json
* Some integration test set up is included, but it is broken

### Possible improvements

* Integration tests setup - seems like Hibernate runs code before creating tables. This results in table not found exceptions on test startup.
* CORS protection could be implemented instead of disabling it in Spring security config
* CI/CD pipeline could be set up on Jenkins or Bamboo with stages like build, test, deploy
* The application can be deployed in a docker container or kubernetes pods with separate configs for development and production
* Roles could be implemented for admin who could edit anybody. This can be implemented on service level, where from the principal the role of current user is available. The other users could edit only their own data.