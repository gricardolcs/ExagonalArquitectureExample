import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function uploadFile(applicantId, bootcampId, file) {
    const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
    const url = `applicants/applicant/${applicantId}/bootcamp/${bootcampId}/report`;
    try {
        const formData = new FormData();
        formData.append('file', file);
        const response = await instance.post(url, formData, {
            headers: {
                'content-type': 'multipart/form-data'
            }
        });
        return response.data; 
    } catch (error) {
        console.log(error.response.data);
    }
}