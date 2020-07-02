# Event Bus with RxJava

This example shows how to use Vert.x and RxJava to realize a simple 
Publish-Subscribe pattern.

The `SenderVerticle` creates a random number from `0` to `9` every second and send 
to address `number`. There are two `ReceiverVerticle` instances that subscribe `number`. 
The first instance accepts odd numbers while the second accepts even numbers, and print out.  

## Requirements
- JDK 11 or later
- Gradle 6.5.1

## To run
```shell script
../gradlew vertxRun
```

## To compile and run `.jar`
In the root directory, run
```shell script
../gradlew clean build
```
and
```shell script
java -jar ./build/libs/event-bus-rxjava-1.0-SNAPSHOT-all.jar
```

