import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function categoryFetch(callback) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `settings/category`;
  try {
    const response = await instance.get(url);
    callback(response.data);
  } catch (error) {
    console.log(error.response);
  }
}
export default categoryFetch;
