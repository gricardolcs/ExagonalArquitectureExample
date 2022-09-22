import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function createUser(user, callback) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `authentication/user`;
  try {
    const response = await instance.post(url, user);
    callback(response.data);
  } catch (error) {
    console.log(error.response.data);
  }
}

export default createUser;
