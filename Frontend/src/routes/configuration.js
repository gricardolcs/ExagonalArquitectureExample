import HomePage from '../pages/HomePage/HomePage';
import { LoginPage } from '../pages/LoginPage/LoginPage';
import { AvailableRoutes } from '../enum/availableRoutes';

const pagesData = [
  {
    path: AvailableRoutes.HOME,
    requireAuthentication: true,
    Component: HomePage,
  },
  {
    path: AvailableRoutes.LOGIN,
    requireAuthentication: false,
    Component: LoginPage,
  },
];

export default pagesData;
