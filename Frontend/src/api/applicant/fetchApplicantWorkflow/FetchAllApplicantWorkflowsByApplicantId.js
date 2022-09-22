import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function fetchAllApplicantWorkflowsByApplicantId(applicantId, callBack) {
    const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
    try {
        const response = await axiosInstance.get(`bootcamps/applicant/${applicantId}/workflow`);
        callBack(response.data);
    } catch (error) {
        console.log(error.response.data);
    }
}

const FetchAllApplicantWorkflowsByApplicantId = {
    fetchAllApplicantWorkflowsByApplicantId,
}

export default FetchAllApplicantWorkflowsByApplicantId;