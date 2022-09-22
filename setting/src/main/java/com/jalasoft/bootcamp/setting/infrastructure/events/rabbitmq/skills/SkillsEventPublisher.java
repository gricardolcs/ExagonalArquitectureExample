package com.jalasoft.bootcamp.setting.infrastructure.events.rabbitmq.skills;

import com.jalasoft.bootcamp.setting.infrastructure.common.RabbitMQConstants;
import java.util.List;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class SkillsEventPublisher
{
    private final AmqpTemplate template;

    @Autowired
    public SkillsEventPublisher(final AmqpTemplate template)
    {
        this.template = template;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleEvent(
        final List<String> skills)
    {
        template.convertAndSend(
            RabbitMQConstants.APPLICATION_TOPIC_EXCHANGE.getName(),
            RabbitMQConstants.SKILL_QUEUE.getRoutingKey(),
            skills);
    }
}
