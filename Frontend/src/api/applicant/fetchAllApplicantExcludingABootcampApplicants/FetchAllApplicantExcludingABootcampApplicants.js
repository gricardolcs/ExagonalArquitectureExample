import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function getApplicantsExceptBootcampApplicants(
  bootcampId,
  sort,
  criteria,
  callback
) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `applicants/applicant/excluding-bootcamp-applicants/${bootcampId}?sort=${sort}&criteria=${criteria}`;
  try {
    const response = await instance.get(url);
    callback(response.data);
  } catch (error) {
    console.log(error.response);
  }
}

const FetchAllApplicantExcludingABootcampApplicants = {
  getApplicantsExceptBootcampApplicants,
};

export default FetchAllApplicantExcludingABootcampApplicants;
