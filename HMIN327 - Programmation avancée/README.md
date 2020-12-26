# HMIN327 - Programmation avancée

## JavaEE

### Maven project

#### Java execution commands

---

`export CLASSPATH=~/eclipse/mysql-connector/mysql-connector-java-8.0.21.jar:.`
`echo $CLASSPATH`
`javac EX1_20.java`
`java EX1_20`

#### MySQL Commands

---

`use e20190009681;`
`mysql -h mysql.etu.umontpellier.fr -u e20190009681 -p e20190009681 < ./monuments_insertion.sql`
`delete from lieu;` -- delete all rows in the table "lieu"
`mysql -uroot -proot databaseName < createTables.sql` : Insert an .sql file into the database

Distortion des langages
Patron de Conception
DAO = Data Access Object

Specification JavaEE, JPA: Java Persistence API (Hibernate)

#### Spring boot

---

Install Spring boot on Mac with brew command:
`brew tap pivotal/tap`
`brew install springboot maven`
`brew cask install springtoolsuite`

Homebrew will install _spring_ to `/usr/local/bin`.

## Spring Boot Application Layering

- Data Access (or Persistence) Layer:
  - Repository classes
    - Entities: @Entity
    - Repository: @Repository
- Business Logic Layer:
  - service classes @Service or @Component
- Presentation Layer:
  - Controller classes @RestController

Spring Dependency Injection:

- Via XML or
- via Annotations

### Difference between @RestController and @Controller Annotation in Spring MVC and REST

The job of @Controller is to create a Map of the model object and find a view but @RestController simply returns the object and object data is directly written into HTTP response as JSON or XML.

This can also be done with traditional @Controller and use @ResponseBody annotation but since this is the default behavior of RESTful Web services, Spring introduced @RestController which combined the behavior of @Controller and @ResponseBody together.

The fundamental difference between a web application and a REST API is that the response from a web application is generally view (HTML + CSS + JavaScript) because they are intended for human viewers while REST API just returns data in form of JSON or XML because most of the REST clients are programs. This difference is also obvious in the @Controller and @RestController annotation.

### Notes

- Entities are of type serializable.

- We can either utilise Spring Data (@Repository) or Entity Manager.

- We can use CommandLineRunner for test the application

- @RestController returns just contents in the format of json, but @Controller returns html file
