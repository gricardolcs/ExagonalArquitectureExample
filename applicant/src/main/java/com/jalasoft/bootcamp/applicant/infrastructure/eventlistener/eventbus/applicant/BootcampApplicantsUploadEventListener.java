package com.jalasoft.bootcamp.applicant.infrastructure.eventlistener.eventbus.applicant;

import com.jalasoft.bootcamp.applicant.infrastructure.events.BootcampApplicantsUploadedEvent;
import com.jalasoft.bootcamp.applicant.infrastructure.common.RabbitMQConstants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class BootcampApplicantsUploadEventListener {

    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public BootcampApplicantsUploadEventListener(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleEvent(final BootcampApplicantsUploadedEvent event) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.APPLICATION_TOPIC_EXCHANGE.getName(),
                RabbitMQConstants.BOOTCAMP_QUEUE.getRoutingKey(), event);
    }
}
