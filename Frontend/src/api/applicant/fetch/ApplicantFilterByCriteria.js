import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export const applicantFilterByCriteria = async (textToSearch, callback) => {
  const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `applicants/applicant/filter`;

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
    const response = await axiosInstance.post(url, filters);
    callback(response.data);
  } catch (error) {
    console.log(error);
  }
}

export default applicantFilterByCriteria;