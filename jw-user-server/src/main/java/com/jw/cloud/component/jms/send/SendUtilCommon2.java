package com.jw.cloud.component.jms.send;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: jiawei
 * @Date: 2019/11/19
 * @Description:
 */
@Component
@Slf4j
public class SendUtilCommon2 extends AbstractRabbitSend implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{

    @Autowired
    @Qualifier("rabbitTemplateCommon")
    private RabbitTemplate rabbitTemplateCommon;

    public void sendMessage(final String text,final String exchangeName,final String queueKey) {
        try {
            Message message = wrapMessage(text);
            log.info("发送内容 text:{}", text);
            rabbitTemplateCommon.setConfirmCallback(this);
            rabbitTemplateCommon.setReturnCallback(this);
            rabbitTemplateCommon.send(exchangeName, queueKey, message);
        } catch (Exception e) {
            log.error("发送失败,text:{}", text, e);
            throw new RuntimeException(e);
        }
    }



    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        String correlationId = message.getMessageProperties().getCorrelationId();
        System.out.println(correlationId);
    }
}
