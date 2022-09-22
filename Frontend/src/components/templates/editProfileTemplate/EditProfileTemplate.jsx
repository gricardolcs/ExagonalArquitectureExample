import React, { useState, useEffect } from 'react';
import ComponentCard from '../../molecules/componentCard/ComponentCard';
import EditPasswordOrganism from '../../organisms/editPasswordOrganism/EditPasswordOrganism';
import EditProfileInformation from '../../organisms/editProfileInformation/ProfileInformation';
import { getUserId } from '../../../helpers/jwtSession/jwtSession';
import { CURRENT_USER } from '../../../utils/constants/constants';
import userFindById from '../../../api/user/fetchUserById/FetchUserById';
import { useHistory } from 'react-router-dom';
import { useApplicationContext } from '../../../context/ApplicationContext';
import { AvailableRoutes } from '../../../enum/availableRoutes';

const EditProfileTemplate = () => {
  const { actions } = useApplicationContext();
  const history = useHistory();
  const [currentUser, setCurrentUser] = useState(CURRENT_USER);

  const closeSession = () => {
    sessionStorage.setItem('token', '');
    actions.onLogout();
    history.push(AvailableRoutes.LOGIN);
  };

  useEffect(() => {
    userFindById(getUserId(), setCurrentUser);
  }, []);

  return (
    <>
      <ComponentCard
        headerText='My Profile'
        BodyComponent={() => (
          <EditProfileInformation
            currentUser={currentUser}
            setCurrentUser={setCurrentUser}
            closeSession={closeSession}
          />
        )}
      />
      <ComponentCard
        headerText='My password'
        BodyComponent={() => (
          <EditPasswordOrganism
            currentUser={currentUser}
            closeSession={closeSession}
          />
        )}
      />
    </>
  );
};

export default EditProfileTemplate;
