FROM openjdk:17
COPY build/libs/myallstarteam-0.0.1-SNAPSHOT.jar myallstarteam.jar
ENTRYPOINT ["java", "-jar", "/myallstarteam.jar"]