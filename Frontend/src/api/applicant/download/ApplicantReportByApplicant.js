import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function fetchApplicantReportById(applicantId, bootcampId, callback) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `applicants/applicant/${applicantId}/bootcamp/${bootcampId}/download`;
  
  try {
    const profile = await instance.get(url);
    callback(profile.data);
  } catch (error) {
    console.log(error.response);
  }
}

const ApplicantReportByApplicant = {
    fetchApplicantReportById,
}

export default ApplicantReportByApplicant;