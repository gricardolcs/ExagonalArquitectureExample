package com.jalasoft.bootcamp.bootcamp.infrastructure.eventlistener.rabbitmq.bootcamp;

import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.bootcamp.infrastructure.common.RabbitMQConstants;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampApplicantAssociationDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.events.BootcampApplicantAssociationEvent;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.manipulation.BootcampApplicantAssociationUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BootcampApplicantsAssociationRabbitMQEventListener
{
    private final BootcampApplicantAssociationUseCase bootcampRegistrationUseCase;

    private static final Logger logger = LogManager.getLogger(
        BootcampApplicantsAssociationRabbitMQEventListener.class);

    @Autowired
    public BootcampApplicantsAssociationRabbitMQEventListener(
        BootcampApplicantAssociationUseCase bootcampApplicantAssociationUseCase)
    {
        this.bootcampRegistrationUseCase = bootcampApplicantAssociationUseCase;
    }

    @RabbitListener(queues = RabbitMQConstants.BOOTCAMP_QUEUE_NAME)
    public void handleEvent(final BootcampApplicantAssociationEvent event)
    {
        try
        {
            bootcampRegistrationUseCase.associate(new BootcampApplicantAssociationDTO(
                event.getBootcampId(), event.getApplicantIds()));
        }
        catch (EntityNotFoundException e)
        {
            logger.error(e.getMessage());
            throw new AmqpRejectAndDontRequeueException(e.getMessage());
        }
    }
}
