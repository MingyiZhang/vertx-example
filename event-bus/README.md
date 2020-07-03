# Event Bus

This simple example shows how to send messages between verticles using event bus.

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
java -jar ./build/libs/event-bus-1.0-SNAPSHOT-all.jar
```

## To send message
```shell script
curl -i -X POST localhost:8080/send/hello
```
In the terminal, you will see something similar to
```shell script
Jul 02, 2020 11:06:44 AM ReceiverVerticle
INFO: Received message: hello
Jul 02, 2020 11:06:44 AM SenderVerticle
INFO: Received reply: Message received!
```

