# yhr-track-app

This repository contains the `yhr-track-app` project, which is built using a Dockerized environment. Follow the instructions below to build, run, and manage the application in a Docker container.

---

## **Building and Running the Application**

### Build and Start the Application
To build the Docker image and start the application:
```bash
docker-compose up --build
```

This command:
- Builds the Docker image using the **Dockerfile**.
- Starts the container using the newly built image.
- Runs the container in the foreground, showing logs in the terminal.
- The application will be accessible at [http://localhost:8080](http://localhost:8080).

### Start Application in Background
To start the application in **detached mode** (background):
```bash
docker-compose up --build -d
```

---

## **Accessing the Running Container**

To access the terminal of the running container and check if the WAR file is in the correct location (e.g., `/usr/local/tomcat/webapps/`), follow these steps:

1. **List Running Containers**:
   ```bash
   docker ps -a
   ```
   Look for your container in the list. The container name should be `track-app`.

2. **Access the Container's Terminal**:
   ```bash
   docker exec -it track-app bash
   ```
   This will open a shell (`bash`) inside the container.

3. **Check the WAR File Directory**:
   ```bash
   ls /usr/local/tomcat/webapps/
   ```

5. **Exit the Container**:
   To exit the container's terminal, type:
   ```bash
   exit
   ```

---

## **Managing the Application**

### Stop the Running Container
To stop the running container:
```bash
docker stop track-app
```

### Start the Stopped Container
To restart the stopped container:
```bash
docker start track-app
```

### Stop and Remove the Container
To stop and remove the container and associated network:
```bash
docker-compose down
```

### Delete the Docker Image
To delete the image created by Docker Compose:
```bash
docker rmi track-app
```

---

## **Temporary Directory Permissions (Optional)**

If you encounter issues with temporary directories in the container, you can ensure writable permissions by adding the following to your Dockerfile:
```dockerfile
ENV JAVA_OPTS="-Djava.io.tmpdir=/app/tmp"
RUN mkdir -p /app/tmp && chmod -R 777 /app/tmp
```

---

## **Additional Notes**

- Use `docker-compose up --build -d` to run the container in detached mode.
- To clean up unused images and containers, use:
  ```bash
  docker system prune -a
  ```
- To remove images and containers. This might help ensure that everything is cleaned up.
  ```bash
    docker-compose down --rmi all
  ```
- You can always view the container logs using:
  ```bash
  docker logs Dockerfile-proxy
  docker logs track-app
  ```

Docker Compose automatically reads from the .env file to substitute values in placeholders in an expression like this: "${HTTP_PORT}:80"  
