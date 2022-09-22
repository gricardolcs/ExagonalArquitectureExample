package com.jalasoft.bootcamp.applicant.infrastructure.configuration.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidQueryParamValueException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.DomainExceptionHandler;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.QueryParam;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class DomainExceptionHandlerTest
{

    @InjectMocks
    private DomainExceptionHandler domainExceptionHandler;

    @Test
    void testHandleInvalidQueryParamValue_thenReturnBadRequestWithMessage()
    {
        InvalidQueryParamValueException exception =
            new InvalidQueryParamValueException(QueryParam.CRITERIA,String.format(
                ErrorsUtil.ERROR_DESCRIPTION_INVALID_QUERY_PARAM,
                QueryParam.CRITERIA));

        ResponseEntity<Map<String, Object>> response = domainExceptionHandler.handleBaseException(
            exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}