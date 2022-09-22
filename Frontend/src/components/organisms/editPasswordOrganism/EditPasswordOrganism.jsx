import React, { useRef, useCallback, useEffect } from 'react';
import CustomButton from '../../atoms/customButton/CustomButton';
import useEditForm from '../../../hooks/useEditForm';
import editPasswordFormFields from './editPasswordProfileFormFields.json';
import { Grid, Ref } from 'semantic-ui-react';
import { useForm } from 'react-hook-form';
import CustomForm from '../../atoms/customForm/CustomForm';
import StructureEditPasswordUserProfile from './structure';
import updatePassword from '../../../api/user/updatePassword/updatePassword';
import {
  CURRENT_USER,
  doNothing,
  errorMessages,
} from '../../../utils/constants/constants';
import { passwordValidation } from '../../../helpers/validation/fieldValidation';
import {
  NEW_PASSWORD,
  PASSWORD_CONFIRMATION,
} from '../../../utils/constants/formFields';
import './style.css';

const EditPasswordOrganism = ({
  currentUser = CURRENT_USER,
  closeSession = doNothing,
}) => {
  const formRef = useRef(null);
  const { isEnableEditForm, hideEditForm, showEditForm } = useEditForm();

  const {
    register,
    setValue,
    trigger,
    getValues,
    handleSubmit,
    formState: { errors, isValid },
  } = useForm();

  const validatePasswordConfirmation = useCallback(() => {
    passwordValidation({
      field: PASSWORD_CONFIRMATION,
      fieldToCompare: NEW_PASSWORD,
      errorMessage: errorMessages.confirmPasswordDoesNotMatch,
      getValues,
      register,
    });
  }, [getValues, register]);

  useEffect(() => {
    validatePasswordConfirmation();
  }, [validatePasswordConfirmation]);

  const handleOnSubmit = (data) => {
    const { passwordConfirmation, ...rest } = data;
    updatePassword(rest, currentUser.id).then(() => closeSession());
  };

  return (
    <div className='edit-password-section'>
      {isEnableEditForm ? (
        <Grid>
          <Grid.Column width={9}>
            <Ref innerRef={formRef}>
              <CustomForm
                register={register}
                setValue={setValue}
                trigger={trigger}
                errors={errors}
                handleOnSubmit={handleSubmit(handleOnSubmit)}
                handleOnCancel={hideEditForm}
                buttonLabel='Save'
                structure={StructureEditPasswordUserProfile}
                fields={editPasswordFormFields}
                isValid={isValid}
              />
            </Ref>
          </Grid.Column>
        </Grid>
      ) : (
        <>
          <div>Password</div>
          <div className='edit-password-form'>
            <div>****************************</div>
            <CustomButton
              label='Edit'
              color='blue'
              onClick={showEditForm}
              circular={false}
            />
          </div>
        </>
      )}
    </div>
  );
};

export default EditPasswordOrganism;
