import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function fetch(
  bootcampId,
  stageId,
  applicantId,
) {
  const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
  try {
    const response = await axiosInstance.get(
      `bootcamps/bootcamp/${bootcampId}/applicant/${applicantId}/stage/${stageId}/qualification`
      );
    return response.data;  
  } catch (error) {
    console.log(error.response.data);
  }
}

const FetchApplicantQualification = {
  fetch,
}

export default FetchApplicantQualification;
