package com.jalasoft.bootcamp.applicant.infrastructure.eventlistener.rabbitmq.applicant;

import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.applicant.infrastructure.common.RabbitMQConstants;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicantstagequalification.ApplicantStageQualificationDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.events.ApplicantStageQualifiedEvent;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicantstagequalification.update.ApplicantStageQualificationUseCase;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicantStageQualificationRabbitMQEventListener {

    private final ApplicantStageQualificationUseCase applicantStageQualificationUseCase;

    private static final Logger logger = LogManager.getLogger(ApplicantStageQualificationRabbitMQEventListener.class);

    @Autowired
    public ApplicantStageQualificationRabbitMQEventListener(
        ApplicantStageQualificationUseCase applicantStageQualificationUseCase) {
        this.applicantStageQualificationUseCase = applicantStageQualificationUseCase;
    }

    @RabbitListener(queues = RabbitMQConstants.APPLICANT_QUEUE_NAME)
    public void handleEvent(final List<ApplicantStageQualifiedEvent> events) {
        try {
            events.forEach(event -> {
                applicantStageQualificationUseCase.addOrUpdateApplicantStageQualified(
                    new ApplicantStageQualificationDTO(event.getStageId(), event.getStageName()
                        , event.getQualificationStatus(), event.getApplicantId()
                        , event.getBootcampId()));
            });
        } catch (EntityNotFoundException exception) {
            logger.error(exception);
            throw new AmqpRejectAndDontRequeueException(exception);
        }
    }
}
