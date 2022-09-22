import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export default async function updatePassword(data, id) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `authentication/user/${id}/update-password`;
  try {
    return await instance.patch(url, data);
  } catch (error) {
    return Promise.reject(error);
  }
}
