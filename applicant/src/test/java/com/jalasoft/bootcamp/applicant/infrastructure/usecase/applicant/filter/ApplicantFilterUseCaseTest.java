package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.infrastructure.utils.Filter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@ExtendWith(MockitoExtension.class)
class ApplicantFilterUseCaseTest {
    @Mock
    MongoTemplate mongoTemplate;

    @Test
    public void shouldBeEmptyResult() {
        Criteria criteria = new Criteria().orOperator(
            Criteria.where("skill").regex("No Lang", "i")
        );
        Query query = new Query(criteria);

        lenient().doReturn(Collections.emptyList()).when(mongoTemplate).find(query,
            Applicant.class);

        Filter filter = new Filter("skill", Arrays.asList("No Lang"));
        ApplicantFilterUseCase sut = new ApplicantFilterUseCase(mongoTemplate);
        List<Applicant> result = sut.getApplicantsByFiltering(null, filter);

        assertEquals(0, result.size());
    }
}
