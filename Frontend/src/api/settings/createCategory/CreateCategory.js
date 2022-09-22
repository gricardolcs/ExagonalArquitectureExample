import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function createCategory(category, callback) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `settings/category`;
  try {
    return await instance.post(url, category);
  } catch (error) {
    console.log(error.response);
  }
}

export default createCategory;
