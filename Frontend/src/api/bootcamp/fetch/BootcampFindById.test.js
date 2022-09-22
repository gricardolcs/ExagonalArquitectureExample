import '@testing-library/jest-dom/extend-expect'

import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';
import bootcampFindById from './BootcampFindById';

jest.mock('../../APIRESTConnectionFactory')

describe('Find a bootcamp tests', () => {

    const bootcampMock = {
        bootcampId: 1,
        name: 'Bootcamp Dev',
        description: 'Bootcamp #10 Dev- 2021',
        startDate: '2021-04-10',
        endDate: '2021-07-10',
        workflowType: 1,
    };

    test('Find a bootcamp', async () => {
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                get: () => Promise.resolve({ data: bootcampMock }),
            }
        });
        await bootcampFindById((data) => {
            expect(data).toEqual(bootcampMock);
        });
    });

    test("Find a bootcamp that doesn't exist", async () => {
        const messageError = "The bootcamp doesn't exist";
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                delete: () => Promise.reject({ response: { data: messageError } }),
            }
        });
        try {
            await bootcampFindById(bootcampMock.bootcampId);
        } catch (error) {
            expect(error.response.data).toEqual(messageError);
        }
    });
});
