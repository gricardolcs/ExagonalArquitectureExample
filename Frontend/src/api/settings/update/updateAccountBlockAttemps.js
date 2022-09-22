import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export default async function updateAccountBlockAttemps(id, blockAttempts) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `settings/configurations/users/account-block-attempts/${id}/${blockAttempts}`;
  try {
    return await instance.patch(url);
  } catch (error) {
    return Promise.reject(error);
  }
}
