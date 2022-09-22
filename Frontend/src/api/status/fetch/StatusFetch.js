import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function statusFetch(callback) {
    const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
    try {
      const response = await axiosInstance.get(`bootcamps/statuses`);
      callback(response.data);
    } catch (error) {
      console.log(error.response.data);
    }
  }
  
  const StatusFetch = {
    statusFetch,
  }
  
  export default StatusFetch;