import axios from "axios"

export async function workflowStagesFetch(id, callback) {
  try {
    // TODO(cristal.flores): API integration to get workflow stages.
    const response = await axios.get(`https://609421aaa7e53a00179524df.mockapi.io/workflow/1/stages`);
    callback(response.data);
  } catch (error) {
    console.log(error.response.data);
  }
}

const WorkflowStagesFetch = {
    workflowStagesFetch
}

export default WorkflowStagesFetch;