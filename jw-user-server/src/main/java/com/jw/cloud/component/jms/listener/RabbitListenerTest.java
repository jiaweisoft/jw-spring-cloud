package com.jw.cloud.component.jms.listener;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: jiawei
 * @Date: 2019/11/29
 * @Description:
 */
@Component
@Slf4j
public class RabbitListenerTest implements MessageListener {

    @SneakyThrows
    @RabbitListener(containerFactory = "containerFactoryCommon",
            bindings = {
                    @QueueBinding(value = @Queue(value = "jw.test.queue.direct", durable = "true", autoDelete = "false"),
                            exchange = @Exchange(value = "jw.test.exchange.common.direct",
                                    durable = "true", autoDelete = "false", type = ExchangeTypes.DIRECT), key = "jw.test.k.direct")})
    public void onMessage(Message message) {
        try {
            log.info("接收内容 message:" + message.toString());
            //Thread.sleep(100000);
            JSONObject json = JSONObject.parseObject(new String(message.getBody()));
            //Integer.parseInt("s");
        } catch (AmqpRejectAndDontRequeueException e) {
            log.error("处理异常 AmqpRejectAndDontRequeueException ", e);
            throw new AmqpRejectAndDontRequeueException(e);
        } catch (Exception e) {
            log.error("处理异常 Exception", e);
            throw new Exception(e);
        }
    }
}
