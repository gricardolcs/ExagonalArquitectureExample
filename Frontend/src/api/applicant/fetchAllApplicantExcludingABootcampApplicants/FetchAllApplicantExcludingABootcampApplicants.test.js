import { createBasicAxiosInstance } from "../../APIRESTConnectionFactory";
import { getApplicantsExceptBootcampApplicants } from "./FetchAllApplicantExcludingABootcampApplicants";

jest.mock("../../APIRESTConnectionFactory");

describe("Fetch All Applicant Excluding A Bootcamp Applicants", () => {
  test("It should response successfully with an applicant list exclude of the bootcamp applicants", async () => {
    const promiseData = [
      {
        fullName: "Martina Lopez",
        country: "Bolivia",
        skills: []
      },
      {
        fullName: "Martina Garcias",
        country: "Colombia",
        skills: []
      }
    ];

    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () =>
          Promise.resolve({
            data: promiseData,
          }),
      };
    });
    await getApplicantsExceptBootcampApplicants(-1, '', '', (data) => {
      expect(data).toEqual(promiseData);
    });
  });

  test("It should response successfully with an ascending applicant list exclude of the bootcamp applicants", async () => {
    const promiseData = [
      {
        fullName: "Martina Lopez",
        country: "Bolivia",
        skills: []
      },
      {
        fullName: "Martina Garcias",
        country: "Colombia",
        skills: []
      }
    ];

    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () =>
          Promise.resolve({
            data: promiseData,
          }),
      };
    });
    await getApplicantsExceptBootcampApplicants(-1, 'location.country,ASC', '', (data) => {
      expect(data).toEqual(promiseData);
    });
  });

  test("It should response successfully with an descending applicant list exclude of the bootcamp applicants", async () => {
    const promiseData = [
      {
        fullName: "Martina Garcias",
        country: "Colombia",
        skills: []
      },
      {
        fullName: "Martina Lopez",
        country: "Bolivia",
        skills: []
      }
    ];

    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () =>
          Promise.resolve({
            data: promiseData,
          }),
      };
    });
    await getApplicantsExceptBootcampApplicants(-1, 'location.country,ASC', '', (data) => {
      expect(data).toEqual(promiseData);
    });
  });

  test("It should response successfully with an applicant list exclude of the bootcamp applicants by Criteria", async () => {
    const promiseData = [
      {
        fullName: "Martina Lopez",
        country: "Bolivia",
        skills: []
      }
    ];

    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () =>
          Promise.resolve({
            data: promiseData,
          }),
      };
    });
    await getApplicantsExceptBootcampApplicants(-1, '', 'L', (data) => {
      expect(data).toEqual(promiseData);
    });
  });
})