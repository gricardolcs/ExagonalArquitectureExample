import { AuthenticationEnum } from '../enum/contextActions';

export default function Reducer(state, action) {
  const { type, payload } = action;
  let response;
  switch (type) {
    case AuthenticationEnum.login:
      response = { ...state, isLoggedIn: payload };
      break;
    case AuthenticationEnum.logout:
      response = { ...state, isLoggedIn: payload, recentlyLogout: true };
      break;
    default:
      response = { ...state };
      break;
  }
  return response;
}
