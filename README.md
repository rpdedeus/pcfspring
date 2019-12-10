# pcfspring

This purpose of this project is to act as a template for Spring applications deployed to Cloud Foundry environments.

# Running locally

1. Clone project and navigate to root directory where the project was cloned.

```none
mkdir /Users/$(whoami)/Dev/
cd /Users/$(whoami)/Dev/
git clone https://github.com/rpdedeus/pcfspring
cd pcfspring
```

2. Ensure Postgres database is running

3. Build and test

```none
./mvnw clean package
```

3. Run project

```none
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=cloud;-DDB_URL=jdbc:postgresql://localhost:5432/pcfspring;-DDB_UN=postgres;-DDB_PW=password"
```

##  Pre-Deployment

1. [Create cloud foundry account](https://login.run.pivotal.io/login)
2. [Download cf cli](https://console.run.pivotal.io/tools) and follow instructions on the page.
3. Create database service.
4. Connect to DB from local client (optional).

## Deployment

1. Login to cf cli

```
cf login -a https://api.run.pivotal.io
```

2. From the project's root directory, issue the command to push the applications

```
cf push
```

## Actuator endpoint 

Use actuator endpoint to validate that the application responds to requests.

```
https://rd-pcfspring.cfapps.io/actuator/health
```

Replace **rd-pcfspring** with you own app name.

## Maintenance / management

### Run Tasks
[Docs](https://docs.cloudfoundry.org/devguide/using-tasks.html)

```
cf run-task rd-pcfspring "ls -la"
```

## Troubleshooting

* Tail logs

```
cf logs rd-pcfspring
```

* Validate app status

View all apps in the current org/space

```
cf apps
```

or  

View specific app in the current org/space

```
cf apps rd-pcfspring
```

* ssh into container

```
cf ssh rd-pcfspring
```
