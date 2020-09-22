## Example Micronaut API with Postgres. Written in Kotlin.

## Tech and tools used
- Kotlin - for development
- PostgreSQL - persistence store
- Liquibase - aMigrations and DB change version control
- NORM - Types for your SQL in kotlin created in house 
- Micronaut - Framework just like SpringBoot for good support for cloud native applications.
- Gradle - Build tool
- Ktlint - for linting

## Project Structure
The project structure follows CCD Architecture
- `core` - Business logic
- `connector` - Database/Kinesis/API ingest
- `delivery` - API
- `migration` : Database migrations.

It is a multi-module gradle project. Top Level project does not contain any sources. 

Main module: `api` : Exposes API for example app

## Pre-requisites
You need Java 11.08, which can be configured using [SDKMAN](https://sdkman.io/).
Also, you would need to setup Gradle JVM to 11.08

### Setting up Java

- Install JDK:
  `$brew cask install adoptopenjdk/openjdk/adoptopenjdk11`

- Setup JAVA_HOME: Follow [these](https://www.appsdeveloperblog.com/how-to-set-java_home-on-mac/) steps.
- Select Gradle JVM Version 11.08

## Ktlint Commands:

- Configure IntelliJ IDEA - `./gradlew ktlintApplyToIdea`
- Configure Pre-commit Hook for Format - `./gradlew addKtlintFormatGitPreCommitHook`
- Format Kotlin Code - `./gradlew ktlintFormat`
- Check Kotlin Lint - `./gradlew ktlintCheck`

## Local setup:
Required tools:
1. Java 11.08
2. Postgres server (by default, this project looks for a local Postgres instance called kt_mn_example)
    a. The example credentials expect a user named 'johndoe' with password 'secret' to exist in your database. 
        You can either create this user or create your own and change the credentials locally.
3. sam cli

---

## Running API locally:
Can be run in two modes:

### Continuous running server
- Run Gradle command for running API:
    `./gradlew :api:run`
- Hit using following endpoint http://localhost:9000/api

### As lambda (using sam cli):

- Generate zip using `./gradlew shadowJar`
- Run sam-cli command `sam local start-api -t sam.yaml`
- Hit using following endpoint `http://localhost:3000/api`
  
---    

### Executing tests (Using Gradle command):
Run Gradle command: 
```
./gradlew test
``` 
This Gradle command will execute all tests present in project.

### Executing tests (Using Kotest plugin)
- Install [Kotest plugin](https://plugins.jetbrains.com/plugin/14080-kotest) in IntelliJ.
- In Test class, at the left side of class name (after line number) you'll see GREEN Play button 🟢▶️. Just click on that and test will be executed.

---

## Guidelines for adding SQLs
We are using NORM for generating types for our SQLs. Once we write the SQLs we need to run the below command to generate those types

```
./gradlew compileNorm
```

## Guidelines for writing migrations
1. Add new sql query in migration/sql/<file_name>.sql
2. Please follow below convention while naming file
    - Add the time stamp at which you are creating the file.
    - YYYY_MM_DD_HR_MM_<description of query>.sql (Use 24hour format)
3. This file will get included in migrations

Update: migrations/migration.gradle file with your local Postgres instance

## Before pushing code
- Format code with KtLint
- Make sure `./gradlew build` is executing without failure.
