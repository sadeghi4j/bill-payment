# OAuth server
## How to Run
* Build 
  * `docker build -t oauth-server .`
* Check Bill is done successfully
  * `docker images`
* Run
  * `docker run -p 8080:8080 oauth-server` 
  * in background `docker run -d -p 8080:8080 oauth-server`
* List Running containers
  * `docker container ls` 


Test authentication:
`curl --location 'localhost:8080/authenticate' \
--header 'Content-Type: application/json' \
--data '{
"username": "techgeeknext",
"password": "password"
}'`

https://www.javaguides.net/2022/12/deploy-spring-boot-application-on-docker.html
https://spring.io/guides/topicals/spring-boot-docker/
