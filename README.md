# Bill Payment

## Introduction
This project is about paying bill. You can inquiry bill by bill id or phone number, and then you can pay it.
After successful payment, the balance of user account will be subtracted and one payment record will be inserted into Payment table. 

There is no relation between `Bill` and `Payment` entity due to increasing performance and not forced to retain previously paid bill in DB. 
## Installation

<details><summary><b>Show instructions</b></summary>

1. First pull mysql image using 
```bash 
docker pull mysql/mysql-server 
```

2. create directory for mysql data  
```bash
mkdir /tmp/mysql-data
```

3. Run MySql Using

   1. docker compose:

        ```bach 
        docker compose up
        ```

    2. Using DockerFile: 

        ```bash
        docker run -d --name mysql --rm -v /tmp/mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=billpayment -e MYSQL_ROOT_HOST='%' -p 3306:3306 -it mysql/mysql-server
        ```

## How to Run
* Run app using IDE with profile `mysql` or `h2` or command `mvn clean package`
</details>

## Send Request
You can import [bill-payment.postman_collection.json](src%2Fmain%2Fresources%2Fpostman%2Fbill-payment.postman_collection.json) into postman in order to send request to app.