import '@testing-library/jest-dom/extend-expect'

import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';
import bootcampFetch from './BootcampFetch';

jest.mock('../../APIRESTConnectionFactory')

describe('Fetch bootcamps tests', () => {

    const bootcampMock = {
        bootcampId: 1,
        name: 'Bootcamp Dev',
        description: 'Bootcamp #10 Dev- 2021',
        startDate: '2021-04-10',
        endDate: '2021-07-10',
        workflowType: 1,
    };
    const bootcampBodyMock = [bootcampMock]

    test('Fetch bootcamps', async () => {
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                get: () => Promise.resolve({ data: bootcampBodyMock }),
            }
        });
        await bootcampFetch((data) => {
            expect(data).toEqual(bootcampBodyMock);
        });
    });

    test('Fetch empty', async () => {
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                get: () => Promise.resolve({ data: [] }),
            }
        });
        await bootcampFetch((data) => {
            expect(data).toEqual([]);
        });
    });
});
