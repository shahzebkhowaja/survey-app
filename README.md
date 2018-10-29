# SurveyApp

## Preprerequisite

### Install JDK
- Use this link to download the JDK and then install it: https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html

### Install and Configure PostgreSQL Database
- Use this link to download the PostgreSQL databse and then install it: https://www.postgresql.org/download/windows/
- Once installed open pgAdmin tool and create a databse for survey-app

### Install Node.jS
- Use the link to download the Node.js and then install it: https://nodejs.org/en/

### Config dropwizard.yml
- Once done with all above steps, you'll have to define the name of the database, username, and password in `dropwizard.yml`.
- It is located under `survey-app\survey-ws\src\dist\config\dropwizard.yml` if your are cloning the project.
- If you are using the distribution-package then it will be located under `survey-app-distribution\survey-ws\config`

## Start Web Services and Web Application

### Run without cloning repo
- Use the link to download the distribution package: https://github.com/shahzebkhowaja/survey-app/releases
- Unzip the package, and open unziped package on terminal/CommandPrompt/PowerShell: e.g ("survey-app-distribution\")
- Navigate to `survey-app-distribution\survey-app\`
- Run `npm install`, to install node module, once finished run `npm start`, it will start the fron-end app on port `4200` - http://localhost:4200/
- Now, open another terminal/CommandPrompt/PowerShell and navigate to `survey-app-distribution\survey-ws\bin`
- Run `survey-app.sh` or `survey-app.cmd`, it will start the back-end service on port 8080

### Run with cloning repo
- `survey-ws` is a back-end project in the same repo and developed using `java`
- `survey-app` is a front-end project developed using `Angular 7`
- Download and install IntelliJ using the link https://www.jetbrains.com/idea/download/#section=windows
- Run `java` back-end application on IntelliJ, it will start the back-end service on port 8080 - http://localhost:8080
- Navigate to cloned repo: eg ("survey-app\")
- Run `npm install`, to install node module, once finished run `npm start`, it will start the fron-end app on port `4200` - http://localhost:4200/
