package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.filter;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.infrastructure.utils.Filter;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidQueryParamValueException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.CriteriaValidatorUtils;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class ApplicantFilterUseCase
{
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ApplicantFilterUseCase(final MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Applicant> getApplicantsByFiltering(Long bootcampID, Filter filter)
    {
        if (filter.getAttribute().equals(Field.FULL_NAME))
        {
            filter.getValues().stream()
                .forEach(value -> CriteriaValidatorUtils.validateCriteria(value));
        }
        try
        {
            String fieldQuery = filter.getAttribute();
            List<Criteria> criterias = filter.getValues()
                .stream()
                .map(
                    item -> Criteria.where(fieldQuery).regex(item, "i")
                ).collect(Collectors.toList());

            Criteria fullCriteria;
            if (bootcampID != null) {
                fullCriteria = new Criteria().andOperator(
                    Criteria.where(Field.INCLUDED_BOOTCAMPS).is(bootcampID),
                    new Criteria().orOperator(
                        criterias.toArray(new Criteria[0])
                    )
                );
            } else {
                fullCriteria = new Criteria().orOperator(
                    criterias.toArray(new Criteria[0])
                );
            }

            return mongoTemplate.find(new Query(fullCriteria), Applicant.class);
        }

        catch (Exception exception)
        {
            exception.printStackTrace();
            throw new InvalidQueryParamValueException(filter.getAttribute(), String.format(
                ErrorsUtil.ERROR_DESCRIPTION_INVALID_QUERY_PARAM,
                filter.getAttribute()));
        }
    }
}
