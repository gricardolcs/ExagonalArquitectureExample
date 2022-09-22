import React, { useCallback, useState } from 'react';
import { Form, Grid, Segment } from 'semantic-ui-react';
import TextField from '../../components/molecules/textField/TextField';
import PasswordField from '../../components/molecules/passwordField/PasswordField';
import CustomButton from '../../components/atoms/customButton/CustomButton';
import CustomLabel from '../../components/atoms/customLabel/CustomLabel';
import { useHistory } from 'react-router-dom';
import { loginUser } from '../../api/login/LoginUser';
import CustomField from '../../components/atoms/customField/CustomField';
import { useApplicationContext } from '../../context/ApplicationContext';
import { AvailableRoutes } from '../../enum/availableRoutes';
import { getAccountStatus } from '../../helpers/jwtSession/jwtSession';
import UserStatus from '../../enum/userStatus';
import './style.css';

export const LoginPage = () => {
  const { state, actions } = useApplicationContext();
  const [usernameOrEmail, setUsernameOrEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isDataCorrect, setIsDataCorrect] = useState(true);
  const [badStatusMessage, setBadStatusMessage] = useState(undefined);
  const history = useHistory();

  const handleLoginError = () => {
    setIsDataCorrect(false);
  };

  const showAlert = (message) => message && alert(message);

  const showBadStatusMessage = useCallback((status) => {
    const ERROR_MESSAGE = '* Your account %s, please contact the administrator';
    const userErrorMessages = {
      BLOCKED: {
        type: UserStatus.BLOCKED,
        value: ERROR_MESSAGE.replace('%s', 'has been BLOCKED'),
      },
      INACTIVE: {
        type: UserStatus.INACTIVE,
        value: ERROR_MESSAGE.replace('%s', 'is INACTIVE'),
      },
    };
    setBadStatusMessage(userErrorMessages[status]);
  }, []);

  const handleLoginSuccess = useCallback(
    (data) => {
      const status = getAccountStatus(data.token);
      if (status === UserStatus.ACTIVE) {
        sessionStorage.setItem('token', data.token);
        clearFormFields();
        actions.onLogin();
        history.push(AvailableRoutes.HOME);
        showAlert(data.message);
      } else {
        showBadStatusMessage(status);
      }
    },
    [history, actions, showBadStatusMessage]
  );

  const clearFormFields = () => {
    setUsernameOrEmail('');
    setPassword('');
  };

  const handleOnSubmit = useCallback(async () => {
    await loginUser(
      { usernameOrEmail, password },
      handleLoginSuccess,
      handleLoginError
    );
  }, [usernameOrEmail, handleLoginSuccess, password]);

  return (
    <>
      <Grid textAlign='center' className='content-login' verticalAlign='middle'>
        <Grid.Column className='login-form'>
          {state.recentlyLogout && (
            <div className='enabled-logout'>
              You are signed out of ATS application
            </div>
          )}
          <Form>
            <Segment className='login-segment-form'>
              {badStatusMessage && (
                <div
                  className={
                    badStatusMessage.type === UserStatus.BLOCKED
                      ? 'login-blocked-message'
                      : 'login-inactive-message'
                  }
                >
                  {badStatusMessage.value}
                </div>
              )}
              <CustomLabel
                value={'Login'}
                size={'massive'}
                variant={'header-login'}
                color={'grey'}
              />

              <CustomField>
                <TextField
                  value={usernameOrEmail}
                  labelValue='Username or Email'
                  labelWidth='5'
                  labelColor='grey'
                  labelSize='large'
                  onChange={setUsernameOrEmail}
                  placeholder='Username'
                  labelVariant='user-email-login-field'
                  inputSize={'large'}
                  required
                />
                <PasswordField
                  value={password}
                  labelValue='Password'
                  labelWidth='5'
                  labelColor='grey'
                  labelSize='large'
                  onChange={setPassword}
                  placeholder='Password'
                  labelVariant='password-login-field'
                  inputSize={'large'}
                  required
                />
              </CustomField>
              {!isDataCorrect && (
                <CustomLabel
                  value={'Username or password are not correct'}
                  size={'large'}
                  variant={'error-message-login'}
                  color={'red'}
                />
              )}
              <CustomButton
                onClick={handleOnSubmit}
                label={'Sign in'}
                type='Submit'
                circular={false}
                color='blue'
                variant='login-button'
                size={'large'}
                fluid
              />
            </Segment>
          </Form>
        </Grid.Column>
      </Grid>
    </>
  );
};
