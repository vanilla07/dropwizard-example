Create a jar file containing the application server : mvn package
Starting the server : java -jar passerelle-admin-1.0-SNAPSHOT.jar server
Start with conf file : java -jar target/passerelle-admin-1.0-SNAPSHOT.jar server auth.yml
Start debug mode with conf file : java -Xdebug -agentlib:jdwp=transport=dt_socket,address=9999,server=y,suspend=n -jar target/passerelle-admin-1.0-SNAPSHOT.jar server auth.yml
Stopping the server : Ctrl+C
Test on Terminal : curl -w "\n" localhost:8080/hello
Test on browser : http://localhost:8080/hello

Grunt angular :  grunt serve
json-server : json-server --watch database.json