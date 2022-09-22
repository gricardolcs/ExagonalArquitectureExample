import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';
import BootcampCreate from './BootcampCreate';

jest.mock('../../APIRESTConnectionFactory');

describe("Bootcamp Creation Tests", () => {
  test("should get the id when creates a bootcamp succesfully", async () => {
    expect.assertions(1);
    const mockBootcamp = {
      name: 'Bolivian bootcamp',
      startDate: '2021-06-20',
      endDate: '2021-12-20',
      workflowType: 1,
      description: 'First Bolivian bootcamp',
    };

    const returnedId = 1029384756;
    APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
      return {
        post: () => Promise.resolve({ data: returnedId }),
      }
    });

    const response = await BootcampCreate.createBootcamp(mockBootcamp);
    expect(response.data).toEqual(returnedId);
  });

  test("should get a message when a bootcamp does not have a name", async () => {
    const mockBootcamp = {
      startDate: '2021-06-20',
      endDate: '2021-12-20',
      workflowType: 1,
      description: 'First Bolivian bootcamp',
    };

    const messageError = "Bootcamp should have a name";
    APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
      return {
        post: () => Promise.reject({ response: { data: messageError } }),
      }
    });
    try {
      await BootcampCreate.createBootcamp(mockBootcamp);
    } catch (error) {
      expect(error.response.data).toEqual(messageError);
    }
  });

  test("should get a message when a creates a new bootcamp with name that already exists", async () => {
    const mockBootcamp = {
      name: 'Bolivian bootcamp',
      startDate: '2021-06-20',
      endDate: '2021-12-20',
      workflowType: 1,
      description: 'First Bolivian bootcamp',
    };

    const messageError = "Bootcamp with name Bolivian bootcamp already exists";
    APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
      return {
        post: () => Promise.reject({ response: { data: messageError } }),
      }
    });
    try {
      await BootcampCreate.createBootcamp(mockBootcamp);
    } catch (error) {
      expect(error.response.data).toEqual(messageError);
    }
  });
});
