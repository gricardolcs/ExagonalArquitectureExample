import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function projectTypeFetch(callback) {
    const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
    try {
      const response = await axiosInstance.get(`bootcamps/projectType`);
      callback(response.data);
    } catch (error) {
      console.log(error.response.data);
    }
  }
  
  const ProjectTypeFetch = {
    projectTypeFetch,
  }
  
  export default ProjectTypeFetch;