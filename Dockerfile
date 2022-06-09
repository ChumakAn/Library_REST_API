FROM openjdk:11
ADD target/spring_boot_lab_6-0.0.1-SNAPSHOT-exec.jar spring_boot_lab_6-0.0.1-SNAPSHOT-exec.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring_boot_lab_6-0.0.1-SNAPSHOT-exec.jar"]
