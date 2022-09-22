import { AuthenticationEnum } from '../enum/contextActions';
import { doNothing } from '../utils/constants/constants';

const login = (dispatch) =>
  dispatch({ type: AuthenticationEnum.login, payload: true });

const logout = (dispatch) =>
  dispatch({ type: AuthenticationEnum.logout, payload: false });

export default function ApplicationContextActions(dispatch = doNothing) {
  return {
    onLogin: () => login(dispatch),
    onLogout: () => logout(dispatch),
  };
}
