# Hello World with RxJava

This simple example shows how to create an HTTP server and how to write to response with RxJava.

## Requirements
- JDK 11 or later
- Gradle 6.5.1

## To run
```shell script
../gradlew vertxRun
```

## To compile and run `.jar`
```shell script
../gradlew clean build
```
and
```shell script
java -jar ./build/libs/hello-world-rxjava-1.0-SNAPSHOT-all.jar
```

A response of `Hi there Vert.x!` can be found in `localhost:8080` through browser.