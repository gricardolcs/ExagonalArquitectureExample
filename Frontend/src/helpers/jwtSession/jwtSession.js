import jwtDecode from 'jwt-decode';

const getToken = () => {
  return sessionStorage.getItem('token');
};

const parseToken = (token = null) =>
  token ? jwtDecode(token) : jwtDecode(getToken());

const getTokenAttribute = (attribute, token) => {
  const tokenInformation = parseToken(token);
  return tokenInformation ? tokenInformation[attribute] : '';
};

export const getName = () => {
  const name = getTokenAttribute('name');
  return name ? name.split(' ')[0] : '';
};

export const getEmail = () => {
  return getTokenAttribute('email');
};

export const getAccountStatus = (token) => {
  return getTokenAttribute('status', token);
};

export const getUserId = () => {
  return getTokenAttribute('id');
};
