FROM eclipse-temurin:17-jre-alpine
RUN mkdir /opt/app
COPY build/libs/dashboard-0.1-all.jar /opt/app
CMD ["java", "-jar", "/opt/app/dashboard-0.1-all.jar"]
