import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';

const fetchAllApplicantShortProfile = async (callback) => {
  const url = 'applicants/applicant';
  try {
    const response = await createBasicAxiosInstance().get(url);
    callback(response.data);
  } catch (error) {
    console.log(error);
  }
}

export default fetchAllApplicantShortProfile;