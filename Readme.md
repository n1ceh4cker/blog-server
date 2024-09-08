*Replace the *** with actual values*
### Docker Build & run

Run the following command to build the docker image.
docker build -t blog --build-arg mongodbUrl=*** --build-arg jwtExpiry=*** --build-arg jwtSecretKey=*** --build-arg port=10000 --build-arg allowedOrigin=*** .

Run the following command to run the docker image and map port 8080 to the exposed port.
docker run -it -p 10000:8080 blog

### Maven build & run
Run the following command to build the jar file using maven
./mvnw clean package -DMONGODB_URL=*** -DJWT_EXPIRY=*** -DJWT_SECRET_KEY=*** -DPORT=8080 -DALLOWED_ORIGIN=***

Run the following command to run the jar
java -jar -DMONGODB_URL=*** -DJWT_EXPIRY=*** -DJWT_SECRET_KEY=*** -DPORT=8080 -DALLOWED_ORIGIN=*** target/*.jar