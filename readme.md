# ๐ฅ Oipie api-core

This is a Work in Progress replication of the existing api-core service for oipie implemented in Java.

## Prerequisites

- ๐ณ Docker
- โ๏ธ Java 17
- ๐ก IntelliJ Idea or another code editor

## Libraries and versions

- โ ๏ธJava `17`
- ๐ฑ Spring-boot `2.7.6`
- ๐ Gradle `7.5.1`
- ๐งช Rest-assured `5.3.0`
- ๐ Liquibase `4.17.2`
- โ๏ธ Checkstyle `10.5.0`

## Run

### Running the App

> 1. Set Env vars
>
> Initialize the database and the service
> 2. `./gradlew bootRun`
>
> In order to close the database
>
> 1. ``./gradlew composeDown``

### Migration flow

> See the migration
>1. ``./gradlew liquibase diff ``
>
> Generate the migration
>2. ``./gradlew liquibase diffChangelog``
>
> Execute migration
>3. ``./gradlew liquibase update``
>
> ### WARNING
> Due to an unresolved issue you must remove the line with content `-- liquibase formatted sql` that is generated with
> each migration

### Running tests

> Memory tests
> 1. `./gradlew testMemory`
>
> H2 tests
> 1. `./gradlew testH2`
>
> Database tests
> 1. `./gradlew testDB`

### Compile without tests the App

> 1. `./gradlew build -x test`

### TO DO:

- [ ]  CI/CD


