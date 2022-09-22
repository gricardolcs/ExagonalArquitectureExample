import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function createApplicant(applicant, callback) {
    const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
    const url = `applicants/applicant`
    try {
        const response = await instance.post(url, applicant);
        callback(response.data)
    } catch (error) {
        console.log(error.response.data);
    }
}

export default createApplicant;