import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function fetchAllUser(callback) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();

  const url = `authentication/user`;
  try {
    const response = await instance.get(url);
    callback(response.data);
  } catch (error) {
    console.log(error.response.data);
  }
}

export default fetchAllUser;
