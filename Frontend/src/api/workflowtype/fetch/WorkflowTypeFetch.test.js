import '@testing-library/jest-dom/extend-expect'

import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';
import { workflowFetch } from './WorkflowTypeFetch';

jest.mock('../../APIRESTConnectionFactory')

describe('Fetch WorkflowTypes tests', () => {

    const workflowType = {
        id: 1,
        name: 'development'
    }
    const workflowTypeBodyMock = [workflowType]

    test('Fetch WorkflowType', async () => {
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                get: () => Promise.resolve({ data: workflowTypeBodyMock }),
            }
        });
        await workflowFetch((data) => {
            expect(data).toEqual(workflowTypeBodyMock);
        });
    });

    test('Fetch empty', async () => {
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                get: () => Promise.resolve({ data: [] }),
            }
        });
        await workflowFetch((data) => {
            expect(data).toEqual([]);
        });
    });
});
