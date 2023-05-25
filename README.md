# OAuth server
## How to Run
* Build 
  * `docker build -t bill-payment .`
* Check Bill is done successfully
  * `docker images`
* Run
  * docker pull mysql/mysql-server
  * docker build -t bill-payment .
  * `docker run -p 8080:8080 bill-payment` 
  * in background `docker run -d -p 8080:8080 bill-payment`
* List Running containers
  * `docker container ls` 


Test authentication:
`curl --location 'localhost:8080/authenticate' \
--header 'Content-Type: application/json' \
--data '{
"username": "username",
"password": "password"
}'`
