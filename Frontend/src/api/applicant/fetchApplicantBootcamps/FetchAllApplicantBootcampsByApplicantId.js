import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function fetchAllApplicantBootcampsByApplicantId(applicantId, callBack) {
    const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
    try {
        const response = await axiosInstance.get(`bootcamps/applicant/${applicantId}/bootcamp`);
        callBack(response.data);
    } catch (error) {
        console.log(error.response.data);
    }
}

const FetchAllApplicantBootcampsByApplicantId = {
    fetchAllApplicantBootcampsByApplicantId,
}

export default FetchAllApplicantBootcampsByApplicantId;