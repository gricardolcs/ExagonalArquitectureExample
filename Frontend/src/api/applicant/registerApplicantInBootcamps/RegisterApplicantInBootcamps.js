import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function registerApplicantInBootcamps(applicantId, bootcampIds, callback) {
    const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
    const url = `applicants/register/${applicantId}/register-at-bootcamps/`
    try {
        const response = await instance.put(url, bootcampIds);
        callback(response.data);
    } catch (error) {
        console.log(error.response.data);
    }
}