import axios from 'axios';
import { createBasicAxiosInstance } from './APIRESTConnectionFactory';

const { REACT_APP_BACKEND_PROTOCOL, REACT_APP_BACKEND_HOST, REACT_APP_BACKEND_PORT } = process.env;

jest.mock('axios')
describe('UrlFactory', () => {
    test('create a basic instance of axios', () => {
        const URLExpected = `${REACT_APP_BACKEND_PROTOCOL}://${REACT_APP_BACKEND_HOST}:${REACT_APP_BACKEND_PORT}/`;
        createBasicAxiosInstance()
        expect(axios.create).toBeCalledWith(expect.objectContaining({ baseURL: URLExpected }));
    })
})