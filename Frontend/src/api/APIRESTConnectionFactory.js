import axios from 'axios';

const {
  REACT_APP_BACKEND_PROTOCOL,
  REACT_APP_BACKEND_HOST,
  REACT_APP_BACKEND_PORT,
} = process.env;

export function createBasicAxiosInstance(requireAuthorization = true) {
  const config = {
    baseURL: `${REACT_APP_BACKEND_PROTOCOL}://${REACT_APP_BACKEND_HOST}:${REACT_APP_BACKEND_PORT}/`,
  };

  if (requireAuthorization) {
    const token = sessionStorage.getItem('token');
    config.headers = {
      Authorization: `Bearer ${token}`,
      SessionSecurity: 'yes',
    };
  }

  return axios.create(config);
}

const APIRESTConnectionFactory = {
  createBasicAxiosInstance,
};

export default APIRESTConnectionFactory;
