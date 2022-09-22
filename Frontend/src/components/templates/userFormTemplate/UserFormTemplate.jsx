import React from 'react';
import PropTypes from 'prop-types';
import { doNothing } from '../../../utils/constants/constants';
import ComponentCard from '../../molecules/componentCard/ComponentCard';
import UserForm from '../../organisms/userForm/UserForm';

function UserFormTemplate({
  onSubmit = doNothing,
  onClose = doNothing,
  dropDownValues = [],
  fields = [],
  structure = [],
  useForm = doNothing,
}) {
  const headerProfileCard = 'Create User';
  const bodyUserProfile = () => (
    <UserForm
      onSubmit={onSubmit}
      onClose={onClose}
      dropDownValues={dropDownValues}
      fields={fields}
      structure={structure}
      useForm={useForm}
    />
  );
  return (
    <>
      <ComponentCard
        BodyComponent={bodyUserProfile}
        headerText={headerProfileCard}
      />
    </>
  );
}

export default UserFormTemplate;

UserFormTemplate.prototype = {
  onSubmit: PropTypes.func,
  onClose: PropTypes.func,
  dropDownValues: PropTypes.array,
  fields: PropTypes.array,
  structure: PropTypes.array,
  useForm: PropTypes.func,
};
