import React from 'react';
import pagesData from './configuration';
import { Switch, Route, Redirect, useLocation } from 'react-router-dom';
import { useApplicationContext } from '../context/ApplicationContext';
import { AvailableRoutes } from '../enum/availableRoutes';

const AppRoutes = () => {
  const { state } = useApplicationContext();
  const location = useLocation();

  const generateRoute = (route, key) => {
    const { Component, path, requireAuthentication } = route;

    function isValidRoute() {
      if (
        (state.isLoggedIn && requireAuthentication) ||
        (!state.isLoggedIn && !requireAuthentication)
      ) {
        return true;
      }
      return false;
    }

    return (
      <Route
        key={key}
        exact
        path={path}
        render={(routeProps) =>
          isValidRoute() ? (
            <Component />
          ) : (
            <Redirect
              to={{
                pathname: requireAuthentication
                  ? AvailableRoutes.LOGIN
                  : location.state?.from.pathname || AvailableRoutes.HOME,
                state: {
                  from: routeProps.location,
                },
              }}
            />
          )
        }
      />
    );
  };

  const routes = pagesData.map(generateRoute);

  return <Switch>{routes}</Switch>;
};

export default AppRoutes;
