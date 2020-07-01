package com.jw.cloud2.component.jms.send;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;

import java.util.UUID;

public abstract class AbstractRabbitSend {

    protected Message wrapMessage(String content) {
        String uuid = UUID.randomUUID().toString();
        Message message = MessageBuilder.withBody(content.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .setHeader("TRACE-ID", uuid)
                .setHeader("CREATE-TIME", System.currentTimeMillis())
                .setHeader("PRODUCER-SYSTEM", "weixin")
                .setMessageId(uuid)
                .build();
        return message;
    }
}
