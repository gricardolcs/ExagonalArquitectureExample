import React, { Fragment, useEffect, useState } from 'react';
import { Menu } from 'semantic-ui-react';
import HeaderTemplate from '../../components/templates/headerTemplate/HeaderTemplate';
import NavBarTemplate from '../../components/templates/navBarTemplate/NavBarTemplate';
import ApplicantsListPage from '../applicantsListPage/ApplicantsListPage';
import BootcampListPage from '../bootcampListPage/BootcampListPage';
import SettingsPage from '../SettingsPage/SettingsPage';
import { DefaultPaths } from '../../enum/availableRoutes';
import { SESSION_COMPONENT } from '../../utils/constants/constants';
import './style.css';
import initPage from '../../helpers/page/initPage';
import EditProfilePage from '../editProfile/EditProfilePage';

function HomePage() {
  const [component, setComponent] = useState('');
  const appName = 'Recruitment System';
  const routes = [
    { activeIndex: 0, component: DefaultPaths.APPLICANTS },
    { activeIndex: 1, component: DefaultPaths.BOOTCAMPS },
    { activeIndex: 2, component: DefaultPaths.SETTINGS },
  ];

  const handleOnClickTab = (event, data) => {
    const routeFound = getRouteByActiveIndex(data.activeIndex);
    if (routeFound) {
      sessionStorage.setItem(SESSION_COMPONENT, routeFound.component);
      setComponent('');
    }
  };

  const getRouteByActiveIndex = (activeIndex) => {
    return routes.find((item) => item.activeIndex === activeIndex);
  };

  const getRouteByComponent = (component) => {
    return component !== null
      ? routes.find((item) => component.includes(item.component))
      : undefined;
  };

  const getDefaultTab = () => {
    const routeFound = getRouteByComponent(
      sessionStorage.getItem(SESSION_COMPONENT)
    );
    if (routeFound !== undefined) {
      return routeFound.activeIndex;
    }
    return routes[0].activeIndex;
  };

  const panes = [
    {
      menuItem: (
        <Menu.Item key='applicants'>
          <p>Applicants</p>
        </Menu.Item>
      ),
    },
    {
      menuItem: (
        <Menu.Item key='projects'>
          <p>Projects</p>
        </Menu.Item>
      ),
    },
  ];

  useEffect(() => {
    initPage(
      {
        defaultPath: DefaultPaths.APPLICANTS,
        variable: SESSION_COMPONENT,
      },
      setComponent
    );
  }, [component, setComponent]);

  return (
    <Fragment>
      {component !== 'Signin' && (
        <div className={'home-page'}>
          <HeaderTemplate
            appName={appName}
            content={
              <NavBarTemplate
                panes={panes}
                updateActivePane={handleOnClickTab}
                defaultActiveIndex={getDefaultTab()}
              />
            }
            setComponent={setComponent}
          />
          {component.includes(DefaultPaths.APPLICANTS) && (
            <ApplicantsListPage />
          )}
          {component.includes(DefaultPaths.BOOTCAMPS) && <BootcampListPage />}
          {component.includes(DefaultPaths.SETTINGS) && (
            <SettingsPage setComponent={setComponent} component={component} />
          )}
          {component.includes(DefaultPaths.EDIT_PROFILE) && <EditProfilePage />}
        </div>
      )}
    </Fragment>
  );
}

export default HomePage;
