#
# Build stage
#
FROM maven:3.9.9-eclipse-temurin-22-alpine AS build
ARG mongodbUrl
ARG jwtExpiry
ARG jwtSecretKey
ARG allowedOrigin
ARG port
ENV MONGODB_URL=$mongodbUrl
ENV JWT_EXPIRY=$jwtExpiry
ENV JWT_SECRET_KEY=$jwtSecretKey
ENV ALLOWED_ORIGIN=$allowedOrigin
ENV PORT=$port
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DMONGODB_URL=${MONGODB_URL} -DJWT_EXPIRY=${JWT_EXPIRY} -DJWT_SECRET_KEY=${JWT_SECRET_KEY} -DPORT=${PORT} -DALLOWED_ORIGIN=${ALLOWED_ORIGIN} 
RUN echo -e "#!bin/sh \n java -jar -DMONGODB_URL=${MONGODB_URL} -DJWT_EXPIRY=${JWT_EXPIRY} -DJWT_SECRET_KEY=${JWT_SECRET_KEY} -DPORT=${PORT} -DALLOWED_ORIGIN=${ALLOWED_ORIGIN} /usr/local/lib/blog.jar" > /home/app/entrypoint.sh

#
# Package stage
#
FROM eclipse-temurin:22-jre-alpine
COPY --from=build /home/app/target/*.jar /usr/local/lib/blog.jar
COPY --from=build /home/app/entrypoint.sh ./entrypoint.sh
RUN chmod +x ./entrypoint.sh
EXPOSE 10000
ENTRYPOINT ["./entrypoint.sh"]
