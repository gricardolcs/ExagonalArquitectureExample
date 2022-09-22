# RECRUIT CRM

There are two ways of configuring the server

1. Using docker
2. Installing the services

## Prerequisites 🔨

1. Install Windows 10 or Linux
2. Install java version 11.0.x (suggested: 11.0.10)
3. Install node version 14.16.x (suggested: 14.16.0)
4. Install PostgreSql version >= 10
5. Install MongoDb version >= 4.4.X

   Note: MongoDB 4.2 and lower does not permit creating collections in transactions. This restriction is lifted in 4.4.

   Note: Robo 3T seems to have problems to access replica sets with authenticated connections via "Authentication".

6. Install RabbitMQ version 3.8.x (suggested: 3.8.14)

### Optional prerequisites in docker

Follow these instructions if you are using docker.

1. Install Docker version 20.10.x (suggested: 20.10.5)
2. Install PostgreSql 10 in docker container.
3. Install MongoDb 4.4 in docker container.
4. Install RabbitMQ 3.8.x in docker container. (suggested: 3.8.14)

#### 1. Install Docker version 20.10.5

- Verify docker is already installed.
  `docker --version`

#### 2. Install PostgreSql 10 in docker container.

1. Download the image:
   `docker pull postgres:10`
2. Start container with:
   `docker run -d --name postgresdb -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres:10`
3. Enter docker bash
   `docker exec -it postgresdb bash`
4. Enter PostgreSql shell
   `psql -U postgres`
5. create db
   `CREATE DATABASE bootcamp;`
   `CREATE DATABASE authentication;`
6. Exit PostgreSql shell:
   `Type \q and then press ENTER to quit psql.`
7. Exit docker shell:
   `Type exit`
8. Credentials:

```
host: localhost/ip
port: 5432
default dbname: postgres
default user: postgres
password: postgres
```

#### 3. Install MongoDb 4.1 in docker container.

1. Download the image:
   `docker pull mongo:4.4`
2. Create a docker network:
   `docker network create -d bridge mongo-network`
3. Verify the network was created:
   `docker network ls`
4. Create and start mongo db docker container with:
   `docker run -d --name mongodb1 -p 27017:27017 --net mongo-network mongo:4.4 --replSet rs0`
5. Enter docker bash:
   `docker exec -it mongodb1 bash`
6. enter mongo shell.
   ` mongo`
7. Proceed to create the replica set (Allows transactions to work):
   `rs.initiate( { _id: "rs0", version: 1, members: [ { _id: 0, host : "mongodb1:27017" }]} )`
8. Verify that replica was init correctly:
   `rs.status()`
9. use db admin
   `use admin`
10. create user and db from admin
    `db.createUser( { user: "root", pwd: "root", roles: [ { role: "readWrite", db: "bootcamp_development" }, { role: "readWrite", db: "setting" }],} );`
11. Exit:
    `exit`
12. Credentials:

##### Applicant Database

```
host: localhost/ip
port: 27017
dbname: bootcamp_development
user: root
pass: root
```

##### Setting Database

```
host: localhost/ip
port: 27017
dbname: setting
user: root
pass: root
```

#### 4. Install RabbitMQ 3.8.14 in docker container.

1. Download the image:
   `docker pull rabbitmq:3.8.14-management`
2. Start container with:
   `docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3.8.14-management`
3. Credentials:

```
host: localhost/ip
port: 15672
management: localhost:15672
user: guest
pass: guest
```

## Configuration based on Windows installations🔧

### Configuration PostgreSql

1. Enter PostgreSql shell
2. Create a data bases.

```
CREATE DATABASE authentication;
CREATE DATABASE bootcamp;
```

### Configuration MongoDb

1. Open mongo ‘.cfg’ config files: mongod.cfg. This can be found in windows in the instalation path in bin folder: `C:\Program Files\MongoDB\Server\4.4\bin`
2. Enable replication to support multidocument transactions.

```
replication:
   oplogSizeMB: 128
   replSetName: "rs0"
   enableMajorityReadConcern: true
```

3. Save
4. Restart the service
5. Open mongo Shell `C:\Program Files\MongoDB\Server\4.4\bin>mongo.exe`
6. Proceed to create the replica set (Allows transactions to work):

```
rs.initiate({
      _id: "rs0",
      version: 1,
      members: [
         { _id: 0, host : "localhost:27017" }
      ]
   }
)
```

7. Write the command below: `use admin`
8. Create the root user:

```
db.createUser( { user: "root",
 pwd: "root",
 roles: [ { role: "readWrite", db: "bootcamp_development" }, { role: "readWrite", db: "setting" }],
   } );
```

### Configuration RabbitMQ

1. Find the next path: `RabbitMQ Server\rabbitmq_server-3.8.14\sbin`
2. Execute the command: `rabbitmq-plugins.bat enable rabbitmq_management`
3. Open the browser and write the URL: http://localhost:15672/
4. Log in as user ‘guest’ and password ‘guest’

## Deploy 🚀

In order to start deploy the project you need to have the project.
you can clone from the following url:
`https://gitlab.com/jalasoft-recruitment/recruitcrm`

note: All application properties files are already set to localhost and default ports. you only need to configure it if you running the services and databases in different servers or ports.

Note: Gradlew version should be 5.6.x or greater

### Backend

Follow this order of deployment when running the application:

1. Run Discovery service `(recruitcrm\bootcamps\Backend\discoveryservice)`.
2. Run Gateway service `(recruitcrm\bootcamps\Backend\gateway)`.
3. Run Authentication service `(recruitcrm\bootcamps\Backend\authentication)`.
4. Run Bootcamp service `(recruitcrm\bootcamps\Backend\bootcamp)`.
5. Run Applicant service `(recruitcrm\bootcamps\Backend\applicant)`.
6. Run Setting service `(recruitcrm\bootcamps\Backend\setting)`.

#### 1. Run Discovery service:

1. Open `recruitcrm\Backend\discoveryservice\src\main\resources\application.properties`
2. If required to change the host and port, configure services host and port on application.properties file, otherwise leave as default.
3. Enter to: `recruitcrm\Backend\discoveryservice`
4. Start with the following command `gradlew bootRun`

#### 2. Run Gateway service:

1. Open `recruitcrm\Backend\gateway\src\main\resources\application.properties`
2. If required to change the host and port, configure services host and port on application.properties file, otherwise leave as default.
3. Enter to: `recruitcrm\Backend\gateway`
4. Execute the command `gradlew bootRun`

#### 4. Run Authentication service:

1. Open `recruitcrm\Backend\authentication\src\main\resources\application.properties`
2. If required to change the host and port, configure services host and port on application.properties file, otherwise leave as default.
3. Configure PostgresSQL credentials, host, and port on application.properties file.
4. Configure RabbitMQ credentials, host, and port on application.properties file.
5. Enter to: `recruitcrm\Backend\authentication`
6. Execute the command `gradlew bootRun`

#### Token administration and generation.

For the generation of a token first is needed have an user to create it, to do it, you must
run the below end point, where {{environemnt}} is the host where you want create the token
...

##### CREATE USER ENDPOINT

POST = http://{{environment}}/createUserCredentials
BODY = {
"username":"jalasoft-User",
"role":"ROLE_ADMIN",
"firstname":"juan",
"lastname":"peres",
"email":"user@mail.com",
"password":"Kas20#P",
"comments":"",
"status":""
}

With this data you will create the user that you will need to create the token.

##### CREATE TOKEN ENDPOINT

POST = http://{{local}}/generateToken
BODY = {
"username":"jalasoft-User",
"password":"Kas20#P"
}

The result will be a token for example
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYWxhc29mdC1Vc2VyIiwiZXhwIjoxNjQ4NzU3MzMxLCJpYXQiOjE2NDg3NDgzMzF9.sn0_tM4Icf7QI6skC7PfM7QpSogF69nGeFjIXC_i3gu7TsnD5vkX2VeMU0HDmP2GpsKd9_iKF9qKPBEQmaUl_Q

##### USING THE TOKEN

You need to add the token into the autorization like a bearer token

#### 5. Run Bootcamp service:

1. Open `recruitcrm\Backend\bootcamp\src\main\resources\application.properties`
2. If required to change the host and port, configure services host and port on application.properties file, otherwise leave as default.
3. Configure PostgresSQL credentials, host, and port on application.properties file.
4. Configure RabbitMQ credentials, host, and port on application.properties file.
5. Enter to: `recruitcrm\Backend\bootcamp`
6. Execute the command `gradlew bootRun`
7. After service is up and running, optionally you can fill database with some default and testing data, to do it, run the next command: `gradlew insertMandatoryData insertTestData` 

#### 6. Run Applicant service:

1. Open `recruitcrm\Backend\applicant\src\main\resources\application.properties`
2. If required to change the host and port, configure services host and port on application.properties file, otherwise leave as default.
3. Configure MongoDB credentials, host, and port on application.properties file.
4. Configure RabbitMQ credentials, host, and port on application.properties file.
5. Enter to: `recruitcrm\Backend\applicant`
6. Execute the command `gradlew bootRun`

#### 7. Run Setting service:

1. Open `recruitcrm\Backend\setting\src\main\resources\application.properties`
2. If required to change the host and port, configure services host and port on application.properties file, otherwise leave as default.
3. Configure MongoDB credentials, host, and port on application.properties file.
4. Configure MongoDB credentials, host, and port on application.properties file.
5. Enter to: `recruitcrm\Backend\setting`
6. Execute the command `gradlew bootRun`

#### 8. Verify the services are running.

In order to see the services that are running correctly you can enter to:
`http://localhost:9091/` and you should see a welcome screen from the discovery service.
**Under Instances currently registered with Eureka you must see: the gateway, bootcamp and applicant services running.**

### Frontend

1. Open `recruitcrm\Frontend\.env`
2. If required to change the host and port, put host and port of Gateway servicies on .env file, otherwise leave as default.
3. Move to: `recruitcrm\Frontend\`
4. Install dependencies with: `npm install`

#### Some few useful commands:

- Run the application: `npm start `
- Run tests: `npm test`
- Show the documentation of the components: `npm run storybook`

## Documentation

After running backend services you can see endpoints documentation on the following URLs:

```
Authentication services : http://localhost:9095/swagger-ui/#/
Setting services : http://localhost:9096/swagger-ui/#/
Bootcamp services  : http://localhost:9093/swagger-ui/#/
Applicant services : http://localhost:9092/swagger-ui/#/
```

## Gitlab Continuous Integration and Delivery 📊

### Prerequisites

Before you install git lab runner you should have the following Prerequisites:

#### Generic Runner:

In this runner will run the following stages (Compile, UnitTest, build and Release)

1. Server: `Ubuntu 20 LTS (suggested: 20.04.2)`
2. Docker installed in server: `Docker version 20 (suggested: 20.10.7)`
3. Java 11 installed in server: `OpenJDK version 11.0.x (suggested: 11.0.10)`
4. NodeJs 11 installed in server: `14.16.x (suggested: 14.16.0)`

#### Deploy runner:

This runner is only for deploy stage.

1. Server: recomended: `Ubuntu 20 LTS (suggested: 20.04.2)`
2. Docker installed in server: `Docker version 20 (suggested: 20.10.7)`
3. Docker compose installed in server: `docker-compose version 1 (suggested: 1.26.2)`

### Install runner

1.  Add the GitLab official repository
    to your servers package manage `curl -L https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.deb.sh | sudo bash`

2.  Install runner: `sudo apt-get install gitlab-runner`

3.  Verify runner version: `sudo gitlab-runner -version`

4.  Verify GitLab Runner is running: `sudo service gitlab-runner status`

5.  Add Gitlab to hosts file: `sudo vi /etc/hosts`
<p align="center">
  <img src="Instructions/Images/gitlab-server-hosts-file.jpg">
</p>

6.  Register runner: `sudo gitlab-runner register`
<p align="center">
  <img src="Instructions/Images/gitlab-runner-registration.jpg">
</p>

7.  Runners should have the following tags:

- Runner should be tagged as: `bootcamp-runner`
- Deploy Runner should be tagged as: `bootcamp-deploy-dev`

### Configure runner

1. (All runners) comment all from the following file `/home/gitlab-runner/.bash_logout`

- Note: This is in order to avoid the following issue: https://gitlab.com/gitlab-org/gitlab-runner/-/issues/26605

2. (All runners) Give docker permissions to runner: `sudo usermod -aG docker gitlab-runner`

3. (All runners) Verify permissions: `sudo -u gitlab-runner -H docker info`

4. (Only Generic Runner) create folder called `bootcamp_builds` on `/home/gitlab-runner/`

5. (Only Generic Runner) create the following files with 0 as a content with in `bootcamp_builds` folder.

- `echo 0 > applicant_build.txt`
- `echo 0 > bootcamp_build.txt`
- `echo 0 > discovery_build.txt`
- `echo 0 > gateway_build.txt`
- `echo 0 > authentication_build.txt`
- `echo 0 > setting_build.txt`
- `echo 0 > recruit_ui_build.txt`

6. (Only Generic Runner) Give all permissions to these files and `bootcamp_builds` folder.

7. (Only Generic Runner) configure config.toml `sudo vi /etc/gitlab-runner/config.toml`.
<p align="center">
  <img src="Instructions/Images/gitlab-config-file.jpg">
</p>

8. you can see config.toml example on Instructions/config.toml

9. (Only Deploy runner) create the following folders and give all permissions:

- `/home/ubuntu/volumes/mongo_data/`
- ` /home/ubuntu/volumes/postgres_data/`

### Git lab Environment Variables

In order to run the gitlab ci, you should create the following Variables:

- `CI_APP_VERSION:` This is the app version example: 1.0.0
- `CI_DOCKER_CLI_SECRET:` This is a token in order to connect to docker registry repository.
- `CI_DOCKER_REGISTRY:` This is the docker registry host. example: (docker.jala.pro/recruitment_ats/)
- `CI_DOCKER_REGISTRY_URL:` This is the docker registry url: example: (https://docker.jala.pro/v2/recruitment_ats/)
- `CI_DOCKER_USERNAME:` This is the docker registry username.
- `CI_MONGO_PASSWORD:` This is mongo password.
- `CI_MONGO_USERNAME:` This is mongo username.
- `CI_POSTGRES_PASSWORD:` This is postgres password.
- `CI_RABBITMQ_PASSWORD:` This is rabbitMQ password.
- `CI_RABBITMQ_USERNAME:` This is rabbitMQ username.
- `SERVER_DEPLOY_HOST_DEV:` This is the ip of deploy server.

<p align="center">
  <img src="Instructions/Images/gitlab-ci-environment-variables.jpg">
</p>

### Debugging runner

To access to Docker containers using SSH, you should ask for the PrivateKey

#### For windows:

1. Download MobaXTerm
2. Create a new ssh session
3. Configure the ip of the machine 10.24.48.123 (This is only accessible from the jala vpn)
4. link the private ssh key in the mobaxterm ui
5. Connect as ubuntu user no password

#### For Linux:

1. ssh ubuntu@10.24.48.123 -i path/to/private/key

### Gitlab Cache

- The gitlab runner cache configuration: There is a global cache for all branches, jobs and pipelines.

- Issue: There is issue when removing dependencies and also 2 or more branches are running the same jobs in parallel.

- Recommendation: When removing dependencies on a branch and it is running jobs, let it finish before running other jobs in other branches.

## Contributing

1. Clone it!
2. Create your feature branch: `git checkout -b CRM-SomeTaskId`
3. Commit your changes: `git commit -m 'CRM-SomeTaskId: Add some feature'`
4. Push to the branch: `git push origin CRM-SomeTaskId`
5. Submit a mergue request.

## Resources

In order to understand the architecture implemented in this project, recommend reviewing these resources.

[Bootcamp Application Docs](https://docs.google.com/document/d/1p9UR3lt2bGUD8LmmORx-kGu46is0mBG5meUipPZaQkY/edit#)

[Tactical Domain-Driven Design](https://vaadin.com/learn/tutorials/ddd/tactical_domain_driven_design)

[Domain-Driven Design and the Hexagonal Architecture](https://vaadin.com/learn/tutorials/ddd/ddd_and_hexagonal)

[RabbitMQ Exchanges, routing keys and bindings](https://www.cloudamqp.com/blog/part4-rabbitmq-for-beginners-exchanges-routing-keys-bindings.html)

[Get Started with RabbitMQ on Docker](https://codeburst.io/get-started-with-rabbitmq-on-docker-4428d7f6e46b)
