###### Prerequests:
- Java 1.8
- Maven 3+
- Docker
- Docker Compose

### 1. create a docker network
```
docker network create justmop_casestudy_network
```

### 2. run mysql server on docker
```
docker container run --network justmop_casestudy_network --name mysql-db -p 3307:3306 -e MYSQL_ROOT_PASSWORD=my_password -e MYSQL_USER=my_user -e MYSQL_PASSWORD=my_password -e MYSQL_DATABASE=my_db -d mysql:5.7
```

### 3. Build Java App
```
cd question1
mvn clean install
```

### 4. Run the app with docker compose
Return to main directory (`cd ..`)
```
docker-compose up --build
```

---

Swagger UI: http://localhost:8081/swagger-ui/index.html

Web UI: http://localhost:8082/

@see [question1/README.md](./question1/README.md)

@see [question2/README.md](./question2/README.md)

@see [example postman collection](./justmop.postman_collection.json)