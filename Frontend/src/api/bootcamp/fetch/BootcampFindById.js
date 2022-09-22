import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';

export const bootcampFindById = async (id, callback) => {
  const url = `bootcamps/bootcamp/${id}`;
  try {
    const response = await createBasicAxiosInstance().get(url);
    callback(response.data);
  } catch (error) {
    console.log(error);
  }
}

export default bootcampFindById;
