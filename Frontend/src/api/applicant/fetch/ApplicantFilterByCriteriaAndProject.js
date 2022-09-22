import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export const applicantFilterByCriteriaAndProject = async (textToSearch, callback) => {
  const projectId = sessionStorage.getItem("currentProjectId");
  const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `applicants/applicant/filter?bootcampId=${projectId}`;

  // (TODO: @Ana Rocabado) These api is already working with filters, 
  // this hard code lines must be removed once searching logic get done in client side 

  // SAME WITH SEARCH AND FILTERING BY STAGES, API ENDPOINT IS READY TO GET STAGES AS FILTER VALUES

  let filters = [
      {
      attribute: 'fullName',
      values: [textToSearch]
    }
  ]; 

  try {
    const response = await axiosInstance.post(url,filters);
    callback(response.data);
  } catch (error) {
    console.log(error);
  }
}

export default applicantFilterByCriteriaAndProject;