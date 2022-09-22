import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { fetchAllUser } from '../../api/user/fetchAllUser/FetchAllUser';
import { SettingsPaths } from '../../enum/availableRoutes';
import initPage from '../../helpers/page/initPage';
import {
  doNothing,
  SESSION_COMPONENT,
  SESSION_USER,
} from '../../utils/constants/constants';
import UserListTemplate from '../../components/templates/userListTemplate/UserListTemplate';

const userColumns = ['Name', 'Username', 'Email', 'Cel Phone', 'Status'];
const rowColumnsFields = [
  'fullname',
  'username',
  'email',
  'phoneNumber',
  'status',
];

export const UsersListPage = ({ setComponent = doNothing }) => {
  const [users, setUsers] = useState([]);
  const loadAllUsers = (users) => {
    setUsers(
      users.map((user) => ({
        ...user,
        fullname: `${user.firstname} ${user.lastname}`,
      }))
    );
  };

  const onClickUser = (user) => {
    sessionStorage.setItem(
      SESSION_COMPONENT,
      SettingsPaths.USERS_ROLES.USER_PROFILE
    );
    sessionStorage.setItem(SESSION_USER, user.id);
    setComponent(SettingsPaths.USERS_ROLES.USER_PROFILE);
  };

  useEffect(() => {
    fetchAllUser(loadAllUsers);
    initPage(
      {
        defaultPath: SettingsPaths.USERS_ROLES.DEFAULT,
        variable: SESSION_COMPONENT,
      },
      setComponent
    );
  }, [setComponent]);

  const onCreateUser = () => {
    sessionStorage.setItem(
      SESSION_COMPONENT,
      SettingsPaths.USERS_ROLES.CREATE_USER
    );
    setComponent(SettingsPaths.USERS_ROLES.CREATE_USER);
  };

  return (
    <UserListTemplate
      tableHeaderData={userColumns}
      tableBodyData={users}
      tableFieldsBody={rowColumnsFields}
      onCreateUser={onCreateUser}
      onClickTableRow={onClickUser}
    />
  );
};

UsersListPage.prototype = {
  setComponent: PropTypes.func,
};
