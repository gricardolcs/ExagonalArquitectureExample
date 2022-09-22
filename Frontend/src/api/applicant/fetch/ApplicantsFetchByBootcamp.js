import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function getApplicantsByBootcampId(bootcampId, unpaged, page, size, callback) {
    const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
    const url = `applicants/applicant/bootcamp/${bootcampId}`
    try {
        const response = await instance.get(url, {
            params: {
                unpaged: unpaged,
                page: page,
                size: size,
                sort: 'fullName,ASC'
            }
        });
        callback(response.data)
    } catch (error) {
        console.log(error.response.data);
    }
}