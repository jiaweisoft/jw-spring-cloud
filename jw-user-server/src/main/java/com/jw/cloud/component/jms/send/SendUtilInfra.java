package com.jw.cloud.component.jms.send;

import com.jw.cloud.component.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @Auther: jiawei
 * @Date: 2019/11/19
 * @Description:
 */
@Component
@Slf4j
public class SendUtilInfra extends AbstractRabbitSend{

    @Autowired
    @Qualifier("rabbitTemplateInfra")
    private RabbitTemplate rabbitTemplateInfra;
    @Transactional(rollbackFor = Exception.class,transactionManager = "rabbitTransactionManager")
    public void sendMessage(final String text,final String exchangeName,final String queueKey) {
        try {
            Message message = wrapMessage(text);
            log.info("发送内容 text:{}", text);
            rabbitTemplateInfra.send(exchangeName, queueKey, message);
            //Integer.parseInt("s");
        } catch (Exception e) {
            log.error("发送失败,text:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void initRabbitTemplate() {
        /*如果消息没有到exchange,则ConfirmCallback回调,ack=false 否则 ack=true*/
        rabbitTemplateInfra.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            if (!ack) {
                //判断是否重试
                //重试时注意correlationId要保持不变
            }
        });

        /**exchange到queue成功,则不回调return exchange到queue失败,则回调return(需设置 mandatory=true,否则exchange找不到queue时，默认直接将消息丢弃,不进行回调) */
        rabbitTemplateInfra.setMandatory(true);
        rabbitTemplateInfra.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}",
                    correlationId, replyCode, replyText, exchange, routingKey);
        });
    }

}
