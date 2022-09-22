import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function workflowFetch(callback) {
  const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
  try {
    const response = await axiosInstance.get(`bootcamps/workflow`);
    callback(response.data);
  } catch (error) {
    console.log(error.response.data);
  }
}

const WorkflowTypeFetch = {
  workflowFetch,
}

export default WorkflowTypeFetch;