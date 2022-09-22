import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import SettingsList from '../../components/organisms/settingsList/SettingList';
import SettingTemplate from '../../components/templates/settingsTemplate/SettingsTemplate';
import { SettingsPaths } from '../../enum/availableRoutes';
import { doNothing, SESSION_COMPONENT } from '../../utils/constants/constants';
import initPage from '../../helpers/page/initPage';
import { UsersAndRolesPage } from '../usersAndRolesPage/UsersAndRolesPage';
import { UserSettingsPage } from '../userSettingsPage/UserSettingsPage';
import { SkillsCategoryPage } from '../skillsCategoryPage/SkillsCategoryPage';
import './style.css';

const availableSettings = {
  users: {
    id: 1,
    title: 'Users and Roles',
    items: [
      {
        label: 'Users',
        component: SettingsPaths.USERS_ROLES.DEFAULT,
      },
      {
        label: 'User Settings',
        component: SettingsPaths.PASSWORD_EXPIRATION,
      },
    ],
  },
  applicants: {
    id: 2,
    title: 'Applicants',
    items: [
      {
        label: 'Skills',
        component: SettingsPaths.APPLICANTS,
      },
    ],
  },
};

const SettingsPage = ({ setComponent = doNothing, component = '' }) => {
  useEffect(() => {
    initPage(
      {
        defaultPath: SettingsPaths.DEFAULT,
        variable: SESSION_COMPONENT,
      },
      setComponent
    );
  }, [setComponent]);

  const returnToSettingsPage = () => {
    sessionStorage.setItem(SESSION_COMPONENT, SettingsPaths.DEFAULT);
    setComponent(SettingsPaths.DEFAULT);
  };

  const renderSetup = (
    { Component, returnToPage, previousLocationName },
    isExactComponent
  ) => {
    return isExactComponent ? (
      <SettingTemplate
        onReturn={returnToPage}
        previousLocationName={previousLocationName}
      >
        <Component />
      </SettingTemplate>
    ) : (
      <Component />
    );
  };

  return (
    <div className='setup-page'>
      {component === SettingsPaths.DEFAULT && (
        <SettingsList data={availableSettings} setComponent={setComponent} />
      )}
      {component.includes(SettingsPaths.USERS_ROLES.DEFAULT) &&
        renderSetup(
          {
            Component: () => (
              <UsersAndRolesPage
                setComponent={setComponent}
                component={component}
                renderSetup={renderSetup}
              />
            ),
            returnToPage: returnToSettingsPage,
            previousLocationName: 'Setups',
          },
          component === SettingsPaths.USERS_ROLES.DEFAULT
        )}
      {component === SettingsPaths.APPLICANTS &&
        renderSetup(
          {
            Component: () => <SkillsCategoryPage />,
            returnToPage: returnToSettingsPage,
            previousLocationName: 'Setups',
          },
          component === SettingsPaths.APPLICANTS
        )}
      {component === SettingsPaths.PASSWORD_EXPIRATION &&
        renderSetup(
          {
            Component: () => <UserSettingsPage />,
            returnToPage: returnToSettingsPage,
            previousLocationName: 'Setups',
          },
          component === SettingsPaths.PASSWORD_EXPIRATION
        )}
    </div>
  );
};

export default SettingsPage;

SettingsPage.prototype = {
  setComponent: PropTypes.func,
  component: PropTypes.string,
};
