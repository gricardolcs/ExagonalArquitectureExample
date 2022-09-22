import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export default async function updateExpirationDays(
  id,
  passwordExpirationInDays
) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `settings/configurations/users/expiration/${id}/${passwordExpirationInDays}`;
  try {
    return await instance.patch(url);
  } catch (error) {
    return Promise.reject(error);
  }
}
