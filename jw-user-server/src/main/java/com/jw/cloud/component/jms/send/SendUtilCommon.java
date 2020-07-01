package com.jw.cloud.component.jms.send;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @Auther: jiawei
 * @Date: 2019/11/19
 * @Description: String exchangeName = "jw.test.exchange.common.direct";
 * String queueKey = "jw.test.k.direct";
 */
@Component
@Slf4j
public class SendUtilCommon extends AbstractRabbitSend {

    @Autowired
    @Qualifier("rabbitTemplateCommon")
    private RabbitTemplate rabbitTemplateCommon;

    public void sendMessage(final String text, final String exchangeName, final String queueKey) {
        Message message = wrapMessage(text);
        log.info("发送内容 text:{}", text);
        CorrelationData c = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplateCommon.send(exchangeName, queueKey, message, c);
        log.info("发送内容成功啦");
    }


    @PostConstruct
    private void initRabbitTemplate() {
        /*如果消息没有到exchange,则ConfirmCallback回调,ack=false 否则 ack=true*/
        rabbitTemplateCommon.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            if (!ack) {
                //判断是否重试
                //重试时注意correlationId要保持不变
            }
        });

        /**exchange到queue成功,则不回调return exchange到queue失败,则回调return(需设置 mandatory=true,否则exchange找不到queue时，默认直接将消息丢弃,不进行回调) */
        rabbitTemplateCommon.setMandatory(true);
        rabbitTemplateCommon.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}",correlationId, replyCode, replyText, exchange, routingKey);
        });
    }
}
