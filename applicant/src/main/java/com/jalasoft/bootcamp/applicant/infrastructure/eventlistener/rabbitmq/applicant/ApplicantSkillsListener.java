package com.jalasoft.bootcamp.applicant.infrastructure.eventlistener.rabbitmq.applicant;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.infrastructure.common.RabbitMQConstants;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantFetchUseCase;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.manipulation.ApplicantHandleUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class ApplicantSkillsListener
{
    private final ApplicantFetchUseCase applicantFetchUseCase;
    private final ApplicantHandleUseCase applicantHandleUseCase;
    private final MongoTemplate mongoTemplate;

    private static final Logger logger = LogManager.getLogger(ApplicantSkillsListener.class);

    @Autowired
    public ApplicantSkillsListener(
        final ApplicantFetchUseCase applicantFetchUseCase,
        final ApplicantHandleUseCase applicantHandleUseCase,
        final MongoTemplate mongoTemplate)
    {
        this.applicantFetchUseCase = applicantFetchUseCase;
        this.applicantHandleUseCase = applicantHandleUseCase;
        this.mongoTemplate = mongoTemplate;
    }

    @RabbitListener(queues = RabbitMQConstants.SKILL_QUEUE_NAME)
    public void handleEvent(final List<String> events)
    {
        try
        {
            List<ApplicantDTO> applicantsDTO = applicantFetchUseCase.fetchAll();
            Set<String> definedSkills = new HashSet<>(events);
            for (ApplicantDTO applicantDTO : applicantsDTO)
            {
                Query query = new Query(Criteria.where(Field.ID.toLowerCase())
                    .is(applicantDTO.getId()));
                Update updateEntity = new Update();
                String cvContent = applicantDTO.getCurriculum().getContent();
                updateEntity.set(
                    Field.SKILL,
                    definedSkills.stream().filter(skill -> cvContent.contains(skill))
                        .collect(Collectors.toList()));
                mongoTemplate.updateFirst(query, updateEntity, Applicant.class);
            }
        }
        catch (EntityNotFoundException exception)
        {
            logger.error(exception);
            throw new AmqpRejectAndDontRequeueException(exception);
        }
    }
}
