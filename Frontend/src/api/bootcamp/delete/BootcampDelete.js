import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function deleteBootcamp(id) {
  const instance = await APIRESTConnectionFactory.createBasicAxiosInstance();
    return instance.delete(`bootcamps/bootcamp/${id}`)
}

const BootcampDelete = {
  deleteBootcamp
}

export default BootcampDelete;