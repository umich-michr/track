FROM tomcat:11.0.0-jdk21

# Set the working directory inside the container
WORKDIR /app

# Copy the source code into the container
COPY . .

RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew clean build -x test

# Deploy the WAR file to Tomcat
RUN cp build/libs/*.war /usr/local/tomcat/webapps/ROOT.war

COPY docker/tomcat/server.xml /usr/local/tomcat/conf/server.xml
# Append required line to catalina.properties for environment variable substitution
RUN echo "org.apache.tomcat.util.digester.PROPERTY_SOURCE=org.apache.tomcat.util.digester.EnvironmentPropertySource" >> /usr/local/tomcat/conf/catalina.properties

# Pass environment variables as system properties
CMD ["catalina.sh", "run"]

# The EXPOSE directive in the Dockerfile is a metadata instruction that declares which ports the container listens on at runtime. It does not actually expose the port to the host or other containers. Instead, it is informational and can be used by tools like docker run or orchestration platforms to understand the container's network configuration.
# Does not expose the port to the host. Does not affect container-to-container communication.
ARG APACHE_AJP_PORT
ARG APACHE_AJP_SECRET
ARG SPRING_PROFILES_ACTIVE
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD

EXPOSE ${APACHE_AJP_PORT}
ENV AJP_PORT=${APACHE_AJP_PORT:-8009}
ENV AJP_SECRET=${APACHE_AJP_SECRET}
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-dev}
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
