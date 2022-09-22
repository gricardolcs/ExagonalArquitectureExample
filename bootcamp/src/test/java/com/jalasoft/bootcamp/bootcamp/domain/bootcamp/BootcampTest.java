package com.jalasoft.bootcamp.bootcamp.domain.bootcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidArgumentsEntityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BootcampTest
{

    private static Long bootcampId;
    private static String bootcampName;
    private static String bootcampDescription;
    private static String bootcampLocation;
    private static LocalDate bootcampStartDate;
    private static LocalDate bootcampEndDate;
    private static int workflowType;
    private static Long workflowId;
    private static int department;
    private static Long departmentId;
    private static Integer bootcampParticipantsExpectedQuantity;
    private static Set<Long> acceptedParticipants;
    private static int projectType;
    private static Long projectTypeId;

    @BeforeAll
    static void setUp()
    {
        bootcampId = UUID.randomUUID().getLeastSignificantBits();
        bootcampName = "Bolivian Bootcamp";
        bootcampDescription = "First Bolivian Bootcamp";
        bootcampLocation = "Av. Melchor Perez #123";
        bootcampStartDate = LocalDate.of(2021, Month.APRIL, 20);
        bootcampEndDate = LocalDate.of(2021, Month.OCTOBER, 20);
        workflowType = 1;
        workflowId = 1L;
        department = 1;
        departmentId = 1L;
        bootcampParticipantsExpectedQuantity = 30;
        acceptedParticipants = new HashSet<>();
        projectType = 1;
        projectTypeId = 1L;
    }

    @Test
    void testValidBootcampCreation()
    {
        Bootcamp bootcamp = new Bootcamp(bootcampId, bootcampName, bootcampDescription
            , bootcampLocation, bootcampStartDate, bootcampEndDate, workflowType, workflowId
            , department, departmentId, bootcampParticipantsExpectedQuantity, acceptedParticipants
            , projectType, projectTypeId, Collections.emptySet());

        String expectedName = "Bolivian Bootcamp";
        String expectedDescription = "First Bolivian Bootcamp";
        LocalDate expectedStartDate = LocalDate.of(2021, Month.APRIL, 20);
        LocalDate expectedEndDate = LocalDate.of(2021, Month.OCTOBER, 20);
        int expectedWorkflowType = 1;
        int expectedDepartment = 1;
        int expectedProjectType = 1;

        assertEquals(bootcampId, bootcamp.getId());
        assertEquals(expectedName, bootcamp.getName());
        assertEquals(expectedDescription, bootcamp.getDescription());
        assertEquals(expectedStartDate, bootcamp.getBootcampStartDate());
        assertEquals(expectedEndDate, bootcamp.getBootcampEndDate());
        assertEquals(expectedWorkflowType, bootcamp.getWorkflowType());
        assertEquals(expectedDepartment, bootcamp.getDepartment());
        assertEquals(expectedProjectType, bootcamp.getProjectType());
    }

    @Test
    void testInvalidStartDateException()
    {
        LocalDate newBootcampStartDate = LocalDate.of(
            2021, Month.OCTOBER, 21);

        Assertions.assertThrows(InvalidArgumentsEntityException.class, () -> {
            new Bootcamp(bootcampId, bootcampName, bootcampDescription, bootcampLocation
                , newBootcampStartDate, bootcampEndDate, workflowType, workflowId
                , department, departmentId, bootcampParticipantsExpectedQuantity
                , acceptedParticipants, projectType, projectTypeId
                , Collections.emptySet());
        });
    }

    @Test
    void testInvalidOneYearFutureStartDateException()
    {
        LocalDate futureBootcampStartDate = LocalDate.of(
            2022, Month.OCTOBER, 20);

        Assertions.assertThrows(InvalidArgumentsEntityException.class, () -> {
            new Bootcamp(bootcampId, bootcampName, bootcampDescription, bootcampLocation
                , futureBootcampStartDate, bootcampEndDate, workflowType, workflowId
                , department, departmentId, bootcampParticipantsExpectedQuantity
                , acceptedParticipants, projectType, projectTypeId
                , Collections.emptySet());
        });
    }

    @Test
    void testValidChangeBootcampAttributes()
    {
        Bootcamp bootcamp = new Bootcamp(bootcampId, bootcampName, bootcampDescription
            , bootcampLocation, bootcampStartDate, bootcampEndDate
            , workflowType, workflowId, department, departmentId
            , bootcampParticipantsExpectedQuantity, acceptedParticipants
            , projectType, projectTypeId, Collections.emptySet());

        LocalDate changedStartDate = LocalDate.of(2021, Month.JUNE, 20);
        LocalDate changedEndDate = LocalDate.of(2021, Month.DECEMBER, 20);

        bootcamp.changeName("Colombian Bootcamp");
        bootcamp.changeDescription("First Colombian Bootcamp");
        bootcamp.changeStartDate(changedStartDate);
        bootcamp.changeEndDate(changedEndDate);
        bootcamp.changeWorkflowType(2);
        bootcamp.changeDepartment(2);
        bootcamp.changeProjectType(2);

        String expectedChangedName = "Colombian Bootcamp";
        String expectedChangedDescription = "First Colombian Bootcamp";
        int expectedChangedWorkflowType = 2;
        int expectedChangedDepartment = 2;
        int expectedChangedProjectType = 2;

        assertEquals(expectedChangedName, bootcamp.getName());
        assertEquals(
            expectedChangedDescription,
            bootcamp.getDescription());
        assertEquals(changedStartDate, bootcamp.getBootcampStartDate());
        assertEquals(changedEndDate, bootcamp.getBootcampEndDate());
        assertEquals(expectedChangedWorkflowType, bootcamp.getWorkflowType());
        assertEquals(expectedChangedDepartment, bootcamp.getDepartment());
        assertEquals(expectedChangedProjectType, bootcamp.getProjectType());
    }
}