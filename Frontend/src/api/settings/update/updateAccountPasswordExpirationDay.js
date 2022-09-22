import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export default async function updateAccountPasswordExpirationDay(
  passwordExpirationInDays
) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `authentication/user/update-account-password/${passwordExpirationInDays}`;
  try {
    return await instance.patch(url);
  } catch (error) {
    return Promise.reject(error);
  }
}
