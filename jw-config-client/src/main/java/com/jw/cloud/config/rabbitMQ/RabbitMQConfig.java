package com.jw.cloud.config.rabbitMQ;

import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @Auther: jiawei
 * @Date: 2019/9/2
 * @Description:
 */
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.virtual-host.common}")
    private String virtualHostCommon;
    @Value("${rabbitmq.virtual-host.infra}")
    private String virtualHostInfra;
    @Value("${rabbitmq.host}")
    private String address;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;
    @Autowired
    @Qualifier("myTaskExecutor")
    private TaskExecutor myTaskExecutor;

    // common begin
    @Bean(value = "connectionFactoryCommon")
    @Primary
    public ConnectionFactory connectionFactoryCommon() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(this.address);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHostCommon);
        connectionFactory.setRequestedHeartBeat(15);
        return connectionFactory;
    }

    @Bean(name = "rabbitTemplateCommon")
    public RabbitTemplate rabbitTemplateCommon(@Qualifier("connectionFactoryCommon") ConnectionFactory connectionFactoryCommon) {
        return new RabbitTemplate(connectionFactoryCommon);
    }

    @Bean(name = "containerFactoryCommon")
    public SimpleRabbitListenerContainerFactory containerFactoryCommon(@Qualifier("connectionFactoryCommon") ConnectionFactory connectionFactoryCommon) {
        SimpleRabbitListenerContainerFactory factory = getMySimpleRabbitListenerContainerFactory(connectionFactoryCommon);
        return factory;
    }

    // common end
    //infra begin
    @Bean(value = "connectionFactoryInfra")
    public ConnectionFactory connectionFactoryInfra() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(this.address);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHostInfra);
        connectionFactory.setRequestedHeartBeat(15);
        return connectionFactory;
    }

    @Bean(name = "rabbitTemplateInfra")
    public RabbitTemplate rabbitTemplateInfra(@Qualifier("connectionFactoryInfra") ConnectionFactory connectionFactoryInfra) {
        return new RabbitTemplate(connectionFactoryInfra);
    }

    @Bean(name = "containerFactoryInfra")
    public SimpleRabbitListenerContainerFactory containerFactoryInfra(@Qualifier("connectionFactoryInfra") ConnectionFactory connectionFactoryInfra) {
        SimpleRabbitListenerContainerFactory factory = getMySimpleRabbitListenerContainerFactory(connectionFactoryInfra);
        return factory;
    }
    // infra end

    public SimpleRabbitListenerContainerFactory getMySimpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());

        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(8);
        factory.setTaskExecutor(myTaskExecutor);
        factory.setChannelTransacted(true);
        factory.setAdviceChain(
                RetryInterceptorBuilder
                        .stateless()
                        .recoverer(new RejectAndDontRequeueRecoverer())
                        .retryOperations(retryTemplate())
                        .build()
        );
        return factory;
    }


    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(backOffPolicy());
        retryTemplate.setRetryPolicy(retryPolicy());
        return retryTemplate;
    }

    @Bean
    public ExponentialBackOffPolicy backOffPolicy() {
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(1000);
        backOffPolicy.setMaxInterval(10000);
        return backOffPolicy;
    }

    @Bean
    public SimpleRetryPolicy retryPolicy() {
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        return retryPolicy;
    }
}


