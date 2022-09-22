import APIRESTConnectionFactory from '../APIRESTConnectionFactory';

async function logoutUser() {
  // eslint-disable-next-line no-unused-vars
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance(false);
  // eslint-disable-next-line no-unused-vars
  const url = 'authentication/logout';
  try {
    const response = await instance.get(url);
    return response;
  } catch (error) {
    console.log(error);
  }
}

export default logoutUser;
