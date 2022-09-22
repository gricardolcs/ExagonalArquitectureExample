import '@testing-library/jest-dom/extend-expect';

import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';
import applicantFetchShortProfile from './ApplicantFetchShortProfile';

jest.mock('../../APIRESTConnectionFactory');

describe('Fetch applicants tests', () => {
  const applicantMock1 = {
    applicantId: 1,
    fullName: "Luis Mercado",
    email: "luis.mercado@email.com",
    telephone: "7695268",
    city: "La paz",
    country: "Bolivia",
    career: "System Engineering",
  };

  const applicantMock2 = {
    applicantId: 1,
    fullName: "Maria Delgado",
    email: "maria.delgado@email.com",
    telephone: "76954532",
    city: "Cali",
    country: "Colombia",
    career: "Informatics Engineering",
  };
  const applicantBodyMock = [applicantMock1, applicantMock2];

  test('Fetch applicants', async () => {
    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({ data: applicantBodyMock }),
      };
    });
    await applicantFetchShortProfile((data) => {
      expect(data).toEqual(applicantBodyMock);
    });
  });

  test('Fetch applicants empty', async () => {
    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({ data: [] }),
      };
    });
    await applicantFetchShortProfile((data) => {
      expect(data).toEqual([]);
    });
  });
});
