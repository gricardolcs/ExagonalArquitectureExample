import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export async function createBootcamp(bootcamp) {
  const axiosInstance = APIRESTConnectionFactory.createBasicAxiosInstance();
  try {
    return await axiosInstance.post(
      `bootcamps/bootcamp`,
      bootcamp);
  } catch (error) {
    console.log(error.response.data);
  }
}

const BootcampCreate = {
  createBootcamp,
}

export default BootcampCreate;
