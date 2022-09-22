package com.jalasoft.bootcamp.applicant.infrastructure.common;

import org.springframework.amqp.core.Queue;

public final class RabbitMQConstants {

    public static final String APPLICANT_QUEUE_NAME = "applicant-queue";
    public static final RabbitMQQueueBuilder APPLICANT_QUEUE = new RabbitMQQueueBuilder(APPLICANT_QUEUE_NAME
            , true, "applicant.key");

    public static final String BOOTCAMP_QUEUE_NAME = "bootcamp-queue";
    public static final RabbitMQQueueBuilder BOOTCAMP_QUEUE = new RabbitMQQueueBuilder(BOOTCAMP_QUEUE_NAME
            , true, "bootcamp.key");

    public static final String NOTIFICATION_QUEUE_NAME = "notification-queue";
    public static final RabbitMQQueueBuilder NOTIFICATION_QUEUE = new RabbitMQQueueBuilder(NOTIFICATION_QUEUE_NAME
            , true, "notification.key");

    public static final String SKILL_QUEUE_NAME = "skill-queue";
    public static final RabbitMQQueueBuilder SKILL_QUEUE = new RabbitMQQueueBuilder(
        SKILL_QUEUE_NAME, true, "skill.key");

    public static final RabbitMQExchangeProperties APPLICATION_TOPIC_EXCHANGE = new RabbitMQExchangeProperties("application-exchange"
            , true, false);

    public static final RabbitMQExchangeProperties APPLICATION_FANOUT_EXCHANGE = new RabbitMQExchangeProperties("application-fanout"
            , true, false);

    private RabbitMQConstants() {
        // This class must not be instantiated.
    }

    public static final class RabbitMQQueueBuilder {
        private final String name;
        // Persists data in case the machine shutdowns.
        private final boolean persistingData;
        // Defines the routing key used for this queue.
        private final String routingKey;

        public RabbitMQQueueBuilder(String name, boolean persistingData, String routingKey) {
            this.name = name;
            this.persistingData = persistingData;
            this.routingKey = routingKey;
        }

        public String getName() {
            return name;
        }

        public boolean isPersistingData() {
            return persistingData;
        }

        public String getRoutingKey() {
            return routingKey;
        }

        public Queue build() {
            return new Queue(getName(), isPersistingData());
        }
    }

    public static final class RabbitMQExchangeProperties {
        private final String name;
        // Persists the exchange in case the machine shutdowns, once it's back up the exchange will be brought back alive.
        private final boolean persistingExchange;
        // This exchange is deleted in case all queues stopped using this exchange.
        private final boolean autoDelete;

        public RabbitMQExchangeProperties(String name, boolean persistingExchange, boolean autoDelete) {
            this.name = name;
            this.persistingExchange = persistingExchange;
            this.autoDelete = autoDelete;
        }

        public String getName() {
            return name;
        }

        public boolean isPersistingExchange() {
            return persistingExchange;
        }

        public boolean isAutoDelete() {
            return autoDelete;
        }
    }
}
