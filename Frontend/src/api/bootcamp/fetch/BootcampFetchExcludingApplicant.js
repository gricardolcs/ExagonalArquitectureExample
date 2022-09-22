import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function getBootcampsExceptBootcampsOfApplicant(
  applicantId = '',
  callback = () =>{},
  criteria = '',
  sort = ''
) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `bootcamps/bootcamp/excluding-applicant-bootcamps/${applicantId}?sort=${sort}&nameCriteria=${criteria}`;
  try {
    const response = await instance.get(url);
    callback(response.data);
  } catch (error) {
    console.log(error.response.data);
  }
}

const BootcampFetchExcludingApplicant = {
  getBootcampsExceptBootcampsOfApplicant,
};

export default BootcampFetchExcludingApplicant;
