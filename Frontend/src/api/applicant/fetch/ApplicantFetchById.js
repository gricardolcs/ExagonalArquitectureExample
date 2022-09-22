import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function fetchApplicantById(id, callback) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = 'applicants/applicant';
  try {
    const profile = await instance.get(`${url}/${id}`);
    callback(profile.data);
  } catch (error) {
    console.log(error.response.data);
  }
}

const ApplicantFetchById = {
  fetchApplicantById,
}

export default ApplicantFetchById;