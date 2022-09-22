package com.jalasoft.bootcamp.bootcamp.infrastructure.configuration.rabbitmq;

import static com.jalasoft.bootcamp.bootcamp.infrastructure.common.RabbitMQConstants.APPLICANT_QUEUE;
import static com.jalasoft.bootcamp.bootcamp.infrastructure.common.RabbitMQConstants.APPLICATION_FANOUT_EXCHANGE;
import static com.jalasoft.bootcamp.bootcamp.infrastructure.common.RabbitMQConstants.APPLICATION_TOPIC_EXCHANGE;
import static com.jalasoft.bootcamp.bootcamp.infrastructure.common.RabbitMQConstants.BOOTCAMP_QUEUE;
import static com.jalasoft.bootcamp.bootcamp.infrastructure.common.RabbitMQConstants.NOTIFICATION_QUEUE;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    private Queue bootcampQueue;
    private Queue applicantQueue;
    private Queue notificationQueue;

    private TopicExchange topicExchange;
    private FanoutExchange fanoutExchange;

    /*
     * Allows RabbitMQ to create the queues in case the broker doesn't find them created.
     */
    @Bean
    Queue defineBootcampQueue() {
        if (bootcampQueue == null) {
            bootcampQueue = BOOTCAMP_QUEUE.build();
        }
        return bootcampQueue;
    }

    @Bean
    Queue defineApplicantQueue() {
        if (applicantQueue == null) {
            applicantQueue = APPLICANT_QUEUE.build();
        }
        return applicantQueue;
    }

    @Bean
    Queue defineNotificationQueue() {
        if (notificationQueue == null) {
            notificationQueue = NOTIFICATION_QUEUE.build();
        }
        return notificationQueue;
    }

    /*
     * Allows RabbitMQ to create the exchanges if the broker doesn't find them created.
     */
    @Bean
    TopicExchange defineTopicExchange() {
        if (topicExchange == null) {
            topicExchange = new TopicExchange(APPLICATION_TOPIC_EXCHANGE.getName()
                , APPLICATION_TOPIC_EXCHANGE.isPersistingExchange()
                , APPLICATION_TOPIC_EXCHANGE.isAutoDelete());
        }
        return topicExchange;
    }

    @Bean
    FanoutExchange defineFanoutExchange() {
        if (fanoutExchange == null) {
            fanoutExchange = new FanoutExchange(APPLICATION_FANOUT_EXCHANGE.getName()
                , APPLICATION_FANOUT_EXCHANGE.isPersistingExchange()
                , APPLICATION_FANOUT_EXCHANGE.isAutoDelete());
        }
        return fanoutExchange;
    }

    /*
     * Creates the bindings between the exchanges, queues and routing key
     */
    @Bean
    Binding defineBootcampQueueTopicExchangeBinding() {
        return BindingBuilder.bind(defineBootcampQueue()).to(defineTopicExchange()).with(
            BOOTCAMP_QUEUE.getRoutingKey());
    }

    @Bean
    Binding defineBootcampQueueFanoutExchangeBinding() {
        return BindingBuilder.bind(defineBootcampQueue()).to(defineFanoutExchange());
    }

    @Bean
    Binding defineApplicantQueueTopicExchangeBinding() {
        return BindingBuilder.bind(defineApplicantQueue()).to(defineTopicExchange())
            .with(APPLICANT_QUEUE.getRoutingKey());
    }

    @Bean
    Binding defineApplicantQueueFanoutExchangeBinding() {
        return BindingBuilder.bind(defineApplicantQueue()).to(defineFanoutExchange());
    }

    @Bean
    Binding defineNotificationQueueTopicExchangeBinding() {
        return BindingBuilder.bind(defineNotificationQueue()).to(defineTopicExchange())
            .with(NOTIFICATION_QUEUE.getRoutingKey());
    }

    @Bean
    Binding defineNotificationQueueFanoutExchangeBinding() {
        return BindingBuilder.bind(defineNotificationQueue()).to(defineFanoutExchange());
    }

    /*
     * Allows RabbitMQ to deserialize and serialize our messages as JSON.
     */
    @Bean
    public MessageConverter defineJSONMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
