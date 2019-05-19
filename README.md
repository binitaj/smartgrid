#  Smart Grid Data Portal on Spring Boot

## What this project is about?
The project allows browsing of Smart Grid's smart meter data on a webpage. 


## Guides
### Prerequisites
To build test the application, you need 
  - maven installed, and `mvn` command available on path
  - jdk 8 installed, and `java` and `javac` available on path
  
### Setup and Testing
To start the application,
 - you will need to build the uberjar. Run `mvn install`
 - run the uberjar. Assuming you have extracted contents of zip as is, and are in the root foldesor, run `java -jar target\reads-0.0.1-SNAPSHOT.jar`. 
 
Now point your browser to http://localhost:8090 to query REST endpoints.

With the provided test data, here are a few REST URLs that work:

http://localhost:8090/api/accounts/032-002790-01
http://localhost:8090/api/accounts/032-002790-01/devices
http://localhost:8090/api/reads?device=35653439&page=2
