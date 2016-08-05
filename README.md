## Configuration
See `src/main/resources/application.properties` for the configuration bridge configuration which
is used to wire up Camel routes in `SpringBootAwsCamelApplication`.

If you would like to disable any camel routes, you can merely comment out/delete the configuration line.

## Testing

### AWS Setup
  * Create SNS name: `spring-boot-camel`, create an SQS queue to attach to the SNS topic
  * Create SQS names: `spring-boot-camel-entrypoint`, `spring-boot-camel-endpoint`
  * Have your AWS Credentials available as either environment variables or in `~/.aws/config `

### ActiveMQ/JMS Setup
```
docker run --name='activemq' -d \
-e 'ACTIVEMQ_NAME=amqp-srv1' \
-e 'ACTIVEMQ_STATIC_QUEUES=amq-queue' \
-p 8161:8161 \
-p 61616:61616 \
webcenter/activemq:5.13.2
```

### Running
  * Run the Spring Boot application via: `./gradlew bootRun`
  * Populate messages in the `spring-boot-camel-entrypoint` SQS channel via `./gradlew sendMessages` (will populate 250 messages)

### Observations
You will now be able to see messages being pulled from SQS `spring-boot-camel-entrypoint`,
into the ActiveMQ `amq-queue` (http://localhost:8161/admin/queues.jsp),
pulled from ActiveMQ and put into both the `spring-boot-camel-endpoint` SQS channel and sent to `spring-boot-camel` SNS Topic.
You can now verify the queue lengths in the Amazon SQS console (note: the JMS consumers are are competing for messages
and the resulting SQS & SNS endpoints should result in ~1/2 of the message counts each).
