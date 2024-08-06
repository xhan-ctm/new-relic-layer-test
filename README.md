# new-relic-layer-test
A sample project that illustrate an issue when using New Relic Java Layer for Lambda

## Issue description
When a New Relic layer is applied to a Lambda function, `dynamodb` in `records` of `DynamodbEvent` is missing after the processing of the handler `com.newrelic.java.HandlerWrapper::handleRequest`.

## Steps to replicate the issue
1. Clone this sample repo: https://github.com/xhan-ctm/new-relic-layer-test
2. Build the project through Maven (`mvn install` or `mvn package`)
3. Log in AWS through CLI.
4. Navigate to the root directory `new-relic-layer-test` and run `sam local invoke -e ./dynamo-stream-event.json -t deployment/sam.yml DynamodbStreamConsumer`. The result should look like this: 
![image](https://github.com/user-attachments/assets/54af4492-9475-451f-ae2d-60f2775a7f27)

It can be observed that when using layers and `com.newrelic.java.HandlerWrapper::handleRequest`, `dynamodb` is empty.

5. Edit `deployment/sam.yml`, removing `Layers` and replacing `Handler` with `org.example.DynamoDbStreamHandler::handleRequest` (line 15-19), then run the command line in step 3 again. The result should look like this: 
![image](https://github.com/user-attachments/assets/611d1fe1-623b-433a-a940-b57e60c54468)

It can be observed that when using the handler of the Lambda function directly, `dynamodb` is not empty.

6. Change the `Runtime` and Layers in `deployment/sam.yml` (line 16 and 20) and `java.version` in `pom.xml` to Java 8, rebuild the project (`mvn clean install` or `mvn clean package`) and test it. The result is the same with Java 11.
