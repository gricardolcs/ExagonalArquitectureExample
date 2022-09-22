import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import createUser from '../../api/user/createUser/CreateUser';
import UserFormTemplate from '../../components/templates/userFormTemplate/UserFormTemplate';
import { SettingsPaths } from '../../enum/availableRoutes';
import initPage from '../../helpers/page/initPage';
import { doNothing, SESSION_COMPONENT } from '../../utils/constants/constants';
import userFields from '../../data/userForm/userFields.json';
import StructureUserForm from '../../data/userForm/structure';
import { useForm } from 'react-hook-form';
import statusData from '../../data/status/status.json';
import './style.css';

const UserFormPage = ({
  setComponent = doNothing,
  component = '',
  returnToPage = doNothing,
}) => {
  useEffect(() => {
    initPage(
      {
        defaultPath: SettingsPaths.USERS_ROLES.CREATE_USER,
        variable: SESSION_COMPONENT,
      },
      setComponent
    );
  }, [setComponent]);

  const handleClose = () => {
    returnToPage();
  };

  const handleSave = (user) => {
    createUser(user, returnToPage);
  };

  const getOptions = [
    { propertyName: 'status', name: 'statuses', data: statusData },
  ];

  return (
    <div className='create-user-page'>
      {component === SettingsPaths.USERS_ROLES.CREATE_USER && (
        <UserFormTemplate
          onSubmit={handleSave}
          onClose={handleClose}
          dropDownValues={getOptions}
          fields={userFields}
          structure={StructureUserForm}
          useForm={useForm}
        />
      )}
    </div>
  );
};

export default UserFormPage;

UserFormPage.prototype = {
  setComponent: PropTypes.func,
  component: PropTypes.string,
  returnToPage: PropTypes.func,
};

