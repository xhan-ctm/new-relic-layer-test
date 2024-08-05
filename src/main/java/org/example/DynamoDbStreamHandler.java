package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;

public class DynamoDbStreamHandler implements RequestHandler<DynamodbEvent, String> {

    @Override
    public String handleRequest(final DynamodbEvent event, final Context context) {
        System.out.println("------------------");
        System.out.println("java version:");
        System.out.println(System.getProperty("java.version"));
        System.out.println("------------------");
        System.out.println("DynamodbEvent:");
        System.out.println(event);
        System.out.println("------------------");
        System.out.println("getRecords:");
        System.out.println(event.getRecords());
        System.out.println("------------------");
        System.out.println("getDynamodb:");
        System.out.println(event.getRecords().get(0).getDynamodb());
        System.out.println("------------------");
        return "done";
    }
}