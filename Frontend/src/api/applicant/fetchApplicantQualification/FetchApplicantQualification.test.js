import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';
import fetchApplicantQualification from './FetchApplicantQualification';

jest.mock('../../APIRESTConnectionFactory');

describe("Fetch applicant qualification Tests", () => {

  test("Test 1: Fetch applicant qualification succesfully", async () => {
    const expectedResult = {
      stageId: 2,
      stageName: "Psychometric",
      comment: "best student",
      applicantQualification: {
        label: "Result",
        qualification: "1",
        type: "SINGLE_INPUT"
      },
      qualificationStatus: "PASSED"
    };
    APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({ data: expectedResult }),
      }
    });
    const response = await fetchApplicantQualification.fetch(1, 1, 1);
    expect(response).toEqual(expectedResult);
  });

  test("Test 2: Fetch applicant qualification with bad bootcamp id", async () => {
    const expectedResult = "The bootcamp doesn't exist";
    APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({data: expectedResult}),
      }
    });
    const response = await fetchApplicantQualification.fetch(1, 1, 1);
    expect(response).toEqual(expectedResult);
  });

  test("Test 3: Fetch applicant qualification with bad stage id", async () => {
    const expectedResult = "The stage doesn't exist";
    APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({data: expectedResult}),
      }
    });
    const response = await fetchApplicantQualification.fetch(1, 1, 1);
    expect(response).toEqual(expectedResult);
  });

  test("Test 4: Fetch applicant qualification that does not exist", async () => {
    const expectedResult = "Qualification stage does not exist";
    APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({data: expectedResult}),
      }
    });
    const response = await fetchApplicantQualification.fetch(1, 1, 1);
    expect(response).toEqual(expectedResult);
  });

});
