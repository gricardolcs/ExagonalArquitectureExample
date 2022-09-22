package com.jalasoft.bootcamp.bootcamp.infrastructure.eventpublisher.rabbitmq.applicant;

import com.jalasoft.bootcamp.bootcamp.infrastructure.common.RabbitMQConstants;
import com.jalasoft.bootcamp.bootcamp.infrastructure.events.ApplicantStageQualifiedEventList;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ApplicantStageQualifiedEventPublisher
{
    private final AmqpTemplate template;

    @Autowired
    public ApplicantStageQualifiedEventPublisher(AmqpTemplate template)
    {
        this.template = template;
    }

    /**
     * This event will be run once an applicant is qualified.
     *
     * @param applicantStageQualifiedEventList This is the event that will send applicants
     *                                         services.
     */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleEvent(
        final ApplicantStageQualifiedEventList applicantStageQualifiedEventList)
    {
        template.convertAndSend(
            RabbitMQConstants.APPLICATION_TOPIC_EXCHANGE.getName(),
            RabbitMQConstants.APPLICANT_QUEUE.getRoutingKey(),
            applicantStageQualifiedEventList.getApplicantStageQualifiedEvents());
    }
}
