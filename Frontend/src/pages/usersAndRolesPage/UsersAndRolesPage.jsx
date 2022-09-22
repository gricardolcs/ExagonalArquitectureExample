import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import { UsersListPage } from '../usersListPage/UsersListPage';
import { SettingsPaths } from '../../enum/availableRoutes';
import initPage from '../../helpers/page/initPage';
import { doNothing, SESSION_COMPONENT } from '../../utils/constants/constants';
import UserFormPage from '../userFormPage/UserFormPage';
import UserProfilePage from '../userProfilePage/UserProfilePage';
import './style.css';

export const UsersAndRolesPage = ({
  setComponent = doNothing,
  component = '',
  renderSetup = doNothing,
}) => {
  useEffect(() => {
    initPage(
      {
        defaultPath: SettingsPaths.USERS_ROLES.DEFAULT,
        variable: SESSION_COMPONENT,
      },
      setComponent
    );
  }, [setComponent]);

  const returnToUsersPage = () => {
    sessionStorage.setItem(
      SESSION_COMPONENT,
      SettingsPaths.USERS_ROLES.DEFAULT
    );
    setComponent(SettingsPaths.USERS_ROLES.DEFAULT);
  };

  return (
    <div className='user-page'>
      {component === SettingsPaths.USERS_ROLES.DEFAULT && (
        <UsersListPage setComponent={setComponent} renderSetup={renderSetup} />
      )}
      {component === SettingsPaths.USERS_ROLES.CREATE_USER &&
        renderSetup(
          {
            Component: () => (
              <UserFormPage
                setComponent={setComponent}
                component={component}
                returnToPage={returnToUsersPage}
              />
            ),
            returnToPage: returnToUsersPage,
            previousLocationName: 'Users',
          },
          component === SettingsPaths.USERS_ROLES.CREATE_USER
        )}
      {component === SettingsPaths.USERS_ROLES.USER_PROFILE &&
        renderSetup(
          {
            Component: () => (
              <UserProfilePage
                setComponent={setComponent}
                component={component}
                returnToPage={returnToUsersPage}
              />
            ),
            returnToPage: returnToUsersPage,
            previousLocationName: 'Users',
          },
          component === SettingsPaths.USERS_ROLES.USER_PROFILE
        )}
    </div>
  );
};

UsersAndRolesPage.prototype = {
  setComponent: PropTypes.func,
  component: PropTypes.string,
  renderSetup: PropTypes.func,
};
