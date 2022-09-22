import '@testing-library/jest-dom/extend-expect'
import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';
import { fetchApplicantById } from './ApplicantFetchById';

jest.mock('../../APIRESTConnectionFactory');

describe("Applicant profile fetch by id Tests", () => {

  const applicantMock = {
    applicantId: -456412335,
    fullName: 'Maria Orellana',
    phone: '75948789',
    email: 'maria.orellana@gmail.com',
    birthday: '23/03/1995',
    career: 'Ing sistemas',
    image: ''
  };
  test("Should get the applicant when fetch by id", async () => {
    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({ data: applicantMock }),
      }
    });
    await fetchApplicantById(applicantMock.applicantId, (response) => {
      expect(response).toEqual(applicantMock);
    });
  });
  test("Should get a message when the applicantId is incorrect", async () => {
    const messageError = "The applicant doesn't exist";
    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.reject({ response: { data: messageError } }),
      }
    });
    try {
      await fetchApplicantById(applicantMock.applicantId);
    } catch (error) {
      expect(error.response.data).toEqual(messageError);
    }
  });
})