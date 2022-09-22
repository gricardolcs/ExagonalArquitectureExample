import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function create(
  bootcampId,
  stageId,
  applicantId,
  requestBody
) {
  const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
  try {
    return await axiosInstance.post(
      `bootcamps/bootcamp/${bootcampId}/stage/${stageId}/applicant/${applicantId}/grade`,
      requestBody);
  } catch (error) {
    console.log(error.response.data);
  }
}

const CreateApplicantQualification = {
  create,
}

export default CreateApplicantQualification;
