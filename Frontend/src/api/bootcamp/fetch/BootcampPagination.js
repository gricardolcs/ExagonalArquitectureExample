import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';

const BootcampPagination = async (callback, page, size) => {
    const endpoint = "bootcamps/bootcamp/pagination"
    const url = `${endpoint}?page=${page}&size=${size}&sort=bootcampStartDate,DESC`;
    try {
        const response = await createBasicAxiosInstance().get(url);
        const data = response.data;
        callback(data.content);
    } catch (error) {
        console.log(error);
    }
}

export default BootcampPagination;
