import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';

export default async function updateUser(user, onResolve, onReject) {
  const instance = APIRESTConnectionFactory.createBasicAxiosInstance();
  const url = `authentication/user/${user.id}`;
  try {
    const response = await instance.put(url, user);
    onResolve(response.data);
  } catch (error) {
    onReject(error);
  }
}
