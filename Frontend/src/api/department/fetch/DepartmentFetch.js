import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function departmentFetch(callback) {
    const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
    try {
      const response = await axiosInstance.get(`bootcamps/department`);
      callback(response.data);
    } catch (error) {
      console.log(error.response.data);
    }
  }
  
  const DepartmentFetch = {
    departmentFetch,
  }
  
  export default DepartmentFetch;