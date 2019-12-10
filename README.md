# pcfspring

This purpose of this project is to act as a guide for Spring applications deployed to Cloud Foundry environments.

# Running locally

1. Clone project and navigate to root directory where the project was cloned.

```none
mkdir /Users/$(whoami)/Dev/
cd /Users/$(whoami)/Dev/
git clone https://github.com/rpdedeus/pcfspring
cd pcfspring
```

2. Ensure Postgres database is running.

3. Build and test.

```none
./mvnw clean package
```

3. Run project locally.

```none
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=cloud;-DDB_URL=jdbc:postgresql://localhost:5432/pcfspring;-DDB_UN=postgres;-DDB_PW=password"
```

# Deployment 

##  Pre-Deployment

1. [Create cloud foundry account](https://login.run.pivotal.io/login)
2. [Download cf cli](https://console.run.pivotal.io/tools) and follow instructions on the page.
3. Create database service.
4. Connect to DB from local client (optional).


### Create a service

View available services

```
cf marketplace
```

Create an instance of the ElephantSQL's service using the free tier called "turtle":

```
cf create-service elephantsql turtle rd-pcfspring-db
```

View service details using cf cli. Copy the dashboard URL to your clipboard.

```
cf service rd-pcfspring-db
```

Make sure you are logged into the pivotal web console and go to the dashboard URL acquired from the previous step.
From the ElephantSQL dashboard, acquire, connection URL, username and password.


## Connect to DB from local client.

Use the credentials from the "Create a service" section, to create a database connection using your favorite client.


## Deployment

1. Login to cf cli.

```
cf login -a https://api.run.pivotal.io
```

2. Open the manifest file. Change the value of the "name" key to something of you liking. This value has to be unique.

3. From the project's root directory, issue the command to push the applications.

```
cf push --no-start
```

4. Set environment variables.

Set environment variables using the following format:  
cf set-env APP_NAME ENV_VAR_NAME ENV_VAR_VALUE

```
cf set-env rd-pcfspring DB_UN <actual_user_name>
cf set-env rd-pcfspring DB_PW <actual_password>
cf set-env rd-pcfspring DB_URL <actual_url>
```

5. Start application.

```
cf start rd-pcfspring
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
