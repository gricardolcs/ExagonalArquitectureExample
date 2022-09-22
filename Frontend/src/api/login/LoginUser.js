import APIRESTConnectionFactory from '../APIRESTConnectionFactory';

export async function loginUser(user, correctDataFunction, wrongDataFunction) {
  // eslint-disable-next-line no-unused-vars
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance(false);
  // eslint-disable-next-line no-unused-vars
  const url = 'authentication/login';
  try {
    const response = await instance.post(url, user);
    correctDataFunction(response.data);
  } catch (error) {
    console.log(error.response.data);
    wrongDataFunction();
  }
}

export default loginUser;
