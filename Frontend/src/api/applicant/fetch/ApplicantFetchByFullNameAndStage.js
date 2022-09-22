import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function getApplicantsPageByFullNameAndStages(bootcampId, fullName, stages, page, size, callback) {
    const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
    const url = `applicants/applicant/filter?bootcampId=${bootcampId}`;

    // (TODO: @Ana Rocabado) These api is already working with filters, 
    // this hard code lines must be removed once searching logic get done in client side 

    let filters = [
        {
        attribute: 'fullName',
        values: [fullName]
      },
      {
        attribute: 'stage',
        values: [stages.toString()]
      },
    ];
    try {
        const response = await instance.post(url, filters);
        callback(response.data);
    } catch (error) {
        throw new Error(error.response.data);
    }
}

const ApplicantFetchByFullNameAndStage = {
    getApplicantsPageByFullNameAndStages
}

export default ApplicantFetchByFullNameAndStage;