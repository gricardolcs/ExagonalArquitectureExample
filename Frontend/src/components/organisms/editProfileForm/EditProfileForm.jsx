import React, { useRef } from 'react';
import { CURRENT_USER, doNothing } from '../../../utils/constants/constants';
import editProfileFields from './editUserProfileFormFields.json';
import { Ref } from 'semantic-ui-react';
import CustomForm from '../../atoms/customForm/CustomForm';
import { useForm } from 'react-hook-form';
import StructureEditUserProfile from './structure';
import { Grid } from 'semantic-ui-react';
import updateUser from '../../../api/user/updateUser/updateUser';

const EditProfileForm = ({
  onCloseEditForm = doNothing,
  currentUser = CURRENT_USER,
  setCurrentUser = doNothing,
  closeSession = doNothing,
}) => {
  const formRef = useRef(null);

  const defaultValues = {
    photo: currentUser.photo,
    phoneNumber: currentUser.phoneNumber,
    username: currentUser.username,
    email: currentUser.email,
    firstname: currentUser.firstname,
    lastname: currentUser.lastname,
  };

  const {
    register,
    setValue,
    trigger,
    handleSubmit,
    formState: { errors, isValid },
  } = useForm({ defaultValues });

  const updateInformation = (user) => {
    setCurrentUser(user);
    onCloseEditForm();
  };

  const handleUpdateSuccessfully = (response) => {
    const { username, email } = response;
    if (username !== currentUser.username || email !== currentUser.email) {
      updateInformation(response);
      closeSession();
      return;
    }

    updateInformation(response);
  };

  const handleOnSubmit = (data) => {
    let requestData = { ...currentUser, ...data };
    updateUser(requestData, handleUpdateSuccessfully, updateInformation);
  };

  return (
    <Grid>
      <Grid.Column width={9}>
        <Ref innerRef={formRef}>
          <CustomForm
            register={register}
            setValue={setValue}
            trigger={trigger}
            errors={errors}
            handleOnSubmit={handleSubmit(handleOnSubmit)}
            handleOnCancel={onCloseEditForm}
            buttonLabel='Save'
            structure={StructureEditUserProfile}
            fields={editProfileFields}
            defaultValues={defaultValues}
            isValid={isValid}
          />
        </Ref>
      </Grid.Column>
    </Grid>
  );
};

export default EditProfileForm;
