import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function uploadCVFile(applicantId, file, callback) {
    const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
    const url = `applicants/applicant/${applicantId}/cv`;
    try {
        const formData = new FormData();
        formData.append('file', file);
        const response = await instance.post(url, formData, {
            headers: {
                'content-type': 'multipart/form-data'
            }
        });
        return callback(response.data); 
    } catch (error) {
        console.log(error.response.data);
    }
}