import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';

export const userFindById = async (id, callback) => {
  const url = `authentication/user/${id}`;
  try {
    const response = await createBasicAxiosInstance().get(url);
    callback(response.data);
  } catch (error) {
    console.log(error);
  }
};

export default userFindById;
