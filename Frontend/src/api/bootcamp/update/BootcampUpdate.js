import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';

export async function updateBootcamp(id, bootcampEdited) {
    const instance = createBasicAxiosInstance();
    try {
        const response = await instance.put(`bootcamps/bootcamp/${id}`, bootcampEdited);
        return response.data;
    } catch(error) {
        console.log(error.response.data)
    }
}

const BootcampUpdate = {
    updateBootcamp
}

export default BootcampUpdate;