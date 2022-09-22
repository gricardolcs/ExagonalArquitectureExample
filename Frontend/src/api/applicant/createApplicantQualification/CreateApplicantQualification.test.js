import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';
import CreateApplicantQualification from './CreateApplicantQualification';

jest.mock('../../APIRESTConnectionFactory');

describe("Applicant qualification Tests", () => {

  test("Test 1: Create applicant qualification succesfully", async () => {
    // Mock Service
    const bodyResponse = {
      applicantId: 1,
      stageId: 2,
      qualificationStatus: "PASSED"
    };
    APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
      return {
        post: () => Promise.resolve({ data: bodyResponse }),
      }
    });
    // Create Request
    const bootcampId = 1;
    const stageId = 2;
    const applicantId = 2;
    const bodyRequest = {
      comment: "Applicant did good work.",
      qualificationStatus: "PASSED",
      qualificationType: "SINGLE_INPUT",
      schema: "{'type':'SINGLE_INPUT','label':'Assessment(0-5)','qualification':8}"
    };
    const response = await CreateApplicantQualification.create(
      bootcampId,
      stageId,
      applicantId,
      bodyRequest);
    // Assertion
    expect(response.data).toEqual(bodyResponse);
  });

  test("Test 2: Create applicant qualification with bad bootcamp id", async () => {
    const expectedResult = "The bootcamp doesn't exist";
    APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
      return {
        post: () => Promise.resolve(expectedResult),
      }
    });
    const response = await CreateApplicantQualification.create(1, 1, 1, {});
    expect(response).toEqual(expectedResult);
  });

  test("Test 3: Create applicant qualification with bad stage id", async () => {
    const expectedResult = "The stage doesn't exist";
    APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
      return {
        post: () => Promise.resolve(expectedResult),
      }
    });
    const response = await CreateApplicantQualification.create(1, 1, 1, {});
    expect(response).toEqual(expectedResult);
  });

});
