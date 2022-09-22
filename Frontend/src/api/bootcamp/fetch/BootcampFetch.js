import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';

const bootcampFetch = async (callback) => {
  const url = "bootcamps/bootcamp";
  try {
    const response = await createBasicAxiosInstance().get(url);
    callback(response.data);
  } catch (error) {
    console.log(error);
  }
}

export default bootcampFetch;
