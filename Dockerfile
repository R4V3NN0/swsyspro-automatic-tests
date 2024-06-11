FROM maven:3.9.7-eclipse-temurin-22-jammy
COPY . .
RUN mvn package
ENTRYPOINT ["java", "-jar", "target/swsyspro-automatic-tests-0.0.1-SNAPSHOT.jar"]