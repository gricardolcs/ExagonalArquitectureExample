import '@testing-library/jest-dom/extend-expect'
import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';
import { deleteBootcamp } from './BootcampDelete';

jest.mock('../../APIRESTConnectionFactory')
describe('bootcampDelete tests', () => {
    const bootcampName = 'Bootcamp DevOps';
    const bootcampMock = {
        bootcampId: 1,
        name : 'Bootcamp DevOps',
        description: 'Bootcamp #02 DevOps - 2021',
        startDate: '2021-04-10',
        endDate: '2021-07-10',
        workflowType: 1,
    };
    test('Delete a correct bootcamp with id', async () => {
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                delete:() => Promise.resolve({ data: bootcampName }),
            }
        });
        const response = await deleteBootcamp(bootcampMock.bootcampId);
        expect(response.data).toEqual(bootcampName);
    });

    test('Delete a incorrect bootcamp with id', async () => {
        const messageError = "The bootcamp doesn't exist";
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                delete: () => Promise.reject({ response: { data: messageError } }),
            }
        });
        try {
            await deleteBootcamp(bootcampMock.bootcampId);  
        } catch (error) {
            expect(error.response.data).toEqual(messageError);
        }
    });
});