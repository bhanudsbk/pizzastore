FROM openjdk:11
WORKDIR /target
COPY /target/pizzastore-0.0.1-SNAPSHOT.jar /target/pizzastore.jar
ENTRYPOINT ["java","-jar","pizzastore.jar"]