import React from 'react';
import PropTypes from 'prop-types';
import UserProfile from '../../organisms/userProfile/UserProfile';
import {
  doNothing,
  headerProfileCard,
  SESSION_COMPONENT,
} from '../../../utils/constants/constants';
import ComponentCard from '../../molecules/componentCard/ComponentCard';
import UserForm from '../../organisms/userForm/UserForm';
import { useForm } from 'react-hook-form';
import userFields from '../../../data/userForm/userFields.json';
import StructureUserForm from '../../../data/userForm/structure';
import statusData from '../../../data/status/status.json';
import useEditForm from '../../../hooks/useEditForm';
import updateUser from '../../../api/user/updateUser/updateUser';
import fetchAllUser from '../../../api/user/fetchAllUser/FetchAllUser';
import { SettingsPaths } from '../../../enum/availableRoutes';
import './style.css';

const UserProfileTemplate = ({
  setComponent = doNothing,
  currentUser = {},
  refreshUserList = doNothing,
}) => {
  const { isEnableEditForm, hideEditForm, showEditForm } = useEditForm();

  const bodyUserProfile = () => (
    <UserProfile
      setComponent={setComponent}
      currentUser={currentUser}
      enableEditForm={showEditForm}
    />
  );

  const onResolve = (data) => {
    fetchAllUser(refreshUserList);
    sessionStorage.setItem(
      SESSION_COMPONENT,
      SettingsPaths.USERS_ROLES.DEFAULT
    );
    setComponent(SettingsPaths.USERS_ROLES.DEFAULT);
  };

  const handleSubmit = (data) => {
    updateUser({ ...currentUser, ...data }, onResolve, doNothing);
  };

  const getOptions = [
    { propertyName: 'status', name: 'statuses', data: statusData },
  ];

  const bodyEditUser = () => (
    <UserForm
      onClose={hideEditForm}
      onSubmit={handleSubmit}
      currentUser={currentUser}
      dropDownValues={getOptions}
      fields={userFields}
      structure={StructureUserForm}
      useForm={useForm}
    />
  );

  return (
    <div className='user-profile-template'>
      {isEnableEditForm ? (
        <ComponentCard BodyComponent={bodyEditUser} headerText='Edit User' />
      ) : (
        <ComponentCard
          BodyComponent={bodyUserProfile}
          headerText={headerProfileCard}
        />
      )}
    </div>
  );
};

UserProfileTemplate.propTypes = {
  onReturn: PropTypes.func,
  previousLocationName: PropTypes.string,
  children: PropTypes.any,
};

export default UserProfileTemplate;
