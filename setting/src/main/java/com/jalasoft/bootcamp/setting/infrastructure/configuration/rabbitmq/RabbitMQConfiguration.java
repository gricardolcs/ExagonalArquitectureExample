package com.jalasoft.bootcamp.setting.infrastructure.configuration.rabbitmq;

import com.jalasoft.bootcamp.setting.infrastructure.common.RabbitMQConstants;
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
public class RabbitMQConfiguration
{
    private Queue applicantQueue;
    private Queue skillQueue;
    private Queue notificationQueue;
    private TopicExchange topicExchange;
    private FanoutExchange fanoutExchange;

    /*
     * Allows RabbitMQ to create the queues in case the broker doesn't find them created.
     */
    @Bean
    Queue defineApplicantQueue()
    {
        if (applicantQueue == null)
        {
            applicantQueue = RabbitMQConstants.APPLICANT_QUEUE.build();
        }
        return applicantQueue;
    }

    @Bean
    Queue definedSkillQueue()
    {
        if (skillQueue == null)
        {
            skillQueue = RabbitMQConstants.SKILL_QUEUE.build();
        }
        return skillQueue;
    }

    @Bean
    Queue defineNotificationQueue()
    {
        if (notificationQueue == null)
        {
            notificationQueue = RabbitMQConstants.NOTIFICATION_QUEUE.build();
        }
        return notificationQueue;
    }

    /*
     * Allows RabbitMQ to create the exchanges if the broker doesn't find them created.
     */
    @Bean
    TopicExchange defineTopicExchange()
    {
        if (topicExchange == null)
        {
            topicExchange = new TopicExchange(RabbitMQConstants.APPLICATION_TOPIC_EXCHANGE.getName()
                , RabbitMQConstants.APPLICATION_TOPIC_EXCHANGE.isPersistingExchange()
                , RabbitMQConstants.APPLICATION_TOPIC_EXCHANGE.isAutoDelete());
        }
        return topicExchange;
    }

    @Bean
    FanoutExchange defineFanoutExchange()
    {
        if (fanoutExchange == null)
        {
            fanoutExchange = new FanoutExchange(RabbitMQConstants.APPLICATION_FANOUT_EXCHANGE.getName()
                , RabbitMQConstants.APPLICATION_FANOUT_EXCHANGE.isPersistingExchange()
                , RabbitMQConstants.APPLICATION_FANOUT_EXCHANGE.isAutoDelete());
        }
        return fanoutExchange;
    }

    /*
     * Creates the bindings between the exchanges, queues and routing key
     */
    @Bean
    Binding defineApplicantQueueTopicExchangeBinding()
    {
        return BindingBuilder.bind(defineApplicantQueue()).to(defineTopicExchange())
            .with(RabbitMQConstants.APPLICANT_QUEUE.getRoutingKey());
    }

    @Bean
    Binding defineApplicantQueueFanoutExchangeBinding()
    {
        return BindingBuilder.bind(defineApplicantQueue()).to(defineFanoutExchange());
    }

    @Bean
    Binding defineSkillQueueTopicExchangeBinding()
    {
        return BindingBuilder.bind(definedSkillQueue()).to(defineTopicExchange())
            .with(RabbitMQConstants.SKILL_QUEUE.getRoutingKey());
    }

    @Bean
    Binding defineSkillQueueFanoutExchangeBinding()
    {
        return BindingBuilder.bind(definedSkillQueue()).to(defineFanoutExchange());
    }

    @Bean
    Binding defineNotificationQueueTopicExchangeBinding()
    {
        return BindingBuilder.bind(defineNotificationQueue()).to(defineTopicExchange())
            .with(RabbitMQConstants.NOTIFICATION_QUEUE.getRoutingKey());
    }

    @Bean
    Binding defineNotificationQueueFanoutExchangeBinding()
    {
        return BindingBuilder.bind(defineNotificationQueue()).to(defineFanoutExchange());
    }

    /*
     * Allows RabbitMQ to deserialize and serialize our messages as JSON.
     */
    @Bean
    public MessageConverter defineJSONMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
}
