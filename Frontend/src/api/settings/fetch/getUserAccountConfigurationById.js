import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function getUserAccountConfigurationById(id) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance(false);
  const url = `settings/configurations/users/${id}`;
  try {
    const response = await instance.get(url);
    return response.data;
  } catch (error) {
    console.error(error);
  }
}

export default getUserAccountConfigurationById;
