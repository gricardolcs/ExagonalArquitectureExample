import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import userFindById from '../../api/user/fetchUserById/FetchUserById';
import UserProfileTemplate from '../../components/templates/userProfileTemplate/UserProfileTemplate';
import { SettingsPaths } from '../../enum/availableRoutes';
import { doNothing, SESSION_USER } from '../../utils/constants/constants';

const UserProfilePage = ({ setComponent = doNothing, component = '' }) => {
  const [user, setUser] = useState({});

  useEffect(() => {
    const idUser = sessionStorage.getItem(SESSION_USER);
    userFindById(idUser, setUser);
  }, []);
  return (
    <div className='profile-user-page'>
      {component === SettingsPaths.USERS_ROLES.USER_PROFILE && (
        <UserProfileTemplate
          setComponent={setComponent}
          currentUser={user}
          refreshUserList={setUser}
        />
      )}
    </div>
  );
};

export default UserProfilePage;

UserProfilePage.prototype = {
  setComponent: PropTypes.func,
  component: PropTypes.string,
};
