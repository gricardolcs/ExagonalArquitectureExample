import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function registerExistingApplicantsInBootcamp(bootcampId, applicantIds, callback) {
    const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
    const url = `applicants/register/applicants-in-bootcamp/${bootcampId}`
    try {
        const response = await instance.put(url, applicantIds);
        callback(response.data);
    } catch (error) {
        console.log(error.response.data);
    }
}