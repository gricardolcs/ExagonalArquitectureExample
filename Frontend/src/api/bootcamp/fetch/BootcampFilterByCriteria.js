import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export const bootcampFilterByCriteria = async (textToSearch, callback) => {
  const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `bootcamps/bootcamp/filter?criteria=${textToSearch}`;
  try {
    const response = await axiosInstance.get(url);
    callback(response.data);
  } catch (error) {
    console.log(error);
  }
}

export default bootcampFilterByCriteria;