import '@testing-library/jest-dom/extend-expect'
import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';
import { updateBootcamp } from './BootcampUpdate';

jest.mock('../../APIRESTConnectionFactory');
describe('BootcampUpdate', () => {
    const id = 1;
    const bootcamp = {
        name: 'Teach Elevator',
        description: 'We offer a coding bootcamp dedicated to preparing students.',
        startDate: '2021-03-15',
        endDate: '2021-06-15',
        workflowType: 1,
    };
    test('Updating a valid Bootcamp', async () => {
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                put: () => Promise.resolve({ data: { id, ...bootcamp } })
            }
        });
        const response = await updateBootcamp(id, bootcamp);
        expect(response).toEqual({ id, ...bootcamp });
    });

    test('Updating a non-existent Bootcamp', async () => {
        const messageError = "The bootcamp doesn't exist";
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                put: () => Promise.reject({ response: { data: new Error(messageError) } })
            }
        });
        expect(await updateBootcamp.bind(id, bootcamp)).rejects.toThrow(messageError);
    })

    test('Updating a Bootcamp with existing name', async () => {
        const messageError = `The Bootcamp with name: ${bootcamp.name} already exist`;
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                put: () => Promise.reject({ response: { data: new Error(messageError) } })
            }
        });
        expect(await updateBootcamp.bind(id, bootcamp)).rejects.toThrow(messageError);
    })
})